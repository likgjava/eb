<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<input type="hidden" id="chartXml" value="${chartXml}" />
<div id="saveAmountCompare"></div>
<%@ include file="/view/smallscale/chart/export_tip_div.jsp" %>

<div class="chart">
<table id="saveAmountCompareTable" border="3">
	<tr align="center">
		<td rowspan="2">&nbsp;</td>
		<td class="title1" colspan="2">节省额(元)</td>
	</tr>
	<tr align="center">
		<td>${param.year1}年</td>
		<td>${param.year2}年</td>
		<td class="gap">差 额</td>
	</tr>
	<c:set var="totalSaveAmout1" value="0" />
	<c:set var="totalSaveAmout2" value="0" />
	<c:set var="totalGapAmount" value="0" />
	<c:forEach var="saveAmout1" items="${list1}" varStatus="status">
	<tr align="center">
		<c:set var="totalSaveAmout1" value="${totalSaveAmout1 + saveAmout1}" />
		<c:set var="totalSaveAmout2" value="${totalSaveAmout2 + list2[status.index]}" />
		<c:set var="totalGapAmount" value="${totalGapAmount + (list2[status.index] - saveAmout1)}" />
		<td>${status.index+1}月</td>
		<td><c:choose><c:when test="${saveAmout1 != null}"><fmt:formatNumber value="${saveAmout1}" pattern="#,##0.00#" /></c:when><c:otherwise>--</c:otherwise></c:choose></td>
		<td><c:choose><c:when test="${list2[status.index] != null}"><fmt:formatNumber value="${list2[status.index]}" pattern="#,##0.00#" /></c:when><c:otherwise>--</c:otherwise></c:choose></td>
		<td class="gap"><fmt:formatNumber value="${list2[status.index] - saveAmout1}" pattern="#,##0.00#" /></td>
	</tr>
	</c:forEach>
	<tr align="center" class="gap">
		<td>合计</td>
		<td><fmt:formatNumber value="${totalSaveAmout1}" pattern="#,##0.00#" /></td>
		<td><fmt:formatNumber value="${totalSaveAmout2}" pattern="#,##0.00#" /></td>
		<td><fmt:formatNumber value="${totalGapAmount}" pattern="#,##0.00#" /></td>
	</tr>
</table>
</div>

<script>
$(document).ready(function(){

	var myChart = new FusionCharts($("#initPath").val()+"/view/resource/plug-in/FusionChart/Charts/MSColumn2D.swf", "myChartId", "100%", "300");
	myChart.setDataXML($("#chartXml").val());
	myChart.render("saveAmountCompare");
})
</script>