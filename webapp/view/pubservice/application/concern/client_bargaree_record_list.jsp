<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/pubservice/application/concern/client_bargaree_record_list.js"></script>

<input type="hidden" id="currentTab2" value="0"/>
<input type="hidden" id="concernId" value="${concern.objId}"/>
<input type="hidden" id="currentUser" value="${currentUser.objId}"/>
<input type="hidden" id="clientUser" value="${concern.user.objId}"/>
<input type="hidden" id="clienterId" value="${clienter.objId}"/>
<input type="hidden" id="currentOrgId" value="${currentOrg.objId}"/>

<div id="conTitle">
	<div class="navCurrent">
		与客户     <span style="font-size: 20px;margin-right: 10px;margin-left: 10px;">${clienter.orgName}</span> 的交易记录
	</div>
</div>

<!-- TAB页开始 -->
<div id="epsTabs">
	<ul>
		<li>
			<a href="#orderListInfoDiv" id = "my_orders_create"><span>采购订单记录</span></a>
		</li>
		<li>
			<a href="#orderListInfoDiv" id = "my_orders_receive"><span>供货订单记录</span></a>
		</li>
		<li>
			<a href="#projectsInfoDiv" id = "my_create_projects"><span>采购项目记录</span></a>
		</li>
		<li>
			<a href="#signUpProjectListDiv" id = "my_baoming_projects"><span>供货项目记录</span></a>
		</li>
	</ul>

	<!-- 订单记录 -->
	<div id="orderListInfoDiv">
		<table class="frontTableList" id="orderInfoTableList">
			<thead>
				<tr>
					<th class="left">订单编号</th>
					<th class="left omission" omiLength="5">采购品目</th>
					<th class="amount">总数量</th>
					<th class="money">总金额（元）</th>
					<th class="date">创建时间</th>
					<th class="left">合同编号</th>
					<th class="left">支付状态</th>
					<th class="operation">操作</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>

	<!-- 议价、竞价项目记录 -->
	<div id="projectsInfoDiv">
		<table class="frontTableList" id="projectsInfoTableList">
			<thead>
				<tr>
					<th class="left omission">项目名称</th>
					<th class="center">项目类型</th>
					<th class="date">项目创建时间</th>
					<th class="left">项目结果</th>
					<th class="left">客户是否中标</th>
					<th class="operation">操作</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
	
	<!-- 报名参与的议价、竞价项目记录 -->
	<div id="signUpProjectListDiv">
		<table class="frontTableList" id="signUpProjectList">
			<thead>
				<tr>
					<th class="left omission">项目名称</th>
					<th class="left omission">项目编号</th>
					<th class="center">项目类型</th>
					<th class="date">项目创建时间</th>
					<th class="date">项目结果</th>
					<th class="left">我是否中标</th>
					<th class="operation">操作</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
</div>
<!-- TAB页结束 -->