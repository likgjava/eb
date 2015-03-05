/**
 * 礼品列表js
 */
var gift_list={};
var show_list = {};

//跳至礼品主页面
gift_list.toGiftIndex=function(){
	$("#sysContent").loadPage($('#initPath').val()+"/GiftShowController.do?method=toGiftIndex");
}

//跳至详情页面
gift_list.showDetail = function(giftId,tabId) {
	var params = "";
	if(tabId) {
		params += "::tabId=" + tabId;
	}
	var targetUrl = $('#initPath').val()+"/GiftShowController.do?method=getGiftInfo::objId=" + giftId+"::giftSeries="+$("#giftSeriesId").val() + params;
	var contentSubUrl = $('#initPath').val()+'/GiftShowController.do?method=getRecommendGift';
	window.open( $('#initPath').val()+"/IndexViewController.do?method=index&contentMainUrl="+targetUrl+"&contentSubUrl="+contentSubUrl );
}

//跳至列表页面
showList = function(seriesId,keyWord,type) {
	var sysContentUrl = $('#initPath').val()+'/GiftShowController.do?method=toGiftList::rp=21::page=1::seriesId='+seriesId+'::giftType='+type;
	window.open( $('#initPath').val()+"/IndexViewController.do?method=index&sysContentUrl="+sysContentUrl);
}

//构造搜索条件
show_list.makeSearchData=function(seriesId){
	gift_list.search = "";
//	//系列
//	if(seriesId){
//		gift_list.search += "&seriesId="+seriesId;
//	}
	if($("#series").find("span[class=weightfont]").html()){
		gift_list.search += "&seriesId="+$("#series").find("span[class=weightfont]").attr("id");
	}
	
	//分页信息
	if($('#rp') && $('#rp').val()==null){
		gift_list.search += "&rp=20";
		gift_list.search += "&page=1";
	}else{
		gift_list.search += "&rp="+$('#rp').val();
		gift_list.search += "&page="+$('#page').val();
	}
	
	//展示方式
	gift_list.search += "&style=" + $("#style").val();
	
	//排序
	var sort = ""; 
	$.each($("span.goodsSort"),function(i,n){
		//获得升序属性
		if($(n).hasClass("siUp")) {
			sort += $(n).attr("name") + "_asc,"; 
		}
		else if($(n).hasClass("siDown")) {
			sort += $(n).attr("name") + "_desc,"; 
		}
	})
	if(sort.length > 0) {
		gift_list.search += "&order=" + sort.substring(0,sort.length-1);
	}
	
	//关键字
	if($("#keyWord").val() != "") {
		gift_list.search += "&keyWord=" + native2ascii(strIgnore($("#keyWord").val()));
	}
	
	//礼品类型
	if($("input[name=giftType]:checked").html()!=null){
		gift_list.search += "&giftType=" + $("input[name=giftType]:checked").val();
	}
	
	$("#showGiftListAndPic").empty().loadPage($('#initPath').val()+"/GiftShowController.do?method=getGiftForListAndPic"+gift_list.search,function(){
		$("#totalAmount").html($("#totalRowNum").val());
	});
}

$(document).ready(function(){
	//调整页面布局 
	fnHiddenSuppNoRemove()
	changeTabsCss("goToGift"); //选中顶部菜单
	
	$("#contentSub").removeClass().addClass("contentSub").addClass("PointsMallLeft");
	$("#contentMain").removeClass().addClass("contentMain").addClass("index2paRR");//样式调整 
	
	//选中查询下拉框
	keyWordTypeChange('7');
	//如果没有关键字加载礼品主页
	if(!$("#keyWord").val()&&!$("#seriesId").val()&&!$("input[name=giftType]:checked").val()){
		gift_list.toGiftIndex();
		return false;
	}
	
	//列表显示
	$('#showGoodsList').click(function(){
		$("#style").val("list");
		show_list.makeSearchData();
	});

	//大图显示
	$('#showGoodsPic').click(function(){
		$("#style").val("pic");
		show_list.makeSearchData();
	});
	
	//排序
	$('.goodsSort').click(function(){
		common.order(this);
		show_list.makeSearchData();
	});
	
	//加载系列栏
	$("#contentSub").loadPage($("#initPath").val()+"/GiftShowController.do?method=getGiftSeriesList&seriesId="+$("#seriesId").val());
	
});