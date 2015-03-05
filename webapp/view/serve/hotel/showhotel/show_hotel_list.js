/*
 * 小额交易平台.酒店展示列表
 * author: liangxj
 * mail: liangxj@gpcsoft.com
 */
//定义文件全局变量 处理方法名重复问题
var show_list={};

//显示酒店详情
show_list.showDetail = function(hotelId,tabId) {
	var params = "";
	if(tabId) {
		params += "::tabId=" + tabId;
	}
	var districtName =  $("#propDisp2").find("a[class=strong]").html();
	if(districtName!=null && districtName!=""){
		districtName = native2ascii(districtName);
	}
	if(null!=districtName){
		var districtId =  $("#propDisp2").find("a[class=strong]").attr('name');
		params += "::districtId="+districtId+"::districtName="+districtName;
	}
	
	var targetUrl = $('#initPath').val()+"/HotelShowController.do?method=getHotelInfo::objId=" + hotelId + params;
	var contentSuppUrl = $('#initPath').val()+'/HotelShowController.do?method=getRecommendHotel::rp=10::page=1::'+"::keyWord="+strIgnore($("#keyWord").val());
	window.open( $('#initPath').val()+"/IndexViewController.do?method=index&contentMainUrl="+targetUrl+"&contentSuppUrl="+contentSuppUrl );
}

//构造搜索条件
show_list.makeSearchData=function(firstPage){
	show_list.search = "";
	
	//分页信息
	if(($('#rp') && $('#rp').val()==null) || firstPage!=null){
		show_list.search += "&rp=20";
		show_list.search += "&page=1";
	}else{
		show_list.search += "&rp="+$('#rp').val();
		show_list.search += "&page="+$('#page').val();
	}
	
	//评价排序
	if($("#evalSort").hasClass("siUp")) {
		show_list.search += "&evalSort=" + "asc";
	}else if($("#evalSort").hasClass("siDown")) {
		show_list.search += "&evalSort=" + "desc";
	}
	
	//星级排序
	if($("#starSort").hasClass("siUp")) {
		show_list.search += "&starSort=" + "asc";
	}else if($("#starSort").hasClass("siDown")) {
		show_list.search += "&starSort=" + "desc";
	}
	
	//关键字
	if($("#keyWord").val() != "") {
		show_list.search += "&keyWord=" + native2ascii(strIgnore($("#keyWord").val()));
	}
	
	//酒店星级
	show_list.typeId = "";
	if($('#propDisp1 a.strong').attr("name") != "-1") {
		$.each($('#propDisp1 a.strong'),function(i,n){
			show_list.typeId += $(n).attr("name") + ",";
		})
	}
	if(show_list.typeId.length > 0) {
		show_list.search += "&starLevel=" + show_list.typeId.substring(0,show_list.typeId.length-1);
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

	//其他过滤条件
	show_list.search += "&" + native2ascii(show_list.getMoreFilter());

	$("#showGoodsListAndPic").empty().loadPage($('#initPath').val()+"/HotelShowController.do?method=getHotelForListAndPic"+show_list.search,function(){
		$("#totalAmount").html($("#totalRowNum").val());
		show_list.search = null;//内存泄露问题 by yucy
	});
}

//获得更多过滤条件,将json格式的数据转换成url传参
show_list.getMoreFilter=function(){
	var filter = formToJsonObject("goodsFilter");
	
	//获得客房类型
	var grtype = "";
	$("input[name=guestRoomType_box]:checked").each(function(i,n){
		grtype += $(n).val() + ",";
	})
	filter.guestRoomType = grtype;
	
	//获得娱乐设施
	var fun = "";
	$("input[name=funFacilities_box]:checked").each(function(i,n){
		fun += $(n).val() + ",";
	})
	filter.funFacilities = fun;
	
	//获得服务项目
	var seritem = "";
	$("input[name=serviceItems_box]:checked").each(function(i,n){
		seritem += $(n).val() + ",";
	})
	filter.serviceItems = seritem;
	
	//获得接受信用卡类型
	var credit = "";
	$("input[name=creditCardType_box]:checked").each(function(i,n){
		credit += $(n).val() + ",";
	})
	filter.creditCardType = credit;
	
	//会议室信息
	var mrtype = "";
	if($("#meetingRoomType_sel").val() != ""){
		mrtype += $("#meetingRoomType_sel").val();
	}
	if($("#meetingRoomNum_sel").val() != ""){
		mrtype += "-" + $("#meetingRoomNum_sel").val();
	}
	filter.meetingRoomType = mrtype;
	
	filter = obj2str(filter);
	var temp = filter.replace(/\",/g,"&");
	temp = temp.replace(/:\"/g,"=");
	temp = temp.replace(/{/g,"");
	temp = temp.replace(/\"}/g,"");
	return temp;
}

//点击区域过滤
show_list.changeDistrict=function(districtId){
	$("#propDisp2 a").removeClass("strong");
	$("#propDisp2 a[name="+districtId+"]").addClass("strong");
	
	$('#page').val('1');//点击不同的商品分类后重置分页值
	
	show_list.makeSearchData(); 
}

$(document).ready(function(){
	fnRemoveOtherMain(); 
	changeTabsCss("goToHotel");
	
	//选中查询下拉框
	keyWordTypeChange('6');
	
	//扩展搜索
	$("#startDate").epsDatepicker();
	$("#endDate").epsDatepicker();

	//推荐酒店
	$("#reconmendDiv").loadPage($('#initPath').val()+'/HotelShowController.do?method=getRecommendHotel&rp=10&page=1');
	
	//点击分类过滤
	$('#propDisp1 a').click(function(){
		
		$("#propDisp1 a").removeClass("strong");
		$("#propDisp2 a").removeClass("strong");
		$(this).addClass("strong");
		
		$('#page').val('1');//点击不同的商品分类后重置分页值
		
		show_list.makeSearchData();  //重新加载酒店
		
		//获取区域信息
		$.getJSON($('#initPath').val()+'/HotelShowController.do?method=getDistrictListShowByStar'+show_list.search,{},function(json){
			if(json.result.length < 17) {
				$("#expandProp2").parent().remove();
			}else {
				$("#moreProp2").next("div").empty().append('<a href="javascript:void(0);" onclick="common.showOrHiddenMore(\'expandProp2\',\'moreProp2\',\'区域\');return false;"><span class="sysicon siDownGray" id="expandProp2">显示全部区域</span></a>');
			}
			
			$("#propDisp2").empty().append('<li><a name="-1" href="javascript:void(0);" onclick="show_list.changeDistrict(\'-1\');return false;">全部地区</a></li>');
			$.each(json.result,function(i,n){
				$("#propDisp2").append('<li><a href="javascript:void(0);" onclick="show_list.changeDistrict(\'' + n[0] + '\');return false;" name="' + n[0] + '">' + n[1] + '</a><i>(' + n[2] + ')</i></li>');
			})
			
		});
		
		//刷新推荐酒店
		$("#reconmendDiv").loadPage($('#initPath').val()+'/HotelShowController.do?method=getRecommendHotel&rp=5&page=1&star='+show_list.typeId.substring(0,show_list.typeId.length-1));
	});

	//评价、星级排序
	$('.goodsSort').click(function(){
		common.order(this);
		show_list.makeSearchData();
	});
});