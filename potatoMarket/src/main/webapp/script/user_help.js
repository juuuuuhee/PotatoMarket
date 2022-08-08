$('#id_bnt').click(e=>{
	
	let checkName = $('#help_name').val();
	let checkPhone = $('#help_phone').val();
	
	$.ajax({
		url: 'http://localhost:8080/potatoMarket/ajaxFindId?help_name='+ checkName +'&help_phone='+checkPhone,
		method: "POST",

		success: function(data) {
			if(data.id== ""){
				alert("회원정보를 다시 확인해주세요.");
			}else{
				$("#result_id").html("회원님의 아이디는 "+data.id+"입니다.");
			}
		}
	})
	
})

$('#pw_bnt').click(e=>{
	
	let checkId = $('#help_id').val();
	let checkPhone = $('#help_phone1').val();
	
	
	$.ajax({
		url: 'http://localhost:8080/potatoMarket/ajaxFindPw?help_id='+ checkId +'&help_phone1='+checkPhone,
		method: "POST",

		success: function(data) {
			if(data.pw== ""){
				alert("회원정보를 다시 확인해주세요.");
			}else{
				$("#result_pw").html("회원님의 비밀번호는 "+data.pw+"입니다.");
				//let link = './resetPw_page';
				//window.location.href = link;
				//window.location.replace(link);
				//window.open( link, "Child", "width=400, height=300, top=50, left=50" ); 
			}
		}
	})
	
})


$('#menuTitle_id').click(e=>{
	document.getElementById('menuTitle_id').className = 'menuName selected';
	document.getElementById('menuTitle_pw').className = 'menuName';
})

$('#menuTitle_pw').click(e=>{
	document.getElementById('menuTitle_id').className = 'menuName';
	document.getElementById('menuTitle_pw').className = 'menuName selected';
	
})

