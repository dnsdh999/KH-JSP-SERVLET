<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.io.IOException"%>
<%
	int bId = (int)request.getAttribute("bId");
	String category = (String)request.getAttribute("category");
	String title = (String)request.getAttribute("title");
	String content = (String)request.getAttribute("content");
	
	String[] selected = new String[7];
	System.out.println(category);

	switch(category){
	case "공통" : selected[0] = "selected"; break;
	case "운동" : selected[1] = "selected"; break;
	case "등산" : selected[2] = "selected"; break;
	case "게임" : selected[3] = "selected"; break;
	case "낚시" : selected[4] = "selected"; break;
	case "요리" : selected[5] = "selected"; break;
	case "기타" : selected[6] = "selected"; break;
	}
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.outer{
		width: 800px; height: 500px; background: rgba(255, 255, 255, 0.4); border: 5px solid white;
		margin-left: auto; margin-right: auto; margin-top: 50px;
	}
	.tableArea {width:500px; height:350px; margin-left:auto; margin-right:auto;}
	#updateBtn, #cancelBtn{background: #B2CCFF; border-radius: 10px; color: white; width: 80px; height: 25px; display: inline-block;}
	#updateBtn:hover, #cancelBtn:hover{cursor: pointer;}
	#cancelBtn{background: #D1B2FF;}
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
		
	<div class="outer">
		<br>
		<h2 align="center">게시판 수정</h2>
		<div class="tableArea">
			<form action="<%= request.getContextPath() %>/update.bo" method="post">
				<table>
					<tr>
						<th>분야<input type="hidden" name="bid"></th>
						<td>
							<select name="category">
								<option>--------</option>
								<option value="공통" <%= selected[0] %> name="공통">공통</option>
								<option value="운동" <%= selected[1] %> name = "운동">운동</option>
								<option value="등산" <%= selected[2] %> name = "등산">등산</option>
								<option value="게임" <%= selected[3] %> name = "게임">게임</option>
								<option value="낚시" <%= selected[4] %> name = "낚시">낚시</option>
								<option value="요리" <%= selected[5] %> name = "요리">요리</option>
								<option value="기타" <%= selected[6] %> name = "기타">기타</option>
							</select>
						</td>	
					</tr>
					<tr>
						<th>제목</th>
						<td colspan="3"><input type="text" size="58" name="title" value="<%= title %>"></td>
					</tr>
					<tr>
						<th>내용</th>
						<td colspan="3">
							<textarea rows="15" cols="60" name="content" style="resize:none;"><%= content %></textarea>
							<input type="hidden" size="58" name="bId" value="<%= bId %>">
							<input type="hidden" size="58" name="cate" value="<%= category %>">
						</td>
					</tr>
				</table>
				<br>
				<div align="center">
					<button type="submit" id="updateBtn">수정</button>
					<div id="cancelBtn" onclick="location.href='<%= request.getContextPath() %>/list.bo'">취소</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>