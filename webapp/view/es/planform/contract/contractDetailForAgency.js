
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
		$("#tabform").empty().loadPage($('#initPath').val()+'/view/es/planform/contract/contractList.jsp?projectId='+$("#projectId").val());
	});
	$("input[name='subProj']").each(
			function(i,n){
				if($(this).val()==$("#subProjectId").val()){
					$(this).attr("checked","checked");
				}
		});
	$("input[name='subProj']").attr("disabled","disabled");
});
