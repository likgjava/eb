<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/smallscale/css/chart.css"/>

<input type="hidden" id="userType" name="userType" value="${param.userType }" />
<div class="conSearch">
	<h4><span><spring:message code="globe.query" /></span></h4>
	<form>
		<ul >
			<li>
				<label>选择时间：</label>
				<a href="javascript:void(0);" onclick="AmountQtyCompare.modifyYear('-','year1');"><img src="view/resource/skin/pubservice/img/bag_close.gif" /></a>
				<input type="text" name="year1" id="year1" readonly="readonly" style="width:35px;" value="2010" disabled="disabled" />
				<a href="javascript:void(0);" onclick="AmountQtyCompare.modifyYear('+','year1');"><img src="view/resource/skin/pubservice/img/bag_open.gif" /></a>
			</li>
			<li>
				<label>选择时间：</label>
				<a href="javascript:void(0);" onclick="AmountQtyCompare.modifyYear('-','year2');"><img src="view/resource/skin/pubservice/img/bag_close.gif" /></a>
				<input type="text" name="year2" id="year2" readonly="readonly" style="width:35px;" value="2011" disabled="disabled" />
				<a href="javascript:void(0);" onclick="AmountQtyCompare.modifyYear('+','year2');"><img src="view/resource/skin/pubservice/img/bag_open.gif" /></a>
			</li>
			<li><label></label></li>
			<li class="operationBtnDiv">
				<button type="button" id="query"><span><spring:message code="globe.query" /></span></button>
			</li>
		</ul>
	</form>
</div>

<div id="amountQtyCompareDiv"></div>

<div class="conOperation">
	<button type="button" name="historyBackBtn"><span><spring:message code="globe.return"/></span></button>
	<button type="button" id="exportAmountQtyCompareToExcel"><span>导出</span></button>
</div>

<script>
var AmountQtyCompare = {};

//修改年份
AmountQtyCompare.modifyYear = function(oper,yerarId){
	var year = Number($("#"+yerarId).val());
	if(oper == "+"){
		year = year + 1;
	}else{
		year = year - 1;
	}
	$("#"+yerarId).val(year);
}

//加载图表和列表数据
AmountQtyCompare.loadChartAndTableData = function(){
	var url = $('#initPath').val()+'/ChartController.do?method=getAmountQtyCompareData&year1='+$("#year1").val()+"&year2="+$("#year2").val()+"&userType="+$("#userType").val();
	$("#amountQtyCompareDiv").loadPage(url);
}

$(document).ready(function(){
	//加载图表和列表数据
	AmountQtyCompare.loadChartAndTableData();
	
	//查询
	$("#query").click(function(){
		if($("#year1").val() == $("#year2").val()){alert("请选择不同的年份！"); return;}
		AmountQtyCompare.loadChartAndTableData();
	});

	//导出数据
	$("#exportAmountQtyCompareToExcel").click(function(){
		var url = $("#initPath").val()+'/ChartController.do?method=exportAmountQtyCompareToExcel&year1='+$("#year1").val()+"&year2="+$("#year2").val()+"&userType="+$("#userType").val();
		window.location.href = url;
	});
		
})
</script>