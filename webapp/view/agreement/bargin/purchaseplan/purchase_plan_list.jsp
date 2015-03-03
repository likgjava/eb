<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/agreement/bargin/purchaseplan/purchase_plan_list.js"></script>

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
		  		<label for="input01">创建时间：</label>
		  		<input name="startDate" id="startDate"/>&nbsp;到
	            <input name="endDate" id="endDate"/>
		  	</li>
	      <li class="operationBtnDiv right">
	       	<button type="button" id="query"><span><spring:message code="globe.query"/></span></button>
	      </li>
	    </ul>
    </form>
</div>

<!-- 操作 -->
<form id="importPlanForm" action="<%=request.getContextPath() %>/ProcurementtaskController.do?method=inputPlan" enctype="multipart/form-data" method="post">
<div class="formTips attention">
	<ul>
		<li>
			<em>注意：</em>新增任务书请点击
			<span id="addPlan"><a class="sysicon siAdd" href="javascript:void(0);" onclick="ProcurementTask.newPlan();return false;"><strong>新增任务书</strong></a></span>
			
			&nbsp;
			
			<span>您还可以导入任务书
			
			<input type="file" name="importPlan" id="importPlan"/>
			<button onclick="ProcurementTask.importPlanImport();return false">导入</button>
			
			</span>
		</li>
	</ul>
</div>
</form>


<!-- Tab页 
<div id="epsTabs" class="">
<ul>
	<li>
		<a href="#procurementInfo" id="tabs_draft" class="refreshData" rel=""><span>起草中计划</span></a>
	</li>
	<li><a href="#procurementInfo" id="tabs_todo" class="refreshData" rel=""><span>采购中计划</span></a>
	</li>
	<li><a href="#procurementInfo" id="tabs_done" class="refreshData" rel="0"><span>已完成任务书</span></a>
	</li>
</ul>-->
<div id="procurementInfo"><!-- 任务书列表 -->
<table class="frontTableList" id="procurementTask">
	<thead>
		<tr>
			<th class="center">编号</th>
			<th>采购内容</th>
			<th class="money">预算金额&nbsp;</th>
			<th class="amount">&nbsp;采购数量</th>
			<th class="amount">&nbsp;完成数量</th>
			<th class="center">计划类型</th>
			<th class="date">创建时间</th>
			<th class="operation">操作</th>
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>
</div>




