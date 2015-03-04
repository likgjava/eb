
var dosBuyRecordForm={};

$(document).ready(function(){
	//附件 缴纳证明
	$('#attachRelaId3').loadPage($('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',{
		defineSelf:'attachRelaId',//存放关联id的属性名
		attachRelaId:$("#attachRelaId2").val(),
		readOnly:'yes'
	});
	
	$('#bailRecordReturn').click(function(){//关闭
		$("#epsDialogCloseReload").click();
	});
	
	$('#passButton').click(function(){//通过
		var jsonObject = {};
		jsonObject.bailRecordId = $('#bailRecordId').val();
		jsonObject.opinion = $('#opinion').val();
		jsonObject.useStatus = '01';
		jsonObject.auditStatus = '01';
		jsonObject.bailStatus = '01';//已收
		$.getJSON($('#initPath').val()+'/BailRecordController.do?method=saveConfirmBailRecord',jsonObject,function(json){
		    if(json.result)alert(json.result);if(json.failure)return;
		    planTemplateTask.clickMethod($("#projectTaskId").val()+"");
		    planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
		    $("#epsDialogCloseReload").click();return;
		});		
	});

	$('#noPassButton').click(function(){//不通过
		var jsonObject = {};
		jsonObject.bailRecordId = $('#bailRecordId').val();
		jsonObject.opinion = $('#opinion').val();
		jsonObject.useStatus = '01';
		jsonObject.auditStatus = '00';
		jsonObject.bailStatus = '00';//退回
		$.getJSON($('#initPath').val()+'/BailRecordController.do?method=saveConfirmBailRecord',jsonObject,function(json){
		    if(json.result)alert(json.result);if(json.failure)return;
		    planTemplateTask.clickMethod($("#projectTaskId").val()+"");
		    planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
		    $("#epsDialogCloseReload").click();return;
		});	
	});

});
