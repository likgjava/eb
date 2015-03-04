<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript">
var GoodsOptionalFittingForm = {};
$(document).ready(function() {
	//关闭
	$('button[id=closeBtn]').click(function() {
		if($("#_dialogID").val() != "")
			$(document.getElementById($("#_dialogID").val())).find('.epsDialogClose').trigger('click');
		else
			$('.epsDialogClose').trigger('click');
	})
})
</script>
<input type="hidden" id="_dialogID"  value="<c:out value="${param.dialogId}"/>"/>
<form:form id="GoodsOptionalFittingForm" method="post" modelAttribute="goodsOptionalFitting">
	<div class="formLayout form2Pa">
	<ul>
		<li class="fullLine"><label>选配内容：</label> 
			${goodsOptionalFitting.optionContent}
		</li>
		<li class="formTextarea"><label>选配规格型号描述：</label> 
			${goodsOptionalFitting.specification}
		</li>
	</ul>
	</div>
	<div class="conOperation">
	<button type="button" id="closeBtn"><span>关闭</span></button>
	</div>
</form:form>

