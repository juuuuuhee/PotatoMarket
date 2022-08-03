<%@page import="user.UserDTO"%>
<%@page import="user.UserDAO"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<link rel="stylesheet" href="./css/mypage.css">
<title>Title</title>
</head>
<body>
	<div class="wrap">
		<div>
			<%@include file="../modules/header.jsp"%>
		</div>
		<%
		// 로그인한 유저 코드
		UserDTO loginUser = (UserDTO) session.getAttribute("log");
		int loginCode = loginUser.getCode();
		// 정보 가져오기
		UserDAO dao = UserDAO.getInstance();
		UserDTO dto = dao.getUserData(loginCode);
		String name = dto.getName();
		String id = dto.getId();
		String add = dto.getAddress();
		String ph = dto.getPhone();
		%>
		<div class="contents_wrap">
			<div class="pagedetail">
				<a class="mydetail" id="name">이름 :<%=name %></a>
				<a class="mydetail"id="id">아이디 : <%=id %></a> 
				<a class="mydetail" id="add">주소 : <%=add %></a>
				<a class="mydetail" id="phone">전화 번호 : <%=ph %></a>
			</div>
			<button class="mypage" onclick="location='./profileUpdate'">내 정보 수정</button>
			<button class="mypage" onclick="location='./bookingList'">나의 예 목록</button>
			<button class="mypage" onclick="location='./orderdList'">나의 구매 목록</button>
			<button class="mypage" onclick="location='./favoList'">나의 관심 카테고리</button>
		</div>
		<div class="footer">
			<%@include file="../modules/footer.jsp"%>
		</div>
	</div>
</body>
</html>
