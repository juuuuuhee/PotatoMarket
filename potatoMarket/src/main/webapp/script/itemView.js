
function changeFavo() {
	$('#command').val('addFavo');
	if($('#favocode').val()!=-1){
		alert("이미 찜한 아이템 입니다.");
	}else{
		alert("찜 목록에 추가 되었습니다.");
	}
	$('form').submit();
}

