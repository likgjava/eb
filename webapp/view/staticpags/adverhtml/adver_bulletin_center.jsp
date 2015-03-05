<%@ page pageEncoding="UTF-8"%>
<style>
	.imgPlayDiv{
		width: 500px;
		height:240px;
	}
	.imgPlayDiv img{
		width: 500px;
		height:240px;
	}
	.imgPlayDiv li{
		width: 500px;
		height:240px;
	}
</style>
<div id="focusDiv">
	<ul id="focusUl">
			<li>
				<a href="http://www.lzlj.com.cn/" title="" target="_blank"><img src="AttachmentController.do?method=showImg&objId=ff808081309200df01309c23db930aeb" /><span></span></a>
			</li>
			<li>
				<a href="http://www.ebid360.com/xygh/OrgShopController.do?method=toOrgShopIndexView&orgInfoId=ff8080812f04f7fd012f105202f617af" title="" target="_blank"><img src="AttachmentController.do?method=showImg&objId=ff808081309200df01309c272dd80b1e" /><span></span></a>
			</li>
	</ul>
</div>
<script>
$(document).ready(function(){
	//跑马灯
	$("#focusDiv").imgPlay({cssClass:"imgPlayDiv"});
});
</script>