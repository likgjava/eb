
var signUprecordForm={};

$(document).ready(function(){
	
	signUprecordForm.removeFile = function(num){
		$("#removeFileEle"+num).prev('a').remove();
		$("#removeFileEle"+num).remove();
		$("[name=attachRelaId"+num+"]").val("");
	}
	
	
	
	$("#applyDate").epsDatepicker({timeShow:true});
	$('#signUprecordForm').validate();
	
	//附件
	$('#attachRelaId').loadPage($('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',{
		defineSelf:'attachRelaId',//存放关联id的属性名
		attachRelaId:$("#attachRelaId").text()
	});
		
	
	//关闭
	$('#signUprecordReturn').click(function(){
		$('#epsDialogCloseNoReload').click();
	});
	//提交
	$('#signUprecordSave').click(function(){
		
		var i = $('input[id=i]').length;  //报名响应个数

		// 更新文件空状态
		$("#signUpRespone").find('input[type=file]').each(function(i,n){
			
			var fileName = $(n).attr("name").toString();
			var isUploadFileObj = $("input[name=isUploadFile"+fileName.substring(fileName.length-1,fileName.length)+"]");
			var attachRelaIdObj = $("input[name=attachRelaId"+fileName.substring(fileName.length-1,fileName.length)+"]");
			if($(attachRelaIdObj).val()!=""){
				$(n).removeClass("required"); 
			}
			
			if(n.value == ""){
				$(isUploadFileObj).val("false");
			}else{
				$(isUploadFileObj).val("true");
			}
		})	

		if($('#form_one'))$('#form_one').remove();
		if(!$('#signUprecordForm').valid()){alert('请正确填写表单!');return;}
		if(confirm('确定要保存吗？')){
			$('#signUprecordForm').ajaxSubmit({
				url:$('#initPath').val()+"/SignUprecordController.do?method=updateSignUprecordForAgency&num="+i,
				dataType:'json',
				success:function(json){
					if(json.result)alert(json.result);if(json.failure)return;
					$('#epsDialogCloseNoReload').click();
				},
				error:function(msg){
					alert(msg);
				}
			});
			if($("#projectTaskId") && $("#projectTaskId").val() != ""){
	        	planTemplateTask.clickMethod($("#projectTaskId").val()+"");
		   		planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
            }
		}
	});
	$("#supplierName").autocomplete(
			$('#initPath').val() + '/OrgInfoController.do?method=getObjectQuery&queryColumns=objId,orgName', 
			{
				matchColumn:'orgName',//作为查询显示, 被选中之后匹配的列
				extraParams:{"supplierId":null,"supplierId_op":"!="},
				mustMatch: true,
				formatItem: function(data, i, total) {
					return data.orgName;
				},
				formatMatch: function(data, i, total) {
					return data.orgName;
				},
				formatResult: function(data) {
					return data.orgName;
				}
			}
		).result(function(event,data,formatted){
			if(data){
				$("#supplierName").val(data.orgName);//回填id
				$("input[id=supplier.objId]").val(data.objId);//回填id
			}
		});
});
