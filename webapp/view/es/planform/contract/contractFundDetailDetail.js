
var contractFundDetailForm={};
$(document).ready(function(){
	if($('#objId').val()!=''){
    	var queryColumns=[];
    	$.getJSON($('#initPath').val()+'/ContractFundDetailController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
    		if(json.result)alert(json.result);if(json.failure)return;
    		json2Object('contractFundDetailDetailForm',json.contractFundDetail);
    	});
    }
	//返回
	$('#contractFundDetailReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+'/ContractFundDetailController.do');
	});
});