<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/agreement/bargin/purchaseplan/purchase_plan_detail.js"></script>

<div class="formLayout form2Pa">
  <form id="procurementForm" method="post">
  <input type="hidden" name="objId" id="objId" value="${procurementtask.objId}"/>
  <h4><span>采购任务</span></h4>
    <ul>
      <li class="fullLine"><label>任务书编号：</label><span>${procurementtask.taskNo }</span></li>
      <li><label>时期类型：</label>
 		<c:if test="${procurementtask.periodType=='year'}">
 			<span>年采购任务</span>
 		</c:if>
 		<c:if test="${procurementtask.periodType=='month'}">
 			<span>月采购任务</span>
 		</c:if>
 		<c:if test="${procurementtask.periodType=='quarter'}">
 			<span>季度采购任务</span>
 		</c:if>
      </li>
      <li><label>时期值：</label>
      <span>
      	<c:if test="${procurementtask.periodType!='quarter'}">
 			${procurementtask.periodValue}
 		</c:if>
 		<c:if test="${procurementtask.periodType=='quarter'}">
	 		${fn:split(procurementtask.periodValue,'-')[0]}年${fn:split(procurementtask.periodValue,'-')[1]}季度
 		</c:if>
      </span>
      </li>
      <li><label>预算总额：</label><span id="sumTotal"><fmt:formatNumber value="${procurementtask.sumTotal}" pattern="#,##0.00#" /></span>（元）</li>
      <li><label>采购总数量：</label><span id="sumQty">${procurementtask.sumQty }</span></li>
      <li class="fullLine"><label>备注：</label><span>${procurementtask.memo }</span></li>
    </ul>
  </form>
  
<h4><span>采购任务明细</span></h4>
<table class="frontTableList" id="procurementTaskItem">
	<thead>
		<tr>
			<th style="width:40px;"><input title="全选" type="checkbox" name="checkAll" onclick="procurementTaskForm.checkAllOrNot('checkCell',this)" /></th>
			<th >描述</th>
			<th >分类、品目</th>
			<th style="width:60px;">采购数量</th>
			<th style="width:60px;">完成数量</th>
			<th style="width:60px;">可用数量</th>
			<th style="width:110px;">期望价（元）</th>
			<th style="width:110px;">预算资金（元）</th>
			<th style="width:110px;">完成资金（元）</th>
			<th>选购商品</th>
		</tr>
	</thead>
	<tbody id="content">
	
	<c:if test="${fn:length(procurementtask.protaskItems)>0}">
		<c:forEach var="protaskItem" items="${procurementtask.protaskItems}" varStatus="status">
			<tr id="tempTR${status.index+1}">
				<td align="center">
					<input name="checkCell${protaskItem.objId}" type="checkbox" onclick="procurementTaskForm.checkAllOrNot('${protaskItem.objId}',this)" value="${protaskItem.objId}" >
					<input name="objId" type="hidden" value="${protaskItem.objId }">
				</td>
				<td>
					<span title="${protaskItem.memo}"><c:choose><c:when test="${fn:length(protaskItem.memo) > 18}">${fn:substring(protaskItem.memo,0,17)}…</c:when><c:otherwise>${protaskItem.memo}</c:otherwise></c:choose></span>
				</td>
				<td>
					<span>${protaskItem.goodsClass.goodsClassName },${protaskItem.purCategory.categoryName}</span>
				</td>
				<td align="right"><span>${protaskItem.goodsQty}</span></td>
				<td align="right"><span><a href="javascript:void(0);" onclick="procurementTaskForm.finishedOrderView('${protaskItem.objId}');">${protaskItem.finGoodSqty}</a></span></td>
				<td align="right"><span>${protaskItem.finQty}</span></td>
				<td align="right"><span><fmt:formatNumber value="${protaskItem.goodsPrice}" pattern="#,##0.00#" /></span></td>
				<td align="right"><span><fmt:formatNumber value="${protaskItem.goodsTotal}" pattern="#,##0.00#" /></span></td>
				<td align="right"><span><fmt:formatNumber value="${protaskItem.finGoodTotal}" pattern="#,##0.00#" /></span></td>
				<td align="center"><span><a href="<%=request.getContextPath()%>/GoodsShowController.do?method=toGoodsList&rp=21&page=1&goodsClassCode=${protaskItem.goodsClass.goodsClassCode }" target="_blank">选购</a></span></td>
			</tr>
		</c:forEach>
	</c:if>
	</tbody>
</table>
</div>

<div class="conOperation">
	<button name="historyBackBtn" type="button"><span>返回</span></button>
	<button name="createProjectBtn" type="button"><span>创建竞价项目</span></button>
</div>

<table id="tempTable">
<tr class="hidden" id="tempTR">
	<td style="width:300px;">
		<textarea name="memo" maxlength="2000" class="required" style="width:300px;height:60px"></textarea>
	</td>
	<td>
		<input type="hidden" value="" id="purCategory1.objId" name="purCategory.objId" class="input_medium"/>
		<input style="width:100px;" id="purCategory1.name" name="purCategory.categoryName" class="sysicon siSearch" readonly="readonly">
	</td>
	<td><input type="text" name="goodsQty" onchange="procurementTaskForm.QtyOrPriceChange(this)" value="0" class="required digits amount" style="width:40px"/></td>
	<td><input type="text" name="goodsPrice" onchange="procurementTaskForm.QtyOrPriceChange(this)" value="0.0" maxlength="10" class="required center" style="width:30px"/></td>
	<td><input type="text" name="goodsTotal" id="goodsTotal" style="width:60px" value="0.0" disabled="disabled" class="r"/></td>
	<td class="center">
		<a id="delRequireItem" href="javascript:void(0);" onclick="procurementTaskForm.removeRequireItem(this,'');return false;">删除</a>
	</td>
</tr>
</table>
