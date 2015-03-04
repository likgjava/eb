<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/esdemo/projectmanager/abeyance_list.js"></script>
<script language="JavaScript" src="view/resource/plug-in/FusionChart/JSClass/FusionCharts.js"></script>

<!-- 我的桌面默认页面 -->

<div class="formLayout form2Pa">
	<!-- <h5><span>我的桌面</span><span class="add" id="projProcessRuleSave" style="padding-left: 900px;"></span></h5> -->
		<ul>
			<li>
			<span style="width:500px;height:270px;padding-left:3px">
			<ul style="padding-top:35px"></ul>
			<div class="partContainers operationLog"><h5 id="reportResults"><span class="switch  left11">我的项目</span></h5>
	
						<table class="tableList">
  							<thead>
      							<tr>
          							<th class="center">编号</th>
          							<th class="center">名称</th>
          							<th class="center">采购方式</th>
          							<th class="center">主管单位</th>
          							<th class="center">项目进度</th>
     							</tr>
							</thead>
							<tbody>
								<tr>
									<td class="center"><a href='#' class='next' target='' onClick='javascript:projectList.modify(1)'>AH-GK001</a></td>
          							<td class="center">广东省招标采购中心采购服务器</td>
          							<td class="center">公开招标</td>
          							<td class="center">采购中心</td>
          							<td class="center">设置项目规则</td>
								</tr>
								<tr>
									<td class="center"><a href='#' class='next' target='' onClick='javascript:projectList.modify(1)'>AH-GK002</a></td>
          							<td class="center">广东省成套招标采购中心采购笔记本</td>
          							<td class="center">公开招标</td>
          							<td class="center">采购中心</td>
          							<td class="center">发布<dm:out value="local__PURBULLETIN"  tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">公告</dm:out></td>
								</tr>
								<tr>
									<td class="center"><a href='#' class='next' target='' onClick='javascript:projectList.modify(1)'>AH-GK003</a></td>
          							<td class="center">广东省采购中心采购汽车</td>
          							<td class="center">公开招标</td>
          							<td class="center">采购中心</td>
          							<td class="center">指定经办人</td>
								</tr>
							</tbody>
   	 					</table>
				</div>
			</span>
			</li>
			<li>
			<span style="width:500px;height:270px;padding-left:3px">
			<authz:authorize ifAnyGranted="view/es/planform/project/agent_assign_inf.jsp">
			<ul style="padding-top:35px"></ul>
			<div class="partContainers operationLog"><h5 id="reportResults"><span class="switch  left11">项目总进度</span></h5>
	
						<table class="tableList">
  							<thead>
      							<tr>
          							<th class="center">编号</th>
          							<th class="center">名称</th>
          							<th class="center">采购方式</th>
          							<th class="center">主管单位</th>
          							<th class="center">项目进度</th>
     							</tr>
							</thead>
							<tbody>
								<tr>
									<td class="center"><a href='#' class='next' target='' onClick='javascript:projectList.modify(1)'>AH-GK001</a></td>
          							<td class="center">广东省招标采购中心采购服务器</td>
          							<td class="center">公开招标</td>
          							<td class="center">采购中心</td>
          							<td class="center">设置项目规则</td>
								</tr>
								<tr>
									<td class="center"><a href='#' class='next' target='' onClick='javascript:projectList.modify(1)'>AH-GK002</a></td>
          							<td class="center">广东省成套招标采购中心采购笔记本</td>
          							<td class="center">公开招标</td>
          							<td class="center">采购中心</td>
          							<td class="center">发布<dm:out value="local__PURBULLETIN"  tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">公告</dm:out></td>
								</tr>
								<tr>
									<td class="center"><a href='#' class='next' target='' onClick='javascript:projectList.modify(1)'>AH-GK003</a></td>
          							<td class="center">广东省采购中心采购汽车</td>
          							<td class="center">公开招标</td>
          							<td class="center">采购中心</td>
          							<td class="center">指定经办人</td>
								</tr>
							</tbody>
   	 					</table>
				</div>
			</authz:authorize>
			
			<authz:authorize ifAnyGranted="ProjectController.do?method=searchProjectListDljg&userType=supplier,ProjectController.do?method=searchProjectListDljg&userType=buyer">
			<ul style="padding-top:35px"></ul>
			<div class="partContainers operationLog"><h5 id="reportResults"><span class="switch  left11">我的项目进度</span></h5>
	
						<table class="tableList">
  							<thead>
      							<tr>
          							<th class="center">编号</th>
          							<th class="center">名称</th>
          							<th class="center">采购方式</th>
          							<th class="center">主管单位</th>
          							<th class="center">项目进度</th>
     							</tr>
							</thead>
							<tbody>
								<tr>
									<td class="center"><a href='#' class='next' target='' onClick='javascript:projectList.modify(1)'>AH-GK001</a></td>
          							<td class="center">广东省招标采购中心采购服务器</td>
          							<td class="center">公开招标</td>
          							<td class="center">采购中心</td>
          							<td class="center">设置项目规则</td>
								</tr>
								<tr>
									<td class="center"><a href='#' class='next' target='' onClick='javascript:projectList.modify(1)'>AH-GK002</a></td>
          							<td class="center">广东省成套招标采购中心采购笔记本</td>
          							<td class="center">公开招标</td>
          							<td class="center">采购中心</td>
          							<td class="center">发布<dm:out value="local__PURBULLETIN"  tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">公告</dm:out></td>
								</tr>
								<tr>
									<td class="center"><a href='#' class='next' target='' onClick='javascript:projectList.modify(1)'>AH-GK003</a></td>
          							<td class="center">广东省采购中心采购汽车</td>
          							<td class="center">公开招标</td>
          							<td class="center">采购中心</td>
          							<td class="center">指定经办人</td>
								</tr>
							</tbody>
   	 					</table>
				</div>
			</authz:authorize>
			
			<authz:authorize ifAnyGranted="view/es/planform/taskplan/taskPlanListForAudit.jsp">
				 <ul style="padding-top:10px"></ul>
				 <div id="chartdiv" align="center"></div>
				 <ul style="padding-top:10px"></ul>
        			<script type="text/javascript">
		   				var chart = new FusionCharts("view/resource/plug-in/FusionChart/Charts/Doughnut3D.swf", "ChartId", "350", "250","0","0");
		   					chart.setDataURL("<%=request.getContextPath()%>/StatisticsController.do?method=cgfsShow");		   
		   					chart.render("chartdiv");
					</script>
				</authz:authorize>	
			</span>
			</li>
		</ul>
		<ul>
			<li>
				
				<span style="width:500px;height:250px;line-height:250px;">
				<ul style="padding-top:60px"></ul>
				<div class="partContainers operationLog"><h5 id="reportResults"><span class="switch  left11">我的待办</span></h5>
	   				<form id="orgInfoForm" method="post">
						<ul style="padding-top:20px">
								
								<li>
						  			<ol>
							    		<li><a href='#' class='next' target='' onClick='javascript:projectList.modify(1)'>AH-GK001</a>---<a href='#' target='' onClick='javascript:projectList.modify(1)'>广东省招标采购中心采购服务器</a>---<font color="red">需要您发布<dm:out value="local__PURBULLETIN"  tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">公告</dm:out></dm:out></font>---(2000-09-01) </li> 		
							    		<li><a href='#' class='next' target='' onClick='javascript:projectList.modify(1)'>AH-GK002</a>---<a href='#' target='' onClick='javascript:projectList.modify(1)'>广东省成套招标采购中心采购笔记本</a>---<font color="red">需要您起草<dm:out value="local__PURBULLETIN"  tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">公告</dm:out></font>---(2000-09-01) </li>
							    		<li><a href='#' class='next' target='' onClick='javascript:projectList.modify(1)'>AH-GK003</a>---<a href='#' target='' onClick='javascript:projectList.modify(1)'>广东省采购中心采购汽车</a>---<font color="red">需要您<dm:out value="local__OpenTendering__openBid_zh">开标</dm:out></font>---(2000-09-01) </li>
						  			</ol>
			   	 				</li>
			    
			  			</ul> 		 
					</form>
				</div>
				
				</span>
			</li>
		</ul>
</div>