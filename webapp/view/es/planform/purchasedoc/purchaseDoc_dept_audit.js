
var purchaseDocDeptAudit={};

purchaseDocDeptAudit.checkOpinion = function(){
	//操作权限
	var doAuth = true;
	if($("#auditStatus").val()=="N"){
		if(($("textarea[name='opinion']").val()==null || $("textarea[name='opinion']").val()=="")){
			alert("请填写审核不通过原因!");
			doAuth = false;
		}
	}
	return doAuth;
}
purchaseDocDeptAudit.auditBulletin = function(){
	//alert(obj2str(formToJsonObject('purchaseDocDeptAuditForm')));
	$.getJSON($('#initPath').val()+'/PurchaseDocController.do?method=purchaseDocDeptAudit', formToJsonObject('purchaseDocDeptAuditForm'), function(json){
		if(json.result)alert(json.result);if(json.failure)return;
		//$('#conBody').loadPage($('#initPath').val()+'/view/srplatform/wflow/myTaskListView.jsp');
	});
}

$(document).ready(function(){
	if($("#nodoc").val()!='NopurDoc')
	{
		$("#purchaseDoc").empty().loadPage($('#initPath').val()+'/PurchaseDocController.do?method=toPurchaseDoc&projectId='+$("#projectId").val());	
	}
	else
	{
		$("#purchaseDoc").html("<b align='center'>还未上传采购文件</b>");
	}
	//点击确定
	
	
	//确认
	$('#passButton').click(function(){
		if($("#nodoc").val()!='NopurDoc')
		{
			$("#auditStatus").val("Y");
			if(purchaseDocDeptAudit.checkOpinion()){
				purchaseDocDeptAudit.auditBulletin();
			}
		}
		else
		{
			alert('请先提交采购文件！');
		}
	});
	//不确认
	$('#noPassButton').click(function(){
		if($("#nodoc").val()!='NopurDoc')
		{
			$("#auditStatus").val("N");
			if(purchaseDocDeptAudit.checkOpinion()){
				purchaseDocDeptAudit.auditBulletin();
			}
		}
		else
		{
			alert('请先提交采购文件！');
		}
			
	});
		
});
