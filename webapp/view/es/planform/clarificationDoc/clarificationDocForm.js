
var clarificationDocForm={};

/**
* 验证浮点数
*/
function checkTextDataForFLOAT(strValue)
{
var regTextFloat = /^(-)?(\d)*(\.)?(\d)*$/;
return regTextFloat.test(strValue);
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
	$('#purchaseDocSubmit').click(function(){
		if(!$('#purchaseDocForm').valid()){alert('请正确填写表单!');return;}
		$("#useStatus").val("01");
		$("#auditStatus").val("00");
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
			$('#purchaseDocForm').ajaxSubmit({
				url:$('#initPath').val()+"/PurchaseDocController.do?method=submitClarificationDoc",
				dataType:'json',
				success:function(json){
					if(json.result)alert(json.result);if(json.failure)return;
					if($("#projectTaskId") && $("#projectTaskId").val() != ""){
						$('#epsDialogCloseNoReload').click();
			        	planTemplateTask.clickMethod($("#projectTaskId").val()+"");
				   		planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
			        }else{
						$("#myDesktop").click(); 
					}
				},
				error:function(msg){
					alert(msg);
				}
			});
		}
	});
	//保存
	$('#purchaseDocSave').click(function(){
		if(!$('#purchaseDocForm').valid()){alert('请正确填写表单!');return;}
		$("#useStatus").val("00");
		$("#auditStatus").val("00");
		if(confirm('确定要保存吗？')){
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
			$('#purchaseDocForm').ajaxSubmit({
				url:$('#initPath').val()+"/PurchaseDocController.do?method=saveClarificationDoc",
				dataType:'json',
				success:function(json){
					if(json.result)alert(json.result);if(json.failure)return;
					if($("#projectTaskId") && $("#projectTaskId").val() != ""){
						$('#epsDialogCloseNoReload').click();
			        	planTemplateTask.clickMethod($("#projectTaskId").val()+"");
				   		planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
			        }else{
						$("#myDesktop").click(); 
					}
				},
				error:function(msg){
					alert(msg);
				}
			});
		}
	});
	
	//返回
	$('#purchaseDocReturn').click(function(){
		if($("#fromDiv").val() == 'yes'){//若是来源于弹出层，则关闭弹出层
			$('#epsDialogClose').click();
			$('#epsDialogCloseNoReload').click();
		}else{
			$("#myDesktop").click();
		}
	});
	
	if($("#fromDiv").val() != 'yes'){//如果不是来源于弹出层则隐藏
		$('#purchaseDocReturn').hide();
	}
	
});
