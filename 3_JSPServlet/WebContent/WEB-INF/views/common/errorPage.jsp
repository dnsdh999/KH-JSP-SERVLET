<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1 align="center"><%= request.getAttribute("msg") %></h1>
	<div align="center">
		<button onclick="history.back();">이전 페이지</button>
		<button onclick="location.href='<%= request.getContextPath() %>'">홉으로 돌아가기</button>
	</div>
</body>
</html>