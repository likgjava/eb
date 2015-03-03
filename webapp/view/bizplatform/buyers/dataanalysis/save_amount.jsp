<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="conSearch">
	<h4><span><spring:message code="globe.query" /></span></h4>
	<form id="quantityPieSearchForm">
		<ul >
			<li>
				<label>选择时间：</label>
				<a href="javascript:void(0);" onclick="SaveAmount.modifyYear('-','year1');"><img src="view/resource/skin/pubservice/img/bag_close.gif" /></a>
				<input type="text" name="year1" id="year1" readonly="readonly" style="width:35px;" value="2010" disabled="disabled" />
				<a href="javascript:void(0);" onclick="SaveAmount.modifyYear('+','year1');"><img src="view/resource/skin/pubservice/img/bag_open.gif" /></a>
			</li>
			<li><label></label></li>
			<li class="operationBtnDiv">
				<button type="button" id="query"><span><spring:message code="globe.query" /></span></button>
			</li>
		</ul>
	</form>
</div>

<div id="saveAmount"></div>

<div style="margin-top: 20px;">
<table width="745" border="1">
	<tr align="center">
		<td width="75">&nbsp;</td>
		<td width="194">采购预算（元）</td>
		<td width="253">实际额（元）</td>
		<td width="281">节省额（元）</td>
	</tr>
	<tr align="center">
		<td>1月</td>
		<td>55052.00</td>
		<td>48320.00</td>
		<td>353</td>
	</tr>
	<tr align="center">
		<td>2月</td>
		<td>45098.00</td>
		<td>45809.00</td>
		<td>363</td>
	</tr>
	<tr align="center">
		<td>3月</td>
		<td>45038.00</td>
		<td>43302.00</td>
		<td>1622</td>
	</tr>
	<tr align="center">
		<td>4月</td>
		<td>77516.00</td>
		<td>77516.00</td>
		<td>326</td>
	</tr>
	<tr align="center">
		<td>5月</td>
		<td>17023.00</td>
		<td>15038.00</td>
		<td>1980</td>
	</tr>
	<tr align="center">
		<td>6月</td>
		<td>35052.00</td>
		<td>32043.00</td>
		<td>1542</td>
	</tr>
	<tr align="center">
		<td>7月</td>
		<td>15038.00</td>
		<td>15052.00</td>
		<td>622</td>
	</tr>
	<tr align="center">
		<td>8月</td>
		<td>56240.00</td>
		<td>54032.00</td>
		<td>1542</td>
	</tr>
	<tr align="center">
		<td>9月</td>
		<td>67023.00</td>
		<td>67038.00</td>
		<td>364</td>
	</tr>
	<tr align="center">
		<td>10月</td>
		<td>15038.00</td>
		<td>15052.00</td>
		<td>371</td>
	</tr>
	<tr align="center">
		<td>11月</td>
		<td>17339.00</td>
		<td>15038.00</td>
		<td>1627</td>
	</tr>
	<tr align="center">
		<td>12月</td>
		<td>79038.00</td>
		<td>77516.00</td>
		<td>980</td>
	</tr>
</table>
</div>

<div class="conOperation">
	<button type="button" name="historyBackBtn"><span><spring:message code="globe.return"/></span></button>
</div>

<script>
var SaveAmount = {};

//修改年份
SaveAmount.modifyYear = function(oper,yerarId){
	var year = Number($("#"+yerarId).val());
	if(oper == "+"){
		year = year + 1;
	}else{
		year = year - 1;
	}
	$("#"+yerarId).val(year);
}

$(document).ready(function(){
	var myChart = new FusionCharts($("#initPath").val()+"/view/resource/plug-in/FusionChart/Charts/MSCombi2D.swf", "myChartId", "745", "300");
	var url = $("#initPath").val()+"/view/bizplatform/buyers/dataanalysis/save_amount.xml";
	myChart.setDataURL(escape(url));
	myChart.render("saveAmount");

	//查询
	$("#query").click(function(){
		myChart.render("saveAmount");
	});
})
</script>