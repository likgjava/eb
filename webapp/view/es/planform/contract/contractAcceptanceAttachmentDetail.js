
var contractAcceptanceAttachmentForm={};
$(document).ready(function(){
	if($('#objId').val()!=''){
    	var queryColumns=[];
    	$.getJSON($('#initPath').val()+'/ContractAcceptanceAttachmentController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
    		if(json.result)alert(json.result);if(json.failure)return;
    		json2Object('contractAcceptanceAttachmentDetailForm',json.contractAcceptanceAttachment);
    	});
    }
	//返回
	$('#contractAcceptanceAttachmentReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+'/ContractAcceptanceAttachmentController.do');
	});
});