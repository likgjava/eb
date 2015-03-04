<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/contract/contractAgencyForm.js"></script>
<%@ page import="com.gpcsoft.epp.common.domain.EbuyMethodEnum"%>
<div class="formLayout form2Pa">
<input type="hidden" value="${notDivide }" id="notDivide"/>
   <h4><span>手工录入合同</span></h4>
   <ul>
	<form id="contractForm" method="post" enctype="multipart/form-data">
	     	<li class="fullLine">
	     		<input type="hidden" name="cuyerId" id="cuyerId"/>
	     		<input type="hidden" name="cupplierId" id="cupplierId"/>
	     		<input type="hidden" name="agencyId" id="agencyId"  value="${agency.objId}"/>
	     		<input type="hidden" value="00" name="contractStatus" id="contractStatus"/> 
	     		<!-- 合同待提交状态00:保存,合同待确认状态:01:提交,合同被退回状态:02:退回 ,合同已确认状态:03:确认-->
	     		<label for="contractMethod">合同签订方式:</label>
						    <select id="contractMethod" name="contractMethod">
						    <option value="01">
						    	按包签订
						    </option>
						     <option value="02">
						     	 按采购计划条目签订
						    </option>
						     </select>
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li class="fullLine">
	     				<label for="ebuyMethod"><spring:message code="projectForm.ebuyMethod"/>:</label>
					<select name="ebuyMethod" id="ebuyMethod" class="required">
						<option value="<%=EbuyMethodEnum.OPEN_BIDDING%>">公开招标</option>
						<option value="<%=EbuyMethodEnum.INVITE_BIDDING%>">邀请招标</option>
						<option value="<%=EbuyMethodEnum.NEGOTIATE%>">竞争性谈判</option>
						<option value="<%=EbuyMethodEnum.SINGLE_SOURCE%>">单一来源</option>
						<option value="<%=EbuyMethodEnum.INQUIRY%>">询价</option>
					</select>
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
			     		<label for="">合同总金额 :</label>
							<input type="text" name="totalMoeny" id="totalMoeny" class="required" 
								      />
		    	   			<span class="eleRequired">*</span>
		     </li>
		     <li class="fullLine">
			     		<label for="contractMoney">履约保证金:</label>
							<input type="text" name="contractMoney" id="contractMoney" class="required" 
								      />
		    	   			<span class="eleRequired">*</span>
		     </li>
     	<li class="fullLine">
	     		<label for="">退保期限:</label>
					<input type="text" name="returnLimeitTime" id="returnLimeitTime" class="required" 
						      />
    	   			<span class="eleRequired">*</span>
     </li>
     	<li class="fullLine">
	     		<label for="">交(提)货期限 :</label>
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
         <li class="fullLine">
	     		<label for="">合同付款批次<font color="red"><b>(请输入正整数)</b></font></label>
					<input type="text" name="payTimes" id="payTimes" class="required" 
						      onblur="contractForm.payTimes()"/>
    	   			<span class="eleRequired">*</span>
    	 </li>
    	</form>
    	  <li class="fullLine">
	     		<label for="contractEn">合同附件:</label>
					<div id="attachRelaId" class="uploadFile"></div>
    	   </li>
     	</ul>
	<form id="contractPaymentApplyInfoForm">
	 <h4 id="pay"><span>合同付款批次</span></h4>
	 <div id="payTimesDiv">
	</div>
	</form>    	 	
	<form id="contractAcquirerForm" method="post">
	<h4><span>买方资料:</span></h4>
	<ul>
	<li class="fullLine">
	     		<label for="acquirer">买方:</label>
					<input type="text" name="acquirer" id="acquirer" class="required" 
						     value="" onclick="contractForm.showBuyerList(0)"/> 
    	   			<span class="eleRequired">*</span>
     </li>
     	<li class="fullLine">
	     		<label for="acquirerCorporate">买方代表:</label>
					<input type="text" name="acquirerCorporate" id="acquirerCorporate" class="required" 
						      />
    	   			<span class="eleRequired">*</span>
     </li>
     	<li class="fullLine">
	     		<label for="telephone">买方电话:</label>
					<input type="text" name="telephone" id="telephone" class="required" 
						      />
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
					<input type="text" name="supplierName" id="supplierName" class="required" 
						      value="" onclick="contractForm.showSupplierList(0)"/>
    	   			<span class="eleRequired">*</span>
     </li>
     	<li class="fullLine">
	     		<label for="supplierCorporate">卖方代表:</label>
					<input type="text" name="supplierCorporate" id="supplierCorporate" class="required" 
						      />
    	   			<span class="eleRequired">*</span>
     </li>
     	<li class="fullLine">
	     		<label for="">卖方电话:</label>
					<input type="text" name="supplierTel" id="supplierTel" class="required" 
						      />
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
						      value="${agency.orgName}"/>
    	   			<span class="eleRequired">${agency.orgName}</span>
     </li>
     	<li class="fullLine">
	     		<label for="agentCorporate">见证方代表:</label>
					<input type="text" name="agentCorporate" id="agentCorporate" class="required" 
						      value="${agency.company.contact}"/>
    	   			<span class="eleRequired">*</span>
     </li>
     	<li class="fullLine">
	     		<label for="">见证方电话:</label>
					<input type="text" name="tel" id="tel" class="required" 
						     value="${agency.company.tel}" />
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
				<button type="button" id="history" type="button" tabindex="20""><span>操作历史</span></button>
				<button type="button" id="return" type="button" tabindex="20""><span>返回</span></button>
		   </div>
	<div class="formTips">
      <ul>
          <li><spring:message code="globe.input.required.prompt"/></li>
      </ul>
    </div>