<%@page import="user.UserDAO"%>
<%@page import="item.ItemDTO"%>
<%@page import="item.ItemDAO"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Main</title>
</head>
<body>
	<%
	ArrayList<ItemDTO> list;

	if (request.getParameter("keyword") == null) {
		list = ItemDAO.getInstance().getAllItemList();
	} else {
		String keyword = request.getParameter("keyword");
		list = ItemDAO.getInstance().getSearchResult(keyword);
	}
	%>

	<c:set var="itemList" value="<%=list%>" />
	<div class="wrap">
		<div>
			<%@include file="../modules/header.jsp"%>
		</div>
		<div class="contents_wrap">
			<div class="ItemBorad">
				<c:choose>
					<c:when test="${!empty list}">
						<table border="1">
							<tr>
								<th>글번호</th>
								<th>작성자</th>
								<th>제목</th>
								<th>작성일</th>
								<th>거래유무</th>
							</tr>
							<%
							for (ItemDTO i : list) {
							%>
							<tr>
								<th><%= i.getItem_code() %></th>
								<th><%= UserDAO.getInstance().getUser(i.getUser_code()).getId() %></th>
								<th><%= i.getItem_tilte() %></th>
								<th><%= i.getItem_contents() %></th>
								<th><%= i.getItem_seiling() %></th>
							</tr>
							<%
							};
							%>
						</table>
					</c:when>
					<c:otherwise>
						<h3>해당 판매글이 존재하지 않습니다</h3>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<div class="footer">
			<%@include file="../modules/footer.jsp"%>
		</div>
	</div>
</body>
</html>
