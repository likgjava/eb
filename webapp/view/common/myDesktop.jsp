<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
 <script type="text/javascript" src="<%=request.getContextPath()%>/view/common/myDesktop.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/common/planTemplateTask.js"></script>
<authz:authorize ifAnyGranted="ProjectViewController.do?method=searchProjectListForBuyer">
<ul class="purClass">
  <li class="zfcg">
    <h5>政府采购</h5>
    <a title="填报采购登记" href="#" id="fillZFCGTaskPlan">填报采购登记</a> </li>
  <li class="jzgc">
    <h5>建筑工程</h5>
    <a title="填报入市登记" href="#" id="fillJZGCRecordForm">填报入市登记</a> </li>
  <li class="tdjy">
    <h5>土地交易</h5>
    <a title="填报入市登记" href="#" id="fillTDTaskPlan">填报入市登记</a> </li>
  <li class="cqjy">
    <h5>产权交易</h5>
    <a title="填报入市登记" href="#" id="fillCQTaskPlan">填报入市登记</a> </li>
</ul>
</authz:authorize>
<div class="task">
  	<c:set var="workTaskListCount" value="${fn:length(workTaskList)}"></c:set>
					 <h5>待办任务</h5>
			<c:set var="num" value="0"></c:set>
						<ul>
						
				<authz:authorize ifAnyGranted="ProjectViewController.do?method=searchProjectListForAgentManager">
				&nbsp;&nbsp;&nbsp;&nbsp;<li><a  id="waitCreateProjectId" title="查看待立项的任务书条目">待立项的任务书条目(<span style="color: red;">${waitTaskPlanSubNum}</span>)</a>
				</li></authz:authorize>
				<c:if test="${processInstanceNodeNum>0}"> 
					<li><a  id="waitAuditProcessInstance" title="待审核的子流程">待审核的子流程(<span style="color: red;">${processInstanceNodeNum}</span>)</a></li>
				</c:if>
					
						<c:forEach items="${workTaskList}" var="workTast">
							<li><a  href="#work_task_code_div_${num}" taskType="${workTast.worktaskType}" class="workTask">${workTast.worktaskName}(<em>${workTast.count}</em>)</a></li>
								<c:set var="num" value="${num+1}"></c:set>
						</c:forEach>
						 </ul>
					<!-- 待办任务 硬编码 end -->
</div>
<div class="desktopList" id="frameMainId">
  <h5>我的项目
	 <a  title="点击查看更多项目信息..." href="#" id="moreProject" class="moreProjectBtn"  ><span style=""  class="sysicon siNext" id="moreSpan">更多…</span></a>
  </h5>	
  <ul id="projectList">
    
  </ul>
</div>
<authz:authorize ifAnyGranted="ProjectController.do?method=searchProjectListDljg&serviceName=searchProjectListForSupply" >
<div class="desktopList" id="bulletinId">
  <h5>我的公告</h5>
  <ul id="bulletinList">
  </ul>
</div>
</authz:authorize>
<authz:authorize ifAnyGranted="ProjectViewController.do?method=searchProjectListForAgentManager" >
<div class="task">
  <h5>常用报表</h5>
  <ul>
    <li> <a href="#" >常用报表1</a></li>
    <li> <a href="#">常用报表2</a></li>
    <li> <a href="#">常用报表3</a></li>
    <li> <a href="#">常用报表4</a></li>
    <li> <a href="#">常用报表5</a></li>
  </ul>
</div>
</authz:authorize>
<authz:authorize ifAnyGranted="ProjectViewController.do?method=searchProjectListForAgentManager" >
<div class="dataSta">
  <h5>统计数据</h5>
  <ul>
    <li>
    <div class="chartDiv" id="chartdivA"></div>
    <script type="text/javascript">
    	   var chart = new FusionCharts("view/resource/plug-in/FusionChart/Charts/Doughnut3D.swf", "ChartId", "300", "300","0","0");
		   var url=$("#initPath").val()+"/ProjectViewController.do?method=ebuyMethodProject";
		    <%request.getSession().setAttribute("year",request.getParameter("year"));
		    %>
		   chart.setDataURL(url);		   
		   chart.render("chartdivA");
		</script>
    </li>
    <li>
      <div class="chartDiv" id="chartdivB">
          <script type="text/javascript">
		   var chart = new FusionCharts("view/resource/plug-in/FusionChart/Charts/Doughnut3D.swf", "ChartId", "300", "300","0","0");
		   chart.setDataURL("<%=request.getContextPath()%>/ProjectViewController.do?method=purchasingItemsProject");		   
		   chart.render("chartdivB");
		</script>
      </div>
    </li>
  </ul>
