
$('#id').change(e => {
	let check =  $('#id').val();
	console.log(check);
	$.ajax({

		url: 'http://localhost:8082/potatoMarket/ajaxCon?id=' + check,
		method: "POST"

	}).success(result => {

		console.log(result)
		console.log(typeof result)

		if (result == false) {
			$('#msg_error').hide();
			$('#msg_ok').show();
		} else {
			$('#msg_error').show();
			$('#msg_ok').hide();
		}
	})

})
