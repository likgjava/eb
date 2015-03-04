
var oppugnReplyForm={};

$(document).ready(function(){
	$('#oppugnReplyForm').validate();
	
	//附件
	//$('#attachRelaId').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=attachRelaId&attachRelaId='+$("#attachRelaId").text());
	$('#attachRelaId').loadPage($('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',//组件载体
							{
								defineSelf:"attachRelaId",//多附件关联id属性名
								attachRelaId:$("#attachRelaId").text(),
								readOnly:'no'//只读模式将屏蔽上传和删除功能
							}
	);
	//保存
	$('#oppugnReplySave').click(function(){
		if($('#form_one'))$('#form_one').remove();//移除附件form否则validator会出错
		var projectId = $("#projectId").val();
		if(!$('#oppugnReplyForm').valid()){alert('请正确填写表单!');return;}
		$.getJSON($('#initPath').val()+'/OppugnReplyController.do?method=saveOppugnReply', formToJsonObject('oppugnReplyForm'), function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			$('#epsDialogClose').click();
		});
	});
	
	//返回
	$('#oppugnReplyReturn').click(function(){
		$('#epsDialogClose').click();
	});
});
