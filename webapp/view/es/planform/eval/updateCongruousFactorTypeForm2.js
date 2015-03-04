
var congruousFactorTypeForm={};
$(document).ready(function(){
	//返回
	$('#back').click(function(){
		$('#epsDialogCloseNoReload').click();
		$("#congruousFactorGrid").reload();
	});
	$('#congruousFactorTypeSave').click(function(){
		$.getJSON($('#initPath').val()+'/CongruousFactorTypeController.do?method=checkTypecodeUnique', {"typeCode":$("#typeCode").val(),"projectId":$("#projId").val(),"objId":$("#objId").val()}, function(json){
			if(json.notUnique == 'true'){
				$("#typeCodeCheck").text("编号不得重复，请重新输入！");
			}else{
				if(!$('#congruousFactorTypeForm').valid()){alert('请正确填写表单!');return;}
				$.getJSON($('#initPath').val()+'/CongruousFactorTypeController.do?method=saveCongruousFactorType', formToJsonObject('congruousFactorTypeForm'), function(json){
					if(json.result)alert(json.result);if(json.failure)return;
					alert("保存成功！");
					$("#epsDialogClose").click();
					if($("#projectTaskId") && $("#projectTaskId").val() != null && $("#projectTaskId").val() != "") {
				    	planTemplateTask.clickMethod($("#projectTaskId").val()+"");
				   		planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
				    }
				});
			}
		})

	});
});
