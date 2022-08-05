<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<div>
		<%@include file="../modules/header.jsp"%>
	</div>
	<div class="contents_wrap">
		<div class="wrap">
			<div class="contents_wrap">
				<div id="help_box">
					<div class="id">
						<input type="text" placeholder="이름">
						<input type="text" placeholder="핸드폰번호">
					</div>
				</div>
				<div>
					<div class="pw">
						<input type="text" placeholder="아이디">
						<input type="text" placeholder="핸드폰번호">
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="footer">
		<%@include file="../modules/footer.jsp"%>
	</div>
</body>
</html>