
var contractAcquirerForm={};

$(document).ready(function(){
	$('#contractAcquirerForm').validate();
     			$("#signedTime").epsDatepicker();
     			$("#createTime").epsDatepicker();
    if($('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/ContractAcquirerController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
    		jsonObjectToForm('contractAcquirerForm', json.contractAcquirer);
    	});
    }
	//返回
	$('#contractAcquirerReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/ContractAcquirerController.do");
	});
	//提交
	$('#contractAcquirerSave').click(function(){
		if(!$('#contractAcquirerForm').valid()){alert('请正确填写表单!');return;}
		$.getJSON($('#initPath').val()+'/ContractAcquirerController.do?method=save', formToJsonObject('contractAcquirerForm'), function(json){
			if(json.failure){alert(json.result);return;}
			$('#conBody').loadPage($('#initPath').val()+'/ContractAcquirerController.do');
		});
	});

});
