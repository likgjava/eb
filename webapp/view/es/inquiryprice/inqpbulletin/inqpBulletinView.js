$(document).ready(function(){
	
	$("#signUpCondFactorView").loadPage($('#initPath').val()+'/SignUpCondFactorController.do?method=getSignUpCondFactorListByProjectId&type=detail&projectId='+$("input[name=projectId]").val());
});
//返回
function bullentinReturn(){
	var auditStatus = $('#auditStatus').val();
	var bullType = $('#bullType').val();
	$('#conBody').loadPage($('#initPath').val()+'/InqpbulletinController.do?method=getMoreForBulletinList&auditStatus='+auditStatus+'&bullType='+bullType);
	
}