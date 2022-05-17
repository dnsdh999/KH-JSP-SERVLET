package notice.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.service.NoticeService;
import notice.model.vo.Notice;

/**
 * Servlet implementation class UpdateNoticeServlet
 */
@WebServlet("/update.no")
public class UpdateNoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateNoticeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("UTF-8");
		int no = Integer.parseInt(request.getParameter("no"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String date = request.getParameter("date");
		Date sqlDate = null;
	      
	      if(date!="") {
	         String[] dateArr = date.split("-");
	         int year = Integer.parseInt(dateArr[0]);
	         int month = Integer.parseInt(dateArr[1])-1;
	         int day = Integer.parseInt(dateArr[2]);
	         
	         sqlDate = new Date(new GregorianCalendar(year,month,day).getTimeInMillis());
	      }else {
	    	  sqlDate = new Date(new GregorianCalendar().getTimeInMillis());
	      }
		//Notice n = new Notice(no, title, content, null, null, 0, sqlDate, null);
		Notice n = new Notice(no, title, content, sqlDate);
		
		/*
		Notice n = new Notice();
		n.setNoticeNo(no);
		n.setNoticeTitle(title);
		n.setNoticeContent(content);
		n.setNoticeDate(sqlDate);
		*/
		int result = new NoticeService().updateNotice(n);
		
		String page = null;
		if(result > 0) {
			page = "/detail.no?no=" + no;
		}else {
			page = "WEB-INF/views/common/errorPage.jsp";
			request.setAttribute("msg", "공지사항 수정 실패");
		}
		
		RequestDispatcher view = request.getRequestDispatcher(page);
		view.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
