
var contractClauseForm={};

$(document).ready(function(){
	$('#contractClauseForm').validate();
    if($('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/ContractClauseController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
    		jsonObjectToForm('contractClauseForm', json.contractClause);
    	});
    }
	//返回
	$('#contractClauseReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/ContractClauseController.do");
	});
	//提交
	$('#contractClauseSave').click(function(){
		if(!$('#contractClauseForm').valid()){alert('请正确填写表单!');return;}
		$.getJSON($('#initPath').val()+'/ContractClauseController.do?method=save', formToJsonObject('contractClauseForm'), function(json){
			if(json.failure){alert(json.result);return;}
			$('#conBody').loadPage($('#initPath').val()+'/ContractClauseController.do');
		});
	});

});
