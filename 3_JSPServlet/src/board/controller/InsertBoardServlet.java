package board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.Board;
import member.model.vo.Member;

/**
 * Servlet implementation class InsertBoardServlet
 */
@WebServlet("/insert.bo")
public class InsertBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertBoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("UTF-8");	//핉터적용하자 된디
		String category = request.getParameter("category");
		String boardTitle = request.getParameter("title");
		String boardContent = request.getParameter("content");
		String boardWriter = ((Member)request.getSession().getAttribute("loginUser")).getUserId();
		
		//String category = new BoardService().selectCate(categoryNum);
		
		
		
		
		Board b = new Board();
		b.setBoardType(1);
		b.setCategory(category);
		b.setBoardTitle(boardTitle);
		b.setBoardContent(boardContent);
		b.setBoardWriter(boardWriter);
		
		int result = new BoardService().insertBoard(b);
		
		if(result > 0) {
			response.sendRedirect("list.bo");
		}else {
	         request.setAttribute("msg", "공지사항 등록에 실패하였습니다.");
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
