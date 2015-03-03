<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/agreement/order/task/procurement_task_list.js"></script>

<!-- 查询条件 -->
<div class="conSearch">
    <h4><span><spring:message code="globe.query"/></span></h4>
    <form id="ProcurementTaskSearchForm">
	    <ul>
	      <li>
	        <label>任务书编号：</label>
			<input name="taskNo" id = "taskNo" type="text" >	
			<input name="taskNo_op" type="hidden" value="like">		
	      </li>
		  <li>
		  		<label for="input01">下达时间：</label>
		  		<input name="startDate" id="startDate"/>&nbsp;到
	            <input name="endDate" id="endDate"/>
		  	</li>
	      <li class="operationBtnDiv right">
	       	<button type="button" id="query"><span><spring:message code="globe.query"/></span></button>
	      </li>
	    </ul>
    </form>
</div>

<!-- Tab页 -->
<div id="epsTabs" class="">
<ul>
	<li><a href="#procurementInfo" id="tabs_todo" class="refreshData" rel=""><span>采购中任务书</span></a>
	</li>
	<li><a href="#procurementInfo" id="tabs_done" class="refreshData" rel="0"><span>已完成采购任务书</span></a>
	</li>
</ul>
<div id="procurementInfo"><!-- 任务书列表 -->
<table class="frontTableList" id="procurementTask">
	<thead>
		<tr>
			<th class="center">编号</th>
			<th>采购内容</th>
			<th class="money">预算金额</th>
			<th class="amount">采购数量</th>
			<th class="amount">已完成数量</th>
			<th class="amount">可用数量</th>
			<th class="date">下达时间</th>
			<th class="operation">操作</th>
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>
</div>
</div>




