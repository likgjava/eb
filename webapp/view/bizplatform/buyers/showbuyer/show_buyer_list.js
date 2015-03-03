/*
 * 小额交易平台.采购人展示列表
 * author: liangxj
 * mail: liangxj@gpcsoft.com
 */
//定义文件全局变量 处理方法名重复问题
var show_list={};

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
	
	//入库时间排序
	if($("#validSort").hasClass("siUp")) {
		show_list.search += "&validSort=" + "asc";
	}else if($("#validSort").hasClass("siDown")) {
		show_list.search += "&validSort=" + "desc";
	}
	
	//交易金额排序
	if($("#dealMoneySort").hasClass("siUp")) {
		show_list.search += "&dealMoneySort=" + "asc";
	}else if($("#dealMoneySort").hasClass("siDown")) {
		show_list.search += "&dealMoneySort=" + "desc";
	}
	
	//采购人类型
	show_list.typeId = "";
	if($('#propDisp1 a.strong').attr("name") != "-1") {
		$.each($('#propDisp1 a.strong'),function(i,n){
			show_list.typeId += $(n).attr("name") + ",";
		})
	}
	if(show_list.typeId.length > 0) {
		show_list.search += "&unitType=" + show_list.typeId.substring(0,show_list.typeId.length-1);
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
	
	show_list.search += "&" + native2ascii(show_list.getMoreFilter());

	$("#showBuyerListAndPic").empty().loadPage($('#initPath').val()+"/BuyerShowController.do?method=getBuyerForList"+show_list.search,function(){
		$("#totalAmount").html($("#totalRowNum").val());
	});
}

//点击区域过滤
show_list.changeDistrict=function(districtId){
	$("#propDisp2 a").removeClass("strong");
	$("#propDisp2 a[name="+districtId+"]").addClass("strong");
	$("#page").val(1);
	show_list.makeSearchData('firstPage'); 
}


//获得更多过滤条件,将json格式的数据转换成url传参
show_list.getMoreFilter=function(){
	var filter = formToJsonObject("goodsFilter");
	
	//获得最近交易时间
	var ltime = "";
	$("input[name=dealTime]:checked").each(function(i,n){
		ltime += $(n).val() + ",";
	})
	filter.dealTime = ltime;
	
	filter = obj2str(filter);
	var temp = filter.replace(/\",/g,"&");
	temp = temp.replace(/:\"/g,"=");
	temp = temp.replace(/{/g,"");
	temp = temp.replace(/\"}/g,"");
	
	return strIgnore(temp);
}

//加入我的客户
show_list.addClient = function(groupType,orgInfoId,orgInfoName){
	$.epsDialog({
		title:"关注采购人",
		width:500,
		height:300,
		url:$('#initPath').val()+"/ConcernController.do?method=toAddConcernForm&groupType="+groupType+"&orgInfoId="+orgInfoId,
		afterLoad: function(){$('#orgInfoName').html(orgInfoName) }
	});
}

$(document).ready(function(){
	//布局页面
	fnRemoveOtherMain(); 
	changeTabsCss("goToBuyer");//选中顶部菜单
	
	//选中查询下拉框
	keyWordTypeChange('2');
	
	//成交金额，控制不能输入非数字
	$("#moneyTotleLeft").inputFillter({type:'float'});
	$("#moneyTotleRight").inputFillter({type:'float'});
	
	//点击性质分类过滤
	$('#propDisp1 a').click(function(){
		$("#propDisp1 a").removeClass("strong");
		$("#propDisp2 a").removeClass("strong");
		$(this).addClass("strong");
		show_list.makeSearchData('firstPage');  //重新加载采购人
		
		//获取区域信息
		$.getJSON($('#initPath').val()+'/BuyerShowController.do?method=getDistrictListShowByUnitType'+show_list.search,{},function(json){
			$("#propDisp2").empty().append('<li><a name="-1" href="javascript:void(0);" onclick="show_list.changeDistrict(\'-1\');return false;">全部地区</a></li>');
			$.each(json.result,function(i,n){
				$("#propDisp2").append('<li><a name="' + n[0] + '" href="javascript:void(0);" onclick="show_list.changeDistrict(\''+ n[0]+'\');return false;">' + n[1] + '</a><i>(' + n[2] + ')</i></li>');
			})
		});
		
		//刷新推荐采购人
		$("#reconmendDiv").loadPage($('#initPath').val()+'/BuyerShowController.do?method=getRecommendBuyer&rp=5&page=1&unitType=' + show_list.typeId.substring(0,show_list.typeId.length-1));
	});
	
	//评价、时间排序
	$('.buyerSort').click(function(){
		common.order(this);
		show_list.makeSearchData();
	});
});