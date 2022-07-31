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
				$('#msg_error').hide();
				$('#msg_ok').show();
			} else if(data.check === 2) {
				$('#msg_error').show();
				$('#msg_ok').hide();
			}else if(data.check === 3){
				$('#check').html("")
			}
		}
	})

})

$('#password2').keyup(e => {
	let password1 = $('#password1').val();
	let password2 = $('#password2').val();
	console.log(password1);
	console.log(password2);
	
	if(password1 !== password2){
		$('#pwcheck').css('color','red')
        $('#pwcheck').html("비밀번호가 일치하지않습니다.")
	}else{
		 $('#pwcheck').html("")
	}

})

$('#bnt').click(e => {
	let essential = $('.essntial').val();

	
	if(essential === null){
		$('#check').css('color','red')
		$('#check').html("필수 정보입니다.")
	}else{
		$('#check').html("")
	}
})
