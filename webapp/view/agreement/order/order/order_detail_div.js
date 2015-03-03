/*
 * 协议供货.订单详情弹出层页面
 * author: liangx
 * mail: liangxj@gpcsoft.com
 */
//定义文件全局变量 处理方法名重复问题
var orderDetail={};
orderDetail.oTable;	


//显示商品详情
orderDetail.showGoodsDetail = function(goodsId){
	$.epsDialog({
		id:"showGoodsDetail",
		title:"商品详情",
		url:$("#initPath").val()+"/GoodsShowController.do?method=getGoodsInfo&viewName=goodsDetailView&viewFrom=bargain&fromDiv=yes&dailogId=showGoodsDetail&isShowPic=true&objId="+goodsId
	})
}

//显示或隐藏商品礼包
orderDetail.showOrHideGoodsGift = function(obj){
	$(obj).parent().parent().next().toggle();
}
//显示或隐藏商品零配件
orderDetail.showOrHideGoodsAccess = function(obj){
	$(obj).parent().parent().next().toggle();
}

$(document).ready(function(){
	
	//关闭
	$("#orderDivclose").click(function(){
		if($("#_dialogID").val() != "")
			$(document.getElementById($("#_dialogID").val())).find('.epsDialogClose').trigger('click');
		else
			$('.epsDialogClose').trigger('click');
	})
});
	
