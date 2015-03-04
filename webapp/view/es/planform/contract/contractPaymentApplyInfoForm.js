
var contractPaymentApplyInfoForm={};

$(document).ready(function(){
	$('#contractPaymentApplyInfoForm').validate();
     			$("#applyDate").epsDatepicker();
     			$("#createTime").epsDatepicker();
    if($('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/ContractPaymentApplyInfoController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
    		jsonObjectToForm('contractPaymentApplyInfoForm', json.contractPaymentApplyInfo);
    	});
    }
	//返回
	$('#contractPaymentApplyInfoReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/ContractPaymentApplyInfoController.do");
	});
	//提交
	$('#contractPaymentApplyInfoSave').click(function(){
		if(!$('#contractPaymentApplyInfoForm').valid()){alert('请正确填写表单!');return;}
		$.getJSON($('#initPath').val()+'/ContractPaymentApplyInfoController.do?method=save', formToJsonObject('contractPaymentApplyInfoForm'), function(json){
			if(json.failure){alert(json.result);return;}
			$('#conBody').loadPage($('#initPath').val()+'/ContractPaymentApplyInfoController.do');
		});
	});

});
