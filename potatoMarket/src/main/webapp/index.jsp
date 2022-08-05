<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Main</title>
	<link rel="stylesheet" href="./css/index.css">
</head>
<body>
	<div class="wrap">
		<div>
			<%@include file="modules/header.jsp" %>
		</div>
		<div class="contents_wrap">
			<div class="banner_image_fir">
				<img src="./resource/indexDOLOGIN.jpg" >
				<img src="./resource/indexDOSEARCH.jpg">
				<img src="./resource/indexDOCHAT.jpg">
				<img src="./resource/indexDOMYINFO.jpg">
			</div>
		</div>
		<div class="footer">
			<%@include file="modules/footer.jsp" %>
		</div>
		
	</div>
</body>
</html>	