<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<input type="hidden" id="chartXml" value="${chartXml}" />
<div id="purcategoryAmountQtySave"></div>
<%@ include file="/view/smallscale/chart/export_tip_div.jsp" %>

<div class="chart">
<table border="3">
	<tr align="center">
		<td width="55">&nbsp;</td>
		<td width="155"><c:choose><c:when test="${userType == 'buyer'}">采购额</c:when><c:otherwise>销售额</c:otherwise></c:choose>（元）</td>
		<td width="183"><c:choose><c:when test="${userType == 'buyer'}">采购量</c:when><c:otherwise>销售量</c:otherwise></c:choose>（订单数）</td>
		<c:if test="${userType == 'buyer'}">
			<td width="202">节省额（元）</td>
		</c:if>
	</tr>
	<c:set var="totalActualAmout" value="0" />
	<c:set var="totalQty" value="0" />
	<c:set var="totalSaveAmout" value="0" />
	<c:forEach var="amountQtySave" items="${amountQtySaveList}" varStatus="status">
	<tr align="center">
		<c:set var="totalActualAmout" value="${totalActualAmout + amountQtySave[0]}" />
		<c:set var="totalQty" value="${totalQty + amountQtySave[1]}" />
		<c:set var="totalSaveAmout" value="${totalSaveAmout + amountQtySave[2]}" />
		<td>${status.index+1}月</td>
		<td><c:choose><c:when test="${amountQtySave[0] != null}"><fmt:formatNumber value="${amountQtySave[0]}" pattern="#,##0.00#" /></c:when><c:otherwise>--</c:otherwise></c:choose></td>
		<td><c:choose><c:when test="${amountQtySave[1] != null}">${amountQtySave[1]}</c:when><c:otherwise>--</c:otherwise></c:choose></td>
		<c:if test="${userType == 'buyer'}">
			<td><c:choose><c:when test="${amountQtySave[2] != null}"><fmt:formatNumber value="${amountQtySave[2]}" pattern="#,##0.00#" /></c:when><c:otherwise>--</c:otherwise></c:choose></td>
		</c:if>
	</tr>
	</c:forEach>
	<tr align="center" class="gap">
		<td>合计</td>
		<td><fmt:formatNumber value="${totalActualAmout}" pattern="#,##0.00#" /></td>
		<td><fmt:formatNumber value="${totalQty}" pattern="#,###" /></td>
		<c:if test="${userType == 'buyer'}">
			<td><fmt:formatNumber value="${totalSaveAmout}" pattern="#,##0.00#" /></td>
		</c:if>
	</tr>
</table>
</div>

<script>
$(document).ready(function(){

	var myChart = new FusionCharts($("#initPath").val()+"/view/resource/plug-in/FusionChart/Charts/MSCombiDY2D.swf", "myChartId", "100%", "300");
	myChart.setDataXML($("#chartXml").val());
	myChart.render("purcategoryAmountQtySave");
})
</script>