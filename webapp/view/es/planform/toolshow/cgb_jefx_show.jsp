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
          		<th width="20%">月份</th>
          		<th width="20%">资金金额（万元）</th>
     		</tr>
		</thead>
	<tbody>
		<tr>
				<td class="center" width="20%">2009</td>
          		<td width="20%">1月</td>
          		<td width="20%">46</td>
          		
		</tr>
		<tr>
				<td class="center" width="20%">2009</td>
          		<td width="20%">2月</td>
          		<td width="20%">85</td>
          		
		</tr>
		<tr>
          		<td class="center" width="20%">2009</td>
          		<td width="20%">3月</td>
          		<td width="20%">67</td>
		</tr>
		<tr>
          		<td class="center" width="20%">2009</td>
          		<td width="20%">4月</td>
          		<td width="20%">49</td>
		</tr>
		<tr>
          		<td class="center" width="20%">2009</td>
          		<td width="20%">5月</td>
          		<td width="20%">76</td>
		</tr>
		<tr>
          		<td class="center" width="20%">2009</td>
          		<td width="20%">6月</td>
          		<td width="20%">96</td>
		</tr>
		<tr>
          		<td class="center" width="20%">2009</td>
          		<td width="20%">7月</td>
          		<td width="20%">62</td>
		</tr>
		<tr>
          		<td class="center" width="20%">2009</td>
          		<td width="20%">8月</td>
          		<td width="20%">62</td>
		</tr>
		<tr>
          		<td class="center" width="20%">2009</td>
          		<td width="20%">9月</td>
          		<td width="20%">37</td>
		</tr>
		<tr>
          		<td class="center" width="20%">2009</td>
          		<td width="20%">10月</td>
          		<td width="20%">49</td>
		</tr>
		<tr>
          		<td class="center" width="20%">2009</td>
          		<td width="20%">11月</td>
          		<td width="20%">76</td>
		</tr>
		<tr>
          		<td class="center" width="20%">2009</td>
          		<td width="20%">12月</td>
          		<td width="20%">96</td>
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
		   chart.setDataURL("<%=request.getContextPath()%>/StatisticsController.do?method=jefxShow");		   
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
								  