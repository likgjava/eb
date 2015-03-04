<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/requirement/purRequirementDraft.js"></script>
<div class="partContainers">
	<div class="formLayout form2Pa">        
		<form id="purRequirementForm" method="post">
			<input type="hidden" name="taskPlanId" id="taskPlanId" value="<c:out value="${param.objId}"/>"/>
			<input type="hidden" name="objId" id="objId" />
			<input type="hidden" name="useStatus" id="useStatus" value="01"/>
	     		<h5><span>采购需求</span></h5>
	     		<ul>
						<li class="fullLine">
							<label for="name"><spring:message code="purRequirementForm.name"/>：</label>
							<input type="text" name="name" id="name" 
										class="required"
								  />
								<span class="eleRequired">*</span>
						</li>
						<li class="formTextarea">
							<label for="preqAbout"><spring:message code="purRequirementForm.preqAbout"/>：</label>
							<textarea id="preqAbout"  name="preqAbout"></textarea>
						</li>
						<li class="fullLine">
							<label for="requireAtt"><spring:message code="purRequirementForm.requireAtt"/>：</label>
							<div id="requireFile" class="uploadFile"></div>
						</li>
	
			</ul>
			<div class="conOperation">
				<button id="purRequirementSave" type="button" tabindex="18"><span><spring:message code="globe.save"/></span></button>
				<button name="historyBackBtn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
			</div>
		</form>
	</div>
</div>
<div id="preqEntryList"></div>