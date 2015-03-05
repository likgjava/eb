var forumTopicList={};

forumTopicList.oTable1;

forumTopicList.oTable2;

forumTopicList.currentTabId='tabs_topicManager';

// 主题查询
$("#forumTopicSearch").click(function() {
	forumTopicList.oTable1.fnDraw();
});

// 帖子查询
$("#forumReplySeach").click(function() {
	forumTopicList.oTable2.fnDraw();
});

//刷新  oTable1 主题
forumTopicList.refreshoTable1 = function(){
	forumTopicList.oTable1.fnDraw();
}

//刷新 oTable2 帖子
forumTopicList.refreashReplyList = function(){
	forumTopicList.oTable2.fnDraw();
}

//是否置顶(isTop),是否显示(isShow),是否精华(isElite)
forumTopicList.updateTopOrShowOrElite=function(objId,isStatusVale,setType){
	var tips = '';
	if(setType == 'isTop'){tips = "置顶";}
	else if(setType == 'isElite'){tips = "精华";}
	else if(setType == 'isShow'){tips = "显示";}
	if(confirm("确认"+(isStatusVale=='true'?('取消'+tips+'?'):(tips+'？')))){
		$.getJSON($('#initPath').val()+'/ForumTopicController.do?method=updateTopOrShowOrElite',{"objId":objId,"isStatusVale":isStatusVale,'setType':setType},function(json){
			if(json.result == 'success'){
				alert("操作成功！");
				forumTopicList.refreshoTable1();
			}else{
				alert("操作失败！");
			}
		})
	}
}

//更改帖子的显示状态  帖子管理
forumTopicList.stopReply=function(objId,isShow){
	if(confirm("确认"+(isShow=='true'?'禁止显示？':'启动显示？'))){
		$.getJSON($('#initPath').val()+'/ForumReplyController.do?method=isUnfurlTopicReply',{"objId":objId,"isShow":isShow},function(json){
			if(json.result == 'success'){
				alert("操作成功！");
				forumTopicList.refreashReplyList();
			}else{
				alert("操作失败！");
				forumTopicList.refreashReplyList();
				return;
			}
		})
	}
}

// 查看主题信息
forumTopicList.viewForumTopic=function(objId){
	$.epsDialog({
		title:'查看主题信息',
		url:$('#initPath').val()+'/ForumTopicShowController.do?method=toTopicShow&objId='+objId,
		onClose:function(){
		forumTopicList.refreshoTable1();
		}
	})
}

// 查看帖子信息
forumTopicList.viewForumReply=function(objId){
	$.epsDialog({
		title:'查看帖子信息',
		url:$('#initPath').val()+'/ForumTopicShowController.do?method=toTopicReplyDetail&objId='+objId,
		onClose:function(){
		forumTopicList.refreashReplyList();
		}
	})
}

// 主题删除   
forumTopicList.removeTopicAndReply=function(objId){
	if(window.confirm("确定删除吗？若确定删除将同时删除此主题下的所有回帖。")){
		$.getJSON($('#initPath').val()+'/ForumTopicController.do?method=removeTopicAndReply',{"objId":objId},function(json){
			if(json.failure){if(json.result)alert(json.result);return;}
			alert("删除成功!");
			forumTopicList.refreshoTable1();
		});
	}
}

// 帖子删除
forumTopicList.remove=function(objId){
	if(window.confirm("确定删除吗")){
		$.getJSON($('#initPath').val()+'/ForumReplyController.do?method=remove',{"objId":objId},function(json){
			if(json.failure){if(json.result)alert(json.result);return;}
			alert("删除成功!");
			forumTopicList.refreashReplyList();
		});
	}
}

// 帖子批量删除
$('#removeForumTopicReply').click(function(){
	var selects = forumTopicList.oTable2.dtSelects();
	if(selects.length <= 0){alert("请至少选择一条记录");return false;}
	if(window.confirm("确定删除吗")){
		$.getJSON($('#initPath').val()+'/ForumReplyController.do?method=removeForumReplyArray',{"forumReplyIds":selects},function(json){
			if(json.failure){if(json.result)alert(json.result);return;}
			alert("删除成功!");
			forumTopicList.refreashReplyList();
		});
	}
});

