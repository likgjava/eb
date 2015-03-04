<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div class="toDoTasks">
<input type="hidden" id="planNum" value="${fn:length(projectPlanList)}">
  <h5>待办任务  <a  title="关闭" onclick="nextplanTask.exit();"  href="#" class="sysicon siDelete" style="margin-left: 50px;"></a></h5>
    <ul>
  <c:forEach items="${projectPlanList}" var="plan">
  	  <li><a   href="#" onclick="nextplanTask.selectedProjectPlan('${plan.objId}','${plan.parent.objId}');">${plan.name}</a></li>
  </c:forEach>
	  </ul>
   
</div>
<script>
var nextplanTask={};
$(document).ready(function(){
var planNum = $("#planNum").val();
if(planNum==0){  //当全部任务都完成时，清空待办任务页面。
	$("#nextplanTaskPage").empty();
}

	
nextplanTask.exit = function(){
	$("#nextplanTaskPage").empty();
}
	
nextplanTask.selectedProjectPlan = function(planId,parentPlanId){
	ProjectInfo.selectedProjectPlan(planId,parentPlanId);
	$("#nextplanTaskPage").empty();
}

	
})



</script>
