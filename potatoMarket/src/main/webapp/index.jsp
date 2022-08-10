<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
 <link rel="shortcut icon" href="https://cdn-icons-png.flaticon.com/128/1538/1538920.png">
<title>Main</title>
	<link rel="stylesheet" href="./css/index.css">
</head>
<body>
	<%
	// 게시글 삭제했을때 알림창을 띄운다
	Object deleteChk = request.getAttribute("delete");
	if (deleteChk != null) {
		%> <script>alert("삭제완료");</script> <%
	}
	
	int logCode = -1;
	Object loginCodeObj = session.getAttribute("log");
	
	if (loginCodeObj != null) {
		UserDTO user = (UserDTO) loginCodeObj;
		logCode = user.getCode();
	}
	%>
	<div class="wrap">
		<div>
			<%@include file="modules/header.jsp" %>
		</div>
		<div class="contents_wrap">
			<div class="banner_image_fir">
			<c:choose>
				<c:when test="<%=logCode == -1 %>">
					<a href="./itemList?keyword=고양"><img src="./resource/mainSearch.jpg" class="img_b"></a>
					<a href="./login_page"><img src="./resource/mainChat.jpg" class="img_b"></a>
					<a href="./login_page"><img src="./resource/mainMypage.jpg" class="img_b"></a>
				</c:when>
				<c:otherwise>
					<a href="./itemList?keyword=고양"><img src="./resource/mainSearch.jpg" class="img_b"></a>
					<a href="./chatList"><img src="./resource/mainChat.jpg" class="img_b"></a>
					<a href="./myPage"><img src="./resource/mainMypage.jpg" class="img_b"></a>
				</c:otherwise>
			</c:choose>
			</div>
		</div>
		<div class="footer">
			<%@include file="modules/footer.jsp" %>
		</div>
		
	</div>
</body>
</html>	