//主题列表
forumTopicList.getforumTopicList = function(){
	if(null==forumTopicList.oTable1){
		forumTopicList.oTable1 = $('#forumTopicList').dataTable({ 
			'singleSelect':true,	
			'checkbox':false,
			'searchZone':'forumTopicSearchForm',
			"sAjaxSource": $('#initPath').val()+"/ForumTopicController.do?method=list",
			params:{},
			'queryColumns':'title,community.communityName,orginfo.orgName,attachment,updateTime',
			'hiddenColumns':'isTop,isShow,isElite,objId',
			'fnInitComplete' : function(oSettings) {
			},
			'fnRowCallback': function( nRow, aData, iDisplayIndex ) {
				// 添加回帖数
				$.getJSON($("#initPath").val()+"/ForumTopicShowController.do?method=countReplyTopicNum",{"objId":aData.objId},function(json){
					if(json.replyTopicCount){
						$(nRow).find('td[name=attachment]').empty().append(json.replyTopicCount);
					}else{
						$(nRow).find('td[name=attachment]').empty().append("0");
					}
				});
				
				$(nRow).append(forumTopicList.getOperatorStr(aData.objId,aData.isShow,aData.isTop,aData.isElite));
				return nRow;
			}
		});
	}else{
		forumTopicList.oTable1.fnDraw();
	}
}

//帖子列表
forumTopicList.getforumTopicReplyList = function(){
	if(null == forumTopicList.oTable2){
		forumTopicList.oTable2 = $('#forumReplyList').dataTable({
			'singleSelect':false,	
			'checkbox':true,
			'searchZone':'forumReplySearchForm',
			"sAjaxSource": $('#initPath').val()+"/ForumReplyController.do?method=list",
			params:{},
			'queryColumns':'topic.title,orginfo.orgName,createTime',
			'hiddenColumns':'isShow,objId',
			'fnInitComplete' : function(oSettings) {
			},
			'fnRowCallback': function( nRow, aData, iDisplayIndex ) {
				$(nRow).append(forumTopicList.getOperatorReplyStr(aData.objId,aData.isShow));
				return nRow;
			}
		});
	}else{
		forumTopicList.oTable2.fnDraw();
	}
}

//主题列表操作字符串
forumTopicList.getOperatorStr=function(objId,isShow,isTop,isElite){
	var operatorHtml = "";
	operatorHtml +='<td><a href="javascript:void(0);" onclick="forumTopicList.updateTopOrShowOrElite(\''+objId+'\',\''+isTop+'\',\'isTop\');">'+(isTop=='true'?'<span>已置顶</span>':'<span>未置顶</span>')+'</a>';
	operatorHtml += '  <a href="javascript:void(0);" onclick="forumTopicList.updateTopOrShowOrElite(\''+objId+'\',\''+isElite+'\',\'isElite\');">'+(isElite=='true'?'<span>已精华</span>':'<span>未精华</span>')+'</a>';
	operatorHtml += '  <a href="javascript:void(0);" onclick="forumTopicList.updateTopOrShowOrElite(\''+objId+'\',\''+isShow+'\',\'isShow\');">'+(isShow=='true'?'<span>已显示</span>':'<span>未显示</span>')+'</a>';
	operatorHtml += '  <a href="javascript:void(0);" onclick="forumTopicList.viewForumTopic(\''+objId+'\');return false;" title="查看" ><span>查看</span></a><a href="javascript:void(0);" onclick="forumTopicList.removeTopicAndReply(\''+objId+'\')"><span>删除</span></a>';
	operatorHtml += '</td>';
	return operatorHtml;
}

//帖子列表操作字符串
forumTopicList.getOperatorReplyStr=function(objId,isShow,isTop){
	var operatorHtml = "";
	operatorHtml += '<td><a href="javascript:void(0);" onclick="forumTopicList.stopReply(\''+objId+'\',\''+isShow+'\');">'+(isShow=='true'?'<span>已显示</span>':'<span>未显示</span>')+'</a>';
	operatorHtml += '<a href="javascript:void(0);" onclick="forumTopicList.viewForumReply(\''+objId+'\');"><span>查看<span></a><a href="javascript:void(0);" onclick="forumTopicList.remove(\''+objId+'\');"><span>删除</span></a>';
	operatorHtml += '</td>';
	return operatorHtml;
}

//tabs页点击事件
$('li a.refreshData').click(function(){
	forumTopicList.currentTabId = $(this).attr('id');
	if(forumTopicList.currentTabId == 'tabs_topicManager'){
		$('#topicReplyTips').hide();
		$('#areaForumReply').hide();
		$('#areaForumTopic').show();
		$(forumTopicList.oTable1.dataTableSettings).attr('params',{});
		forumTopicList.oTable1.fnDraw();
	}
	if(forumTopicList.currentTabId == 'tabs_topicReplyManager'){
		$('#areaForumTopic').hide();
		$('#topicReplyTips').show();
		$('#areaForumReply').show();
		if(!forumTopicList.oTable2){
			forumTopicList.getforumTopicReplyList();
		}else{				
			forumTopicList.oTable2.fnDraw();
		}
	}
});

$(document).ready(function(){
	//tabs页
	var tabs = $('#epsTabs').tabs();
	
	$('#topicReplyTips').hide();
	$('#areaForumReply').hide();
	
	//加载列表
	forumTopicList.getforumTopicList();
});

