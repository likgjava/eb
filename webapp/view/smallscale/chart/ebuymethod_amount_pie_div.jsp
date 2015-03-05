<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<input type="hidden" id="chartXml" value="${chartXml}" />
<div id="ebuymethodAmountPie"></div>
<%@ include file="/view/smallscale/chart/export_tip_div.jsp" %>

<div class="chart">
<table border="3">
	<tr align="center">
		<td>&nbsp;</td>
		<td>采购额（元）</td>
	</tr>
	<tr align="center">
		<td>直接订购</td>
		<td><c:choose><c:when test="${ebuymethodAmountList[0][1] != null}"><fmt:formatNumber value="${ebuymethodAmountList[0][1]}" pattern="#,##0.00#" /></c:when><c:otherwise>--</c:otherwise></c:choose></td>
	</tr>
	<tr align="center">
		<td>议价</td>
		<td><c:choose><c:when test="${ebuymethodAmountList[1][1] != null}"><fmt:formatNumber value="${ebuymethodAmountList[1][1]}" pattern="#,##0.00#" /></c:when><c:otherwise>--</c:otherwise></c:choose></td>
	</tr>
	<tr align="center">
		<td>竞价</td>
		<td><c:choose><c:when test="${ebuymethodAmountList[2][1] != null}"><fmt:formatNumber value="${ebuymethodAmountList[2][1]}" pattern="#,##0.00#" /></c:when><c:otherwise>--</c:otherwise></c:choose></td>
	</tr>
	<tr align="center" class="gap">
		<td>总计</td>
		<td><fmt:formatNumber value="${ebuymethodAmountList[0][1] + ebuymethodAmountList[1][1] + ebuymethodAmountList[2][1]}" pattern="#,##0.00#" /></td>
	</tr>
</table>
</div>

<script>
$(document).ready(function(){
	var myChart = new FusionCharts($("#initPath").val()+"/view/resource/plug-in/FusionChart/Charts/Pie2D.swf", "myChartId", "100%", "300");
	myChart.setDataXML($("#chartXml").val());
	myChart.render("ebuymethodAmountPie");
})
</script>