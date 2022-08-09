<%@page import="user.UserDTO"%>
<%@page import="item.ItemDTO"%>
<%@page import="favo.FavoriteDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="item.ItemDAO"%>
<%@page import="favo.FavoriteDAO"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>

<link rel="stylesheet" href="css/mypage.css">
<link rel="stylesheet" href="css/favolist.css">
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
	<button class="mypage" onclick="location='./myPage'">내 정보</button>
	<input class="mypage" type="button" value="나의 판매 목록"
		onclick="location='./orderdList'">
	<input class="mypage" type="button" value="나의 구매 목록"
		onclick="location='./bookingList'">
	<input class="mypage selected" type="button" value="나의 찜 목록"
		onclick="location='./favoList'">
	<p class="title logo">관심 아이템</p>

	<div class="contents_wrap">
		<%
		for (int i = 0; i < list.size(); i++) {
			FavoriteDTO favorite = list.get(i);
			ItemDTO item = idao.getdata(favorite.getItemCode());

			String title = item.getItem_tilte();
			String pic = item.getItem_pic();
			int price = item.getItem_price();
			int sellchk = item.getItem_seiling();
			int code = item.getItem_code();
			%>
		<div class="contents_top">
			<a class="contents_img_href" href="./itemView?code=<%=code %>"
				style="cursor: pointer">
				<div class="contents-photo">
					<img id="contents_img_size" src="<%=pic%>">
				</div>
				<div id="contents">
					<form id="favo<%=i + 1%>" method="post" action="./action">
						<input type="hidden" name="command" value="delfavo">
						<div><%=title%></div>
						<div><%=price%>원</div>
						<%
						if (sellchk == 0) {
						%>
						<div>판매중</div>
						<%} else if (sellchk== 1) {%>
						<div>판매완료</div>
						<%
						} 
						%>
						<input type="submit" value="삭제"> 
						<input type="hidden" value="<%=favorite.getFavoCode()%>" name="favocode">
					</form>
				</div>
			</a>
			</div>
			<%
			}
			%>
		</div>
	<div class="footer">
		<%@include file="../modules/footer.jsp"%>
	</div>
</body>
</html>
