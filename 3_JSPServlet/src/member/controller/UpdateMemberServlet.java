package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class UpdateMemberServlet
 */
@WebServlet("/update.me")
public class UpdateMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String userId = request.getParameter("myId");
		String userName = request.getParameter("myName");
		String nickName = request.getParameter("myNickName");
		String phone = request.getParameter("myPhone");
		String email = request.getParameter("myEmail");
		String address = request.getParameter("myAddress");
		String[] interest = request.getParameterValues("myInterest");
		
		String strInterest = "";
		if(interest != null) {
			for(int i = 0; i<interest.length; i++) {
				if(i==0) {
					strInterest += interest[i];
				}else {
					strInterest += ", " + interest[i];
				}
			}
		}
		
		Member m = new Member(userId, null, userName, nickName, phone, email, address, strInterest,null,null,null);
		//request.setAttribute("myInfo", m);
		
		// insert이기 때문에 반환값 int로 지정
		int result = new MemberService().updateMember(m);
				
			if(result > 0) {
				
				//수정된 항목이 있다면
				//세션의 정보를 새로 select해와서 세션안의  member의 값을 업데이트하는 부분.
				Member loginUser = new MemberService().selectMember(userId);
				request.getSession().setAttribute("loginUser", loginUser);
				response.sendRedirect("myPage.me");
				// myPage앞에 슬래시를 붙이면 컨텍스트패스가 사라지게되어 쓰면 안됨.
				
				//request.getRequestDispatcher("WEB-INF/views/member/myPage.jsp").forward(request, response);
			}else {
				request.setAttribute("msg", "회원수정에 실패했습니다.");
				request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp").forward(request, response);
				
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
