<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript">
var bulletinForm = {};

//关闭
bulletinForm.closeDiv  = function(){
	$("#detailDivView").find('.epsDialogClose').trigger('click');
}
</script>

<div class="formLayout">
<form:form id="bulletinForm" method="post" modelAttribute="bulletin">
	<span class="hidden" id="bullContents" ></span>
	<input	type="hidden" name="objId" id="objId"	value="<c:out value="${param.objId}"/>" />
	<ul>
		<li><label>采购预告标题：</label>
		<span>${bulletin.bullTitle}</span>
		</li>
	</ul>
	<div>
	${bulletin.bullContents}
	</div>
	<div class="conOperation">
		<button type="button" id="closeDiv" onclick="bulletinForm.closeDiv();"><span>关闭</span></button>
	</div>
</form:form>
</div>

