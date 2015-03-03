<%@ page pageEncoding="UTF-8"%>
<style>
	.imgPlayUl{
		width: ${advertisingSubscribe.advertisingPosition.adverWidth}px;
		height:${advertisingSubscribe.advertisingPosition.adverLength}px;
	}
	.imgPlayUl img{
		width: ${advertisingSubscribe.advertisingPosition.adverWidth}px;
		height:${advertisingSubscribe.advertisingPosition.adverLength}px;
	}
	.imgPlayUl li{
		width: ${advertisingSubscribe.advertisingPosition.adverWidth}px;
		height:${advertisingSubscribe.advertisingPosition.adverLength}px;
	}
	.playerNav{ background:none repeat scroll 0 0 ;}
</style>


<ul id="focus" class="imgPlayUl">
	<#list adverSubscribeList as adverSubscribe >
		<li><a href="${adverSubscribe['adverLink'] }" target="_blank"><img src="AttachmentController.do?method=showImg&objId=${adverSubscribe['adverFile'] }" /><span></span></a></li>
	</#list>
</ul>

<script>
$(document).ready(function(){
	//跑马灯
	$("#focus").imgPlay();
});
</script>