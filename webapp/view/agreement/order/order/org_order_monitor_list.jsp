<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/agreement/order/order/org_order_monitor_list.js"></script>

<input type="hidden" id="companyId" value="${companyId}" />
<input type="hidden" id="orgInfoId" value="${orgInfoId}" />

<div class="treePage frameMainSub frameMs12 fullScreen">
	<!-- 组织机构树 -->
	<div class="treeOutside frameMain">
		<div id="orgnizationTreeGrid" style="overflow-x: auto;overflow-y: hidden;" class="treeContentDiv"></div>
	</div>
	
	<!-- 订单信息 -->
	<div class="treeRight frameSub">
		<!-- 查询条件 -->
		<div class="conSearch">
			<h4><span><spring:message code="globe.query"/></span></h4>
			<form id="monitorOrderForm">
				<ul>
					<li>
						<label>订单编号：</label>
						<input name="orderNo" type="text" />
						<input type="hidden" name="orderNo_op" value="like" />
					</li>
					<li class="operationBtnDiv right">
						<button type="button" onclick="OrgOrderMonitorList.oTable.fnDraw();"><span><spring:message code="globe.query"/></span></button>
					</li>
				</ul>
			</form>
		</div>

		<!-- 订单列表 -->
		<table class="frontTableList" id="orderList">
			<thead>
				<tr>
					<th>订单编号</th>
					<th class="omission" omiLength="5">采购品目</th>
					<th class="amount">数量</th>
					<th class="money">金额（元）</th>
					<th class="date">创建时间</th>
					<th class="operation">订单状态</th>
					<th class="operation">操作</th>
				</tr>
			</thead>
			<tbody></tbody>
		</table>
	</div>
</div>
