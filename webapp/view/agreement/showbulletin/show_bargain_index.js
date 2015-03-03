var ShowBargainIndex = {};

//推荐项目公告（通过发送邮件分享给好友）
ShowBargainIndex.shareBulletin = function(projectId){
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
ShowBargainIndex.addFavorites = function(projectId){
	if(common.isLogin(true,"请先登录再进行收藏！")){
		$.getJSON($('#initPath').val()+'/BulletinShowController.do?method=getBulletinByProjectId',{'projectId':projectId, 'bulletinType':'12'}, function(json){
			if(json.bulletinList.length > 0){
				bulletin = json.bulletinList[0];
				common.addFavorites(bulletin.objId,bulletin.bullTitle,'07');
			}
		});
	}
}

//跳转到电子采购项目列表页面
ShowBargainIndex.toBargainProjectListView = function(){
	window.open($('#initPath').val()+"/ProjectShowController.do?method=toShowBargainProjectList&rp=21&page=1");
}

$(document).ready(function(){
	//选中顶部菜单
	changeTabsCss("goToBargain");
	
	//加载电子采购页中重磅推荐项目
	$("#recommendBargainProjectDiv").loadPage($('#initPath').val()+'/ProjectShowController.do?method=getRecommendBargainProjectIndexView');
	
	//如果使用静态页面且是已登录用户，则显示登录信息
	if(common.useStaticIndexPage){
		$.getJSON($('#initPath').val()+"/IndexViewController.do?method=getCurrentUser", function(userjson){
			if(userjson.failure){if(userjson.result)alert(userjson.result);return;}
			if(userjson.isLogin){
				//修改头部信息
				common.getCurrUser(userjson);
			}
		});
	}
});