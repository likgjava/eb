
var updateInqpDocForm={};

/**
* 验证浮点数
*/
function checkTextDataForFLOAT(strValue)
{
var regTextFloat = /^(-)?(\d)*(\.)?(\d)*$/;
return regTextFloat.test(strValue);
}

//修改询价文件
updateInqpDocForm.savePurDoc = function(){
	if(confirm('你确定保存吗？')){
		/*验证文件格式*/
		var reg = /^.*(.doc|.docx|.DOC|.DOCX)$/;
		if($('#attachFile').val()!='' && !reg.test($('#attachFile').val())){
		    alert("请选择正确的采购文件!支持doc,docx格式");
		    return false;
		}
		var attachRelaId = $("#attachRelaId").val();
		if((null == attachRelaId || "" == attachRelaId || "null" == attachRelaId ) && (null == $('#attachFile').val() || "" == $('#attachFile').val())){
			alert("请上传询价文件!");
			return false;
		}
		if(null == $('#attachFile').val() || "" == $('#attachFile').val()){
			$("#isUploadFile").val("false");
		}else{
			$("#isUploadFile").val("true");
		}
		$('#purchaseDocForm').ajaxSubmit({
			url:$('#initPath').val()+"/InqpDocController.do?method=updatePurchaseDoc",
			dataType:'json',
			success:function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				if($("#projectTaskId") && $("#projectTaskId").val() != "" &&$("#projectTaskId").val() !=null && $("#projectTaskId").val()!= undefined){
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

$(document).ready(function(){
	 var AttachmentId = $("#attachmentId").val();
	  if(AttachmentId!=null&&AttachmentId!=""){
		  updateInqpDocForm.getFileName(AttachmentId);
	  }
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

	//政府采购使用工具制作按钮单击事件
	$("#useToolCreatePurchaseDoc").click(function(){
		var url = "uepfm://un="+PlatForm.user.usName+"&pwd="+PlatForm.user.password+"&prjCode="+$("#projectProjCode").val()+"&sourcecode=110000/";
		window.open(url, "_self");
	})
	
	//提交
	$('#purchase_doc_save').click(function(){
		if(!$('#purchaseDocForm').valid()){
			alert('请正确填写表单!');
			return false;
		}
		updateInqpDocForm.savePurDoc();
	});
	$('#congrousFactor').empty().loadPage($('#initPath').val()+"/CongruousFactorController.do?method=toFactorTabShow&projectId="+$("#projectId").val());
});



//查看历史
$('#historyId').click(function(){
	$("#historyView").loadPage($('#initPath').val()+"/CompletedWorkTaskController.do?method=getCompletedWorkTaskByBizId&bizId="+$("#objId").val()+'&taskType=03');
});

