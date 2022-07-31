
<%@page import="item.ItemDTO"%>
<%@page import="favo.FavoriteDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="item.ItemDAO"%>
<%@page import="favo.FavoriteDAO"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
//유저 코드 가져오기
int usercode = 1234;
//arraylist로 저장
FavoriteDAO fdao = FavoriteDAO.getInstance();
ItemDAO idao = ItemDAO.getInstance();

ArrayList<FavoriteDTO> list = fdao.getFavoData(usercode);
%>    
<div>
		<%@include file="../modules/header.jsp"%>
    </div>
    <div>
    <h1>관심 아이템</h1>
    <%for(int i =0; i<list.size(); i++){
    	FavoriteDTO favorite = list.get(i);
    	ItemDTO  item = idao.getdata(favorite.getItemCode());
    	
    	String title = item.getTitle();
    	String pic = item.getPic();
    	int price=item.getPrice();
    	int sellchk = item.getSellchk();
    	%>
 
 <article>
 <div><img src="<%=pic%>"></div>
 <div><%= title %></div>
 <div><%=price %>원</div>
 <div><%=sellchk %></div>
 <input type="button" value="삭제">
 </article>
 
 
    <% }%>
    </div>
    
   <div class="footer">
        <%@include file="../modules/footer.jsp"%>
    </div>

</body>
</html>
