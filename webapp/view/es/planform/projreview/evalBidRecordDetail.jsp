<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/projreview/evalBidRecordDetail.js"></script>
<div class="formLayout form2Pa">        
	<form id="evalBidRecordForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<input type="hidden" name="taskId" id="taskId" value="<c:out value="${taskId}"/>"/>
		<input type="hidden" name="project.objId" id="project.objId" value="${param.projectId}"/>
		<input type="hidden" name="projectId" id="projectId" value="${project.objId}"/>
     		<ul>

				<li>
						<label for="evalLinker"><spring:message code="evalBidRecordForm.evalLinker"/></label>
						<input type="text" name="evalLinker" id="evalLinker" style="border-width:0px;background-color:transparent;"
									readonly="readonly"
									
							  />
							
					</li>
                     <li>
					  <label style="background-color: transparent;">&nbsp</label>
					</li>
				
					<li style="height:120px">
						<label for="evalOpinion"><spring:message code="evalBidRecordForm.evalOpinion"/></label>
						<textarea id="evalOpinion"  name="evalOpinion" style="width:300px;height:90px" style="border-width:0px;background-color:transparent;"
									readonly="readonly"></textarea>
					
							
					</li>
					

		</ul>
		<div class="conOperation">
		<button id="evalBidRecordReturn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
		</div>
	</form>
</div>
