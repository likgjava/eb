var changeUserPwd = {};


$(document).ready(function() {	
	//提交注册
	$("#save").click(function(){
		
		if(!$('#changeUserPwdForm').valid()){alert('请正确填写表单!','提示',{icon:'info'});return;}
		var oldPsw = hex_sha1($("#oldPsw").val());
		var newPsw = hex_sha1($("#usrPassrowd1").val());
		var flag=false;
		
		
		$.getJSON($('#initPath').val()+'/UserController.do?method=updateMyPsw', {"oldPsw":oldPsw,"newPsw":newPsw}, function(json){
			if(json.failure){
				alert(json.result);
				$("#changeUserPwdForm")[0].reset();
				return;
			} else{
				alert(json.result);
				$.ajax({
					url : $("#initPath").val()+"/logout.do",
					type :"POST"
				});
				$("#return").click();
			}
		});
	});
	
	$("#return").click(function(){
		document.location.href=$("#initPath").val()+"/IndexViewController.do?method=toLogin"
	})
});	