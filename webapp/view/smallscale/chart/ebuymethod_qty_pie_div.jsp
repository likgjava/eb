<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<input type="hidden" id="chartXml" value="${chartXml}" />
<div id="ebuymethodQtyPie"></div>
<%@ include file="/view/smallscale/chart/export_tip_div.jsp" %>

<div class="chart">
<table border="3">
	<tr align="center">
		<td>&nbsp;</td>
		<td>采购量（订单数）</td>
	</tr>
	<tr align="center">
		<td>直接订购</td>
		<td><c:choose><c:when test="${ebuymethodQtyList[0][2] != null}"><fmt:formatNumber value="${ebuymethodQtyList[0][2]}" pattern="#,###" /></c:when><c:otherwise>--</c:otherwise></c:choose></td>
	</tr>
	<tr align="center">
		<td>议价</td>
		<td><c:choose><c:when test="${ebuymethodQtyList[1][2] != null}"><fmt:formatNumber value="${ebuymethodQtyList[1][2]}" pattern="#,###" /></c:when><c:otherwise>--</c:otherwise></c:choose></td>
	</tr>
	<tr align="center">
		<td>竞价</td>
		<td><c:choose><c:when test="${ebuymethodQtyList[2][2] != null}"><fmt:formatNumber value="${ebuymethodQtyList[2][2]}" pattern="#,###" /></c:when><c:otherwise>--</c:otherwise></c:choose></td>
	</tr>
	<tr align="center" class="gap">
		<td>总计</td>
		<td><fmt:formatNumber value="${ebuymethodQtyList[0][2] + ebuymethodQtyList[1][2] + ebuymethodQtyList[2][2]}" pattern="#,###" /></td>
	</tr>
</table>
</div>

<script>
$(document).ready(function(){
	var myChart = new FusionCharts($("#initPath").val()+"/view/resource/plug-in/FusionChart/Charts/Pie2D.swf", "myChartId", "100%", "300");
	myChart.setDataXML($("#chartXml").val());
	myChart.render("ebuymethodQtyPie");
})
</script>