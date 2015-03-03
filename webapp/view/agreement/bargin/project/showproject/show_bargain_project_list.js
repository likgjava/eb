/*小额交易平台.采购项目展示列表*/

var show_list={};

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
	
	//项目创建时间排序
	if($("#createTimeSort").hasClass("siUp")) {
		show_list.search += "&createTimeSort=asc";
	}else if($("#createTimeSort").hasClass("siDown")) {
		show_list.search += "&createTimeSort=desc";
	}
	
	//采购金额区间
	show_list.amountRange = "";
	if($('#propDisp1 a.strong').attr("name")) {
		show_list.amountRange = $('#propDisp1 a.strong').attr("name");
		show_list.search += "&amountRange=" + show_list.amountRange;
	}else {
		show_list.search += "&amountRange=";
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

	var url = $('#initPath').val()+"/ProjectShowController.do?method=getBargainProjectListForShow";
	$("#showSuppListAndPic").empty().loadPage(url + show_list.search, function(){
		$("#totalAmount").html($("#totalRowNum").val());
	});
}

//点击区域过滤
show_list.changeDistrict=function(districtId){
	$("#propDisp2 a").removeClass("strong");
	$("#propDisp3 a").removeClass("strong");
	$("#propDisp2 a[name="+districtId+"]").addClass("strong");
	
	show_list.makeSearchData('firstPage');  
	
	//获取采购品目信息
	$.getJSON($('#initPath').val()+'/ProjectShowController.do?method=getPurCategoryListForShow'+show_list.search, {}, function(json){
		$("#propDisp3").empty().append('<li><a name="-1" href="javascript:void(0);" onclick="show_list.changePurCategory(\'-1\');return false;">全部分类</a></li>');
		$.each(json.purCategoryList[0],function(i,id){
			$("#propDisp3").append('<li><a name="'+ id +'" href="javascript:void(0);" onclick="show_list.changePurCategory(\''+ id+'\');return false;">' + json.purCategoryList[1][i] + '</a><i>(' + json.purCategoryList[2][i] + ')</i></li>');
		});
	});
}
//点击采购品目过滤
show_list.changePurCategory=function(purCategoryId){
	$("#propDisp3 a").removeClass("strong");
	$("#propDisp3 a[name="+purCategoryId+"]").addClass("strong");
	
	show_list.makeSearchData('firstPage');  
}

//获得更多过滤条件,将json格式的数据转换成url传参
show_list.getMoreFilter=function(){
	var filter = obj2str(formToJsonObject("bulletinFilter"));
	var temp = filter.replace(/\",/g,"&");
	temp = temp.replace(/:\"/g,"=");
	temp = temp.replace(/{/g,"");
	temp = temp.replace(/\"}/g,"");
	return strIgnore(temp);
}

//推荐项目公告（通过发送邮件分享给好友）
show_list.shareBulletin = function(projectId){
	if(common.isLogin(true,"请先登录再进行推荐！")){
		$.getJSON($('#initPath').val()+'/BulletinShowController.do?method=getBulletinByProjectId',{'projectId':projectId, 'bulletinType':'12'}, function(json){
			if(json.bulletinList.length > 0){
				bulletin = json.bulletinList[0];
				$.epsDialog({
			        title:'将此公告推荐给好友',
			        width:320,
			        height:150,
			        url:$('#initPath').val()+'/view/agreement/showbulletin/share_bulletin.jsp?bulletinId='+bulletin.objId
				});
			}
		});
	}
}

//收藏公告
show_list.addFavorites = function(projectId){
	if(common.isLogin(true,"请先登录再进行收藏！")){
		$.getJSON($('#initPath').val()+'/BulletinShowController.do?method=getBulletinByProjectId',{'projectId':projectId, 'bulletinType':'12'}, function(json){
			if(json.bulletinList.length > 0){
				bulletin = json.bulletinList[0];
				common.addFavorites(bulletin.objId,bulletin.bullTitle,'07');
			}
		});
	}
}

$(document).ready(function(){
	//选中顶部菜单
	changeTabsCss("goToBargain");
	
	//点击不同的采购金额区间过滤
	$('#propDisp1 a').click(function(){
		$("#propDisp1 a").removeClass("strong");
		$("#propDisp2 a").removeClass("strong");
		$("#propDisp3 a").removeClass("strong");
		$(this).addClass("strong");
		
		//重新加载采购项目
		show_list.makeSearchData('firstPage');
		
		//获取区域信息
		$.getJSON($('#initPath').val()+'/ProjectShowController.do?method=getDistrictListForShow'+show_list.search, {}, function(json){
			$("#propDisp2").empty().append('<li><a name="-1" href="javascript:void(0);" onclick="show_list.changeDistrict(\'-1\');return false;">全部地区</a></li>');
			$.each(json.result,function(i,n){
				$("#propDisp2").append('<li><a name="' + n[0] + '" href="javascript:void(0);" onclick="show_list.changeDistrict(\''+ n[0]+'\');return false;">' + n[1] + '</a><i>(' + n[2] + ')</i></li>');
			});
		});
		
		//获取采购品目信息
		$.getJSON($('#initPath').val()+'/ProjectShowController.do?method=getPurCategoryListForShow'+show_list.search, {}, function(json){
			$("#propDisp3").empty().append('<li><a name="-1" href="javascript:void(0);" onclick="show_list.changePurCategory(\'-1\');return false;">全部分类</a></li>');
			$.each(json.purCategoryList[0],function(i,id){
				$("#propDisp3").append('<li><a name="' + id + '" href="javascript:void(0);" onclick="show_list.changePurCategory(\''+ id+'\');return false;">' + json.purCategoryList[1][i] + '</a><i>(' + json.purCategoryList[2][i] + ')</i></li>');
			});
		});
		
		//刷新推荐采购项目
		$("#reconmendDiv").loadPage($('#initPath').val()+'/ProjectShowController.do?method=toRecommendProjectSuppView&rp=5&page=1&amountRange='+show_list.amountRange);
	});

	//点击排序
	$('.supplierSort').click(function(){
		common.order(this);
		show_list.makeSearchData();
	});
	
});