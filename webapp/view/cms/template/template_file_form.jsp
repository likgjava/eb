<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript">
$(document).ready(function(){
	$('#templateFileForm').validate();
	//var path = $("#templateFileForm>input[name=path]").val();
	//$("#templateFileForm span[name=currentPath]").html("当前路径"+path.substring(path.indexOf(tempFileType,0),path.length)+"\\");
	
    // 关闭
	$('#closeAddDirectory').click(function(){
		$('#epsDialogClose').click();
	});
	
	// 提交
	$('#saveDirectory').click(function(){
		if(!$('#templateFileForm').valid()){return;}
		$.getJSON($('#initPath').val()+'/TemplateFileController.do?method=createDirectory', {"path":$("#templateFileForm input[name=path]").val(),"fileName":$("#templateFileForm input[name=fileName]").val()}, function(json){
			if(json.failure){alert(json.result);return;}
			templateFileList.initTreeFromFile();
			$('#epsDialogClose').click();
		});
	});

});
</script>
<div class="formLayout formPa">
	<form id="templateFileForm" method="post">
		<input type="hidden" name="path" id="currentRealPath" value="<c:out value="${param.path}"/>"/>
     	<h5><span name="currentPath"></span></h5>
     	<ul>
	     	<li>
	     		<label for="">目录名称：</label>	<input type="text" name="fileName" id="fileName" class="required" 
						      />
    	   			<span class="eleRequired">*</span>
    	    </li>
		</ul>
		   <div class="conOperation">
				<button type="button" id="saveDirectory"><span><spring:message code="globe.save"/></span></button>
				<button type="button" id="closeAddDirectory" tabindex="19""><span><spring:message code="globe.close"/></span></button>
		   </div>
	</form>
</div>
