<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/contract/contractClauseDetail.js"></script>

<div class="formLayout form2Pa">
	<form id="contractClauseDetailForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
     	<h4><span></span></h4>
			     	<ul>
					</ul>
		   <div class="conOperation">
				<button type="button" id="contractClauseReturn" type="button" tabindex="20""><span><spring:message code="globe.return"/></span></button>
		   </div>
	</form>
</div>

	<div class="formTips">
      <ul>
          <li><spring:message code="globe.input.required.prompt"/></li>
      </ul>
    </div>
