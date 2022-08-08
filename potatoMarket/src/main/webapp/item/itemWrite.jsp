<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Title</title>
<link rel="stylesheet" href="css/itemWrite.css">
</head>
<body>
	<div>
		<%@include file="../modules/header.jsp"%>
	</div>
	<div class="contents_wrap">
		<div id="board_box">
			<form method="post" action="./action" id="form">
				<input type="hidden" name="command" value="writeBoard">
				<input type="hidden" id="user_code" value="<%=logincode%>">
				<div id="img_box"></div>
				<div class="filebox">
					<label for="ex_file">업로드</label>
  					<input type="file" id="ex_file" onchange="setSom(this)">
  				</div>
				<div id="form_div"><input type="text" id="title" name="title" placeholder="title" required></div>
				<div id="form_div"><textarea name="contents" id="contents" placeholder="contents text" required></textarea></div>
				<div id="form_div"><input type="number" id="price" placeholder="가격"></div>
				<div class="button_box">
					<input class="bnt_input" type="button" onclick="location.href='./itemView'" value="뒤로가기">
					<input class="bnt_input" id="button_chat" type="submit"onclick="uploadImg()" value="작성하기">
				</div>
			</form>
		</div>
	</div>
	<div class="footer">
		<%@include file="../modules/footer.jsp"%>
		
	</div>
		<script src="script/boardWrite.js"></script>
</body>
</html>
