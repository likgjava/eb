var ShowCommunityIndex = {};
ShowCommunityIndex.oTable1;
ShowCommunityIndex.oTable2;

//显示采购人或供应商详情
ShowCommunityIndex.showOrgDetail = function(orgInfoId, buyerId, supplierId) {
	if(orgInfoId==null || orgInfoId==""){return ;}
	if(buyerId!=null && buyerId!="" && buyerId!="undefined"){
		common.geToBuyerDetail(buyerId);
	}else{
		common.goToOrgShop(orgInfoId);
	}
}

//打开主题详情
ShowCommunityIndex.showTopicDetail = function(topicId) {
	$('#returnList').removeClass();//显示返回列表按钮
	$("#topicList").addClass("hidden"); //隐藏列表页面
	$('#topicInfo').removeClass().loadPage($('#initPath').val()+"/ForumTopicShowController.do?method=toTopicShow&objId="+topicId+"&skipType=unfurlTopic");
}

//取取商圈会员列表
ShowCommunityIndex.getBusinessMemberList = function() {
	if(null==ShowCommunityIndex.oTable1){
		ShowCommunityIndex.oTable1 = $('#businessMemberList').dataTable({   
			'params':{communityId:$("#communityId").val()},
			'searchZone':'messageSearchForm',
			'singleSelect':true,	
			'checkbox':false,		
			'queryColumns':'orgInfo.orgName,orgInfo.belongIndustry.name,orgInfo.distinctName,createTime',
			'fnInitComplete':function(oSettings) {
				 //表格初始化完毕、未开始查询之前的方法
			},
			'fnDrawCallback':function(oSettings) {
				ShowCommunityIndex.oTable1.oSettings=oSettings;
			},
			'fnRowCallback': function( nRow, aData, iDisplayIndex ) {
				
				//中文错误特殊处理
				if(aData.orgInfo.belongIndustry==null){
					$(nRow).find("td[name=orgInfo.belongIndustry.name]").html(null);
				}
				
				$(nRow).find('td[name=createTime]').html(aData.createTime.substring(0,aData.createTime.indexOf('.')));
				var buyerId = aData.orgInfo.buyerId==null ? "" : aData.orgInfo.buyerId;
				var supplierId = aData.orgInfo.supplierId==null ? "" : aData.orgInfo.supplierId;
				$(nRow).children(":first").html('<a href="javascript:void(0);" onclick="ShowCommunityIndex.showOrgDetail(\''+aData.orgInfo.objId+'\',\''+buyerId+'\',\''+supplierId+'\')">'+aData.orgInfo.orgName+'</a>');
				return nRow;
			},
			"sAjaxSource": $('#initPath').val()+"/BusinessMemberShowController.do?method=getBusinessMemberList"
		});
	}else{
		$(ShowCommunityIndex.oTable1.dataTableSettings).attr('params', {communityId:$("#communityId").val()});
		ShowCommunityIndex.oTable1.fnDraw();
	}
}