</div>

</authz:authorize>
		<div>
			<input type="hidden" id="serviceName" value=""/>
			<authz:authorize ifAnyGranted="ProjectViewController.do?method=searchProjectListForAgent" ifNotGranted="ProjectViewController.do?method=searchProjectListForAgentManager">
				<script type="text/javascript">
				//获取代理机构项目负责人相关联的项目信息
				$('#serviceName').val('searchProjectListForAgent');
				$.getJSON($('#initPath').val()+'/ProjectViewController.do?method=searchProjectListForAgent',{page:"1",rp:"5",queryColumns:'objId,ebuyMethod,projCode,projName,createTime'},function(json){
					if(json.total < 1){
						$("#myProjectCount").append(0);
						$("#more").remove();
						$("#projectList").empty().append("<li>"
								+"暂时没相关的招标项目！</li>");
					}else{
						$("#myProjectCount").append(json.total);
						$("#myAgentProject").empty();
						$.each(json.rows,function(i,n){
							if(n.projProcessStatusCN==undefined){
								n.projProcessStatusCN = "还未开始！"
							}
							var shortTime = n.createTime.substring(0,10);
							var shortName = "";
			
							if(n.projName.length>15){
							 	shortName = n.projName.substring(0,15)+"...";
							}else {
								shortName = n.projName;
							}
							$("#projectList").append("<li><div class='desktopInfo'>"
									+"<h6><a href='#' name=\"projs\" onClick=\"ProjectInfoReload('"+n.objId+"');\" title=\""+n.projName+"(项目编号:"+n.projCode+")\">"
									+"<span id='"+n.ebuyMethod+"' name='"+n.ebuyMethod+"'></span>"+"【"+n.projCode+"】"+shortName
									+"</a></h6><p class='keyInfo'>项目类型："+n.tenderTypeCN+",采购方式:"+n.ebuyMethodCN+",时间:"+shortTime+",进度 :<span class='spaceused' >"+n.processPers+"</span></p></div><p class='curState greenHighlight'>"+n.projProcessStatusCN+"</p> <p class='functionBtnDiv'><button type='button' onClick=\"ProjectInfoReload('"+n.objId+"');\"><span>进入项目</span></button></p></li>");
							})
						changeProjectColor();
						$(".spaceused").progressBar({showText:true});
					}
				});
				
				</script>
			</authz:authorize>
			<authz:authorize ifAnyGranted="ProjectViewController.do?method=searchProjectListForAgentManager">
				<script type="text/javascript">
				//获取代理机构管理员相关联的项目信息
				$('#serviceName').val('searchProjectListForAgentManager');
				$.getJSON($('#initPath').val()+'/ProjectViewController.do?method=searchProjectListForAgentManager',{page:"1",rp:"5",queryColumns:'objId,ebuyMethod,projCode,projName,createTime'},function(json){
					if(json.total < 1){
						$("#myProjectCount").append(0);
						$("#more").remove();
						$("#projectList").empty().append("<li>"
								+"暂时没相关的招标项目！</li>");
					}else{
						$("#myProjectCount").append(json.total);
						$("#myAgentManagerProject").empty();
						$.each(json.rows,function(i,n){
							if(n.projProcessStatusCN==undefined){
								n.projProcessStatusCN = "还未开始！"
							}
							var shortTime = n.createTime.substring(0,10);
							var shortName = "";
							
							if(n.projName.length>15){
							 	shortName = n.projName.substring(0,15)+"...";
							}else {
								shortName = n.projName;
							}
							$("#projectList").append("<li><div class='desktopInfo'>"
									+"<h6><a href='#' name=\"projs\" onClick=\"ProjectInfoReload('"+n.objId+"');\" title=\""+n.projName+"(项目编号:"+n.projCode+")\">"
									+"<span id='"+n.ebuyMethod+"' name='"+n.ebuyMethod+"'></span>"+"【"+n.projCode+"】"+shortName
									+"</a></h6><p class='keyInfo'>项目类型："+n.tenderTypeCN+",采购方式:"+n.ebuyMethodCN+",时间:"+shortTime+",进度 :<span class='spaceused' >"+n.processPers+"</span></p></div><p class='curState greenHighlight'>"+n.projProcessStatusCN+"</p> <p class='functionBtnDiv'><button type='button' onClick=\"ProjectInfoReload('"+n.objId+"');\"><span>进入项目</span></button></p></li>");
							})
						changeProjectColor();
						$(".spaceused").progressBar({showText:true});
					}
				});
				</script>
			</authz:authorize>
			<authz:authorize ifAnyGranted="ProjectViewController.do?method=searchProjectListForSupervise">
				<script type="text/javascript">
				//获取监管人相关联的项目信息
				$('#serviceName').val('searchProjectListForSupervise');
				$.getJSON($('#initPath').val()+'/ProjectViewController.do?method=searchProjectListForSupervise',{page:"1",rp:"5",queryColumns:'objId,ebuyMethod,projCode,projName,createTime'},function(json){
					if(json.total < 1){
						$("#myProjectCount").append(0);
						$("#more").remove();
						$("#projectList").empty().append("<li>"
								+"暂时没相关的招标项目！</li>");
					}else{
						$("#myProjectCount").append(json.total);
						$("#mySuperviseProject").empty();
						$.each(json.rows,function(i,n){
							if(n.projProcessStatusCN==undefined){
								n.projProcessStatusCN = "还未开始！"
							}
							var shortTime = n.createTime.substring(0,10);
							var shortName = "";
							if(n.projName.length>15){
							 	shortName = n.projName.substring(0,15)+"...";
							}else {
								shortName = n.projName;
							}
							$("#projectList").append("<li style='height:80px;'><div class='desktopInfo'>"
									+"<h6><a href='#' name=\"projs\" onClick=\"ProjectInfoReload('"+n.objId+"');\" title=\""+n.projName+"(项目编号:"+n.projCode+")\">"
									+"<span id='"+n.ebuyMethod+"' name='"+n.ebuyMethod+"'></span>"+"【"+n.projCode+"】"+shortName
									+"</a></h6><p class='keyInfo'>项目类型："+n.tenderTypeCN+",采购方式:"+n.ebuyMethodCN+",时间:"+shortTime+",进度 :<span class='spaceused' >"+n.processPers+"</span></p></div><p class='curState greenHighlight'>"+n.projProcessStatusCN+"</p> <p class='functionBtnDiv'><button type='button' onClick=\"ProjectInfoReload('"+n.objId+"');\"><span>进入项目</span></button></p></li>");
							})
						changeProjectColor();
						$(".spaceused").progressBar({showText:true});
					}
				});
				</script>
			</authz:authorize>
			<authz:authorize ifAnyGranted="ProjectViewController.do?method=searchProjectListForSuperviseManager">
				<script type="text/javascript">
				//获取监管单位管理员相关联的项目信息
				$('#serviceName').val('searchProjectListForSuperviseManager');
				$.getJSON($('#initPath').val()+'/ProjectViewController.do?method=searchProjectListForSuperviseManager',{page:"1",rp:"5",queryColumns:'objId,ebuyMethod,projCode,projName,createTime'},function(json){
					if(json.total < 1){
						$("#myProjectCount").append(0);
						$("#more").remove();
						$("#projectList").empty().append("<li>"
								+"暂时没相关的招标项目！</li>");
					}else{
						$("#myProjectCount").append(json.total);
						$("#mySuperviseManagerProject").empty();
						$.each(json.rows,function(i,n){
							if(n.projProcessStatusCN==undefined){
								n.projProcessStatusCN = "还未开始！"
							}
							var shortTime = n.createTime.substring(0,10);
							var shortName = "";
							if(n.projName.length>15){
							 	shortName = n.projName.substring(0,15)+"...";
							}else {
								shortName = n.projName;
							}
							$("#projectList").append("<li><div class='desktopInfo'>"
									+"<h6><a href='#' name=\"projs\" onClick=\"ProjectInfoReload('"+n.objId+"');\" title=\""+n.projName+"(项目编号:"+n.projCode+")\">"
									+"<span id='"+n.ebuyMethod+"' name='"+n.ebuyMethod+"'></span>"+"【"+n.projCode+"】"+shortName
									+"</a></h6><p class='keyInfo'>项目类型："+n.tenderTypeCN+",采购方式:"+n.ebuyMethodCN+",时间:"+shortTime+",进度 :<span class='spaceused' >"+n.processPers+"</span></p></div><p class='curState greenHighlight'>"+n.projProcessStatusCN+"</p> <p class='functionBtnDiv'><button type='button' onClick=\"ProjectInfoReload('"+n.objId+"');\"><span>进入项目</span></button></p></li>");
							})
						changeProjectColor();
						$(".spaceused").progressBar({showText:true});
					}
				});
				</script>
			</authz:authorize>
			<authz:authorize ifAnyGranted="ProjectViewController.do?method=searchProjectListForBuyer">
				<script type="text/javascript">
				//获取采购人相关联的项目信息
				$('#serviceName').val('searchProjectListForBuyer');
				$.getJSON($('#initPath').val()+'/ProjectViewController.do?method=searchProjectListForBuyer',{page:"1",rp:"5",queryColumns:'objId,ebuyMethod,projCode,projName,createTime'},function(json){
					if(json.total < 1){
						$("#myProjectCount").append(0);
						$("#more").remove();
						$("#projectList").empty().append("<h2 style=\"border-bottom: 1px dotted rgb(204, 204, 204); width: 98%; padding: 3px;\">"
								+"暂时没相关的招标项目！</h2>");
					}else{
						$("#myProjectCount").append(json.total);
						$("#myBuyerProject").empty();
						$.each(json.rows,function(i,n){
							if(n.projProcessStatusCN==undefined){
								n.projProcessStatusCN = "还未开始！"
							}
							var shortTime = n.createTime.substring(0,10);
							var shortName = "";
							if(n.projName.length>15){
							 	shortName = n.projName.substring(0,15)+"...";
							}else {
								shortName = n.projName;
							}
							$("#projectList").append("<li><div class='desktopInfo'>"
									+"<h6><a href='#' name=\"projs\" onClick=\"ProjectInfoReload('"+n.objId+"');\" title=\""+n.projName+"(项目编号:"+n.projCode+")\">"
									+"<span id='"+n.ebuyMethod+"' name='"+n.ebuyMethod+"'></span>"+"【"+n.projCode+"】"+shortName
									+"</a></h6><p class='keyInfo'>项目类型："+n.tenderTypeCN+",采购方式:"+n.ebuyMethodCN+",时间:"+shortTime+",进度 :<span class='spaceused' >"+n.processPers+"</span></p></div><p class='curState greenHighlight'>"+n.projProcessStatusCN+"</p> <p class='functionBtnDiv'><button type='button' onClick=\"ProjectInfoReload('"+n.objId+"');\"><span>进入项目</span></button></p></li>");
							})
						changeProjectColor();
						$(".spaceused").progressBar({showText:true});
					}
				});
				</script>
			</authz:authorize>
			<authz:authorize ifAnyGranted="ProjectViewController.do?method=searchProjectListForGovernment">
				<script type="text/javascript">
				//获取监管单位管理员相关联的项目信息
				$('#serviceName').val('searchProjectListForGovernment');
				$.getJSON($('#initPath').val()+'/ProjectViewController.do?method=searchProjectListForGovernment',{page:"1",rp:"5",queryColumns:'objId,ebuyMethod,projCode,projName,createTime'},function(json){
					if(json.total < 1){
						$("#myProjectCount").append(0);
						$("#more").remove();
						$("#projectList").empty().append("<li>"
								+"暂时没相关的招标项目！</li>");
					}else{
						$("#myProjectCount").append(json.total);
						$("#myGovernmentProject").empty();
						$.each(json.rows,function(i,n){
							var shortTime = n.createTime.substring(0,10);
							var shortName = "";
							
							if(n.projProcessStatusCN=='undefined'){
								n.projProcessStatusCN = "还未开始！"
							}
							if(n.projName.length>15){
							 	shortName = n.projName.substring(0,15)+"...";
							}else {
								shortName = n.projName;
							}
							$("#projectList").append("<li><div class='desktopInfo'>"
									+"<h6><a href='#' name=\"projs\" onClick=\"ProjectInfoReload('"+n.objId+"');\" title=\""+n.projName+"(项目编号:"+n.projCode+")\">"
									+"<span id='"+n.ebuyMethod+"' name='"+n.ebuyMethod+"'></span>"+"【"+n.projCode+"】"+shortName
									+"</a></h6><p class='keyInfo'>项目类型："+n.tenderTypeCN+",采购方式:"+n.ebuyMethodCN+",时间:"+shortTime+",进度 :<span class='spaceused' >"+n.processPers+"</span></p></div><p class='curState greenHighlight'>"+n.projProcessStatusCN+"</p> <p class='functionBtnDiv'><button type='button' onClick=\"ProjectInfoReload('"+n.objId+"');\"><span>进入项目</span></button></p></li>");
							})
						changeProjectColor();
						$(".spaceused").progressBar({showText:true});
					}
				});
				</script>
			</authz:authorize>
			<authz:authorize ifAnyGranted="ProjectController.do?method=searchProjectListDljg&serviceName=searchProjectListForSupply">
				<script type="text/javascript">
				//获取供应商相关联的项目信息
				$('#serviceName').val('searchProjectListForSupply');
				$.getJSON($('#initPath').val()+'/ProjectViewController.do?method=searchProjectListForSupply',{page:"1",rp:"5",queryColumns:'objId,ebuyMethod,projCode,projName,createTime'},function(json){
					if(json.total < 1){
						$("#myProjectCount").append(0);
						$("#more").remove();
						$("#projectList").empty().append("<li>"
								+"暂时没相关的招标项目！</li>");
					}else{
						$("#myProjectCount").append(json.total);
						$("#mySupplyProject").empty();
						$.each(json.rows,function(i,n){
							if(n.projProcessStatusCN==undefined){
								n.projProcessStatusCN = "还未开始！"
							}
							var shortTime = n.createTime.substring(0,10);
							if(n.projName.length>15){
							 	shortName = n.projName.substring(0,15)+"...";
							}else {
								shortName = n.projName;
							}
							$("#projectList").append("<li><div class='desktopInfo'>"
									+"<h6><a href='#' name=\"projs\" onClick=\"ProjectInfoReload('"+n.objId+"');\" title=\""+n.projName+"(项目编号:"+n.projCode+")\">"
									+"<span id='"+n.ebuyMethod+"' name='"+n.ebuyMethod+"'></span>"+"【"+n.projCode+"】"+shortName
									+"</a></h6><p class='keyInfo'>项目类型："+n.tenderTypeCN+",采购方式:"+n.ebuyMethodCN+",时间:"+shortTime+",进度 :<span class='spaceused' >"+n.processPers+"</span></p></div><p class='curState greenHighlight'>"+n.projProcessStatusCN+"</p> <p class='functionBtnDiv'><button type='button' onClick=\"ProjectInfoReload('"+n.objId+"');\"><span>进入项目</span></button></p></li>");
							})
						changeProjectColor();
						$(".spaceused").progressBar({showText:true});
					}
				});
				$.getJSON($('#initPath').val()+'/BulletinController.do?method=getBulletinList',{page:"1",rp:"5",queryColumns:'objId,bullTitle,bulletinNo,createTime,bullType',bullType_op:'in',bullType:'\'01\',\'10\'',auditStatus:'01'},function(json){
					if(json.total < 1){
						$("#bulletinList").empty().append("<li>"
								+"暂时没相关的公告！</li>");
					}else{
						$.each(json.rows,function(i,n){
							var shortTime = n.createTime.substring(0,10);
							$("#bulletinList").append("<li><div class='desktopInfo'>"
									+"<h6><a href='#' name=\"projs\" onClick=\"mydesktop_supervise.view('"+n.objId+"','"+n.bullType+"');\" title=\""+n.bullTitle+"(公告编号:"+n.bulletinNo+")\">"
									+"<span id='"+n.bullType+"' name='"+n.bullType+"'></span>"+"【"+n.bulletinNo+"】"+n.bullTitle
									+"</a></h6><p class='keyInfo'>公告类型："+n.bullTypeCN+",创建时间:"+shortTime+"</p></div><p class='curState greenHighlight'>"+n.bullTypeCN+"</p> <p class='functionBtnDiv'><button type='button' onClick=\"mydesktop_supervise.view('"+n.objId+"','"+n.bullType+"');\"><span>查看公告</span></button></p></li>");
							})
						changeProjectColor();
					}
				});
				</script>
			</authz:authorize>
			</div>
