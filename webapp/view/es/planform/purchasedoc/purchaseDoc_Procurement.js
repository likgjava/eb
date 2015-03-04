
var purchaseDoc_config = {};

purchaseDoc_config.checkOpinion = function(){
	//操作权限
	var doAuth = true;
	if($("#auditStatus").val()=="N"){
		if(($("textarea[name='opinion']").val()==null || $("textarea[name='opinion']").val()=="")||$("textarea[name='opinion']").val()=='同意'){
			$("textarea[name='opinion']").val('');
			alert("请填写审核不通过原因!");
			doAuth = false;
		}
	}
	return doAuth;
}

purchaseDoc_config.auditpurchaseDoc = function() {
	if(!$('#purchaseDocConfigForm').valid()) {
		alert("请正确填写表单！");return;
	} else if(window.confirm("确认提交?")) {
		$('#passButton').attr("disabled","disabled");
		$('#noPassButton').attr("disabled","disabled");
		var jsonObj = formToJsonObject('purchaseDocConfigForm');
		var workFlowTaskId = $('[id=auditTaskId]').val();
		if(undefined == workFlowTaskId || null == workFlowTaskId || '' == workFlowTaskId){
			workFlowTaskId = $('[id=auditTask_Id]').val();
		}
		jsonObj.workFlowTaskId = workFlowTaskId;
		$.getJSON($('#initPath').val()+'/PurchaseDocController.do?method=savePruchaseDocAudit&objId='+$("#purchaseDocId").val()+'&auditStatus='+$("#auditStatus").val(),jsonObj, function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			if($("#fromType").val()=='fromList'){
				var workFlowTaskId = $('[id=auditTaskId]').val();
//				if($("#projectTaskId") && $("#projectTaskId").val() != null && $("#projectTaskId").val() != "") {
//		        	planTemplateTask.clickMethod($("#projectTaskId").val()+"");
//			   		planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
//			    }  else{
//					$("#myDesktop").click();
//				}
				$('#conBody').loadPage("ProjectController.do?method=toProjectInfo&objId="+$("#projectId").val());
			} else {
				$("#myDesktop").click();
			}
		});
	}
}

$(document).ready(function(){
	$('#purchaseDocConfigForm').validate();
	$('#congrousFactor').hide();
	//下载招标文件下载记录
	purchaseDoc_config.oTable = $('#downRecordList').dataTable({
		'singleSelect' : true,
		'checkbox' : false,
		'queryColumns' : 'downOrg.orgName,downUser.emp.name,downDate,downIP',
		'fnInitComplete' : function(oSettings) {
		},
		'fnDrawCallback' : function(oSettings) {
			purchaseDoc_config.oTable.oSettings = oSettings;
		},
		'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
			return nRow;
		},
		"params" : {"fileId":$('#purchaseDocId').val()},
		"sAjaxSource" : $('#initPath').val()+ "/ProcFileDownRecController.do?method=list"
	});
	
	//确认
	$('#passButton').click(function(){
		if($("textarea[name='opinion']").val()==''||$("textarea[name='opinion']").val()==null){
			$("textarea[name='opinion']").val('同意');
		}
		$("#auditStatus").val("Y");
		if(purchaseDoc_config.checkOpinion()){
		 	purchaseDoc_config.auditpurchaseDoc();
		}
	});
	//不确认
	$('#noPassButton').click(function(){
		$("#auditStatus").val("N");
		if(purchaseDoc_config.checkOpinion()){
			purchaseDoc_config.auditpurchaseDoc();	
		}
	});
	$("#downLoad").click(
			function(){
				window.location.href=''+$("#downAddr").text()+'';
			}
	)
	$("#downLoad2").click(
					function(){
						window.location.href=''+$("#downAddr2").text()+'';
					}
			)
	$('#congrousFactor').empty().loadPage($('#initPath').val()+"/CongruousFactorController.do?method=toFactorTabShow&projectId="+$("input[name='projectId']").val());
	$('#congrousFactor').css("display","block");
});



//查看历史
$('#historyId').click(function(){
	$("#historyView").loadPage($('#initPath').val()+"/CompletedWorkTaskController.do?method=getCompletedWorkTaskByBizId&bizId="+$("#purchaseDocId").val()+'&taskType=03');
});
//查看建筑工程采购文件
function viewJZGCPurchaseDoc(){
	var url = $('#initPath').val() + "/PurchaseDocController.do?method=viewJZGCPurchaseDoc&purchaseDocId="+$("#purchaseDocId").val();
	window.open(url, "_blank");
}