//取取商圈主题列表
ShowCommunityIndex.getForumTopicList = function() {
	if(null==ShowCommunityIndex.oTable2){
		ShowCommunityIndex.oTable2 = $('#forumTopicList').dataTable({   
			'params':{"communityId":$("#communityId").val(),"isShow":true},
			'searchZone':'messageSearchForm',
			'singleSelect':true,	
			'checkbox':false,		
			'queryColumns':'title,orginfo.orgName,isShow,updateTime',
			'hiddenColumns':'isTop,isShow,isElite,objId,community,createUser,orginfo.objId,orginfo.buyerId,orginfo.supplierId',
			'fnInitComplete':function(oSettings) {
				//表格初始化完毕、未开始查询之前的方法
			},
			'fnDrawCallback':function(oSettings) {
				ShowCommunityIndex.oTable2.oSettings=oSettings;
			},
			'fnRowCallback': function( nRow, aData, iDisplayIndex ) {
				
				//查看主题回复帖子记录
				$(nRow).find('td:eq(0)').empty().append('<a href="javascript:void(0)" name="viewTopics">'+aData.title+'</a>');
				$(nRow).find('a[name=viewTopics]').click(function(){
					ShowCommunityIndex.showTopicDetail(aData.objId);
				});
				
				//置顶
				if(aData.isTop == 'true'){
					$(nRow).find("td[name=title]").prepend('<img src="view/resource/skin/smallscale/img/top_icon.png" style="cursor:pointer"/>&nbsp;');
				}
				//精英
				if(aData.isElite =='true'){
					$(nRow).find("td[name=title]").prepend('<img src="view/resource/skin/smallscale/img/jing_icon.png" style="cursor:pointer"/>&nbsp;');
				}
				
				if(aData.isTop == 'false' && aData.isElite =='false'){
					$(nRow).find("td[name=title]").prepend('<img src="view/resource/skin/smallscale/img/normal_icon.png" style="cursor:pointer"/>&nbsp;');
				}
				

				//查看发表机构信息
				var buyerId = aData.orginfo.buyerId;
				var supplierId = aData.orginfo.supplierId;
				
				$(nRow).find("td[name='orginfo.orgName']").html('<a href="javascript:void(0);" onclick="ShowCommunityIndex.showOrgDetail(\''+aData.orginfo.objId+'\',\''+buyerId+'\',\''+supplierId+'\')">'+aData.orginfo.orgName+'</a>');

				//添加回帖数
				$.getJSON($("#initPath").val()+"/ForumTopicShowController.do?method=countReplyTopicNum",{"objId":aData.objId},function(json){
					if(json.replyTopicCount){
						$(nRow).find("td[name=isShow]").empty().append(json.replyTopicCount);
					}else{
						$(nRow).find("td[name=isShow]").empty().append("0");
					}
				});
				return nRow;
			},
			"sAjaxSource": $('#initPath').val()+"/ForumTopicShowController.do?method=getCommunityTopicList"
		});
	}else{
		$(ShowCommunityIndex.oTable2.dataTableSettings).attr('params', {"communityId":$("#communityId").val(),"isShow":true});
		ShowCommunityIndex.oTable2.fnDraw();
	}
}

$(document).ready(function(){
	//商圈选中样式
	changeTabsCss('bizarea');

	$('#epsTabs').tabs();//加载tabs
	
	//取取商圈会员列表
	ShowCommunityIndex.getBusinessMemberList();
	
	//取取商圈主题列表或者详情
	if($("#topicId").val() == "" || $("#topicId").val() == "undefind"){
		ShowCommunityIndex.getForumTopicList();
	}else {
		ShowCommunityIndex.showTopicDetail($("#topicId").val());
	}
	
	//新增主题
	$('#newAddForumTopic').click(function(){
		
		//判断当前机构是否拥有'商圈会员'角色
		var jsonObj=$.ajax({url:$("#initPath").val()+"/ForumTopicShowController.do?method=getCurrentUserIsBizMember",data:{}, async: false }).responseText
		jsonObj = eval("("+jsonObj+")");
		if(jsonObj.isBizMember !="true"){
			alert("你还没有订阅'商圈会员'服务,请先订阅商圈会员服务!");
			return false;
		}
		
		$('#returnList').removeClass();//显示返回列表按钮
		$('#newAddForumTopic').addClass("hidden");//隐藏发表主题按钮
		$("#topicList").addClass("hidden"); //隐藏列表页面
		$('#topicInfo').removeClass().loadPage($('#initPath').val()+"/ForumTopicController.do?method=toAddTopicView&communityId="+$("#communityId").val());
	});
	
	//用户未登录时不显示，登录后显示
	if($("#loginUser") ==null || $("#loginUser").length < 1){
		$("#noLoginHiddenDiv").hide();
		$("#newAddForumTopic").hide();
	}else{
		$("#noLoginHiddenDiv").show();
		$("#newAddForumTopic").show();
	}
	
	//返回列表
	$("#returnList").click(function(){
		$("#topicInfo").addClass("hidden");
		$('#topicList').removeClass();
		$('#newAddForumTopic').removeClass("hidden");
		$('#returnList').addClass("hidden");
		ShowCommunityIndex.getForumTopicList();
	})
});

