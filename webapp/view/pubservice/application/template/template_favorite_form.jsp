<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<form id="favoriteTemplateFrom">
	<input type="hidden" id="dotTemplate.objId" name="dotTemplate.objId" value="${dotTemplate.objId}" />
	<c:if test="${hadFavorite}">
		<div class="formTips attention"><em>提示：</em><span>您已加入收藏！</span></div>
	</c:if>
	<div id="addFavorite" class="formLayout form1Pa">
	    <h4 style="height: auto;"><span>${dotTemplate.templateName}</span></h4>
		<ul>
			<li>
				<label>标签：</label>
				<input id="favoriteName" name="favoriteName" class="required" <c:if test="${hadFavorite}">disabled='disabled'</c:if> value="${favoriteName}">
			</li>	
		</ul>
	</div>
</form>

<div class="conOperation">
	<c:if test="${!hadFavorite}">
 	<button id="favoriteSave" type="button"><span><spring:message code="globe.save"/></span></button>
 	</c:if>
 	<button id="favoriteClose" type="button"><span><spring:message code="globe.close"/></span></button>
</div>

<script type="text/javascript">
$(document).ready(function(){
	//保存
	$("#favoriteSave").click(function(){
		if(!$('#favoriteTemplateFrom').valid()){return;}
		var favorites = formToJsonObject("favoriteTemplateFrom");
		$.getJSON($("#initPath").val()+"/DotTemplateFavoriteController.do?method=saveDotTemplateFavorite",favorites,function(json){
			if(json.success){
				alert('收藏成功！');
				$("#favoriteClose").click();
			}
		});
	});

	//关闭
	$("#favoriteClose").click(function(){
		$('.epsDialogClose').click();
	})
})
</script>