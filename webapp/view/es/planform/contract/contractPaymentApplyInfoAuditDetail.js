
var contractPaymentApplyInfoAuditForm={};
$(document).ready(function(){
	if($('#objId').val()!=''){
    	var queryColumns=[];
    	$.getJSON($('#initPath').val()+'/ContractPaymentApplyInfoAuditController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
    		if(json.result)alert(json.result);if(json.failure)return;
    		json2Object('contractPaymentApplyInfoAuditDetailForm',json.contractPaymentApplyInfoAudit);
    	});
    }
	//返回
	$('#contractPaymentApplyInfoAuditReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+'/ContractPaymentApplyInfoAuditController.do');
	});
});