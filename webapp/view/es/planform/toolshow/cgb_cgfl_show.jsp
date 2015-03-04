<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/esdemo/projectmanager/abeyance_list.js"></script>
<div class="formLayout">
	<div id="formLayout form2Pa">
		<div id="setRuleContent">
			<table>
				<tr>
				<td width="20%"><table class="tableList" id="SubProjectList">	
  		<thead>
      		<tr>
          		<th class="center" width="20%">采购分类</th>
          		<th width="20%">项目数量</th>
     		</tr>
		</thead>
	<tbody>
		<tr>
				<td class="center" width="20%">工程类</td>
          		<td width="20%">5</td>
          		
		</tr>
		<tr>
				<td class="center" width="20%">服务类</td>
          		<td width="20%">3</td>
          		
		</tr>
		<tr>
          		<td class="center" width="20%">维护类</td>
          		<td width="20%">2</td>
		</tr>
	</tbody>
    </table>
				</td>
				<td width="40%">
					<table width="98%" border="0" cellspacing="0" cellpadding="3" align="center">
  <tr> 
    <td valign="top" class="text" align="center"> <div id="chartdiv" align="center"> 
        FusionCharts. </div>
        <script type="text/javascript">
		   var chart = new FusionCharts("view/resource/plug-in/FusionChart/Charts/Doughnut3D.swf", "ChartId", "600", "350","0","0");
		   chart.setDataURL("<%=request.getContextPath()%>/StatisticsController.do?method=cgflShow");		   
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
</div>
			