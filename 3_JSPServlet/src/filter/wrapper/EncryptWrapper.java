package filter.wrapper;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncryptWrapper extends HttpServletRequestWrapper{	//요청이기 때문에 이 클래스 상속
	
	public EncryptWrapper(HttpServletRequest request) {
		super(request);
	}
	
	@Override
	public String getParameter(String name) {
		//모든 겟 파라미터 바꿀거아님
		//getParameter를 사용한 패스워드의 변수들을 담아오자
		
		String value = null;
		
		if(name!=null && name.contains("Pwd")) {
			try {
				MessageDigest md = MessageDigest.getInstance("SHA-512");
				byte[] bytes = super.getParameter(name).getBytes(Charset.forName("UTF-8"));	//바이트 단위로 쪼갠다
				md.update(bytes);
				value = Base64.getEncoder().encodeToString(md.digest());
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}else {
			value = super.getParameter(name);
		}
		return value;
	}
}
