/*
 *登录脚本
*/
$(document).ready(function(){
	
	//重新加载验证码
	$('#randImg_div').click(function(){
		var timestamp = (new Date()).valueOf();  
		$(this).attr('src',$(this).attr('src')+'&flag='+timestamp);
	})

	//普通登录
	$("#login_div").click(function(){
		/*******************用户名和密码空值验证*************************************/
		$('#j_username_div').next('span').html('');
		$('#j_password_div').next('span').html('');
		if($('#j_username_div').val().length<1){
			$('#ErrorMsg').html('<font color="red">用户名不能为空</font>');
			return false;
		}
		if($('#j_password_div').val().length<1){
			$('#ErrorMsg').html('<font color="red">密码不能为空</font>');
			return false;
		}
		if($('#j_rand_div').val().length<1){
			$('#ErrorMsg').html('<font color="red">验证码不能为空</font>');
			return false;
		}
		/*******************end 用户名和密码空值验证**********************************/
		$('#j_password_div').attr('value',hex_sha1($('#j_password_div').val()));
		$.ajax({
			url : $("#initPath").val()+"/login.do",
			data : $('#loginDivForm').serialize(),
			type : 'post',
			dataType: 'json',
			cache : 'false',
			success : function(json){	
				if(json.failure){
					if (json.failure == "true") {
						//返回失败信息
						$("#ErrorMsg").html("<font color='red'>"
										+ json.result
										+ "</font>");
					}
					$('#j_password_div').val('');
					$('#randImg_div').click();
					return;
				}else{
					//获取当前用户
					$.getJSON($('#initPath').val()+"/IndexViewController.do?method=getCurrentUser", {} , function(userjson){
						if(userjson.failure){if(userjson.result)alert(userjson.result);return;}
						common.getCurrUser(userjson);
						$('#loginSuccess').val('1'); //标记登录成功
						$('.epsDialogClose').trigger('click');
					});
				}
			},
			error : function(msg) {
				$("#ErrorMsg").html("<font color='red'>请求异常，请检查网络连接</font>");
				$('#j_password_div').attr('value','');
			}
		})
	})
	
	//注册
	$("#registration_div").click(function(){
		$('.epsDialogClose').trigger('click');
		window.open($('#initPath').val()+'/RegistrationController.do?method=toRegistration');
	})
	
	//忘记密码，找回密码
	$("#getPassWord").click(function(){
		$('.epsDialogClose').trigger('click');
		window.open($('#initPath').val()+'/UserSecurityController.do?method=getPassword&step=1');
	})
});

