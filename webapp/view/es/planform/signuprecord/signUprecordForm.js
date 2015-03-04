
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
		if($("#isDividePack").val()=='true'){
			var a="";
			$("input[id='sub']").each(function(i,n){
				if($(n).attr("checked")==true){
					a = a+$(n).val()+",";
				}
			})
			$("#subPrjIds").val(a.substring(0,a.length-1));
			if($("#subPrjIds").val()==""||$("#subPrjIds").val()==null){
				alert("请选择包组！");
				return false;
			}
		}
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
		if(!$('#signUprecordForm').valid()){alert('请正确填写表单!');return;}
		
		if(confirm('你确定提交吗？')){
		var attachRelaId=$("input[name=attachRelaId]").val();
		$('#signUprecordForm').ajaxSubmit({
			url:$('#initPath').val()+"/SignUprecordController.do?method=saveSignUprecord&isAJAX=true&attachRela="+attachRelaId+"&num="+i,
			dataType:'json',
			success:function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$('#epsDialogCloseNoReload').click();
				if($("#fromType").val()=='fromTab'){//从供应商报名Tab页跳转
					var currentId = $("input[name=project.objId]").val();
					$("#taskPlanListInfo").loadPage($('#initPath').val()+"/SignUprecordController.do?method=toViewSignupPage&prjId="+currentId);
				}else{   //从公告页面跳转
					$("#signUprecordReturn").click();
					$("#myDesktop").click();
				}
			},
			error:function(msg){
				alert(msg);
			}
		});

		}
	});
});
