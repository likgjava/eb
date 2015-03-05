//定义文件全局变量 处理方法名重复问题
var show_list={};

//显示采购人或供应商详情
show_list.showDetail = function(orgInfoId, buyerId, supplierId) {
	if(orgInfoId==null || orgInfoId==""){return ;}
	if(buyerId!=null && buyerId!=""){
		common.geToBuyerDetail(buyerId);
	}else if(supplierId!=null && supplierId!=""){
		common.goToOrgShop(orgInfoId);
	}
}

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
	
	//创建时间排序
	if($("#createTimeSort").hasClass("siUp")) {
		show_list.search += "&createTimeSort=" + "asc";
	}else if($("#createTimeSort").hasClass("siDown")) {
		show_list.search += "&createTimeSort=" + "desc";
	}
	
	show_list.search += "&communityId=" + $("#currentCommunityId").val();
	show_list.search += "&" + native2ascii(show_list.getMoreFilter());

	$("#showSuppListAndPic").empty().loadPage($('#initPath').val()+"/BusinessMemberShowController.do?method=getBusinessMemberForListView"+show_list.search,function(){
		$("#totalAmount").html($("#totalRowNum").val());
	});
}

//获得更多过滤条件,将json格式的数据转换成url传参
show_list.getMoreFilter=function(){
	var filter = obj2str(formToJsonObject("businessMemberFilter"));
	var temp = filter.replace(/\",/g,"&");
	temp = temp.replace(/:\"/g,"=");
	temp = temp.replace(/{/g,"");
	temp = temp.replace(/\"}/g,"");
	return strIgnore(temp);
}

//根据社区id加载该社区下的商圈会员
show_list.loadBusinessMemberList = function(communityId){
	$("#currentCommunityId").val(communityId);
	
	$('#page').val('1');//点击不同的商品分类后重置分页值
	
	show_list.makeSearchData();
}

$(document).ready(function(){
	
	//布局页面
	fnRemoveOtherMain(); 
	
	//创建时间排序
	$('.businessMemberSort').click(function(){
		common.order(this);
		show_list.makeSearchData();
	});
	
});