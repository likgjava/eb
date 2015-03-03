<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" >
$(document).ready(function(){
	//推荐
	$("#bulletinShare").click(function(){
		if(!$('#shareBulletinFrom').valid()){return;}
		var param = formToJsonObject("shareBulletinFrom");
		$.getJSON($("#initPath").val()+"/BulletinShowController.do?method=shareBulletin", param, function(json){
			if(json.success){
				alert("推荐成功！");
				$('.epsDialogClose').click();
			}
		});
	});

	//关闭
	$("#bulletinReturn").click(function(){
		$('.epsDialogClose').click();
	})
})
</script>

<form id="shareBulletinFrom">
	<input type="hidden" id="bulletinId" name="bulletinId" value="${param.bulletinId }"/>
	<div id="shareBulletin">
		<ul>
			<li>
				<label>好友邮箱：</label>
				<input id="email" name="toEmail" size="20" class="required email">
				<span class='eleRequired'>*</span>
			</li>
		</ul>
	</div>

	<div class="conOperation center"><br/>
 		<button id="bulletinShare" type="button" class="enterBtn"><span>推荐</span></button>
 		<button id="bulletinReturn" type="button" ><span><spring:message code="globe.close"/></span></button>
	</div>
</form>