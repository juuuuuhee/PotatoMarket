// 모달 선언
let modal = document.getElementById("myModal");

// 버튼 선언
let btn = document.getElementById("update");

// x자 모양
let span = document.getElementsByClassName("close")[0];

// 버튼 클릭시 모달 켜짐
function openmodal() {
	modal.style.display = "block"; 	
}
//여백클릭시 모달꺼짐
span.onclick = function () {
    modal.style.display = "none";
  }
  // x 클릭시 모달꺼짐
 function closemodal(){
      modal.style.display="none";
  }
  //비밀번호 입력 후 체크
  function chkpw(form){ 
	let userpw = $('#chkpw1').val();
	let chkpw = $('#chk').val();
	  console.log(chkpw);
	  console.log(userpw);
	  if(chkpw!==userpw){
		alert("비밀번호를 확인하세요");
		$('#chk').val("");
		  //modal.style.display ="none";
	  }else{
		form.submit();
	  }
	
	
	  
  }
