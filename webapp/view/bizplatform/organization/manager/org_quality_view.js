/*
 * 供应商资质表单(弹出用)
 * created by yucy
 */
var supplierQualityAudit={};

//提交审核
supplierQualityAudit.audit = function(statu){
	$.getJSON($('#initPath').val()+"/OrgQualityController.do?method=saveAuditStatus",{"statu":statu,"objId":$("#objId").val()},function(json){
		if(json.success){
			alert(json.result);
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
	
	
//	if(null!=resId&&""!=resId){
//		$('#qualificationFile').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=qualificationFile&isSelect=yes&attachRelaId='+resId);
//	}
//	else{
//		$('#qualificationFile').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=qualificationFile&isSelect&isSelect=yes');
//	}
	
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