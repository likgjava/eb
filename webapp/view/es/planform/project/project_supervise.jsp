<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/common/planTemplateTask.js"></script>
<!-- 隐藏数据 -->
<!-- 项目 全局使用 start-->
<input type="hidden" value="${project.objId }" id="projectId" name="projectId"/>
<input type="hidden" value="${project.ebuyMethod }" id="ebuyMethod" name="ebuyMethod"/>
<input type="hidden" value="" id="subProjectId" name="subProjectId" />
<input type="hidden" value="" id="projectTaskId" name="projectTaskId" />
<input type="hidden" value="${planId }" id="planId" name="planId"/>
<input type="hidden" value="" id="auditTask_Id"></input>
<!-- 项目 全局使用 end-->
<div id="projectTop" class="accountInfo" style="margin-bottom:5px;">
	<div class="right">
		<p></p>
		<p></p>
	</div>
	<p><span>[${project.projCode }]${project.projName }</span></p>
	<p>
	<b>采购方44式：</b>${project.ebuyMethodCN } 
	|<b>监管：</b>
	${project.monitor.name }
	<authz:authorize ifAnyGranted="ProjectController.do?method=saveProjectMonitor">
	<c:if test="${project.monitor  == null ||project.monitor.objId == null }"><select id="monitor.objId" name="monitor.objId"><option>选择项目监管人</option></select>[<a href="#" onClick="javascript:saveMonitorId();">保存</a>]</c:if>
	</authz:authorize> 
	|<b>负责人：</b>
	${project.manager.name }
	<authz:authorize ifAnyGranted="ProjectController.do?method=toProjectLinkGovMan">
	<c:if test="${project.manager  == null ||project.manager.objId == null }">[<a href="#"    onClick="javascript:checkProjectMenuForDialog('xmjbr_fp',false);">指定经办人</a>]</c:if> |
	</authz:authorize>
	|<b>项目状态：</b>${project.projProcessStatusCN}
	</p>
</div>
<div class="frameMainSub frameMs12">
	<div class="accordion frameMain">
		<div class="accordionHeader">
			<h2><span></span><a href="" target="_blank">工作计划</a></h2>
		</div>
		<div class="accordionContent">
			<div id="menuTree"></div>
		</div>
	</div>
	<div id="projectDoDiv" class="frameSub"></div>
</div>
<script language="javascript"><!--
var projectId = $("#projectId").val();
var projectsupervise = {};

$(document).ready(function(){
	//初始化系统配置类型树
	planTemplateTask.loadPlanTree("menuTree",$("#projectId").val()+"");	
	$.getJSON($('#initPath').val()+'/UserApiController.do?method=getEmpListByCurUser&queryColumns=objId,name', {}, function(json){
		if(json.result)alert(json.result);if(json.failure)return;
		$.each(json.empList,function(i,monitor){
			$("[name=monitor.objId]").append("<option value='"+monitor[0]+"'>"+monitor[1]+"</option>");
		});
	});
})
		

function saveMonitorId(){
	$.getJSON($('#initPath').val()+'/ProjectController.do?method=saveProjectMonitor', {projectId:$("#projectId").val(),monitorId:$("[name=monitor.objId]").val()}, function(json){
		if(json.result)alert(json.result);if(json.failure)return;
		//跳转到项目首页
    	$('#conBody').loadPage($('#initPath').val()+'/ProjectController.do?method=toProjectInfoForAgent&objId='+$("#projectId").val()+'&userType=agent');
	});
}

//弹出显示
function checkProjectMenuForDialog(id,isReLoad){
	if(id == 'xmjbr_fp'){//分配项目负责人
		dialogPublicMethod("/ProjectController.do?method=toProjectLinkGovMan&projectId="+$("#projectId").val(),isReLoad,"分配项目负责人",800,300);
	}
	
}
//弹出层共有方法
function dialogPublicMethod(url,isReLoad,title,width,height){
	$.epsDialog({
        title:title,
        url:$("#initPath").val()+url,
        width: width,
        height: height,
        isReload:isReLoad,
        onClose: function(){ 
       		//跳转到项目首页
        	$('#conBody').loadPage($('#initPath').val()+'/ProjectController.do?method=toProjectInfoForAgent&objId='+projectId+'&userType=agent');
        }
	});
}
</script>