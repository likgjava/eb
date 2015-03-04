<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/workgroup/workGroupForm.js"></script>
<div class="formLayout form2Pa">        
	<form id="workGroupForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<input type="hidden" name="taskId" id="taskId" value="<c:out value="${taskId}"/>"/>
	<h4><span>&nbsp;</span></h4>
     		<ul>

					<li>
						<label for="workgType"><spring:message code="workGroupForm.workgType"/></label>
						<input type="text" name="workgType" id="workgType"  style="border-width:0px;background-color:transparent;"
									readonly="readonly"
							  />
							
					</li>

					<li>
						<label for="workgRemark"><spring:message code="workGroupForm.workgRemark"/></label>
						<input type="text" name="workgRemark" id="workgRemark"  style="border-width:0px;background-color:transparent;"
									readonly="readonly"
							  />
					</li>

		</ul>
		<div class="conOperation">
			<button id="workGroupReturn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
		</div>
	</form>	
</div>
<div id="workgMemberList"></div>