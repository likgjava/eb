
var dosBuyRecordForm={};

$(document).ready(function(){
	//附件
	$('#attachRelaId').loadPage($('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',{
		defineSelf:'attachRelaId',//存放关联id的属性名
		attachRelaId:$("#attachRelaId").text()
	});
	
	//返回
	$('#dosBuyRecordReturn').click(function(){
		if($("#projectTaskId") && $("#projectTaskId").val() != "" &&$("#projectTaskId").val() !=null && $("#projectTaskId").val()!= undefined){
			planTemplateTask.clickMethod($("#projectTaskId").val()+"");
			planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
		  } else {
			$("#myDesktop").click();
		}
	});
	//提交
	$('#dosBuyRecordSave').click(function(){
		if($('#form_one'))$('#form_one').remove();//移除附件form否则validator会出错
		if(!$('#dosBuyRecordForm').valid()){alert('请正确填写表单!');return;}
		$.getJSON($('#initPath').val()+'/InqpDocBuyRecordController.do?method=saveDosBuyRecord', formToJsonObject('dosBuyRecordForm'), function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			$('#dosBuyRecordReturn').click();
		});
	});
});
