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
          		<th class="center" width="20%">年度</th>
          		<th width="20%">品目名称</th>
          		<th width="20%">采购数量</th>
     		</tr>
		</thead>
	<tbody>
		<tr>
				<td class="center" width="20%">2009</td>
          		<td width="20%">计算机</td>
          		<td width="20%">50台</td>
          		
		</tr>
		<tr>
				<td class="center" width="20%">2009</td>
          		<td width="20%">防火墙</td>
          		<td width="20%">88套</td>
          		
		</tr>
		<tr>
          		<td class="center" width="20%">2009</td>
          		<td width="20%">电视机</td>
          		<td width="20%">69台</td>
		</tr>
		<tr>
          		<td class="center" width="20%">2009</td>
          		<td width="20%">空气调节设备</td>
          		<td width="20%">50台</td>
		</tr>
		<tr>
          		<td class="center" width="20%">2009</td>
          		<td width="20%">复印机</td>
          		<td width="20%">76台</td>
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
		   var chart = new FusionCharts("view/resource/plug-in/FusionChart/Charts/Column3D.swf", "ChartId", "600", "350", "0", "0");
		   chart.setDataURL("<%=request.getContextPath()%>/StatisticsController.do?method=pmpmShow");		   
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
					  