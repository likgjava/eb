<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript">

var templateFileForm={};

$(document).ready(function(){
	//var path = $("#uploadFileForm>input[name=path]").val();
	//$("#uploadFileForm span[name=currentPath]").html("当前上传路径"+path.substring(path.indexOf(tempFileType,0),path.length)+"\\");
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
					alert("上传成功！\n"+json.msgs);
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

</script>
<div class="formLayout formPa">
	<form id="uploadFileForm" method="post" action="TemplateFileController.do?method=upLoadFile" enctype="multipart/form-data">
		<input type="hidden" name="path" id="currentRealPath" value="<c:out value="${param.path}"/>"/>
		<input type="hidden" name="uploadFils" id="uploadFils" value=""/>
     	<h5><span name="currentPath"></span></h5>
     	<ul>
	     	<li class="fullLine">
	     		<label style="width:20%">资源1：</label>	<input type="file" name="upladFile1" id="upladFile1" class=""  size="40" /><button type="button" class="del" name="deleteFile">清空</button>
    	    </li>
	     	<li class="fullLine">
	     		<label style="width:20%">资源2：</label>	<input type="file" name="upladFile2" id="upladFile2" class=""  size="40" /><button type="button" class="del" name="deleteFile">清空</button>
    	    </li>
	     	<li class="fullLine">
	     		<label style="width:20%">资源3：</label>	<input type="file" name="upladFile3" id="upladFile3" class=""  size="40" /><button type="button" class="del" name="deleteFile">清空</button>
    	    </li>
	     	<li class="fullLine">
	     		<label style="width:20%">资源4：</label>	<input type="file" name="upladFile4" id="upladFile4" class=""  size="40" /><button type="button" class="del" name="deleteFile">清空</button>
    	    </li>
	     	<li class="fullLine">
	     		<label style="width:20%">资源5：</label>	<input type="file" name="upladFile5" id="upladFile5" class=""  size="40" /><button type="button" class="del" name="deleteFile">清空</button>
    	    </li>
	     	<li class="fullLine">
	     		<label style="width:20%">资源6：</label>	<input type="file" name="upladFile6" id="upladFile6" class=""  size="40" /><button type="button" class="del" name="deleteFile">清空</button>
    	    </li>
	     	<li class="fullLine">
	     		<label style="width:20%">资源7：</label>	<input type="file" name="upladFile7" id="upladFile7" class=""  size="40" /><button type="button" class="del" name="deleteFile">清空</button>
    	    </li>
	     	<li class="fullLine">
	     		<label style="width:20%">资源8：</label>	<input type="file" name="upladFile8" id="upladFile8" class=""  size="40" /><button type="button" class="del" name="deleteFile">清空</button>
    	    </li>
	     	<li class="fullLine">
	     		<label style="width:20%">资源9：</label>	<input type="file" name="upladFile9" id="upladFile9" class=""  size="40" /><button type="button" class="del" name="deleteFile">清空</button>
    	    </li>
	     	<li class="fullLine">
	     		<label style="width:20%">资源10：</label>	<input type="file" name="upladFile10" id="upladFile10" class=""  size="40" /><button type="button" class="del" name="deleteFile">清空</button>
    	    </li>
		</ul>
		   <div class="conOperation">
				<button type="button" id="uploadFileBtn"><span>上传</span></button>
				<button type="button" id="closeUploadfile" tabindex="19"><span><spring:message code="globe.close"/></span></button>
		   </div>
	</form>
</div>
