<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%request.getContextPath();%>/view/srplatform/auth/orgDetailForm.js"></script>

<form method="post" name="OrgDetailForm" id="OrgDetailForm"  action="OrgDetailController.do?method=save" >
	<input type="hidden" name="orgnization.objId" id="orgnization.objId" value="<c:out value="${param.orgnizationId}"/>"/>
	<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>

    <div class="tableInput">
            <table>
           		<caption>
      				<span>温馨提示：您当前所做的操作--维护组织机构详细信息</span>
     			</caption>
			     	<tr>
            		<th><spring:message code="OrgDetailForm.agentType"/></th>
            		<td colspan="3">
						<select name="agentType" id="agentType">
						</select>
            		</td>
            		<th><spring:message code="OrgDetailForm.authFirstDate"/></th>
            		<td colspan="3">
						<input type="text" name="authFirstDate" 
							class="WdateShort" 
						 onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" value=""/>
            		</td>
            	   </tr>
            	   
			     	<tr>
            		<th><spring:message code="OrgDetailForm.contPerson"/></th>
            		<td colspan="3">
						<input type="text" name="contPerson" id="contPerson" 
					     >
            		</td>
            		<th><spring:message code="OrgDetailForm.contTel"/></th>
            		<td colspan="3">
						<input type="text" name="contTel" id="contTel" class="telephone"
					     >
					     <span class="requisite"><spring:message code="globe.pleaseInput"/><spring:message code="OrgDetailForm.contTel" /></span>
            		</td>
            	   </tr>
            	   
			     	<tr>
            		<th><spring:message code="OrgDetailForm.dunsNum"/></th>
            		<td colspan="3">
						<input type="text" name="dunsNum" id="dunsNum" 
					     >
					     <span class="requisite"><spring:message code="globe.pleaseInput"/><spring:message code="OrgDetailForm.dunsNum" /></span>
            		</td>
            		<th><spring:message code="OrgDetailForm.fax"/></th>
            		<td colspan="3">
						<input type="text" name="fax" id="fax" 
					     >
					     <span class="requisite"><spring:message code="globe.pleaseInput"/><spring:message code="OrgDetailForm.fax" /></span>
            		</td>
            	   </tr>
            	   
			     	<tr>
            		<th><spring:message code="OrgDetailForm.historyId"/></th>
            		<td colspan="3">
						<input type="text" name="historyId" id="historyId" 
					     >
					     <span class="requisite"><spring:message code="globe.pleaseInput"/><spring:message code="OrgDetailForm.historyId" /></span>
            		</td>
            		<th><spring:message code="OrgDetailForm.openAccount"/></th>
            		<td colspan="3">
						<input type="text" name="openAccount" id="openAccount"  class="account"
					     >
					     <span class="requisite"><spring:message code="globe.pleaseInput"/><spring:message code="OrgDetailForm.openAccount" /></span>
            		</td>
            	   </tr>
            	   
			     	<tr>
            		<th><spring:message code="OrgDetailForm.openBank"/></th>
            		<td colspan="3">
						<select name="openBank" id="openBank" class="digits">
						</select>
						<span class="requisite"><spring:message code="globe.pleaseInput"/><spring:message code="OrgDetailForm.openBank" /></span>
            		</td>
            		<th><spring:message code="OrgDetailForm.orgCode"/></th>
            		<td colspan="3">
						<input type="text" name="orgCode" id="orgCode"  class="digits" >
						<span class="requisite"><spring:message code="globe.pleaseInput"/><spring:message code="OrgDetailForm.orgCode" /></span>
            		</td>
            	   </tr>
            	   
			     	<tr>
            		<th><spring:message code="OrgDetailForm.orgCodeFile"/></th>
            		<td colspan="3">
		            	<input type="hidden" name="orgCodeFile.objId" id="orgCodeFile.objId" value="" size="20">
						<input type="text" name="orgCodeFile.name"
								class="sysicon siSearch" 
						 id="orgCodeFile.name" value="" size="20" readonly="readyonly">
						 <span class="requisite"><spring:message code="globe.pleaseInput"/><spring:message code="OrgDetailForm.orgCodeFile" /></span>
            		</td>
            		<th><spring:message code="OrgDetailForm.regAddress"/></th>
            		<td colspan="3">
						<input type="text" name="regAddress" id="regAddress" 
					     >
					     <span class="requisite"><spring:message code="globe.pleaseInput"/><spring:message code="OrgDetailForm.regAddress" /></span>
            		</td>
            	   </tr>
            	   
			     	<tr>
            		<th><spring:message code="OrgDetailForm.regAuthOrg"/></th>
            		<td colspan="3">
						<input type="text" name="regAuthOrg" id="regAuthOrg" 
					     >
					     <span class="requisite"><spring:message code="globe.pleaseInput"/><spring:message code="OrgDetailForm.regAuthOrg" /></span>
            		</td>
            		<th><spring:message code="OrgDetailForm.regBusScope"/></th>
            		<td colspan="3">
						<input type="text" name="regBusScope" id="regBusScope" 
					     >
					     <span class="requisite"><spring:message code="globe.pleaseInput"/><spring:message code="OrgDetailForm.regBusScope" /></span>
            		</td>
            	   </tr>
            	   
			     	<tr>
            		<th><spring:message code="OrgDetailForm.regCode"/></th>
            		<td colspan="3">
						<input type="text" name="regCode" id="regCode" class="account"
					     >
					     <span class="requisite"><spring:message code="globe.pleaseInput"/><spring:message code="OrgDetailForm.regCode" /></span>
            		</td>
            		<th><spring:message code="OrgDetailForm.regCoporate"/></th>
            		<td colspan="3">
						<input type="text" name="regCoporate" id="regCoporate" 
					     >
					     <span class="requisite"><spring:message code="globe.pleaseInput"/><spring:message code="OrgDetailForm.regCoporate" /></span>
            		</td>
            	   </tr>
            	   
			     	<tr>
            		<th><spring:message code="OrgDetailForm.regDate"/></th>
            		<td colspan="3">
						<input type="text" name="regDate" 
							class="WdateShort" 
						 onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" value=""/>
						 <span class="requisite"><spring:message code="globe.pleaseInput"/><spring:message code="OrgDetailForm.regDate" /></span>
            		</td>
            		<th><spring:message code="OrgDetailForm.regFile"/></th>
            		<td colspan="3">
		            	<input type="hidden" name="regFile.objId" id="regFile.objId" value="" size="20">
						<input type="text" name="regFile.name"
								class="sysicon siSearch" 
						 id="regFile.name" value="" size="20" readonly="readyonly">
						 <span class="requisite"><spring:message code="globe.pleaseInput"/><spring:message code="OrgDetailForm.regFile" /></span>
            		</td>
            	   </tr>
            	   
			     	<tr>
            		<th><spring:message code="OrgDetailForm.regToDate"/></th>
            		<td colspan="3">
						<input type="text" name="regToDate" 
							class="WdateShort" 
						 onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" value=""/>
						 <span class="requisite"><spring:message code="globe.pleaseInput"/><spring:message code="OrgDetailForm.regToDate" /></span>
            		</td>
            		<th><spring:message code="OrgDetailForm.unitAddress"/></th>
            		<td colspan="3">
						<input type="text" name="unitAddress" id="unitAddress" 
					     >
					     <span class="requisite"><spring:message code="globe.pleaseInput"/><spring:message code="OrgDetailForm.unitAddress" /></span>
            		</td>
            	   </tr>
            	   
			     	<tr>
            		<th><spring:message code="OrgDetailForm.unitBrief"/></th>
            		<td colspan="3">
						<input type="text" name="unitBrief" id="unitBrief" 
					     >
					     <span class="requisite"><spring:message code="globe.pleaseInput"/><spring:message code="OrgDetailForm.unitBrief" /></span>
            		</td>
            		<th><spring:message code="OrgDetailForm.unitLog"/></th>
            		<td colspan="3">
						<input type="text" name="unitLog" id="unitLog" 
					     >
					     <span class="requisite"><spring:message code="globe.pleaseInput"/><spring:message code="OrgDetailForm.unitLog" /></span>
            		</td>
            	   </tr>
            	   
			     	<tr>
            		<th><spring:message code="OrgDetailForm.unitName"/></th>
            		<td colspan="3">
						<input type="text" name="unitName" id="unitName" 
					     >
					     <span class="requisite"><spring:message code="globe.pleaseInput"/><spring:message code="OrgDetailForm.unitName" /></span>
            		</td>
            		<th><spring:message code="OrgDetailForm.unitScape"/></th>
            		<td colspan="3">
						<input type="text" name="unitScape" id="unitScape" 
					     >
					     <span class="requisite"><spring:message code="globe.pleaseInput"/><spring:message code="OrgDetailForm.unitScape" /></span>
            		</td>
            	   </tr>
            	   
			     	<tr>
            		<th><spring:message code="OrgDetailForm.unitType"/></th>
            		<td colspan="3">
						<input type="text" name="unitType" id="unitType" 
					     >
					     <span class="requisite"><spring:message code="globe.pleaseInput"/><spring:message code="OrgDetailForm.unitType" /></span>
            		</td>
            		<th><spring:message code="OrgDetailForm.webUrl"/></th>
            		<td colspan="3">
						<input type="text" name="webUrl" id="webUrl" 
					     >
					     <span class="requisite"><spring:message code="globe.pleaseInput"/><spring:message code="OrgDetailForm.webUrl" /></span>
            		</td>
            	   </tr>
           	</table>
           	<div class="buttonClass">
  				<span><button type="button" name="submit"><spring:message code="globe.save"/></button></span>
  				<span><button type="reset"><spring:message code="globe.reset"/></button></span>
  				<span><button type="button" name="return"><spring:message code="globe.return"/></button></span>
			</div>
</div>
</form>

<!-- 操作说明. -->  
<div class="tableTip">
	手工新增页面提示信息。
</div>