
// 提交
$('#record_submit').click(function(){
	if(!$('#evalFactorResultForm').valid()){
		return false;
	}
	var jsonObj = formToJsonObject('evalFactorResultForm','jsonUtils');
	jsonObj.evalFactorResults = JSON.stringify(jsonObj.evalFactorResult);
	delete jsonObj.evalFactorResult;
	jsonObj.packId = $('[name=subProjId]').val();
	jsonObj["supplier.objId"] = $('[id=supplier.objId]').val();
	$.getJSON($('#initPath').val()+'/EvaSellerRecordController.do?method=updateEvaSellerRecord',jsonObj, function(json){
		if(json.result)alert(json.result);if(json.failure)return;
		EvalTypeForm.refreshFactorList();// 刷新打分列表
   		planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
	});
})
$(document).ready(function(){
	// 计算总分
	$('#evalFactorResultForm input[type=text][name$=score]').keyup(function(){
		var factorScore = 0;
		$('#evalFactorResultForm input[type=text][name$=score]').each(function(i,n){
			factorScore += n.value * 1;
		})
		$('[name=factorScore]').val(factorScore);
	})
})