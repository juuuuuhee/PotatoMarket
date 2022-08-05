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
		<div class="ul_box">
			<ul>
				<li class="menuName clicked" id="menuTitle" onclick="showMenu('user_help_id','user_help_menu')">아이디 찾기</li>
				<li>|</li>
				<li class="menuName clicked" id="menuTitle" onclick="showMenu('user_help_pw','user_help_menu')">비밀번호 찾기</li>
			</ul>
		</div>
		<div id="help_box">
			<div class="user_help_id user_help_menu" style="display:none">
				<input type="text" placeholder="이름"> 
				<input type="text" placeholder="핸드폰번호">
			</div>
		</div>
		<div>
			<div class="user_help_pw user_help_menu" style="display:none">
				<input type="text" placeholder="아이디"> <input type="text"
					placeholder="핸드폰번호">
			</div>
		</div>
	</div>
	<div class="footer">
		<%@include file="../modules/footer.jsp"%>
	</div>
	<script src="script/module.js"></script>
</body>
</html>