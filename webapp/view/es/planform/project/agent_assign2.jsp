<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<input type="hidden" name="taskPlanId" id="taskPlanId" value="<c:out value="${param.objId}"/>"/>
<div class="partContainers">
	<div class="formLayout form2Pa">
		<form id="agentAssignForm">
		<h5><span>设置采购方式</span></h5>
			<ul>
				<li class="fullLine">
				   <label for="input02">采购方式：</label>
				   <input type="hidden" name="objId" id="objId" value="${taskPlan.objId}"/>
				   <select name="ebuyMethod" id="ebuyMethod">
				       <option value="00" <c:if test="${taskPlan.ebuyMethod==00}">selected="selected"</c:if>>公开招标</option>
			           <option value="01" <c:if test="${taskPlan.ebuyMethod==01}">selected="selected"</c:if>>邀请招标</option>
			           <option value="02" <c:if test="${taskPlan.ebuyMethod==02}">selected="selected"</c:if>>竞争性谈判</option>
			           <option value="03" <c:if test="${taskPlan.ebuyMethod==03}">selected="selected"</c:if>>询价采购</option>
			           <option value="04" <c:if test="${taskPlan.ebuyMethod==04}">selected="selected"</c:if>>单一来源</option>
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
	
	//指定采购方式
	$("#sure").click(function(){
		$.getJSON($('#initPath').val()+'/TaskPlanController.do?method=submitEBuyMethod', formToJsonObject('agentAssignForm'), function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			alert("设置采购方式成功！");
			$('button[name=historyBackBtn]').click();
		});

	});
	
});
</script>