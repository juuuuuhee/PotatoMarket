<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Main</title>
	<link rel="stylesheet" href="./css/index.css">
</head>
<body>
	<%
	Object deleteChk = request.getAttribute("delete");
	if (deleteChk != null) {
		%> <script>alert("삭제완료");</script> <%
	}
	%>
	<div class="wrap">
		<div>
			<%@include file="modules/header.jsp" %>
		</div>
		<div class="contents_wrap">
			<div class="banner_image_fir">
				<img src="./resource/mainSearch.jpg" class="img_b">
				<img src="./resource/mainChat.jpg" class="img_b">
				<img src="./resource/mainMypage.jpg" class="img_b">
			</div>
		</div>
		<div class="footer">
			<%@include file="modules/footer.jsp" %>
		</div>
		
	</div>
</body>
</html>	