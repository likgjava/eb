
var contractAcceptanceAttachmentForm={};

$(document).ready(function(){
	$('#contractAcceptanceAttachmentForm').validate();
     			$("#createTime").epsDatepicker();
    if($('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/ContractAcceptanceAttachmentController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
    		jsonObjectToForm('contractAcceptanceAttachmentForm', json.contractAcceptanceAttachment);
    	});
    }
	//返回
	$('#contractAcceptanceAttachmentReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/ContractAcceptanceAttachmentController.do");
	});
	//提交
	$('#contractAcceptanceAttachmentSave').click(function(){
		if(!$('#contractAcceptanceAttachmentForm').valid()){alert('请正确填写表单!');return;}
		$.getJSON($('#initPath').val()+'/ContractAcceptanceAttachmentController.do?method=save', formToJsonObject('contractAcceptanceAttachmentForm'), function(json){
			if(json.failure){alert(json.result);return;}
			$('#conBody').loadPage($('#initPath').val()+'/ContractAcceptanceAttachmentController.do');
		});
	});

});
