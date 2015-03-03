<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/bizplatform/organization/manager/organization_manage_list.js"></script>

<!-- 查询条件 -->
<div class="conSearch">
<h4><span><spring:message code="globe.query" /></span></h4>
<form id="OrgInfoManageListForm">
<input type="hidden" id="appType" name="appType" value="${param.appType}"/>
	<ul>
		<li><label>机构名称：</label><input type="text" name="orgName" id="orgName" value=""></li>
		<li><label>机构类别：</label>
			 	<select name="orgType" id="orgType">
					<option value="">不限</option>
					<option value="SUPPLIER">供应商</option>
					<option value="BUYER">采购人</option>
				</select>
		</li>
		<li><label>创建人姓名：</label>
			<input type="text" name="createUser.emp.name" id="createUser.emp.name" value="">
			<input type="hidden" name="createUser.emp.name_op" value="like"></li>
		<li><label>创建人账号：</label>
			<input type="text" name="createUser.usName" id="createUser.usName" value="">
			<input type="hidden" name="createUser.usName_op" value="like"></li>
		<li>
			<label>创建时间：</label>
			<input type="text" name="startDate" id="startDate">&nbsp;到
			<input type="text" name="endDate" id="endDate">
		</li>
		<li class="operationBtnDiv">
			<button type="button" id="query"><span><spring:message code="globe.query" /></span></button>
		</li>
	</ul>
</form>
</div>

<div class="formTips attention">
	<ul>
	  <li>
		<em>创建采购人/供应商请点击
		<c:choose>
		<c:when test="${param.appType=='XEJY'}">
			<span class="sysicon siAdd">
				<a id="createOrgInfoBtn_" href="javascript:void(0);"><strong>创建采购人/供应商</strong></a>
			</span>
		</c:when>
		<c:otherwise>
			<span class="sysicon siAdd">
				<a id="createOrgInfoBtn_buyer" href="javascript:void(0);"><strong>创建采购人</strong></a>
			</span>
			<span class="sysicon siAdd">
				<a id="createOrgInfoBtn_supplier" href="javascript:void(0);"><strong>创建供应商</strong></a>
			</span>
		</c:otherwise>
		</c:choose>
		</em>
	  </li>
	</ul>
</div>

<div id="epsTabs">
  <ul>
    <li>
      <a href="#orgInfosList" id = "tabs_waitAudit" class="refreshData"><span>临时</span></a>
    </li>
    <li>
      <a href="#orgInfosList" id = "tabs_hadAudit" class="refreshData"><span>已通过</span></a>
    </li>
  </ul>
	<div id="orgInfosList">
			<table class="frontTableList" id="OrgInfoManageList">
				<thead>
					<tr>
						<th class="omission left">机构名称</th>
						<th class="omission left">创建人姓名</th>
						<th class="omission left">创建人账号</th>
						<th class="datetime left">创建日期</th>
						<th class="operation">状态</th>
						<th class="left">禁用/开启</th>
						<th class="left">机构类别</th>
						<th class="operation">操作</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
	</div>
</div>
