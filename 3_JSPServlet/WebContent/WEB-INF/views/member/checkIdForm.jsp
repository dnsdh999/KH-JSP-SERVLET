<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body onload="inputValue();">
	<b>아이디 중복 검사</b>
	<br>
	<form action="<%= request.getContextPath() %>/checkId.me" id="idCheckForm">
		<input type="text" id="inputId" name="inputId">
		<input type="submit" value="중복확인"/>
	</form>
	
	<br>
	
	<%
	Integer result = (Integer)request.getAttribute("result");
	if(result != null){
		if(result > 0){
	%>
	이미 사용 중인 아이디 입니다.
	<% } else{ %>
			사용 가능한 아이디입니다.
	<%  } %>
	<% } %>
	
	
	<br>
	<br>
	
	<input type="button" id="usedId" value="확인">
	<input type="button" id="cancel" value="취소" onclick="window.close();">
	
	<script>
		function inputValue(){
			//부모에게 있던 값을 집어넣겠다.
			if(<%= result %> == null){
				document.getElementById('inputId').value = opener.document.joinForm.joinUserId.value;
			} else{
				//스클립틀릿 앞뒤에 따옴표를 해주지 않으면 변수로 인식하여 오류가 남 - uncaught referenceError : rosemary is not defined
				// 큰 따옴표를 추가해준다.
				document.getElementById('inputId').value = "<%= request.getAttribute("checkedId") %>"
			}
		}
		
		document.getElementById('usedId').onclick = function(){
			opener.document.joinForm.joinUserId.value = document.getElementById('inputId').value;
			self.close();
			//window.close();
		}
	</script>
</body>
</html>