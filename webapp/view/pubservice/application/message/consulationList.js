var consulationList={};
consulationList.oTable1;
consulationList.currentTabId = "tabs_send";

$(document).ready(function(){
	//加载tab页
	var tabs = $('#epsTabs').tabs();
	
	//tab页点击事件
	$('li a.refreshData').click(function(){
		consulationList.currentTabId = $(this).attr("id");
		
		//点击时载入条件参数
		$(consulationList.oTable1.dataTableSettings).attr('params',{'type':'01','leaver':$('#consulationLeaver').val()});
		consulationList.oTable1.fnDraw();
	});
	
	//我的咨询列表
	consulationList.oTable1 = $('#consulationList').dataTable({
		'singleSelect':true,
		'checkbox':false,
		'searchZone':'consulationSearchForm',
		'queryColumns':'isRead,isReply,content,createTime',
		'alias':'isRead,isReplyCN,content,createTime',
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
			imgStr = "<img src='"+$('#initPath').val()+"/cms/fileico/close.png' onclick='consulationList.viewNote(\""+ aData.objId+"\",\""+consulationList.currentTabId+"\")' title='未读'/>";
		}
		else // 已读
		{
			imgStr = "<img src='"+$('#initPath').val()+"/cms/fileico/open.png' title='已读'/>";
		}
		$(nRow).children(":first").html(imgStr);
		//查询完毕，每行回填事件方法
		$(nRow).append(consulationList.getOperationStr(aData.objId));
		return nRow;
	},
		'sAjaxSource':$('#initPath').val() + "/NoteController.do?method=list",
		params:{'type':'01','leaver':$('#consulationLeaver').val()}
	});
});

//添加操作字符串
consulationList.getOperationStr = function(objId){
	return '<td><a href="javascript:void(0);" onclick="consulationList.viewNote(\''+objId+'\',\''+consulationList.currentTabId+'\');return false;"><span>查看<span></a> <a href="javascript:void(0);" onclick="consulationList.remove(\''+objId+'\');return false;"><span>删除<span></a></td>';
}

//新增咨询
$('#addConsulationButton').click(function(){
	$.epsDialog({
		title:'我要咨询',
		id:'consulationFormDialog',
		url:$("#initPath").val()+"/NoteController.do?method=toNoteForm&type=01",
		onClose:function(){consulationList.reload();}
	})
});

//查看
consulationList.viewNote = function(noteViewId,currentTabsId){
	$.epsDialog({
		title:'咨询查看',
		id:'viewNoteDialog',
		url:$('#initPath').val()+"/NoteController.do?method=toNoteView&objId="+noteViewId+"&currentTabsId="+currentTabsId,
		onOpen:function(){},
		afterload:function(){},
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
	consulationList.oTable1.fnDraw();
});