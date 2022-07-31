<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <link rel="stylesheet" href="css/join.css">
    <title>Title</title>
</head>
<body>
<div class="wrap">
    <div>
		<%@include file="../modules/header.jsp"%>
    </div>
    <div class="contents_wrap">
        <div id="join_box">
            <h1>회원가입</h1>
            
            <form class="join_form" method="post" action="" id="joinsub">
            <input type="hidden" name="command" value="join">
            
                <div class="input">
                    <label>ID</label><br>
                    <input type="text" class="essential" id="id" placeholder="id" name="id"
                           pattern="^[a-zA-Z0-9]{5,9}"
                           title="아이디는 5글자에서 10글자를 입력해주세요 영문 대소문자, 숫자만 입력 가능합니다">
                    <span id="check"></span>
                    <span class="error_msg" id="msg_error">중복 된 아이디입니다.</span>
                    <span class="error_msg" id="msg_ok">사용가능한 아이디 입니다.</span>
                </div>
                
                <div class="input">
                    <label>PassWord</label><br>
                    <input type="password" class="essential" id="password1" placeholder="password" name="password"
                           pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}"
                           title="비밀번호는 영문, 숫자, 특수문자를 조합하여 최소8자리 이상을 입력해주세요">
                    <input type="password" id="password2" placeholder="Confirm Password">
                    <span id="check"></span>
                	<span id="pwcheck"></span>
                </div>
                
                <div class="input">
                    <label>Name</label><br>
                    <input type="text" class="essential" placeholder="name" name="name">
                    <span id="check"></span>
                </div>
                
                <div class="input">
                    <label>address</label><br>
                    <input type="text" class="essential" placeholder="우편번호" name="address_1" class="address_1" id="address_1" readonly>
                    <span id="check"></span>
                    <input type="button" value="우편번호 찾기" onclick="find_address()" id="find_button"> <br>
                    <input type="text" class="essential" name="address_2" class="address_2" placeholder="도로명주소" readonly><br>
                    <span id="check"></span>
                </div>
                
                <div class="input">
                    <label>phone</label><br>
                    <select name="contact_1" id="phone_1" class="essential">
                        <option value="010">010</option>
                        <option value="010">011</option>
                        <option value="010">017</option>
                    </select>
                    <span>-</span>
                    <input type="number" class="essential" placeholder="0000" name="contact_2" id="phone_2" pattern=".{3,4}" title="전화번호를 3자리에서 4자리 입력해주세요">
                    <span>-</span>
                    <input type="number" class="essential" placeholder="0000" name="contact_3" id="phone_3" pattern=".{4,4}" title="전화번호를 4자리 입력해주세요">
                	<span id="check"></span>
                </div>
                
                <div class="form_submit">
                    <input type="submit" id="bnt" value="회원 가입">
                </div>	
            </form>
        </div>
    </div>
    <div class="footer">
        <%@include file="../modules/footer.jsp"%>
    </div>
</div>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function find_address() {
        new daum.Postcode({
            oncomplete: function (data) {
                $('.address_1').val(data.zonecode);
                $('.address_2').val(data.address);
            }
        }).open();
    }
</script>
<script src="script/validation.js"></script>
</body>
</html>
