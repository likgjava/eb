
var purchaseDocForm={};

/**
* 验证浮点数
*/
function checkTextDataForFLOAT(strValue)
{
var regTextFloat = /^(-)?(\d)*(\.)?(\d)*$/;
return regTextFloat.test(strValue);
}
function makeBusiness(packCode){
	var url = "PmSwbZb://un="+PlatForm.user.usName+"&packCode="+packCode+"&pwd="+PlatForm.user.password+"&prjCode="+$("#projectProjCode").val()+"&sourcecode=110000/";
	window.open(url, "_self");
}
function makeTechnical(packCode){
	var url = "PmJsbZb://un="+PlatForm.user.usName+"&packCode="+packCode+"&pwd="+PlatForm.user.password+"&prjCode="+$("#projectProjCode").val()+"&sourcecode=110000/";
	window.open(url, "_self");
}
$(document).ready(function(){
	$('#biz_Id').val($("#projectTaskId").val());
	$('#purchaseDocForm').validate();
    if($('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/PurchaseDocController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
    		jsonObjectToForm('purchaseDocForm', json.purchaseDoc);
    	});
    }
    /* 
    //加载附件
	if(""!=$("#file").val()&&null!=$("#file").val()){
		$('#purdocFile').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=purchaseDocForm&attachRelaId='+$("#file").val());
	}
	else{
		$('#purdocFile').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=purchaseDocForm&isSingle=yes');
	}*/
	//提交
	$('#purchaseDocSave').click(function(){
		if(!$('#purchaseDocForm').valid()){alert('请正确填写表单!');return;}
		purchaseDocForm.savePurDoc();
	});
	//刷新
	$('#refreshPur').click(function(){
		planTemplateTask.clickMethod($("#projectTaskId").val()+"");
		planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
	});
	
	
	//返回
	$('#purchaseDocReturn').click(function(){
		if($("#fromDiv").val() == 'yes'){//若是来源于弹出层，则关闭弹出层
			$('#epsDialogClose').click();
			$("#myDesktop").click();
		}else{//若不是来源于弹出层，则跳到指定页面
			planTemplateTask.refresh($("#projectTaskId").val()+"");
		}
	});
	
	//政府采购使用工具制作按钮单击事件
	$("#useToolCreatePurchaseDoc").click(function(){
		var url = "uepfm://un="+PlatForm.user.usName+"&pwd="+PlatForm.user.password+"&prjCode="+$("#projectProjCode").val()+"&sourcecode=110000/";
		window.open(url, "_self");
	})
	//非政府采购使用工具制作按钮单击事件
	$("#useToolCreatePurchaseDoc2").click(function(){
		var url = "uepfm://un="+PlatForm.user.usName+"&pwd="+PlatForm.user.password+"&prjCode="+$("#projectProjCode").val()+"&sourcecode=110000/";
		window.open(url, "_self");
	})
	if($("#fromDiv").val() != 'yes'){//如果不是来源于弹出层则隐藏
		$('#purchaseDocReturn').hide();
	}
	//确认完成所进行的相关操作
	$("#confirmFinish").click(function(){
		$.getJSON($('#initPath').val()+'/PurchaseDocController.do?method=confirmFinish',{objId:$('#objId').val()},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			if($("#projectTaskId") && $("#projectTaskId").val() != null && $("#projectTaskId").val() != "") {
	        	planTemplateTask.clickMethod($("#projectTaskId").val()+"");
		   		planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
		    } else {
		    	$("#myDesktop").click();
		    }
    	});
	})
	purchaseDocForm.savePurDoc = function(){
		if(confirm('确定要提交吗？')){
			var workFlowTaskId = $('[id=auditTaskId]').val();
			if(undefined == workFlowTaskId || null == workFlowTaskId || '' == workFlowTaskId){
				workFlowTaskId = $('[id=auditTask_Id]').val();
			}
			$('#workFlowTaskId').val(workFlowTaskId);
			var projectTaskId = $("[id=projectTaskId]").val();
			$("[name=projectTaskId]").val(projectTaskId);
			
			/*验证文件格式*/
			var reg = /^.*(.doc|.docx|.DOC|.DOCX)$/;
			if($('#attachFile').val()!='' && !reg.test($('#attachFile').val())){
			    alert("请选择正确的采购文件!支持doc,docx格式");
			    return false;
			}
			if(null == $('#attachFile').val() || "" == $('#attachFile').val()){
				$("#isUploadFile").val("false");
			}else{
				$("#isUploadFile").val("true");
			}
			$("#purchaseDocSave").attr("disabled","disabled");
			$('#purchaseDocForm').ajaxSubmit({
				url:$('#initPath').val()+"/PurchaseDocController.do?method=savePurchaseDoc&isAJAX=true",
				dataType:'json',
				success:function(json){
				alert('错误信息....')
					if(json.failure){alert(json.result);$("#purchaseDocSave").attr("disabled",false);return;}
					if($("#projectTaskId") && $("#projectTaskId").val() != null && $("#projectTaskId").val() != "") {
			        	planTemplateTask.clickMethod($("#projectTaskId").val()+"");
				   		planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
				    } else {
				    	$("#myDesktop").click();
				    }
				},
				error:function(msg){
					alert('错误信息')
					alert(msg);
				}
			});
		}
	}
	$('#congrousFactor').empty().loadPage($('#initPath').val()+"/CongruousFactorController.do?method=toFactorTabShow&projectId="+$("#projectId").val());
});
