$('#id').change(e=>{
    let id = document.getElementById()
    if(id = ""){
        $('#essential').show();
    }else {
        const requestData = {
            "id": $('#id').val()
        }
        $.ajax({
            url: "/joinChk/" + $('#id').val(),
            method: "POST",
            data: JSON.stringify(requestData),
            contentType: "application/json"
        }).success(result => {
            if (result) {
                $('#msg_error').hide();
                $('#msg_ok').show();
            } else {
                $('#msg_error').show();
                $('#msg_ok').hide();
            }
        })
    }
})
