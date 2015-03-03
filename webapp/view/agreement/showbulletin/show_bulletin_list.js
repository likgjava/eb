/*
 * 小额交易平台.采购项目展示列表
 * author: likg
 */

//定义文件全局变量 处理方法名重复问题
var show_list={};

//定时刷新时间
show_list.flushRemainEvalEndTime=function(){
	$("#remainTime").html(getRemainTime($("#remainTime").attr("name")));
	$("#remainTime").attr("name", (parseInt($("#remainTime").attr("name"))-1000));
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
	
	//采购金额排序
	if($("#budgetMoneySort").hasClass("siUp")) {
		show_list.search += "&budgetMoneySort=" + "asc";
	}else if($("#budgetMoneySort").hasClass("siDown")) {
		show_list.search += "&budgetMoneySort=" + "desc";
	}
	
	//公告创建时间排序
	if($("#createTimeSort").hasClass("siUp")) {
		show_list.search += "&createTimeSort=" + "asc";
	}else if($("#createTimeSort").hasClass("siDown")) {
		show_list.search += "&createTimeSort=" + "desc";
	}
	
	//采购金额区间
	show_list.intervalBudgetMoney = "";
	if($('#propDisp1 a.strong').attr("name")) {
		show_list.intervalBudgetMoney = $('#propDisp1 a.strong').attr("name");
		show_list.search += "&intervalBudgetMoney=" + show_list.intervalBudgetMoney;
	}else {
		show_list.search += "&intervalBudgetMoney=";
	}
	
	//区域
	show_list.districtId = "";
	if($('#propDisp2 a.strong').attr("name") != "-1") {
		$.each($('#propDisp2 a.strong'),function(i,n){
			show_list.districtId += $(n).attr("name") + ",";
		})
	}
	if(show_list.districtId.length > 0) {
		show_list.search += "&districtId=" + show_list.districtId.substring(0,show_list.districtId.length-1);
	}else {
		show_list.search += "&districtId=" + $("#districtId").val();
	}

	//区域的级别
	if($("#districtLevel").val() != null) {
		show_list.search += "&districtLevel=" + $("#districtLevel").val();
	}
	
	//采购品目
	var purCategoryId = "";
	if($('#propDisp3 a.strong').attr("name") != null && $('#propDisp3 a.strong').attr("name") != "-1") {
		purCategoryId = $('#propDisp3 a.strong').attr("name");
	}
	if($("#purCategoryId").val()!=null && $("#purCategoryId").val()!="" && purCategoryId=="") {
		purCategoryId = $("#purCategoryId").val();
	}
	if(purCategoryId.length > 0) {
		show_list.search += "&purCategoryId=" + purCategoryId;
	}
	
	show_list.search += "&" + native2ascii(show_list.getMoreFilter());

	var url = $('#initPath').val()+"/BulletinShowController.do?method=getBulletinForList";
	//重新设置开始页
	if(restart!=null && restart=="restart"){
		url += "&page=1";
	}
	$("#showSuppListAndPic").empty().loadPage(url + show_list.search, function(){
		$("#totalAmount").html($("#totalRowNum").val());
	});
}

//点击区域过滤
show_list.changeDistrict=function(districtId){
	$("#propDisp2 a").removeClass("strong");
	$("#propDisp3 a").removeClass("strong");
	$("#propDisp2 a[name="+districtId+"]").addClass("strong");
	
	$('#page').val('1');//点击不同的商品分类后重置分页值
	
	show_list.makeSearchData('restart');  
	
	//获取采购品目信息
	$.getJSON($('#initPath').val()+'/BulletinShowController.do?method=getGoodsClassListShowByIntervalBudgetMoneyAndDistrict'+show_list.search, {}, function(json){
		$("#propDisp3").empty().append('<li><a name="-1" href="javascript:void(0);" onclick="show_list.changeGoodsClass(\'-1\');return false;">全部分类</a></li>');
		$.each(json.result,function(i,n){
			$("#propDisp3").append('<li><a name="' + n[0] + '" href="javascript:void(0);" onclick="show_list.changeGoodsClass(\''+ n[0]+'\');return false;">' + n[1] + '</a><i>(' + n[2] + ')</i></li>');
		});
	});
}
//点击商品类别过滤
show_list.changeGoodsClass=function(purCategoryId){
	$("#propDisp3 a").removeClass("strong");
	$("#propDisp3 a[name="+purCategoryId+"]").addClass("strong");
	
	$('#page').val('1');//点击不同的商品分类后重置分页值
	
	show_list.makeSearchData('restart');  
}

// 获得更多过滤条件,将json格式的数据转换成url传参
show_list.getMoreFilter=function(){
	var filter = obj2str(formToJsonObject("bulletinFilter"));
	var temp = filter.replace(/\",/g,"&");
	temp = temp.replace(/:\"/g,"=");
	temp = temp.replace(/{/g,"");
	temp = temp.replace(/\"}/g,"");
	return strIgnore(temp);
}

//重新加载列表
show_list.reloadList = function(){
	$("#sysContent").loadPage($('#initPath').val()+"/BulletinShowController.do?method=toBulletinList&rp=21&page=1&districtLevel=1");
}

$(document).ready(function(){
	//调整页面布局 
	fnRemoveOtherMain(); 
	
	//点击不同的采购金额区间过滤
	$('#propDisp1 a').click(function(){
		$("#propDisp1 a").removeClass("strong");
		$("#propDisp2 a").removeClass("strong");
		$("#propDisp3 a").removeClass("strong");
		$(this).addClass("strong");
		
		$('#page').val('1');//点击不同的商品分类后重置分页值
		
		show_list.makeSearchData('restart');  // 重新加载采购项目
		
		//获取区域信息
		$.getJSON($('#initPath').val()+'/BulletinShowController.do?method=getDistrictListShowByIntervalBudgetMoney'+show_list.search, {}, function(json){
			$("#propDisp2").empty().append('<li><a name="-1" href="javascript:void(0);" onclick="show_list.changeDistrict(\'-1\');return false;">全部地区</a></li>');
			$.each(json.result,function(i,n){
				$("#propDisp2").append('<li><a name="' + n[0] + '" href="javascript:void(0);" onclick="show_list.changeDistrict(\''+ n[0]+'\');return false;">' + n[1] + '</a><i>(' + n[2] + ')</i></li>');
			});
		});
		
		//获取采购品目信息
		$.getJSON($('#initPath').val()+'/BulletinShowController.do?method=getGoodsClassListShowByIntervalBudgetMoneyAndDistrict'+show_list.search, {}, function(json){
			$("#propDisp3").empty().append('<li><a name="-1" href="javascript:void(0);" onclick="show_list.changeGoodsClass(\'-1\');return false;">全部分类</a></li>');
			$.each(json.result,function(i,n){
				$("#propDisp3").append('<li><a name="' + n[0] + '" href="javascript:void(0);" onclick="show_list.changeGoodsClass(\''+ n[0]+'\');return false;">' + n[1] + '</a><i>(' + n[2] + ')</i></li>');
			});
		});
		
		//刷新推荐采购项目
		$("#reconmendDiv").loadPage($('#initPath').val()+'/BulletinShowController.do?method=getRecommendProject&rp=5&page=1&viewName=bulletinRecommendView&intervalBudgetMoney='+show_list.intervalBudgetMoney);
	});

	//点击排序
	$('.supplierSort').click(function(){
		common.order(this);
		show_list.makeSearchData('restart');
	});
	
	//推荐采购项目
	$("#reconmendDiv").loadPage($('#initPath').val()+'/BulletinShowController.do?method=getRecommendProject&rp=5&page=1');
});