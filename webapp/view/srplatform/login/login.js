/*
 *登录脚本
*/
$(document).ready(function(){
	
	//标签页
	$('#userLogin').tabs();
	
    //重新加载验证码
	$('#randImg').click(function(){
		var timestamp = (new Date()).valueOf();  
		$(this).attr('src',$(this).attr('src')+'&flag='+timestamp);
	})

	//普通登录
	$("#login").click(function(){
		
		/*******************用户名和密码空值验证*************************************/
		$('#j_username').next('span').html('');
		$('#j_password').next('span').html('');
		
		if($('#j_username').val().length<1){
			$('#ErrorMsg').html('用户名不能为空').parent().show();
			return false;
		}
		if($('#j_password').val().length<1){
			$('#ErrorMsg').html('密码不能为空').parent().show();
			return false;
		}
		if($('#j_rand').val().length<1){
			$('#ErrorMsg').html('验证码不能为空').parent().show();
			return false;
		}
		/*******************end 用户名和密码空值验证**********************************/
		$('#j_password').attr('value',hex_sha1($('#j_password').val()));
		$.ajax({
			url : $("#initPath").val()+"/login.do",
			data : $('#loginForm').serialize(),
			type : 'post',
			dataType: 'json',
			cache : 'false',
			success : function(json){	
				if(json.failure){
					if (json.failure == "true") {
						//返回失败信息
						$("#ErrorMsg").html(json.result).parent().show();
					}
					$('#j_password').val('');
					$('#randImg').click();
					return;
				}else{
					window.location.href=$("#initPath").val()+"/IndexViewController.do?method=index";
				}
			},
			error : function(msg) {
				$("#ErrorMsg").html("请求异常，请检查网络连接").parent().show();
				$('#j_password').attr('value','');
			}
		})
		return false;
	});
	
	//注册
	$("#registration").click(function(){
		window.open($('#initPath').val()+'/RegistrationController.do?method=toRegistration');
	})
	
	//忘记密码，找回密码
	$("#getPassWord").click(function(){
		window.open($('#initPath').val()+'/UserSecurityController.do?method=getPassword&step=1');
	})
	
});
