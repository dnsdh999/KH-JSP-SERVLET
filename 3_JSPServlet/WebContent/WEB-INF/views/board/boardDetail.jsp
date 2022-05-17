<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="board.model.vo.Board, board.model.vo.Reply, java.util.ArrayList"%>
<%
	Board b = (Board)request.getAttribute("b");
	ArrayList<Reply> list = (ArrayList<Reply>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.outer{
		width:800px; min-height:500px; background: rgba(255, 255, 255, 0.4); border: 5px solid white;
		margin-left:auto; margin-right:auto; margin-top:50px;
	}
	.tableArea {width: 450px; height:350px; margin-left:auto; margin-right:auto; align: center;}
	table{align: center;}
	#updateBtn{background: #B2CCFF;}
	#menuBtn{background: #D1B2FF;}
	#deleteBtn{background: #D5D5D5;}
	#addReply{background: #B2CCFF;}
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
		
	<div class="outer">
		<br>
		<h2 align="center">게시판 상세보기</h2>
		<div class="tableArea">
			<form action="<%= request.getContextPath() %>/boardUpdateForm.bo?bo=<%= b.getBoardId() %>" id="detailForm" method="post">
				<table>
					<tr>
						<th>분야</th>
						<td><%= b.getCategory() %></td>
						<input type="hidden" name="category" value="<%= b.getCategory() %>">
						<th>제목</th>
						<td colspan="3"><%= b.getBoardTitle() %></td>
						<input type="hidden" name="title" value="<%= b.getBoardTitle() %>">
					</tr>
					<tr>
						<th>작성자</th>
						<td><%= b.getNickName() %></td>
						
						<th>조회수</th>
						<td><%= b.getBoardCount() %></td>
						
						<th>작성일</th>
						<td><%= b.getCreateDate() %></td>
						
					</tr>
					<tr>
						<th>내용</th>
					</tr>
					<tr>
						<td colspan="6">
							<textarea cols="60" rows="15" style="resize:none;" readonly><%= b.getBoardContent() %></textarea>
							<input type="hidden" name="content" value="<%= b.getBoardContent() %>">
							<input type="hidden" name="bId" value="<%= b.getBoardId() %>">
						</td>
					</tr>
				</table>
				
				<div align="center">
					<% if(loginUser != null && loginUser.getUserId().equals(b.getBoardWriter())){ %>
					<input type="submit" id="updateBtn" value="수정">
					<input type="button" onclick="deleteBoard();" id="deleteBtn" value="삭제">
					<% } %>
					<input type="button" onclick="location.href='<%= request.getContextPath() %>/list.bo'" id="menuBtn" value="메뉴로">
				</div>
			</form>
		</div>
		
		<div class="replyArea">
			<div class="replyWriterArea">
				<table>
					<tr>
						<td>댓글 작성</td>
						<td><textarea rows="3" cols="80" id="replyContent" style="resize:none;"></textarea></td>
						<td><button id="addReply">댓글 등록</button></td>
					</tr>
				</table>
			</div>
			
			<div id="replySelctArea">
				<table id="replySelectTable">
					<% if(list.isEmpty()){ %>
					<tr><td colspan="3">댓글이 없습니다.</td></tr>
					<%} else{ %>
					<% 		for(int i = 0; i<list.size(); i++){ %>
							<tr>
								<td width="100px"><%= list.get(i).getNickName() %></td>
								<td width="400px"><%= list.get(i).getReplyContent() %></td>
								<td width="200px"><%= list.get(i).getModifyDate() %></td>
							</tr>
						<%} %>
					<% } %>
				</table>
			
			</div>
			
		</div>
	</div>
	<script>
		function deleteBoard(){
			var bool = confirm('정말 삭제하시겠습니까?');
			if(bool){
				location.href='<%= request.getContextPath() %>/deleteBoard.bo?bId=<%= b.getBoardId() %>';
				
			}
		}
		
		$('#addReply').on('click', function(){
			var writer = '<%= loginUser.getUserId() %>';
			var content = $('#replyContent').val();
			var bId = <%= b.getBoardId() %>;
			
			$.ajax({
				url:'insertReply.bo',
				data:{writer:writer, content:content, bId: bId},
				success:function(data){
					console.log(data);
					
					$replyTable = $('#replySelectTable');
					$replyTable.html('');
					
					for(var i in data){
					var $tr = $('<tr>');
					var $writerTd = $('<td>').text(data[i].nickName).css('width','100px');
					var $contentTd = $('<td>').text(data[i].replyContent).css('width','400px');
					var $dateTd = $('<td>').text(data[i].modifyDate).css('width','200px');
				
					
					$tr.append($writerTd);
					$tr.append($contentTd);
					$tr.append($dateTd);
					
					$replyTable.append($tr);
					
					$('#replyContent').val('');
					}
				},
				error:function(data){
					console.log(data);
				}
			});
		});
	</script>
</body>
</html>