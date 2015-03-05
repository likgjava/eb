var ShowGroupBuyingIndex = {};

//跳转到下订单页面
ShowGroupBuyingIndex.toGroupBuyerFormView = function(groupBuyingId){
	var url = $('#initPath').val()+"/GroupBuyerController.do?method=toCreateOrUpdateView&groupBuyingId="+groupBuyingId;
	window.open(url);
}

$(document).ready(function(){
	//选中顶部菜单
	changeTabsCss("goToGoodsMall");
});
