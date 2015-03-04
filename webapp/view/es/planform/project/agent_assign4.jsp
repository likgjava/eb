<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<input type="hidden" name="taskPlanId" id="taskPlanId" value="<c:out value="${param.objId}"/>"/>
<div class="partContainers">
	<div class="formLayout form2Pa">
		<form id="agentAssignForm">
		<h5><span>指定招标中心</span></h5>
			<ul>
				<li class="fullLine">
				   <label for="input02">招标中心：</label>
				   <input type="hidden" name="objId" id="objId" value="${taskPlan.objId}"/>
				   <select name="taskAgent.objId" id="taskAgent.objId">
				   	  <c:forEach items="${orgInfoList}" var="orgInfo" >	
				       	<option value="${orgInfo.objId}" <c:if test="${taskPlan.taskAgent.objId==orgInfo.objId}">selected="selected"</c:if>>${orgInfo.orgName}</option>
				      </c:forEach>
				   </select>
				        <span class='eleRequired'>*</span><span class="eleWarning"></span>
				</li>
			</ul>
			<div class="conOperation">
		    	<button type="button" id="sure"><span>确定</span></button>
		    	<button name="historyBackBtn" type="button" tabindex="20"><span><spring:message code="globe.return"/></span></button>
			</div>
		</form>
	</div>
</div>
<div id="taskPlanDetail"></div>

<script>
$(document).ready(function(){
	//加载详细信息页面
	$("#taskPlanDetail").loadPage($('#initPath').val()+'/TaskPlanController.do?method=showDetail&type=div&objId='+$("#taskPlanId").val());
	
	//指定招标中心
	$("#sure").click(function(){
		$.getJSON($('#initPath').val()+'/TaskPlanController.do?method=submitAgent', formToJsonObject('agentAssignForm'), function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			alert("指定招标中心成功！");
			$('button[name=historyBackBtn]').click();
		});
	});
});
</script>