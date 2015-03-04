
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
		//if(!checkTextDataForFLOAT($('#price').val())){alert('购买价格必须为数字!');return;}
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
				url:$('#initPath').val()+"/SingleSourceDocController.do?method=savePurchaseDoc",
				dataType:'json',
				success:function(json){
					if(json.result)alert(json.result);if(json.failure)return;
					planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
					alert("提交完成！");
					$('#purchaseDocReturn').click();
				},
				error:function(msg){
					alert(msg);
					$('#epsDialogClose').click();
				}
			});
		}
	}

});
