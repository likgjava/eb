<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/esdemo/projectmanager/abeyance_list.js"></script>
<script language="JavaScript" src="view/resource/plug-in/FusionChart/JSClass/FusionCharts.js"></script>

<!-- 我的桌面默认页面 -->

<div class="formLayout form2Pa">
	<!-- <h5><span>我的桌面</span><span class="add" id="projProcessRuleSave" style="padding-left: 900px;"></span></h5> -->
		<ul>
			<li>
			<span>			
			 	<ul style="padding-top:10px"></ul>
				 <div id="chart1div"  align="center"></div>
       			 <script type="text/javascript">
		   			var chart = new FusionCharts("view/resource/plug-in/FusionChart/Charts/Doughnut3D.swf", "ChartId", "480", "250","0","0");
		   			chart.setDataURL("<%=request.getContextPath()%>/StatisticsController.do?method=cgflShow");		   
		   			chart.render("chart1div");
				 </script>
				<ul style="padding-top:10px"></ul>
			</span>
			</li>
			<li>
			<span>
			<ul style="padding-top:10px"></ul>
			<div id="chartdiv" align="center"></div>
        			<script type="text/javascript">
		   					var chart = new FusionCharts("view/resource/plug-in/FusionChart/Charts/Doughnut3D.swf", "ChartId", "480", "250","0","0");
		   					chart.setDataURL("<%=request.getContextPath()%>/StatisticsController.do?method=cgfsShow");		   
		   					chart.render("chartdiv");
					</script>
			
			<ul style="padding-top:10px"></ul>
			</span>
			</li>
		</ul>
		<ul>
			<li>
				<span>
				<ul style="padding-top:10px"></ul>
				<div id="chart3div" align="center"></div>
        			<script type="text/javascript">
		   				var chart = new FusionCharts("view/resource/plug-in/FusionChart/Charts/Column3D.swf", "ChartId", "480", "250", "0", "0");
		   				chart.setDataURL("<%=request.getContextPath()%>/StatisticsController.do?method=pmpmShow");		   
		   				chart.render("chart3div");
					</script>
				<ul style="padding-top:10px"></ul>
				</span>
			</li>
			<li>
				<span>
				<ul style="padding-top:10px"></ul>
				<div id="chart2div" align="center"></div>
        			<script type="text/javascript">
		   				var chart = new FusionCharts("view/resource/plug-in/FusionChart/Charts/Column3D.swf", "ChartId", "480", "250", "0", "0");
		   				chart.setDataURL("<%=request.getContextPath()%>/StatisticsController.do?method=jefxShow");		   
		   				chart.render("chart2div");
					</script>
				<ul style="padding-top:10px"></ul>		
				</span>
			</li>
		</ul>
</div>