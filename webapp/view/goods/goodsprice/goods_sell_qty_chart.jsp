<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>FusionCharts v3 Documentation</title>
</head>

<!--页面按钮开始-->
<body >
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td valign="top" align="center">
		<div id="chartdiv" align="center"></div>
		</td>
		<c:if test="${fn:length(hotTagsList)>0}">
			<td valign="top" align="center">
			<div id="priceChartDiv" align="center"></div>
		</td>
		</c:if>
	</tr>
</table>
</body>
<script>
$(document).ready(function(){
	var myChart = new FusionCharts($("#initPath").val()+"/view/resource/plug-in/FusionChart/Charts/MSCombiDY2D.swf", "myChartId", "370", "250");
	var url = $("#initPath").val()+"/GoodsPriceShowController.do?method=getGoodsSellChartXml&goodsId="+$('#goodsId').val();
	myChart.setDataURL(escape(url));
	myChart.render("chartdiv");
	if($("#priceChartDiv")!=null&&$("#priceChartDiv").html()!=null){
		var myChart1 = new FusionCharts($("#initPath").val()+"/view/resource/plug-in/FusionChart/Charts/MSLine.swf", "myChartId", "370", "250");
		var url2 = $("#initPath").val()+"/GoodsPriceShowController.do?method=getGoodsPriceChartXml&goodsId="+$("#goodsId").val();
		myChart1.setDataURL(escape(url2));
		myChart1.render("priceChartDiv");
		myChart1 = null;
	}
})
</script>