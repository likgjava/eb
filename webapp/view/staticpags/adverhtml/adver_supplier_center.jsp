<%@ page pageEncoding="UTF-8"%>
<style>
	.imgPlayUl{
		width: 672px;
		height:230px;
	}
	.imgPlayUl img{
		width: 672px;
		height:230px;
	}
	.imgPlayUl li{
		width: 672px;
		height:230px;
	}
	.playerNav{ background:none repeat scroll 0 0 ;}
</style>


<ul id="focus" class="imgPlayUl">
		<li>
			<a href="http://www.lontime.net/" target="_blank"><img src="AttachmentController.do?method=showImg&objId=ff8080813218571c013219e145840196" /><span></span></a>
		</li>
		<li>
			<a href="http://www.军魂酒业.cn/Product/ProductList.aspx" target="_blank"><img src="AttachmentController.do?method=showImg&objId=ff8080813218571c013219e3179e0198" /><span></span></a>
		</li>
</ul>

<script>
$(document).ready(function(){
	//跑马灯
	$("#focus").imgPlay();
});
</script>