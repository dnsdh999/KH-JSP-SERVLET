package common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyFileRenamePolicy implements FileRenamePolicy{	//연결
	
	@Override
	public File rename(File originFile) {	//원본파일받기
		//업로드하는 날짜와 시간
		long currentTime = System.currentTimeMillis();	//현재의 시간
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		int ranNum = (int)(Math.random()*100000);	//랜덤의 숫자
		
		String name = originFile.getName();
		String ext = null;
		
		int dot = name.lastIndexOf(".");
		// this.is.my.dog.image.png - 가장 마지막에 있는 .의 위치를 추출한 것.
		
		if(dot != -1) {
			ext = name.substring(dot);
		}else {
			ext = "";
		}
		
		String fileName = sdf.format(new Date(currentTime)) + ranNum + ext;
		File newFile = new File(originFile.getParent(), fileName);
		
		return newFile;
	}
	
	
}
