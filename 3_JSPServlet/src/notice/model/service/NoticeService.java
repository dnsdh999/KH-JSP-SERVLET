package notice.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import notice.model.dao.NoticeDAO;
import notice.model.vo.Notice;

public class NoticeService {

	private NoticeDAO nDAO = new NoticeDAO();
	
	public ArrayList<Notice> selectList() {
		Connection conn = getConnection();
		
		ArrayList<Notice> list = nDAO.selectList(conn);
		
		close(conn);
		
		return list;
	}


	public int insertNotice(Notice n) {
	      Connection conn = getConnection();
	      
	      int result = nDAO.insertNotice(conn, n);
	      
	      if(result > 0) {
	         commit(conn);
	      }else {
	         rollback(conn);
	      }
	      close(conn);
	      
	      return result;
	   }


	public Notice selectNotice(int no) {
		Connection conn = getConnection();
		
		int result = nDAO.updateCount(conn, no);
		
		Notice notice = null;
		
		if(result > 0) {
			notice = nDAO.selectNotice(conn, no);
			if(notice != null) {
				commit(conn);
			}else {
				rollback(conn);
			} 
			}else {
				rollback(conn);
		}
		
		close(conn);
		
		return notice;
	}


	public int updateNotice(Notice n) {
		Connection conn = getConnection();
		
		int result = nDAO.updateNotice(conn, n);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}


	public int deleteNotice(int no) {
		Connection conn = getConnection();
		
		int result = nDAO.deleteNotice(conn, no);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

}
