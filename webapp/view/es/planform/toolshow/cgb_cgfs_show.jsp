<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<link rel="stylesheet" href="../Contents/Style.css" type="text/css" >
<script language="JavaScript" src="view/resource/plug-in/FusionChart/JSClass/FusionCharts.js"></script>
	<div class="formLayout">
	<div id="formLayout form2Pa">	
			<table>
				<tr>
				<td width="20%"><table class="tableList" id="SubProjectList">	
  					<thead>
      					<tr>
          				<th class="center" >年度</th>
          				<th  >采购方式</th>
          				<th  >项目数量</th>
     					</tr>
					</thead>
					<tbody>
						<tr>
							<td class="center" >2009</td>
          					<td >公开招标</td>
          					<td >5</td>
          				
						</tr>
						<tr>
							<td class="center" >2009</td>
          					<td >邀请招标</td>
          					<td >3</td>
          		
						</tr>
						<tr>
          					<td class="center" width="10%">2009</td>
          					<td >竞争性谈判</td>
          					<td >1</td>
						</tr>
						<tr>
          					<td class="center" >2009</td>
          					<td >单一来源</td>
          					<td >1</td>
						</tr>
						<tr>
          					<td class="center" >2009</td>
          					<td>询价</td>
          					<td >1</td>
						</tr>
					</tbody>
    						</table>
				</td>
				<td width="40%" >
				<table width="98%" border="0" cellspacing="0" cellpadding="3" align="center">
  <tr> 
    <td valign="top" class="text" align="center"> <div id="chartdiv" align="center"> 
        FusionCharts. </div>
        <script type="text/javascript">
		   var chart = new FusionCharts("view/resource/plug-in/FusionChart/Charts/Doughnut3D.swf", "ChartId", "600", "350","0","0");
		  //  chart.setDataURL("view/resource/plug-in/FusionChart/Gallery/Data/Doughnut3D1.xml");	
		   chart.setDataURL("<%=request.getContextPath()%>/StatisticsController.do?method=cgfsShow");	   
		   chart.render("chartdiv");
		</script>
	</td>
  </tr>
</table>
				</td>
				</tr>		
			</table>	
	</div>
</div>
							  