<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<input type="hidden" id="chartXml" value="${chartXml}" />
<div id="purcategorysAmountPie"></div>
<%@ include file="/view/smallscale/chart/export_tip_div.jsp" %>

<div class="chart">
<table border="3">
	<tr align="center">
		<td>&nbsp;</td>
		<td><c:choose><c:when test="${userType == 'buyer'}">采购额</c:when><c:otherwise>销售额</c:otherwise></c:choose>（元）</td>
	</tr>
	<c:set var="totalAmount" value="0" />
	<c:forEach var="purcategorysAmount" items="${purcategorysAmountList}">
	<tr align="center">
		<td>${purcategorysAmount[1]}</td>
		<td><fmt:formatNumber value="${purcategorysAmount[2]}" pattern="#,##0.00#" /></td>
		<c:set var="totalAmount" value="${totalAmount + purcategorysAmount[2]}" />
	</tr>
	</c:forEach>
	<tr align="center" class="gap">
		<td>合计</td>
		<td><fmt:formatNumber value="${totalAmount}" pattern="#,##0.00#" /></td>
	</tr>
</table>
</div>

<script>
$(document).ready(function(){
	var myChart = new FusionCharts($("#initPath").val()+"/view/resource/plug-in/FusionChart/Charts/Pie2D.swf", "myChartId", "100%", "300");
	myChart.setDataXML($("#chartXml").val());
	myChart.render("purcategorysAmountPie");
})
</script>