$('#id').change(e => {
	let check = $('#id').val();
	console.log(check);
	$.ajax({

		url: 'http://localhost:8082/potatoMarket/ajaxCon?id=' + check,
		method: "POST",

		success: function(data) {
			console.log(data);
			// alert(data.check);
			if (data.check === 1) {
				$('#msg_').css('color', '#4ae182')
				$('#msg_').html("사용 가능한 아이디 입니다.");
			} else if (data.check === 2) {
				$('#msg_').css('color', 'red')
				$('#msg_').html("중복 된 아이디입니다.");
			} else {
				$('#msg_').html("")
			}
		}
	})

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
	e.preventDefault();

	let $ess = $('.essential');
		if ($ess !== "") {
			e.preventDefault();
		}
	for (let i = 0; i < $ess.length; i++) {
		if ($($ess.get(i)).val() === "") {
			$('.check').css('color', 'red')
			$($ess.get(i)).siblings('.check').text("필수 정보입니다.")
		} else {
			$($ess.get(i)).siblings('.check').text("?")
		}
	}
})











