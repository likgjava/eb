<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>

<form id="AddInfoDivForm">

<input type="hidden" id="suppbuyStatus" value="${param.suppbuyStatus}"/>

<div class="formTips attention">
	<ul>
		<li>
			<em>提示：</em><span name="msg"></span>
		</li>
	</ul>
</div>

		<div class="conOperation"> 
		  <button class="largeBtn" id="ok" type="button"><span>确定</span></button>
		</div>

</form>

<script>
$(document).ready(function(){
	var msg = '';
	if($('#suppbuyStatus').val()=='00') {
		msg = "您好，你当前的机构信息不完整，请完善信息";
	} else if($('#suppbuyStatus').val()=='03') {
		msg = "您好，你当前的机构信息未通过审核，请修改信息";
	}
	$('span[name=msg]').text(msg);

	//确定
	$('#ok').click(function(){
		$('.epsDialogClose').trigger('click');
	})
})
</script>