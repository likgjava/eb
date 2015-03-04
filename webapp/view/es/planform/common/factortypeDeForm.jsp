<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/common/factortypeDeForm.js"></script>
<h5><span>指标分类信息</span></h5>
<input type="hidden" id="_facType" value="${factortypeDe.facType}"></input>
<form id="factortypeDeForm">
	<input type="hidden" name="objId" id="objId" value="${factortypeDe.objId}"></input>
	<input type="hidden" name="isLeaf" id="isLeaf" value="${factortypeDe.isLeaf}"></input>
	<input type="hidden" name="sort" id="sort" value="${factortypeDe.sort}"></input>
	<input type="hidden" name="parent.objId" id="parent.objId" value="${factortypeDe.parent.objId}"></input>
	<ul>
	    <li class="fullLine"><label><spring:message code="factortypeDeForm.factorTypeName"/>:</label>
	    <input type="text" name="factorTypeName" id="factorTypeName" value="${factortypeDe.factorTypeName}" class="required"></input><span class="eleRequired">*</span>
		<label><spring:message code="factortypeDeForm.facType"/>:</label>
		<select id="facType" name="facType" style="width: 60px">
			<option value="0">通用</option>
			<option value="1">行业</option>
		</select>
	   	<label><spring:message code="factortypeDeForm.purchaseCategory"/>:</label>
	   	<input type="text" id="purchaseCategory.objId" value="${factortypeDe.purchaseCategory.objId}"></input>
	   	&nbsp;&nbsp;&nbsp;<button id="save_btn"><span>保存</span></button></li>
	</ul>
</form>