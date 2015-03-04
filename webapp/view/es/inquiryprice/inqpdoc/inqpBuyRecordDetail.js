
var dosBuyRecordForm={};

$(document).ready(function(){
	//附件
	$('#attachRelaId').loadPage($('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',{
		defineSelf:'attachRelaId',//存放关联id的属性名
		attachRelaId:$("#attachRelaId").text(),
		readOnly:'yes'
	});
	if($('#form_one'))$('#form_one').remove();//移除附件form否则validator会出错
	$('#dosBuyRecordForm').validate();
	//返回
	$('#dosBuyRecordReturn').click(function(){
		if($("#projectTaskId") && $("#projectTaskId").val() != "" &&$("#projectTaskId").val() !=null && $("#projectTaskId").val()!= undefined){
			planTemplateTask.clickMethod($("#projectTaskId").val()+"");
			planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
		  } else {
			$("#myDesktop").click();
		}
	});
});
