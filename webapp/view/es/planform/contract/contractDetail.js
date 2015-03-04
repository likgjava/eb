
var contractForm={};
$(document).ready(function(){
	$('#attachRelaId').loadPage($('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',{
		defineSelf:'attachRelaId',//存放关联id的属性名
		attachRelaId:$("#attachRelaId").text(),
		readOnly:'yes'
	});
	//返回
	$('#return').click(function(){
		$("#tabValue").val("9");
		if($("#fromType").val()=='fromLeft'){
			$("#conBody").empty().loadPage($('#initPath').val()+'/ContractController.do?method=toContractSupplierListByRole&projectId='+$("#projectId").val());
		}else{
			$("#tabform").empty().loadPage($('#initPath').val()+'/ContractController.do?method=toContractList&projectId='+$("#projectId").val());
		}
	});
	$("input[name='subProj']").each(
			function(i,n){
				if($(this).val()==$("#subProjectId").val()){
					$(this).attr("checked","checked");
				}
		});
	$("input[name='subProj']").attr("disabled","disabled");
});
