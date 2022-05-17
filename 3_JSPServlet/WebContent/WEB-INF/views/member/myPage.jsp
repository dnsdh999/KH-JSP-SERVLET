<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 데이터베이스에서 저장된 정보를 갖고오는 방식의 로그인 코드
// 주석처리된 윗 부분을 풀면 사용가능
	//Member m = (Member)request.getAttribute("member");


//세션에 있는 로그인 멤버 따오기 코드
	Member m = (Member)session.getAttribute("loginUser");
// 하지만 이런 방식으로 한다면 정보수정후에도 바로 반영이 되지않음
// 왜?
// 로그인할때의 값을 그대로 가지고 오기 때문임.
// 그래서 아무리 DB가 바뀐다고한들 변화가 되지않음.
// 고로 정보를 수정한 다음에 세션에 있는 값들을 업데이트 시켜주어야함


%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
	.outer{
		width: 48%; height: 450px; background-color: rgba(255, 255, 255, 0.4); border: 5px solid white;
		margin-left: auto; margin-right: auto; margin-top: 5%;
	}
	#myForm td {text-align: center;}
	#myForm>table{margin: auto;}
	#updateBtn {background: #D1B2FF; color: white;}
	#updatePwdBtn {background: #FFD8D8; color: white;}
	#deleteBtn {background: #D5D5D5; color: white;}
	#goMain {background: #B2CCFF; color: white;}
</style>
<title>Insert title here</title>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
	
	<div class="outer">
		<br>
		<h2 align="center">내 정보 보기</h2>
		
		<form action="<%= request.getContextPath() %>/updateForm.me" method="post" id="myForm" name="myForm">
			<table>
				<tr>
					<td width="200px">아이디</td>
					<td width="200px">
						<%= m.getUserId() %>
						<input type="hidden" maxlength="13" name="myId" required value="<%= m.getUserId() %>"></td>
						<!-- input타입 히든으로 해서 수정하기를 누르면 값을 인풋을 통해 보낼 수 있도록 만들어준다. -->
						<!--  오직 코딩의 편리함을 위해서 만든것.  -->
				</tr>
				<tr>
					<td>이름</td>
					<td>
						<%= m.getUserName() %>
						<input type="hidden" name="myName" required value="<%= m.getUserName() %>"></td>
				</tr>
				<tr>
					<td>닉네임</td>
					<td>
						<%= m.getNickName() %>
					<input type="hidden" maxlength="15" name="myNickName" required value="<%= m.getNickName() %>"></td>
				</tr>
				<tr>
					<td>연락처</td>
					<td>
						<%= m.getPhone() == null ? "-" : m.getPhone() %>
						<input type="hidden" maxlength="11" name="myPhone" placeholder="(-없이)01012345678" value="<%= m.getPhone() %>">
					</td>
				</tr>
				<tr>
					<td>이메일</td>
					<td>
						<%= m.getEmail() == null ? "-" : m.getEmail() %>
					<input type="hidden" name="myEmail" value="<%= m.getEmail() %>"></td>
				</tr>
				<tr>
					<td>주소</td>
					<td>
						<%= m.getAddress() == null ? "-" : m.getAddress() %>
					<input type="hidden" name="myAddress" value="<%= m.getAddress() %>"></td>
				</tr>
				<tr>
					<td>관심분야</td>
					<td>
						<%= m.getInterest() == null ? "-" : m.getInterest() %>
						<input type="hidden" id="sports" name="myInterest" value="<%= m.getInterest() %>">
						<!--<label for="sports">운동</label>
						<input type="checkbox" id="climbing" name="myInterest" value="등산">
						<label for="climbing">등산</label>
						<input type="checkbox" id="fishing" name="myInterest" value="낚시">
						<label for="fishing">낚시</label>
						<input type="checkbox" id="cooking" name="myInterest" value="요리">
						<label for="cooking">요리</label>
						<input type="checkbox" id="game" name="myInterest" value="게임">
						<label for="game">게임</label>
						<input type="checkbox" id="etc" name="myInterest" value="기타">
						<label for="etc">기타</label>  -->
					</td>
				</tr>
			</table>
			
			<br>
			
			<div class="myPageBtns" align="center">
				<input id="updateBtn" type="submit" value="수정하기">
				<input id="updatePwdBtn" type="button" value="비밀번호 변경 하기" onclick="location.href='updatePwdForm.me'">
				<input id="deleteBtn" type="button" value="탈퇴하기" onclick="deleteMember();">
				<input type="button" id="goMain" onclick="goMain();" value="메인으로">
			</div>
		</form>
	</div>
	
	<script>
		function deleteMember(){
			// 정말 삭제할거냐고 물어본 후, 삭제하겠다고 하면 delete.me로 요청
			var bool = confirm('정말 탈퇴하시겠습니까?');
			if(bool){
				location.href='<%= request.getContextPath() %>/delete.me';
			}
			
		}
	</script>
</body>
</html>