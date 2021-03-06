package com.ede.qna;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ede.board.BoardDAO;
import com.ede.board.BoardDTO;
import com.ede.util.MakeRow;
import com.ede.qna.QnaDTO;
import com.ede.util.DBConnector;

public class QnaDAO implements BoardDAO {

	public int getNum() throws Exception {
		Connection con = DBConnector.getConnect();
		String sql ="select qna_seq.nextval from dual";
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		rs.next();
		int num = rs.getInt(1);
		DBConnector.disConnect(rs, st, con);
		
		return num;
	}
	
	@Override
	public int insert(BoardDTO boardDTO) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql ="insert into qna values(?,?,?,?,0,sysdate,?,0,0,0,0)";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, boardDTO.getNum());
		st.setString(2, boardDTO.getTitle());
		st.setString(3, boardDTO.getContents());
		st.setString(4, boardDTO.getWriter());
		st.setInt(5, boardDTO.getNum());
		int result = st.executeUpdate();
		DBConnector.disConnect(st, con);
		
		return result;
	}

	@Override
	public int update(BoardDTO boardDTO) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql="update qna set title=?, contents=? where num=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, boardDTO.getTitle());
		st.setString(2, boardDTO.getContents());
		st.setInt(3, boardDTO.getNum());
		int result = st.executeUpdate();
		DBConnector.disConnect(st, con);
		
		return result;
	}

	@Override
	public int delete(int num) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql ="delete qna where num=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, num);
		int result = st.executeUpdate();
		DBConnector.disConnect(st, con);
		
		return result;
	}

	@Override
	public BoardDTO selectOne(int num) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql ="select * from qna where num=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, num);
		ResultSet rs = st.executeQuery();
		QnaDTO qnaDTO = null;
		if(rs.next()) {
			qnaDTO = new QnaDTO();
			qnaDTO.setNum(rs.getInt("num"));
			qnaDTO.setTitle(rs.getString("title"));
			qnaDTO.setWriter(rs.getString("writer"));
			qnaDTO.setContents(rs.getString("contents"));
			qnaDTO.setReg_date(rs.getDate("reg_date"));
			qnaDTO.setHit(rs.getInt("hit"));
			qnaDTO.setRef(rs.getInt("ref"));
			qnaDTO.setStep(rs.getInt("step"));
			qnaDTO.setDepth(rs.getInt("depth"));
		}
		DBConnector.disConnect(rs, st, con);
		return qnaDTO;
	}

	@Override
	public List<BoardDTO> selectList(MakeRow makeRow) throws Exception {
		List<BoardDTO> ar = new ArrayList<BoardDTO>();
		Connection con = DBConnector.getConnect();
		String sql ="select * from "
				+ "(select rownum R, N.* from "
				+ "(select * from qna where partition=0 and "+makeRow.getKind()+" like ? order by ref desc, step asc) N) "
				+ "where R between ? and ?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%"+makeRow.getSearch()+"%");
		st.setInt(2, makeRow.getStartRow());
		st.setInt(3, makeRow.getLastRow());
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			QnaDTO qnaDTO = new QnaDTO();
			qnaDTO.setNum(rs.getInt("num"));
			qnaDTO.setTitle(rs.getString("title"));
			qnaDTO.setWriter(rs.getString("writer"));
			qnaDTO.setContents(rs.getString("contents"));
			qnaDTO.setReg_date(rs.getDate("reg_date"));
			qnaDTO.setHit(rs.getInt("hit"));
			qnaDTO.setRef(rs.getInt("ref"));
			qnaDTO.setStep(rs.getInt("step"));
			qnaDTO.setDepth(rs.getInt("depth"));
			ar.add(qnaDTO);
		}
		DBConnector.disConnect(rs, st, con);
		
		return ar;
	}

	@Override
	public int getTotalCount(MakeRow makeRow) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql ="select nvl(count(num), 0) from qna where "+makeRow.getKind() +" like ?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%"+makeRow.getSearch()+"%");
		ResultSet rs = st.executeQuery();
		rs.next();
		int totalCount= rs.getInt(1);
		
		DBConnector.disConnect(rs, st, con);
		
		return totalCount;
	}

	@Override
	public int hit(int num) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql ="update qna set hit=hit+1 where num=?";
		
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, num);
		
		int result = st.executeUpdate();
		
		DBConnector.disConnect(st, con);
		
		return result;
	}
	
	public int replyInsert(QnaDTO qnaDTO, QnaDTO parent)throws Exception{
		Connection con = DBConnector.getConnect();
		String sql ="insert into qna values(qna_seq.nextval,?,?,?,0,sysdate,?,?,?,0,0)";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, qnaDTO.getTitle());
		st.setString(2, qnaDTO.getContents());
		st.setString(3, qnaDTO.getWriter());
		st.setInt(4, parent.getRef());
		st.setInt(5, parent.getStep()+1);
		st.setInt(6, parent.getDepth()+1);
		int result = st.executeUpdate();
		DBConnector.disConnect(st, con);
		return result;
	}
	
	public int replyUpdate(QnaDTO qnaDTO) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql = "update qna set step=step+1 where ref=? and step>?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, qnaDTO.getRef());
		st.setInt(2, qnaDTO.getStep());
		int result = st.executeUpdate();
		DBConnector.disConnect(st, con);
		return result;
		
	}
}
