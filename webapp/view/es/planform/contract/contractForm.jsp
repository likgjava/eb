<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/contract/contractForm.js"></script>
<div class="formLayout form2Pa">
<input type="hidden" value="${notDivide }" id="notDivide"/>
<input type="hidden" value="${taskPlanMSubListSize }" id="taskPlanMSubListSize"/>


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
	<c:set value="0" var="subProjectCount"/>
	<c:forEach items="${subProjectList}" var="subProject1" >
		<tr id="${subProject1.project.objId}">
			<td align="center" name="prokCodeForCheck"><input type="hidden" value="${subProject1.project.objId}" name="subProj" id="subProj"/>${subProject1.project.projCode }
			
			</td>
			<td align="center" name="projNameForCheck">${subProject1.project.projName }
			</td>
			<td align="center" name="quantity" class="center">${subProject1.quantity }
			</td>
			<td align="center" name="money" class="center">${subProject1.money }
			</td>
			<td align="center" class="center">
				<c:choose>
		      		<c:when test="${subProject1.tproject.ebuyMethod== '00'}">公开招标</c:when>
		      		<c:when test="${subProject1.tproject.ebuyMethod== '01'}">邀请招标</c:when>
		      		<c:when test="${subProject1.tproject.ebuyMethod== '02'}">竞争性谈判</c:when>
		      		<c:when test="${subProject1.tproject.ebuyMethod== '03'}">询价</c:when>
		      		<c:when test="${subProject1.tproject.ebuyMethod== '04'}">单一来源</c:when>
		      		<c:otherwise>未定</c:otherwise>
		      	</c:choose>
			</td>
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
			<td align="center" name="prokCodeForCheck"><input type="hidden" value="${project.objId}" name="subProj" id="subProj"/>${project.projCode }
			
			</td>
			<td align="center" name="projNameForCheck">${project.projName }
			</td>
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
    
    
    <div  id="div2">
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
		<c:forEach items="${taskPlanMSubList}" var="subProjectMTaskPlanSub" varStatus="i">
		<tr>
			<td class="center"><input type="checkbox" checked="checked" name="taskPlanSubId" value="${subProjectMTaskPlanSub.taskPlanSub.objId 

}"/>${subProjectMTaskPlanSub.taskPlanSub.budgetName}</td>
			<td class="center"><a href="#" onclick="projectInfoDetail.showDetail

