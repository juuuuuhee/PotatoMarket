<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<link rel="stylesheet" href="css/join.css">
<title>Title</title>
</head>
<body>
	<%
	String nid = request.getParameter("id");
	String nname = request.getParameter("name");
	String kid = request.getParameter("k_id");
	String kname = request.getParameter("name");
	%>
	<%
	if(request.getParameter("join_check") != null){%>
	<script>
		alert("회원가입 실패.");
	</script>
	
	<%}%>
	<div>
		<%@include file="../modules/header.jsp"%>
	</div>
	<div class="wrap">
		<div class="contents_wrap">
			<div id="join_box">
				<div class="title_submit">
			
					<span class="join_font">회원가입</span>
				</div>

				<form class="join_form" method="post" action="./action" id="joinsub">
					<input type="hidden" name="command" value="join">

					<div class="input">
						<label>ID</label><br>
						<%
						if (nid != null) {
							nid = nid.substring(1, nid.length() - 1);
							System.out.print(nid + ": naver");
						%>
						<input type="text" id="id" name="id" value='<%=nid%>' readonly>
						<%
						} else if (kid != null) {
						%>
						<input type="text" id="id" name="id" value='<%=kid%>' readonly>
						<%
						} else {
						%>
						<input type="text" class="essential" id="id" placeholder="id"
							name="id">
						<%
						}
						%>
						<br> <span class="id_check"></span> <span class="check"></span>
						<span id="msg_"></span>
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
						<label>Name</label><br>
						<%
						if (nname != null) {
							nname = nname.substring(1, nname.length() - 1);
						%>
						<input type="text" id="name" name="name" value="<%=nname%>"
							readonly>
						<%
						} else if (kname != null) {
						kname = kname.substring(1, kname.length() - 1);
						%>

						<input type="text" id="name" name="name" value="<%=kname%>"
							readonly>
						<%
						} else {
						%>
						<input type="text" class="essential" placeholder="name"
							name="name"><br>
						<%
						}
						%>
						<span class="check"></span>
					</div>

					<div class="input">
						<label>address</label><br> <input type="text"
							class="essential" placeholder="우편번호" name="address_1"
							class="address_1" id="address_1" readonly> <input
							type="button" id="add_bnt" value="우편번호 찾기"
							onclick="find_address()" id="find_button"><br> <input
							type="text" class="essential" name="address_2" class="address_2"
							id="address_2" placeholder="도로명주소" readonly><br> <span
							class="check"></span>
					</div>

					<div class="input">
						<label>phone</label><br> <input type="text"
							class="phonePattern" name="phone_number" id="phone_1"
							class="essential" placeholder="휴대폰번호"> <br>
						<span class="check"></span> <span id="phone_check"></span>
					</div>

					<div class="title_submit">
						<div class="form_submit">
							<input type="submit" id="bnt" value="회원 가입"> <input
								type="button" onclick="location.href='index.jsp'" value="뒤로가기">
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="footer">
		<%@include file="../modules/footer.jsp"%>
	</div>
	<script
		src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
		function find_address() {
			new daum.Postcode({
				oncomplete : function(data) {
					$('#address_1').val(data.zonecode);
					$('#address_2').val(data.address);
				}
			}).open();
		}
	</script>
	<script src="script/validation.js"></script>
</body>
</html>
