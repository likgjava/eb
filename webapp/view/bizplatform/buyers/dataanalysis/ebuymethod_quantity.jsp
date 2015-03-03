<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="conSearch">
	<h4><span><spring:message code="globe.query" /></span></h4>
	<form id="aqcSearchForm">
		<ul>
			<li>
				<label>选择时间：</label>
				<a href="javascript:void(0);" onclick="EbuymethodQuantity.modifyYear('-','year1');"><img src="view/resource/skin/pubservice/img/bag_close.gif" /></a>
				<input type="text" name="year1" id="year1" readonly="readonly" style="width:35px;" value="2010" disabled="disabled" />
				<a href="javascript:void(0);" onclick="EbuymethodQuantity.modifyYear('+','year1');"><img src="view/resource/skin/pubservice/img/bag_open.gif" /></a>
			</li>
			<li><label></label></li>
			<li class="operationBtnDiv">
				<button type="button" id="query"><span><spring:message code="globe.query" /></span></button>
			</li>
		</ul>
	</form>
</div>

<div id="ebuymethodQuantity"></div>

<div style="margin-top: 20px;">
<table width="745" border="1">
	<tr align="center">
		<td width="55">&nbsp;</td>
		<td width="155">竞价</td>
		<td width="183">议价</td>
		<td width="202">直接订购</td>
		<td width="202">总量</td>
	</tr>
	<tr align="center">
		<td>1月</td>
		<td>150</td>
		<td>483</td>
		<td>48</td>
		<td>322</td>
	</tr>
	<tr align="center">
		<td>2月</td>
		<td>98</td>
		<td>45</td>
		<td>150</td>
		<td>54</td>
	</tr>
	<tr align="center">
		<td>3月</td>
		<td>38</td>
		<td>43</td>
		<td>24</td>
		<td>98</td>
	</tr>
	<tr align="center">
		<td>4月</td>
		<td>38</td>
		<td>77</td>
		<td>24</td>
		<td>36</td>
	</tr>
	<tr align="center">
		<td>5月</td>
		<td>16</td>
		<td>16</td>
		<td>54</td>
		<td>622</td>
	</tr>
	<tr align="center">
		<td>6月</td>
		<td>150</td>
		<td>32</td>
		<td>150</td>
		<td>326</td>
	</tr>
	<tr align="center">
		<td>7月</td>
		<td>24</td>
		<td>15</td>
		<td>45</td>
		<td>622</td>
	</tr>
	<tr align="center">
		<td>8月</td>
		<td>24</td>
		<td>54</td>
		<td>67</td>
		<td>543</td>
	</tr>
	<tr align="center">
		<td>9月</td>
		<td>670</td>
		<td>670</td>
		<td>77</td>
		<td>326</td>
	</tr>
	<tr align="center">
		<td>10月</td>
		<td>150</td>
		<td>150</td>
		<td>96</td>
		<td>326</td>
	</tr>
	<tr align="center">
		<td>11月</td>
		<td>673</td>
		<td>150</td>
		<td>62</td>
		<td>363</td>
	</tr>
	<tr align="center">
		<td>12月</td>
		<td>150</td>
		<td>775</td>
		<td>77</td>
		<td>980</td>
	</tr>
</table>
</div>

<div class="conOperation">
	<button type="button" name="historyBackBtn"><span><spring:message code="globe.return"/></span></button>
</div>

<script>
var EbuymethodQuantity = {};

//修改年份
EbuymethodQuantity.modifyYear = function(oper,yerarId){
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
	var url = $("#initPath").val()+"/view/bizplatform/buyers/dataanalysis/ebuymethod_quantity.xml";
	myChart.setDataURL(escape(url));
	myChart.render("ebuymethodQuantity");

	//查询
	$("#query").click(function(){
		myChart.render("ebuymethodQuantity");
	});
})
</script>