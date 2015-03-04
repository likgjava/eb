<%@ page contentType="text/html;charset=UTF-8" %><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src='<c:url value="/view/pubservice/application/template/template_manage_list.js"/>'></script>

<!--搜索条件 -->
<div class="conSearch">
	<h4><span>搜索</span></h4>
	<form id="templateSearchForm">
		<ul>
		    <li>
				<label>范本名称：</label>
				<input type="text" name="templateName" />
				<input type="hidden" name="templateName_op" value="like" />
		    </li>
		    <li>
				<label>区域名称：</label>
				<input type="text" name="districtName" />
				<input type="hidden" name="districtName_op" value="like" />
		    </li>
		    <li>
				<label>品目名称：</label>
				<input type="text" name="categoryName" />
				<input type="hidden" name="categoryName_op" value="like" />
		    </li>
		    <li>
				<label>所属机构：</label>
				<input type="text" name="org.orgName" />
				<input type="hidden" name="org.orgName_op" value="like" />
		    </li>
		    <li>
				<label>品目类型：</label>
				<html:select id="templateType" name="templateType" code="pubservice.application.template.templateType">
					<html:option value="">全部</html:option>
				</html:select>
		    </li>
		    <li class="operationBtnDiv">
		        <button type="button" onclick="TemplateManageList.getTemplateList();"><span>查询</span></button>
		    </li>
		</ul>
	 </form>
</div>

<div class="formTips attention">
	<ul>
		<li>
			<em>注意：</em>新增范本请点击<a id="addTemplate" class="sysicon siAdd" href="javascript:void(0);"><span><strong>新增范本</strong></span></a>
		</li>
	</ul>
</div>

<!--范本列表-->
<div>
	<table class="frontTableList" id="templateList">
		<thead>
			<tr>
				<th class="omission" omiLength="10">范本名称</th>
				<th class="omission" omiLength="8">区域名称</th>
				<th class="omission" omiLength="8">品目名称</th>
				<th class="omission" omiLength="8">所属机构</th>
				<th>范本类型</th>
				<th class="center">共享</th>
				<th class="center">收藏|下载</th>
				<th class="operation">操作</th>
			</tr>
		</thead>
		<tbody></tbody>
	</table>
</div>
