
var purchaseDocForm={};

/**
* 验证浮点数
*/
function checkTextDataForFLOAT(strValue)
{
var regTextFloat = /^(-)?(\d)*(\.)?(\d)*$/;
return regTextFloat.test(strValue);
}

$(document).ready(function(){
	$('#refreshPur').click(function(){
		planTemplateTask.clickMethod($("#projectTaskId").val()+"");
		planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
	});
	$("#purchaseDocSubmit").click(function(){
		if(!$('#purchaseDocForm').valid()){alert('请正确填写表单!');return;}
		if(null == $('#attachFile').val() || "" == $('#attachFile').val()){
			$("#isUploadFile").val("false");
		}else{
			$("#isUploadFile").val("true");
		}
		$("#objId").val($("#purchaseDocobjId").val());
		$('#purchaseDocForm').ajaxSubmit({
			url:$('#initPath').val()+"/PurchaseDocController.do?method=submitPurDoc",
			dataType:'json',
			success:function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				if($("#projectTaskId") && $("#projectTaskId").val() != null && $("#projectTaskId").val() != "") {
		        	planTemplateTask.clickMethod($("#projectTaskId").val()+"");
			   		planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
			    } else {
			    	$("#myDesktop").click();
			    }
			},
			error:function(msg){
				alert(msg);
			}
		});
	});
	//指标展现
	$('#congrousFactor').empty().loadPage($('#initPath').val()+"/CongruousFactorController.do?method=toFactorTabShow&projectId="+$("input[name='project.objId']").val());
	//使用工具制作按钮单击事件
	$("#useToolCreatePurchaseDoc").click(function(){
		var url = "uepfm://un="+PlatForm.user.usName+"&pwd="+PlatForm.user.password+"&prjCode="+$("#projectProjCode").val()+"&sourcecode=110000/";
		window.open(url, "_self");
	})
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
});



//查看历史
$('#historyId').click(function(){
	$("#historyView").loadPage($('#initPath').val()+"/CompletedWorkTaskController.do?method=getCompletedWorkTaskByBizId&bizId="+$("input[name='purchaseDocobjId']").val()+'&taskType=03');
});
//指标详
$('#detail').click(function(){
	
	$("#historyView").loadPage($('#initPath').val()+"/CongruousFactorController.do?method=toDetail&projectId="+$("input[name='project.objId']").val());
});