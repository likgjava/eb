
var planTemplateForm={};
$(document).ready(function(){
	if($('#objId').val()!=''){
    	var queryColumns=[];
    	$.getJSON($('#initPath').val()+'/PlanTemplateController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
    		if(json.result)alert(json.result);if(json.failure)return;
    		json2Object('planTemplateDetailForm',json.planTemplate);
    	});
    }
	//返回
	$('#planTemplateReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+'/PlanTemplateController.do');
	});
});