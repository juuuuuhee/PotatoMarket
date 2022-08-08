// https://api.imgbb.com/1/upload?key=2fb4e168309749e4102b48b0184bd913
let filesArr = new Array();

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
		}
	};

	callUploadApi(settings);
	dataUpload();
}

let img_Url = "";

function callUploadApi(settings) {
	
	$.ajax(settings)
		.done(response => {
			console.log(response);
			let jx = JSON.parse(response);
			// jx.data.id의 값도 저장해야함 - 삭제 시 필요
			$('#img_url').val(jx.data.url);
			$('#del_url').val(jx.data.delete_url);
			let tmp = $('#img_url').val()
			console.log(typeof jx);
			
			let boardJson = {
				"url": "/upload",
				"method": "POST",
				"contentType": "application/json",

				"data": JSON.stringify({
					"user_id": $('#user_id').val(),
					"img_url": $('#img_url').val(),
					"contents": $('#b_contents').val(),
					"public_scope": $('#scope').val(),
					//"delete_url": $('#del_url').val()
				})
			};

			//img_Url =  $('#img_url').val();
	 		console.log(boardJson.data+"?");
			$.ajax(boardJson)
				.done(result => {
					console.log("uploadImg success");
				}).fail(error=>{
					console.log(error+"에러냐");
				})
		})
		.fail(error => {
			console.log(error);
		})
}

function dataUpload() {
	let user_code = $('#user_code').val();
	let title = $('#title').val();
	let contents= $('#contents').val();
	let price = $('#price').val();

	$.ajax({

		url: 'http://localhost:8080/potatoMarket/action?command=uploadPic',
		method: "POST",

		success: function(data) {
			console.log("넘어 오나여ㅛ?");
		
		

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
