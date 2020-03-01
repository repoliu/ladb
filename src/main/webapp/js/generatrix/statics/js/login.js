
$("#password").keydown(function(e) {
    if (e.keyCode == 13) {
        checkLoginInfo();
    }
});

function checkLoginInfo() {
	var name = $.trim($("#name").val());
	var pwd = $.trim($("#password").val());
	if (name == "" || pwd == "") {
		return false;
	}

	$.ajax({
		type : 'post',
		url : rooturl+'/user/login.action',
		data : 'name='+name+'&password='+pwd+'',
		success : function(data) {
			if (data.name==name) {
				//alert(data.name);
                location.href = rooturl+"/DbAreaData/show.action";

                // alert("ok");
			} else {
				layer.msg('用户名或密码错误', {
                offset:200,
                icon:5,
                time : 1500
            });
        }
		}
	});
}
