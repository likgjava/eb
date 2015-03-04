<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script>
$(document).ready(function(){
	$("#releaseBtn").click(function(){
		$.getJSON($('#initPath').val()+'/BulletinController.do?method=resultPublicityForRelease', {objId:$("#objId").val(),auditStatus:$("#auditStatus").val(),taskId:$("#taskId").val(),opinion:$("#opinion").val()}, function(json){
			if(json.result)alert(json.result);if(json.failure)return;
		});
	})
})
</script>
<div class="partContainers">
		<div class="formLayout form2Pa">
		<h5><span><dm:out  value="local__PURCHASEPREVIEW" tenderType="${project.tenderType}">结果公示</dm:out>信息</span></h5>
			<ul>
				<li>
					<label for="bullTitle"><spring:message code="bulletinForm.bullTitle"/>：</label>
					<span id="bullTitle">${bulletin.bullTitle }</span>
				</li>
				<li>
					<label for="bulletinNo"><spring:message code="bulletinForm.bulletinNo"/>：</label>
					<span id="bulletinNo">${bulletin.bulletinNo }</span>
				</li>
				<li>
					<label>&nbsp;</label>
					<span>&nbsp;</span>
				</li>
				<li class="formTextarea">
					<label for="bullContents"><spring:message code="bulletinForm.bullContents"/>：</label>
					<span id="bullContents">${bulletin.bullContents }</span>
				</li>
			</ul>
			<form id="assignDeptForm">
			 <div class="conOperation" align="center">
				   <button type="button" id="releaseBtn"><span>发布<dm:out  value="local__PURCHASEPREVIEW" tenderType="${project.tenderType}">结果公示</dm:out></span></button>
			</div> 
			</form>
		</div>
</div>