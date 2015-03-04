
var contractOperInfoForm={};
$(document).ready(function(){
	if($('#objId').val()!=''){
    	var queryColumns=[];
    	$.getJSON($('#initPath').val()+'/ContractOperInfoController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
    		if(json.result)alert(json.result);if(json.failure)return;
    		json2Object('contractOperInfoDetailForm',json.contractOperInfo);
    	});
    }
	//返回
	$('#contractOperInfoReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+'/ContractOperInfoController.do');
	});
});