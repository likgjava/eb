<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<style>
.areaClass{width:70%;margin-left:1px;height:60px;}
</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/resproject/loadResProjectInfo.js"></script>
<div class="formLayout form2Pa">
<h5 align="center"><span>委托建设项目基本信息</span></h5>
<form id="resProjectInfoForm" name="resProjectInfoForm" method="post" >
<input type="hidden" id="moneyModel" name="moneyModel" >
<input type="hidden" id="parentId" name="parentId" value="${resProject.parent.objId }" >
<input type="hidden" id="taskPlanId" name="taskPlanId" value="${resProject.taskPlanId }" >
<input type="hidden" id="objId" name="objId" value="${resProject.objId }" >
<table class="tableList">

<c:if test="${resProject.parent.objId != null }">
	<tr>
	<th><label class="short"  for="projectName">上级项目名称：&nbsp;</label></th>
	<td colspan="3">${resProject.parent.projectName }</td>		
</tr>
</c:if>
<tr>
	<th><label class="short"  for="projectName">委托建设项目名称：&nbsp;<span class="eleRequired">*</span></label></th>
	<td colspan="3"><input id="projectName" name="projectName" class="required" style="width: 400px;" value="${resProject.projectName }"></td>		
</tr>
<tr>
	<th><label class="short">总投资额(万元)：<span class="eleRequired">*</span></label></th>
	<td><input name="amt" id="amt"   value="${resProject.amt}" class="required bigMoney"></td>
	<th><label class="short">工程地点：</label></th>
	<td><input type="text" id="projectAddress" name="projectAddress" style="width: 400px;size: 200px;" value="${resProject.projectAddress }"></td>
</tr>
<tr>
	<th><label class="short">建设规模：</label></th>
	<td colspan="3"><textarea id="constructScale" name="constructScale" class="areaClass" >${resProject.constructScale}</textarea></td>
</tr>
<tr>
<th><label class="short">项目投资类型：</label></th>
<td colspan="3">
	<input type="radio" id="centralType" name="projectType" value="00" <c:if test="${resProject.projectType eq '00' }">checked</c:if> onclick="changeType(this)"/> 中央投资
	<input type="radio" name="projectType" value="01" <c:if test="${resProject.projectType eq '01' }">checked</c:if> onclick="changeType(this)"/> 非中央投资
</td>				
</tr> 
<tr>
	<td colspan="4">
		<table>
			<tr>
				<th style="width:8%" rowspan="3"><label class="short">中央投资</label></th>
				<th style="width:12%"><label class="short">中央投资金额(万元)：</label></th>
				<td>
					<input type="text" id="centralInvestmentMoney" value="${centralInvestmentMoney }" disabled="disabled" />
					<span class="eleRequired">(中央投资金额  = 中央投资各项资金来源累加之和)</span>
				</td>
			</tr>
			<tr>
				<th style="width:12%;"><label class="short">资金使用方式：</label></th>
				<td>
	        		<input type="checkbox" name="moneyUseModel" value="1" <c:if test="${not empty moneyModels[0]}">checked</c:if>/>&nbsp;直接投资&nbsp;&nbsp;
					<input type="checkbox" name="moneyUseModel" value="2" <c:if test="${not empty moneyModels[1]}">checked</c:if>/>&nbsp;资本金注入&nbsp;&nbsp;
					<input type="checkbox" name="moneyUseModel" value="3" <c:if test="${not empty moneyModels[2]}">checked</c:if>/>&nbsp;投资资补贴&nbsp;&nbsp;
					<input type="checkbox" name="moneyUseModel" value="4" <c:if test="${not empty moneyModels[3]}">checked</c:if>/>&nbsp;代款贴息&nbsp;&nbsp;
					<input type="checkbox" name="moneyUseModel" value="5" <c:if test="${not empty moneyModels[4]}">checked</c:if>/>&nbsp;转贷
				</td>
			</tr>
			<tr>
				<th style="width:12%;"><label class="short">资金来源：</label></th>
				<td>
					<table style="width:40%;" id="central">
						<c:forEach items="${centralMoneySourceList}" var ="moneySource" varStatus="status">
							<tr>
							<td>${moneySource[1]}(万元):</td>
							<td><input type="text" name="moneySource" id="${moneySource[0]}" value="${moneySource[2]}" onkeyup ="computeMoney(this)"/></td>
							</tr>
						</c:forEach>
					</table>
				</td>
			</tr>
		</table>
	</td>
</tr>
<tr>
	<td colspan="4">
		<table>
			<tr>
				<th style="width:8%" rowspan="2"><label class="short">非中央投资</label></th>
				<th style="width:12%"><label class="short">非中央投资金额(万元)：</label></th>
				<td>
					<input type="text" id="notCentralInvestmentMoney" value="${notCentralInvestmentMoney }" disabled="disabled"/>
					<span class="eleRequired">(非中央投资金额 = 非中央投资各项资金来源累加之和)</span>
				</td>
			</tr>
			<tr>
				<th style="width:12%;"><label class="short">资金来源：</label></th>
				<td>
					<table style="width:40%;" id="notCentral">
						<c:forEach items="${notCentralMoneySourceList}" var ="moneySource" varStatus="status">
							<tr>
							<td>${moneySource[1]}(万元):</td>
							<td><input type="text" name="moneySource" id="${moneySource[0]}" value="${moneySource[2]}" onkeyup ="computeMoney(this)"/></td>
							</tr>
						</c:forEach>
					</table>
				</td>
			</tr>
		</table>
	</td>
</tr>
 </table>
