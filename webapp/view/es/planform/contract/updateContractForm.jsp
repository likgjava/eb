<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/view/es/planform/contract/updateContractForm.js"></script>
<div class="formLayout form2Pa">
<input type="hidden" value="${notDivide }" id="notDivide" />
<input type="hidden" id="fromType"  value="${param.fromType}"  >
<div id="div1">
<c:if test="${notDivide == false}">
<h4><span>未起草合同<dm:out value="local__package" tenderType="${project.tenderType}">包组</dm:out>(已中标的)</span></h4>
<table class="tableList" id="SubProjectList">
	<thead>
		<tr>
			<th class="center" width="15%"><dm:out value="local__package" tenderType="${project.tenderType}">包组</dm:out>编号</th>
			<th><dm:out value="local__package" tenderType="${project.tenderType}">包组</dm:out>名称</th>
			<th>拆分数量</th>
			<th>拆分预算（元）</th>
			<th width="15%">采购方式</th>
		</tr>
	</thead>
	<tbody>
		<c:set value="0" var="subProjectCount" />
		<c:forEach items="${subProjectList}" var="subProject1">
			<tr id="${subProject1.project.objId}">
				<td align="center" name="prokCodeForCheck"><input type="radio"
					name="subProj" value="${subProject1.project.objId}" class="hidden" />${subProject1.project.projCode
				}</td>
				<td align="center" name="projNameForCheck">${subProject1.project.projName
				}</td>
				<td align="center" name="quantity" class="center">${subProject1.quantity
				}</td>
				<td align="center" name="money" class="center">${subProject1.money
				}</td>
				<td align="center" class="center"><c:choose>
					<c:when test="${subProject1.tproject.ebuyMethod== '00'}">公开招标</c:when>
					<c:when test="${subProject1.tproject.ebuyMethod== '01'}">邀请招标</c:when>
					<c:when test="${subProject1.tproject.ebuyMethod== '02'}">竞争性谈判</c:when>
					<c:when test="${subProject1.tproject.ebuyMethod== '03'}">询价</c:when>
					<c:when test="${subProject1.tproject.ebuyMethod== '04'}">单一来源</c:when>
					<c:otherwise>未定</c:otherwise>
				</c:choose></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</c:if>


<c:if test="${notDivide == true}">
  <h4><span>未起草合同项目(已中标的)</span></h4>
  <table class="tableList" id="SubProjectList">	
  	<thead>
    		<tr>
        		<th class="center" width="15%">项目编号</th>
        		<th>项目名称</th>
        		<th width="15%">采购方式</th>
   			</tr>
	</thead>
  	<tbody>
  	<tr id="${project.objId}">
		<td align="center" name="prokCodeForCheck"><input type="hidden" value="${project.objId}" name="subProj" id="subProj"/>${project.projCode }</td>
		<td align="center" name="projNameForCheck">${project.projName }</td>
		<td align="center" class="center">
		<c:choose>
    		<c:when test="${project.ebuyMethod== '00'}">公开招标</c:when>
    		<c:when test="${project.ebuyMethod== '01'}">邀请招标</c:when>
    		<c:when test="${project.ebuyMethod== '02'}">竞争性谈判</c:when>
    		<c:when test="${project.ebuyMethod== '03'}">询价</c:when>
    		<c:when test="${project.ebuyMethod== '04'}">单一来源</c:when>
    		<c:otherwise>未定</c:otherwise>
    	</c:choose>
		</td>
	</tr>
  </tbody>
  </table>
  </c:if>



</div>
<div id="div2">
<h4><span>采购计划条目</span></h4>
<table class="tableList" id="SubProjectList">
	<thead>
		<tr class="center">
			<th>招标单位</th>
			<th>申报书编号</th>
			<th>品目名称</th>
			<th>预算（元）</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${taskPlanMSubList}" var="subProjectMTaskPlanSub"
			varStatus="i">
			<tr>
				<td class="center"><input type="checkbox" checked="checked" />${subProjectMTaskPlanSub.taskPlanSub.budgetName}</td>
				<td class="center"><a href="#"
					onclick="projectInfoDetail.showDetail('${subProjectMTaskPlanSub.taskPlan.objId}')">${subProjectMTaskPlanSub.taskPlan.taskCode}</a></td>
				<td class="center">${subProjectMTaskPlanSub.taskPlanSub.purchaseName}</td>
				<td class="center"><fmt:formatNumber
					value="${subProjectMTaskPlanSub.taskPlanSub.totalPrice}"
					type="currency" /></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</div>
