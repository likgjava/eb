<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/agreement/bargin/purchaseplan/purchase_plan_form.js"></script>

<!--任务书ID-->

<div class="formLayout form2Pa">
  <form:form id="procurementForm" name="procurementForm" method="post" modelAttribute="procurementtask" enctype="multipart/form-data">
  <input type="hidden" name="objId" id="objId" value="${procurementtask.objId}"/>
  <h4><span>采购计划</span></h4>
    <ul>
      <li><label>时期类型：</label>
      <form:select path="periodType" onchange="procurementTaskForm.periodTypeChange()">
	      <form:option value="year">年采购计划</form:option>
	      <form:option value="month">月采购计划</form:option>
	      <form:option value="quarter">季度采购计划</form:option>
      </form:select>
      </li>
      <li><label>时期值：</label>
      	<input type="hidden" name="periodValue" value="${procurementtask.periodValue }">
      	<select style="width:100px" id="year" name="year"></select>&nbsp;
      	<select style="width:100px" id="month" name="month" class="hidden"></select>
      	<select style="width:100px" id="quarter" name="quarter" class="hidden">
      		<option value="1">1季度</option>
      		<option value="2">2季度</option>
      		<option value="3">3季度</option>
      		<option value="4">4季度</option>
      	</select>
      </li>
      <li><label>预算总额：</label>
      	<span id="sumTotal"><fmt:formatNumber value="${procurementtask.sumTotal}" pattern="#,##0.00#"/></span>（元）
      </li>
      <li><label>采购总数量：</label>
      	<span id="sumQty">${procurementtask.sumQty }</span></li>
      <li class="formTextarea"><label>备注：</label><textarea name="memo" id="memo">${procurementtask.memo }</textarea></li>
    </ul>
   </form:form>  

<h4><span>采购计划明细</span></h4>



<div class="formTips attention">
	<span id="addPlanItem"><a class="sysicon siAdd" href="javascript:void(0);" onclick="procurementTaskForm.cloneTr();return false;"><strong>新增采购计划明细</strong></a></span>
</div>
</div>

<div>
<table class="frontTableList" id="procurementTaskItem">
	<thead>
		<tr>
			<th>描述</th>
			<th>分类、品目</th>
			<th>采购数量</th>
			<th>期望价</th>
			<th>预算资金（元）</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody id="content">
	
	<c:if test="${fn:length(procurementtask.protaskItems)>0}">
		<c:forEach var="protaskItem" items="${procurementtask.protaskItems}" varStatus="status">
			<tr id="tempTR${status.index+1}">
				<td style="width:300px;">
					<input name="objId" type="hidden" value="${protaskItem.objId }" />
					<textarea name="memo" maxlength="2000" class="required" style="width:300px;height:60px">${protaskItem.memo }</textarea>
				</td>
				<!--
				<td>
					<input type="hidden" value="${protaskItem.purCategory.objId }" id="purCategory${status.index+1}.objId" name="purCategory.objId" class="input_medium"/>
					<input style="width:100px;" id="purCategory${status.index+1}.name" name="purCategory.categoryName" value="${protaskItem.purCategory.categoryName }" class="sysicon siSearch" readonly="readonly">
				</td>
				-->
				<td>
					<input type="text" style="width:100px;" value="${protaskItem.goodsClass.goodsClassName},${protaskItem.purCategory.categoryName}" id="goodsClass${status.index+1}_purCategory${status.index+1}.id" name="goodsClass_purCategory.name" class="required sysicon siSearch" readonly="readonly">
					<input type="hidden" value="${protaskItem.goodsClass.objId}" id="goodsClass${status.index+1}.objId" name="goodsClass.objId"/>
					<input type="hidden" value="${protaskItem.goodsClass.goodsClassName}" id="goodsClass${status.index+1}.name" name="goodsClass.goodsClassName">
					<input type="hidden" value="${protaskItem.purCategory.objId}" id="purCategory${status.index+1}.objId" name="purCategory.objId" />
					<input type="hidden" value="${protaskItem.purCategory.categoryName}" id="purCategory${status.index+1}.name" name="purCategory.categoryName" >
				</td>
				<td><input type="text" name="goodsQty" onchange="procurementTaskForm.QtyOrPriceChange(this)" value="${protaskItem.goodsQty}" class="required digits amount" style="width:40px"/></td>
				<td><input type="text" name="goodsPrice" onchange="procurementTaskForm.QtyOrPriceChange(this)" value="<fmt:formatNumber value="${protaskItem.goodsPrice}" pattern="#,##0.00#" />" maxlength="10" class="required center" style="width:30px"/></td>
				<td><input type="text" name="goodsTotal" id="goodsTotal" style="width:60px" value="<fmt:formatNumber value="${protaskItem.goodsTotal}" pattern="#,##0.00#" />" disabled="disabled" class="r"/></td>
				<td class="operation">
					<a id="delRequireItem" href="javascript:void(0);" onclick="procurementTaskForm.removeRequireItem(this,'${protaskItem.objId }');return false;">删除</a>
				</td>
			</tr>
		</c:forEach>
	</c:if>
	</tbody>
</table>
</div>


<div class="conOperation">
	<button id="savePlan" type="button" onclick="procurementTaskForm.savePlanAndItem('submit')"><span>保存</span></button>
	<button name="historyBackBtn" type="button"><span>返回</span></button>
</div>


<table id="tempTable">
<tr class="hidden" id="tempTR">
	<td style="width:300px;">
		<textarea name="memo" maxlength="2000" style="width:300px;height:60px"></textarea>
	</td>
	<!--
	<td>
		<input type="hidden" value="" id="purCategory1.objId" name="purCategory.objId" class="input_medium"/>
		<input style="width:100px;" id="purCategory1.name" name="purCategory.categoryName" class="sysicon siSearch" readonly="readonly">
	</td>
	-->
	<td>
		<input style="width:100px;" id="goodsClass1_purCategory1.id" name="goodsClass_purCategory.name" class="required sysicon siSearch" readonly="readonly">
		<input type="hidden" value="" id="goodsClass1.objId" name="goodsClass.objId"/>
		<input type="hidden" value="" id="goodsClass1.name" name="goodsClass.goodsClassName"/>
		<input type="hidden" value="" id="purCategory1.objId" name="purCategory.objId"/>
		<input type="hidden" value="" id="purCategory1.name" name="purCategory.categoryName"/>
	</td>
	<td><input type="text" name="goodsQty" onchange="procurementTaskForm.QtyOrPriceChange(this)" value="0" style="width:40px"/></td>
	<td><input type="text" name="goodsPrice" onchange="procurementTaskForm.QtyOrPriceChange(this)" value="0.0" maxlength="10" style="width:30px"/></td>
	<td><input type="text" name="goodsTotal" id="goodsTotal" style="width:60px" value="0.0" disabled="disabled"/></td>
	<td class="operation">
		<a id="delRequireItem" href="javascript:void(0);" onclick="procurementTaskForm.removeRequireItem(this,'');return false;">删除</a>
	</td>
</tr>
</table>








