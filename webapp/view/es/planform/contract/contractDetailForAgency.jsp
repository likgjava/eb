<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/contract/contractDetailForAgency.js"></script>
<div class="formLayout form2Pa">
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
    	   			<c:when test="${contract.contractMethod==01}">按包签订</c:when>
    	   			<c:otherwise>按采购计划条目签订</c:otherwise>
    	   			</c:choose>
    	   			</span>
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
    	   			<span class="eleRequired">${contract.contractName}</span>
    	    </li>
	     	<li class="short">
	     		<label for="contractNo">项目(合同)编号:</label>
    	   			<span class="eleRequired">${contract.contractNo}</span>
    	    </li>
	<li class="short">
	     		<label for="">合同总金额 (元):</label>
    	   			<span class="eleRequired">${contract.totalMoeny}</span>
     </li>
     	<li class="short">
	     		<label for="contractMoney">履约保证金(元):</label>
    	   			<span class="eleRequired">${contract.contractMoney}</span>
     </li>
     	<li class="short">
	     		<label for="">退保期限:</label>
    	   			<span class="eleRequired">${contract.returnLimeitTime}</span>
     </li>
     	<li class="short">
	     		<label for="">交(提)货期限 :</label>
    	   			<span class="eleRequired">${contract.deliver}</span>
     </li>
       	<li class="short">
	     		<label for="">合同份数 :</label>
    	   			<span class="eleRequired">${contract.totalCopies}</span>
     </li>
        <li class="fullLine">
	     		<label for="contractEn">合同附件:</label>
					<b><div id="attachRelaId" class="fileDown">${contract.attachRelaId}</div></b>
    	   </li>
         <li class="fullLine">
	     		<label for="">合同付款方式(批次)</label>
    	   			<span class="eleRequired">${contract.payTimes}</span>
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
					<td>${applyInfo.thisTimePaymentAmt}</td>
					<td> <fmt:formatDate value="${applyInfo.applyDate }" pattern="yyyy年MM月dd日" /></td>
				 </tr>
	 		 		</c:forEach>
			</table >
    	 </li>
     </ul>
	</form>
	<form id="contractAcquirerForm" method="post">
	<h4><span>买方资料:</span></h4>
	<ul>
	<li class="short">
	     		<label for="acquirer">买方:</label>
    	   			<span class="eleRequired">${contract.partyA.acquirer}</span>
     </li>
     	<li class="short">
	     		<label for="acquirerCorporate">买方代表:</label>
						<span class="eleRequired">${contract.partyA.acquirerCorporate}</span>      
     </li>
     	<li class="short">
	     		<label for="telephone">买方电话:</label>
						   <span class="eleRequired">${contract.partyA.telephone}</span>   
     </li>
     	<li class="short">
	     		<label for="">签订时间 :</label>
						   <span class="eleRequired">${contract.partyA.acquirerSignedTime}</span>   
     </li>
     </ul>
	</form>
	<form id="contractSupplierForm" method="post">
	<h4><span>卖方资料:</span></h4>
	<ul>
	<li class="short">
	     		<label for="supplierName">卖方:</label>
    	   			<span class="eleRequired">${contract.secondParty.supplierName}</span>
     </li>
     	<li class="short">
	     		<label for="supplierCorporate">卖方代表:</label>
    	   			<span class="eleRequired">${contract.secondParty.supplierCorporate}</span>
     </li>
     	<li class="short">
	     		<label for="">卖方电话:</label>
    	   			<span class="eleRequired">${contract.secondParty.supplierTel}</span>
     </li>
     	<li class="short">
	     		<label for="supplierSignedTime">签订时间 :</label>
    	   			<span class="eleRequired">${contract.secondParty.supplierSignedTime}</span>
     </li>
     </ul>
	</form>
<form id="witnessPartyForm" method="post">
	<h4><span>见证方资料:</span></h4>
	<ul>
	<li class="short">
	     		<label for="agentName">见证方:</label>
						 <span class="eleRequired">${contract.witenessParty.agentName}</span>
     </li>
     	<li class="short">
	     		<label for="agentCorporate">见证方代表:</label>
    	   		<span class="eleRequired">${contract.witenessParty.agentCorporate}</span>
     </li>
     	<li class="short">
	     		<label for="">见证方电话:</label>
    	   			<span class="eleRequired">${contract.witenessParty.tel}</span>
     </li>
     	<li class="short">
	     		<label for="">签订时间 :</label>
    	   			<span class="eleRequired">${contract.witenessParty.signedTime}</span>
     </li>
     </ul>
	</form>
	</div>
	   <div class="conOperation">
				<button type="button" id="return" type="button" tabindex="20""><span>返回</span></button>
		   </div>
