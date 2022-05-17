<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="notice.model.vo.Notice, java.sql.Date" %>
<%
	Notice n = (Notice)request.getAttribute("notice");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.outer{
		width: 600px; height: 500px; background-color: rgba(255, 255, 255, 0.4); border: 5px solid white;
		margin-left: auto; margin-right: auto; margin-top: 50px;
	}
	.tableArea {width:450px; height:350px; margin-left:auto; margin-right:auto;}
	#updateNoBtn{background: #D1B2FF;}
	#cancelBtn{background: #B2CCFF;}
	#deleteNoBtn{background: #D5D5D5;}
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
	<div class="outer">
		<br>
		<h2 align="center">공지사항</h2>
		<div class="tableArea">
			<form action="<%= request.getContextPath() %>/noticeUpdateForm.no?no=<%= n.getNoticeNo() %>" id="detailForm" name="detailForm" method="post">
				<table>
					<tr>
						<th>제목</th>
						<td colspan="3">
						<%= n.getNoticeTitle() %>
						<input type="hidden" size="50" name="title" value="<%= n.getNoticeTitle() %>"></td>				
					</tr>
					<tr>
						<th>작성자</th>
						<td>
							<%= n.getNickName() %>
							<input type="hidden" size="50" name="writer" value="<%= n.getNickName() %>">
						</td>
						<th>작성일</th>
						
						<td>
						<%= n.getNoticeDate() %>
						<input type="hidden" name="date" value="<%= n.getNoticeDate() %>"></td>
					</tr>
					<tr>
						<th>내용</th>
					</tr>
					<tr>
						<td colspan="4">
							<textarea name="content" cols="60" rows="15" style="resize:none;" readonly><%= n.getNoticeContent() %></textarea>
							<input type="hidden" name="no" value="<%= n.getNoticeNo() %>">
						</td>
					</tr>
				</table>
			
				<br>
				<% if(loginUser != null && loginUser.getUserId().equals("admin")){ %>
				<div align="center">
					<input type="submit" id="updateNoBtn" value="수정">
					<input type="button" onclick="deleteNotice();" value="삭제">
				<%} %>
				<input type="button" onclick="location.href='javascript:history.go(-1);'" id="cancelBtn" value="취소">
				</div>
			</form>
		</div>
	</div>
	<script>
		function deleteNotice(){
			var bool = confirm('정말 삭제하시겠습니까?');
			if(bool){
				location.href='<%= request.getContextPath() %>/deleteNotice.no?no=<%= n.getNoticeNo() %>';
				
			}
		}
	</script>
</body>
</html>