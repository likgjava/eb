
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
$(document).ready(function(){
	$("#purchaseDocSubmit").click(function(){
		if(!$('#purchaseDocForm').valid()){alert('请正确填写表单!');return;}
		if(confirm('确定要提交吗？')){
			$('#purchaseDocForm').ajaxSubmit({
				url:$('#initPath').val()+"/PurchaseDocController.do?method=confirmFinish&prjId="+$('#projectId').val(),
				dataType:'json',
				success:function(json){
					if(json.result)alert(json.result);if(json.failure)return;
			        planTemplateTask.clickMethod($("#projectTaskId").val()+"");
				   	planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
				},
				error:function(msg){
					alert(msg);
				}
			});
		}
	});
});



//查看历史
$('#historyId').click(function(){
	$("#historyView").loadPage($('#initPath').val()+"/CompletedWorkTaskController.do?method=getCompletedWorkTaskByBizId&bizId="+$("#objId").val()+'&taskType=03');
});

