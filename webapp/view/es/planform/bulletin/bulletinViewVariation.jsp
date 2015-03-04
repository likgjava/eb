<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

	<span style="height:auto;">${bulletin.bullContents}</span>


	<div class="conOperation">
		<button id="subProjectReturn" type="button" tabindex="19"><span><spring:message code="globe.close"/></span></button>
	</div>
	
	
<script type="text/javascript">

//关闭
$("#subProjectReturn").click(function(){
	$('#epsDialogCloseNoReload').click();
});
	
</script>