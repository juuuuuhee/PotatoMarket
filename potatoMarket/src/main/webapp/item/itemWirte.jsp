<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Title</title>
</head>
<body>
	<div>
		<%@include file="../modules/header.jsp"%>
	</div>
	<div class="contants_wrap">
		<div class="board_wrap">
			<form method="post" action="./action">
				<input type="hidden" name="command" value="writeBoard"> <input
					type="text" id="title" name="title" placeholder="title" required><br>
				<textarea name="contents" placeholder="contents text" required></textarea>
				<div class="button_box">
					<button onclick="location.href='./board_page'">뒤로가기</button>
					<input id="button_chat" type="submit" value="작성하기">
				</div>
				<br>
			</form>
		</div>
	</div>
	<div class="footer">
		<%@include file="../modules/footer.jsp"%>
	</div>
</body>
</html>
