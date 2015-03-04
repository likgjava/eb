
var purchaseDocForm={};

/**
* 验证浮点数
*/
function checkTextDataForFLOAT(strValue)
{
var regTextFloat = /^(-)?(\d)*(\.)?(\d)*$/;
return regTextFloat.test(strValue);
}
//启动制作 商务标工具
function makeBusiness(packCode){
	var url = "GlodonGZB://un="+PlatForm.user.usName+"&packCode="+packCode+"&pwd="+PlatForm.user.password+"&prjCode="+$("#projectProjCode").val()+"&sourcecode=110000/";
	window.open(url, "_self");
}
//启动制作技术标工具
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
	
	
	if($("#fromDiv").val() != 'yes'){//如果不是来源于弹出层则隐藏
		$('#purchaseDocReturn').hide();
	}
	
	
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
			if(null == $('#attachFile').val() || "" == $('#attachFile').val()){
				$("#isUploadFile").val("false");
			}else{
				$("#isUploadFile").val("true");
			}
			$("#purchaseDocSave").attr("disabled","disabled");
			$('#purchaseDocForm').ajaxSubmit({
				url:$('#initPath').val()+"/PurchaseDocController.do?method=confirmFinish&prjId="+$('#projectId').val(),
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
		}
	}
});