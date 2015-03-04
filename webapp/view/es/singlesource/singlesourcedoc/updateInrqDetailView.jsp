<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<style type="text/css">
<!--
h5.htop {text-align: right;margin-right:10px;}
--> 
</style>
<input type="hidden" id="auditStatus" value="${inrqDetail.inviterollrequ.auditStatus}"/>
<input type="hidden" id="fromType" value="${fromType}">
<form id="inrqDetailForm" method="post">
<div class="formLayout formPa" >
	<textarea name="contents" id="contents" class="required hidden"/></textarea><span class="eleRequired">*</span>
	<input type="hidden" name="fromProj" value="${fromProj}" id="fromProj"/>
	<input type="hidden" name="objId" value="${inrqDetail.objId }"/>
</div>
<div class="conOperation">
<c:if test="${inrqDetail.inviterollrequ.auditStatus==00}">
	<button id="save" type="button" tabindex="19" onclick="updateInrqDetail('${inrqDetail.objId}')"><span><spring:message code="globe.save"/></span></button>
</c:if>
	<button id="returnBtn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
</div>
</form>
<textarea id="bulletin" style="display:none;" >${inrqDetail.contents}</textarea>
<script type="text/javascript">

$(document).ready(function(){
	oFCKeditor = new FCKeditor('contents','100%','450','Template','') ;
	$('#contents').text($("#bulletin").text());
	oFCKeditor.ReplaceTextarea();
   if( $("#fromType").val()!='look'){
      $("#returnBtn").hide();
	 }

   $("#returnBtn").click(function(){
	   $('#epsDialogClose').click();
	   });
	
});

function updateInrqDetail(objId){
	FCKeditor_BackValue(); 
	if(window.confirm("确认保存?"))
	{
		$.getJSON($('#initPath').val()+'/InrqDetailController.do?method=updateInrqDetailView&objId='+objId, formToJsonObject('inrqDetailForm'), function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			$('#epsDialogClose').click();
		});
	}
}
</script>