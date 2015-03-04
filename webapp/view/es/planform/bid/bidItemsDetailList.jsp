<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<table>
	<c:set var="i" value="0"></c:set>
	<c:forEach items="${bidItems}" var="bidItem">
		<tr>
			<td colspan="4" style="font-weight: bold;text-align: center;">明细${i+1}<font color="red">(${bidItem.packName})</font></td>
		</tr>
		<tr>
			<th>品目名称</th>
			<td>
				<span>${bidItem.purchaseName}</span>
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
				<input type="hidden" name="bidItems[${i}].packId" value="${bidItem.packId}"></input>
			</td>
		</tr>
		<tr>
			<th>数量</th>
			<td>
				<span>${bidItem.number}</span>
			</td>
		</tr>
		<tr>
			<th>报价(元)</th>
			<td>
				<span><fmt:formatNumber value="${bidItem.price}" type="currency"></fmt:formatNumber></span>
			</td>
		</tr>
		<c:set var="i" value="${i+1}"></c:set>
	</c:forEach>
</table>