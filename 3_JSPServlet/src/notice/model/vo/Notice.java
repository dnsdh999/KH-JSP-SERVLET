package notice.model.vo;

import java.sql.Date;

public class Notice {
	private int noticeNo;
	private String noticeTitle;
	private String noticeContent;
	private String noticeWriter;
	private String nickName;
	private int noticeCount;
	private Date noticeDate;
	private String status;
	
	public Notice() {}
	// db에 있는 값(요소)들과 같은지 확인하기
	// 일치하지 않음.
	// 여기는 데이터를 담는 변수이고 여러 값을 담을 수 있는 것일 뿐이다.
	// db와 같이 만들필요없고 조인을 많이한다면 조인 후에 담을 데이터 변수도 선언해주는 것이 편하다.
	
	
	public Notice(int noticeNo, String noticeTitle, String noticeContent, String noticeWriter, String nickName,
			int noticeCount, Date noticeDate, String status) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.noticeWriter = noticeWriter;
		this.nickName = nickName;
		this.noticeCount = noticeCount;
		this.noticeDate = noticeDate;
		this.status = status;
	}

	
	

	public Notice(int noticeNo, String noticeTitle, String noticeContent, Date noticeDate) {
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.noticeDate = noticeDate;
	}
	
	
	public Notice(String noticeTitle, String noticeContent, String noticeWriter, Date noticeDate) {
		super();
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.noticeWriter = noticeWriter;
		this.noticeDate = noticeDate;
	}

	
	public int getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public String getNoticeWriter() {
		return noticeWriter;
	}

	public void setNoticeWriter(String noticeWriter) {
		this.noticeWriter = noticeWriter;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getNoticeCount() {
		return noticeCount;
	}

	public void setNoticeCount(int noticeCount) {
		this.noticeCount = noticeCount;
	}

	public Date getNoticeDate() {
		return noticeDate;
	}

	public void setNoticeDate(Date noticeDate) {
		this.noticeDate = noticeDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Notice [noticeNo=" + noticeNo + ", noticeTitle=" + noticeTitle + ", noticeContent=" + noticeContent
				+ ", noticeWriter=" + noticeWriter + ", nickName=" + nickName + ", noticeCount=" + noticeCount
				+ ", noticeDate=" + noticeDate + ", status=" + status + "]";
	}
	
	
}
