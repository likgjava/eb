<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript">

$(document).ready(function(){
	$('#renameTemplateFileForm').validate();
	
	 // 关闭
		$('#closerenameDirectory').click(function(){
			$('#epsDialogClose').click();
		});
		

});
</script>
<div class="formLayout formPa">
     	<ul>
	     	<li>
	     		<label for="">相对路径：</label>	<span id="currentPath"></span>
	     		
    	    </li>
		</ul>
		   <div class="conOperation">
				<button type="button" id="closerenameDirectory" tabindex="19""><span><spring:message code="globe.close"/></span></button>
		   </div>
</div>
