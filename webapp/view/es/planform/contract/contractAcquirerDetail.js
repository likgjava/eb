
var contractAcquirerForm={};
$(document).ready(function(){
	if($('#objId').val()!=''){
    	var queryColumns=[];
    	$.getJSON($('#initPath').val()+'/ContractAcquirerController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
    		if(json.result)alert(json.result);if(json.failure)return;
    		json2Object('contractAcquirerDetailForm',json.contractAcquirer);
    	});
    }
	//返回
	$('#contractAcquirerReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+'/ContractAcquirerController.do');
	});
});