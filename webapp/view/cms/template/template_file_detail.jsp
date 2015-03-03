<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript">

$(document).ready(function(){
	$('#renameTemplateFileForm').validate();
	
	 // 关闭
		$('#closerenameDirectory').click(function(){
			$('#epsDialogClose').click();
		});
		
		// 提交
		$('#renameDirectory').click(function(){
			if(!$('#renameTemplateFileForm').valid()){return;}
			$.getJSON($('#initPath').val()+'/TemplateFileController.do?method=renameFile', {"path":$("#renameTemplateFileForm input[name=path]").val(),"fileName":$("#renameTemplateFileForm input[name=fileName]").val(), "tmpType":$("#tmpType").val()}, function(json){
				if(json.failure){alert(json.result);return;}
				templateFileList.initTreeFromFile();
				$('#epsDialogClose').click();
			});
		});

});
</script>
<div class="formLayout formPa">
	<form id="renameTemplateFileForm" method="post">
		<input type="hidden" name="path" id="currentRealPath" value="<c:out value="${param.path}"/>"/>
		<input type="hidden" name="tmpType" id="tmpType" value="<c:out value="${param.tmpType}"/>"/>
     	<h5><span>重命名</span></h5>
     	<ul>
	     	<li>
	     		<label for="">名称：</label>	<input type="text" name="fileName" id="curFileName" class="required" value="${param.name}" />
    	   			<span class="eleRequired">*</span>
    	    </li>
		</ul>
		   <div class="conOperation">
				<button type="button" id="renameDirectory"><span><spring:message code="globe.save"/></span></button>
				<button type="button" id="closerenameDirectory" tabindex="19""><span><spring:message code="globe.close"/></span></button>
		   </div>
	</form>
</div>
