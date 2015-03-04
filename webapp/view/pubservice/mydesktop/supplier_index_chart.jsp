<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<input type="hidden" id="chartXml1" value="${chartXml1}" />
<input type="hidden" id="chartXml2" value="${chartXml2}" />
<div class="cols">
	<h2>销售统计</h2>
	<ul class="chartList">
		<li><div id="chartdivA" class="chartDiv"></div></li>
		<li><div id="chartdivB" class="chartDiv"></div></li>
	</ul>
	<div class="more"><a href="javascript:void(0);" onclick="SupplierIndex.loadView('/view/smallscale/chart/sale_data_analysis.jsp');">更多</a></div>
</div>
      
<script>
$(document).ready(function(){
	//创建图表1,‘月销售额和销售量同比、环比图’的图表
	var myChart1 = new FusionCharts($("#initPath").val()+"/view/resource/plug-in/FusionChart/Charts/MSCombiDY2D.swf", "myChartId3", "590", "300", "0", "0");
	myChart1.setDataXML($("#chartXml1").val());
	myChart1.render("chartdivA");

	//创建图表2,‘各品目年度销售总额分布图’
	var myChart2 = new FusionCharts($("#initPath").val()+"/view/resource/plug-in/FusionChart/Charts/Pie2D.swf", "myChartId4", "360", "300");
	myChart2.setDataXML($("#chartXml2").val());
	myChart2.render("chartdivB");
})
</script>      