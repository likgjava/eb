
var contractPaymentInfoForm={};

$(document).ready(function(){
	$('#contractPaymentInfoForm').validate();
     			$("#paymentTime").epsDatepicker();
     			$("#createTime").epsDatepicker();
    if($('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/ContractPaymentInfoController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
    		jsonObjectToForm('contractPaymentInfoForm', json.contractPaymentInfo);
    	});
    }
	//返回
	$('#contractPaymentInfoReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/ContractPaymentInfoController.do");
	});
	//提交
	$('#contractPaymentInfoSave').click(function(){
		if(!$('#contractPaymentInfoForm').valid()){alert('请正确填写表单!');return;}
		$.getJSON($('#initPath').val()+'/ContractPaymentInfoController.do?method=save', formToJsonObject('contractPaymentInfoForm'), function(json){
			if(json.failure){alert(json.result);return;}
			$('#conBody').loadPage($('#initPath').val()+'/ContractPaymentInfoController.do');
		});
	});

});
