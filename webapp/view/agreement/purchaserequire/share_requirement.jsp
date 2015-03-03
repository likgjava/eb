<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" >
$(document).ready(function(){
	//推荐
	$("#requirementShare").click(function(){
		if(!$('#shareRequirementFrom').valid()){return;}
		var param = formToJsonObject("shareRequirementFrom");
		$.getJSON($("#initPath").val()+"/RequirementController.do?method=shareRequirement", param, function(json){
			if(json.success){
				alert("推荐成功！");
				$('.epsDialogClose').click();
			}
		});
	});

	//关闭
	$("#requirementReturn").click(function(){
		$('.epsDialogClose').click();
	})
})
</script>

<form id="shareRequirementFrom">
	<input type="hidden" id="requirementId" name="requirementId" value="${param.requirementId }"/>
	<div>
		<ul>
			<li>
				<label>好友邮箱：</label>
				<input id="email" name="toEmail" size="20" class="required email">
				<span class='eleRequired'>*</span>
			</li>
		</ul>
	</div>

	<div class="conOperation center"><br/>
 		<button id="requirementShare" type="button" class="enterBtn"><span>推荐</span></button>
 		<button id="requirementReturn" type="button" ><span><spring:message code="globe.close"/></span></button>
	</div>
</form>