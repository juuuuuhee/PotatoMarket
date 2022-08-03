
<%@page import="user.UserDTO"%>
<%@page import="user.UserDAO"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
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
	//010/1234/1234
	String ph1 =ph.substring(3,7);
	String ph2 =ph.substring(7);
	System.out.println(ph1);
	System.out.println(ph2);
	//우편번호/도로명
	String address[] = add.split("/");
	System.out.println(address[0]);
	System.out.println(address[1]);
	

	
%>
	<div class="wrap">
		<div>
			<%@include file="../modules/header.jsp"%>
		</div>
		<div class="contents_wrap">
			<div id="updatebox">
				<div class="title_submit">
					<span class="join_font">정보 수정</span>
				</div>

				<form class="updateform" method="post" action="./action" >
					<input type="hidden" name="command" value="update">
					<input type="hidden" name="code" value="<%=loginCode%>">

					<div class="input">
						<label>ID</label><br> <input type="text" id="n_id"
							value='<%=id%>' readonly> <br>

					</div>

					<div class="input">
						<label>PassWord</label><br> <input type="password"
							class="essential" id="password1" placeholder="password"
							name="password"> <span id="pwd1_check"></span> <input
							type="password" class="essential" id="password2"
							placeholder="Confirm Password"> <br> <span
							class="check"></span><br> <span id="pwcheck"></span>
					</div>

					<div class="input">
						<label>Name</label><br> <input type="text" id="n_name"
							value="<%=name%>" readonly>

					</div>

					<div class="input">
						<label>address</label><br> <input type="text"
							class="essential" value="<%=address[0] %>" name="address_1"
							class="address_1" id="address_1" readonly> <input
							type="button" id="add_bnt" value="우편번호 찾기"
							onclick="find_address()" id="find_button"><br> <input
							type="text" class="essential" name="address_2" class="address_2"
							id="address_2" value="<%=address[1] %>" readonly><br> <span
							class="check"></span>
					</div>

					<div class="input">
						<label>phone</label><br> <select name="contact_1"
							id="phone_1" class="essential">
							<option value="010">010</option>
							<option value="010">011</option>
							<option value="010">017</option>
						</select> <span>-</span> <input type="number" class="essential"
							value="<%=ph1 %>" name="contact_2" id="phone_2" pattern=".{3,4}"
							title="전화번호를 3자리에서 4자리 입력해주세요"> <span>-</span> <input
							type="number" class="essential" value="<%=ph2 %>"
							name="contact_3" id="phone_3" pattern=".{4,4}"
							title="전화번호를 4자리 입력해주세요"><br> <span class="check"></span>
					</div>

					<div class="title_submit">
						<div class="form_submit">
							<input type="submit" id="bnt" value="정보 수정"> 
							<input type="button" onclick="location.href='./mypage?loginCode=<%=loginCode %>'" value="뒤로가기">
						</div>
					</div>
				</form>
			</div>
		</div>
		<div class="footer">
			<%@include file="../modules/footer.jsp"%>
		</div>
	</div>
</body>
</html>
