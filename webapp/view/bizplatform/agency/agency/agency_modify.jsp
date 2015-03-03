<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<form:form id="AgencyInfoForm" method="post" modelAttribute="agency">
	<input type="hidden" name="objId" id="agencyId" value="<c:out value='${param.agencyId}'/>"> 
	<input type="hidden" id="agencyAuditStatus" value="${agency.auditStatus}" />
	
	<div class="formLayout form2Pa">
    <ul>
    	<li class="fullLine">
             <label for="input01">代理机构类型：</label>
             <html:select selectedValue="${agency.agentType}" id="agentType" name="agentType" code="biz.agent.agentType"></html:select>
        </li>
        <li class="fullLine">
             <label for="input01">企业类型：</label>
             <html:select selectedValue="${agency.unitType}" id="unitType" name="unitType" code="biz.agent.unitType"></html:select>
        </li>
        <li class="fullLine">
            <label for="input02">主管单位：</label>
            <input type="text" name="supervisor" id="supervisor" size="60"/>
			<input type="hidden" id="supervisorId" name="unitInCharge.objId"/>
        </li>
	    <li class="fullLine">
           	<label for="input02">开户银行名称：</label>
           	<input type="text" name="openBank" id="openBank" maxlength="50" value="${agency.openBank}" class="required" size="60"/>
           	<span class="eleRequired">*</span> 
       	</li>
        <li class="fullLine">
            <label for="input02">开户银行账号：</label>
        	<input type="text" name="openAccount" id="openAccount" maxlength="50" value="${agency.openAccount}" class="required digits" size="60"/>
            <span class="eleRequired">*</span> 
        </li>
        <li class="fullLine">
            <label for="input02">开户账号姓名：</label>
        	<input type="text" name="openAccountName" id="openAccountName" maxlength="50" value="${agency.openAccountName}" class="required" size="60"/>
            <span class="eleRequired">*</span> 
        </li>
        <li class="fullLine">
            <label for="input02">网址：</label>
         	<input type="text" name="webUrl" id="webUrl" class="url" maxlength="50" value="${agency.webUrl}" size="60"/>（请以http:// 开头）
            <span class="eleRequired"></span> 
        </li>
        <li class="fullLine">
            <label for="input02">评标地址：</label>
         	<input type="text" name="bidPrpsEvltAddr" id="bidPrpsEvltAddr" maxlength="100" value="${agency.bidPrpsEvltAddr}" size="60"/>
        </li>
        <li class="fullLine">
            <label for="input02">流动资产(万元)：</label>
         	<input type="text" name="crntAst" id="crntAst" maxlength="8" class="money" value="<fmt:formatNumber value="${agency.crntAst}" pattern="####" />" size="60"/>
        </li>
        <li class="fullLine">
            <label for="input02">固定资产年折旧额(万元)：</label>
         	<input type="text" name="fixedAstDprcYr" id="fixedAstDprcYr" maxlength="8" class="money" value="<fmt:formatNumber value="${agency.fixedAstDprcYr}" pattern="####" />" size="60"/>
        </li>
        <li class="fullLine">
            <label for="input02">高级职称技术人员数：</label>
         	<input type="text" name="highTitleTchstNmbr" id="highTitleTchstNmbr" class="digits" value="${agency.highTitleTchstNmbr}" size="60"/>
        </li>
        <li class="fullLine">
            <label for="input02">中级职称技术人员数：</label>
         	<input type="text" name="middleTitleTchstNmbr" id="middleTitleTchstNmbr" class="digits" value="${agency.middleTitleTchstNmbr}" size="60"/>
        </li>
        <li class="fullLine">
            <label for="input02">从业人员总数：</label>
         	<input type="text" name="prctTotalNmbr" id="prctTotalNmbr" class="digits" value="${agency.prctTotalNmbr}" size="60"/>
        </li>
        <li>
             <label for="input01">采购代理：</label>
             <form:select path="purAgent" id="purAgent">
            	<form:option value="01">集中采购</form:option>
            	<form:option value="02">招标代理</form:option>
             </form:select>
        </li>
        <li class="fullLine">
            <label for="input02">工商注册地址：</label>
         	<input type="text" name="regAddress" id="regAddress" maxlength="100" value="${agency.regAddress}" size="60"/>
        </li>
        <li class="fullLine">
            <label for="input02">工商注册发证机关：</label>
         	<input type="text" name="regAuthOrg" id="regAuthOrg" maxlength="50" value="${agency.regAuthOrg}" size="60"/>
        </li>
        <li class="fullLine">
            <label for="input02">注册资金(万元)：</label>
         	<input type="text" name="regCapital" id="regCapital" class="money" maxlength="8" value="<fmt:formatNumber value="${agency.regCapital}" pattern="####" />" size="60"/>
        </li>
        <li class="fullLine">
            <label for="input02">工商注册号：</label>
         	<input type="text" name="regCode" id="regCode" maxlength="50" value="${agency.regCode}" size="60"/>
        </li>
        <li class="fullLine">
            <label for="input02">工商注册法人：</label>
         	<input type="text" name="regCoporate" id="regCoporate" maxlength="50" value="${agency.regCoporate}" size="60"/>
        </li>
        <li class="fullLine">
            <label for="input02">工商注册日期：</label>
         	<input type="text" name="regDate" id="regDate" value="${agency.regDate}" size="40" readonly="readonly"/>
		</li>
		<li class="fullLine">
            <label for="input02">营业执照开始日期：</label>
         	<input type="text" name="tradeStartDate" id="tradeStartDate" value="${agency.tradeStartDate}" size="40" readonly="readonly"/>
		</li>
		<li class="fullLine">
            <label for="input02">营业执照结束日期：</label>
         	<input type="text" name="tradeEndDate" id="tradeEndDate" value="${agency.tradeEndDate}" size="40" readonly="readonly"/>
		</li>
        <li class="fullLine">
            <label for="input02">工商注册有效期：</label>
         	<input type="text" name="regToDate" id="regToDate" value="${agency.regToDate}" size="40" readonly="readonly"/>
		</li>
		<li class="fullLine">
            <label for="input02">注册执业人员数：</label>
         	<input type="text" name="regPrctsNmbr" id="regPrctsNmbr" class="digits" value="${agency.regPrctsNmbr}" size="60"/>
        </li>
        <li class="fullLine">
            <label for="input02">负责总额(万元)：</label>
         	<input type="text" name="totalCharge" id="totalCharge" class="money" maxlength="8" value="<fmt:formatNumber value="${agency.totalCharge}" pattern="####" />" size="60"/>
        </li>
        <li class="fullLine">
            <label for="input02">资产总额(万元)：</label>
         	<input type="text" name="totalAssets" id="totalAssets" class="money" maxlength="8" value="<fmt:formatNumber value="${agency.totalAssets}" pattern="####" />" size="60"/>
        </li>
        <li class="fullLine">
            <label for="input02">经济性质：</label>
         	<input type="text" name="ecnmNature" id="ecnmNature" maxlength="50" value="${agency.ecnmNature}" size="60"/>
        </li>
        <li class="formTextarea">
            <label for="input02">营业范围（主营）：</label>
          	<textarea name="mianBussScp" id="mianBussScp" maxlength="500">${agency.mianBussScp}</textarea>	
        </li>
        <li class="formTextarea">
            <label for="input02">营业范围（兼营）：</label>
          	<textarea name="cncrBussScp" id="cncrBussScp" maxlength="500">${agency.cncrBussScp}</textarea>	
        </li>
        <li class="formTextarea">
            <label for="input02">工商注册营业范围：</label>
         	<textarea name="regBusScope" id="regBusScope" maxlength="500">${agency.regBusScope}</textarea>	
        </li>
        <li class="formTextarea">
            <label for="input02">企业规模：</label>
          	<textarea name="unitScape" id="unitScape" maxlength="500">${agency.unitScape}</textarea>	
        </li>
        <li class="formTextarea">
            <label for="input02">企业简介：</label>
          	<textarea name="unitDesc" id="unitDesc" maxlength="500" class="required">${agency.unitDesc}</textarea>	
            <span class="eleRequired">*</span> 
        </li>
        <li class="formTextarea">
            <label for="input02">近三年承担过的招标代理项目：</label>
          	<textarea name="undtkBidProj" id="undtkBidProj" >${agency.undtkBidProj}</textarea>	
        </li>
        <li class="formTextarea">
            <label for="input02">近三年经营代理情况：</label>
          	<textarea name="agencyBussCndt" id="agencyBussCndt" >${agency.agencyBussCndt}</textarea>	
        </li>
        
        <c:if test="${agency.opinion!=null}">
        	<li class="formTextarea">
        		<label>审核意见：</label>
        		<span>${agency.opinion }(${agency.auditStatusCN })</span>
        	</li>
        </c:if>
   	</ul>
  </div>
</form:form>

<script>
var AgencyInfoForm={};
$(document).ready(function(){
	$('#AgencyInfoForm').validate();
	$("#regDate").epsDatepicker();
	$("#regToDate").epsDatepicker();
	$("#tradeEndDate").epsDatepicker();
	$("#tradeStartDate").epsDatepicker();

	//主管单位
	$("#supervisor").click(function(){
		$.epsDialog({
	        title:'选择',
	        url:$("#initPath").val()+'/view/bizplatform/agency/registration/select_chargedepartment.jsp?Hname=supervisor&Hid=supervisorId'
	    })
	})
})
</script>