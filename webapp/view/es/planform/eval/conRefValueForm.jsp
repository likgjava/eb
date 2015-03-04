<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/eval/conRefValueForm.js"></script>
<div class="formLayout form2Pa">
	<form id="conRefValueForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
     	<h4><span></span></h4>
     	<ul>
		</ul>
		   <div class="conOperation">
				<button type="button" id="conRefValueSave"><span><spring:message code="globe.save"/></span></button>
				<button type="button" name="historyBackBtn" tabindex="19""><span><spring:message code="globe.return"/></span></button>
				<button type="button" id="conRefValueReset" type="button" tabindex="20""><span><spring:message code="globe.reset"/></span></button>
		   </div>
	</form>
</div>

	<div class="formTips">
      <ul>
          <li><spring:message code="globe.input.required.prompt"/></li>
      </ul>
    </div>