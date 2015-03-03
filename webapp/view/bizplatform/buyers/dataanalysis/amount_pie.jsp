<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="conSearch">
	<h4><span><spring:message code="globe.query" /></span></h4>
	<form id="aqcSearchForm">
		<ul >
			<li>
				<label>选择时间：</label>
				<a href="javascript:void(0);" onclick="AmountPie.modifyYear('-','year1');"><img src="view/resource/skin/pubservice/img/bag_close.gif" /></a>
				<input type="text" name="year1" id="year1" readonly="readonly" style="width:35px;" value="2010" disabled="disabled" />
				<a href="javascript:void(0);" onclick="AmountPie.modifyYear('+','year1');"><img src="view/resource/skin/pubservice/img/bag_open.gif" /></a>
			</li>
			<li><label></label></li>
			<li class="operationBtnDiv">
				<button type="button" id="query"><span><spring:message code="globe.query" /></span></button>
			</li>
		</ul>
	</form>
</div>

<div id="amountPie"></div>

<div style="margin-top: 20px;">
<table width="745" border="1">
	<tr align="center">
		<td width="125">&nbsp;</td>
		<td width="306">采购额（元）</td>
	</tr>
	<tr align="center">
		<td>服务器</td>
		<td>10983.00</td>
	</tr>
	<tr align="center">
		<td>电冰箱</td>
		<td>4772.00</td>
	</tr>
	<tr align="center">
		<td>洗衣机</td>
		<td>9385.00</td>
	</tr>
	<tr align="center">
		<td>红酒</td>
		<td>8938.00</td>
	</tr>
	<tr align="center">
		<td>电视机</td>
		<td>1273.00</td>
	</tr>
	<tr align="center">
		<td>笔记本</td>
		<td>4983.00</td>
	</tr>
	<tr align="center">
		<td>台式电脑</td>
		<td>5467.00</td>
	</tr>
</table>
</div>

<div class="conOperation">
	<button type="button" name="historyBackBtn"><span><spring:message code="globe.return"/></span></button>
</div>

<script>
var AmountPie = {};

//修改年份
AmountPie.modifyYear = function(oper,yerarId){
	var year = Number($("#"+yerarId).val());
	if(oper == "+"){
		year = year + 1;
	}else{
		year = year - 1;
	}
	$("#"+yerarId).val(year);
}

$(document).ready(function(){
	var myChart = new FusionCharts($("#initPath").val()+"/view/resource/plug-in/FusionChart/Charts/Pie2D.swf", "myChartId", "745", "300");
	var url = $("#initPath").val()+"/view/bizplatform/buyers/dataanalysis/amount_pie.xml";
	myChart.setDataURL(escape(url));
	myChart.render("amountPie");

	//查询
	$("#query").click(function(){
		myChart.render("amountPie");
	});
})
</script>