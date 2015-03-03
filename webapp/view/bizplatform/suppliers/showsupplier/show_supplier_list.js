/*
 * 小额交易平台.供应商展示列表
 * author: liangxj
 * mail: liangxj@gpcsoft.com
 */

//定义文件全局变量 处理方法名重复问题
var show_list={};

//构造搜索条件
show_list.makeSearchData=function(){
	show_list.search = "";
	//分页信息
	if($('#rp') && $('#rp').val()==null){
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
	
	//类别
	show_list.categoryId = "";
	if($('#propDisp1 a.strong').attr("name") != "-1") {
		$.each($('#propDisp1 a.strong'),function(i,n){
			show_list.categoryId += $(n).attr("name") + ",";
		})
	}
	if(!show_list.categoryId) {//点击左边品目菜单时没有class=strong
		show_list.categoryId += $('#categoryCode_menu').val() + ",";
	}
	if(show_list.categoryId.length > 0) {
		show_list.search += "&categoryCode=" + show_list.categoryId.substring(0,show_list.categoryId.length-1);
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

	$("#showSuppListAndPic").empty().loadPage($('#initPath').val()+"/SupplierShowController.do?method=getSupplierForList"+show_list.search,function(){
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

//加入我的客户
show_list.addClient = function(groupType,orgInfoId,orgInfoName){
	$.epsDialog({
		title:"关注供应商",
		width:500,
		height:300,
		url:$('#initPath').val()+"/ConcernController.do?method=toAddConcernForm&groupType="+groupType+"&orgInfoId="+orgInfoId,
		afterLoad: function(){$('#orgInfoName').html(orgInfoName) }
	});
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
	changeTabsCss("goToSupplier");//选中顶部菜单
	
	//选中查询下拉框
	keyWordTypeChange('4');
	
	//点击分类过滤
	$('#propDisp1 a').click(function(){
		$("#propDisp1 a").removeClass("strong");
		$("#propDisp2 a").removeClass("strong");
		$(this).addClass("strong");
		
		$('#page').val('1');//点击不同的商品分类后重置分页值
		
		show_list.makeSearchData();  //重新加载供应商(分页从第一页开始)
		
		//获取区域信息
		$.getJSON($('#initPath').val()+'/SupplierShowController.do?method=getDistrictListShowByCategory'+show_list.search,{},function(json){
			$("#propDisp2").empty().append('<li><a  name="-1" href="javascript:void(0);" onclick="show_list.changeDistrict(\'-1\');return false;">全部地区</a></li>');
			$.each(json.result,function(i,n){
				$("#propDisp2").append('<li><a  name="' + n[0] + '" href="javascript:void(0);" onclick="show_list.changeDistrict(\''+ n[0]+'\');return false;">' + n[1] + '</a><i>(' + n[2] + ')</i></li>');
			})
		});
		
		//刷新推荐供应商
		$("#reconmendDiv").loadPage($('#initPath').val()+'/SupplierShowController.do?method=getRecommendSupplierInfo&rp=5&page=1&categoryCode=' + show_list.categoryId.substring(0,show_list.categoryId.length-1));
	});

	//评价、时间排序
	$('.supplierSort').click(function(){
		common.order(this);
		show_list.makeSearchData();
	});
});