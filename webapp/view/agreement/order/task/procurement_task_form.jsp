<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/agreement/order/task/procurement_task_form.js"></script>

<!--任务书ID-->
<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>

<div class="formLayout form2Pa">
  <form id="procurementForm" method="post">
  <h4><span>任务书信息</span></h4>
   <div class="functionBtnDiv right"><button id="printTask"><span>打印任务书</span></button></div>
    <ul>
      <li><label>任务书编号：</label><span id="taskNo"></span></li>
      <li><label>签发时间：</label><span id="createTime"></span></li>
      <li><label>预算总额（元）：</label>￥<span id="sumTotal" tabType="money"></span></li>
      <li><label>采购数量：</label><span id="sumQty"></span></li>
    </ul>
    
  </form>
</div>
<!-- Tab页 -->
<div id="epsTabs">
	<ul>
		<li><a href="#front" id="tabs_todo" class="refreshData" rel="view/order/procurementTaskList_1.json"><span>采购品目</span></a>
		</li>
		<!--<li><a href="#funds" id="tabs_done" class="refreshData" rel="view/order/procurementTaskList_2.json"><span>资金一览表</span></a>
		</li>
	--></ul>
	<div id="front">
		<table class="frontTableList" id="procurementTaskItem">
		  <thead>
		    <tr>
		      <th>采购品目</th>
		      <th class="money">预算资金（元）</th>
		      <th class="amount">采购数量</th>
		      <th class="amount">已完成数量</th>
		      <th class="money">已完成金额</th>
		      <th class="amount">可用数量</th>
		      <th class="money">可用金额</th>
		      <th class="operation">操作</th>
		    </tr>
		  </thead>
		  <tbody>
		  </tbody>
		</table>
	</div>

	<!--<div id="funds">
			<table class="frontTableList" id="fundsItem">
			    <thead>
			      <tr>
			        <th>资金归口</th>
			        <th>类款项</th>
			        <th>功能科目</th>
			        <th>资金类型</th>
			        <th>指标来源</th>
			        <th>支出经济分类</th>
			        <th>总价</th>
			        <th>备注</th>
			      </tr>
			    </thead>
			    <tbody>
			    </tbody>
			</table>
	</div>
--></div>
<div class="conOperation">
<button id="createReverseProjectBtn"><span>创建竞价项目</span></button>
<button id="return"><span>返回</span></button>
</div>




