<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/eval/updateCongruousFactorTypeForm.js"></script>
<div class="formLayout form2Pa">
	<form id="congruousFactorTypeForm" method="post" modelAttribute="congruousFactorType">
     	<h4><span></span></h4>
     	<ul>
 				<input type="hidden" tabindex="9" value="${congruousFactorType.objId}" id="objId" name="objId">
    				<input type="hidden" tabindex="9" value="${congruousFactorType.projId}" id="projId" name="projId">
    				<input type="hidden" tabindex="9" value="1" id="isLeaf" name="isLeaf">
    				<input type="hidden" tabindex="9" value="${congruousFactorType.parent.objId}" id="parent.objId" name="parent.objId">
			      <li class="fullLine">
			        <label for="rname" class="short">指标名称：</label>
			        <input type="text" tabindex="1" class="required" name="typeName" value="${congruousFactorType.typeName}" id="typeName">
			        <span class="eleRequired">*</span>
			      </li>
			       <li class="fullLine">
			        <label for="rname" class="short">指标权重：</label>
			        <input type="text" tabindex="1" class="required" name="weightCoefficient" value="${congruousFactorType.weightCoefficient}" id="weightCoefficient">
			        <span class="eleRequired">*</span>
			      </li >
			      	<li class="fullLine">	
	     			<label  class="short">审核方式：</label>
	     			<html:select styleClass="required" id="auditMethod" name="auditMethod" code="auditMethod" selectedValue="${congruousFactorType.auditMethod}" disabled="true">
						</html:select>
	   	   			<span class="eleRequired">*</span>
	    		 </li>
			       <li class="fullLine">
			        <label for="rname" class="short">指标排序号：</label>
			        <input type="text" tabindex="1" class="required" name="sort" value="${congruousFactorType.sort}" id="sort">
			        <span class="eleRequired">*</span>
			      </li>
			      <li class="fullLine">
			        <label for="input01" class="short">指标说明：</label>
			        <input type="text" tabindex="1" class="required" name="remark" value="${congruousFactorType.remark}" id="remark">
				    <span class="eleRequired">*</span>			      
		      	 </li>
		</ul>
	</form>
</div>
  <div class="conOperation">
	<button type="button" id="congruousFactorTypeSave"><span><spring:message code="globe.save"/></span></button>
	<button type="button" id="back" tabindex="19""><span><spring:message code="globe.return"/></span></button>
  </div>