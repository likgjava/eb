/*
 * 供应商资质表单(弹出用)
 * created by yucy
 */
var supplierQualityAudit={};

//提交审核
supplierQualityAudit.audit = function(statu){
	if(!$('#paramForm').valid()){alert('请正确填写表单!');return;}
	var paramForm = formToJsonObject("paramForm");
	paramForm.statu = statu;
	$.getJSON($('#initPath').val()+"/OrgQualityController.do?method=saveAuditStatus",paramForm,function(json){
		if(json.success){
			$('.epsDialogClose').trigger('click');
			//跳转页面
			$('#conBody').loadPage($('#initPath').val()+"/OrgQualityController.do?method=toOrgQualityAuditView");
		}else{
			alert("操作失败!");
		}
	})
}


$(document).ready(function(){
	
	var resId = $("#qualificationFile").attr("value")
	$('#qualificationFile').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=qualificationFile&isSelect=yes&attachRelaId='+resId);
	
	//通过
	$("#pass").click(function(){
		if(confirm('确认通过？')){
			supplierQualityAudit.audit('pass');
		}
	})
	
	//不通过
	$("#nopass").click(function(){
		if(confirm('确认不通过？')){
			supplierQualityAudit.audit('nopass');
		}
	})
	
	//点击关闭
	$("#close").click(function(){
		$('.epsDialogClose').trigger('click');
	})
});