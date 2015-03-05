<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/smallscale/expert/expert_manage_list.js"></script>

<!-- 查询条件 -->
<div class="conSearch">
<h4><span><spring:message code="globe.query" /></span></h4>
<form id="expertInfoManageListForm">
	<ul>
		<li>
			<label>专家姓名：</label>
			<input type="text" name="name" id="name" value="">
			<input type="hidden" name="name_op" value="like">
		</li>
		<li><label>创建人：</label><input type="text" name="createUser.usName" id="createUser.usName" value=""></li>
		<li>
			<label>创建日期：</label>
			<input type="text" name="createTime_show" id="createTime_show">
			<input type="hidden" name="createTime" id="createTime">
			<input type="hidden" name="createTime_op" value="bt">
		</li>
		<li class="operationBtnDiv">
			<button type="button" id="query"><span><spring:message code="globe.query" /></span></button>
		</li>
	</ul>
</form>
</div>

<!-- 新增操作 -->
<div class="formTips attention">
	<ul>
	  <li>
		<em>创建专家请点击
		<span class="sysicon siAdd"><a id="createExpertInfoBtn" href="javascript:void(0);"><strong>创建专家</strong></a></span>
		</em>
	  </li>
	</ul>
</div>

<!-- Tab页 -->
<div id="epsTabs">
	<ul>
		<li>
			<a href="#expertInfosList" id = "tabs_temp" class="refreshData"><span>临时</span></a>
		</li>
		<li>
			<a href="#expertInfosList" id = "tabs_valid" class="refreshData"><span>已通过</span></a>
		</li>
	</ul>
	<div id="expertInfosList">
		<table class="frontTableList" id="expertInfoManageList">
		<thead>
			<tr>
				<th class="center">专家姓名</th>
				<th class="center">所属行业</th>
				<th class="center">创建人</th>
				<th class="date center">创建日期</th>
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
