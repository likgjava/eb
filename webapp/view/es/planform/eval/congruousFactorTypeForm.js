
var congruousFactorTypeForm={};
$(document).ready(function(){
	$('#congruousFactorTypeSave').click(function(){
		if(!$('#congruousFactorTypeForm2').valid()){alert('请正确填写表单!');return;}
		$.getJSON($('#initPath').val()+'/CongruousFactorTypeController.do?method=saveCongruousFactorTypeOther', formToJsonObject('congruousFactorTypeForm2'), function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			alert("保存成功！");
			$("#epsDialogClose").click();
			if($("#projectTaskId") && $("#projectTaskId").val() != null && $("#projectTaskId").val() != "") {
		    	planTemplateTask.clickMethod($("#projectTaskId").val()+"");
		   		planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
		    }
		});
	});
	$("#return").click(
			function(){
				$("#epsDialogClose").click();
			}
	)
});
