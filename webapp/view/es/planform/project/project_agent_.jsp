<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<br class="spacer" />
<div id="detail_project" class="wrap">
	<div id="simple" >
		<h2>[${project.projCode }]${project.projName }<img onclick="javascript:bullet_arrow_up();" alt="点击展开" src="<%=request.getContextPath() %>/view/resource/images/icon16/16x16_0140/bullet_arrow_down.png"></img></h2>
		<p class="rightTxt1">采购方式：<span>${project.ebuyMethodCN }</span> [<a href="#"  onClick="javascript:checkProjectMenuForDialog('menu_bidtype',true);">变更</a>]<span style="padding-left:80px;padding-right:60px;"></span><span class="rightTxt1">项目经办人：${project.projManager }[<a href="#"    onClick="javascript:checkProjectMenuForDialog('xmjbr_fp',true);">变更</a>]</span></p>
		<p class="rightTxt1">
		项目<dm:out value="local__package" tenderType="${project.tenderType}">包组</dm:out>：
		<c:set var="subProjectCount1" value="0"/>
		<c:forEach items="${project.subProject}" var="subProject">
			<c:set var="subProjectCount1" value="${subProjectCount1+1}"/>
			<c:if test="${subProjectCount1==1 }">
				<!-- 若是第一个包组，则需要把包组ID隐藏了公有数据中 -->
				<script language="javascript">$("#subProjectId").val('${subProject.objId}');</script>
			</c:if>
			<input type="radio" name="subProjectRadio" id="${subProject.objId }" onClick="javascript:project_agent.changeSubProjectId('${subProject.objId}');" <c:if test="${subProjectCount1==1 }">checked</c:if>/><a href=""  onClick="">${subProject.projName }</a>&nbsp;
		</c:forEach>
		<c:if test="${subProjectCount == 0}">未分<dm:out value="local__package" tenderType="${project.tenderType}">包组</dm:out></c:if>
		</p>
		<div id="tj">
			<p class="rightTxt2">
				待办任务（5）<span style="padding-left:40px;padding-right:40px;"></span>
				投标单位（9）<span style="padding-left:40px;padding-right:40px;"></span>
				<dm:out value="local__OpenTendering__notice_zh">通知书</dm:out>（6）<span style="padding-left:40px;padding-right:40px;"></span>
			</p>
		</div>
	</div>
	<div id="simple_right" >
		<h2>快速通道</h2>
		<p class="rightTxt1"><dm:out value="local__PURBULLETIN"  tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">公告</dm:out> [<a href="#" onclick="javascript:checkProjectMenuForDialog('menu_purBulletin',true);">查看</a>]&nbsp;&nbsp;<dm:out value="local__PURDOC" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">招标文件</dm:out> [<a href="#" onclick="javascript:checkProjectMenuForDialog('menu_purchaseDoc',false);">查看</a>]</p>
		<p class="rightTxt1">变更公告 [<a href="#">查看</a>]质疑 [<a href="#">查看</a>]</p>
	</div>
	<div id="left" style="display:none">
		<h2>[${project.projCode }]${project.projName }<img onclick="javascript:bullet_arrow_down();" alt="点击收起" src="<%=request.getContextPath() %>/view/resource/images/icon16/16x16_0140/bullet_arrow_up.png"></img></h2>
		<!-- 隐藏数据 -->
		<!-- 项目 全局使用 start-->
		<input type="hidden" value="${project.objId }" id="projectId" name="projectId"/>
		<input type="hidden" value="" id="subProjectId" name="subProjectId" />
		<!-- 项目 全局使用 end-->
		<p class="rightTxt1">采购方式：<span>${project.ebuyMethodCN }</span> [<a href="#"  onClick="javascript:checkProjectMenuForDialog('menu_bidtype',true);">变更</a>]<span style="padding-left:80px;padding-right:60px;"></span><span class="rightTxt1">项目经办人：${project.projManager }[<a href="#"    onClick="javascript:checkProjectMenuForDialog('xmjbr_fp',true);">变更</a>]</span></p>
		<p class="rightTxt1">联 系 人：[<a href="#">刘洋（13910003872 010-62567890-830 liuy@gpcsoft.com）</a> </p>
		<p class="rightTxt1">报名时间：<fmt:formatDate value="${project.projectSchedule.signUpSTime}" pattern="yyyy-MM-dd HH:mm"/> 至 <fmt:formatDate value="${project.projectSchedule.signUpETime}" pattern="yyyy-MM-dd HH:mm"/> <span style="padding-left:50px;"></span><dm:out value="local__OpenTendering__submitTender_zh">投标</dm:out>时间： <fmt:formatDate value="${project.projectSchedule.tenderStartTime}" pattern="yyyy-MM-dd HH:mm"/> 至  <fmt:formatDate value="${project.projectSchedule.tenderEndTime}" pattern="yyyy-MM-dd HH:mm"/> </p>
		<p class="rightTxt1">
		项目包组：
		<c:set var="subProjectCount" value="0"/>
		<c:forEach items="${project.subProject}" var="subProject">
			<c:set var="subProjectCount" value="${subProjectCount+1}"/>
			<c:if test="${subProjectCount==1 }">
				<!-- 若是第一个包组，则需要把包组ID隐藏了公有数据中 -->
				<script language="javascript">$("#subProjectId").val('${subProject.objId}');</script>
			</c:if>
			<input type="radio" name="subProjectRadio" id="${subProject.objId }" onClick="javascript:project_agent.changeSubProjectId('${subProject.objId}');" <c:if test="${subProjectCount==1 }">checked</c:if>/><a href=""  onClick="">${subProject.projName }</a>&nbsp;
		</c:forEach>
		<c:if test="${subProjectCount == 0}">未分<dm:out value="local__package" tenderType="${project.tenderType}">包组</dm:out></c:if>
		</p>
		<div id="tj">
			<p class="rightTxt2">
				待办任务（5）<span style="padding-left:40px;padding-right:40px;"></span>
				投标单位（9）<span style="padding-left:40px;padding-right:40px;"></span>
				<dm:out value="local__OpenTendering__notice_zh">通知书</dm:out>（6）<span style="padding-left:40px;padding-right:40px;"></span>
			</p>
		</div>
	</div>
	<div id="right" style="display:none">
		<h2>快速通道</h2>
		<p class="rightTxt1"><dm:out value="local__PURBULLETIN"  tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">公告</dm:out> [<a href="#" onclick="javascript:checkProjectMenuForDialog('menu_purBulletin',true);">查看</a>]</p>
		<p class="rightTxt1"><dm:out value="local__PURDOC" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">招标文件</dm:out>[<a href="#" onclick="javascript:checkProjectMenuForDialog('menu_purchaseDoc',false);">查看</a>]</p>
		<p class="rightTxt1">变更公告 [<a href="#">查看</a>]</p>
		<p class="rightTxt1">质疑 [<a href="#">查看</a>]</p>
	</div>
</div>
<br class="spacer" />
<div class="wrap background">
	<ul id="menu">
		<li><a id="tab1" class="current" href="#">准备阶段</a></li>
		<li><a id="tab2" href="#">执行阶段</a></li>
		<li><a id="tab3" href="#">定标阶段</a></li>
	</ul>
	<div id="menuTab">
		<h5>
			
		</h5>
	</div>
</div>
<div id="projDoDiv">
</div>
<div id="menu1" style="display:none">
	<span onClick="javascript:checkProjectMenu('menu_project');">设置<dm:out value="local__package" tenderType="${project.tenderType}">包组</dm:out></span>&nbsp;&nbsp;&nbsp;&nbsp;
	<span onClick="javascript:checkProjectMenu('menu_projProcessRule');">设置项目规则</span>&nbsp;&nbsp;&nbsp;&nbsp;
	<span onClick="javascript:checkProjectMenu('menu_openbidgroup');"><dm:out value="local__OpenTendering__openBid_zh">开标</dm:out>小组</span>&nbsp;&nbsp;&nbsp;&nbsp;
	<span onClick="javascript:checkProjectMenu('menu_meetRoom');">设置标评室</span>&nbsp;&nbsp;&nbsp;&nbsp;
	<span onClick="javascript:checkProjectMenu('menu_purchaseDoc');"><dm:out value="local__PURDOC" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">招标文件</dm:out></span>&nbsp;&nbsp;&nbsp;&nbsp;
	<span onClick="javascript:checkProjectMenu('menu_purBulletin');"><dm:out value="local__PURBULLETIN"  tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">公告</dm:out></span>&nbsp;&nbsp;&nbsp;&nbsp;
</div>
<div id="menu2" style="display:none">
	<span onClick="javascript:checkProjectMenu('menu_openbid');"><dm:out value="local__OpenTendering__openBid_zh">开标</dm:out></span>&nbsp;&nbsp;&nbsp;&nbsp;
	<span onClick="javascript:checkProjectMenu('menu_evalbid');">评标</span>&nbsp;&nbsp;&nbsp;&nbsp;
</div>
<div id="menu3" style="display:none">
	<span onClick="javascript:checkProjectMenu('menu_project');"><dm:out  value="local__PURCHASEPREVIEW" tenderType="${project.tenderType}">结果公示</dm:out></span>&nbsp;&nbsp;&nbsp;&nbsp;
	<span onClick="javascript:checkProjectMenu('menu_project');"><dm:out  value="local__PURCHASEPREVIEW" tenderType="${project.tenderType}">结果公示</dm:out></span>&nbsp;&nbsp;&nbsp;&nbsp;
	<span onClick="javascript:checkProjectMenu('menu_project');">审核<dm:out  value="local__PURCHASEPREVIEW" tenderType="${project.tenderType}">结果公示</dm:out></span>&nbsp;&nbsp;&nbsp;&nbsp;
	<span onClick="javascript:checkProjectMenu('menu_project');">发布<dm:out  value="local__PURCHASEPREVIEW" tenderType="${project.tenderType}">结果公示</dm:out></span>&nbsp;&nbsp;&nbsp;&nbsp;
	<span onClick="javascript:checkProjectMenu('menu_project');"><dm:out value="local__RESULTBULLETIN" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">中标公告</dm:out></span>&nbsp;&nbsp;&nbsp;&nbsp;
	<span onClick="javascript:checkProjectMenu('menu_project');">审核<dm:out value="local__RESULTBULLETIN" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">中标公告</dm:out></span>&nbsp;&nbsp;&nbsp;&nbsp;
	<span onClick="javascript:checkProjectMenu('menu_project');">审批<dm:out value="local__RESULTBULLETIN" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">中标公告</dm:out></span>&nbsp;&nbsp;&nbsp;&nbsp;
	<span onClick="javascript:checkProjectMenu('menu_project');">发布<dm:out value="local__RESULTBULLETIN" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">中标公告</dm:out></span>&nbsp;&nbsp;&nbsp;&nbsp;
	<span onClick="javascript:checkProjectMenu('menu_project');">结果<dm:out value="local__OpenTendering__notice_zh">通知书</dm:out></span>&nbsp;&nbsp;&nbsp;&nbsp;
</div>
<script>
var project_agent={};
$(document).ready(function(){
	$("#tab1").click(function(){
		$('#tab2').removeClass();
		$('#tab3').removeClass();
   		$('#tab1').addClass('current');
   		$("#menuTab").empty().append("<h5>"+$("#menu1").html()+"</h5>");
	});
	$("#tab2").click(function(){
		$('#tab1').removeClass();
		$('#tab3').removeClass();
   		$('#tab2').addClass('current');
   		$("#menuTab").empty().append("<h5>"+$("#menu2").html()+"</h5>");
	});
	$("#tab3").click(function(){
		$('#tab1').removeClass();
		$('#tab2').removeClass();
   		$('#tab3').addClass('current');
   		$("#menuTab").empty().append("<h5>"+$("#menu3").html()+"</h5>");
	});
	//$('#projDoDiv').loadPage($('#initPath').val()+"/view/es/planform/project/project_left_agent.jsp");
})
//改变隐藏的公有参数
project_agent.changeSubProjectId = function(subProjectId){
	$("#subProjectId").val(subProjectId);
}


//弹出显示
function checkProjectMenuForDialog(id,isReLoad){
	var projectId = $('#projectId').val();
	if(id == 'menu_bidtype'){
		dialogPublicMethod("/ProjectController.do?method=toProjectComWork&projectId="+$("#projectId").val(),isReLoad,"变更采购方式",800,300);
	}
	if(id=='menu_purBulletin'){
		//招标公告
		dialogPublicMethod("/BulletinController.do?method=toPurBulletin&projectId="+$("#projectId").val(),isReLoad,"招标公告",1000,500);
	}
	if(id == 'xmjbr_fp'){//分配项目经办人
		dialogPublicMethod("/ProjectController.do?method=toProjectLinkGovMan&projectId="+$("#projectId").val(),isReLoad,"分配项目经办人",800,300);
	}
	if(id=='menu_purchaseDoc'){
		dialogPublicMethod("/PurchaseDocController.do?method=toPurchaseDoc&projectId="+$("#projectId").val(),isReLoad,"招标文件",800,300);
	}
}
//弹出层共有方法
function dialogPublicMethod(url,isReLoad,title,width,height){
	var projectId = $('#projectId').val();
	$.epsDialog({
        title:title,
        url:$("#initPath").val()+url,
        width: width,
        height: height,
        onClose: function(){ 
       			if(isReLoad){//若需要刷新页面，则跳转
        		//跳转到招标中心项目首页
        		$('#conBody').empty().loadPage($('#initPath').val()+'/ProjectController.do?method=toProjectInfoForAgent&objId='+projectId);
        	}
        }
	});
}
//公共区域显示
function checkProjectMenu(id){
	var projectId = $('#projectId').val();
	/*
	if(id == 'menu_bidtype'){
		$('#projDoDiv').empty().loadPage($("#initPath").val()+"/ProjectController.do?method=toProjectComWork&projectId="+$("#projectId").val());	     
	}
	if(id == 'menu_project_number'){
		//设置招标编号
		$("#projDoDiv").empty().loadPage($("#initPath").val()+"/view/es/planform/project/numberSet.jsp?projectId="+$("#projectId").val());
	}*/
	if(id == 'menu_project'){//项目分包
		$("#projDoDiv").empty().loadPage($("#initPath").val()+"/ProjectController.do?method=toProjectCraete&projectId="+$("#projectId").val());
	}
	if(id=='menu_purchaseDoc'){//招标文件
		$("#projDoDiv").empty().loadPage($('#initPath').val()+'/PurchaseDocController.do?method=toPurchaseDoc&projectId='+$("#projectId").val());
	}
	if(id=='menu_purBulletin'){
		//招标公告
		$("#projDoDiv").empty().loadPage($('#initPath').val()+'/BulletinController.do?method=toPurBulletin&projectId='+$("#projectId").val());
	}
	if(id=='menu_projProcessRule'){//项目规则
		$("#projDoDiv").empty().loadPage($('#initPath').val()+'/ProjProcessRuleController.do?method=toProjProcessRule&projectId='+$("#projectId").val());
	}
	if(id=='menu_evaSellerRecord')
	{   //评标
		$("#projDoDiv").empty().loadPage($('#initPath').val()+'/EvaSellerRecordController.do?method=toEvaSellerRecord&projectId='+$("#projectId").val());
	}
	if(id=='menu_meetRoom'){//设置标评室   针对包组
		$("#projDoDiv").empty().loadPage($('#initPath').val()+'/view/es/planform/projrule/meetRoomShowView.jsp?projectId='+$("#projectId").val());
	}
	if(id=='menu_openbidgroup'){//开标小组
		$("#projDoDiv").empty().loadPage($('#initPath').val()+'/WorkGroupController.do?method=toWorkGroupMemberListForOpenBid&projectId='+$("#projectId").val());
	}
	if(id=='menu_openbid'){//开标
		$("#projDoDiv").empty().loadPage($('#initPath').val()+'/view/es/planform/projreview/openBidList.jsp?projectId='+$("#projectId").val());
	}
	if(id=='menu_evalbid'){//评标
		$("#projDoDiv").empty().loadPage($('#initPath').val()+'/EvaSellerRecordController.do?method=toEvaSellerRecord&projectId='+$("#projectId").val());
	}
}

//收起
function bullet_arrow_down(){
	$("#simple").show();
	$("#simple_right").show();
	$("#left").hide();
	$("#right").hide();
}
//展开
function bullet_arrow_up(){
	$("#left").show();
	$("#right").show();
	$("#simple").hide();
	$("#simple_right").hide();
}
</script>