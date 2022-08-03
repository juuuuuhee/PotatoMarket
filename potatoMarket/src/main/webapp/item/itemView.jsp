<%@page import="item.ItemDTO"%>
<%@page import="item.ItemDAO"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Title</title>
</head>
<body>
	<%
	ItemDAO dao = ItemDAO.getInstance();
	String item_code = request.getParameter("code");
	ItemDTO thisItem = dao.getItem(Integer.parseInt(item_code));
	%>
	<div>
		<%@include file="../modules/header.jsp"%>
	</div>
	<div class="contents_wrap">
		<div class="contants_detail">
			<div class="detail_img">
				<img alt="" src="<%=thisItem.getItem_pic()%>">
			</div>
			<div class="detail_item_name">
				<h2><%=thisItem.getItem_tilte()%></h2>
			</div>
			<div class="detail_item_price">
				<span>₩<%=thisItem.getItem_price()%></span>
			</div>
			<div class="detail_text_text">
				<%=thisItem.getItem_contents()%>
			</div>
		</div>
	</div>
	<div class="footer">
		<%@include file="../modules/footer.jsp"%>
	</div>
</body>
</html>
