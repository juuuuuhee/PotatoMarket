<%@page import="user.UserDAO"%>
<%@page import="item.ItemDTO"%>
<%@page import="item.ItemDAO"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<link rel="stylesheet" href="./css/itemList.css">
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
	<div>
		<%@include file="../modules/header.jsp"%>
	</div>
	<div class="wrap">
		<div class="contents_wrap">
			<div class="cards_wrap">
				<c:choose>
					<c:when test="${!empty list}">
						<h3>해당 판매글이 존재하지 않습니다</h3>
					</c:when>
					<c:otherwise>
						<%
						for (ItemDTO i : list) {
						%>
						<div class="card-top">
							<a class="img_href" href="./itemView?code=<%=i.getItem_code()%>">
								<div class="card-photo">
									<img id="img_size" src="<%=i.getItem_pic()%>">
								</div>
								<div id="item_contents">
									<div><%=i.getItem_tilte() %></div>
									<div><%=i.getItem_price() %>원</div>
									<%
									if(i.getItem_seiling() == 0){%>
									<div>판매중</div>
									<%}else if(i.getItem_seiling() == 1){%>
									<div>예약중</div>	
									<%}else if(i.getItem_seiling() == 2){%>
									<div>판매완료</div>
					
									<%
									}
									%>
								</div>
							</a>
						</div>
						<%
						};
						%>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
	<div class="footer">
		<%@include file="../modules/footer.jsp"%>
	</div>
</body>
</html>
