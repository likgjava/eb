<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<input type="hidden" id="chartXml" value="${chartXml}" />
<div id="biddingWinnerRecordsColumnDiv2"></div>
<%@ include file="/view/smallscale/chart/export_tip_div.jsp" %>

<div class="chart">
<table border="3">
	<tr align="center">
		<td>&nbsp;</td>
		<td>中标数</td>
		<td>未中标数</td>
	</tr>
	<c:forEach var="biddingWinner" items="${all_biddingWinnerList}">
		<tr align="center">
			<td>${biddingWinner[0]}</td>
			<td>${biddingWinner[1]}</td>
			<td>${biddingWinner[2]}</td>
		</tr>
	</c:forEach>
</table>
</div>

<script>
$(document).ready(function(){
	var myChart = new FusionCharts($("#initPath").val()+"/view/resource/plug-in/FusionChart/Charts/StackedColumn2D.swf", "myChartId", "100%", "300");
	myChart.setDataXML($("#chartXml").val());
	myChart.render("biddingWinnerRecordsColumnDiv2");
})
</script>