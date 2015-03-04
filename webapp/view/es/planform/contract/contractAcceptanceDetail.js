
var contractAcceptanceForm={};
$(document).ready(function(){
	if($('#objId').val()!=''){
    	var queryColumns=[];
    	$.getJSON($('#initPath').val()+'/ContractAcceptanceController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
    		if(json.result)alert(json.result);if(json.failure)return;
    		json2Object('contractAcceptanceDetailForm',json.contractAcceptance);
    	});
    }
	//返回
	$('#contractAcceptanceReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+'/ContractAcceptanceController.do');
	});
});