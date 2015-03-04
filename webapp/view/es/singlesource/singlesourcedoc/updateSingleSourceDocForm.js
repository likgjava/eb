
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
	purchaseDocForm.savePurDoc = function(){
		
		if(confirm('你确定保存吗？')){
		
			/*验证文件格式*/
			var reg = /^.*(.doc|.docx|.DOC|.DOCX)$/;
			if($('#attachFile').val()!='' && !reg.test($('#attachFile').val())){
			    alert("请选择正确的采购文件!支持doc,docx格式");
			    return false;
			}
			var attachRelaId = $("#attachRelaId").val();
			if((null == attachRelaId || "" == attachRelaId || "null" == attachRelaId ) && (null == $('#attachFile').val() || "" == $('#attachFile').val())){
				alert("请上传采购文件!");
				return false;
			}
			if(null == $('#attachFile').val() || "" == $('#attachFile').val()){
				$("#isUploadFile").val("false");
			}else{
				$("#isUploadFile").val("true");
			}
			$('#purchaseDocForm').ajaxSubmit({
				url:$('#initPath').val()+"/PurchaseDocController.do?method=savePurchaseDoc",
				dataType:'json',
				success:function(json){
					if(json.result)alert(json.result);if(json.failure)return;
					planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
					alert("提交完成！");
					if($("#fromDiv").val() == 'yes'){//若是来源于弹出层，则关闭弹出层
						$('#epsDialogClose').click();
					}else{//若不是来源于弹出层，则跳到指定页面
		                if($("#fromDesk").val()=='YES') {//若是来源于我的桌面，则返回到我的桌面
		                	$("#myDesktop").click();
		                }else {
		                	planTemplateTask.refresh($("#projectTaskId").val()+"");
		                }
					}
				},
				error:function(msg){
					alert(msg);
					$('#epsDialogClose').click();
				}
			});
		}
	}
	
	//提交
	$('#purchaseDocSave').click(function(){
		if(!$('#purchaseDocForm').valid()){alert('请正确填写表单!');return;}
		//if(!checkTextDataForFLOAT($('#price').val())){alert('购买价格必须为数字!');return;}
		purchaseDocForm.savePurDoc();
		
	});
});



//查看历史
$('#historyId').click(function(){
	$("#historyView").loadPage($('#initPath').val()+"/CompletedWorkTaskController.do?method=getCompletedWorkTaskByBizId&bizId="+$("#objId").val()+'&taskType=03');
});

