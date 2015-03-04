<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src='<c:url value="/view/pubservice/application/template/template_used_manage_list.js"/>'></script>

<!--搜索条件 -->
<div class="conSearch">
	<h4><span>搜索</span></h4>
	<form id="templateUsedSearchForm">
		<ul>
		    <li>
				<label>范本名称：</label>
				<input type="text" name="dotTemplate.templateName" />
				<input type="hidden" name="dotTemplate.templateName_op" value="like" />
		    </li>
		    <li>
				<label>机构名称：</label>
				<input type="text" name="org.orgName" />
				<input type="hidden" name="org.orgName_op" value="like" />
		    </li>
		    <li>
				<label>项目名称：</label>
				<input type="text" name="projectName" />
				<input type="hidden" name="projectName_op" value="like" />
		    </li>
		    <li>
				<label>范本类型：</label>
				<html:select id="templateType" name="templateType" code="pubservice.application.template.templateType">
					<html:option value="">全部</html:option>
				</html:select>
		    </li>
		    <li class="operationBtnDiv">
		        <button type="button" onclick="TemplateUsedManageList.getTemplateUsedList();"><span>查询</span></button>
		    </li>
		</ul>
	 </form>
</div>

<!--范本列表-->
<div>
	<table class="frontTableList" id="templateUsedList">
		<thead>
			<tr>
				<th class="omission" omiLength="12">范本名称</th>
				<th class="omission" omiLength="12">机构名称</th>
				<th class="omission" omiLength="12">项目名称</th>
				<th>范本类型</th>
				<th class="date">使用时间</th>
				<th class="operation">操作</th>
			</tr>
		</thead>
		<tbody></tbody>
	</table>
</div>
