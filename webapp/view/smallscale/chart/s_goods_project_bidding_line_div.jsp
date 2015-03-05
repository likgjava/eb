<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<input type="hidden" id="chartXml" value="${chartXml}" />
<div id="goodsProjectBiddingChartDiv"></div>
<%@ include file="/view/smallscale/chart/export_tip_div.jsp" %>

<div class="chart">
<table border="3">
	<tr align="center">
		<td >项目名称</td>
		<td class="title1">商品报价(元)</td>
	</tr>
	<c:forEach var="goodsBiddingPrice" items="${goodsProjectBiddingList}" varStatus="status">
	<tr align="center">
		<td>${goodsBiddingPrice.project.projName}</td>
		<td><fmt:formatNumber value="${goodsBiddingPrice.goodsPrice}" pattern="#,###" /></td>
	</tr>
	</c:forEach>
</table>
</div>

<script>
$(document).ready(function(){
	var myChart = new FusionCharts($("#initPath").val()+"/view/resource/plug-in/FusionChart/Charts/MSLine.swf", "myChartId", "100%", "300");
	myChart.setDataXML($("#chartXml").val());
	myChart.render("goodsProjectBiddingChartDiv");
})
</script>