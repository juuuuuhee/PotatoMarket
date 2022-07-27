
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Title</title>
</head>
<body>
	<div>
		<%@include file="../modules/header.jsp"%>
	</div>
	<div class="contents_wrap">
		<div class="">
			<div>
				<form method="post" action="/loginUser">
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
				</form>
			</div>
		</div>
	</div>
	<div class="footer">
		<%@include file="../modules/footer.jsp"%>
	</div>
</body>
</html>
