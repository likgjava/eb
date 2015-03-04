<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>

<div id="projectTitle">当前项目：${project.projName} <span class="detailsSwitch" id="projectInfoClick">查看详情</span></div>
<!-- 公有数据隐藏显示 -->
<!-- 项目ID -->
<input type="hidden" value="${project.objId }" name="projectId" id="projectId"/>
<!-- 用户类型 -->
<input type="hidden" value="${param.userType }" name="userType" id="userType"/>
<div id="projectInfo" class="formLayout form2Pa" style="display: block;">
	<!--<ul>
    <li><label>招标编号</label><span>${project.projCode }</span></li>
    <li><label>招标名称</label><span>${project.projName }</span></li>
    <li><label>创建时间</label><span>${project.projName }</span></li>
    <li><label>项目状态</label><span>${project.useStatus }</span></li>
    <li class="formTextarea"><label>项目摘要</label><span>${project.projSummary }</span></li>
	</ul>
--></div>
<div id="projectNav" ></div>
<div id="projectContent">项目操作信息</div>
<script>
$(document).ready(function(){
	if("agent" == $("#userType").val()){
		$("#contentSub").empty().loadPage($('#initPath').val()+'/view/es/planform/project/project_left2.jsp');
		$("#projectContent").empty().loadPage($("#initPath").val()+"/view/es/planform/project/project_agent.jsp");
	}else if("buyer" == $("#userType").val()){
		$("#projectContent").empty().loadPage($("#initPath").val()+"/view/es/planform/project/project_buyer.jsp");
	}else if("supplier" == $("#userType").val()){
		$("#projectContent").empty().loadPage($("#initPath").val()+"/view/es/planform/project/project_supplier.jsp");
	}
	
	$("#projectInfoClick").click(function(){
		$("#projectInfo").empty().loadPage($("#initPath").val()+"/ProjectController.do?method=toProjectInfo&objId="+$("#projectId").val());
		$("#projectInfo").show();
	});
});
</script>
