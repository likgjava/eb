
var ExtAttachmentAjaxFile={};

/**
 * 控制上传附件功能可见性
 */
ExtAttachmentAjaxFile.controlUploadAttachmentVisibility = function(){
	var attaCount = $("#extAtta_display_ul li").length;//附件总数量
	var allowUploadFileCount = $("#AAFAllowUploadFileCount").val();//允许上传的附件数量
	if($("#AAFReadOnly").val() != 'yes' && (allowUploadFileCount == 'null' || attaCount < parseInt(allowUploadFileCount)))
		$("#AAFForm_one").show();
	else
		$("#AAFForm_one").hide();
}

ExtAttachmentAjaxFile.del= function(id){
	
	if(window.confirm("确认要删除该附件吗？")){
		$.getJSON($('#initPath').val()+'/AttachmentController.do?method=delectAjax',{objId:id},function(json){
			if(json.failtrue){
				alert(json.result);
				return;
			}
			$('#'+id).remove();
			ExtAttachmentAjaxFile.controlUploadAttachmentVisibility();
		})
	}
	
}
ExtAttachmentAjaxFile.show = function(attachmentId){
	window.open($('#initPath').val()+"/AttachmentController.do?method=downLoadFile&objId="+attachmentId);
}
ExtAttachmentAjaxFile.upload = function(){
	$('#AAFForm_one').ajaxSubmit({
			url:$('#initPath').val()+'/ExtAttachmentController.do?method=upLoadAjax&isAJAX=true',
			dataType:'json',
			success:function(json){
				if(json.failure){
					alert(json.result);
					return;
				}
				var htm ='<li id=\''+json.attachmentId+'\'><a href="#" onclick="javascript:ExtAttachmentAjaxFile.show(\''+json.attachmentId+'\')" >'+ascii2native(json.viewName)+'</a> ' +
						'<span onclick="javascript:ExtAttachmentAjaxFile.del(\''+json.attachmentId+'\')" class="sysicon siDelete">删除</span></li>';
				$('#extAtta_display_ul').append(htm);
				
				ExtAttachmentAjaxFile.controlUploadAttachmentVisibility();
				return false;
			},
			error:function(msg){
				alert('上传文件出错!');
				var file = $("#AAFForm_one");  
			    file.after(file.clone().val(""));  
			    file.remove();  
			}
		});
}
$(document).ready(function(){
	ExtAttachmentAjaxFile.controlUploadAttachmentVisibility();
});