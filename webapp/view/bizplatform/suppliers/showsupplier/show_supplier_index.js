/*
 * 小额交易平台.供应商展示首页列表
 */
var ShowSupplierIndex = {};

//按照品目查询供应商
ShowSupplierIndex.toSupplierList = function(categoryCode) {
	//$('#sysContent').loadPage();
	window.open( $('#initPath').val()+'/SupplierShowController.do?method=toSupplierList&rp=21&page=1&districtLevel=1&categoryCode='+categoryCode )
}

$(document).ready(function(){
	//供应商库选中样式
	changeTabsCss('goToSupplier');
	
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