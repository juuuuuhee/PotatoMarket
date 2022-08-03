<%@page import="user.UserDTO"%>
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
<div>
		<%@include file="../modules/header.jsp"%>
    </div>
<%
//유저 코드 가져오기
UserDTO loginUser = (UserDTO) session.getAttribute("log");
int loginCode = loginUser.getCode();
//arraylist로 저장
FavoriteDAO fdao = FavoriteDAO.getInstance();
ItemDAO idao = ItemDAO.getInstance();

ArrayList<FavoriteDTO> list = fdao.getFavoData(loginCode);
%>    

    <div>
    <h1>관심 아이템</h1>
    <%for(int i =0; i<list.size(); i++){
    	FavoriteDTO favorite = list.get(i);
    	ItemDTO  item = idao.getdata(favorite.getItemCode());
    	
    	String title = item.getItem_tilte();
    	String pic = item.getItem_pic();
    	int price=item.getItem_price();
    	int sellchk = item.getItem_seiling();
    	%>
 
 <article>
 <form id="favo<%=i+1 %>" method="post" action="./action"> 
 <input type="hidden" name="command" value="delfavo">
 <div><img src="<%=pic%>"></div>
 <div><%= title %></div>
 <div><%=price %>원</div>
 <div><%=sellchk %></div>
 <input type="submit" value="삭제" >
 <input type = "hidden" value="<%=favorite.getFavoCode() %>" name="favocode">
 </form>
 </article>
    <% }%>
    </div>
    
   <div class="footer">
        <%@include file="../modules/footer.jsp"%>
    </div>

</body>
</html>
