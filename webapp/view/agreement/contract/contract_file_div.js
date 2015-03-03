/*
 * 我的任务书下载文件页面
 * author: liangxj
 * mail: liangxj@gpcsoft.com
 */

//定义文件全局变量 处理方法名重复问题
var contractFileDiv={};


$(document).ready(function(){
	
	$('#contractFile').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=contractFile&isSelect=yes&attachRelaId='+$("#fileId").val());
	
	//关闭
	$("#contractDivclose").click(function(){
		$('.epsDialogClose').trigger('click');
	})

});
	
