
var proofOpinionForm={};
$(document).ready(function(){	
//	$('span[id=attachRelaId]').each(function(){
//		var obj = $(this);
//		alert(obj.text());
//				obj.loadPage(
//				$('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',
//				{readOnly:'yes',attachRelaId:$("#attachRelaId").text()}
//				);
//	})
	$('#purchaseDocForm').validate();
	//返回
	$('#proofOpinionReturn').click(function(){
		$('#epsDialogCloseNoReload').click();
	});
	
	//提交
	$('#proofOpinionSave').click(function(){
		var num = $("#num").val();
		if(num==0){
			alert("还未录入论证信息！");
			return;	
		}
	    if($("#proofOpinion").val()==''){
	    	alert("论证结果不能为空！");
	    	return;
	    }
		if(!$('#purchaseDocForm').valid()){
			alert("请正确填写表单!");
			return false;
		}
		$.getJSON($('#initPath').val()+'/PurchaseDocController.do?method=saveProofOpinion', formToJsonObject('purchaseDocForm'),function(json){
			if(json.failure){alert(json.result);return;}
			planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
			$('#epsDialogCloseNoReload').click();
			if($("#projectTaskId") && $("#projectTaskId").val() != "" &&$("#projectTaskId").val() !=null && $("#projectTaskId").val()!= undefined){
				planTemplateTask.clickMethod($("#projectTaskId").val()+"");
			  }
		});
	});
	
});
//查看历史
$('#historyId').click(function(){
	$("#historyView").loadPage($('#initPath').val()+"/CompletedWorkTaskController.do?method=getCompletedWorkTaskByBizId&bizId="+$("#purchaseDocId").val()+'&taskType=06');
});
