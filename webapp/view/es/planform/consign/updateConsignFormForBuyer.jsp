<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/consign/updateConsignFormForBuyer.js"></script> 
<div class="partContainers">
	<input type="hidden" value="${consign.consType }" id="consignType"/>
	<input type="hidden" value="${consign.objId }" id="objId" name="objId"/>
	<form:form id="consignForm" method="post" modelAttribute="consign">
		<div class="formLayout form2Pa">
		<h5 align="center"><span>委托协议</span></h5>
     		<ul>
					<li>
						<label class="short" for="consCode"><spring:message code="consignForm.consCode"/>：</label>
						<input type="hidden" value="${consign.useStatus }" id="useStatus" name="useStatus"/> 
						<input type="hidden" value="${consign.confirmStatus }" id="confirmStatus" name="confirmStatus"/> 
						<input type="text" name="consCode"  value="${consign.consCode }" readonly="readonly" disabled="disabled"/>
					</li>

					<li>
						<label class="short" for="consName"><spring:message code="consignForm.consName"/>：</label>
						<input type="text" name="consName"  id="consName" value="${consign.consName }"/>
						<span class="eleRequired">*</span> 
					</li>
					<li >
						<label class="short" for="consBuyerName"><spring:message code="consignForm.consBuyerName"/>：</label>
						<input type="text" name="consBuyerName"  id="consBuyerName" value="${consign.consBuyerName }" disabled="disabled"/>
						<input  type="hidden" value="${consign.consBuyer.objId}" name="consBuyer.objId"/>
						<span class="eleRequired">*</span> 
					</li>
					<li><label class="short" for="consType">委托类型：</label>
					
					<input type="radio" name="consType" value="00" <c:if test="${consign.consType==00}">checked="checked"</c:if>>项目委托
					<input type="radio" name="consType" value="01" <c:if test="${consign.consType==01}">checked="checked"</c:if>>长期委托
					</li>
					<li>
						<label class="short" for="consBuyerLinker"><spring:message code="consignForm.consBuyerLinker"/>：</label>
						<input type="text" name="consBuyerLinker"  id="consBuyerLinker" value="${consign.consBuyerLinker }" disabled="disabled"/>
						<span class="eleRequired">*</span> 
					</li>
					
					<li>
						<label class="short" for="consBuyerTel"><spring:message code="consignForm.consBuyerTel"/>：</label>
						<input type="text" name="consBuyerTel"  id="consBuyerTel" value="${consign.consBuyerTel }" disabled="disabled"/>
						<span class="eleRequired">*</span> 
					</li>

					<li id="dljg" class="fullLine">
						<label class="short" for="consAgent"><spring:message code="taskPlanForm.taskAgent"/>：</label>
						<input name="consAgentName" type="text" id="consAgentName" value="${consign.consAgentName }"/>
						<span class="eleRequired">*</span>
						<input type="hidden" name="consAgent.objId" id="consAgent.objId" value="${consign.consAgent.objId }"/>
					</li>
					<li id="startTime">
						<label class="short" for="effectiveStartTime">有效期开始时间：</label>
						<input type="text" name="effectiveStartTime"   id="effectiveStartTime" readonly="readonly" value="<fmt:formatDate value="${consign.effectiveStartTime }" pattern="yyyy-MM-dd HH:mm:SS"/>"/>
						<span class="eleRequired">*</span> 
					</li>
					<li id="endTime">
						<label class="short" for="effectiveEndTime">有效期结束时间：</label>
						<input type="text" name="effectiveEndTime"   id="effectiveEndTime" readonly="readonly" value="<fmt:formatDate value="${consign.effectiveEndTime }" pattern="yyyy-MM-dd HH:mm:SS"/>"/>
						<span class="eleRequired">*</span> 
					</li>
				
					<li>
						<label class="short" for="consTime"><spring:message code="consignForm.consTime"/>：</label>
						<input type="text" name="consTime"   id="consTime" readonly="readonly" value="<fmt:formatDate value="${consign.consTime  }" pattern="yyyy-MM-dd HH:mm:SS"/>"/>
						<span class="eleRequired">*</span> 
					</li>
					
					<li>
						<label class="short" for="consFinishTime"><spring:message code="consignForm.consFinishTime"/>：</label>
						<input type="text" name="consFinishTime"   id="consFinishTime" readonly="readonly" value="<fmt:formatDate value="${consign.consFinishTime }" pattern="yyyy-MM-dd HH:mm:SS"/>"/>
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