<form id="contractForm" method="post" enctype="multipart/form-data">
<h4><span>手工录入合同</span></h4>
  <ul>
	<li class="fullLine"><input type="hidden" name="objId"
		value="${contract.objId }"> <input type="hidden" 
		name="projectId" value="${contract.projectId }"> <input
		type="hidden" name="contractStatus" id="contractStatus"
		value="${contract.contractStatus }" /> <input type="hidden"
		value="${contract.subProjectId }" name="subProjectId"
		id="subProjectId" /> <input type="hidden"
		value="${contract.taskPlanSubIds }" name="taskPlanSubIds"
		id="taskPlanSubIds" /> <!-- 合同待提交状态00:保存,合同待确认状态:01:提交,合同被退回状态:02:退回 ,合同已确认状态:03:确认-->
	<label for="contractMethod">合同签订方式:</label> <select id="contractMethod"
		name="contractMethod">
		<option value="01" <c:if test="${contract.contractMethod == '01' }">selected="selected"</c:if>>
		<c:choose>
    	   			<c:when test="${notDivide == false}">
    	   			按<dm:out value="local__package" tenderType="${project.tenderType}">包组</dm:out>签订
    	   			</c:when>
    	   			<c:otherwise>
    	   		               按项目签订
    	   			</c:otherwise>
    	   			</c:choose>
		
		</option>
	</select> <span class="eleRequired">*</span></li>
	<li class="fullLine"><label for="purchaseMethod">采购方式 :</label> <span
		class="eleRequired">${project.ebuyMethodCN }</span></li>
	<li class="fullLine"><label for="contracttTaskplanid">任务书执行情况:</label>
	<select id="contracttTaskplan" name="contracttTaskplan">
		<option value="01">已完成</option>
		<option value="02"
			<c:if test="${contract.contracttTaskplan == '02' }">selected="selected"</c:if>>暂未全部完成</option>
	</select> <span class="eleRequired">*</span></li>
	<li class="fullLine"><label for="contractName">合同名称:</label> <input
		type="text" name="contractName" id="contractName" class="required"
		value="${contract.contractName}" /> <span class="eleRequired">*</span>
	</li>
	<li class="fullLine"><label for="contractNo">项目(合同)编号:</label> <input
		type="text" name="contractNo" id="contractNo" class="required"
		value="${contract.contractNo}" /> <span class="eleRequired">*</span>
	</li>
	<li class="fullLine"><label for="acquirer">买方:</label> <input
		type="hidden" name="cuyerId" id="cuyerId" value="${project.buyersId }" />
	<input type="hidden" name="acquirer" id="acquirer" class="required"
		value="${project.buyersName}" /> <span class="">${project.buyersName}</span>
	</li>
	<li class="fullLine"><label for="supplierName">卖方:</label> <input
		type="hidden" name="cupplierId" id="cupplierId"
		value="${supplier.objId }" /> <input type="hidden"
		name="supplierName" id="supplierName" class="required"
		value="${supplier.orgName }" /> <span class="">${supplier.orgName
	}</span></li>
	<li class="fullLine"><label for="">合同总金额（元） :</label> <input
		type="text" name="totalMoeny" id="totalMoeny" class="required"
		value="${contract.totalMoeny}" /> <span class="eleRequired">*</span></li>
	<li class="fullLine"><label for="contractMoney">履约保证金（元） :</label> <input
		type="text" name="contractMoney" id="contractMoney" class="required"
		value="${contract.contractMoney}" /> <span class="eleRequired">*</span>
	</li>
	<li class="fullLine"><label for="">退保期限（天）:</label> <input
		type="text" name="returnLimeitTime" id="returnLimeitTime"
		class="required" value="${contract.returnLimeitTime}" /> <span
		class="eleRequired">*</span></li>
	<li class="fullLine"><label for="">交(提)货期限 （天）:</label> <input
		type="text" name="deliver" id="deliver" class="required"
		value="${contract.deliver}" /> <span class="eleRequired">*</span></li>
	<li class="fullLine"><label for="">合同份数 :</label> <input
		type="text" name="totalCopies" id="totalCopies" class="required"
		value="${contract.totalCopies}" /> <span class="eleRequired">*</span>
	</li>
	
	<li class="fullLine"><label for="contractEn">合同附件:</label>
	<div id="attachRelaId" class="uploadFile">${contract.attachRelaId}</div>
	</li>
	<li class="fullLine"><label for="">合同付款方式(批次)<font
		color="red"><b>(请输入正整数)</b></font></label> <input type="text" name="payTimes"
		id="payTimes" class="required digits" onblur="contractForm.payTimes()"  min="1"  max="99"
		value="${contract.payTimes}" /> <span class="eleRequired">*</span></li>
