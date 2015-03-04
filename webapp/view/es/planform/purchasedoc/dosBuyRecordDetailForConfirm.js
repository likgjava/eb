
var dosBuyRecordForm={};

$(document).ready(function(){
	//附件 缴纳证明
	$('#attachRelaId3').loadPage($('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',{
		defineSelf:'attachRelaId',//存放关联id的属性名
		attachRelaId:$("#attachRelaId2").val(),
		readOnly:'yes'
	});
	//附件 发票附件
	$('#invoiceFile3').loadPage($('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',{
		defineSelf:'attachRelaId',//存放关联id的属性名
		attachRelaId:$("#invoiceFile2").val(),
		readOnly:'yes'
	});
	
	$('#passButton').click(function(){//通过
		var jsonObject = {};
		jsonObject.dosBuyRecordId = $('#dosBuyRecordId').val();
		jsonObject.opinion = $('#opinion').val();
		jsonObject.useStatus = '01';
		jsonObject.docBuyStatus = '01';
		jsonObject.auditStatus = '01';
		$.getJSON($('#initPath').val()+'/DosBuyRecordController.do?method=saveConfirmDosBuyRecordForm',jsonObject,function(json){
		    if(json.result)alert(json.result);if(json.failure)return;
		    planTemplateTask.clickMethod($("#projectTaskId").val()+"");
		    $("#epsDialogCloseReload").click();return;
		});		
	});

	$('#noPassButton').click(function(){//不通过
		var jsonObject = {};
		jsonObject.dosBuyRecordId = $('#dosBuyRecordId').val();
		jsonObject.opinion = $('#opinion').val();
		jsonObject.useStatus = '01';
		jsonObject.docBuyStatus = '00';
		jsonObject.auditStatus = '02';
		$.getJSON($('#initPath').val()+'/DosBuyRecordController.do?method=saveConfirmDosBuyRecordForm',jsonObject,function(json){
		    if(json.result)alert(json.result);if(json.failure)return;
		    planTemplateTask.clickMethod($("#projectTaskId").val()+"");
		    $("#epsDialogCloseReload").click();return;
		});	
	});
	
	$('#dosBuyRecordReturn').click(function(){//关闭
		$("#epsDialogCloseReload").click();
	});
});
