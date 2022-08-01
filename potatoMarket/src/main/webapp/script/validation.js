

$('#id').change(e => {

	let check = $('#id').val();
	console.log(check);
	$.ajax({

		url: 'http://localhost:8082/potatoMarket/ajaxCon?id=' + check,
		method: "POST",

		success: function(data) {
			console.log(data);
			let idPattern = /^[A-Za-z0-9_\-]{5,20}$/;

			if (!idPattern.test($('#id').val())) {
				$('.id_check').html("5~20자 영문 대,소문자 숫자를 사용하세요<br>특수문자 사용불가");
				$('.id_check').css('color', 'red')
				$('#msg_').html("");
			} else {
				$('.id_check').html("");
				if (data.check == 1) {
					$('#msg_').css('color', '#26652e');
					
					$('#msg_').html("사용 가능한 아이디 입니다.");
				} else if (data.check == 2) {
					$('#msg_').css('color', 'red')
					$('#msg_').html("중복 된 아이디입니다.");
				}
			}
		}
	})
})


$('#password1').change(e => {
	let pwPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^*()\-_=+\\\|\[\]{};:\'",.<>\/?]).{8,16}$/;

	if (!pwPattern.test($('#password1').val())) {		
		$('#pwd1_check').css('color','red');
		$('#pwd1_check').html("<br>8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.");
	} else {
		$('#pwd1_check').html("");
	}
})

$('#password2').keyup(e => {
	let password1 = $('#password1').val();
	let password2 = $('#password2').val();
	console.log(password1);
	console.log(password2);

	if (password1 !== password2) {
		$('#pwcheck').css('color', 'red')
		$('#pwcheck').html("비밀번호가 일치하지않습니다.")
	} else {
		$('#pwcheck').html("")
	}

})


$('#bnt').click(e => {

	let $ess = $('.essential');
	let check = true;
	for (let i = 0; i < $ess.length; i++) {
		if ($($ess.get(i)).val() === "") {
			$('.check').css('color', 'red');
			$($ess.get(i)).siblings('.check').text("필수 정보입니다.");
			check = false;
			console.log(check + $($ess.get(i)).val());
		} else {
			$($ess.get(i)).siblings('.check').text("");
		}
	}
	console.log(check);
	if (check === false) {
		e.preventDefault();
	}
})











