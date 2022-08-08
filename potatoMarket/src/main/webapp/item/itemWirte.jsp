<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Title</title>
</head>
<body>
	<%
		UserDTO loginUser = (UserDTO) session.getAttribute("log");
		int loginCode = -1;
		if (loginUser != null) {
			loginCode = loginUser.getCode();
		}
	%>
	<div>
		<%@include file="../modules/header.jsp"%>
	</div>
	<div class="contants_wrap">
		<div class="board_wrap">
			<form method="post" action="./action">
				<input type="hidden" name="command" value="writeBoard">
				<input type="hidden" id="user_code" name="user_code" value="<%=loginCode%>">
				<div id="img_box"> </div>
				<input type="file" onchange="setSom(this)">
				<input type="button" onclick="uploadImg()">
				<input type="text" id="title" name="title" placeholder="title" required><br>
				<textarea name="contents" id="contents" placeholder="contents text" required></textarea>
				<input type="number" id="price" placeholder="가격">
				<div class="button_box">
					<button onclick="location.href='./board_page'">뒤로가기</button>
					<input id="button_chat" type="submit"onclick="uploadImg()" value="작성하기">
				</div>
				<br>
			</form>
		</div>
	</div>
	<div class="footer">
		<%@include file="../modules/footer.jsp"%>
		
	</div>
		<script src="script/boardWrite.js"></script>
</body>
</html>
