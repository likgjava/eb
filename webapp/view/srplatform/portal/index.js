
$(document).ready(function(){
	
	//如果使用静态页面且是已登录用户，则显示登录信息
	if(common.useStaticIndexPage){
		$.getJSON($('#initPath').val()+"/IndexViewController.do?method=getCurrentUser", function(userjson){
			if(userjson.failure){if(userjson.result)alert(userjson.result);return;}
			if(userjson.isLogin){
				//修改头部信息
				common.getCurrUser(userjson);
				
				//加载首页中进入后台的按钮
				$("#loginRegDiv").empty().loadPage($('#initPath').val()+'/view/srplatform/portal/include/login_reg_div.jsp');
			}
		});
	}
	
	//加载首页中重磅推荐项目
	$("#recommendProjectIndexDiv").loadPage($('#initPath').val()+'/IndexViewController.do?method=getRecommendProjectIndexView');
	
	//加载首页中推荐专家
	if($("#recommendExp").html() || $("#recommendExp").html() == ""){
		$("#recommendExp").loadPage($('#initPath').val()+'/ExpertShowController.do?method=getRecommendExpert&rp=9&page=1&viewName=recommendExpertIndexView');
	}
	
	//推荐社区
	if($("#recommendCommunity").html() || $("#recommendCommunity").html() == ""){
		$("#recommendCommunity").loadPage($('#initPath').val()+'/CommunityShowController.do?method=getCommunityForIndex&rp=8&page=1');
	}
});