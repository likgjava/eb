
var signUprecordForm={};

$(document).ready(function(){
	$("#applyDate").epsDatepicker({timeShow:true});
	$('#signUprecordForm').validate();
	
	//附件
	$('#attachRelaId').loadPage($('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',{
		defineSelf:'attachRelaId',//存放关联id的属性名
		attachRelaId:$("#attachRelaId").text()
	});
	var i = $('input[id=i]').length;  //报名响应个数
	//关闭
	$('#signUprecordReturn').click(function(){
		$('#epsDialogCloseNoReload').click();
	});
	//提交
	$('#signUprecordSave').click(function(){
		// 更新文件空状态
		$("#signUpRespone").find('input[type=file]').each(function(i,n){
			
			var fileName = $(n).attr("name").toString();
			var isUploadFileObj = $("input[name=isUploadFile"+fileName.substring(fileName.length-1,fileName.length)+"]");
			if(n.value == ""){
				$(isUploadFileObj).val("false");
			}else{
				$(isUploadFileObj).val("true");
			}
		})	
		if($('#form_one'))$('#form_one').remove();//移除附件form否则validator会出错
		if(!$('#signUprecordForm').valid()){
			alert('请正确填写表单!');
			//附件
			$('#attachRelaId').loadPage($('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',{
				defineSelf:'attachRelaId',//存放关联id的属性名
				attachRelaId:$("#attachRelaId").text()
			});
			return false;
		}
		if(confirm('确定要保存吗？')){
			$('#signUprecordSave').attr("disabled","disabled");
			$('#signUprecordForm').ajaxSubmit({
				url:$('#initPath').val()+"/SignUprecordController.do?method=saveSignUprecordForAgency&num="+i,
				dataType:'json',
				success:function(json){
						if(json.failure)alert("该投标单位已报名！");
						else{
							$('#epsDialogCloseReload').click();
							if($("#projectTaskId") && $("#projectTaskId").val() != ""){
					        	planTemplateTask.clickMethod($("#projectTaskId").val()+"");
						   		planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
				            }
						}
				},
				error:function(msg){
					alert(msg);
				}
			});
		}else{
			//附件
			$('#attachRelaId').loadPage($('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',{
				defineSelf:'attachRelaId',//存放关联id的属性名
				attachRelaId:$("#attachRelaId").text()
			});
		}
	});
	$("#supplierName").autocomplete(
			
			$('#initPath').val() + '/OrgInfoController.do?method=getObjectQuery&queryColumns=objId,orgName,user.emp.name,user.emp.idCard,user.emp.telOffice,user.emp.address,user.emp.zipCode', 
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
				/*
				key=user.emp.name  value=王先生,
				key=objId  value=402887da2f8a9c60012f8b4b416800cd,
				key=user.emp.address  value=北京海淀区,
				key=user.emp.telOffice  value=123456789,
				key=user.emp.idCard  value=,
				key=user.emp.zipCode  value=100010,
				key=orgName  value=北京长城宽带有限公司
				*/
				$("#linker").val(data['user.emp.name']);
				$("#address").val(data['user.emp.address']);
				$("#linkerTel").val(data['user.emp.telOffice']);
				$("#idCard").val(data['user.emp.idCard']);
				$("#zipCode").val(data['user.emp.zipCode']);
				$("#supplierName").val(data['orgName']);//回填id
				$("input[id=supplier.objId]").val(data['objId']);//回填id
			
			}
		});
});
