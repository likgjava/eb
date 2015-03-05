<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/smallscale/css/chart.css"/>

<div class="conSearch">
	<h4><span><spring:message code="globe.query" /></span></h4>
	<form id="aqcSearchForm">
		<ul >
			<li>
				<label>选择时间：</label>
				<a href="javascript:void(0);" onclick="SaveAmountCompare.modifyYear('-','year1');"><img src="view/resource/skin/pubservice/img/bag_close.gif" /></a>
				<input type="text" name="year1" id="year1" readonly="readonly" style="width:35px;" value="2010" disabled="disabled" />
				<a href="javascript:void(0);" onclick="SaveAmountCompare.modifyYear('+','year1');"><img src="view/resource/skin/pubservice/img/bag_open.gif" /></a>
			</li>
			<li>
				<label>选择时间：</label>
				<a href="javascript:void(0);" onclick="SaveAmountCompare.modifyYear('-','year2');"><img src="view/resource/skin/pubservice/img/bag_close.gif" /></a>
				<input type="text" name="year2" id="year2" readonly="readonly" style="width:35px;" value="2011" disabled="disabled" />
				<a href="javascript:void(0);" onclick="SaveAmountCompare.modifyYear('+','year2');"><img src="view/resource/skin/pubservice/img/bag_open.gif" /></a>
			</li>
			<li><label></label></li>
			<li class="operationBtnDiv">
				<button type="button" id="query"><span><spring:message code="globe.query" /></span></button>
			</li>
		</ul>
	</form>
</div>

<div id="saveAmountCompareDiv"></div>

<div class="conOperation">
	<button type="button" name="historyBackBtn"><span><spring:message code="globe.return"/></span></button>
	<button type="button" id="exportSaveAmountCompareToExcel"><span>导出</span></button>
</div>

<script>
var SaveAmountCompare = {};

//修改年份
SaveAmountCompare.modifyYear = function(oper,yerarId){
	var year = Number($("#"+yerarId).val());
	if(oper == "+"){
		year = year + 1;
	}else{
		year = year - 1;
	}
	$("#"+yerarId).val(year);
}

//加载图表和列表数据
SaveAmountCompare.loadChartAndTableData = function(){
	var url = $('#initPath').val()+'/ChartController.do?method=toSaveAmountCompareView&year1='+$("#year1").val()+"&year2="+$("#year2").val();
	$("#saveAmountCompareDiv").loadPage(url);
}

$(document).ready(function(){
	//加载图表和列表数据
	SaveAmountCompare.loadChartAndTableData();
	
	//查询
	$("#query").click(function(){
		if($("#year1").val() == $("#year2").val()){alert("请选择不同的年份！"); return;}
		SaveAmountCompare.loadChartAndTableData();
	});

	//导出数据
	$("#exportSaveAmountCompareToExcel").click(function(){
		var url = $("#initPath").val()+'/ChartController.do?method=exportSaveAmountCompareToExcel&year1='+$("#year1").val()+"&year2="+$("#year2").val();
		window.location.href = url;
	});
		
})
</script>