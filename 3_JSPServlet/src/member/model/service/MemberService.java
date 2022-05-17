package member.model.service;

import static common.JDBCTemplate.*;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;

import member.model.dao.MemberDAO;
import member.model.vo.Member;

public class MemberService {

	private MemberDAO mDAO = new MemberDAO();
	
	public Member loginMember(String userId, String userPwd) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		
		Member loginUser = mDAO.loginMember(userId, userPwd, conn);
		
		close(conn);
		
		return loginUser;
	}

	public int insertMember(Member member) {
		Connection conn = getConnection();
		
		int result = mDAO.insertMember(conn, member);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}

	public int checkId(String inputId) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		
		int result = mDAO.checkId(conn, inputId);
		
		close(conn);
		return result;
	}

	public int checkNick(String inputNick) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		
		int result = mDAO.checkNick(conn, inputNick);
		
		close(conn);
		return result;
	}

	public Member selectMember(String userId) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		
		Member member = mDAO.selectMember(conn, userId);
		
		close(conn);
		
		return member;
	}

	public int updateMember(Member m) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		
		int result = mDAO.updateMember(conn, m);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public int updatePwd(String userId, String userPwd, String newPwd) {
		// TODO Auto-generated method stub
Connection conn = getConnection();
		
		int result = mDAO.updatePwd(conn, userId, userPwd, newPwd);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public int deleteMember(String userId) {
		Connection conn = getConnection();
		
		int result = mDAO.deleteMember(conn, userId);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	

}
