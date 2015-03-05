var communityMineTopic={};

communityMineTopic.oTable1;

communityMineTopic.oTable2;

communityMineTopic.currentTabId='tabs_topicManager';

// 主题查询
$("#forumTopicSearch").click(function() {
	communityMineTopic.oTable1.fnDraw();
});

// 帖子查询
$("#forumReplySeach").click(function() {
	communityMineTopic.oTable2.fnDraw();
});

//刷新  oTable1 主题
communityMineTopic.refreshoTable1 = function(){
	communityMineTopic.oTable1.fnDraw();
}

//刷新 oTable2 帖子
communityMineTopic.refreashReplyList = function(){
	communityMineTopic.oTable2.fnDraw();
}

//更改主题的显示状态  主题管理
communityMineTopic.stopTopic=function(objId,isShow){
	if(confirm("确认"+(isShow=='true'?'禁止显示？':'启动显示？'))){
		$.getJSON($('#initPath').val()+'/ForumTopicController.do?method=updateShowStatus',{"objId":objId,"showType":isShow},function(json){
			if(json.result == 'success'){
				alert("操作成功！");
				communityMineTopic.refreshoTable1();
			}else{
				alert("操作失败！");
			}
		})
	}
}

//更改帖子的显示状态  帖子管理
communityMineTopic.stopReply=function(objId,isShow){
	if(confirm("确认"+(isShow=='true'?'禁止显示？':'启动显示？'))){
		$.getJSON($('#initPath').val()+'/ForumReplyController.do?method=isUnfurlTopicReply',{"objId":objId,"isShow":isShow},function(json){
			if(json.result == 'success'){
				alert("操作成功！");
				communityMineTopic.refreashReplyList();
			}else{
				alert("操作失败！");
				communityMineTopic.refreashReplyList();
				return;
			}
		})
	}
}

// 置顶
communityMineTopic.topShow=function(objId){
	if(confirm("确定让该信息置顶？")){
		$.getJSON($('#initPath').val()+'/ForumTopicController.do?method=updatetopShow',{"objId":objId},function(json){
			if(json.result == 'success'){
				alert("置顶成功！");
				communityMineTopic.refreshoTable1();
			}else{
				alert("置顶失败！");
			}
		})
	}
}

// 查看主题信息
communityMineTopic.viewForumTopic=function(objId){
	$.epsDialog({
		title:'查看主题信息',
		url:$('#initPath').val()+'/ForumTopicShowController.do?method=toTopicShow&objId='+objId,
		onClose:function(){
		communityMineTopic.refreshoTable1();
		}
	})
}

// 查看帖子信息
communityMineTopic.viewForumReply=function(objId){
	$.epsDialog({
		title:'查看帖子信息',
		url:$('#initPath').val()+'/ForumTopicShowController.do?method=toTopicReplyDetail&objId='+objId,
		onClose:function(){
		communityMineTopic.refreashReplyList();
		}
	})
}

// 主题删除   
communityMineTopic.remove=function(objId){
	if(window.confirm("确定删除吗？若确定删除将同时删除此主题下的所有回帖。")){
		$.getJSON($('#initPath').val()+'/ForumTopicController.do?method=removeTopicAndReply',{"objId":objId},function(json){
			if(json.failure){if(json.result)alert(json.result);return;}
			alert("删除成功!");
			communityMineTopic.refreshoTable1();
		});
	}
}

// 帖子删除
communityMineTopic.remove=function(objId){
	if(window.confirm("确定删除吗")){
		$.getJSON($('#initPath').val()+'/ForumReplyController.do?method=remove',{"objId":objId},function(json){
			if(json.failure){if(json.result)alert(json.result);return;}
			alert("删除成功!");
			communityMineTopic.refreashReplyList();
		});
	}
}

// 帖子批量删除
$('#removeForumTopicReply').click(function(){
	var selects = communityMineTopic.oTable2.dtSelects();
	if(selects.length <= 0){alert("请至少选择一条记录");return false;}
	if(window.confirm("确定删除吗")){
		$.getJSON($('#initPath').val()+'/ForumReplyController.do?method=removeForumReplyArray',{"forumReplyIds":selects},function(json){
			if(json.failure){if(json.result)alert(json.result);return;}
			alert("删除成功!");
			communityMineTopic.refreashReplyList();
		});
	}
});

