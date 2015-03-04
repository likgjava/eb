
var contractOperInfoForm={};

$(document).ready(function(){
	$('#contractOperInfoForm').validate();
     			$("#operDate").epsDatepicker();
     			$("#createTime").epsDatepicker();
    if($('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/ContractOperInfoController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
    		jsonObjectToForm('contractOperInfoForm', json.contractOperInfo);
    	});
    }
	//返回
	$('#contractOperInfoReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/ContractOperInfoController.do");
	});
	//提交
	$('#contractOperInfoSave').click(function(){
		if(!$('#contractOperInfoForm').valid()){alert('请正确填写表单!');return;}
		$.getJSON($('#initPath').val()+'/ContractOperInfoController.do?method=save', formToJsonObject('contractOperInfoForm'), function(json){
			if(json.failure){alert(json.result);return;}
			$('#conBody').loadPage($('#initPath').val()+'/ContractOperInfoController.do');
		});
	});

});
