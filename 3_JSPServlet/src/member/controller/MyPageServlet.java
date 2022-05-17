package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MyPageServlet
 */
@WebServlet("/myPage.me")
public class MyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.db에 저장되어있는 내 정보를 select해오는 방법
//		HttpSession session = request.getSession();	//세션 받아오기
//		Member loginUser = (Member)session.getAttribute("loginUser");
//		String userId = loginUser.getUserId();
//		
//		Member member = new MemberService().selectMember(userId);
//		
//		request.setAttribute("member", member);
//		request.getRequestDispatcher("WEB-INF/views/member/myPage.jsp").forward(request, response);
		
		//2.로그인하면서 세션에 저장된 내 정보 불러오는 방법
		request.getRequestDispatcher("WEB-INF/views/member/myPage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
