package member.controller;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ConfirmMailServlet
 */ 
@WebServlet("/confirmMail.me")
public class ConfirmMailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmMailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//원래는 인코딩 거쳐야함~
		String receiver = request.getParameter("email");
		String title = "[JSP/Servlet] 유효 메일 확인";
		String content = "본 메일은 현재 고객님의 메일이 <b>유효한지 확인</b>하기 위한 메일입니다.";	//html 꾸미기 가능
		
		String host = "smtp.naver.com";
		String sender = "dnsdh999@naver.com";	//실제 보내는 사람의 메일이 유효한 메일
		String senderPwd = "themuseumvisitor";	//그 메일의 실제 비밀번호
		
		Properties prop = new Properties();
		prop.setProperty("mail.smtp.host", host);
		prop.setProperty("mail.smtp.auth", "true");
		
		Session session = Session.getDefaultInstance(prop, new Authenticator() {
			//익명클래스
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(sender,senderPwd);
			}
		});
		
		 
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(sender));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
			message.setSubject(title);
			message.setText(content, "UTF-8","html");
			
			Transport.send(message);
			
			response.getWriter().println("success");
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.getWriter().println("fail");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.getWriter().println("fail");
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
