<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<style>
<!--
a.abtn {text-decoration:underline;font-style:inherit;cursor: hand;}
-->
</style>
<script type="text/javascript">
	var  bidItemList = {};
	bidItemList.viewTaskPlanSub = function(objId){
		$.epsDialog({
	    	title:'申报书条目-查看需求信息',
	    	url:$('#initPath').val()+'/TaskPlanController.do?method=toLookTaskPlanSubRequireInfoView&objId='+objId,
	    	width: '650',
	    	height: '450',
	    	maxWin:false,
	    	onClose: function(){}
		});
	}


	$(document).ready(function(){
		// 计算总报价
		$('#bid_items_table input[type=text].money').change(function(){
			var bidQuoteSum = 0;
			$('#bid_items_table input[type=text].money').each(function(i,n){
				bidQuoteSum += n.value * 1;
			})
			$('#bidQuoteSum').val(bidQuoteSum);
		})
		
		
	})
	

	
</script>
<table >
	<c:set var="i" value="0"></c:set>
	<c:forEach items="${bidItems}" var="bidItem">
		<tr>
			<td colspan="4" style="font-weight: bold;text-align: center;">明细${i+1}<font color="red">(${bidItem.packName})</font></td>
		</tr>
		<tr>
			<th>品目名称：</th>
			<td>
				<a onclick="bidItemList.viewTaskPlanSub('${bidItem.taskPlansubId}')" class="abtn" title="点击查看品目明细">${bidItem.purchaseName}</a>
				<input type="hidden" value="${bidItem.taskPlansubId}" name="bidItems[${i}].taskPlansubId" />
				<input type="hidden" name="bidItems[${i}].objId" value="${bidItem.objId}"></input>
				<input type="hidden" name="bidItems[${i}].purchaseId" value="${bidItem.purchaseId}"></input>
				<input type="hidden" name="bidItems[${i}].purchaseName" value="${bidItem.purchaseName}"></input>
				<input type="hidden" name="bidItems[${i}].number" value="${bidItem.number}"></input>
				<input type="hidden" name="bidItems[${i}].quotesum" value="${bidItem.quotesum}"></input>
				<input type="hidden" name="bidItems[${i}].price" value="${bidItem.price}"></input>
				<input type="hidden" name="bidItems[${i}].budgetId" value="${bidItem.budgetId}"></input>
				<input type="hidden" name="bidItems[${i}].budgetName" value="${bidItem.budgetName}"></input>
				<input type="hidden" name="bidItems[${i}].bidMemo" value="${bidItem.bidMemo}"></input>
				<input type="hidden" name="bidItems[${i}].measureUnit" value="${bidItem.measureUnit}"></input>
				<input type="hidden" name="bidItems[${i}].bidPackId" value="${bidItem.bidPackId}"></input>
				<input type="hidden" id="${i}" name="bidItems[${i}].packId" value="${bidItem.packId}"></input>
			</td>
		</tr>
		<tr>
			<th>数量：</th>
			<td>
				<span name="${i}">${bidItem.number}</span>
			</td>
		</tr> 
		<!--  
	    <c:if test="${project.tenderType == '04'}">
	    <tr>
			<th>项目经理：</th>
			<td>
				<input type="text" name="bidItems[${i}].projManager" value="${bidItem.projManager}" class="required  "  maxlength="25" style="width: 60px;"></input>
				<span class="eleRequired">*</span>
			</td>
		</tr>
		</c:if>
		-->
		<tr>
			<th>报价(元)：</th>
			<td>
				<input type="text" name="bidItems[${i}].price" value="${bidItem.price}" class="required money checkBail"  maxlength="25" style="width: 60px;"></input>
				<span class="eleRequired">*</span>
			</td>
		</tr>
		<c:set var="i" value="${i+1}"></c:set>
	</c:forEach>
</table>
