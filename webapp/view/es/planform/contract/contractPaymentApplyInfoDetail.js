
var contractPaymentApplyInfoForm={};
$(document).ready(function(){
	if($('#objId').val()!=''){
    	var queryColumns=[];
    	$.getJSON($('#initPath').val()+'/ContractPaymentApplyInfoController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
    		if(json.result)alert(json.result);if(json.failure)return;
    		json2Object('contractPaymentApplyInfoDetailForm',json.contractPaymentApplyInfo);
    	});
    }
	//返回
	$('#contractPaymentApplyInfoReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+'/ContractPaymentApplyInfoController.do');
	});
});