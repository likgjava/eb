<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div class="partContainers">
	<div class="formLayout form2Pa">  
			<h5><span>查看处理方式</span></h5>
				<ul>
				<li>
					<label class="short">招标编号：</label>
					<span>${projectExceptionApply.project.projCode}</span>
				</li>
				<li>
					<label class="short">招标名称：</label>
					<span>${projectExceptionApply.project.projName}</span>
				</li>
				<li>
					<label class="short">项目内容：</label>
					<span>${projectExceptionApply.project.content}</span>
				</li>
				<li>
					<label class="short">采购方式：</label>
					<span>${projectExceptionApply.project.ebuyMethodCN}</span>
				</li>
				<li>
					<label class="short">项目负责人：</label>
					<span>${projectExceptionApply.project.manager.name}</span>
				</li>
				<li>
					<label class="short">预算金额：</label>
					<span><fmt:formatNumber value="${projectExceptionApply.project.budgetTotalMoney}" type="currency"/></span>
				</li>
				<li class="fullLine">
					<label class="short">选择处理方式：</label>
					<span>${projectExceptionApply.adviceProcwayCN}</span>
				</li>
				<c:if test="${projectExceptionApply.adviceProcway==01}">
				<li class="fullLine">
					<label class="short">重新招标原因：</label>
					<span>${projectExceptionApply.reasonDesc}</span>
				</li>
				</c:if>
				<c:if test="${projectExceptionApply.adviceProcway==02}">
				<li class="fullLine">
					<label class="short">采购方式：</label>
					<span>${projectExceptionApply.newEbuyMethodCN}</span>
				</li>
				<li class="fullLine">
					<label class="short">重新招标原因：</label>
					<span>${projectExceptionApply.reasonDesc}</span>
				</li>
				</c:if>
				<c:if test="${projectExceptionApply.adviceProcway==03}">
				<li class="fullLine">
					<label class="short">终止项目原因：</label>
					<span>${projectExceptionApply.reasonDesc}</span>
				</li>
				</c:if>
				
				</ul>
	</div>
<div class="conOperation">
	<button name="historyBackBtn" type="button" tabindex="20"><span><spring:message code="globe.return"/></span></button>
</div>
</div>
        
	 
<script language="javascript">
var projectExceptionDetailForm={};
$(document).ready(function(){
	$("#returnUrl").val($('#initPath').val()+"/view/es/planform/project/projectExceptionApplyList.jsp");
});

</script>