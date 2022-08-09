<%@page import="user.UserDTO"%>
<%@page import="user.UserDAO"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<link rel="stylesheet" href="./css/mypage.css">
<link rel="stylesheet" href="./css/modal.css">
<title>Title</title>

</head>
<body>
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
	String pw = dto.getPw();
	%>
	<div>
		<%@include file="../modules/header.jsp"%>
	</div>
	<div class="mypage_bnt">
		<button class="mypage" id="update " onclick="openmodal()">내
			정보 수정</button>

		<!-- Modal content -->
		<div id="myModal" class="modal">
			<div class="modal-content">
				<form action="profileUpdate" method="post">
					<span class="close">&times;</span>
					<p>비밀번호를 입력해주세요.</p>
					<div>
						<input type="password" id="chk" name="chk">
						<input type="hidden" id="chkpw1" value="<%=pw%>">
					</div>
					<div>
						<input type="button" class="modalbut" id="ok" onclick="chkpw(form)" value="확인">
						<input type="button" class="modalbut" id="back" onclick="closemodal()" value="취소">
					</div>
				</form>
			</div>
		</div>

		<input class="mypage" type="button" value="나의 판매 목록" onclick="location='./orderdList'">
		<input class="mypage" type="button" value="나의 구매 목록" onclick="location='./bookingList'">
		<input class="mypage" type="button" value="나의 찜 목록" onclick="location='./favoList'">
	</div>
	<div class="wrap">
		<div class="contents_wrap">
			<div class="pagedetail">
				<a class="mydetail" id="name">이름 : <%=name%></a>
				<a class="mydetail" id="id">아이디 : <%=id%></a>
				<a class="mydetail" id="add">주소 : <%=add%></a>
				<a class="mydetail" id="phone">전화 번호 : <%=ph%></a>
			</div>
		</div>
		<div class="footer">
			<%@include file="../modules/footer.jsp"%>
		</div>
	</div>
</body>
<script src="script/modal.js">
	
</script>
</html>
