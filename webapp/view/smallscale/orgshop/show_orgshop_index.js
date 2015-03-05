/*
 * 小额交易平台.企业商铺
 */
var OrgShopIndex = {};
var show_list = {};
OrgShopIndex.orgInfoId = null;

//查看企业详细信息视图
OrgShopIndex.showView = function(obj,viewName){
	//修改当前位置
	$("#currentLocation").html($(obj).html());
	
	if(viewName==null || viewName==""){
		//window.location.href = $('#initPath').val()+"/OrgShopController.do?method=toOrgShopIndexView&orgInfoId="+OrgShopIndex.orgInfoId;
		window.location.reload(); //解决IE6使用window.location.href页面不刷新的问题
	}else{
		$("#boxRightContent").loadPage($('#initPath').val()+"/OrgShopController.do?method=toOrgShopOtherView&rp=20&page=1&viewName="+viewName+"&orgInfoId="+OrgShopIndex.orgInfoId);
	}
}

//显示商品详情
OrgShopIndex.showGoodsDetail = function(goodsId) {
	common.geToGoodsDetail( goodsId );
}

//根据商品分类查看商品信息
OrgShopIndex.showGoodsInfoView = function(goodsClassCode){
	$("#boxRightContent").loadPage($('#initPath').val()+"/OrgShopController.do?method=toOrgShopOtherView&rp=20&page=1&viewName=orgshopGoodsInfoView&orgInfoId="+OrgShopIndex.orgInfoId+"&goodsClassCode="+goodsClassCode);
}

//为当前商铺的供应商留言
OrgShopIndex.addNote = function(supplierId){
	if(common.isLogin(true,"请先登录再进行留言！")){ //判断是否登录
		$.epsDialog({
			title:'我要留言',
			id:'noteFormDialog',
			url:$("#initPath").val()+"/NoteController.do?method=toNoteForm&type=00&receive=" + supplierId
		})
	}
}

//加入我的客户
show_list.addClient = function(groupType,orgInfoId,orgInfoName){
	$.epsDialog({
		title:"关注客户",
		width:500,
		height:300,
		url:$('#initPath').val()+"/ConcernController.do?method=toAddConcernForm&groupType="+groupType+"&orgInfoId="+orgInfoId,
		afterLoad: function(){$('#orgInfoName').html(orgInfoName) }
	});
}

//构造搜索条件
show_list.makeSearchData = function(){
	show_list.search = "";
	
	//分页信息
	if($('#rp') && $('#rp').val()==null){
		show_list.search += "&rp=12";
		show_list.search += "&page=1";
	}else{
		show_list.search += "&rp="+$('#rp').val();
		show_list.search += "&page="+$('#page').val();
	}

	//当前视图页面
	var currentViewName = $("#currentViewName").val(); 
	show_list.search += "&viewName=" + currentViewName;

	//当前商铺所属机构的id
	show_list.search += "&orgInfoId=" + OrgShopIndex.orgInfoId;
	
	if(currentViewName == "orgshopGoodsInfoView"){
		//商品分类code
		show_list.search += "&goodsClassCode=" + $("#goodsClassCode").val();
	}
	
	if($("#complainType").val()){
		//投诉or举报
		show_list.search +="&complainType="+$("#complainType").val();
	}
	
	$("#boxRightContent").empty().loadPage($('#initPath').val()+"/OrgShopController.do?method=toOrgShopOtherView"+show_list.search,function(){
		show_list.search = null;
	});
}

$(document).ready(function(){
	
	//当前企业商铺所属机构的id
	OrgShopIndex.orgInfoId = $("#orgInfoId").val();
	
	if($('#currentTab').val() != "0") {
		if($('#currentTab').val() == "3") {
			$('#cgal').click();
		}
	}
	
	//跳转到首页
	$('#toToIndex').click(function(){
		window.open($('#initPath').val()+"/IndexViewController.do?method=index");
		return false;
	});
	
	//跳转到采购需求
	$('#toToBulltin').click(function(){
		window.open($('#initPath').val()+"/BulletinShowController.do?method=toShowBulletinIndexView");
		return false;
	});
	
	//跳转到商品库	
	$('#toToGoods').click(function(){
		window.open($('#initPath').val()+"/GoodsShowController.do?method=toShowGoodsIndexView");
		return false;
	});
});