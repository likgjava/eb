<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/projreview/evalBidRecordForm.js"></script>
<div class="formLayout form2Pa">        
	<form id="evalBidRecordForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<input type="hidden" name="taskId" id="taskId" value="<c:out value="${taskId}"/>"/>
		<input type="hidden" name="project.objId" id="project.objId" value="${project.objId}"/>
		<input type="hidden" name="projectId" id="projectId" value="${project.objId}"/>
     		<ul>

					<!--<li>
						<label for="subProjId"><spring:message code="evalBidRecordForm.subProjId"/></label>
						<input type="text" name="subProjId" id="subProjId" 
									class="required"
							  />
							<span class="eleRequired">*</span>
					</li>

					-->
						<li>
						<label for="evalLinker"><spring:message code="evalBidRecordForm.evalLinker"/></label>
						<input type="text" name="evalLinker" id="evalLinker" 
									class="required"
							  />
							<span class="eleRequired">*</span>
					</li>
                     <li>
					  <label style="background-color: transparent;">&nbsp</label>
					</li>
				
					<li style="height:120px">
						<label for="evalOpinion"><spring:message code="evalBidRecordForm.evalOpinion"/></label>
						<textarea id="evalOpinion"  name="evalOpinion" style="width:300px;height:90px" class="required"></textarea>
					
							<span class="eleRequired">*</span>
					</li>

		</ul>
		<div class="conOperation">
			<button id="evalBidRecordSave" type="button" tabindex="18"><span><spring:message code="globe.save"/></span></button>
			<button id="evalBidRecordReturn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
		</div>
	</form>
</div>