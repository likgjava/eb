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
						<select name="workgType" id="workgType" class="required" style="width: 153px;height: 23px">
						<option value="论证小组">论证小组</option>
						<option value="评审小组">评审小组</option>
						<option value="开标小组">开标小组</option>
						</select>	  
							<span class="eleRequired">*</span>
					</li>

					<li>
						<label for="workgRemark"><spring:message code="workGroupForm.workgRemark"/></label>
						<input type="text" name="workgRemark" id="workgRemark" 
							  />
					</li>

		</ul>
		<div class="conOperation">
			<button id="workGroupSave" type="button" tabindex="18"><span><spring:message code="globe.save"/></span></button>
			<button id="workGroupReturn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
		</div>
	</form>	
</div>
<div id="workgMemberList"></div>