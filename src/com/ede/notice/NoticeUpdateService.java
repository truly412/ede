package com.ede.notice;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ede.action.Action;
import com.ede.action.ActionFoward;
import com.ede.board.BoardDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class NoticeUpdateService implements Action {

	@Override
	public ActionFoward doProcess(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		String method = request.getMethod();
		NoticeDAO noticeDAO = new NoticeDAO();
		BoardDTO boardDTO = null;
		if(method.equals("POST")) {
			boardDTO = new BoardDTO();
			int result=0;
			String filePath = request.getServletContext().getRealPath("upload");
			File file = new File(filePath);
			if(!file.exists()) {
				file.mkdirs();
			}
			int maxSize=1024*1024*10;
			try {
				MultipartRequest multi = new MultipartRequest(request, filePath, maxSize, "UTF-8", new DefaultFileRenamePolicy());
				boardDTO.setNum(Integer.parseInt(multi.getParameter("num")));
				boardDTO.setTitle(multi.getParameter("title"));
				boardDTO.setContents(multi.getParameter("contents"));
				System.out.println(multi.getParameter("contents"));
				result=noticeDAO.update(boardDTO);
				
			} catch (Exception e) {
				
				// TODO: handle exception
			}
			if(result>0) {
				actionFoward.setCheck(false);
				actionFoward.setPath("./noticeList.notice");
			}else {
				request.setAttribute("message", "Fail");
				request.setAttribute("path", "./noticeList.notice");
				actionFoward.setCheck(true);
				actionFoward.setPath("../WEB-INF/view/common/result.jsp");
				
			}
			
			
		}else {
			int num=0;
			try {
			num = (Integer.parseInt(request.getParameter("num")));
			boardDTO=noticeDAO.selectOne(num);
			}catch (Exception e) {
				// TODO: handle exception
			}
			if(boardDTO != null) {
				request.setAttribute("view", boardDTO);
				actionFoward.setPath("../WEB-INF/view/board/boardUpdate.jsp");
				request.setAttribute("board", "notice");
				request.setAttribute("title", "공지사항");
			}else {
				request.setAttribute("message", "Fail");
				request.setAttribute("path", "./noticeList.notice");
				actionFoward.setPath("../WEB-INF/view/common/result.jsp");
			}
			actionFoward.setCheck(true);
			
		}
		
		
		return actionFoward;
	}

}
