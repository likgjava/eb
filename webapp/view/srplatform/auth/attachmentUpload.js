var upload 
$(document).ready(function(){
	var FlashUpLoadConfig =window.parent.FlashUpLoadConfig;
	if(!FlashUpLoadConfig){
		FlashUpLoadConfig={
				thisName:"upload",
				rootPath:$("#initPath").val()+"/view/resource/plug-in/upload/",
				url:$("#initPath").val()+"/AttachmentController.do?method=uploadAttachment",
				divId:"flashLoad",
				uploadCompelete:function(data){
					var attr =eval('('+data+')');
					$("#attachmentInfo").append("<tr id='"+attr.data.objId+"'></tr>")
					$("#"+attr.data.objId).append("<td><a href='javascript:void(0);'>"+attr.data.viewName+"</a></td>")
					$("#"+attr.data.objId).append("<td><a href='javascript:void(0);' onclick='Attachment.delObject(\""+attr.data.objId+"\");return false;'>删除</a></td>")
					upload.UploadUrl=upload.UploadUrl+"&relationId="+attr.data.relationId
				},
				onAllLoadFinish:function(data){
					var attr =eval('('+data+')');
					$("#attachmentInfo").append("<tr id='"+attr.data.objId+"'></tr>")
					$("#"+attr.data.objId).append("<td><a href='javascript:void(0);'>"+attr.data.viewName+"</a></td>")
					$("#"+attr.data.objId).append("<td><a href='javascript:void(0);' onclick='Attachment.delObject(\""+attr.data.objId+"\");return false;'>删除</a></td>")
				},
				FileFilter:"*.*;"
		}
	}else{
		FlashUpLoadConfig.thisName="upload"
		FlashUpLoadConfig.divId="flashLoad";
		if(!FlashUpLoadConfig.rootPath)
			FlashUpLoadConfig.rootPath=$("#initPath").val()+"/view/resource/plug-in/upload/";
		//alert(FlashUpLoadConfig.rootPath)
		if(!FlashUpLoadConfig.url)
			FlashUpLoadConfig.url=$("#initPath").val()+"/AttachmentController.do?method=uploadAttachment";
		//alert(FlashUpLoadConfig.rootPath)
		if(!FlashUpLoadConfig.uploadCompelete)
			FlashUpLoadConfig.uploadCompelete=function(data){
			    var attr =eval('('+data+')');
			    $("#"+FlashUpLoadConfig.tBodyId).append("<tr id='"+attr.data.objId+"'></tr>")
				$("#"+attr.data.objId).append("<td><a href='javascript:void(0);'>"+attr.data.viewName+"</a></td>")
				$("#"+attr.data.objId).append("<td><a href='javascript:void(0);' onclick='Attachment.delObject(\""+attr.data.objId+"\");return false;'>删除</a></td>")
				$("#"+FlashUpLoadConfig.hiddenId).val(attr.data.relationId)
				upload.UploadUrl=upload.UploadUrl+"&relationId="+attr.data.relationId
			}
		if(!FlashUpLoadConfig.onAllLoadFinish)
			FlashUpLoadConfig.onAllLoadFinish=function(data){
				var attr =eval('('+data+')');
				$("#"+FlashUpLoadConfig.tBodyId).append("<tr id='"+attr.data.objId+"'></tr>")
				$("#"+attr.data.objId).append("<td><a href='javascript:void(0);'>"+attr.data.viewName+"</a></td>")
				$("#"+attr.data.objId).append("<td><a href='javascript:void(0);' onclick='Attachment.delObject(\""+attr.data.objId+"\");return false;'>删除</a></td>")
				$("#"+FlashUpLoadConfig.hiddenId).val(attr.data.relationId)
			}
		if(!FlashUpLoadConfig.FileFilter)
			FlashUpLoadConfig.FileFilter="*.*;";
	}
	upload = new FlashUpLoad(FlashUpLoadConfig)
	if($("#"+FlashUpLoadConfig.hiddenId).length>0){
		if($("#"+FlashUpLoadConfig.hiddenId).val!=""){
			upload.UploadUrl=upload.UploadUrl+"&relationId="+$("#"+FlashUpLoadConfig.hiddenId).val()
		}
	}
	upload.create();
})