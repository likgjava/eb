<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/agreement/contract/org_contract_monitor_list.js"></script>

<input type="hidden" id="companyId" value="${companyId}" />
<input type="hidden" id="orgInfoId" value="${orgInfoId}" />

<div class="treePage frameMainSub frameMs12 fullScreen">
	<!-- 组织机构树 -->
	<div class="treeOutside frameMain">
		<div id="orgnizationTreeGrid" style="overflow-x: auto;overflow-y: hidden;" class="treeContentDiv"></div>
	</div>
	
	<!-- 合同信息 -->
	<div class="treeRight frameSub">
		<!-- 查询条件 -->
		<div class="conSearch">
			<h4><span><spring:message code="globe.query"/></span></h4>
			<form id="monitorContractForm">
				<ul>
					<li>
						<label>合同编号：</label>
						<input type="text" name="contractNo" style="width: 100px;" />
						<input type="hidden" name="contractNo_op" value="like" />
					</li>
					<li>
						<label>合同名称：</label>
						<input type="text" name="contractName" style="width: 100px;" />
						<input type="hidden" name="contractName_op" value="like" />
					</li>
					<li class="operationBtnDiv right">
						<button type="button" onclick="OrgContractMonitorList.oTable.fnDraw();"><span><spring:message code="globe.query"/></span></button>
					</li>
				</ul>
			</form>
		</div>

		<!-- 合同列表 -->
		<table class="frontTableList" id="contractList">
			<thead>
				<tr>
					<th>合同编号</th>
					<th class="omission" omiLength="5">合同名称</th>
					<th class="omission" omiLength="5">供应商</th>
					<th class="amount">总额（元）</th>
					<th class="date">签订时间</th>
					<th class="operation">合同状态</th>
					<th class="operation">操作</th>
				</tr>
			</thead>
			<tbody></tbody>
		</table>
	</div>
</div>
