<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div id="voteTemplateInfo" class="formLayout form2Pa">
	<div class="k1">
		<c:choose>
			<c:when test="${voteAssessedThing.picture != null}">
				<div class="img_250_1" id="newPreview">
					<img id="view"  src="AttachmentController.do?method=showImg&objId=${voteAssessedThing.picture }" width="200px" height="175px"></img>
				</div>
			</c:when>
			<c:otherwise>
					<img width="200" height="175" src="<c:url value="/view/resource/skin/bizplatform/img/orginfo_add.gif"/>"></img>
			</c:otherwise>
		</c:choose>
		<center>${voteAssessedThing.attenderName }</center>
	</div>
  
	<h4>描述：</h4>
	<div>${voteAssessedThing.thingComment }</div>
	
	<div class="conOperation">
		<button type="button" id="voteAssessedThingBtnReturn" tabindex="19""><span><spring:message code="globe.return"/></span></button>
	</div>
</div>
<script>
//返回
$('#voteAssessedThingBtnReturn').click(function(){
	//自动关闭		
	$('.epsDialogClose').trigger('click');	
});
</script>