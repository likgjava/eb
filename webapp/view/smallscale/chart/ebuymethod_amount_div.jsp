<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<input type="hidden" id="chartXml" value="${chartXml}" />
<div id="ebuymethodAmount"></div>
<%@ include file="/view/smallscale/chart/export_tip_div.jsp" %>

<div class="chart">
<table border="3">
	<tr align="center">
		<td width="55">&nbsp;</td>
		<td width="155">竞价（元）</td>
		<td width="183">议价（元）</td>
		<td width="202">直接订购（元）</td>
		<td width="202">总额（元）</td>
	</tr>
	<c:set var="totalAmout1" value="0" />
	<c:set var="totalAmout2" value="0" />
	<c:set var="totalAmout3" value="0" />
	<c:forEach var="amounts1" items="${amountList1}" varStatus="status">
	<tr align="center">
		<c:set var="totalAmout1" value="${totalAmout1 + amounts1[0]}" />
		<c:set var="totalAmout2" value="${totalAmout2 + amountList2[status.index][0]}" />
		<c:set var="totalAmout3" value="${totalAmout3 + amountList3[status.index][0]}" />
		<td>${status.index+1}月</td>
		<td><c:choose><c:when test="${amounts1[0] != null}"><fmt:formatNumber value="${amounts1[0]}" pattern="#,##0.00#" /></c:when><c:otherwise>--</c:otherwise></c:choose></td>
		<td><c:choose><c:when test="${amountList2[status.index][0] != null}"><fmt:formatNumber value="${amountList2[status.index][0]}" pattern="#,##0.00#" /></c:when><c:otherwise>--</c:otherwise></c:choose></td>
		<td><c:choose><c:when test="${amountList3[status.index][0] != null}"><fmt:formatNumber value="${amountList3[status.index][0]}" pattern="#,##0.00#" /></c:when><c:otherwise>--</c:otherwise></c:choose></td>
		<td class="gap"><fmt:formatNumber value="${amounts1[0] + amountList2[status.index][0] + amountList3[status.index][0]}" pattern="#,##0.00#" /></td>
	</tr>
	</c:forEach>
	<tr align="center" class="gap">
		<td>合计</td>
		<td><fmt:formatNumber value="${totalAmout1}" pattern="#,##0.00#" /></td>
		<td><fmt:formatNumber value="${totalAmout2}" pattern="#,##0.00#" /></td>
		<td><fmt:formatNumber value="${totalAmout3}" pattern="#,##0.00#" /></td>
		<td><fmt:formatNumber value="${totalAmout1 + totalAmout2 + totalAmout3}" pattern="#,##0.00#" /></td>
	</tr>
</table>
</div>

<script>
$(document).ready(function(){
	var myChart = new FusionCharts($("#initPath").val()+"/view/resource/plug-in/FusionChart/Charts/StackedColumn2D.swf", "myChartId", "100%", "300");
	myChart.setDataXML($("#chartXml").val());
	myChart.render("ebuymethodAmount");
})
</script>