<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/agreement/bargin/project/bargain/org_project_monitor_list.js"></script>

<input type="hidden" id="companyId" value="${companyId}"/>
<input type="hidden" id="orgInfoId" value="${orgInfoId}"/>

<div class="treePage frameMainSub frameMs12 fullScreen">
	<!-- 组织机构树 -->
	<div class="treeOutside frameMain">
		<div id="orgnizationTreeGrid" style="overflow-x: auto;overflow-y: hidden;" class="treeContentDiv"></div>
	</div>
	
	<!-- 项目信息 -->
	<div class="treeRight frameSub">
		<!-- 搜索条件 -->
		<div class="conSearch">
			<form id="projectSearchForm">
				<h4><span>搜索</span></h4>
				<ul>
					<li>
						<label>项目名称：</label>
						<input type="text" name="projName" />
						<input type="hidden" name="projName_op" value="like" />
					</li>
					<li>
						<label>项目类型：</label>
						<select name="ebuyMethod" style="width: 80px;">
							<option value="05,06">全部</option>
							<option value="06">竞价</option>
							<option value="05">议价</option>
						</select>
						<input type="hidden" name="ebuyMethod_op" value="in">
					</li>
					<li class="operationBtnDiv">
						<button type="button" onclick="OrgProjectMonitorList.oTable.fnDraw();"><span>查询</span></button>
					</li>
				</ul>
			</form>
		</div>

		<!-- 项目列表 -->
		<table id="projectList" class="frontTableList">
			<thead>
				<tr>
					<th class="omission">项目名称</th>
					<th class="center">采购方式</th>
					<th class="center">报价开始时间</th>
					<th class="center">报价结束时间</th>
					<th class="operation">项目状态</th>
					<th class="operation">操作</th>
				</tr>
			</thead>
			<tbody></tbody>
		</table>
	</div>
</div>