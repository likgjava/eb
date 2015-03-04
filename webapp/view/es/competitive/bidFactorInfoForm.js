var bidFactorInfoForm = {};
bidFactorInfoForm.removeFile = function(num){
	$("#removeFileEle"+num).prev('a').remove();
	$("#removeFileEle"+num).remove();
	$("[name=attrId"+num+"]").val("");
}
$(document).ready(function(){
	
});
