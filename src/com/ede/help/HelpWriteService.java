package com.ede.help;

import java.io.File;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ede.action.Action;
import com.ede.action.ActionFoward;
import com.ede.files.FileDAO;
import com.ede.files.FileDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class HelpWriteService implements Action {

	@Override
	public ActionFoward doProcess(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		String method=request.getMethod();//GET, POST
		if(method.equals("POST")) {
			HelpDAO helpDAO = new HelpDAO();
			HelpDTO helpDTO = new HelpDTO();
			int result=0;
			int result2=0;
			int num = 0;
			String filePath = request.getServletContext().getRealPath("upload");
			File file = new File(filePath);
			if(!file.exists()) {
				file.mkdirs();
			}
			int maxSize=1024*1024*10;
			try {
				MultipartRequest multi = new MultipartRequest(request, filePath, maxSize, "UTF-8", new DefaultFileRenamePolicy());
				num = helpDAO.getNum();
				helpDTO.setNum(num);
				helpDTO.setWriter(multi.getParameter("writer"));
				helpDTO.setTitle(multi.getParameter("title"));
				helpDTO.setContents(multi.getParameter("contents"));
				try {
					result = helpDAO.insert(helpDTO);
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					result=0;
				}
				if(result>0) {
					Enumeration<Object> names = multi.getFileNames();
					while(names.hasMoreElements()) {
						String name = (String)names.nextElement();
						String fName = multi.getFilesystemName(name);
						String oName = multi.getOriginalFileName(name);						
						FileDTO fileDTO = new FileDTO();
						fileDTO.setoName(oName);
						fileDTO.setfName(fName);
						fileDTO.setNum(num);
						FileDAO fileDAO = new FileDAO();
						try {
							result2 = fileDAO.insert(fileDTO);	
							
						}catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
							result2=0;
						}		            	
					}

						if(result2>0) {
							actionFoward.setCheck(false);
							actionFoward.setPath("./helpList.help");
						}else {
							request.setAttribute("message", "Fail");
							request.setAttribute("path", "./helpList.help");
							actionFoward.setCheck(true);
							actionFoward.setPath("../WEB-INF/view/common/result.jsp");
						}
					
				}else {
					request.setAttribute("message", "Fail");
					request.setAttribute("path", "./helpList.help");
					actionFoward.setCheck(true);
					actionFoward.setPath("../WEB-INF/view/common/result.jsp");
				}



			}
			catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

		}
		
		else {
			request.setAttribute("board", "help");
			request.setAttribute("title", "1:1 문의 게시판");
			actionFoward.setCheck(true);
			actionFoward.setPath("../WEB-INF/view/board/boardWrite.jsp");
		}
		return actionFoward;
	}

}
