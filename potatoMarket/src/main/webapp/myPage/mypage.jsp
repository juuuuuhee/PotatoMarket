<%--
  Created by IntelliJ IDEA.
  User: juhee
  Date: 2022/07/26
  Time: 4:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page import="user.UserDTO"%>
<%@page import="user.UserDAO"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
// 로그인한 유저 코드
int usercode = 1234;
// 정보 가져오기
UserDAO dao = UserDAO.getInstance();
UserDTO dto = dao.getUserData(usercode);
String name = dto.getName();
String id = dto.getId();
String add = dto.getAdd();
String ph = dto.getPhone();


%>
<div>
		<%@include file="../modules/header.jsp"%>
    </div>

 <div class="pagedetail"> 
        <div style="text-align : center;">
		</div>
			<a class="mydetail" id="name">이름 :<%=name %>  </a>
			<a class="mydetail" id="id">아이디 : <%=id %></a>
			<a class="mydetail" id="add">주소 : <%=add %> </a>
			<a class="mydetail" id="phone">전화 번호 : <%=ph %> </a>
			
			<form method="post" action="./profileUpdate.jsp">
				<input type="submit" value="비밀번호 변경" class="mydetail" id="changePw" >
			</form>
			<button onclick="location='./bookinglist.jsp'">판매 목록</button>
			<button onclick="location='./orderdlist.jsp'">구매 목록</button>
			<button onclick="location='./favolist.jsp'">관심 카테고리</button>
        </div>
        <div class="footer">
        <%@include file="../modules/footer.jsp"%>
    </div>

</body>
</html>
