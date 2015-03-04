<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<style>
.areaClass{width:70%;margin-left:1px;height:60px;}
</style>
<script type="text/javascript">
$('#back').click(function(){
	$('#conBody').loadPage($('#initPath').val()+'/view/es/planform/resproject/resProjectInfoList.jsp');
	//if($('#parentId').val()==''){
	//	$('#conBody').loadPage($('#initPath').val()+'/ResProjectController.do?method=loadResProjectManagePage');
	//}else{
	//	 $('#conBody').loadPage($('#initPath').val()+"/ResProjectController.do?method=loadDisassemblePage&resProjectId="+$('#parentId').val());
	//}
});
</script>
<div class="formLayout form2Pa">
<h5 align="center"><span>项目基本信息</span></h5>
<form id="resProjectInfoForm" name="resProjectInfoForm" method="post" >
<input type="hidden" id="moneyModel" name="moneyModel" >
<input type="hidden" id="parentId" name="parentId" value="${resProject.parent.objId }" >
<input type="hidden" id="taskPlanId" name="taskPlanId" value="${resProject.taskPlanId }" >
<input type="hidden" id="objId" name="objId" value="${resProject.objId }" >
<table class="tableList">
<tr>
	<th><label class="short"  for="projectName">委托建设项目名称：&nbsp;<span class="eleRequired">*</span></label></th>
	<td colspan="3">${resProject.projectName }</td>		
</tr>
<tr>
	<th><label class="short">总投资额(万元)：</label></th>
	<td>${resProject.amt}</td>
	<th><label class="short">工程地点：</label></th>
	<td>${resProject.projectAddress }</td>
</tr>
<tr>
	<th><label class="short">建设规模：</label></th>
	<td colspan="3">${resProject.constructScale}</td>
</tr>
<tr>
<th><label class="short">项目投资类型：</label></th>
<td colspan="3">
	<c:if test="${resProject.projectType eq '00' }">中央投资</c:if>
	<c:if test="${resProject.projectType eq '01' }">非中央投资</c:if>
</td>				
</tr> 
<tr>
	<td colspan="4">
		<table>
			<tr>
				<th style="width:8%" rowspan="3"><label class="short">中央投资</label></th>
				<th style="width:12%"><label class="short">中央投资金额(万元)：</label></th>
				<td>${centralInvestmentMoney }</td>
			</tr>
			<tr>
				<th style="width:12%;"><label class="short">资金使用方式：</label></th>
				<td>
					<c:if test="${not empty moneyModels[0]}">直接投资</c:if>
					<c:if test="${not empty moneyModels[1]}">资本金注入</c:if>
					<c:if test="${not empty moneyModels[2]}">投资资补贴</c:if>
					<c:if test="${not empty moneyModels[3]}">代款贴息</c:if>
					<c:if test="${not empty moneyModels[4]}">转贷</c:if>
				</td>
			</tr>
			<tr>
				<th style="width:12%;"><label class="short">资金来源：</label></th>
				<td>
					<table style="width:40%;" id="central">
						<c:forEach items="${centralMoneySourceList}" var ="moneySource" varStatus="status">
							<tr>
							<td>${moneySource[1]}(万元):</td>
							<td>${moneySource[2]}</td>
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
				<td>${notCentralInvestmentMoney }</td>
			</tr>
			<tr>
				<th style="width:12%;"><label class="short">资金来源：</label></th>
				<td>
					<table style="width:40%;" id="notCentral">
						<c:forEach items="${notCentralMoneySourceList}" var ="moneySource" varStatus="status">
							<tr>
							<td>${moneySource[1]}(万元):</td>
							<td>${moneySource[2]}</td>
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
	<td>${resProject.agenty.orgName}
		<input name="agentyId" id="agentyId" type="hidden" value="${resProject.agenty.objId}"/>
	</td>
	<th class="short">代理机构负责人：</th>
	<td>${emp.objId eq  resProject.agentyLinker}</td>
</tr>
<tr>
	<th><label class="short" for="agentyLinkerTel">代理机构负责人联系电话：</label></th>
	<td>${resProject.agentyLinkerTel}</td>
</tr>
</table>
<h5 align="center"><span>招标人</span></h5>
<table class="tableList">					
<tr>
	<th><label class="short" for="budget">招标人名称：<span class="eleRequired">*</span></label></th>
	<td>${resProject.budgetName}</td>
	<th><label class="short" for="budgetLinker">招标人联系人：<span class="eleRequired">*</span></label></th>
	<td>${resProject.budgetLinker}</td>
</tr>
<tr> 
	<th><label class="short" for="budgetLinkerTel">招标人联系电话：</label></th>
	<td>${resProject.budgetLinkerTel}</td>
</tr>  
</table>
<h5 align="center"><span>所属专业公司</span></h5>
<table class="tableList">
<tr> 
	<th><label class="short" for="departmentName">所属专业公司名称：</label></th>
	<td>${resProject.departmentName}</td>
    <th><label class="short" for="departmentLinker">专业公司联系人：</label></th>
	<td>${resProject.departmentLinker}</td>
</tr> 
<tr> 
	<th><label class="short" for="departmentLinkerTel">联系电话：</label></th>
	<td>${resProject.departmentLinkerTel}</td>
</tr>
</table>
<h5 align="center"><span>委托人</span></h5>
<table class="tableList">	
 <tr> 
	<th><label class="short" for="commissionedUnitName">委托人名称：</label></th>
	<td>${resProject.commissionedUnitName}</td>
    <th><label class="short" for="commissionedUnitLinker">委托联系人：</label></th>
	<td>${resProject.commissionedUnitLinker}</td>
</tr>
<tr> 
	<th><label class="short" for="commissionedUnitTel">委托人联系电话：</label></th>
	<td>${resProject.commissionedUnitTel}</td>
</tr>
</table>
</form>   
<div class="conOperationBtnDiv">
	<button id="back" class="subBtn" type="button" tabindex="20"><span><spring:message code="globe.return"/></span></button>
</div>
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
