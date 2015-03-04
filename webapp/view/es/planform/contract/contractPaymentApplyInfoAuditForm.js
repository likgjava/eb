
var contractPaymentApplyInfoAuditForm={};

$(document).ready(function(){
	$('#contractPaymentApplyInfoAuditForm').validate();
     			$("#auditTime").epsDatepicker();
     			$("#createTime").epsDatepicker();
    if($('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/ContractPaymentApplyInfoAuditController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
    		jsonObjectToForm('contractPaymentApplyInfoAuditForm', json.contractPaymentApplyInfoAudit);
    	});
    }
	//返回
	$('#contractPaymentApplyInfoAuditReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/ContractPaymentApplyInfoAuditController.do");
	});
	//提交
	$('#contractPaymentApplyInfoAuditSave').click(function(){
		if(!$('#contractPaymentApplyInfoAuditForm').valid()){alert('请正确填写表单!');return;}
		$.getJSON($('#initPath').val()+'/ContractPaymentApplyInfoAuditController.do?method=save', formToJsonObject('contractPaymentApplyInfoAuditForm'), function(json){
			if(json.failure){alert(json.result);return;}
			$('#conBody').loadPage($('#initPath').val()+'/ContractPaymentApplyInfoAuditController.do');
		});
	});

});
