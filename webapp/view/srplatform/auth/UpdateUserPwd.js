//定义文件全局变量 处理方法名重复问题
var UserForm={};

$(document).ready(function(){

	$("#submit").click(function(){
		if(!$('#pswForm').valid()){alert('请正确填写表单!','提示',{icon:'info'});return;}
		if($("input[name=newPsw]").val()!=$("input[name=newPsw2]").val()){
			alert("两次新密码不一致！");
			return;
		}
		$('#oldPsw').attr('value',hex_sha1($('#oldPsw').val()));
		$('#newPsw').attr('value',hex_sha1($('#newPsw').val()));
		$.getJSON($('#initPath').val()+'/UserController.do?method=updateMyPsw', formToJsonObject('pswForm'), function(json){
			if(json.result)alert(json.result,{inco:'info'});
			if(json.failure)return;
			$('#epsDialogClose').click();
		});
			
	})
})

