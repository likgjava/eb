
var draft_notice={};

$(document).ready(function(){
	draft_notice.oFCKeditor = new FCKeditor('contents','100%','240','Template','') ;
	$('#contents').text($("#contentsBack").text());
	draft_notice.oFCKeditor.ReplaceTextarea();

	
	$('#saveNotice').click(function(){//保存
		$('#useStatusId').val('01');
		$('#sendStatusId').val('01');
		$('#receiveStatusId').val('00');
		draft_notice.saveNotice();
		$('#epsDialogCloseReload').click();
	});
	$('#closeNotice').click(function(){
		$('#epsDialogCloseReload').click();
	});
});

draft_notice.saveNotice = function(){
	FCKeditor_BackValue(); 	//此方法很重要， 在AJAX提交之前把FCK里的东西往FORM里放.
	$.getJSON($('#initPath').val()+'/NoticeController.do?method=submitNoticeFile', formToJsonObject('noticeForm'), function(json){
		planTemplateTask.clickMethod($("#projectTaskId").val()+"");
		planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
	})
}

//draft_notice.submitNotice = function(){
//	FCKeditor_BackValue(); 	//此方法很重要， 在AJAX提交之前把FCK里的东西往FORM里放.
//	$.getJSON($('#initPath').val()+'/NoticeController.do?method=submitNoticeFile', formToJsonObject('noticeForm'), function(json){
//		planTemplateTask.clickMethod($("#projectTaskId").val()+"");
//		planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
//	})
//}

