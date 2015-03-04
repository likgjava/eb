<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<input type="hidden" name="taskPlanId" id="taskPlanId" value="<c:out value="${param.objId}"/>"/>

<div class="partContainers">
	<div class="formLayout form2Pa">
		<form id="agentAssignForm">
		<h5><span>设置招标编号</span></h5>
			<ul>
				<li class="fullLine">
				   <label for="input02">招标编号：</label>
				   <input type="text" name="projCode" id="projCode" value="${projectCode}"/>
				   <!-- 默认为临时 -->
				   <input type="hidden" name="useStatus" id="useStatus" value="00"/>
				   <input type="hidden" name="taskPlan.objId" id="taskPlan.objId" value="${taskPlan.objId}"/>
				   <input type="hidden" name="monitor.objId" id="monitor.objId" value="${taskPlan.leader.objId}"/>
				   <input type="hidden" name="ebuyMethod" id="ebuyMethod" value="${taskPlan.ebuyMethod}"/> 
				   <input type="hidden" name="agencies.objId" id="agencies.objId" value="${taskPlan.taskAgent.objId}"/> 
				</li>
				<li class="fullLine">
				<label for="input02">招标名称：</label>
				  <input type="text" name="projName" id="projName" value="${projectName}"/> 
				</li>
			</ul>
			<div class="conOperation">
				<!--<button type="button" id="viewT"><span>查看申报书详细信息</span></button>
		    	--><button type="button" id="sure"><span>确定</span></button>
		    	<button name="historyBackBtn" type="button" tabindex="20"><span><spring:message code="globe.return"/></span></button>
			 </div>
		</form>
	</div>
</div>
<div id="taskPlanDetail"></div>

<script>
$(document).ready(function(){

	$.validator.addMethod(
			"unique",
			function(value,element,param){
				return uniqueHandler("Project",param,value,$("#objId").val());
			},
			'项目编 号已 存在 '
		);
	
	
	$("#agentAssignForm").validate({
		rules:{
			projCode:{unique:"projCode"}
		}
	});	
    
    
	//$("#viewT").click(function(){
		//加载详细信息页面
		$("#taskPlanDetail").loadPage($('#initPath').val()+'/TaskPlanController.do?method=showDetail&type=div&objId='+$("#taskPlanId").val());		
	//});
	
	//指定招标编号
	$("#sure").click(function(){
		$.getJSON($('#initPath').val()+'/ProjectController.do?method=createProjectCode', formToJsonObject('agentAssignForm'), function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			alert("设置招标编号成功！");
			$('button[name=historyBackBtn]').click();
		});
	});
});







</script>