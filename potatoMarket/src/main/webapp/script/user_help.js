$('#id_bnt').click(e=>{
	
	let checkName = $('#help_name').val();
	let checkPhone = $('#help_phone').val();
	
	$.ajax({
		url: 'http://localhost:8080/potatoMarket/ajaxFindId?help_name=김사과&help_phone=01012341234',
		method: "POST",

		success: function(data) {
			if(data){
				$("#result_id").html("아이디 = "+data);
			}else{
				alert("회원정보를 다시 확인해주세요.");
			}
		}
	})
	
})