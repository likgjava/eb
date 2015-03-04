<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/eval/updateCongruousFactorForm.js"></script>
<div class="formLayout form2Pa">
<br>
	<input type="hidden" id="PROJ_ID" value="${project.objId}" ></input>
	<input type="hidden" id="PROJ_NAME" value="${project.projName}" ></input>
	<form id="congruousFactorForm" method="post" modelAttribute="congruousFactor">
		<input type="hidden" name="objId" id="objId" value="${congruousFactor.objId}"/>
		<input type="hidden" name="factorType.objId" value="${congruousFactor.factorType.objId}"/>
     	<ul>
	     	<c:if test="${fn:length(packList) > 0}">
				<li class="fullLine">
	   	       		<label class="short"><spring:message code="congruousFactorForm.projName"/>：</label>
	   	       		<c:forEach items="${packList}" var="pack">
	   	       			<c:set var="i" value="0"></c:set>
	   	       			<c:forEach items="${factypeMfactorList}" var="factypeMfactor">
	   	       				<c:if test="${pack.objId == factypeMfactor.projId}">
	   	       					<c:set var="i" value="${i+1}"></c:set>
	   	       				</c:if>
	   	       			</c:forEach>
	   	       			<input type="checkBox" id="projId" value="${pack.objId}" projName="${pack.projName}" <c:if test="${i > 0}">checked='checked'</c:if> />${pack.projName}
	   	       		</c:forEach>
	    	    </li>
			</c:if>
     		<li class="fullLine">	
     			<label  class="short"><spring:message code="congruousFactorForm.factorName"/>：</label>
   	   			<span >${congruousFactor.factorName}</span>
    	    </li>
    	    <li class="fullLine">	
	     			<label  class="short">分值：</label>
					<span>${congruousFactor.score}</span>  	   			
	    	 </li>
    	      <li class="fullLine">	
	     			<label  class="short">最大值：</label>
	   	   			<span >${congruousFactor.maxScore}</span>
	    	 </li>
	    	 <li class="fullLine">	
	     			<label  class="short">最小值：</label>
	   	   			<span >${congruousFactor.minScore}</span>
	    	 </li>
    	    <c:if test="${param.scoreNum > 0.0 || param.scoreNum > 0}">
	    	    <li class="fullLine">	
	     			<label  class="short"><spring:message code="congruousFactorForm.score"/>：</label>
	   	   			<span >${congruousFactor.score}</span>
	    	    </li>
    	    </c:if>
    	      <li class="fullLine">	
	     			<label  class="short">审核方式：</label>
	     			<html:select styleClass="required" id="auditMethod" name="auditMethod" code="auditMethod" selectedValue="${congruousFactor.auditType}" disabled="true">
						</html:select>
	    	 </li>
    	    <li class="fullLine">
   	       		<label  class="short"><spring:message code="congruousFactorForm.isNeed"/>：</label>
   	       		<c:if test="${congruousFactor.isNeed == '01'}">是</c:if>
				<c:if test="${congruousFactor.isNeed != '01'}">否</c:if>
    	    </li>
    	    <li class="fullLine">
   	       		<label  class="short"><spring:message code="congruousFactorForm.remark"/>：</label>
				<span >${congruousFactor.remark}</span>
    	    </li>
		</ul>
	</form>
</div>