/*商品展示首页列表*/
var ShowGoodsIndex = {};

//查看所有商品分类
ShowGoodsIndex.changeCategory=function(){
	window.open( $('#initPath').val()+"/IndexViewController.do?method=getGoodsClassForSortHasGoods" );
	return false;
}

//点击某一个分类
ShowGoodsIndex.searchByGoodsClass=function(goodsClassCode){
	//window.open( $('#initPath').val()+'/GoodsListInfoShow/keyWord_/ClassCode_'+goodsClassCode+'/searchType_' );
	window.open( $('#initPath').val()+'/GoodsShowController.do?method=toGoodsList&rp=21&page=1&goodsClassCode='+goodsClassCode );
	return false;
}

//显示商品详情
ShowGoodsIndex.showGoodsDetail = function(goodsId ,classcode) {
	common.geToGoodsDetail( goodsId );
}

$(document).ready(function(){
	//商品库选中样式
	changeTabsCss('goToGoods');
	
	//按商品分类展示商品
	$("#goodsListByClass").loadPage($('#initPath').val()+'/GoodsShowController.do?method=toGoodsListByClassView');
	
	//如果使用静态页面且是已登录用户，则显示登录信息
	if(common.useStaticIndexPage){
		$.getJSON($('#initPath').val()+"/IndexViewController.do?method=getCurrentUser", function(userjson){
			if(userjson.failure){if(userjson.result)alert(userjson.result);return;}
			if(userjson.isLogin){
				//修改头部信息
				common.getCurrUser(userjson);
			}
		});
	}
});