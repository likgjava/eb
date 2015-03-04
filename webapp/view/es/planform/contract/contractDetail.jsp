<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/contract/contractDetail.js"></script>
<input type="hidden" value="${notDivide }" id="notDivide"/>
<div class="formLayout form2Pa">
<c:choose>
<c:when test="${contract.contractMethod==01}">
<div id="div1">
<c:if test="${notDivide == false}">
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
			<td align="center" name="prokCodeForCheck"><input type="hidden" name="subProj" value="${subProject1.project.objId}"/>${subProject1.project.projCode }
			</td>
			<td align="center" name="projNameForCheck">${subProject1.project.projName }
			</td>
			<td align="center" name="quantity" class="center">${subProject1.quantity }
			</td>
			<td align="center" name="money" class="center"><fmt:formatNumber value="${subProject1.money }" type="currency"/>
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
    </c:when>
    <c:otherwise>
    <div  id="div2">
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
			<td class="center"><input type="hidden" value="${subProjectMTaskPlanSub.taskPlanSub.objId}"/>${subProjectMTaskPlanSub.taskPlanSub.budgetName}</td>
			<td class="center"><a href="#" onclick="projectInfoDetail.showDetail('${subProjectMTaskPlanSub.taskPlan.objId}')">${subProjectMTaskPlanSub.taskPlan.taskCode}</a></td>
			<td class="center">${subProjectMTaskPlanSub.taskPlanSub.purchaseName}</td>
			<td class="center"><fmt:formatNumber value="${subProjectMTaskPlanSub.taskPlanSub.totalPrice}" type="currency"/></td>
		</tr>
		</c:forEach>
	</tbody>
    </table>
    </div>
    </c:otherwise>
   </c:choose>
	<form id="contractForm" method="post" enctype="multipart/form-data">
     	<h4><span>手工录入合同</span></h4>
     	<ul>
	     	<li class="short">
	     	
	     		<input type="hidden" name="objId" id="objId" value="${contract.objId }">
	     		<input type="hidden" value="${contract.contractStatus }" name="contractStatus" id="contractStatus"/> 
	     		<!-- 合同待提交状态00:保存,合同待确认状态:01:提交,合同被退回状态:02:退回 ,合同已确认状态:03:确认-->
	     		<label for="contractMethod">合同签订方式:</label>
    	   			<span >
    	   			<c:choose>
    	   			<c:when test="${notDivide == false}">按包签订</c:when>
    	   			<c:otherwise>按项目签订</c:otherwise>
    	   			</c:choose>
    	   			</span>
    	    </li>
	     	<li class="short">
	     		<label for="purchaseMethod">采购方式 :</label>
    	   			<span >${project.ebuyMethodCN }</span>
    	    </li>
	     	<li class="short">
	     		<label for="contracttTaskplanid">任务书执行情况:</label>
    	   		<span >
    	   			<c:choose>
    	   			<c:when test="${contract.contracttTaskplan==01}">已完成</c:when>
    	   			<c:otherwise>暂未全部完成</c:otherwise>
    	   			</c:choose>
    	   			</span>
    	    </li>
	     	<li class="short">
	     		<label for="contractName">合同名称:</label>
    	   			<span class="">${contract.contractName}</span>
    	    </li>
	     	<li class="fullLine">
	     		<label for="contractNo">项目(合同)编号:</label>
    	   			<span class="">${contract.contractNo}</span>
    	    </li>
	<li class="short">
	     		<label for="">合同总金额(元) :</label>
    	   			<span class=""><fmt:formatNumber value="${contract.totalMoeny}" type="currency"/></span>
     </li>
     	<li class="short">
	     		<label for="contractMoney">履约保证金(元):</label>
    	   			<span class=""><fmt:formatNumber value="${contract.contractMoney}" type="currency"/></span>
     </li>
     	<li class="short">
	     		<label for="">退保期限（天）:</label>
    	   			<span class="">${contract.returnLimeitTime}</span>
     </li>
     	<li class="short">
	     		<label for="">交(提)货期限 （天）:</label>
    	   			<span class="">${contract.deliver}</span>
     </li>
       	<li class="short">
	     		<label for="">合同份数 :</label>
    	   			<span class="">${contract.totalCopies}</span>
     </li>
        <li class="fullLine">
	     		<label for="contractEn">合同附件:</label>
					<b><div id="attachRelaId" class="fileDown">${contract.attachRelaId}</div></b>
    	   </li>
         <li class="fullLine">
	     		<label for="">合同付款方式(批次)</label>
    	   			<span class="">${contract.payTimes}</span>
    	 </li>
    	 	 <li class="fullLine">
    	 	 	<table style="width: 60%">
				  <tr>
				    <th style="text-align: center">批次</th>
				    <th style="text-align: center">本次付款金额(元)</th>
				     <th style="text-align: center">付款日期</th>
				  </tr>
				   <c:forEach items="${applyInfoList}" var="applyInfo">
				 <tr>
					<td>${applyInfo.theTimes }</td>
					<td><fmt:formatNumber value="${applyInfo.thisTimePaymentAmt}" type="currency"/></td>
					<td><fmt:formatDate value="${applyInfo.applyDate}" pattern="yyyy年MM月dd日" /></td>
				 </tr>
	 		 		</c:forEach>
			</table >
    	 </li>
     </ul>
	</form>
	<form id="contractAcquirerForm" method="post">
	<h4><span>买方资料:</span></h4>
	<ul>
	<li class="fullLine">
	     		<label for="acquirer">买方:</label>
    	   			<span class="">${contract.partyA.acquirer}</span>
     </li>
     	<li class="fullLine">
	     		<label for="acquirerCorporate">买方代表:</label>
						<span class="">${contract.partyA.acquirerCorporate}</span>      
     </li>
     	<li class="fullLine">
	     		<label for="telephone">买方电话:</label>
						   <span class="">${contract.partyA.telephone}</span>   
     </li>
     	<li class="fullLine">
	     		<label for="">签订时间 :</label>
						   <span class="">${contract.partyA.acquirerSignedTime}</span>   
     </li>
     </ul>
	</form>
	<form id="contractSupplierForm" method="post">
	<h4><span>卖方资料:</span></h4>
	<ul>
	<li class="fullLine">
	     		<label for="supplierName">卖方:</label>
    	   			<span class="">${contract.secondParty.supplierName}</span>
     </li>
     	<li class="fullLine">
	     		<label for="supplierCorporate">卖方代表:</label>
    	   			<span class="">${contract.secondParty.supplierCorporate}</span>
     </li>
     	<li class="fullLine">
	     		<label for="">卖方电话:</label>
    	   			<span class="">${contract.secondParty.supplierTel}</span>
     </li>
     	<li class="fullLine">
	     		<label for="supplierSignedTime">签订时间 :</label>
    	   			<span class="">${contract.secondParty.supplierSignedTime}</span>
     </li>
     </ul>
	</form>
<form id="witnessPartyForm" method="post">
	<h4><span>见证方资料:</span></h4>
	<ul>
	<li class="fullLine">
	     		<label for="agentName">见证方:</label>
						 <span class="">${contract.witenessParty.agentName}</span>
     </li>
     	<li class="fullLine">
	     		<label for="agentCorporate">见证方代表:</label>
    	   		<span class="">${contract.witenessParty.agentCorporate}</span>
     </li>
     	<li class="fullLine">
	     		<label for="">见证方电话:</label>
    	   			<span class="">${contract.witenessParty.tel}</span>
     </li>
     	<li class="fullLine">
	     		<label for="">签订时间 :</label>
    	   			<span class="">${contract.witenessParty.signedTime}</span>
     </li>
     </ul>
	</form>
	</div>
	   <div class="conOperation">
	  			 <input type="hidden" value="${fromType}" id="fromType"/>
				<button type="button" id="return" type="button" tabindex="20""><span>返回</span></button>
		   </div>
