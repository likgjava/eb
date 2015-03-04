<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/common/toFactortypeDeDetail.js"></script>
<div class="formLayout form2Pa detail"  id="menuDetail">
	<h5><span>指标分类信息</span></h5>
	<table class="tableList">
		<tr>
			<th><label><spring:message code="factortypeDeForm.factorTypeName"/>:</label></th>
			<td><span>${factortypeDe.factorTypeName}</span></td>
			<th><label><spring:message code="factortypeDeForm.facType"/>:</label></th>
			<td><span>${factortypeDe.facTypeCn}</span></td>
			<th><label><spring:message code="factortypeDeForm.purchaseCategory"/>:</label></th>
			<td> <span>${factortypeDe.purchaseCategory.name}</span></td>
		</tr>
	</table>
</div>
