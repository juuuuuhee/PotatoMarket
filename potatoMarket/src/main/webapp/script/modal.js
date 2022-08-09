// 모달 선언
let modal = document.getElementById("myModal");

// 버튼 선언
let btn = document.getElementById("update");

// x자 모양
let span = document.getElementsByClassName("close")[0];

// 버튼 클릭시 모달 켜짐
function openmodal() {
	$('#chk').val("");
	modal.style.display = "block";
}

// x 클릭시 모달꺼짐
span.onclick = function() {
	modal.style.display = "none";
}
function closemodal() {
	$('#chk').val("");
	modal.style.display = "none";
}
//비밀번호 입력 후 체크
function chkpw(form) {
	let userpw = $('#chkpw1').val();
	let chkpw = $('#chk').val();
	console.log(userpw);
	console.log(chkpw);
	if ($('#chkpw1').val() !== $('#chk').val()) {
		alert("비밀번호를 확인하세요");
		$('#chk').val("");
		//modal.style.display ="none";
	} else {
		form.submit();
	}
}
window.onclick = function(event) {
	if (event.target == modal) {
		$('#chk').val("");
		modal.style.display = "none";
	}
}


$(document).keypress(function(e) {
	if (e.keyCode == 13)
		e.preventDefault();
});

