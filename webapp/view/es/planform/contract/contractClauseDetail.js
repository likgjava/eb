
var contractClauseForm={};
$(document).ready(function(){
	if($('#objId').val()!=''){
    	var queryColumns=[];
    	$.getJSON($('#initPath').val()+'/ContractClauseController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
    		if(json.result)alert(json.result);if(json.failure)return;
    		json2Object('contractClauseDetailForm',json.contractClause);
    	});
    }
	//返回
	$('#contractClauseReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+'/ContractClauseController.do');
	});
});