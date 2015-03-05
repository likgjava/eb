<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<input type="hidden" id="chartXml" value="${chartXml}" />
<div id="duringGoodsQtyPieDiv1"></div>
<%@ include file="/view/smallscale/chart/export_tip_div.jsp" %>

<div class="chart">
<table border="3">
	<tr align="center">
		<td>商品名称</td>
		<td><c:choose><c:when test="${userType == 'buyer'}">采购量</c:when><c:otherwise>销售量</c:otherwise></c:choose>（商品数）</td>
	</tr>
	<c:set var="totalQty" value="0" />
	<c:forEach var="durGoodsQty" items="${durGoodsQtyList}">
	<tr align="center">
		<td>${durGoodsQty[0]}</td>
		<td><fmt:formatNumber value="${durGoodsQty[2]}" pattern="#,###" /></td>
		<c:set var="totalQty" value="${totalQty + durGoodsQty[2]}" />
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
	myChart.render("duringGoodsQtyPieDiv1");
})
</script>