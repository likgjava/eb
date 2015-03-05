

$(document).ready(function(){
	var contractFile_value ='demo';
	$('#contractFile').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=contractFile&maxSize=1024&attachRelaId='+contractFile_value);
	
	$('#save').click(function(){
		alert(obj2str(formToJsonObject('contractForm')));
	})
});
	
