<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<input type="hidden" id="_memberClassDetailDialogID"  value="<c:out value="${param.dialogId}"/>"/>
<form:form id="memberClassDetailForm" method="post" modelAttribute="memberClass">
	<div id="memberClassInfo" class="formLayout form2Pa">
		<div style="border: 1px solid #D5D5D5; float: left;">
			<img src="<c:url value="AttachmentController.do?method=showImg&objId=${memberClass.memberClassPic}" />" width="120px" height="120px">
		</div>
		<ul>
			<li class="fullLine"><label>级别名称：</label> <span>${memberClass.memberClassName}</span></li>
			<li class="fullLine"><label>会员级别：</label> <span>${memberClass.memberClassNumCN}</span></li>
			<li class="fullLine"><label>入会时长：</label><span>${memberClass.minAge}个月~${memberClass.maxAge}个月</span></li>
			<li class="fullLine"><label>缴费金额：</label> <span>￥<fmt:formatNumber value="${memberClass.minFee}" pattern="#,##0.00#" />~￥<fmt:formatNumber value="${memberClass.maxFee}" pattern="#,##0.00#" /></span></li>
	  	</ul>
		<ul>
			<li class="fullLine"><label>级别描述：</label><p>${memberClass.memberClassDesc}</p></li>
	  	</ul>
	</div>
</form:form>
	
<div class="conOperation">
	<button class="operationBtnDiv" type="button" id="memberClassDetailClose"><span>关闭</span></button>
</div>

<script>
/**
 * 会员级别详细信息页面
 * create by likg
 */
$(document).ready(function(){
	//关闭
	$("#memberClassDetailClose").click(function(){
		if($("#_memberClassDetailDialogID").val() != ""){
			$(document.getElementById($("#_memberClassDetailDialogID").val())).find('.epsDialogClose').trigger('click');
		}else{
			$('.epsDialogClose').trigger('click');
		}
	})
});
</script>