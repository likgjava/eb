<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<style type="text/css">
<!--
a.abtn {text-decoration:underline}
.formLayout th {
    background: none repeat scroll 0 0 #EBECEE;
    border-right: 0 none;
    color: #666666;
    font-weight: bold;
    padding: 5px 0;
    text-align: right;
    vertical-align: top;
    width:150px;
}
--> 
</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/resproject/loadCreateTenderProjectPage.js"></script>
<div class="formLayout form2Pa">
	<form id="tenderProjectForm" name="tenderProjectForm" method="post" >
	<input type="hidden" id="projSubIds" name="projSubIds" value=""/>
	<input type="hidden" id="resProjectId" name="resProjectId" value="${resProject.objId }"/>
	<input type="hidden" id="resProjectParentId" name="resProjectParentId" value="${resProject.parent.objId }"/>
	<input name="taskPlanId" id="objId" type="hidden" value="${resProject.taskPlanId}"/>
	<input type="hidden" id="taskPlanSubIds" name="taskPlanSubIds" value="${taskPlanSubIds }"/>
	<!-- 项目类型默认01招标采购 -->
	<input type="hidden" name="tenderType" id="tenderType" value="01"/>
	<h5 align="center"><span>录入招标项目信息</span></h5>
	<ul>
		<li>
			<label class="short"  for="projCode">招标编号：</label>
			<input type="text" name="projCode" id="projCode" class="required" value="${tenderProjectCode }" />
		</li>
		<li>
			<label class="short"  for="projName">招标内容：</label>
			<input type="text" name="projName" id="projName" class="required"  style="width: 300px;" value="" />
			<span class="eleRequired">*</span>
		</li>
		<li>
			<label class="short"  for="projName">代理机构项目负责人：</label>
			<select name="managerId" id="managerId" class="required">
	   		<option value="">--请选择--</option>
	        <c:forEach items="${empList}" var="emp" >	
	        	<option value="${emp.objId}" <c:if test="${emp.objId eq  resProject.agentyLeader.objId}">selected</c:if>>${emp.name}</option>
        	</c:forEach>
    	</select><span class="eleRequired">*</span>
		</li>
		<li>
			<label class="short">招标方式：</label>
			<html:select styleClass="required" id="ebuyMethod" name="ebuyMethod" code="ebuyMethod">
			</html:select>
		</li>
		<li>
			<label class="short">评标方式：</label>
			<select name="evalMethod" id="evalMethod">
				<option value="01">综合评分法</option>
				<option value="02">综合评价法</option>
			</select>
		</li>
		<li>
			<label class="short">招标类型：</label>
			<select name="purCategoryIds" id="purCategoryIds">
				<option value="A">货物类</option>
				<option value="B">工程类</option>
				<option value="C">服务类</option>
			</select>
		</li>
		<li class="formTextarea">
			<label class="short">备注：</label>
			<textarea name="content" id="content" class="texFull" maxlength="200"></textarea>
		</li>
	</ul>
	</form>
</div>
<div class="formLayout form2Pa">
	<div class="conOperation">
		<button id="save" type="button" tabindex="18"><span><spring:message code="globe.save"/></span></button>
		<button id="back" type="button" tabindex="18"><span><spring:message code="globe.return"/></span></button>
	</div>
</div>
<div id="taskplanListView">
	<flex:flexgrid checkbox="true" id="consignGrid" url="TaskPlanController.do?method=getTaskPlanSubNotInProject" queryColumns=""  
		rp="5" title="项目明细"  onSuccess="craeteProjForTaskPlanSub.success" onSubmit="craeteProjForTaskPlanSub.before">
				<flex:flexCol name="purchaseName" display="招标内容/类型"  sortable="true" width="130"align="left"></flex:flexCol>
				 <flex:flexCol name="purChasemodel" display="规格" sortable="true" width="150"align="left"></flex:flexCol>
				<flex:flexCol name="quantity" display="数量" sortable="true" width="150"align="left"></flex:flexCol>
				<flex:flexCol name="unit" display="计量单位" sortable="true" width="150"align="left"></flex:flexCol>
			<flex:flexBtn name="globe.new" bclass="add" onpress="craeteProjForTaskPlanSub.addSub"></flex:flexBtn>
	</flex:flexgrid>
