<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/projectDetail.js"></script>
<div class="partContainers">
<div class="formLayout form2Pa">        
	<form id="projectForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<input type="hidden" name="taskId" id="taskId" value="<c:out value="${taskId}"/>"/>
		<input type="hidden" name="parentID" id="parentID" value="${param.parentId}"/>
     		<ul>

					<li>
						<label for="projCode"><spring:message code="projectForm.projCode"/>：</label>
						<span name="projCode" id="projCode"></span>
					</li>

					<li>
						<label for="projName"><spring:message code="projectForm.projName"/>：</label>
						<span name="projName" id="projName"></span>
					</li>

					<li>
						<label for="createOperator"><spring:message code="projectForm.createOperator"/>：</label>
						<span name="createOperator" id="createOperator"></span>
					</li>

					<li>
						<label for="projDepartMent"><spring:message code="projectForm.projDepartMent"/>：</label>
						<span name="projDepartMent" id="projDepartMent"></span>
					</li>

					<li>
						<label for="projManager"><spring:message code="projectForm.projManager"/>：</label>
						<span name="projManager" id="projManager"></span>
					</li>

					<li>
						<label for="ebuyMethod"><spring:message code="projectForm.ebuyMethod"/>：</label>
						<span name="ebuyMethodCN" id="ebuyMethod"></span>
					</li>

					<li class="formTextarea">
						<label for="projSummary"><spring:message code="projectForm.projSummary"/>：</label>
						<span name="projManager" id="projManager"></span>
					</li>
					
					<li class="formTextarea">
						<label for="remark"><spring:message code="projectForm.remark"/>：</label>
						<span name="remark" id="remark"></span>
					</li>  
		</ul>
		<div class="conOperation">
			<button id="projectSave" type="button" tabindex="18"><span><spring:message code="globe.save"/></span></button>
			<button id="projectReturn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
		</div>
	</form>
</div>
</div>