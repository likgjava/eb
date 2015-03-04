
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
	$('#purchaseDocForm').validate();

	//提交
	$('#purchaseDocSave').click(function(){
		if(!$('#purchaseDocForm').valid()){alert('请正确填写表单!');return;}
		purchaseDocForm.savePurDoc();
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
	
	//刷新
	$('#refreshPur').click(function(){
		planTemplateTask.clickMethod($("#projectTaskId").val()+"");
		planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
	});
	
	
	purchaseDocForm.savePurDoc = function(){
		
		if(confirm('你确定保存吗？')){
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
				url:$('#initPath').val()+"/InqpDocController.do?method=savePurchaseDoc&isAJAX=true",
				dataType:'json',
				success:function(json){
					if(json.result)alert(json.result);if(json.failure)return;
					if($("#projectTaskId") && $("#projectTaskId").val() != "" &&$("#projectTaskId").val() !=null && $("#projectTaskId").val()!= undefined){
						planTemplateTask.clickMethod($("#projectTaskId").val()+"");
						planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
					  } 
				},
				error:function(msg){
					alert(msg);
					$('#epsDialogClose').click();
				}
			});
		}
	}
	//政府采购使用工具制作按钮单击事件
	$("#useToolCreatePurchaseDoc").click(function(){
		var url = "uepfm://un="+PlatForm.user.usName+"&pwd="+PlatForm.user.password+"&prjCode="+$("#projectProjCode").val()+"&sourcecode=110000/";
		window.open(url, "_self");
	})
	
});
