package board.model.service;

import board.model.dao.BoardDAO;
import board.model.vo.Attachment;
import board.model.vo.Board;
import board.model.vo.PageInfo;
import board.model.vo.Reply;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;
public class BoardService {
	
	private BoardDAO bDAO = new BoardDAO();
	
	public int getListCount() {
		
		Connection conn = getConnection();
		
		int listCount = bDAO.getListCount(conn);
		
		close(conn);
		
		
		return listCount;
	}

	public ArrayList<Board> selectList(PageInfo pi) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		
		ArrayList<Board> list = bDAO.selectList(conn, pi);
		
		close(conn);
		
		return list;
	}

	public String selectCate(int categoryNum) {
		Connection conn = getConnection();
		
		String category = bDAO.selectCate(conn, categoryNum);
		
		close(conn);
		
		
		return category;
	}

	public int insertBoard(Board b) {
		Connection conn = getConnection();
		
		int result = bDAO.insertBoard(conn, b);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

	public Board selectBoard(int bId, String upd) {
		Connection conn = getConnection();
		
		int result = 0;
		if(!(upd != null && upd.equals("Y"))) {
			result = bDAO.updateCount(conn, bId);
		}
		
		Board b = bDAO.selectBoard(conn, bId);
		
		if(result > 0 && b!= null) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		return b;
	}

	public int updateBoard(Board b) {
		Connection conn = getConnection();
		
		int result1 = bDAO.getCateNum(conn, b);
		
		b.setCategoryNum(result1);
		int result2 = bDAO.updateBoard(conn,b);
		
		if(result2>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result2;
	}

	public int deleteBoard(int bId) {
		Connection conn = getConnection();
		
		int result = bDAO.deleteBoard(conn, bId);
		
		if(result >  0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	//같은 메소드로 반환이 2가지니까 <>를 지운다.
	public ArrayList selectTList(int i) {
		Connection conn = getConnection();
		
		ArrayList list = null;
		if(i==1) {
			list = bDAO.selectBList(conn);
		}else {
			list = bDAO.selectFList(conn);
		}
		
		close(conn);
		
		return list;
	}

	public int insertThumbnail(Board b, ArrayList<Attachment> fileList) {
		Connection conn = getConnection();
		
		int result1 = bDAO.insertBoard(conn, b);
		int result2 = bDAO.insertAttachment(conn, fileList);
		
		//System.out.println(result1);
		//System.out.println(result2);
		
		if(result1 > 0 && result2>0) {
			commit(conn);
			
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result1+result2;
	}

	public ArrayList<Attachment> selectThumbnail(int bId) {
		Connection conn = getConnection();
		ArrayList<Attachment> list = bDAO.selectThumbnail(bId, conn);
		close(conn);
		return list;
	}

	public ArrayList<Reply> selectReplyList(int bId) {
		Connection conn = getConnection();
		
		ArrayList<Reply> list = bDAO.selectReplyList(conn, bId);
		
		close(conn);
		
		return list;
	}

	public ArrayList<Reply> insertReply(Reply r) {
		Connection conn = getConnection();
		
		int result = bDAO.insertReply(conn, r);
		
		ArrayList<Reply> list = null;
		if(result > 0) {
			list = bDAO.selectReplyList(conn, r.getRefBid());
			
			if(list != null) {
				commit(conn);
			}else {
				rollback(conn);
			}
			
		}else {
			rollback(conn);
		}
		close(conn);
		
		return list;
	}

}
