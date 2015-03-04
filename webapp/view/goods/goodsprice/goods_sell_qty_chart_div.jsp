<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>FusionCharts v3 Documentation</title>
</head>

<!--页面按钮开始-->
<input type="hidden" name="goodsId" id="goodsId" value="${param.goodsId }">
<input type="hidden" name="districtId" id="districtId" value="${param.districtId }">

<body >
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td valign="top" align="center">
		<div id="chartdiv1" align="center"></div>
		</td>
		
		<td valign="top" align="center">
		<div id="chartdiv2" align="center"></div>
		</td>
	</tr>
</table>

<br>
<c:if test="${fn:length(priceList)>0 }">
<div class="formTips attention">
	<ul>
		<li>其他城市的行情趋势：
		<c:forEach var="price" items="${priceList}">
		<a href="javascript:void(0);" onclick="goodsSellQtyChartDiv.getChart('${param.goodsId }','${price[0]}');return false;"><span>${price[1]}</span></a>
		</c:forEach>
		</li>
	</ul>
</div>
</c:if>

<div class="conOperation">
	<button type="button" onclick="goodsSellQtyChartDiv.closeDiv()" ><span>关闭</span></button>
</div>

</body>
<script>
var goodsSellQtyChartDiv = {}
goodsSellQtyChartDiv.maChart;

//关闭
goodsSellQtyChartDiv.closeDiv = function(){
	$("#priceChartDiv").find(".epsDialogClose").click();
}

//获得图形
goodsSellQtyChartDiv.getChart = function(goodsId,districtId){
	goodsSellQtyChartDiv.myChart = new FusionCharts($("#initPath").val()+"/view/resource/plug-in/FusionChart/Charts/MSLine.swf", "myChartId", "370", "250");
	var url = $("#initPath").val()+"/GoodsPriceShowController.do?method=getGoodsPriceChartXml&goodsId="+$('#goodsId').val()+"&districtId="+districtId;
	goodsSellQtyChartDiv.myChart.setDataURL(escape(url));
	goodsSellQtyChartDiv.myChart.render("chartdiv1");
}

$(document).ready(function(){
	var goodsId = $("#goodsId").val()!=""?$("#goodsId").val():$("#goodsIdDiv").val() ;
	var districtId = $("input[name=districtId]").val()
	goodsSellQtyChartDiv.getChart(goodsId,districtId);
})
</script>