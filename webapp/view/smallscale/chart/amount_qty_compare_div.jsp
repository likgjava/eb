<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<input type="hidden" id="chartXml" value="${chartXml}" />
<div id="amountQtyCompare"></div>
<%@ include file="/view/smallscale/chart/export_tip_div.jsp" %>

<div class="chart">
<table border="3">
	<tr align="center">
		<td rowspan="2">&nbsp;</td>
		<td class="title1" colspan="2"><c:choose><c:when test="${userType == 'buyer'}">采购额</c:when><c:otherwise>销售额</c:otherwise></c:choose>(元)</td>
		<td class="title1" colspan="2"><c:choose><c:when test="${userType == 'buyer'}">采购量</c:when><c:otherwise>销售量</c:otherwise></c:choose>(订单数)</td>
	</tr>
	<tr align="center">
		<td>${param.year1}年</td>
		<td>${param.year2}年</td>
		<td>${param.year1}年</td>
		<td>${param.year2}年</td>
	</tr>
	<c:set var="totalAmount1" value="0" />
	<c:set var="totalAmount2" value="0" />
	<c:set var="totalQty1" value="0" />
	<c:set var="totalQty2" value="0" />
	<c:forEach var="amountQty1" items="${amountQtyList1}" varStatus="status">
	<tr align="center">
		<td>${status.index+1}月</td>
		<td><c:choose><c:when test="${amountQty1[1] != null}"><fmt:formatNumber value="${amountQty1[1]}" pattern="#,##0.00#" /></c:when><c:otherwise>--</c:otherwise></c:choose></td>
		<td><c:choose><c:when test="${amountQtyList2[status.index][1] != null}"><fmt:formatNumber value="${amountQtyList2[status.index][1]}" pattern="#,##0.00#" /></c:when><c:otherwise>--</c:otherwise></c:choose></td>
		<td><c:choose><c:when test="${amountQty1[3] != null}"><fmt:formatNumber value="${amountQty1[3]}" pattern="#,###" /></c:when><c:otherwise>--</c:otherwise></c:choose></td>
		<td><c:choose><c:when test="${amountQtyList2[status.index][3] != null}"><fmt:formatNumber value="${amountQtyList2[status.index][3]}" pattern="#,###" /></c:when><c:otherwise>--</c:otherwise></c:choose></td>
		<c:set var="totalAmount1" value="${totalAmount1 + amountQty1[1]}" />
		<c:set var="totalAmount2" value="${totalAmount2 + amountQtyList2[status.index][1]}" />
		<c:set var="totalQty1" value="${totalQty1 + amountQty1[3]}" />
		<c:set var="totalQty2" value="${totalQty2 + amountQtyList2[status.index][3]}" />
	</tr>
	</c:forEach>
	<tr align="center" class="gap">
		<td>合计</td>
		<td><fmt:formatNumber value="${totalAmount1}" pattern="#,##0.00#" /></td>
		<td><fmt:formatNumber value="${totalAmount2}" pattern="#,##0.00#" /></td>
		<td><fmt:formatNumber value="${totalQty1}" pattern="#,###" /></td>
		<td><fmt:formatNumber value="${totalQty2}" pattern="#,###" /></td>
	</tr>
</table>
</div>

<script>
$(document).ready(function(){
	//创建图表	
	var myChart = new FusionCharts($("#initPath").val()+"/view/resource/plug-in/FusionChart/Charts/MSCombiDY2D.swf", "myChartId", "100%", "300", "0", "0");
	myChart.setDataXML($("#chartXml").val());
	myChart.render("amountQtyCompare");
})
</script>