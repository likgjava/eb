var show_list={};
//构造搜索条件
show_list.makeSearchData=function(firstPage){	
	show_list.search = "";	
	//分页信息
	if(($('#rp') && $('#rp').val()==null) || firstPage){
		show_list.search += "&rp=21";
		show_list.search += "&page=1";
	}else{
		show_list.search += "&rp="+$('#rp').val();
		show_list.search += "&page="+$('#page').val();
	}

	//公告发布时间排序
	if($("#pubTimeSort").hasClass("siUp")) {
		show_list.search += "&pubTimeSort=" + "asc";
	}else if($("#pubTimeSort").hasClass("siDown")) {
		show_list.search += "&pubTimeSort=" + "desc";
	}
	
	//公告创建时间排序
	if($("#createTimeSort").hasClass("siUp")) {
		show_list.search += "&createTimeSort=" + "asc";
	}else if($("#createTimeSort").hasClass("siDown")) {
		show_list.search += "&createTimeSort=" + "desc";
	}
	
	show_list.search += "&" + native2ascii(show_list.getMoreFilter());
	
	//采购品目Code
	show_list.categoryCode = "";
	if($('#propDisp1 a.strong').attr("name")!=null && $('#propDisp1 a.strong').attr("name") != "-1") {
		show_list.categoryCode = $('#propDisp1 a.strong').attr("name");
	}
	if(show_list.categoryCode=="" && $("#categoryCode").val()!=null && $("#categoryCode").val()!="") {
		show_list.categoryCode = $("#categoryCode").val();
	}
	if(show_list.categoryCode.length > 0) {
		show_list.search += "&categoryCode=" + show_list.categoryCode;
	}
	
	//区域
	show_list.districtId = "";
	if($('#propDisp2 a.strong').attr("name") != "-1") {
		$.each($('#propDisp2 a.strong'),function(i,n){
			show_list.districtId += $(n).attr("name") + ",";
		})
	}

	//区域的级别
	if($("#districtLevel").val() != null) {
		show_list.search += "&districtLevel=" + $("#districtLevel").val();
	}
	
	if(show_list.districtId.length > 0) {
		show_list.search += "&districtId=" + show_list.districtId.substring(0,show_list.districtId.length-1);
	}else {
		show_list.search += "&districtId=" + $("#districtId").val();
	}
	
	//价格区间
	show_list.search += "&price=" + $("#spurchaseBudget").val() + "," + $("#epurchaseBudget").val();
	
	var url = $('#initPath').val()+"/RequirementShowController.do?method=getRequirementForList";
	$("#showSuppListAndPic").empty().loadPage(url + show_list.search, function(){
		$("#totalAmount").html($("#totalRowNum").val());
	});
}

//点击区域过滤
show_list.changeDistrict=function(districtId){
	$("#propDisp2 a").removeClass("strong");
	$("#propDisp2 a[name="+districtId+"]").addClass("strong");
	show_list.makeSearchData('firstPage'); 
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

$(document).ready(function(){
	changeTabsCss("goToBargain");//选中顶部菜单
	
	//点击分类过滤
	$('#propDisp1 a').click(function(){
		$("#propDisp1 a").removeClass("strong");
		$("#propDisp2 a").removeClass("strong");
		$(this).addClass("strong");
		
		show_list.makeSearchData('firstPage');  //重新加载采购需求
		
		//获取区域信息
		$.getJSON($('#initPath').val()+'/RequirementShowController.do?method=getDistrictListShowByCategory'+show_list.search,{},function(json){
			$("#propDisp2").empty().append('<li><a  name="-1" href="javascript:void(0);" onclick="show_list.changeDistrict(\'-1\');return false;">全部地区</a></li>');
			$.each(json.result,function(i,n){
				$("#propDisp2").append('<li><a  name="' + n[0] + '" href="javascript:void(0);" onclick="show_list.changeDistrict(\''+ n[0]+'\');return false;">' + n[1] + '</a><i>(' + n[2] + ')</i></li>');
			})
		});
		
		//刷新推荐采购项目
		$("#reconmendDiv").loadPage($('#initPath').val()+'/BulletinShowController.do?method=getRecommendProject&rp=5&page=1&categoryCode=' + show_list.categoryCode);
	});

	//点击排序
	$('.supplierSort').click(function(){
		common.order(this);
		show_list.makeSearchData();
	});	
	
	//发布采购需求
	$("#publicRequ").click(function(){
		if(common.isLogin(true)){
			window.location.href = $('#initPath').val() + "/RequirementController.do?method=toChooseCategory";
			return false;
		}
	})
});