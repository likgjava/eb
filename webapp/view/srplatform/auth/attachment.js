var FlashUpLoadConfig={"tBodyId":"attachmentInfo","hiddenId":$("#uploadAttachmentId").val()}
var Attachment={};
Attachment.delObject=function(id){
	if(window.confirm("确定删除?")){
		$.ajax({
				url:$('#initPath').val()+"/AttachmentController.do?method=remove",
				type:"POST",
				data:{objId:id},
				success:function(msg){
					$("#"+id).remove();
				},error:function(msg){
					alert('删除附件失败');
				}
		});
	}
}
$(document).ready(function(){
	if($("#uploadAttachmentId").val()!="null"&&$("#uploadAttachmentId").val()!=""){
		 if($("#"+$("#uploadAttachmentId").val()).val()!=""){	
			$.getJSON($('#initPath').val()+"/AttachmentController.do?method=getObject",{relationId:$("#"+$("#uploadAttachmentId").val()).val()},function(json){
	    		$.each(json,function(i,n){
	    			$("#attachmentInfo").append("<tr id='"+n.objId+"'></tr>")
					$("#"+n.objId).append("<td><a href='javascript:void(0);'>"+n.viewName+"</a></td>")
					$("#"+n.objId).append("<td><a href='javascript:void(0);' onclick='Attachment.delObject(\""+n.objId+"\");return false;'>删除</a></td>")
	    		})
	    	})
		 }
	}
	
	$("#btnUpload").click(function(e){
		$.nyroModalManual({
	   	      url: $("#initPath").val()+'/AttachmentController.do?method=toUpload'
	   	    });
	   	return false;
	})
})
