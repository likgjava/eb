<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<style>
	.imgPlayDiv{
		width: ${adverWidth}px;
		height:${adverHeight}px;
	}
	.imgPlayDiv img{
		width: ${adverWidth}px;
		height:${adverHeight}px;
	}
	.imgPlayDiv li{
		width: ${adverWidth}px;
		height:${adverHeight}px;
	}
	.playerNav{
		background:none repeat scroll 0 0 ;
	}
</style>
<input type="hidden" id="fileValueTotal" value="${fileValueTotal}"/>
<div id="focusDiv">
	<ul id="focusUl">
		<c:forEach var="adverSubscribe" items="${adverSubscribeList}">
			<li>
				<a href="${adverSubscribe.adverLink }" title="" target="_blank"><img src="AttachmentController.do?method=showImg&objId=${adverSubscribe.adverFile }" /><span></span></a>
			</li>
		</c:forEach>
	</ul>
</div>
<script>
var adverPreviewDiv = {};
$(document).ready(function(){
	//新添
	if($("#pictureFile").val() != "" && $("#pictureFile").val() != null){
		var obj = document.getElementById("pictureFile");
		var adverLink = $("#adverLink").val();//广告链接
		$("#focusUl").append('<li><a href="'+adverLink+'" target="_blank"><img src="'+preViewPic(obj)+'"/><span></span></a></li>');
	}
	
	//跑马灯
	$("#focusDiv").imgPlay({cssClass:"imgPlayDiv"});
});
</script>