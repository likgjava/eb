<%@ page contentType="text/html;charset=UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<table class="frontTableList" id="GoodsSelectList">
	<thead>
		<tr>
			<th align="center">支付号</th>
			<th align="center">支付业务类型</th>
			<th align="right">支付金额(元)</th>
			<th align="center">支付人</th>
			<th align="center">支付时间</th>
			<th align="center">支付状态</th>
			<th align="center">公司名称</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${payRecordList}" var="payRecord" varStatus="status">
		<tr>
			<td align="center">${payRecord.payNo}</td>
			<td align="center">${payRecord.payBusinessTypeCN}</td>
			<td align="right"><fmt:formatNumber value="${payRecord.payAmount}" pattern="#,##0.00#" /></td>
			<td align="center">${payRecord.payPersonName}</td>
			<td align="center"><fmt:formatDate value="${payRecord.payTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<td align="center">${payRecord.payStatusCN}</td>
			<td align="center" title="${payRecord.payOrgName}"><c:choose><c:when test="${fn:length(payRecord.payOrgName) > 13}">${fn:substring(payRecord.payOrgName,0,12)}…</c:when><c:otherwise>${payRecord.payOrgName}</c:otherwise></c:choose></td>
		</tr>
		</c:forEach>
	</tbody>
</table>
<div class="conOperation"><button id="close" type="button"><span>关闭</span></button></div>

<script>
$(document).ready(function(){
	//关闭
	$("#close").click(function(){
		$('.epsDialogClose').trigger('click');
	})
})
</script>