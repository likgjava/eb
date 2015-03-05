<%@ page pageEncoding="UTF-8"%>
<style>
	.imgPlayUl{
		width: 548px;
		height:145px;
	}
	.imgPlayUl img{
		width: 548px;
		height:145px;
	}
	.imgPlayUl li{
		width: 548px;
		height:145px;
	}
	.playerNav{ background:none repeat scroll 0 0 ;}
</style>


<ul id="focus" class="imgPlayUl">
		<li>
			<a href="http://www.ebid360.com/OrgShopController.do?method=toOrgShopIndexView&orgInfoId=ff8080812f44ac00012f47d908d603c5" target="_blank"><img src="AttachmentController.do?method=showImg&objId=ff8080813049102001304dfdffd20ad8" /><span></span></a>
		</li>
		<li>
			<a href="http://www.lontime.net/" target="_blank"><img src="AttachmentController.do?method=showImg&objId=ff808081304e848f01304ed301c0000d" /><span></span></a>
		</li>
		<li>
			<a href="http://www.paihang360.com/zt/jiusc2011/jiusheng.jsp" target="_blank"><img src="AttachmentController.do?method=showImg&objId=ff80808130ab37e40130afd0d14c06c2" /><span></span></a>
		</li>
		<li>
			<a href="http://www.dcgou.com/static4/page_dcgou/jfhl/index.html" target="_blank"><img src="AttachmentController.do?method=showImg&objId=ff8080813140dc47013145236fb30342" /><span></span></a>
		</li>
		<li>
			<a href="http://www.togo815.com/" target="_blank"><img src="AttachmentController.do?method=showImg&objId=ff8080813140dc470131456f814603dc" /><span></span></a>
		</li>
</ul>

<script>
$(document).ready(function(){
	//跑马灯
	$("#focus").imgPlay();
});
</script>