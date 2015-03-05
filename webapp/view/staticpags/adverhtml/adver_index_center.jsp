<%@ page pageEncoding="UTF-8"%>
<style>
	.imgPlayUl{
		width: 484px;
		height:242px;
	}
	.imgPlayUl img{
		width: 484px;
		height:242px;
	}
	.imgPlayUl li{
		width: 484px;
		height:242px;
	}
	.playerNav{ background:none repeat scroll 0 0 ;}
</style>


<ul id="focus" class="imgPlayUl">
		<li>
			<a href="http://www.ebid360.com/GroupBuyingShowController.do?method=toShowGroupBuyingIndexView" target="_blank"><img src="AttachmentController.do?method=showImg&objId=ff80808132811a910132b30fddd15a50" /><span></span></a>
		</li>
		<li>
			<a href="http://www.ebid360.com/OrgShopController.do?method=toOrgShopIndexView&orgInfoId=ff808081304e848f013053629bba095d" target="_blank"><img src="AttachmentController.do?method=showImg&objId=ff80808132811a910132b316fb525abc" /><span></span></a>
		</li>
		<li>
			<a href="http://www.lzhb.com.cn/" target="_blank"><img src="AttachmentController.do?method=showImg&objId=ff80808132811a910132b31909695ac6" /><span></span></a>
		</li>
		<li>
			<a href="http://www.军魂酒业.cn/Product/ProductList.aspx" target="_blank"><img src="AttachmentController.do?method=showImg&objId=ff80808132811a910132b317e7fe5ac1" /><span></span></a>
		</li>
</ul>

<script>
$(document).ready(function(){
	//跑马灯
	$("#focus").imgPlay();
});
</script>