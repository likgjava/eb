<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/eval/congruousFactorForm.js"></script>
<div class="formLayout form2Pa">
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
	   	       			<input type="checkBox" id="projId2" value="${pack.objId}" projName="${pack.projName}" <c:if test="${i > 0}">checked='checked'</c:if> />${pack.projName}
	   	       		</c:forEach>
	    	    </li>
			</c:if>
     		<li class="fullLine">	
     			<label  class="short"><spring:message code="congruousFactorForm.factorName"/>：</label>
				<input type="text" name="factorName" id="factorName" class="required" value="${congruousFactor.factorName}"  style="width:50%"/>
   	   			<span class="eleRequired">*</span>
    	    </li>
    	    <li class="fullLine">	
	     			<label  class="short">最大值：</label>
					<input type="text" name="maxScore" id="maxScore" class="required floats" value="${congruousFactor.maxScore}"  style="width:60px;" min="0" max="${param.scoreNum}"/>
	   	   			<span class="eleRequired">*</span>
	    	 </li>
	    	 <li class="fullLine">	
	     			<label  class="short">最小值：</label>
					<input type="text" name="minScore" id="minScore" class="required floats" value="${congruousFactor.minScore}"  style="width:60px;" min="0" max="${param.scoreNum}"/>
	   	   			<span class="eleRequired">*</span>
	    	 </li>
	    	<li class="fullLine">	
	     			<label  class="short"><spring:message code="congruousFactorForm.score"/>：</label>
					<input type="text" name="score" id="score" class="required floats" value="${congruousFactor.score}"  style="width:60px;" min="0" max="${param.scoreNum}"/>
	   	   			<span class="eleRequired">*</span>
	    	 </li>
	    	 <input value="${congruousFactor.auditMethod}" id="auditMethod" type="hidden" name="auditMethod">
    	    <!-- 暂时隐藏 mod liuy -->
    	    <li class="fullLine" style="display:none">
   	       		<label  class="short"><spring:message code="congruousFactorForm.isNeed"/>：</label>
   	       		<c:if test="${congruousFactor.isNeed == '01'}">
   	       			是<input type="radio" name="isNeed"  value="01" checked="checked"/>否<input type="radio" name="isNeed"  value="00"/>
   	       		</c:if>
				<c:if test="${congruousFactor.isNeed != '01'}">
					是<input type="radio" name="isNeed"  value="01" />否<input type="radio" name="isNeed"  value="00" checked="checked"/>
				</c:if>
    	    </li>
    	    <li class="fullLine">
   	       		<label class="short"><spring:message code="congruousFactorForm.remark"/>：</label>
				<textarea style="width: 50%;height: 60px" id="remark" name="remark" maxlength="200" class="maxInput">${congruousFactor.remark}</textarea>
				<span class="eleRequired"></span>
    	    </li>
		</ul>
		   <div class="conOperation">
				<button type="button" id="congruousFactorSave"><span><spring:message code="globe.save"/></span></button>
				<button type="button" id="back" tabindex="19""><span><spring:message code="globe.return"/></span></button>
		   </div>
	</form>
</div>