<h5 align="center"><span>代理机构</span></h5>
<table class="tableList">
<tr>
	<th><label class="short" for="agentyName">代理机构：</label></th>
	<td>
		<input name="agentyId" id="agentyId" type="hidden" value="${resProject.agenty.objId}"/>
		<input name="agentyName" id="agentyName" type="text" value="${resProject.agenty.orgName}"/>
	</td>
	<th class="short">代理机构项目负责人：</th>
	<td>
		<select name="agentyLeaderId" id="agentyLeaderId" class="required">
	   		<option value="">--请选择--</option>
	        <c:forEach items="${empList}" var="emp" >	
	        	<option value="${emp.objId}" <c:if test="${emp.objId eq  resProject.agentyLeader.objId}">selected</c:if>>${emp.name}</option>
        	</c:forEach>
    	</select><span class="eleRequired">*</span>
   	</td>
</tr>
<tr>
	<th><label class="short" for="agentyLinkerTel">代理机构负责人联系电话：</label></th>
	<td><input type="text" id="agentyLinkerTel" name="agentyLinkerTel" value="${resProject.agentyLinkerTel}" class="linkPhone"/></td>
</tr>
</table>
<h5 align="center"><span>招标人</span></h5>
<table class="tableList">					
<tr>
	<th><label class="short" for="budget">招标人名称：<span class="eleRequired">*</span></label></th>
	<td>
		<input type="text" id="yzdw_name" name="budgetName" value="${resProject.budgetName}" readonly="readonly" class="required"/>
        <input type="hidden" id="yzdw_id" name="budgetId" value="${resProject.budget.objId}">
       <a href="#" onclick="setCommissioner('budget')" class="button">修改为委托人</a> 
	</td>
	<th><label class="short" for="budgetLinker">招标人联系人：<span class="eleRequired">*</span></label></th>
	<td>
		<input type="hidden" id="OrgId"  />
    	<div id='budgetLinkerDiv'>
    		<input type="hidden" name="budgetLeaderId" id="budgetLeaderId" class="required" value="${resProject.budgetLeader.objId }">
    		<input type="text" id="budgetLinker" name ="budgetLinker" class="required" value="${resProject.budgetLinker}" class="required" disabled="disabled">
    	</div>
	</td>
</tr>
<tr> 
	<th><label class="short" for="budgetLinkerTel">招标人联系电话：</label></th>
	<td><input type="text" id="budgetLinkerTel" name="budgetLinkerTel" value="${resProject.budgetLinkerTel}" class="linkPhone"/></td>
</tr>  
</table>
<h5 align="center"><span>所属专业公司</span></h5>
<table class="tableList">
<tr> 
	<th><label class="short" for="departmentName">所属专业公司名称：</label></th>
	<td>
	  	<input type="text" name="departmentName" id="yzdw_name_parentname" value="${resProject.departmentName}" readonly="readonly" class="required"/>
	  	<input type="hidden" id="yzdw_id_parentid" name="departmentId" value="${resProject.department.objId}"/>
	 	<a href="#" onclick="setCommissioner('department')" class="button">修改为委托人</a> 
    </td>
    <th><label class="short" for="departmentLinker">专业公司联系人：</label></th>
	<td>
	<div id='budgetLinkerDiv'>
		<input type="text" id="departmentLinker" name="departmentLinker" value="${resProject.departmentLinker}"/>
	</div>
	</td>
</tr> 
<tr> 
	<th><label class="short" for="departmentLinkerTel">联系电话：</label></th>
	<td><input type="text" id="departmentLinkerTel" name="departmentLinkerTel" value="${resProject.departmentLinkerTel}" class="linkPhone"/></td>
</tr>
</table>
<h5 align="center"><span>委托人</span></h5>
<table class="tableList">	
 <tr> 
	<th><label class="short" for="commissionedUnitName">委托人名称：</label></th>
	<td>
		<input type="hidden" id="yzdw_id_commissionUnitNameId" name="commissionUnitId" value="${resProject.commissionedUnit.objId}"/>
	  	<input type="text" name="commissionedUnitName" id="yzdw_name_commissionUnitName" value="${resProject.commissionedUnitName}">
    </td>
    <th><label class="short" for="commissionedUnitLinker">委托联系人：</label></th>
	<td>
		<input type="text" id="commissionedUnitLinker" name="commissionedUnitLinker" value="${resProject.commissionedUnitLinker}"/>
    </td>
</tr>
<tr> 
	<th><label class="short" for="commissionedUnitTel">委托人联系电话：</label></th>
	<td><input type="text" id="commissionedUnitTel" name="commissionedUnitTel" value="${resProject.commissionedUnitTel}" class="linkPhone"/></td>
</tr>
</table>
<div class="conOperationBtnDiv">
	<button id="save" class="subBtn" type="button" tabindex="18"><span>保存</span></button>
	<button id="back" class="subBtn" type="button" tabindex="20"><span><spring:message code="globe.return"/></span></button>
</div>
</form>   
<input value="${moneySub }" id="moneySub" type="hidden"/>
<!-- 任务书附件 -->
<div class="uploadFile" id="attachId"></div>
</div>

<c:if test="${fn:length(sysConfigItemListString)!=0}">
	<div id="epsTabs2">
		<h5 align="center"><span>附件信息</span></h5>
		<ul>
			<c:forEach items="${sysConfigItemListString}" var="sysConfigItem">
				<li>
			      <a href="#TPFFSUploadAttachment" onclick="javascript:taskPlanForm.loadAttachment('${sysConfigItem.objId}');" class="refreshData"><span>${sysConfigItem.name}</span></a>
			    </li>
		    </c:forEach>
	    </ul>
	    <div id="TPFFSUploadAttachment"></div>
	</div>
</c:if>
<div id="historyView"></div>
