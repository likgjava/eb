<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script>
/*
 * 执行平台，接收委托协议
 * author: 梁潇静
 * mail: liangxj@gpcsoft.com
 */
var consignFormAccept={};

$(document).ready(function(){

	$('#consignFormAccept').validate();
	//加载详细信息页面
	//确认
	$('#passButton').click(function(){
		if(!$('#consignFormAccept').valid()){
			alert("请正确填写表单！");return;
		}else if(window.confirm("确认接受委托协议吗？")){
			var jsonObj = {};
			jsonObj.objId = $("#objId").val();
			$.getJSON($('#initPath').val()+'/ConsignController.do?method=confirmConsignForAgency&opinion='+$('#opinion').val(), jsonObj, function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				var fromMyDesktop = $('#fromMyDesktop').val();
				//来源于桌面或者是来源于待办任务，则点转到桌面
				if(fromMyDesktop=='yes'||($("#auditTaskId") && $("#auditTaskId").val()!=null && $("#auditTaskId").val()!="")){
					$("#myDesktop").click();
				}else{	
				   $("button[name='returnButton']").click();
				}
			});
		}
	});
	
	//不确认
	$('#noPassButton').click(function(){
		if(!$('#consignFormAccept').valid()){
			alert("请正确填写表单！");return;
		}else if(window.confirm("确认不接受委托协议吗？")){
			var jsonObj = {};
			jsonObj.objId = $("#objId").val();
			$.getJSON($('#initPath').val()+'/ConsignController.do?method=backConsignForAgency&opinion='+$('#opinion').val(), jsonObj, function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				var fromMyDesktop = $('#fromMyDesktop').val();
				//来源于桌面或者是来源于待办任务，则点转到桌面
				if(fromMyDesktop=='yes'||($("#auditTaskId") && $("#auditTaskId").val()!=null && $("#auditTaskId").val()!="")){
					$("#myDesktop").click();
				}else{	
				   $("button[name='returnButton']").click();
				}
			});
		}
	});

	//返回操作
	$("button[name='returnButton']").click(function(){
		$('#conBody').empty().loadPage($('#initPath').val()+"/view/es/planform/consign/consignListForAgencyConfirm.jsp");
	});
	
});
</script>
<input type="hidden" name="fromMyDesktop" id="fromMyDesktop" value="<c:out value="${param.fromMyDesktop}"/>"/>
<input type="hidden" name="type" id="type" value="<c:out value="${param.type}"/>"/>
<div id="consignDetail">
<div class="partContainers">
	<input type="hidden" value="${consign.consType }" id="consignType"/>
	<form:form id="consignForm" method="post" modelAttribute="consign">
		
		<div class="formLayout form2Pa">
     		<ul>
					<li>
						<label class="short" for="consCode"><spring:message code="consignForm.consCode"/>：</label>
						<input type="hidden" value="${consign.objId }" id="objId" name="objId"/>
						<input type="hidden" value="${consign.useStatus }" id="useStatus" name="useStatus"/> 
						<input type="hidden" value="${consign.confirmStatus }" id="confirmStatus" name="confirmStatus"/> 
						<span>${consign.consCode }</span>
					</li>

					<li>
						<label class="short" for="consName"><spring:message code="consignForm.consName"/>：</label>
						<span >${consign.consName }</span> 
					</li>
					<li >
						<label class="short" for="consBuyerName"><spring:message code="consignForm.consBuyerName"/>：</label>
						<span >${consign.consBuyerName }</span> 
					</li>
					<li><label class="short" for="consType">委托类型：</label>
					<c:if test="${consign.consType==00}">
					<span>项目委托</span>
					</c:if>
					<c:if test="${consign.consType==01}">
					<span>长期委托</span>
					</c:if>
					</li>
					<li>
						<label class="short" for="consBuyerLinker"><spring:message code="consignForm.consBuyerLinker"/>：</label>
						<span >${consign.consBuyerLinker }</span> 
					</li>
					
					<li>
						<label class="short" for="consBuyerTel"><spring:message code="consignForm.consBuyerTel"/>：</label>
						<span >${consign.consBuyerTel }</span> 
					</li>

					<li id="dljg" class="fullLine">
						<label class="short" for="consAgent"><spring:message code="taskPlanForm.taskAgent"/>：</label>
						<span >${consign.consAgentName }</span>
						<input type="hidden" name="consAgent.objId" id="consAgent.objId" value="${consign.consAgent.objId }"/>
					</li>
					<c:if test="${consign.consType==01}">
					<li id="startTime">
						<label class="short" for="effectiveStartTime">有效期开始时间：</label>
						<span ><fmt:formatDate value="${consign.effectiveStartTime }" pattern="yyyy-MM-dd HH:mm:SS"/></span> 
					</li>
					<li id="endTime">
						<label class="short" for="effectiveEndTime">有效期结束时间：</label>
						<span ><fmt:formatDate value="${consign.effectiveEndTime }" pattern="yyyy-MM-dd HH:mm:SS"/></span> 
					</li>
					</c:if>
					<li>
						<label class="short" for="consTime"><spring:message code="consignForm.consTime"/>：</label>
						<span ><fmt:formatDate value="${consign.consTime  }" pattern="yyyy-MM-dd HH:mm:SS"/></span> 
					</li>
					
					<li>
						<label class="short" for="consFinishTime"><spring:message code="consignForm.consFinishTime"/>：</label>
						<span ><fmt:formatDate value="${consign.consFinishTime }" pattern="yyyy-MM-dd HH:mm:SS"/></span> 
					</li>

		</ul>
		</div>
	</form:form>
</div>	
</div>
<div class="formLayout" style="height: 73px;">
	<h5><span>确认意见</span></h5>
		<form id="consignFormAccept" method="post">
			<textarea name="opinion" id="opinion" class="maxInput" maxlength="200" style="width: 99%;height: 45px;"></textarea>
			<span class="eleRequired"></span>
		</form>
</div>
<div class="conOperation">
	<button id="passButton" type="button" tabindex="18"><span>确认</span></button>
	<button id="noPassButton" type="button" tabindex="19"><span>退回</span></button>
	<button name="returnButton"  type="button" tabindex="20"><span><spring:message code="globe.return"/></span></button>
</div>
<div id="consign_audit_list"></div>