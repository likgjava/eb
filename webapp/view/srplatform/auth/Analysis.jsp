<%@ page contentType="text/html;charset=UTF-8" %>
<!--页面按钮开始-->
<body >
			
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td valign="top" align="center">
		<div id="chartdiv" align="center"></div>
		</td>
	</tr>
</table>
</body>
<script language="javascript">
	var myChart = new FusionCharts("<%=request.getContextPath()%>/view/resource/plug-in/FusionChart/Charts/Column3D.swf", "myChartId", "800", "350");
	myChart.setDataURL("<%=request.getContextPath()%>/AnalysisController.do?method=loginAnalysis");
	myChart.render("chartdiv");
</script>