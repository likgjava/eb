
var attachmentAjaxFile={};
attachmentAjaxFile.del= function(id){
	
	if(window.confirm("确认要删除该附件吗？")){
		$.getJSON($('#initPath').val()+'/AttachmentController.do?method=delectAjax',{objId:id},function(json){
			if(json.failtrue){
				alert(json.result);
				return;
			}
			$('#'+id).remove();
		})
	}
	
}
attachmentAjaxFile.show = function(attachmentId){
	window.open($('#initPath').val()+"/AttachmentController.do?method=downLoadFile&objId="+attachmentId);
	//this.location = $('#initPath').val()+"/AttachmentController.do?method=downLoadFile&objId="+attachmentId;
}
attachmentAjaxFile.upload = function(){
	if($('#file_one').val()=='')return false;
	
	$('#form_one').ajaxSubmit({
			url:$('#initPath').val()+'/AttachmentController.do?method=upLoadAjax&isAJAX=true',
			dataType:'json',
			success:function(json){
				if(json.failure){
					alert('上传文件出错!');
					return;
				}
				$('input[name=attachRelaID]').val(json.relationId);
				$('#form_one').prev('input').val(json.relationId);//给领域类关联的属性赋值
				var htm ='<li id=\''+json.attachmentId+'\'><a href="#" onclick="javascript:attachmentAjaxFile.show(\''+json.attachmentId+'\');return false;" >'+json.viewName+'</a> ' +
						'<span onclick="javascript:attachmentAjaxFile.del(\''+json.attachmentId+'\')" class="sysicon siDelete">删除</span></li>';
				$('#atta_display_ul').append(htm);
				var file = $("#file_one");  
			    file.after(file.clone().val(""));  
			    file.remove();  
			},
			error:function(msg){
				alert('上传文件出错!');
				var file = $("#file_one");  
			    file.after(file.clone().val(""));  
			    file.remove();  
			}
		});
}
$(document).ready(function(){
	$('#go_upload').click(function(){
		attachmentAjaxFile.upload();
	})
});