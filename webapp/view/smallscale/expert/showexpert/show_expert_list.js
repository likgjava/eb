/*
 * 小额交易平台.专家展示列表
 * author: liangxj
 * mail: liangxj@gpcsoft.com
 */

//定义文件全局变量 处理方法名重复问题
var show_list={};

//显示专家详情
show_list.showDetail = function(expertId,tabId) {
	var params = "";
	var categoryName =  $("#propDisp1").find("a[class=strong]").html();
	if(null!=categoryName){
		var categoryId =  $("#propDisp1").find("a[class=strong]").attr("name");
		params += "::categoryId="+categoryId+"::categoryName="+categoryName;
	}
	var districtName =  $("#propDisp2").find("a[class=strong]").html();
	if(null!=districtName){
		var districtId =  $("#propDisp2").find("a[class=strong]").attr('name');
		params += "::districtId="+districtId+"::districtName="+districtName;
	}
	if(tabId) {
		params += "::tabId=" + tabId;
	}
	//$("#contentMain").empty().loadPage($('#initPath').val()+"/ExpertShowController.do?method=getExpertInfo&objId=" + expertId + native2ascii(params));
	common.goToExpertDetail(expertId);
}

//构造搜索条件
show_list.makeSearchData=function(firstPage){
	show_list.search = "";
	//分页信息
	if(($('#rp') && $('#rp').val()==null) || firstPage){
		show_list.search += "&rp=20";
		show_list.search += "&page=1";
	}else{
		show_list.search += "&rp="+$('#rp').val();
		show_list.search += "&page="+$('#page').val();
	}
	
	//从事年限排序
	if($("#specifySort").hasClass("siUp")) {
		show_list.search += "&specifySort=" + "asc";
	}else if($("#specifySort").hasClass("siDown")) {
		show_list.search += "&specifySort=" + "desc";
	}
	
	//入库时间排序
	if($("#validSort").hasClass("siUp")) {
		show_list.search += "&validSort=" + "asc";
	}else if($("#validSort").hasClass("siDown")) {
		show_list.search += "&validSort=" + "desc";
	}
	
	//类别
	show_list.categoryId = "";
	if($('#propDisp1 a.strong').attr("name") != "-1") {
		$.each($('#propDisp1 a.strong'),function(i,n){
			show_list.categoryId += $(n).attr("name") + ",";
		})
	}
	if(show_list.categoryId.length > 0) {
		show_list.search += "&categoryId=" + show_list.categoryId.substring(0,show_list.categoryId.length-1);
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

	$("#showSuppListAndPic").empty().loadPage($('#initPath').val()+"/ExpertShowController.do?method=getExpertForList"+show_list.search,function(){
		$("#totalAmount").html($("#totalRowNum").val());
	});
}

//获得更多过滤条件,将json格式的数据转换成url传参
show_list.getMoreFilter=function(){
	var filter = obj2str(formToJsonObject("goodsFilter"));
	var temp = filter.replace(/\",/g,"&");
	temp = temp.replace(/:\"/g,"=");
	temp = temp.replace(/{/g,"");
	temp = temp.replace(/\"}/g,"");
	return strIgnore(temp);
}

//点击区域过滤
show_list.changeDistrict=function(districtId){
	$("#propDisp2 a").removeClass("strong");
	$("#propDisp2 a[name="+districtId+"]").addClass("strong");
	
	$('#page').val('1');//点击不同的商品分类后重置分页值
	
	show_list.makeSearchData(); 
}

$(document).ready(function(){
	//布局页面
	fnRemoveOtherMain(); 
	changeTabsCss("goToExpert");//选中顶部菜单
	
	//选中查询下拉框
	keyWordTypeChange('5');
	
	//工作年限，控制不能输入非数字
	$("#sspecifyYear").inputFillter({type:'int'});
	$("#especifyYear").inputFillter({type:'int'});
	
	//点击分类过滤
	$('#propDisp1 a').click(function(){
		$("#propDisp1 a").removeClass("strong");
		$("#propDisp2 a").removeClass("strong");
		$(this).addClass("strong");
		
		$('#page').val('1');//点击不同的商品分类后重置分页值
		
		show_list.makeSearchData();  //重新加载专家
		
		//获取区域信息
		$.getJSON($('#initPath').val()+'/ExpertShowController.do?method=getDistrictListShowByCategory'+show_list.search,{},function(json){
			$("#propDisp2").empty().append('<li><a name="-1" href="javascript:void(0);" onclick="show_list.changeDistrict(\'-1\');return false;">全部地区</a></li>');
			$.each(json.result,function(i,n){
				$("#propDisp2").append('<li><a name="' + n[0] + '" href="javascript:void(0);" onclick="show_list.changeDistrict(\''+ n[0]+'\');return false;">' + n[1] + '</a><i>(' + n[2] + ')</i></li>');
			})
		});
		
		//刷新最新专家
		$("#reconmendDiv").loadPage($('#initPath').val()+'/ExpertShowController.do?method=getRecommendExpert&rp=5&page=1&categoryId=' + show_list.categoryId.substring(0,show_list.categoryId.length-1));
	});

	//评价、时间排序
	$('.expertSort').click(function(){
		common.order(this);
		show_list.makeSearchData();
	});
});