('${subProjectMTaskPlanSub.taskPlan.objId}')">${subProjectMTaskPlanSub.taskPlan.taskCode}</a></td>
			<td class="center">${subProjectMTaskPlanSub.taskPlanSub.purchaseName}</td>
			<td class="center"><fmt:formatNumber value="${subProjectMTaskPlanSub.taskPlanSub.totalPrice}" type="currency"/></td>
		</tr>
		</c:forEach>
	</tbody>
    </table>
    </div>
   <h4><span>手工录入合同</span></h4>
   <ul>
	<form id="contractForm" method="post" enctype="multipart/form-data">
	     	<li class="fullLine">
	     	<input type="hidden" value="" name="subProjectId" id="subProjectId"/> 
	     	<input type="hidden" value="" name="taskPlanSubIds" id="taskPlanSubIds"/> 
	     		<input type="hidden" value="00" name="contractStatus" id="contractStatus"/> 
	     		<!-- 合同待提交状态00:保存,合同待确认状态:01:提交,合同被退回状态:02:退回 ,合同已确认状态:03:确认-->
	     		<label for="contractMethod">合同签订方式:</label>
	     		<input id="contractMethod" name="contractMethod" value="${contractMethod }" type="hidden">
    	   			<span>
    	   			<c:choose>
    	   			<c:when test="${notDivide == false}">
    	   			按<dm:out value="local__package" tenderType="${project.tenderType}">包组</dm:out>签订
    	   			</c:when>
    	   			<c:otherwise>
    	   		               按项目签订
    	   			</c:otherwise>
    	   			</c:choose>
    	   			</span>
    	    </li>
	     	<li class="fullLine">
	     		<label for="purchaseMethod">采购方式 :</label>
    	   			<span class="eleRequired">${project.ebuyMethodCN }</span>
    	    </li>
	     	<li class="fullLine">
	     		<label for="contracttTaskplanid">任务书执行情况:</label>
					<select id="contracttTaskplan" name="contracttTaskplan">
					<option value="01">已完成</option>
					<option value="02">暂未全部完成</option>				
					</select>
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li class="fullLine">
	     		<label for="contractName">合同名称:</label>
					<input type="text" name="contractName" id="contractName" class="required" 
						      />
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li class="fullLine">
	     		<label for="contractNo">项目(合同)编号:</label>
					<input type="hidden" name="contractNo" id="contractNo" class="required" 
						      value="${contractNo }"/>
    	   			<span>${contractNo }</span>
    	    </li>
    	      <li class="fullLine">
	     		<label for="acquirer">买方:</label>
	     			<input type="hidden" name="cuyerId" id="cuyerId"  value="${project.buyersId }"/>
    	   			<span >${project.buyersName}</span>
    	    </li>
    	      <li class="fullLine">
	     		<label for="supplierName">卖方:</label>
	     			<input type="hidden" name="cupplierId" id="cupplierId"  value="${supplier.objId }"/>
    	   			<span >${supplier.orgName }</span>
    	    </li>
	<li class="fullLine">
	     		<label for="">合同总金额（元） :</label>
					<input type="text" name="totalMoeny" id="totalMoeny" class="required" 
						      onkeyup="contractForm.showmoney('totalMoeny')"/>
    	   			<span class="eleRequired" >*</span>
    	   			<span id="viewtotalMoeny"></span>
     </li>
     	<li class="fullLine">
	     		<label for="contractMoney">履约保证金（元）:</label>
					<input type="text" name="contractMoney" id="contractMoney" class="required" 
						     onkeyup="contractForm.showmoney('contractMoney')" />
    	   			<span class="eleRequired">*</span>
    	   			<span id="viewcontractMoney"></span>
     </li>
     	<li class="fullLine">
	     		<label for="">退保期限（天）:</label>
					<input type="text" name="returnLimeitTime" id="returnLimeitTime" class="required" 
						      />
    	   			<span class="eleRequired">*</span>
     </li>
     	<li class="fullLine">
	     		<label for="">交(提)货期限（天） :</label>
					<input type="text" name="deliver" id="deliver" class="required" 
						      />
    	   			<span class="eleRequired">*</span>
     </li>
       	<li class="fullLine">
	     		<label for="">合同份数 :</label>
					<input type="text" name="totalCopies" id="totalCopies" class="required" 
						      />
    	   			<span class="eleRequired">*</span>
     </li>
    	</form>
    		<li class="fullLine">
	     		<label for="contractEn">合同附件:</label>
					<div id="attachRelaId" class="uploadFile"></div>
    	   </li>
    	  <li class="fullLine">
	     		<label for="">合同付款批次<font color="red"><b>(请输入正整数)</b></font></label>
					<input type="text" name="payTimes" id="payTimes" class="required digits" 
					 min="1"  max="99"	 onblur="contractForm.payTimes()"/>
    	   			<span class="eleRequired">*</span>
    	 </li>
     	</ul>
	<form id="contractPaymentApplyInfoForm">
	 <div id="payTimesDiv">
	</div>
	</form>    	 	
	<form id="contractAcquirerForm" method="post">
	<h4><span>买方资料:</span></h4>
	<ul>
	<li class="fullLine">
	     		<label for="acquirer">买方:</label>
					<input type="hidden" name="acquirer" id="acquirer" class="required" 
						     value="${projectMTaskPlan.buyMainBody.orgName}" /> 
    	   			<span >${projectMTaskPlan.buyMainBody.orgName}</span>
     </li>
     	<li class="fullLine">
	     		<label for="acquirerCorporate">买方代表:</label>
					<input type="text" name="acquirerCorporate" id="acquirerCorporate" class="required" 
						     value="${projectMTaskPlan.buyMainBody.company.contact }" />
    	   			<span class="eleRequired">*</span>
     </li>
     	<li class="fullLine">
	     		<label for="telephone">买方电话:</label>
					<input type="text" name="telephone" id="telephone" class="required" 
						       value="${projectMTaskPlan.buyMainBody.company.tel }" />
    	   			<span class="eleRequired">*</span>
     </li>
     	<li class="fullLine">
	     		<label for="">签订时间 :</label>
					<input type="text" name="acquirerSignedTime" id="acquirerSignedTime" class="required" 
						      />
    	   			<span class="eleRequired">*</span>
     </li>
     </ul>
	</form>
	<form id="contractSupplierForm" method="post">
	<h4><span>卖方资料:</span></h4>
	<ul>
	<li class="fullLine">
	     		<label for="supplierName">卖方:</label>
					<input type="hidden" name="supplierName" id="supplierName" class="required" 
						      value="${supplier.orgName }"/>
    	   			<span >${supplier.orgName }</span>
     </li>
     	<li class="fullLine">
	     		<label for="supplierCorporate">卖方代表:</label>
					<input type="text" name="supplierCorporate" id="supplierCorporate" class="required" 
						    value="${supplier.company.contact }"/>
    	   			<span class="eleRequired">*</span>
     </li>
     	<li class="fullLine">
	     		<label for="">卖方电话:</label>
					<input type="text" name="supplierTel" id="supplierTel" class="required" 
						      value="${supplier.company.tel }" />
    	   			<span class="eleRequired">*</span>
     </li>
     	<li class="fullLine">
	     		<label for="supplierSignedTime">签订时间 :</label>
					<input type="text" name="supplierSignedTime" id="supplierSignedTime" class="required" 
						      />
    	   			<span class="eleRequired">*</span>
     </li>
     </ul>
	</form>
<form id="witnessPartyForm" method="post">
	<h4><span>见证方资料:</span></h4>
	<ul>
	<li class="fullLine">
	     		<label for="agentName">见证方:</label>
					<input type="hidden" name="agentName" id="agentName" class="required" 
						      value="${project.agencies.orgName }"/>
    	   			<span >${project.agencies.orgName }</span>
     </li>
     	<li class="fullLine">
	     		<label for="agentCorporate">见证方代表:</label>
					<input type="text" name="agentCorporate" id="agentCorporate" class="required" 
						      value="${project.agencies.company.contact}"/>
    	   			<span class="eleRequired">*</span>
     </li>
     	<li class="fullLine">
	     		<label for="">见证方电话:</label>
					<input type="text" name="tel" id="tel" class="required" 
						      value="${project.agencies.company.tel}"/>
    	   			<span class="eleRequired">*</span>
     </li>
     	<li class="fullLine">
	     		<label for="">签订时间 :</label>
					<input type="text" name="signedTime" id="signedTime" class="required" 
						      />
    	   			<span class="eleRequired">*</span>
     </li>
     </ul>
	</form>
	</div>
	   <div class="conOperation">
				<button type="button" id="contractSave"><span><spring:message code="globe.save"/></span></button>
				<button type="button" name="contractSubmit" tabindex="19"" id="contractSubmit"><span>提交</span></button>
				<button type="button" id="history" type="button" tabindex="20" class="hidden"><span>操作历史</span></button>
				<button type="button" id="return" type="button" tabindex="20""><span>返回</span></button>
		   </div>
	<div class="formTips">
      <ul>
          <li><spring:message code="globe.input.required.prompt"/></li>
      </ul>
    </div>