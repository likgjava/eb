<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/smallscale/css/chart.css"/>

<div class="conSearch">
	<h4><span><spring:message code="globe.query" /></span></h4>
	<form>
		<ul >
			<li>
				<label>选择时间：</label>
				<a href="javascript:void(0);" onclick="EbuymethodQty.modifyYear('-','year');"><img src="view/resource/skin/pubservice/img/bag_close.gif" /></a>
				<input type="text" name="year" id="year" readonly="readonly" style="width:35px;" value="2011" disabled="disabled" />
				<a href="javascript:void(0);" onclick="EbuymethodQty.modifyYear('+','year');"><img src="view/resource/skin/pubservice/img/bag_open.gif" /></a>
			</li>
			<li><label></label></li>
			<li class="operationBtnDiv">
				<button type="button" id="query"><span><spring:message code="globe.query" /></span></button>
			</li>
		</ul>
	</form>
</div>

<div id="ebuymethodQtyDiv"></div>

<div class="conOperation">
	<button type="button" name="historyBackBtn"><span><spring:message code="globe.return"/></span></button>
	<button type="button" id="exportEbuymethodQtyToExcel"><span>导出</span></button>
</div>

<script>
var EbuymethodQty = {};

//修改年份
EbuymethodQty.modifyYear = function(oper,yerarId){
	var year = Number($("#"+yerarId).val());
	if(oper == "+"){
		year = year + 1;
	}else{
		year = year - 1;
	}
	$("#"+yerarId).val(year);
}

//加载图表和列表数据
EbuymethodQty.loadChartAndTableData = function(){
	var url = $('#initPath').val()+'/ChartController.do?method=getEbuymethodQtyData&year='+$("#year").val();
	$("#ebuymethodQtyDiv").loadPage(url);
}

$(document).ready(function(){
	//加载图表和列表数据
	EbuymethodQty.loadChartAndTableData();
	
	//查询
	$("#query").click(function(){
		EbuymethodQty.loadChartAndTableData();
	});

	//导出数据
	$("#exportEbuymethodQtyToExcel").click(function(){
		var url = $("#initPath").val()+'/ChartController.do?method=exportEbuymethodQtyToExcel&year='+$("#year").val();
		window.location.href = url;
	});
})
</script>