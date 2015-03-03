<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<input type="hidden" id="_dialogID"  value="<c:out value="${param.dialogId}"/>"/>
<div>
	<div>
		${bullContents}
	</div>
	<div class="conOperation">
		<button type="button" id="bulletinDetailClose"><span>关闭</span></button>
	</div>
</div>

<script>
$(document).ready(function(){
	//关闭弹出层
	$("#bulletinDetailClose").click(function(){
		if($("#_dialogID").val() != "")
			$(document.getElementById($("#_dialogID").val())).find('.epsDialogClose').trigger('click');
		else
			$('.epsDialogClose').trigger('click');
	});
});
</script>