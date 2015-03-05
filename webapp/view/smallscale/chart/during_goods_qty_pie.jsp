<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/smallscale/css/chart.css"/>

<input type="hidden" id="userType" name="userType" value="${param.userType }" />
<div class="conSearch">
	<h4><span><spring:message code="globe.query" /></span></h4>
	<form id="duringGoodsQtyPieForm">
		<ul >
			<li>
				<label>选择时间：</label>
				<input type="text" name="startDate" id="startDate">&nbsp;到
	            <input type="text" name="endDate" id="endDate">
			</li>
			<li><label></label></li>
			<li class="operationBtnDiv">
				<button type="button" id="query"><span><spring:message code="globe.query" /></span></button>
			</li>
		</ul>
	</form>
</div>

<div id="DuringGoodsQtyPieDiv"></div>

<div class="conOperation">
	<button type="button" name="historyBackBtn"><span><spring:message code="globe.return"/></span></button>
	<button type="button" id="exportDuringGoodsQtyPieToExcel"><span>导出</span></button>
</div>

<script>
var DuringGoodsQtyPie = {};

//加载图表和列表数据
DuringGoodsQtyPie.loadChartAndTableData = function(){
	var url = $('#initPath').val()+'/ChartController.do?method=getDuringGoodsQtyPieData&startDate='+$("#startDate").val()+"&endDate="+$("#endDate").val()+"&userType="+$("#userType").val();
	$("#DuringGoodsQtyPieDiv").loadPage(url);
}

$(document).ready(function(){
	//开始时间
    $("#startDate").epsDatepicker();  //增加结束时间的规则
    //结束时间
    $("#endDate").epsDatepicker();  //增加开始时间的规则
	
	//加载图表和列表数据
	DuringGoodsQtyPie.loadChartAndTableData();
	
	//查询
	$("#query").click(function(){
		DuringGoodsQtyPie.loadChartAndTableData();
	});

	//导出数据
	$("#exportDuringGoodsQtyPieToExcel").click(function(){
		var url = $("#initPath").val()+'/ChartController.do?method=exportDuringGoodsQtyPieToExcel&startDate='+$("#startDate").val()+"&endDate="+$("#endDate").val()+"&userType="+$("#userType").val();
		window.location.href = url;
	});
})
</script>