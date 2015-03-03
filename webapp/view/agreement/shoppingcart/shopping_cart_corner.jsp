<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript">

//刷新购物车信息
function getCartInfo(){
	//加载购物车信息
	$.getJSON($('#initPath').val()+'/ShoppingCartController.do?method=findMyShoppingCar',{},function(json){
		if(json.failure){alert(json.result);return;}
		var html='<li><table class="tableList2">';
		if(json.rows.length==0){
			$('#shoppingCartGoodsTotalMoney').html('0');
			$('span[name=shoppingCartGoodsTotal]').html('0');
		}
		$.each(json.rows,function(i,n){
			$('#shoppingCartGoodsTotalMoney').html(formatAmount(json.rows[0].shoppingCart.goodsTotal));
			$('span[name=shoppingCartGoodsTotal]').html(formatAmount(n.shoppingCart.goodsQty));
		});
	});
	$("#cartInfo").show();
}
$(document).ready(function(){
	//判断登陆
	if(common.isLogin(false)){
		getCartInfo();
	}
});
</script>

<div class="workNews hotProduct hidden" id="cartInfo">
<!-- 购物车开始 -->
<h4><span class="jiantou">我的购物车</span></h4>
<ul class="center" id="myShoppingCart">
	<li><h6>购物车有 <strong><span name="shoppingCartGoodsTotal">0</span></strong> 件商品</h6></li>
	<li class="center">共<strong><span name="shoppingCartGoodsTotal">0</span></strong>件商品&nbsp;&nbsp;共<strong>￥<span id="shoppingCartGoodsTotalMoney">0</span></strong>元</li>
	<li ><button type="button" onclick="index.goinToShopCart()" class="toCart"><span>查看购物车</span></button></li>
</ul>
<!-- 购物车结束-->
</div>