</ul>
</form>
<form id="contractPaymentApplyInfoForm">
<div id="payTimesDiv">
<c:forEach items="${applyInfoList}"
	var="applyInfo">
	<li class='fullLine'><label for=''>${applyInfo.theTimes }: </label><label for=''><input
		type='hidden' name='theTimes${applyInfo.theTimes }' value='${applyInfo.theTimes }' />支付时间: </label><input
		type='text' name='applyDate${applyInfo.theTimes }' id='applyDate${applyInfo.theTimes }' value="<fmt:formatDate value="${applyInfo.applyDate }" pattern="yyyy-MM-dd" />" str='times'/><span></span><label
		for=''>支付金额: </label><input type='text' name='thisTimePaymentAmt${applyInfo.theTimes }'
		id='thisTimePaymentAmt${applyInfo.theTimes }'
		onkeyup='contractForm.showmoney("thisTimePaymentAmt${applyInfo.theTimes }")' onblur='contractForm.totalMoney()' value="${applyInfo.thisTimePaymentAmt}"/><span
		id='viewthisTimePaymentAmt${applyInfo.theTimes }'></span></li>
</c:forEach></div>
</form>
<form id="contractAcquirerForm" method="post">
<h4><span>买方资料:</span></h4>
<ul>
	<li class="fullLine"><label for="acquirer">买方:</label> <input
		type="hidden" name="objId" value="${contract.partyA.objId }" /> <input
		type="text" name="acquirer" id="acquirer" class="required"
		value="${contract.partyA.acquirer}" /> <span class="eleRequired">*</span>
	</li>
	<li class="fullLine"><label for="acquirerCorporate">买方代表:</label>
	<input type="text" name="acquirerCorporate" id="acquirerCorporate"
		class="required" value="${contract.partyA.acquirerCorporate}" /> <span
		class="eleRequired">*</span></li>
	<li class="fullLine"><label for="telephone">买方电话:</label> <input
		type="text" name="telephone" id="telephone" class="required"
		value="${contract.partyA.telephone}" /> <span class="eleRequired">*</span>
	</li>
	<li class="fullLine"><label for="">签订时间 :</label> <input
		type="text" name="acquirerSignedTime" id="acquirerSignedTime"
		class="required" value="${contract.partyA.acquirerSignedTime}" /> <span
		class="eleRequired">*</span></li>
</ul>
</form>
<form id="contractSupplierForm" method="post">
<h4><span>卖方资料:</span></h4>
<ul>
	<li class="fullLine"><label for="supplierName">卖方:</label> <input
		type="hidden" name="objId" value="${contract.secondParty.objId }" />
	<input type="text" name="supplierName" id="supplierName"
		class="required" value="${contract.secondParty.supplierName}" /> <span
		class="eleRequired">*</span></li>
	<li class="fullLine"><label for="supplierCorporate">卖方代表:</label>
	<input type="text" name="supplierCorporate" id="supplierCorporate"
		class="required" value="${contract.secondParty.supplierCorporate}" />
	<span class="eleRequired">*</span></li>
	<li class="fullLine"><label for="">卖方电话:</label> <input
		type="text" name="supplierTel" id="supplierTel" class="required"
		value="${contract.secondParty.supplierTel}" /> <span
		class="eleRequired">*</span></li>
	<li class="fullLine"><label for="supplierSignedTime">签订时间
	:</label> <input type="text" name="supplierSignedTime" id="supplierSignedTime"
		class="required" value="${contract.secondParty.supplierSignedTime}" />
	<span class="eleRequired">*</span></li>
</ul>
</form>
<form id="witnessPartyForm" method="post">
<h4><span>见证方资料:</span></h4>
<ul>
	<li class="fullLine"><label for="agentName">见证方:</label> <input
		type="hidden" name="objId" value="${contract.witenessParty.objId }" />
	<input type="text" name="agentName" id="agentName" class="required"
		value="${contract.witenessParty.agentName}" /> <span
		class="eleRequired">*</span></li>
	<li class="fullLine"><label for="agentCorporate">见证方代表:</label> <input
		type="text" name="agentCorporate" id="agentCorporate" class="required"
		value="${contract.witenessParty.agentCorporate}" /> <span
		class="eleRequired">*</span></li>
	<li class="fullLine"><label for="">见证方电话:</label> <input
		type="text" name="tel" id="tel" class="required"
		value="${contract.witenessParty.tel}" /> <span class="eleRequired">*</span>
	</li>
	<li class="fullLine"><label for="">签订时间 :</label> <input
		type="text" name="signedTime" id="signedTime" class="required"
		value="${contract.witenessParty.signedTime}" /> <span
		class="eleRequired">*</span></li>
</ul>
</form>
</div>
<div class="conOperation">
<button type="button" name="contractSubmit" tabindex="19"
	id="contractSave"><span>保存</span></button>
<button type="button" name="contractSubmit" tabindex="19"
	id="contractSubmit"><span>提交</span></button>
<button type="button" id="history" type="button" tabindex="20""><span>操作历史</span></button>
<button type="button" id="return" type="button" tabindex="20""><span>返回</span></button>
</div>
<div class="formTips">
<ul>
	<input type="hidden" value="${fromType}" id="fromType"/>
	<li><spring:message code="globe.input.required.prompt" /></li>
</ul>
</div>