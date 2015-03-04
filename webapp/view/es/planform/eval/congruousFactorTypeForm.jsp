<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/eval/congruousFactorTypeForm.js"></script>
<div class="formLayout form2Pa">
	<form id="congruousFactorTypeForm2" method="post" modelAttribute="congruousFactorType">
		<input type="hidden" name="projId" value="${supCongruousFactorType.projId}"></input>
		<input type="hidden" name="parent.objId" value="${supCongruousFactorType.objId}"></input>
		<input type="hidden" name="auditMethod" value="${supCongruousFactorType.auditMethod}"></input>
		<input type="hidden" tabindex="9" value="1" id="isLeaf" name="isLeaf">
     	<h4><span></span></h4>
     	<ul>
			      <li class="fullLine">
			        <label for="rname" class="short">指标名称：</label>
			        <input type="text" tabindex="1" class="required" name="typeName" value="" id="typeName">
			        <span class="eleRequired">*</span>
			      </li>
			       <li class="fullLine">
			        <label for="rname" class="short">指标权重：</label>
			        <input type="text" tabindex="1" class="required digits" name="weightCoefficient" value="" id="weightCoefficient">
			        <span class="eleRequired">*</span>
			      </li >
			       <li class="fullLine">
			        <label for="rname" class="short">指标排序号：</label>
			        <input type="text" tabindex="1" class="required digits" name="sort" value="" id="sort">
			        <span class="eleRequired">*</span>
			      </li>
			      <li class="fullLine">
			        <label for="input01" class="short">指标说明：</label>
			        <input type="text" tabindex="1" class="required" name="remark" value="" id="remark">
				    <span class="eleRequired">*</span>			      
		      	 </li>
		</ul>
	</form>
</div>
		   <div class="conOperation">
				<button type="button" id="congruousFactorTypeSave"><span><spring:message code="globe.save"/></span></button>
				<button type="button" id="return" tabindex="19"><span><spring:message code="globe.return"/></span></button>
		   </div>