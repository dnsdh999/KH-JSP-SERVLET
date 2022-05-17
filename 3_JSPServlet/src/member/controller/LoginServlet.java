package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name="LoginServlet", urlPatterns="/login.me")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("UTF-8");
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		// 변경시이거보고변경 db에복붙
		//System.out.println(userPwd);
		
		Member loginUser = new MemberService().loginMember(userId,userPwd);
		
		//System.out.println(loginUser.toString());
		
		//1. select count(*)를 사용하지 않은 이유는 계정이 있다/없다만 판단하는것이 아니라 계정정보를 이용하기 위해서
		//2. 계정 정보를이용해 첫번째 가지고 온 데이터를 메인화면에 뿌리기 : forward()
		//3. 로그인 후 다른 서비스를 이용할 때 계속해서 추가적인 로그인을 하지 않음 : session 영역 활용
		if(loginUser != null) {
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(6000); 	//6000초 뒤 세션 종료
			session.setAttribute("loginUser", loginUser);
			response.sendRedirect(request.getContextPath());
		}else {
			request.setAttribute("msg", "로그인 실패");
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp");
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
