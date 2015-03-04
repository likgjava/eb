var consulationList={};
consulationList.oTable1;
consulationList.currentTabId = "tabs_receive";

$(document).ready(function(){
	//开始时间
	$("#startDate").epsDatepicker();
	//结束时间
	$("#endDate").epsDatepicker();
	
	
	//加载tab页
	var tabs = $('#epsTabs').tabs();
	
	//tab页点击事件
	$('li a.refreshData').click(function(){
		consulationList.currentTabId = $(this).attr("id");
		//点击时载入条件参数
		if(consulationList.currentTabId == 'tabs_done'){
			$(consulationList.oTable1.dataTableSettings).attr('params',{'type':'01','isReply':'true'});
			consulationList.oTable1.fnDraw();
		}
		if(consulationList.currentTabId == 'tabs_receive'){
			$(consulationList.oTable1.dataTableSettings).attr('params',{'type':'01','isReply':'false'});
			consulationList.oTable1.fnDraw();
		}
	});
	
	//咨询处理列表
	consulationList.oTable1 = $('#consulationList').dataTable({
		'singleSelect':true,
		'checkbox':false,
		'searchZone':'consulationSearchForm',
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
		if(aData.isRead == 'false' && aData.isReply == 'false'){ // 未读
			imgStr = "<img src='"+$('#initPath').val()+"/cms/fileico/close.png' onclick='consulationList.viewNote(\""+ aData.objId+"\",\""+consulationList.currentTabId+"\")' title='未读'/>";
			
		}else{ //已读
			imgStr = "<img src='"+$('#initPath').val()+"/cms/fileico/open.png' title='已读'/>";
		}
		
		$(nRow).children(":first").html(imgStr);

		//查询完毕，每行回填事件方法
		$(nRow).append(consulationList.getOperationStr(aData.objId));
		return nRow;
	},
		'sAjaxSource':$('#initPath').val() + "/NoteController.do?method=list",
		params:{'type':'01','isReply':'false'}
	});
});

//添加操作字符串
consulationList.getOperationStr = function(objId){
	var rowOperatorStr = '';
	if(consulationList.currentTabId == 'tabs_receive'){rowOperatorStr = '<td><a href="javascript:void(0);" onclick="consulationList.viewNote(\''+objId+'\',\''+consulationList.currentTabId+'\');return false;"><span>查看<span></a> <a href="javascript:void(0);" onclick="consulationList.reply(\''+objId+'\');return false;"><span>回复<span></a> <a href="javascript:void(0);" onclick="consulationList.remove(\''+objId+'\');return false;"><span>删除<span></a></td>';}
	if(consulationList.currentTabId == 'tabs_done'){rowOperatorStr = '<td><a href="javascript:void(0);" onclick="consulationList.viewNote(\''+objId+'\',\''+consulationList.currentTabId+'\');return false;"><span>查看<span></a> <a href="javascript:void(0);" onclick="consulationList.remove(\''+objId+'\');return false;"><span>删除<span></a></td>';}
	return rowOperatorStr;
}

//查看
consulationList.viewNote = function(noteViewId,currentTabsId){
	$.epsDialog({
		title:'咨询查看',
		id:'viewNoteDialog',
		url:$('#initPath').val()+"/NoteController.do?method=toNoteView&objId="+noteViewId+"&currentTabsId="+currentTabsId,
		onClose:function(){consulationList.reload();}
	});
}

//回复
consulationList.reply = function(noteReplyId){
	$.epsDialog({
		title:'咨询回复',
		id:'replyNoteDialog',
		url:$('#initPath').val()+"/NoteController.do?method=toNoteView&viewOrReplyParam=reply&objId="+noteReplyId,
		onClose:function(){consulationList.reload();}
	});
}

//删除
consulationList.remove = function(noteId){
	if(confirm('删除为永久性删除，删除数据将无法恢复。确定删除吗？')){
		$.getJSON($('#initPath').val()+'/NoteController.do?method=remove',{objId:noteId},function(json){
			if(json.result){alert(json.result);consulationList.reload();}
			if(json.failure)return;
		});		
	}
}

//重载  刷新
consulationList.reload = function(){
	consulationList.oTable1.fnDraw();
}

//查询
$('#consulationSearch').click(function(){
	var table = consulationList.oTable1;
	var setting = consulationList.oTable1.dataTableSettings[0];
	if(consulationList.currentTabID == "tabs_done"){
		table = consulationList.oTable2;
		setting = OrderMonitorList.oTable2.dataTableSettings[1];
	}
	
	if($("#startDate").val().length > 0 && $("#endDate").val().length == 0){
		$(table.dataTableSettings).attr('params', 
				$.extend(setting.params,{"createTime":$("#startDate").val(),"createTime_op":"ge"}));
	}
	else if($("#endDate").val().length > 0 && $("#startDate").val().length == 0){
		$(table.dataTableSettings).attr('params',
				$.extend(setting.params,{"createTime":$("#startDate").val(),"createTime_op":"le"}));
	}
	else if($("#endDate").val().length > 0 && $("#startDate").val().length > 0){
		$(table.dataTableSettings).attr('params',
				$.extend(setting.params,{"createTime":$("#startDate").val()+","+$("#endDate").val(),"createTime_op":"bt"}));
	}
	table.fnDraw();
});