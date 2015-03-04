//定义文件全局变量 处理方法名重复问题

var expertForm={};

$(document).ready(function(){
	
	$.validator.addMethod(
			"unique",
			function(value,element,param){
				return uniqueHandler("User","usName",value);
			},
			'账号已经存在'
	);
	$("#expertForm").validate(
			{
				rules:{
					usName:{unique:"usName"}
				}
			}
	);	
    
    
	//提交
	$('#saveExpert').click(function(){
		if(!$('#expertForm').valid()){alert('请正确填写表单!','提示',{icon:'info'});return;}
		if($("input[name=password]").val()!=$("input[name=password2]").val()){
			alert("两次密码不一致！");
			return;
		}
		$.getJSON($('#initPath').val()+'/WorkgMemberController.do?method=saveEvalBidMember', formToJsonObject('expertForm'), function(json){
			if(json.result)alert(json.result);;if(json.failure)return;
			$('#epsDialogClose').click();
			
			if ($('#formTab').val()=='yes') {
				$('#agentTabDiv').loadPage($('#initPath').val()+'/WorkGroupController.do?method=toWorkGroupForProject&fromTab=yes&groupType=02&projectId='+$('#projectId').val());
			}else{
				$('#projectDoDiv').loadPage($('#initPath').val()+'/WorkGroupController.do?method=toWorkGroupForProject&groupType=02&projectId='+$('#projectId').val());
			}
//			if($('#agentTabDiv'))
//				$('#agentTabDiv').loadPage($('#initPath').val()+'/WorkGroupController.do?method=toWorkGroupForProject&groupType=02&projectId='+$('#projectId').val());
//			if($('#projectDoDiv'))
//				$('#projectDoDiv').loadPage($('#initPath').val()+'/WorkGroupController.do?method=toWorkGroupForProject&groupType=02&projectId='+$('#projectId').val());
		});
	})

})
