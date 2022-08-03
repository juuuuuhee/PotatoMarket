
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Title</title>
<script
	src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.2.js"
	charset="utf-8"></script>
</head>
<body>
	<div>
		<%@include file="../modules/header.jsp"%>
	</div>
	<div class="contents_wrap">
		<div class="">
			<div>
				<form method="post" action="action">
					<input type="hidden" name="command" value="login">
					<h2>
						아이디<span>*</span>
					</h2>
					<input type="text" name="id" placeholder="id" required>
					<h2>
						비밀번호<span>*</span>
					</h2>
					<input type="password" name="password" placeholder="password"
						required>
					<div class="buttons">
						<input type="submit" value="로그인"> <input type="button"
							value="회원가입" onclick="location.href='/join'">
					</div>
					<div class="container">
						<div class="login-area">
							<div id="message">로그인 버튼을 눌러 로그인 해주세요.</div>
							<div id="button_area">
								<div id="naverIdLogin"></div>
							</div>
						</div>
					</div>
					<div>
						<a href="javascript:kakaoLogin();"><img
							src="resource/kakao_login.png" alt="카카오계정 로그인"
							style="height: 60px;" /></a>
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
