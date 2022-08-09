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
		// 검색창에 키워드를 입력했다면 해당 키워드에 해당하는 아이템들을 불러온다
		String keyword = request.getParameter("keyword");
		list = ItemDAO.getInstance().getSearchResult(keyword);
	}
	
	// session에서 로그인코드loginCode를 가져온다
	int loginCode = -1;
	Object loginObj = session.getAttribute("log");
	if (loginObj != null) {
		UserDTO user = (UserDTO) loginObj;
		loginCode = user.getCode();
	}
	%>
	<c:set var="itemList" value="<%=list%>" />
	<div>
		<%@include file="../modules/header.jsp"%>
	</div>
		<div class="contents_wrap">
			<div class="cards_wrap">
				<c:choose>
					<c:when test="${empty itemList}">
						<h3>해당 판매글이 존재하지 않습니다</h3>
					</c:when>
					<c:otherwise>
						<%
						for (ItemDTO i : list) {
						%>
						<div class="card-top">
							<a class="img_href" href="./itemView?code=<%=i.getItem_code()%>">
								<div class="card-photo">
									<% if(loginCode == i.getUser_code()) { %>
										<span class="note"></span>
									<% }
									
									if(i.getItem_seiling() == 0) { %>
										<img id="img_size" src="<%=i.getItem_pic()%>">
									<% } else if(i.getItem_seiling() == 1) { %>
										<img id="img_size" src="<%=i.getItem_pic()%>" style="filter: brightness(35%)">
									<% } %>
									
								</div>
								<div id="item_contents">
									<%
									if(i.getItem_seiling() == 0) { %>
										<div><%=i.getItem_tilte() %></div>
										<div>
											<%String str = decFormat.format(i.getItem_price()); %>
											<%=str%>원
										</div>
										<div>판매중</div>
									<% } else if(i.getItem_seiling() == 1) { %>
										<div class="sell" ><%=i.getItem_tilte() %></div>
										<div class="sell" >
											<%String str = decFormat.format(i.getItem_price()); %>
											<%=str%>원
										</div>
										<div class="sell" >판매완료</div>
									<% 
									}
									%>
								</div>
							</a>
						</div>
						<%
						}
						%>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	<div class="footer">
		<%@include file="../modules/footer.jsp"%>
	</div>
</body>
</html>
