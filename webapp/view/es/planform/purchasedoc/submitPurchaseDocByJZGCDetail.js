
var purchaseDocForm={};

/**
* 验证浮点数
*/
function checkTextDataForFLOAT(strValue)
{
var regTextFloat = /^(-)?(\d)*(\.)?(\d)*$/;
return regTextFloat.test(strValue);
}
function downFile(attRaId){
	window.location.href=$('#initPath').val()+"/PurchaseDocController.do?method=downLoadPartsFile&attRaId="+attRaId;
}
//启动制作 商务标工具
function makeBusiness(packCode){
	var url = "PmSwbZb://un="+PlatForm.user.usName+"&packCode="+packCode+"&pwd="+PlatForm.user.password+"&prjCode="+$("#projectProjCode").val()+"&sourcecode=110000/";
	window.open(url, "_self");
}
//启动制作技术标工具
function makeTechnical(packCode){
	var url = "PmJsbZb://un="+PlatForm.user.usName+"&packCode="+packCode+"&pwd="+PlatForm.user.password+"&prjCode="+$("#projectProjCode").val()+"&sourcecode=110000/";
	window.open(url, "_self");
}
//查看建筑工程采购文件
function viewJZGCPurchaseDoc(){
	var url = $('#initPath').val() + "/PurchaseDocController.do?method=viewJZGCPurchaseDoc&purchaseDocId="+$("#purchaseDocId").val();
	window.open(url, "_blank");
}
$(document).ready(function(){
	$("#purchaseDocSubmit").click(function(){
		if(!$('#purchaseDocForm').valid()){alert('请正确填写表单!');return;}
		if(confirm('确定要提交吗？')){
			$.getJSON($('#initPath').val()+'/PurchaseDocController.do?method=confirmFinish&prjId='+$('#projectId').val(),formToJsonObject('purchaseDocForm'), function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				planTemplateTask.clickMethod($("#projectTaskId").val()+"");
		   		planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
			});
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
	if($("#purchaseDocId").val()!=null&&$("#purchaseDocId").val()!=""){
		$('#congrousFactor').empty().loadPage($('#initPath').val()+"/CongruousFactorController.do?method=toFactorTabShow&projectId="+$("#projectId").val());
	}	
});



//查看历史
$('#historyId').click(function(){
	$("#historyView").loadPage($('#initPath').val()+"/CompletedWorkTaskController.do?method=getCompletedWorkTaskByBizId&bizId="+$("#objId").val()+'&taskType=03');
});

