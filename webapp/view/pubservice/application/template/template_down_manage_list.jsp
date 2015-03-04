<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src='<c:url value="/view/pubservice/application/template/template_down_manage_list.js"/>'></script>

<!--搜索条件 -->
<div class="conSearch">
	<h4><span>搜索</span></h4>
	<form id="templateDownSearchForm">
		<ul>
		    <li>
				<label>范本名称：</label>
				<input type="text" name="dotTemplate.templateName" />
				<input type="hidden" name="dotTemplate.templateName_op" value="like" />
		    </li>
		    <li>
				<label>区域名称：</label>
				<input type="text" name="dotTemplate.districtName" />
				<input type="hidden" name="dotTemplate.districtName_op" value="like" />
		    </li>
		    <li>
				<label>品目名称：</label>
				<input type="text" name="dotTemplate.categoryName" />
				<input type="hidden" name="dotTemplate.categoryName_op" value="like" />
		    </li>
		    <li>
				<label>下载机构：</label>
				<input type="text" name="org.orgName" />
				<input type="hidden" name="org.orgName_op" value="like" />
		    </li>
		    <li>
				<label>范本类型：</label>
				<html:select id="templateType" name="templateType" code="pubservice.application.template.templateType">
					<html:option value="">全部</html:option>
				</html:select>
		    </li>
		    <li class="operationBtnDiv">
		        <button type="button" onclick="TemplateDownManageList.getTemplateDownList();"><span>查询</span></button>
		    </li>
		</ul>
	 </form>
</div>

<!--范本列表-->
<div>
	<table class="frontTableList" id="templateDwonList">
		<thead>
			<tr>
				<th class="omission" omiLength="10">范本名称</th>
				<th class="omission" omiLength="8">区域名称</th>
				<th class="omission" omiLength="8">品目名称</th>
				<th class="omission" omiLength="8">下载机构</th>
				<th>范本类型</th>
				<th class="date">下载时间</th>
				<th class="operation">操作</th>
			</tr>
		</thead>
		<tbody></tbody>
	</table>
</div>
