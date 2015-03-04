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
			        <span >${congruousFactorType.typeName}</span>
			      </li>
			       <li class="fullLine">
			        <label for="rname" class="short">指标权重：</label>
			        <span >${congruousFactorType.weightCoefficient}</span>
			      </li >
			      	<li class="fullLine">	
	     			<label  class="short">审核方式：</label>
	     			<html:select styleClass="required" id="auditMethod" name="auditMethod" code="auditMethod" selectedValue="${congruousFactorType.auditType}" disabled="true">
						</html:select>
	    		 </li>
			       <li class="fullLine">
			        <label for="rname" class="short">指标排序号：</label>
			        <span >${congruousFactorType.sort}</span>
			      </li>
			      <li class="fullLine">
			        <label for="input01" class="short">指标说明：</label>
				    <span >${congruousFactorType.remark}</span>			      
		      	 </li>
		</ul>
	</form>
</div>
