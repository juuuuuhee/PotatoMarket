
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Title</title>
	<script
	src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.2.js"
	charset="utf-8"></script>
	<link rel="stylesheet" href="css/login.css">
</head>
<body>
	<div>
		<%@include file="../modules/header.jsp"%>
	</div>
	<div class="contents_wrap">
		<div id="login_box">
			<div>
				<span class="log_font_fir">로그인</span>
			</div>
			<div>
				<form method="post" action="action">
					<input type="hidden" name="command" value="login">
					
					<div class="input_box">
						<h2 class="log_font">
							아이디<span id="essential">*</span>
						</h2>
						<input id="bnt_login" type="text" name="id" placeholder="id" required>
					</div>
					
					<div class="input_box">
						<h2 class="log_font">
							비밀번호<span id="essential">*</span>
						</h2>
						<input id="bnt_login" type="password" name="password" placeholder="password"
							required>
					</div>
					<div>
						<input class="input_box" id="bnt_login" type="submit" value="로그인">
					</div>
					
					<div class="buttons">
						<input class="bnt_size" type="button" value="회원가입" onclick="location.href='./join_page'">
						<span>|</span>
						<input class="bnt_size" type="button" value="아이디 찾기" onclick="location.href='./user_help_page?id=help_box'">
						<span>|</span>
						<input class="bnt_size" type="button" value="비밀번호 찾기" onclick="location.href='./user_help_page'">
					</div>
					<div>
						<div class="login-area">
							<div id="button_area">
								<div id="naverIdLogin"></div>
							</div>
						</div>
					</div>
					
					<div>
						<a href="javascript:kakaoLogin();"><img
							src="resource/kakao_login.png" alt="카카오계정 로그인"
							style="height: 60px;" />
						</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="footer">
		<%@include file="../modules/footer.jsp"%>
	</div>
	<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
	<script src="script/login.js"></script>
</body>
</html>
