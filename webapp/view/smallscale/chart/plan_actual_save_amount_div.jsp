<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<input type="hidden" id="chartXml" value="${chartXml}" />
<div id="planActualSaveAmount"></div>
<%@ include file="/view/smallscale/chart/export_tip_div.jsp" %>

<div class="chart">
<table id="saveAmountCompareTable" border="3">
	<tr align="center">
		<td width="75">&nbsp;</td>
		<td width="194">采购预算（元）</td>
		<td width="253">实际额（元）</td>
		<td width="281">节省额（元）</td>
	</tr>
	<c:set var="totalSaveAmout1" value="0" />
	<c:set var="totalSaveAmout2" value="0" />
	<c:set var="totalGapAmount" value="0" />
	<c:forEach var="amounts" items="${amountList}" varStatus="status">
	<tr align="center">
		<c:set var="totalPlanAmount" value="${totalPlanAmount + amounts[0]}" />
		<c:set var="totalActualAmount" value="${totalActualAmount + amounts[1]}" />
		<c:set var="totalSaveAmount" value="${totalSaveAmount + amounts[2]}" />
		<td>${status.index+1}月</td>
		<td><c:choose><c:when test="${amounts[0] != null}"><fmt:formatNumber value="${amounts[0]}" pattern="#,##0.00#" /></c:when><c:otherwise>--</c:otherwise></c:choose></td>
		<td><c:choose><c:when test="${amounts[1] != null}"><fmt:formatNumber value="${amounts[1]}" pattern="#,##0.00#" /></c:when><c:otherwise>--</c:otherwise></c:choose></td>
		<td><c:choose><c:when test="${amounts[2] != null}"><fmt:formatNumber value="${amounts[2]}" pattern="#,##0.00#" /></c:when><c:otherwise>--</c:otherwise></c:choose></td>
	</tr>
	</c:forEach>
	<tr align="center" class="gap">
		<td>合计</td>
		<td><fmt:formatNumber value="${totalPlanAmount}" pattern="#,##0.00#" /></td>
		<td><fmt:formatNumber value="${totalActualAmount}" pattern="#,##0.00#" /></td>
		<td><fmt:formatNumber value="${totalSaveAmount}" pattern="#,##0.00#" /></td>
	</tr>
</table>
</div>

<script>
$(document).ready(function(){
	var myChart = new FusionCharts($("#initPath").val()+"/view/resource/plug-in/FusionChart/Charts/MSCombi2D.swf", "myChartId", "100%", "300");
	myChart.setDataXML($("#chartXml").val());
	myChart.render("planActualSaveAmount");
})
</script>