<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/consign/consignDatailForBuyer.js"></script> 
<div class="partContainers">
	<input type="hidden" value="${consign.consType }" id="consignType"/>
	<form:form id="consignForm" method="post" modelAttribute="consign">
		<div class="formLayout form2Pa">
		<h5 align="center"><span>委托协议详情</span></h5>
     		<ul>
					<li>
						<label class="short" for="consCode"><spring:message code="consignForm.consCode"/>：</label>
						<input type="hidden" value="${consign.objId }" id="objId" name="objId"/>
						<input type="hidden" value="${consign.useStatus }" id="useStatus" name="useStatus"/> 
						<input type="hidden" value="${consign.confirmStatus }" id="confirmStatus" name="confirmStatus"/> 
						<span>${consign.consCode }</span>
					</li>

					<li>
						<label class="short" for="consName"><spring:message code="consignForm.consName"/>：</label>
						<span >${consign.consName }</span> 
					</li>
					<li >
						<label class="short" for="consBuyerName"><spring:message code="consignForm.consBuyerName"/>：</label>
						<span >${consign.consBuyerName }</span> 
					</li>
					<li><label class="short" for="consType">委托类型：</label>
					<c:if test="${consign.consType==00}">
					<span>项目委托</span>
					</c:if>
					<c:if test="${consign.consType==01}">
					<span>长期委托</span>
					</c:if>
					</li>
					<li>
						<label class="short" for="consBuyerLinker"><spring:message code="consignForm.consBuyerLinker"/>：</label>
						<span >${consign.consBuyerLinker }</span> 
					</li>
					
					<li>
						<label class="short" for="consBuyerTel"><spring:message code="consignForm.consBuyerTel"/>：</label>
						<span >${consign.consBuyerTel }</span> 
					</li>

					<li id="dljg" class="fullLine">
						<label class="short" for="consAgent"><spring:message code="taskPlanForm.taskAgent"/>：</label>
						<span >${consign.consAgentName }</span>
						<input type="hidden" name="consAgent.objId" id="consAgent.objId" value="${consign.consAgent.objId }"/>
					</li>
					<li >
						<label class="short" for="consAgent"><spring:message code="consignForm.consAgentLinker"/>：</label>
						<span >${consign.consAgent.company.contact }</span>	
					</li>
					<li >
						<label class="short" for="consAgent"><spring:message code="consignForm.consAgentTel"/>：</label>
						<span >${consign.consAgent.company.tel }</span>
					</li>
					<li id="startTime">
						<label class="short" for="effectiveStartTime">有效期开始时间：</label>
						<span ><fmt:formatDate value="${consign.effectiveStartTime }" pattern="yyyy-MM-dd HH:mm:SS"/></span> 
					</li>
					<li id="endTime">
						<label class="short" for="effectiveEndTime">有效期结束时间：</label>
						<span ><fmt:formatDate value="${consign.effectiveEndTime }" pattern="yyyy-MM-dd HH:mm:SS"/></span> 
					</li>
					<li>
						<label class="short" for="consTime"><spring:message code="consignForm.consTime"/>：</label>
						<span ><fmt:formatDate value="${consign.consTime  }" pattern="yyyy-MM-dd HH:mm:SS"/></span> 
					</li>
					
					<li>
						<label class="short" for="consFinishTime"><spring:message code="consignForm.consFinishTime"/>：</label>
						<span ><fmt:formatDate value="${consign.consFinishTime }" pattern="yyyy-MM-dd HH:mm:SS"/></span> 
					</li>

		</ul>
	</div>
		<div class="conOperation">
			<button name="historyBackBtn" type="button" tabindex="20"><span><spring:message code="globe.return"/></span></button>
		</div>
	</form:form>
</div>	
<div id="consign_audit_list" class="hidden"></div>