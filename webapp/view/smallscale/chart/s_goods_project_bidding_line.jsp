<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/smallscale/css/chart.css"/>

<input type="hidden" id="userType" name="userType" value="${param.userType }" />
<input type="hidden" id="goodsId" name="goodsId" value="${param.goodsId }" />
<div id="goodsProjectBiddingDiv"></div>

<div class="conOperation">
	<button type="button" name="historyBackBtn"><span><spring:message code="globe.return"/></span></button>
	<button type="button" id="exportGoodsProjectBiddingToExcel"><span>导出</span></button>
</div>

<script>
var GoodsProjectBidding = {};

//加载图表和列表数据
GoodsProjectBidding.loadChartAndTableData = function(){	
	var url = $('#initPath').val()+'/ChartController.do?method=getGoodsProjectBiddingLineData&goodsId='+$("#goodsId").val()+"&userType="+$("#userType").val();
	$("#goodsProjectBiddingDiv").loadPage(url);
}

$(document).ready(function(){
	//加载图表和列表数据
	GoodsProjectBidding.loadChartAndTableData();
	
	//导出数据
	$("#exportGoodsProjectBiddingToExcel").click(function(){		
		var url = $("#initPath").val()+'/ChartController.do?method=exportGoodsProjectBiddingToExcel&goodsId='+$("#goodsId").val()+"&userType="+$("#userType").val();
		window.location.href = url;
	});
})
</script>