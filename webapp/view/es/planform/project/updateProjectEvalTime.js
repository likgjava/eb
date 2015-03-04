
var updateProjectEvalTime={};
//对时间进行校验
updateProjectEvalTime.checkTime=function(){
	if($("#evalStartTime").val()>=$("#evalEndTime").val()){alert('评标结束时间应大于评标开始时间！');return false;}
	return true;
}

//保存
$('#projectSaveEvalTime').click(function(){
	if(!$('#evalStartTimeForm').valid()){alert('请正确填写表单!');return;}
	//同步时间
	if(updateProjectEvalTime.checkTime()){
		if(window.confirm("确认保存?")){
		$.getJSON($('#initPath').val()+'/ProjectRuleController.do?method=updateProjectEvalTime', formToJsonObject('evalStartTimeForm'), function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
			planTemplateTask.refresh($("#projectTaskId").val()+"");
		});
	 }
	}
})


$(document).ready(function(){
	$('#evalStartTimeForm').validate();
	$("#evalStartTime").epsDatepicker({ timeShow:true});
	$("#evalEndTime").epsDatepicker({ timeShow:true});

});



