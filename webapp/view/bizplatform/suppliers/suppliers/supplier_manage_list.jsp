<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/bizplatform/organization/manager/supplier_manage_list.js"></script>

<!-- 查询条件 -->
<div class="conSearch">
<h4><span><spring:message code="globe.query" /></span></h4>
<form id="OrgInfoManageListForm">
	<ul>
		<li><label>机构名称 ：</label><input type="text" name="orgName" id="orgName" value=""></li>
		<li class="operationBtnDiv">
			<button type="button" id="query"><span><spring:message code="globe.query" /></span></button>
		</li>
	</ul>
</form>
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
						<th class="left">机构代码</th>
						<th class="omission">机构名称</th>
						<th class="date center">生效日期</th>
						<th class="date center">失效日期</th>
						<th class="center">状态</th>
						<th class="center">禁用/开启</th>
						<th class="operation">操作</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
	</div>
</div>