</div>

<div class="formLayout">
<h5 align="center"><span>项目基本信息</span></h5>
<table >
<tr>
	<th ><c:if test="${resProject.parent.objId != null}">子项目名称:</c:if><c:if test="${resProject.parent.objId == null}">委托建设项目名称:</c:if>  </th>
	<td>${resProject.projectName }</td>		
	<th  >委托建设项目总投资额(万元)：</th>
	<td>${resProject.amt}</td>
	<th  >工程地点：</th>
	<td>${resProject.projectAddress }</td>
</tr>
<tr>
	<th>投资类型：</th>
	<td><c:if test="${resProject.projectType eq '00' }">中央投资</c:if><c:if test="${resProject.projectType eq '01' }">非中央投资</c:if></td>	
	<th>建设规模：</th>
	<td colspan="4">${resProject.constructScale}</td>
</tr>
<!-- 
<tr>
	<td colspan="4">
		<table>
			<tr>
				<th style="width:8%" rowspan="3"><label class="short">中央投资</label></th>
				<th style="width:12%"><label class="short">中央投资金额：</label></th>
				<td><input type="text" id="centralInvestmentMoney" value="" disabled/></td>
			</tr>
			<tr>
				<th style="width:12%;"><label class="short">资金使用方式：</label></th>
				<td>
				<c:if test="${not empty moneyModels[0]}">直接投资</c:if>&nbsp;&nbsp;
				<c:if test="${not empty moneyModels[1]}">资本金注入</c:if>&nbsp;&nbsp;
				<c:if test="${not empty moneyModels[2]}">投资资补贴</c:if>&nbsp;&nbsp;
				<c:if test="${not empty moneyModels[3]}">代款贴息</c:if>&nbsp;&nbsp;
				<c:if test="${not empty moneyModels[4]}">转贷</c:if>&nbsp;&nbsp;
				</td>
			</tr>
			<tr>
				<th style="width:12%;"><label class="short">资金来源：</label></th>
				<td>
					<table style="width:40%;" id="central">
						<c:forEach items="${centralMoneySourceList}" var ="moneySource" varStatus="status">
							<tr>
							<td>${moneySource[1]}</td>
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
				<th style="width:12%"><label class="short">非中央投资金额：</label></th>
				<td><input type="text" id="notCentralInvestmentMoney" value="" disabled/></td>
			</tr>
			<tr>
				<th style="width:12%;"><label class="short">资金来源：</label></th>
				<td>
					<table style="width:40%;" id="notCentral">
						<c:forEach items="${notCentralMoneySourceList}" var ="moneySource" varStatus="status">
							<tr>
							<td>${moneySource[1]}</td>
							<td>${moneySource[2]}</td>
							</tr>
						</c:forEach>
					</table>
				</td>
			</tr>
		</table>
	</td>
</tr>-->
<tr>
	<th>代理机构：</th>
	<td>${resProject.agenty.orgName}</td>
	<th class="short">代理机构负责人：</th>
	<td>${resProject.agentyLinker}</td>
	<th>代理机构负责人联系电话：</th>
	<td>${resProject.agentyLinkerTel}</td>
</tr>
<tr>
	<th>招标人名称:</th>
	<td>${resProject.budgetName}</td>
	<th>招标人联系人：</th>
	<td>${resProject.budgetLinker}</td>
	<th>招标人联系电话：</th>
	<td>${resProject.budgetLinkerTel}</td>
</tr>
<tr> 
	<th>所属专业公司名称：</th>
	<td>${resProject.departmentName}</td>
    <th>专业公司联系人：</th>
	<td>${resProject.departmentLinker}</td>
	<th>联系电话：</th>
	<td>${resProject.departmentLinkerTel}</td>
</tr> 
 <tr> 
	<th>委托人名称：</th>
	<td>${resProject.commissionedUnitName}</td>
    <th>委托联系人：</th>
	<td>${resProject.commissionedUnitLinker}</td>
	<th>委托人联系电话：</th>
	<td>${resProject.commissionedUnitTel}</td>
</tr>
</table>
</div>
