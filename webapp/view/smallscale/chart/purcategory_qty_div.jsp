<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<input type="hidden" id="chartXml" value="${chartXml}" />
<div id="purcategoryQty"></div>
<%@ include file="/view/smallscale/chart/export_tip_div.jsp" %>

<div class="chart">
<table border="3">
	<tr align="center">
		<td rowspan="2">&nbsp;</td>
		<td class="title1" colspan="${fn:length(purCategoryNameList)}"><c:choose><c:when test="${userType == 'buyer'}">采购量</c:when><c:otherwise>销售量</c:otherwise></c:choose>(订单数)</td>
	</tr>
	<tr align="center">
		<c:forEach var="purCategoryName" items="${purCategoryNameList}" varStatus="status">
			<td>${purCategoryName}</td>
		</c:forEach>
	</tr>
	<c:forEach var="qtys" items="${qtysList}" varStatus="status">
	<tr align="center">
		<td>${status.index+1}月</td>
		<c:forEach var="qty" items="${qtys}" varStatus="status2">
			<td><c:choose><c:when test="${qty != null}"><fmt:formatNumber value="${qty}" pattern="#,###" /></c:when><c:otherwise>--</c:otherwise></c:choose></td>
		</c:forEach>
	</tr>
	</c:forEach>
	<tr align="center" class="gap">
		<td>合计</td>
		<c:forEach var="totalQty" items="${totalQtyList}" varStatus="status">
			<td><fmt:formatNumber value="${totalQty}" pattern="#,###" /></td>
		</c:forEach>
	</tr>
</table>
</div>

<script>
$(document).ready(function(){
	var myChart = new FusionCharts($("#initPath").val()+"/view/resource/plug-in/FusionChart/Charts/MSLine.swf", "myChartId", "100%", "300");
	myChart.setDataXML($("#chartXml").val());
	myChart.render("purcategoryQty");
})
</script>