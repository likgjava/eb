
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
	
	$('#dosBuyRecordReturn').click(function(){//关闭
		$("#epsDialogCloseReload").click();
	});
});
