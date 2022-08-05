function saveToDos(token) { //item을 localStorage에 저장합니다. 
	typeof  (Storage) !== 'undefined' && sessionStorage.setItem('AccessKEY', JSON.stringify(token));
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
					//console.log(kakao_account)
					console.log(typeof JSON.stringify(kakao_account.profile.nickname) + "여기야~~");

					window.location.href = '/potatoMarket/join_page?k_id=' + JSON.stringify(res.id) + '&name=' + JSON.stringify(kakao_account.profile.nickname); //리다이렉트 되는 코드

				}
			});

		},
		fail: function(error) {
			console.log(error);
		}
	}); 
}


const naverLogin = new naver.LoginWithNaverId({
	clientId: "Qc5AuIfVqlnmCqi8oeYe",
	callbackUrl: "http://localhost:8080/potatoMarket/user/naver_callback.html",
	loginButton: {
		color: "green",
		type: 3,
		height: 60
	}
});
naverLogin.init(); // 로그인 설정

