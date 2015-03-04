
var contractPaymentInfoForm={};
$(document).ready(function(){
	if($('#objId').val()!=''){
    	var queryColumns=[];
    	$.getJSON($('#initPath').val()+'/ContractPaymentInfoController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
    		if(json.result)alert(json.result);if(json.failure)return;
    		json2Object('contractPaymentInfoDetailForm',json.contractPaymentInfo);
    	});
    }
	//返回
	$('#contractPaymentInfoReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+'/ContractPaymentInfoController.do');
	});
});