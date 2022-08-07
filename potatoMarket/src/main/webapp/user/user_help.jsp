<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<link rel="stylesheet" href="css/user_help.css">
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<body>
	<div>
		<%@include file="../modules/header.jsp"%>
	</div>
	<div class="contents_wrap">
		<div class="contents_box">
			<ul class="li_box">
				<li class="menuName" id="menuTitle"
					onclick="showMenu('user_help_id','user_help_menu')">아이디 찾기</li>
				<p>|</p>
				<li class="menuName" id="menuTitle"
					onclick="showMenu('user_help_pw','user_help_menu')">비밀번호 찾기</li>
			</ul>
			<div id="help_box">
				<div class="user_help_id user_help_menu" style="display: none">
					<div class="title_box">
						<div class="check_box">
							<h2>회원 이름</h2>
							<input type="text" id="help_name" name="help_name" placeholder="이름">
						</div>
						<div class="check_box">
							<h2>회원 번호</h2>
							<input type="text" id="help_phone" name="help_phone" placeholder="핸드폰번호">
						</div>
						<div class="check_box">
							<input type="button" id="id_bnt" value="찾기" class="submit_box">
						</div>
						<span id="result_id"></span>
					</div>
				</div>
				
			</div>
			<div>
				<div class="user_help_pw user_help_menu" style="display: none">
					<div class="title_box">
							<div class="check_box">
								<h2>회원 아이디</h2>
								<input type="text" id="help_id" name="help_id" placeholder="아이디">
							</div>
							<div class="check_box">
								<h2>회원 번호</h2>
								<input type="text" id="help_phone" name="help_phone" placeholder="핸드폰번호">
							</div>
							<div class="check_box">
								<input type="button" value="찾기" class="submit_box">
							</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="footer">
		<%@include file="../modules/footer.jsp"%>
	</div>
	<script src="script/module.js"></script>
	<script src="script/user_help.js"></script>
</body>
</html>