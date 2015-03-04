<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<input type="hidden" name="taskPlanId" id="taskPlanId" value="<c:out value="${param.objId}"/>"/>
<input type="hidden" id="taskType" value="${taskPlan.taskType}">
<div class="partContainers">
<form id="agentAssignForm">
	<div class="formLayout form2Pa">
		<c:choose>
		<c:when test="${css==null}"><h5><span>指定项目负责人</span></h5>
			<ul>
				<li class="fullLine">
				   <label  class="short" for="input02">项目负责人：</label>
				   <select name="leader.objId" id="leader">
				   		<option value="-1">--请选择--</option>
				        <c:forEach items="${empList}" var="empe" >	
				        <option value="${empe.objId}" <c:if test="${empe.objId==taskPlan.leader.objId}">selected="selected"</c:if>>${empe.name}</option>
				        </c:forEach>
				    </select>
				        <span class='eleRequired'>*</span><span class="eleWarning"></span>
				</li>
				<li class="fullLine">
					<label class="short" for="taskCode"><spring:message code="taskPlanForm.ebuyMethod"/>：</label>
					<html:select styleClass="required" id="ebuyMethod" name="ebuyMethod" code="ebuyMethod" selectedValue="${taskPlan.ebuyMethod}">
					</html:select>
				        <span class='eleRequired'>*</span><span class="eleWarning"></span>
				</li>
			</ul></c:when>
		<c:otherwise>
		<h5><span>指定项目负责人</span></h5>
			<ul>
				<li class="fullLine">
				   <label for="input02">项目负责人：</label>
				        <span > <c:forEach items="${empList}" var="empe" >	
				        <c:if test="${empe.objId==taskPlan.leader.objId}">${empe.name}</c:if>
				        </c:forEach></span>
				</li>
				<li class="fullLine">
				   <label for="input02">采购方式：</label>
				   <span>
				      <c:if test="${taskPlan.ebuyMethod==00}">公开招标</c:if>
			          <c:if test="${taskPlan.ebuyMethod==01}">邀请招标</c:if>
			           <c:if test="${taskPlan.ebuyMethod==02}">竞争性谈判</c:if>
			        	<c:if test="${taskPlan.ebuyMethod==03}">询价采购</c:if>
			           <c:if test="${taskPlan.ebuyMethod==04}">单一来源</c:if>
			           </span>
				</li>
			</ul>
		</c:otherwise>
		</c:choose>
	</div>
	
		<c:if test="${css==null}">
		<div class="partContainers formLayoutAudit">
			<h5><span>审核意见</span></h5>
			<ul>
				<li>
				<textarea name="opinion" id="opinion" class="texFull" >同意</textarea>
				</li>
			</ul>
		</div>
			<div class="conOperation">
				<c:if test="${css==null}">
		    	<button type="button" id="sure"><span>通过</span></button>
		    	</c:if>
		    	<button type="button" id="nopass"><span>不通过</span></button>
		    	<button name="historyBackBtn" type="button" tabindex="20"><span><spring:message code="globe.return"/></span></button>
				<button id="historyId" name="historyName" style="<c:if test='${taskPlan.objId == null || taskPlan.objId == "" }'>display:none</c:if>" type="button" tabindex="20"><span>操作历史</span></button>
			</div>
		</c:if>
</form>
<div id="taskPlanDetail"></div>
<div id="historyView"></div>
	<c:if test="${css!=null}">
			<div class="conOperation">
				<button id="historyId" name="historyName" style="<c:if test='${taskPlan.objId == null || taskPlan.objId == "" }'>display:none</c:if>" type="button" tabindex="20"><span>操作历史</span></button>
		    	<button name="historyBackBtn" type="button" tabindex="20"><span><spring:message code="globe.return"/></span></button>
			</div>
	</c:if>	
</div>
<script>
$(document).ready(function(){
	//加载详细信息页面
	$("#taskPlanDetail").loadPage($('#initPath').val()+'/TaskPlanController.do?method=showDetail&type=div&objId='+$("#taskPlanId").val());
	
	$("#sure").click(function(){ //指定项目负责人

		if ($('#leader').val()=='-1'){
			alert('请选择负责人！');
			return false;
		}
		
		if(!$('#agentAssignForm').valid()){
			alert("意见输入过长,请控制!");
			return false;
		}
		if($("#opinion").val()==''||$("#opinion").val()==null){
			$("#opinion").val('同意');
		}
		if ($('#leader').val()==PlatForm.user.emp.objId) {
			if (window.confirm('确认要分配给自己？')) {
				$("#sure").attr("disabled","disabled");
				$.getJSON($('#initPath').val()+'/TaskPlanController.do?method=submitLeader', formToJsonObject('agentAssignForm'), function(json){
					if(json.result)alert(json.result);if(json.failure)return;
					$('button[name=historyBackBtn]').click();
				});
			}
		}else{
			$("#sure").attr("disabled","disabled");
			$.getJSON($('#initPath').val()+'/TaskPlanController.do?method=submitLeader', formToJsonObject('agentAssignForm'), function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$('button[name=historyBackBtn]').click();
			});
		}
	});

	$("#nopass").click(function(){//退回

		if ($('#leader').val()=='-1'){
			alert('请选择负责人！');
			return false;
		}
	
		if(!$('#agentAssignForm').valid()){
			alert("意见输入过长,请控制!");
			return false;
		}
		if($("#opinion").val()==''||$("#opinion").val()==null||$("#opinion").val()=='同意'){
				$("#opinion").val('');
				alert("请添加不通过原因！");
				return;
			}
		$("#nopass").attr("disabled","disabled");
		$.getJSON($('#initPath').val()+'/TaskPlanController.do?method=submitLeaderNoPass', formToJsonObject('agentAssignForm'), function(json){
		if(json.result)alert(json.result);if(json.failure)return;
		$('button[name=historyBackBtn]').click();
		});
		})




	
});

	//操作历史
	$('#historyId').click(function(){
		$("#historyView").loadPage($('#initPath').val()+"/CompletedWorkTaskController.do?method=getCompletedWorkTaskByBizId&bizId="+$("#taskPlanId").val()+'&taskType=00');
	});

  
	
   //如果进入页面时采购方式不为公开招标
   if($(":select[name='ebuyMethod']:checked").val()!='00'){
    	       $("select[name='ebuyMethod']").css("color","red"); 
           }
   
   
   //判断当前项目采购方式是否为公开招标
    $("select[name='ebuyMethod']").change( function() {
       if($(":select[name='ebuyMethod']:checked").val()!='00'){
    	       $("select[name='ebuyMethod']").css("color","red"); 
           }
       else{
    	   	   $("select[name='ebuyMethod']").css("color","black");
           }
	}); 
</script>