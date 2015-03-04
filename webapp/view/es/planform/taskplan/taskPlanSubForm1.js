/*
 * 执行平台，采购申报书明细表单
 * author: 梁潇静
 * mail: liangxj@gpcsoft.com
 */
var taskPlanSubForm={};
$(document).ready(function(){
	
    if($('#planSubId').val()!=''){
    	$.getJSON($('#initPath').val()+'/PreqEntryController.do?method=createOrUpdate',{objId:$('#preqEntryId').val(),includedProperties:'purchase'},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
    		jsonObjectToForm('preqEntryForm', json.preqEntry);
    	});
    }

	//提交
	$('#savePreqEntry').click(function(){
		var preqEntry = formToJsonObject('preqEntryForm','json');
		if(!$('#preqEntryForm').valid()){alert('请正确填写表单!');return;}
		$.getJSON($('#initPath').val()+'/PreqEntryController.do?method=savePreqEntry',preqEntry, function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			$('#epsDialogCloseNoReload').click();
		});
	});
	
	//关闭
	$('#taskPlanSubClose').click(function(){
		$('#epsDialogCloseNoReload').click();
	});
});
