var noteList={};

noteList.oTable1;
noteList.oTable2;

noteList.currentTabId = "tabs_send";

$(document).ready(function(){
	//加载tab页
	var tabs = $('#epsTabs').tabs();
	
	//tab页点击事件
	$('li a.refreshData').click(function(){
		noteList.currentTabId = $(this).attr("id");
		//点击时载入条件参数
		if(noteList.currentTabId == 'tabs_send'){			
			noteList.oTable1.fnDraw();
		}
		if(noteList.currentTabId == 'tabs_receive'){
			if(!noteList.oTable2){
				noteList.down();
			}else{				
				noteList.oTable2.fnDraw();
			}			
		}
	});
	
	//发表的留言列表
	noteList.oTable1 = $('#NoteSendList').dataTable({
		'singleSelect':true,
		'checkbox':false,
		'searchZone':'noteSearchForm',
		'queryColumns':'isRead,isReply,content,createTime,receiverName',
		'alias':'isRead,isReplyCN,content,createTime,receiverName',
		'hiddenColumns':'objId',
		'fnInitComplete':function(oSettings){
		//表格初始化完毕，未开始查询之前		
	},
		'fnDrawCallback':function(oSettings){
		
	},
		'fnRowCallback':function(nRow,aData,iDisplayIndex){
		var imgStr = '';
		if(aData.isRead == 'false' && aData.isReply == 'true') // 未读
		{
			imgStr = "<img src='"+$('#initPath').val()+"/cms/fileico/close.png' onclick='noteList.viewNote(\""+ aData.objId+"\",\""+noteList.currentTabId+"\")' title='未读'/>";
		}
		else // 已读
		{
			imgStr = "<img src='"+$('#initPath').val()+"/cms/fileico/open.png' title='已读'/>";
		}
		$(nRow).children(":first").html(imgStr);
		//查询完毕，每行回填事件方法
		$(nRow).append(noteList.getOperationStr(aData.objId));
		return nRow;
	},
		'sAjaxSource':$('#initPath').val() + "/NoteController.do?method=list",
		params:{'type':'00','leaver':$('#noteLeaver').val()}
	});
});

noteList.down = function(){
	//接收的留言列表
	noteList.oTable2 = $('#NoteReceiveList').dataTable({
		'singleSelect':true,
		'checkbox':false,
		'searchZone':'noteSearchForm',
		'queryColumns':'isRead,isReply,content,createTime,leaverName',
		'alias':'isRead,isReplyCN,content,createTime,leaverName',
		'hiddenColumns':'objId',
		'fnInitComplete':function(oSettings){
		//表格初始化完毕，未开始查询之前		
	},
		'fnDrawCallback':function(oSettings){
		
	},
		'fnRowCallback':function(nRow,aData,iDisplayIndex){
		var imgStr = '';
		if(aData.isRead == 'false' && aData.isReply == 'false') // 未读
		{
			imgStr = "<img src='"+$('#initPath').val()+"/cms/fileico/close.png' onclick='noteList.viewNote(\""+ aData.objId+"\",\""+noteList.currentTabId+"\")' title='未读'/>";
		}
		else // 已读
		{
			imgStr = "<img src='"+$('#initPath').val()+"/cms/fileico/open.png' title='已读'/>";
		}
		$(nRow).children(":first").html(imgStr);
		//查询完毕，每行回填事件方法
		$(nRow).append(noteList.getOperationStr(aData.objId));
		return nRow;
	},
		'sAjaxSource':$('#initPath').val() + "/NoteController.do?method=list",
		params:{'type':'00','receiver':$('#noteLeaver').val()}
	});
}

//添加操作字符串
noteList.getOperationStr = function(objId){
	var rowOperatorStr = '';
	if(noteList.currentTabId == 'tabs_send'){rowOperatorStr = '<td><a href="javascript:void(0);" onclick="noteList.viewNote(\''+objId+'\',\''+noteList.currentTabId+'\');return false;"><span>查看<span></a> <a href="javascript:void(0);" onclick="noteList.remove(\''+objId+'\');return false;"><span>删除<span></a></td>';}
	if(noteList.currentTabId == 'tabs_receive'){
		rowOperatorStr = '<td><a href="javascript:void(0);" onclick="noteList.reply(\''+objId+'\');return false;"><span>回复<span></a><a href="javascript:void(0);" onclick="noteList.viewNote(\''+objId+'\',\''+noteList.currentTabId+'\');return false;"><span>查看<span></a></td>';
	}
	
	return rowOperatorStr;
}

//查看
noteList.viewNote = function(noteViewId,currentTabsId){
	$.epsDialog({
		title:'留言查看',
		id:'viewNoteDialog',
		url:$('#initPath').val()+"/NoteController.do?method=toNoteView&objId="+noteViewId+"&currentTabsId="+currentTabsId,
		onOpen:function(){},
		afterload:function(){},
		onClose:function(){noteList.reload();}
	});
}

//回复
noteList.reply = function(noteReplyId){
	$.epsDialog({
		title:'留言回复',
		id:'replyNoteDialog',
		url:$('#initPath').val()+"/NoteController.do?method=toNoteView&viewOrReplyParam=reply&objId="+noteReplyId,
		onOpen:function(){},
		afterload:function(){},
		onClose:function(){noteList.reload();}
	});
}

//删除
noteList.remove = function(noteId){
	if(confirm('确定删除吗？')){
		$.getJSON($('#initPath').val()+'/NoteController.do?method=remove',{objId:noteId},function(json){
			if(json.result){alert(json.result);noteList.reload();}
			if(json.failure)return;
		});
	}	
}

//重载  刷新
noteList.reload = function(){
	if(noteList.currentTabId == 'tabs_send'){
		noteList.oTable1.fnDraw();
	}
	if(noteList.currentTabId == 'tabs_receive'){
		noteList.oTable2.fnDraw();
	}
	
}

//查询
$('#noteSearch').click(function(){
	if("tabs_send"==noteList.currentTabId){
		noteList.oTable1.fnDraw();
	}else{
		noteList.oTable2.fnDraw();
	}
});