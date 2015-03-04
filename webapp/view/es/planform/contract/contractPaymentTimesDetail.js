
var contractPaymentTimesForm={};
$(document).ready(function(){
	if($('#objId').val()!=''){
    	var queryColumns=[];
    	$.getJSON($('#initPath').val()+'/ContractPaymentTimesController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
    		if(json.result)alert(json.result);if(json.failure)return;
    		json2Object('contractPaymentTimesDetailForm',json.contractPaymentTimes);
    	});
    }
	//返回
	$('#contractPaymentTimesReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+'/ContractPaymentTimesController.do');
	});
});