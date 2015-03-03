var show_list={};
//显示采购预告详情
show_list.showDetail = function(objId) {
	var targetUrl = $('#initPath').val()+"/BulletinShowController.do?method=toShowBulletinView::objId="+objId;
	var contentSuppUrl = $('#initPath').val()+'/BulletinShowController.do?method=getRecommendProject::rp=9::page=1';

	window.open( $('#initPath').val()+"/IndexViewController.do?method=index&contentMainUrl="+targetUrl +"&contentSuppUrl="+contentSuppUrl);
}

//构造搜索条件
show_list.makeSearchData=function(restart){	
	show_list.search = "";	
	//分页信息
	if($('#rp').val()==null){
		show_list.search += "&rp=20";
	}else{
		show_list.search += "&rp="+$('#rp').val();
	}
	show_list.search += "&page="+$('#page').val();

	//公告创建时间排序
	if($("#createTimeSort").hasClass("siUp")) {
		show_list.search += "&createTimeSort=" + "asc";
	}else if($("#createTimeSort").hasClass("siDown")) {
		show_list.search += "&createTimeSort=" + "desc";
	}
	
show_list.search += "&" + native2ascii(show_list.getMoreFilter());
	
	var url = $('#initPath').val()+"/BulletinShowController.do?method=getBulletinBuyPreForList";
	//重新设置开始页
	if(restart!=null && restart=="restart"){
		url += "&page=1";
	}
	$("#showSuppListAndPic").empty().loadPage(url + show_list.search, function(){
		$("#totalAmount").html($("#totalRowNum").val());
	});
	
}

//获得过滤条件,将json格式的数据转换成url传参
show_list.getMoreFilter=function(){
	var filter = obj2str(formToJsonObject("bulletinFilter"));
	var temp = filter.replace(/\",/g,"&");
	temp = temp.replace(/:\"/g,"=");
	temp = temp.replace(/{/g,"");
	temp = temp.replace(/\"}/g,"");
	return strIgnore(temp);
}

var pageDirection = {};
//跳转
pageDirection.jump = function(page){	
	$("#page").val(page);
	show_list.makeSearchData();
}

$(document).ready(function(){
	//调整页面布局 
	fnRemoveOtherMain(); 
	changeTabsCss("goToBulltin");//选中顶部菜单
	
	//选中查询下拉框
	keyWordTypeChange('1');


	//点击排序
	$('.supplierSort').click(function(){
		common.order(this);
		show_list.makeSearchData('restart');
	});	
});