//主题列表
communityMineTopic.getcommunityMineTopic = function(){
	if(null==communityMineTopic.oTable1){
		communityMineTopic.oTable1 = $('#forumTopicList').dataTable({ 
			'singleSelect':true,	
			'checkbox':false,
			'searchZone':'forumTopicSearchForm',
			"sAjaxSource": $('#initPath').val()+"/ForumTopicController.do?method=list",
			params:{'createUser.objId':$('#currentUserId').val()},
			'queryColumns':'title,community.communityName,orginfo.orgName,updateTime',
			'hiddenColumns':'isTop,isShow,objId,community',
			'fnInitComplete' : function(oSettings) {
			},
			'fnRowCallback': function( nRow, aData, iDisplayIndex ) {
				// 查看社区信息
				$(nRow).find('td[name=community.communityName]').empty().append('<a href="javascript:void(0);" name="communityNameStr">'+aData["community.communityName"]+'</a>');
				$(nRow).find('a[name=communityNameStr]').click(function(){
					$('#shangquanCommunityInfo').loadPage($('#initPath').val()+'/ForumTopicShowController.do?method=toCommunityTopicInfo&objId='+aData["community"].objId);
				});
				
				// 添加回帖数
				$.getJSON($("#initPath").val()+"/ForumTopicShowController.do?method=countReplyTopicNum",{"objId":aData.objId},function(json){
					if(json.replyTopicCount){
						$(nRow).find('td[name=orginfo.orgName]').empty().append(json.replyTopicCount);
					}else{
						$(nRow).find('td[name=orginfo.orgName]').empty().append("0");
					}
				});
				$(nRow).append(communityMineTopic.getOperatorStr(aData.objId,aData.isShow,aData.isTop));
				return nRow;
			}
		});
	}else{
		communityMineTopic.oTable1.fnDraw();
	}
}

//帖子列表
communityMineTopic.getforumTopicReplyList = function(){
	if(null == communityMineTopic.oTable2){
		communityMineTopic.oTable2 = $('#forumReplyList').dataTable({
			'singleSelect':false,	
			'checkbox':true,
			'searchZone':'forumReplySearchForm',
			"sAjaxSource": $('#initPath').val()+"/ForumReplyController.do?method=list",
			params:{'createUser.objId':$('#currentUserId').val()},
			'queryColumns':'topic.title,createTime',
			'hiddenColumns':'isShow,objId',
			'fnInitComplete' : function(oSettings) {
			},
			'fnRowCallback': function( nRow, aData, iDisplayIndex ) {
				$(nRow).append(communityMineTopic.getOperatorReplyStr(aData.objId,aData.isShow));
				return nRow;
			}
		});
	}else{
		communityMineTopic.oTable2.fnDraw();
	}
}

//主题列表操作字符串
communityMineTopic.getOperatorStr=function(objId,isShow,isTop){
	var operatorHtml = "";
	operatorHtml += '<td>  <a href="javascript:void(0);" onclick="communityMineTopic.viewForumTopic(\''+objId+'\');return false;" title="查看" ><span>查看</span></a><a href="javascript:void(0);" onclick="communityMineTopic.remove(\''+objId+'\')"><span>删除</span></a>';
	operatorHtml += '</td>';
	return operatorHtml;
}

//帖子列表操作字符串
communityMineTopic.getOperatorReplyStr=function(objId,isShow,isTop){
	var operatorHtml = "";
	operatorHtml += '<td><a href="javascript:void(0);" onclick="communityMineTopic.viewForumReply(\''+objId+'\');"><span>查看<span></a><a href="javascript:void(0);" onclick="communityMineTopic.remove(\''+objId+'\');"><span>删除</span></a>';
	operatorHtml += '</td>';
	return operatorHtml;
}

//tabs页点击事件
$('li a.refreshData').click(function(){
	communityMineTopic.currentTabId = $(this).attr('id');
	if(communityMineTopic.currentTabId == 'tabs_topicManager'){
		$('#topicReplyTips').hide();
		$('#areaForumReply').hide();
		$('#areaForumTopic').show();
		$(communityMineTopic.oTable1.dataTableSettings).attr('params',{'createUser.objId':$('#currentUserId').val()});
		communityMineTopic.oTable1.fnDraw();
	}
	if(communityMineTopic.currentTabId == 'tabs_topicReplyManager'){
		$('#areaForumTopic').hide();
		$('#topicReplyTips').show();
		$('#areaForumReply').show();
		if(!communityMineTopic.oTable2){
			communityMineTopic.getforumTopicReplyList();
		}else{				
			communityMineTopic.oTable2.fnDraw();
		}
	}
});

$(document).ready(function(){
	//tabs页
	var tabs = $('#epsTabs').tabs();
	
	$('#topicReplyTips').hide();
	$('#areaForumReply').hide();
	
	var currentUserId = $("#loginUser").find('a').attr('id');
	$('#currentUserId').val(currentUserId);
	
	//加载列表
	communityMineTopic.getcommunityMineTopic();
});

