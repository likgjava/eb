
var projProcessRuleForm={};

$(document).ready(function(){
	
	$('#projProcessRuleForm').validate();
     			$("#createTime").epsDatepicker();
     			$("#updateTime").epsDatepicker();
	//返回
	$('#projProcessRuleReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/ProjProcessRuleController.do");
	});
	//提交
	$('#projProcessRuleSave').click(function(){
		if(!$('#projProcessRuleForm').valid()){alert('请正确填写表单!');return;}
		var formJSON = formToJsonObject('projProcessRuleForm');
		$.getJSON($('#initPath').val()+'/ProjProcessRuleController.do?method=saveProjProcessRule', formJSON, function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			if($("#fromType").val()=='fromList'){
				planTemplateTask.refresh($('#projectTaskId').val())
			}else{
				general_agent.viewProjectRule($("input[name=project.objId]").val());
			}
		});
	});

});
