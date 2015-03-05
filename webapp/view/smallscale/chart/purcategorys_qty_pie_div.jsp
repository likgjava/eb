<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<input type="hidden" id="chartXml" value="${chartXml}" />
<div id="purcategorysQtyPie"></div>
<%@ include file="/view/smallscale/chart/export_tip_div.jsp" %>

<div class="chart">
<table border="3">
	<tr align="center">
		<td>&nbsp;</td>
		<td><c:choose><c:when test="${userType == 'buyer'}">采购量</c:when><c:otherwise>销售量</c:otherwise></c:choose>（订单数）</td>
	</tr>
	<c:set var="totalQty" value="0" />
	<c:forEach var="purcategorysQty" items="${purcategorysQtyList}">
	<tr align="center">
		<td>${purcategorysQty[1]}</td>
		<td><fmt:formatNumber value="${purcategorysQty[3]}" pattern="#,###" /></td>
		<c:set var="totalQty" value="${totalQty + purcategorysQty[3]}" />
	</tr>
	</c:forEach>
	<tr align="center" class="gap">
		<td>合计</td>
		<td><fmt:formatNumber value="${totalQty}" pattern="#,###" /></td>
	</tr>
</table>
</div>

<script>
$(document).ready(function(){
	var myChart = new FusionCharts($("#initPath").val()+"/view/resource/plug-in/FusionChart/Charts/Pie2D.swf", "myChartId", "100%", "300");
	myChart.setDataXML($("#chartXml").val());
	myChart.render("purcategorysQtyPie");
})
</script>