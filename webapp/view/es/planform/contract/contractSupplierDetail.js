
var contractSupplierForm={};
$(document).ready(function(){
	if($('#objId').val()!=''){
    	var queryColumns=[];
    	$.getJSON($('#initPath').val()+'/ContractSupplierController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
    		if(json.result)alert(json.result);if(json.failure)return;
    		json2Object('contractSupplierDetailForm',json.contractSupplier);
    	});
    }
	//返回
	$('#contractSupplierReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+'/ContractSupplierController.do');
	});
});