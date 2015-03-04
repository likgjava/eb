
var contractPaymentTimesForm={};

$(document).ready(function(){
	$('#contractPaymentTimesForm').validate();
     			$("#payTime").epsDatepicker();
     			$("#createTime").epsDatepicker();
    if($('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/ContractPaymentTimesController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
    		jsonObjectToForm('contractPaymentTimesForm', json.contractPaymentTimes);
    	});
    }
	//返回
	$('#contractPaymentTimesReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/ContractPaymentTimesController.do");
	});
	//提交
	$('#contractPaymentTimesSave').click(function(){
		if(!$('#contractPaymentTimesForm').valid()){alert('请正确填写表单!');return;}
		$.getJSON($('#initPath').val()+'/ContractPaymentTimesController.do?method=save', formToJsonObject('contractPaymentTimesForm'), function(json){
			if(json.failure){alert(json.result);return;}
			$('#conBody').loadPage($('#initPath').val()+'/ContractPaymentTimesController.do');
		});
	});

});
