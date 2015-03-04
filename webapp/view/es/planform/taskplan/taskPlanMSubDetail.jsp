<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div class="formLayout ">
		<ul>
	     	<li class="fullLine" class="short">
	     		<label class="short">预算单位：</label>
				<span>${taskPlanMSub.taskPlanSub.budgetName}</span>
    	    </li>
	     	<li class="fullLine" class="short">
	     		<label class="short">预算总金额：</label>
				<span><fmt:formatNumber value="${taskPlanMSub.taskPlanSub.totalPrice}" type="currency"/></span>
    	    </li>
	     	<li class="fullLine" class="short">
	     		<label class="short">采购品目：</label>
				<span>${taskPlanMSub.taskPlanSub.purchaseName}</span>
    	    </li>
	     	<li class="fullLine" class="short">
	     		<label class="short">采购方式：</label>
				<span>${taskPlanMSub.taskPlan.ebuyMethodCN}</span>
    	    </li>
	     	<li class="fullLine" class="short">
	     		<label class="short">采购类型：</label>
				<span>${taskPlanMSub.taskPlan.taskTypeCN}</span>
    	    </li>
	     
		</ul>
		   <%--<div class="conOperation">
				<button type="button" id="closeId" tabindex="19""><span><spring:message code="globe.close"/></span></button>
		   </div>--%>
</div>

<script type="text/javascript">
	$('#closeId').click(function(){//关闭
		$("#epsDialogCloseNoReload").click();
	});

</script>