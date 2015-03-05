
var ftp_attachment={};
//ftp上传
function ftpUpload(json) {
	ftpUploadObject.FileCompression = false;
    ftpUploadObject.FtpUrl=json.ftpUrl;
    ftpUploadObject.FtpUserName = json.ftpUserName;//用户名即账号
	ftpUploadObject.FtpPassword = json.ftpPassword;//密码
    ftpUploadObject.FileTempPath = json.ftpFileTempPath;
    ftpUploadObject.UploadFile(json.localFile, json.saveName);
}
//ftp下载
function ftpDownload (json) {
    ftpUploadObject.FileCompression = false;
    ftpUploadObject.FtpUrl=json.ftpUrl;
    ftpUploadObject.FtpUserName = json.ftpUserName;//用户名即账号
	ftpUploadObject.FtpPassword = json.ftpPassword;//密码
    ftpUploadObject.FileTempPath = json.ftpFileTempPath;
    ftpUploadObject.DownloadFile(json.saveName, json.viewName);
}
ftp_attachment.del= function(id){
	
	if(window.confirm("确认要删除该附件吗？")){
		$.getJSON($('#initPath').val()+'/AttachmentController.do?method=delectFtp',{objId:id},function(json){
			if(json.failtrue){
				alert(json.result);
				return;
			}
			$('#'+id).remove();
		})
	}
	
}
ftp_attachment.show = function(url){
	window.open(url);
//	$.getJSON($('#initPath').val()+"/AttachmentController.do?method=downLoadFtp",{objId:attachmentId},function(json){
//		//alert(obj2str(json))
//		ftpDownload(json);
//	});
}
ftp_attachment.upload = function(){
	if($('#file_ftp').val()=='')return false;
	$('input[name=attachFile]').val($('#file_ftp').val())
	$.getJSON($('#initPath').val()+'/AttachmentController.do?method=upLoadFtp',formToJsonObject('form_ftp'),function(json){
				if(json.failtrue){
					alert(json.result);
					return;
				}
				$('input[name=attachRelaID]').val(json.relationId);
				$('#form_ftp').prev('input').val(json.relationId);//给领域类关联的属性赋值
				json.localFile = $('#file_ftp').val();
				//alert(obj2str(json))
				ftpUpload(json);
				var htm ='<li id=\''+json.objId+'\'><a href="#" onclick="javascript:ftp_attachment.show(\''+json.ftpUrl+'/'+json.saveName+'\');return false;" >'+json.viewName+'</a> ' +
						'<span onclick="javascript:ftp_attachment.del(\''+json.objId+'\')" class="sysicon siDelete">删除</span></li>';
				$('#ftp_atta_display_ul').append(htm);
				var file = $("#file_ftp");  
			    file.after(file.clone().val(""));  
			    file.remove(); 
			    $('input[name=viewName]').val('');
	})
}
$(document).ready(function(){
	$('#go_ftp_upload').click(function(){
		ftp_attachment.upload();
	})
});