<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="conSearch">
	<h4><span><spring:message code="globe.query" /></span></h4>
	<form id="aqcSearchForm">
		<ul >
			<li>
				<label>选择时间：</label>
				<a href="javascript:void(0);" onclick="EbuymethodAmount.modifyYear('-','year1');"><img src="view/resource/skin/pubservice/img/bag_close.gif" /></a>
				<input type="text" name="year1" id="year1" readonly="readonly" style="width:35px;" value="2010" disabled="disabled" />
				<a href="javascript:void(0);" onclick="EbuymethodAmount.modifyYear('+','year1');"><img src="view/resource/skin/pubservice/img/bag_open.gif" /></a>
			</li>
			<li><label></label></li>
			<li class="operationBtnDiv">
				<button type="button" id="query"><span><spring:message code="globe.query" /></span></button>
			</li>
		</ul>
	</form>
</div>

<div id="ebuymethodAmount"></div>

<div style="margin-top: 20px;">
<table width="745" border="1">
	<tr align="center">
		<td width="55">&nbsp;</td>
		<td width="155">竞价</td>
		<td width="183">议价</td>
		<td width="202">直接订购</td>
		<td width="202">总额</td>
	</tr>
	<tr align="center">
		<td>1月</td>
		<td>15052.00</td>
		<td>48320.00</td>
		<td>48320.00</td>
		<td>322323.00</td>
	</tr>
	<tr align="center">
		<td>2月</td>
		<td>15098.00</td>
		<td>45809.00</td>
		<td>15038.00</td>
		<td>542420.00</td>
	</tr>
	<tr align="center">
		<td>3月</td>
		<td>15038.00</td>
		<td>43302.00</td>
		<td>67023.00</td>
		<td>980898.00</td>
	</tr>
	<tr align="center">
		<td>4月</td>
		<td>77516.00</td>
		<td>77516.00</td>
		<td>96240.00</td>
		<td>364484.00</td>
	</tr>
	<tr align="center">
		<td>5月</td>
		<td>67023.00</td>
		<td>15038.00</td>
		<td>54032.00</td>
		<td>622383.00</td>
	</tr>
	<tr align="center">
		<td>6月</td>
		<td>15052.00</td>
		<td>32043.00</td>
		<td>15052.00</td>
		<td>326648.00</td>
	</tr>
	<tr align="center">
		<td>7月</td>
		<td>45038.00</td>
		<td>15052.00</td>
		<td>45038.00</td>
		<td>622232.00</td>
	</tr>
	<tr align="center">
		<td>8月</td>
		<td>96240.00</td>
		<td>54032.00</td>
		<td>67339.00</td>
		<td>542673.00</td>
	</tr>
	<tr align="center">
		<td>9月</td>
		<td>67023.00</td>
		<td>67038.00</td>
		<td>77516.00</td>
		<td>326483.00</td>
	</tr>
	<tr align="center">
		<td>10月</td>
		<td>15038.00</td>
		<td>15052.00</td>
		<td>96240.00</td>
		<td>326582.00</td>
	</tr>
	<tr align="center">
		<td>11月</td>
		<td>67339.00</td>
		<td>15038.00</td>
		<td>62730.00</td>
		<td>363980.00</td>
	</tr>
	<tr align="center">
		<td>12月</td>
		<td>15038.00</td>
		<td>77516.00</td>
		<td>77516.00</td>
		<td>980389.00</td>
	</tr>
</table>
</div>

<div class="conOperation">
	<button type="button" name="historyBackBtn"><span><spring:message code="globe.return"/></span></button>
</div>

<script>
var EbuymethodAmount = {};

//修改年份
EbuymethodAmount.modifyYear = function(oper,yerarId){
	var year = Number($("#"+yerarId).val());
	if(oper == "+"){
		year = year + 1;
	}else{
		year = year - 1;
	}
	$("#"+yerarId).val(year);
}

$(document).ready(function(){
	var myChart = new FusionCharts($("#initPath").val()+"/view/resource/plug-in/FusionChart/Charts/StackedColumn2D.swf", "myChartId", "745", "300");
	var url = $("#initPath").val()+"/view/bizplatform/buyers/dataanalysis/ebuymethod_amount.xml";
	myChart.setDataURL(escape(url));
	myChart.render("ebuymethodAmount");

	//查询
	$("#query").click(function(){
		myChart.render("ebuymethodAmount");
	});
})
</script>