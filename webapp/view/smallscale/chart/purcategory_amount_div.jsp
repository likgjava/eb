<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<input type="hidden" id="chartXml" value="${chartXml}" />
<div id="purcategoryAmount"></div>
<%@ include file="/view/smallscale/chart/export_tip_div.jsp" %>

<div class="chart">
<table border="3">
	<tr align="center">
		<td rowspan="2">&nbsp;</td>
		<td class="title1" colspan="${fn:length(purCategoryNameList)}"><c:choose><c:when test="${userType == 'buyer'}">采购额</c:when><c:otherwise>销售额</c:otherwise></c:choose>(元)</td>
	</tr>
	<tr align="center">
		<c:forEach var="purCategoryName" items="${purCategoryNameList}" varStatus="status">
			<td>${purCategoryName}</td>
		</c:forEach>
	</tr>
	<c:forEach var="amounts" items="${amountsList}" varStatus="status">
	<tr align="center">
		<td>${status.index+1}月</td>
		<c:forEach var="amount" items="${amounts}" varStatus="status2">
			<td><c:choose><c:when test="${amount != null}"><fmt:formatNumber value="${amount}" pattern="#,##0.00#" /></c:when><c:otherwise>--</c:otherwise></c:choose></td>
		</c:forEach>
	</tr>
	</c:forEach>
	<tr align="center" class="gap">
		<td>合计</td>
		<c:forEach var="totalAmount" items="${totalAmountList}" varStatus="status">
			<td><fmt:formatNumber value="${totalAmount}" pattern="#,##0.00#" /></td>
		</c:forEach>
	</tr>
</table>
</div>

<script>
$(document).ready(function(){
	var myChart = new FusionCharts($("#initPath").val()+"/view/resource/plug-in/FusionChart/Charts/MSColumn2D.swf", "myChartId", "100%", "300");
	myChart.setDataXML($("#chartXml").val());
	myChart.render("purcategoryAmount");
})
</script>