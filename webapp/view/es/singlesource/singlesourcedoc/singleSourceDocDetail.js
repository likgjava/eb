
var singleSourceDocDetail={};


singleSourceDocDetail.downloadFile = function(attachRelaId){	
	$.getJSON($('#initPath').val()+'/InqpDocBuyRecordController.do?method=dosIsDownLoad&projectId='+$("#projectId").val(),function(json){
		if(json.result)alert(json.result);if(json.failure)return;
	    if(json.isPay){
	    	$('#downLoadView').empty().loadPage($('#initPath').val()+"/view/es/singlesource/singlesourcedoc/docDownLoad.jsp?attachRelaId="+attachRelaId);
	    }
	});
}

singleSourceDocDetail.getFileName = function(attachRelaId){
	 $.getJSON($('#initPath').val()+'/InqpDocBuyRecordController.do?method=getAttachmentByobjId&attachmentId='+attachRelaId,function(json){
		 if(json.result)alert(json.result);if(json.failure)return;
		 $("#fileName").text(json.attachment.viewName);
		});
}

$(document).ready(function(){
   var AttachmentId = $("#attachmentId").val();
   singleSourceDocDetail.getFileName(AttachmentId);
    

});