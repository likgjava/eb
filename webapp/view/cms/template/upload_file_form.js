
var templateFileForm={};

$(document).ready(function(){
	var path = $("#uploadFileForm>input[name=path]").val();
	$("#uploadFileForm span[name=currentPath]").html("当前上传路径"+path.substring(path.indexOf(tempFileType,0),path.length)+"\\");
	$('#uploadFileForm').validate();
	
	// 关闭
	$('#closeUploadfile').click(function(){
		$('#epsDialogClose').click();
	});
	
	$("#uploadFileForm li").find("button[name=deleteFile]").bind("click",function(){
		var labelStr = $(this).parent("li").find("label").html();
		
		$(this).parent().empty().append("<label style='width:20%'>"+labelStr+"</label>	<input type='file' name='upladFile2' id='upladFile2' class=''  size='40' /><button type='button' class='del' name='deleteFile'>清空</button>");
	});
	
	// 提交
	$('#uploadFileBtn').click(function(){
		$('#uploadFileBtn').attr("disabled",true);
		$('#uploadFileBtn>span').html("正在上传附件...");
		var uploadFils = "";
		$("#uploadFileForm").find("li>input[name^=upladFile]").each(function(){
			if("" == uploadFils){
				uploadFils = $(this).attr("name");
			}else{
				uploadFils = uploadFils+","+$(this).attr("name");
			}
			$("#uploadFileForm>input[name=uploadFils]").val(uploadFils);
		});
		$("#uploadFileForm").submit();
	});
	
	// 表单提交用于上传附件
	$("#uploadFileForm").submit(function(){
		$(this).ajaxForm({
			success:function(json){
				var json = eval('(' + json + ')');
				if(json.success=="true"){
					templateFileList.initTreeFromFile();
					alert("上传成功！\n");
					//alert("上传成功！\n"+json.msgs);
					$('#epsDialogClose').click();
				}else{
					alert("上传失败！\n"+json.msgs);
					$('#uploadFileBtn').attr("disabled",false);
					$('#uploadFileBtn>span').html("上传");
				}
			}
		});
	})
});
