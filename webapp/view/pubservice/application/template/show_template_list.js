//范本展示列表
var show_list={};

//下载范本文件
show_list.downloadTemplate = function(templateId,templateFileId){
	//判断是否登录
	if(common.isLogin(true,"请先登录再进行收藏！")){
		window.location.href = $('#initPath').val()+'/DotTemplateShowController.do?method=downloadTemplateFile&templateFileId='+templateFileId+'&templateId='+templateId;
	}
}

//收藏范本
show_list.favoriteTemplate = function(templateId){
	//判断是否登录
	if(common.isLogin(true,"请先登录再进行收藏！")){
		$.epsDialog({
	        title:'加入收藏',
	        width:400,
	        height:150,
	        url:$('#initPath').val()+'/DotTemplateFavoriteController.do?method=toCreateOrUpdateView&templateId='+templateId
		});
	}
}

//构造搜索条件
show_list.makeSearchData = function(firstPage){
	show_list.search = "";
	
	//分页信息
	if(($('#rp') && $('#rp').val()==null) || firstPage!=null){
		show_list.search += "&rp=20";
		show_list.search += "&page=1";
	}else{
		show_list.search += "&rp="+$('#rp').val();
		show_list.search += "&page="+$('#page').val();
	}
	
	//创建时间排序
	if($("#createTimeSort").hasClass("siUp")) {
		show_list.search += "&createTimeSort=asc";
	}else if($("#createTimeSort").hasClass("siDown")) {
		show_list.search += "&createTimeSort=desc";
	}
	//收藏次数排序
	if($("#favoriteNumSort").hasClass("siUp")) {
		show_list.search += "&favoriteNumSort=asc";
	}else if($("#favoriteNumSort").hasClass("siDown")) {
		show_list.search += "&favoriteNumSort=desc";
	}
	//下载次数排序
	if($("#downNumSort").hasClass("siUp")) {
		show_list.search += "&downNumSort=asc";
	}else if($("#downNumSort").hasClass("siDown")) {
		show_list.search += "&downNumSort=desc";
	}
	
	//采购品目
	show_list.categoryCode = "";
	if($('#propDisp1 a.strong').attr("name")!=null && $('#propDisp1 a.strong').attr("name")!="-1") {
		show_list.categoryCode = $('#propDisp1 a.strong').attr("name");
		show_list.search += "&categoryCode=" + show_list.categoryCode;
	}
	
	//区域
	show_list.districtCode = "";
	if($('#propDisp2 a.strong').attr("name") != "-1") {
		$.each($('#propDisp2 a.strong'),function(i,n){
			show_list.districtCode += $(n).attr("name") + ",";
		})
	}
	if(show_list.districtCode.length > 0) {
		show_list.search += "&districtCode=" + show_list.districtCode.substring(0,show_list.districtCode.length-1);
	}
	
	//关键字
	if($("#keyWord").val() != "") {
		show_list.search += "&keyWord=" + native2ascii(strIgnore($("#keyWord").val()));
	}
	
	//其他过滤条件
	show_list.search += "&" + native2ascii(show_list.getMoreFilter());

	$("#showTemplateList").empty().loadPage($('#initPath').val()+"/DotTemplateShowController.do?method=getTemplateForList"+show_list.search,function(){
		$("#totalAmount").html($("#totalRowNum").val());
		show_list.search = null;
	});
}

//获得更多过滤条件,将json格式的数据转换成url传参
show_list.getMoreFilter=function(){
	var filter = formToJsonObject("templateFilter");
	filter = obj2str(filter);
	var temp = filter.replace(/\",/g,"&");
	temp = temp.replace(/:\"/g,"=");
	temp = temp.replace(/{/g,"");
	temp = temp.replace(/\"}/g,"");
	
	return strIgnore(temp);
}

//点击区域过滤
show_list.changeDistrict = function(districtCode){
	$("#propDisp2 a").removeClass("strong");
	$("#propDisp2 a[name="+districtCode+"]").addClass("strong");
	show_list.makeSearchData(); //重新加载范本
}

$(document).ready(function(){
	//点击品目过滤
	$('#propDisp1 a').click(function(){
		$("#propDisp1 a").removeClass("strong");
		$("#propDisp2 a").removeClass("strong");
		$(this).addClass("strong");
		
		show_list.makeSearchData(); //重新加载范本
		
		//获取区域信息
		$.getJSON($('#initPath').val()+'/DotTemplateShowController.do?method=getDistrictListForShow',{"categoryCode":show_list.categoryCode, "keyWord":native2ascii(strIgnore($("#keyWord").val()))},function(json){
			if(json.result.length < 17) {
				$("#expandProp2").parent().remove();
			}else {
				$("#moreProp2").next("div").empty().append('<a href="javascript:void(0);" onclick="common.showOrHiddenMore(\'expandProp2\',\'moreProp2\',\'区域\');return false;"><span class="sysicon siDownGray" id="expandProp2">显示全部区域</span></a>');
			}
			
			$("#propDisp2").empty().append('<li><a name="-1" href="javascript:void(0);" onclick="show_list.changeDistrict(\'-1\');return false;">全部区域</a></li>');
			$.each(json.result, function(i,n){
				$("#propDisp2").append('<li><a href="javascript:void(0);" onclick="show_list.changeDistrict(\'' + n[0] + '\');return false;" name="' + n[0] + '">' + n[1] + '</a><i>(' + n[2] + ')</i></li>');
			})
		});
	});

	//创建时间排序
	$('.templateSort').click(function(){
		common.order(this);
		show_list.makeSearchData();
	});
	
});