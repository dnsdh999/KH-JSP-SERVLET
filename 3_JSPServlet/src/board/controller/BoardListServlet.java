package board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.Board;
import board.model.vo.PageInfo;

/**
 * Servlet implementation class BoardListServlet
 */
@WebServlet("/list.bo")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 페이징 처리에 필요한 변수
		int listCount;		// 총 게시글 개수
		int currentPage;	// 현재 페이지
		int pageLimit;		// 한 페이지에서 보일 페이지 수
		int boardLimit;		// 한 페이지에서 보일 게시글 수
		int maxPage;		// 가장 마지막 페이지
		int startPage;		// 페이징이 된 페이지 중 시작 페이지
		int endPage;		// 페이징이 된 페이지 중 마지막 페이지
		
		//서비스 2번왔다갔다 할것
		BoardService bService = new BoardService();
		
		// 페이징처리 1단계 : 몇 페이지가 나오는지 계산하기 위해 전체 게시글 개수를 조회해야함
		listCount = bService.getListCount();
		
		//페이징 처리 2단계 : 현재 페이지 설정
		currentPage = 1;
		//들어오면 바로 페이지가 1이기 때문에 처음에 1로 지정
		if(request.getParameter("currentPage") != null) {
			// currentPage가 널이 아니다 -> 파라미터로 currentPage가 들어왔다 (요청받았다) -> 페이징처리가된 버튼을(1,2,3,,,) 눌렀다.
			
			//currentPage가 널이다 - > //페이징 처리를 누르지 않은(이 탭에 처음 들어왔을때 currentPage가 널이 되게 됨.)
			// 이런 경우 1로 하기로 할것임.
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		//페이징처리 3단계 : boardLimit의 pageLimit설정
		pageLimit = 10;
		boardLimit = 10;
		
		//페이징처리 4단계 : 각 변수에 알맞은 계산식
		maxPage = (int)Math.ceil((double)listCount / boardLimit);
		
		//int n = (int)Math.floor(((double)(currentPage-1) / pageLimit));
		
		startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		endPage = startPage + pageLimit - 1;
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		
		PageInfo pi = new PageInfo(currentPage, listCount, pageLimit, boardLimit, maxPage, startPage, endPage);
		
		ArrayList<Board> list = bService.selectList(pi);
		
		System.out.println(list);
		
		String page = null;
		if(list != null) {
			page = "WEB-INF/views/board/boardList.jsp";
			request.setAttribute("list", list);
			request.setAttribute("pi", pi);
		}else {
			page = "WEB-INF/views/common/errorPage.jsp";
			request.setAttribute("msg", "게시글조회실패");
		}
		
		request.getRequestDispatcher(page).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
