
var contractAcceptanceForm={};

$(document).ready(function(){
	$('#contractAcceptanceForm').validate();
     			$("#acceptanceDate").epsDatepicker();
     			$("#acceptanceApplyDate").epsDatepicker();
     			$("#createTime").epsDatepicker();
    if($('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/ContractAcceptanceController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
    		jsonObjectToForm('contractAcceptanceForm', json.contractAcceptance);
    	});
    }
	//返回
	$('#contractAcceptanceReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/ContractAcceptanceController.do");
	});
	//提交
	$('#contractAcceptanceSave').click(function(){
		if(!$('#contractAcceptanceForm').valid()){alert('请正确填写表单!');return;}
		$.getJSON($('#initPath').val()+'/ContractAcceptanceController.do?method=save', formToJsonObject('contractAcceptanceForm'), function(json){
			if(json.failure){alert(json.result);return;}
			$('#conBody').loadPage($('#initPath').val()+'/ContractAcceptanceController.do');
		});
	});

});
