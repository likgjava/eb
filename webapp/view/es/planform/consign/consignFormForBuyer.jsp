<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/consign/consignFormForBuyer.js"></script> 
<div class="partContainers">
	<form:form id="consignForm" method="post" modelAttribute="consign">
		<div class="formLayout form2Pa">
		<h5 align="center"><span>委托协议</span></h5>
     		<ul>
					<li>
						<label class="short" for="consCode"><spring:message code="consignForm.consCode"/>：</label>
						<input type="hidden" value="" id="useStatus" name="useStatus"/> 
						<input type="hidden" value="" id="confirmStatus" name="confirmStatus"/> 
						<input type="text" name="consCode"  value="${consignCode }" readonly="readonly" disabled="disabled"/>
					</li>

					<li>
						<label class="short" for="consName"><spring:message code="consignForm.consName"/>：</label>
						<input type="text" name="consName"  id="consName"/>
						<span class="eleRequired">*</span> 
					</li>
					<li >
						<label class="short" for="consBuyerName"><spring:message code="consignForm.consBuyerName"/>：</label>
						<input type="text" name="consBuyerName"  id="consBuyerName" value="${orgName }" disabled="disabled"/>
						<input  type="hidden" value="${consBuyer.objId }" name="consBuyer.objId"/>
						<span class="eleRequired">*</span> 
					</li>
					<li><label class="short" for="consType">委托类型：</label>
					<input type="radio" name="consType" value="00" checked="checked">项目委托
					<input type="radio" name="consType" value="01">长期委托
					</li>
					<li>
						<label class="short" for="consBuyerLinker"><spring:message code="consignForm.consBuyerLinker"/>：</label>
						<input type="text" name="consBuyerLinker"  id="consBuyerLinker" value="${consBuyerLinker }" disabled="disabled"/>
						<span class="eleRequired">*</span> 
					</li>
					
					<li>
						<label class="short" for="consBuyerTel"><spring:message code="consignForm.consBuyerTel"/>：</label>
						<input type="text" name="consBuyerTel"  id="consBuyerTel" value="${consBuyerTel }" disabled="disabled"/>
						<span class="eleRequired">*</span> 
					</li>

					<li id="dljg" class="fullLine">
						<label class="short" for="consAgent"><spring:message code="taskPlanForm.taskAgent"/>：</label>
						<input name="consAgentName" type="text" id="consAgentName"/>
						<span class="eleRequired">*</span>
						<input type="hidden" name="consAgent.objId" id="consAgent.objId"/>
						<input type="hidden" name="consAgentLinker" id="consAgentLinker"/>
						<input type="hidden" name="consAgentTel" id="consAgentTel"/>
					</li>
					
					<li id="startTime">
						<label class="short" for="effectiveStartTime">有效期开始时间：</label>
						<input type="text" name="effectiveStartTime"   id="effectiveStartTime" readonly="readonly"/>
						<span class="eleRequired">*</span> 
					</li>
					<li id="endTime">
						<label class="short" for="effectiveEndTime">有效期结束时间：</label>
						<input type="text" name="effectiveEndTime"   id="effectiveEndTime" readonly="readonly"/>
						<span class="eleRequired">*</span> 
					</li>
					<li>
						<label class="short" for="consTime"><spring:message code="consignForm.consTime"/>：</label>
						<input type="text" name="consTime"   id="consTime" readonly="readonly"/>
						<span class="eleRequired">*</span> 
					</li>
					
					<li>
						<label class="short" for="consFinishTime"><spring:message code="consignForm.consFinishTime"/>：</label>
						<input type="text" name="consFinishTime"   id="consFinishTime" readonly="readonly"/>
						<span class="eleRequired">*</span> 
					</li>

		</ul>
	</div>
		<div class="conOperation">
			<button id="consignSave" type="button" tabindex="18"><span><spring:message code="globe.save"/></span></button>
			<button id="consignSubmit" type="button" tabindex="19"><span>提交</span></button>
			<button name="historyBackBtn" type="button" tabindex="20"><span><spring:message code="globe.return"/></span></button>
		</div>
	</form:form>
</div>	
<div id="consign_audit_list" class="hidden"></div>