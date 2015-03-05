<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<input type="hidden" id="chartXml" value="${chartXml}" />
<div id="ebuymethodQty"></div>
<%@ include file="/view/smallscale/chart/export_tip_div.jsp" %>

<div class="chart">
<table border="3">
	<tr align="center">
		<td>&nbsp;</td>
		<td>竞价（订单数）</td>
		<td>议价（订单数）</td>
		<td>直接订购（订单数）</td>
		<td>总量（订单数）</td>
	</tr>
	<c:set var="totalQty1" value="0" />
	<c:set var="totalQty2" value="0" />
	<c:set var="totalQty3" value="0" />
	<c:forEach var="qtys1" items="${qtyList1}" varStatus="status">
	<tr align="center">
		<c:set var="totalQty1" value="${totalQty1 + qtys1[1]}" />
		<c:set var="totalQty2" value="${totalQty2 + qtyList2[status.index][1]}" />
		<c:set var="totalQty3" value="${totalQty3 + qtyList3[status.index][1]}" />
		<td>${status.index+1}月</td>
		<td><c:choose><c:when test="${qtys1[1] != null}"><fmt:formatNumber value="${qtys1[1]}" pattern="#,###" /></c:when><c:otherwise>--</c:otherwise></c:choose></td>
		<td><c:choose><c:when test="${qtyList2[status.index][1] != null}"><fmt:formatNumber value="${qtyList2[status.index][1]}" pattern="#,###" /></c:when><c:otherwise>--</c:otherwise></c:choose></td>
		<td><c:choose><c:when test="${qtyList3[status.index][1] != null}"><fmt:formatNumber value="${qtyList3[status.index][1]}" pattern="#,###" /></c:when><c:otherwise>--</c:otherwise></c:choose></td>
		<td class="gap"><fmt:formatNumber value="${qtys1[1] + qtyList2[status.index][1] + qtyList3[status.index][1]}" pattern="#,###" /></td>
	</tr>
	</c:forEach>
	<tr align="center" class="gap">
		<td>合计</td>
		<td><fmt:formatNumber value="${totalQty1}" pattern="#,###" /></td>
		<td><fmt:formatNumber value="${totalQty2}" pattern="#,###" /></td>
		<td><fmt:formatNumber value="${totalQty3}" pattern="#,###" /></td>
		<td><fmt:formatNumber value="${totalQty1 + totalQty2 + totalQty3}" pattern="#,###" /></td>
	</tr>
</table>
</div>

<script>
$(document).ready(function(){
	var myChart = new FusionCharts($("#initPath").val()+"/view/resource/plug-in/FusionChart/Charts/StackedColumn2D.swf", "myChartId", "100%", "300");
	myChart.setDataXML($("#chartXml").val());
	myChart.render("ebuymethodQty");
})
</script>