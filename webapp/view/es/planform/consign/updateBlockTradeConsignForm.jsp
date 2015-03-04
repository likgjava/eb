<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/consign/updateBlockTradeConsignForm.js"></script> 
<div class="partContainers">
	<form:form id="consignForm" method="post" modelAttribute="consign">
	    <input type="hidden" name="consignId" id="consignId" value="${consign.objId}"/>
	    <input type="hidden" name="consAgentName" id="consAgentName" value="${consign.consAgentName}"/>
	    <input type="hidden" name="consAgentLinker" id="consAgentLinker" value="${consign.consAgentLinker}"/>
	    <input type="hidden" name="consAgentTel" id="consAgentTel" value="${consign.consAgentTel}"/>
		<input type="hidden" name="project.objId" id="project.objId" value="${consign.project.objId}"/>
		<input type="hidden" name="consAgent.objId" id="consAgent.objId" value="${consign.consAgent.objId}"/>
		<input type="hidden" name="consBuyer.objId" id="consBuyer.objId" value="${consign.consBuyer.objId}"/>
		<input type="hidden" name="consBuyerName" id="consBuyerName" value="${consign.consBuyerName}"/>
		<input type="hidden" name="num" id="num" value="${num}"/>
		<form:hidden path="objId"></form:hidden> 
		<form:hidden path="confirmStatus"></form:hidden>
		<form:hidden path="useStatus"></form:hidden>
		<div class="formLayout form2Pa">
		<h5><span>委托协议</span></h5>
     		<ul>
					<li>
						<label for="consCode"><spring:message code="consignForm.consCode"/>：</label>
						<input type="text" name="consCode" value="${consign.consCode}" readonly="readonly" style="border: 0">
					</li>

					<li>
						<label for="consName"><spring:message code="consignForm.consName"/>：</label>
						<form:input path="consName" cssClass="required"/>
						<span class="eleRequired">*</span> 
					</li>
					
					<li>
						<label for="consBuyerLinker">采购办联系人：</label>
						<form:input path="consBuyerLinker" cssClass="required" readonly="true" disabled="true"/>
						<span class="eleRequired">*</span> 
					</li>
					
					<li>
						<label for="consBuyerTel">采购办联系电话：</label>
						<form:input path="consBuyerTel" cssClass="required cnPhone"/>
						<span class="eleRequired">*</span> 
					</li>
					<li class="fullLine">
					     <label for="input02">招标中心：</label>
				         <input type="text" name="consAgent.orgName" id="consAgent.orgName" value="${consign.consAgent.orgName}" class="required" />
				  		 <input type="radio" name="getOrgInfo" id="keyinOrgInfo" checked="checked"> 录入招标中心 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				  		 <input type="radio" name="getOrgInfo" id="selectOrgInfo"  > 抽取招标中心
					</li>
					<li>
						<label for="consTime"><spring:message code="consignForm.consTime"/>：</label>
						<form:input path="consTime" cssClass="required" readonly="readonly"/>
						<span class="eleRequired">*</span> 
					</li>

					<li>
						<label for="consFinishTime"><spring:message code="consignForm.consFinishTime"/>：</label>
						<form:input path="consFinishTime" cssClass="required" readonly="readonly"/>
						<span class="eleRequired">*</span> 
					</li>

					<li class="formTextarea">
						<label for="consOpinion"><spring:message code="consignForm.consOpinion"/>：</label>
						<textarea name="consOpinion" id="consOpinion" class="maxInput" maxlength="100" >${consign.consOpinion}</textarea>
					</li>
					
					<li class="formTextarea">
						<label for="consRemark"><spring:message code="consignForm.consRemark"/></label>
						<textarea name="consRemark" id="consRemark" class="maxInput" maxlength="250" >${consign.consRemark}</textarea>
					</li>
					
					<li class="fullLine">
						<label for="attachRelaId"><spring:message code="consignForm.consContentsAtt"/><br><span class="eleRequired">(必填项*)</span>：</label>
						<div id="attachRelaId" class="uploadFile">${consign.consContentsAtt}</div>
					</li>

		</ul>
	</div>
		<div class="conOperation">
			<button id="consignSave" type="button" tabindex="18"><span><spring:message code="globe.save"/></span></button>
			<button id="consignSubmit" type="button" tabindex="19"><span>提交确认</span></button>
			<button name="historyBackBtn" type="button" tabindex="20"><span><spring:message code="globe.return"/></span></button>
		</div>
	</form:form>
</div>	
    <div class="formLayout "> 
    <h5><span>申报书信息</span><button class="sysicon siAdd" onclick="consignForm.addTaskPlan();"></button></h5>  
    <table class="tableList" id="SubProjectList" >
  		<thead>
      		<tr>
          		<th class="center">申报书名称</th>
          		<th class="operation" align="center">操作</th>
     		</tr>
		</thead>
	<tbody>
	<c:forEach items="${taskPlanList}" var="taskPlan" >
		<tr>
			<td id="${taskPlan.objId}">
			<a href="#" onClick="consignForm.lookTaskPlan('${taskPlan.objId}')">${taskPlan.taskName}</a>
			<input type="hidden" name="taskPlans" value="${taskPlan.objId}"/>
			</td>
			<td align="center">
				<button class="sysicon siDelete" onclick="consignForm.deleteTaskPlan('${taskPlan.objId}','${consign.objId}');"><span>删除</span></button>
			</td>
		</tr>
	</c:forEach>
	</tbody>
    </table>   
	</div>
