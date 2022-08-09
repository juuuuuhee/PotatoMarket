// https://api.imgbb.com/1/upload?key=2fb4e168309749e4102b48b0184bd913
let filesArr = new Array();
let img_Url = "";

function uploadImg() {
	let form = new FormData();
	form.append("image", filesArr[0]);
	let settings = {
		"url": "https://api.imgbb.com/1/upload?key=2fb4e168309749e4102b48b0184bd913",
		"method": "POST",
		"timeout": 0,
		"processData": false,
		"mimeType": "multipart/form-data",
		"contentType": false,
		"data": form,
		sync: false,
		fail: error => {
			console.log("image upload fail");
			console.log(error);
		}
	};

	callUploadApi(settings);
}

// 사이트에 업로드하고, 해당 url을 받아온다 
function callUploadApi(settings) {

	$.ajax(settings)
		.done(response => {
			console.log(response);
			let jx = JSON.parse(response);
			// jx.data.id의 값도 저장해야함 - 삭제 시 필요
			$('#del_url').val(jx.data.delete_url);

			// 받아온 데이터(response)를 JSON화 시켜서 img_Url에 저장한다
			let tmp = JSON.parse(response);
			img_Url = tmp.data.url;
			dataUpload();
		})
		.fail(error => {
			console.log(error);
			img_Url = 'https://bit.ly/3Qa3opN';
			dataUpload();
		})
}

// 데이터베이스에 입력한 정보들을 저장한다
function dataUpload() {
	let user_code = $('#user_code').val();
	let title = $('#title').val();
	let contents = $('#contents').val();
	let price = $('#price').val();

	let url = `http://localhost:8080/potatoMarket/action?command=uploadPic&user_code=${user_code}&title=${title}&contents=${contents}&price=${price}&img_Url=${img_Url}`;
	$.ajax({
		url: url,
		method: "POST",
		success: function(data) {
			alert("글쓰기 성공!");
			location.href = './itemList';
		}
	})
}


function setSom(imgFile) {
	for (const file of imgFile.files) {
		let reader = new FileReader();
		reader.onload = function(event) {

			// 띄우기
			let img = document.createElement("img");
			img.setAttribute("src", event.target.result);
			img.setAttribute("class", "img_thumbnail");

			$('#img_box').append(img);
			filesArr.push(file);
		};
		reader.readAsDataURL(file);
	}
}

$('#title').keyup(e=>{
	let title = $('#title').val();
	
	
    if (title.length > 50) {
        $('#title').val($('#title').val().substring(0, 50));
        alert('글자수는 50자까지 입력 가능합니다.');
    };
})

$('#contents').keyup(e=>{
	let contents = $('#contents').val();
	
    if (contents.length > 2000) {
        $('#contents').val($('#contents').val().substring(0, 2000));
        alert('글자수는 2000자까지 입력 가능합니다.');
    };
})



