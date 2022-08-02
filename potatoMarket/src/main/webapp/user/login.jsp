
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Title</title>
<script
	src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.2.js"
	charset="utf-8"></script>
</head>
<body>
	<div>
		<%@include file="../modules/header.jsp"%>
	</div>
	<div class="contents_wrap">
		<div class="">
			<div>
				<form method="post" action="action">
					<input type="hidden" name="command" value="login">
					<h2>
						아이디<span>*</span>
					</h2>
					<input type="text" name="id" placeholder="id" required>
					<h2>
						비밀번호<span>*</span>
					</h2>
					<input type="password" name="password" placeholder="password"
						required>
					<div class="buttons">
						<input type="submit" value="로그인"> <input type="button"
							value="회원가입" onclick="location.href='/join'">
					</div>
					<div class="container">
						<div class="login-area">
							<div id="message">로그인 버튼을 눌러 로그인 해주세요.</div>
							<div id="button_area">
								<div id="naverIdLogin"></div>
							</div>
						</div>
					</div>
					<div>
						<a href="javascript:kakaoLogin();"><img
							src="resource/kakao_login.png" alt="카카오계정 로그인"
							style="height: 60px;" /></a>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="footer">
		<%@include file="../modules/footer.jsp"%>
	</div>
	<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
	<script>
    function saveToDos(token) { //item을 localStorage에 저장합니다. 
        typeof(Storage) !== 'undefined' && sessionStorage.setItem('AccessKEY', JSON.stringify(token)); 
    };
    
        window.Kakao.init('c0c7d97ba874ef5115ea0b140bedc728');

        function kakaoLogin() {
            window.Kakao.Auth.login({
                scope: 'profile_nickname,account_email', //동의항목 페이지에 있는 개인정보 보호 테이블의 활성화된 ID값을 넣습니다.
                success: function(response) {
                	saveToDos(response.access_token)
                    window.Kakao.API.request({ // 사용자 정보 가져오기 
                        url: '/v2/user/me',
                        success: (res) => {
                            const kakao_account = res.kakao_account;
                            console.log(kakao_account)
                            console.log(JSON.stringify(kakao_account.profile)+"여기야~~");
                            
                            window.location.href='/potatoMarket/join_page?k_id='+JSON.stringify(res.id)+'&k_name='+JSON.stringify(res.profile_nickname); //리다이렉트 되는 코드
                        	
                        }
                    });
                   
                },
                fail: function(error) {
                    console.log(error);
                }
            });
        }
/*         const login = document.querySelector('#kakaoLogin');
        login.addEventListener('click', kakaoLogin); */
    </script>
	<script>
		const naverLogin = new naver.LoginWithNaverId({
			clientId : "Qc5AuIfVqlnmCqi8oeYe",
			callbackUrl : "http://localhost:8082/potatoMarket/user/naver_callback.html",
			loginButton : {
				color : "green",
				type : 3,
				height : 60
			}
		});
		naverLogin.init(); // 로그인 설정
	</script>
</body>
</html>
