/* 站内信列表页面 */

var messageList={};
messageList.currentTabId = "tabs_toSystem";
messageList.oTable;
messageList.oTableOutbox;

//操作字符
messageList.getOperatorStr=function(objId){
	var operStr = '';
	operStr ='<td class="operation">'
	operStr += '<a  title="删除" href="javascript:void(0);" onclick="messageList.batchDelete(\''+objId+'\');return false;">删除</a>  ';
	operStr += '<a  title="查看"href="javascript:void(0);" onclick="messageList.toDetail(\''+objId+'\');return false;">查看</a>';
	operStr += '</td>';
	return operStr;
}

//增加
messageList.toAdd = function(type){
	$('#conBody').loadPage($('#initPath').val()+"/MessageController.do?method=toCreateMessageView&type="+type);
}

//批量删除
messageList.batchDelete = function(messageId){
	var messageIds = "";
	if(messageId != null){
		messageIds = messageId;
	}else{
		if(messageList.currentTabId == 'tabs_toOutbox'){
			messageIds = $("#OutboxList").dtSelects();
		}else{
			messageIds = $("#QualityList").dtSelects();
		}
	}
	if(messageIds.length <= 0){alert("请至少选择一行数据！");return;}
	
	if(confirm('确定删除？')){
		$.getJSON($("#initPath").val()+"/MessageController.do?method=remove",{"objId":messageIds.split(",")},function(json){
			if(json.success){
				$("#"+messageList.currentTabId).click();//刷新列表
			}else{
				alert("操作失败！");
			}
		});
	}
}

//查看站内信内容
messageList.toDetail=function(id){
	$.epsDialog({
		title:"站内信内容",
		width: 500,
		height: 300,
		url:$('#initPath').val()+'/MessageController.do?method=toMessageDetailView&objId='+id,
		onClose: function(){
			$("#"+messageList.currentTabId).click(); //刷新列表
			myDesktop.processMessage(); //刷新当前用户的未读的站内信数量
		}
	});
}

//取得站内信收件箱信息列表
messageList.getInboxList = function(index) {
	if(null==messageList.oTable){
		//管理员
		if($("#isManager").val()=='true'){
			messageList.oTable = $('#QualityList').dataTable({   
				'params':{'type':'00','sender':$("#currentUserId").val(),'sender_op':'!=','isSave':'true'},
				'searchZone':'messageSearchForm',
				'singleSelect':false,
				'checkbox':true,
				'queryColumns':'isRead,title,senderName,sendTime',
				'fnInitComplete':function(oSettings) {
				},
				'fnDrawCallback':function(oSettings) {
					messageList.oTable.oSettings=oSettings;
				},
				'fnRowCallback': function(nRow, aData, iDisplayIndex){
					var imgStr = "";
					//已读
					if(aData.isRead=='true' || aData.senderName==$("#username").val()){
						imgStr = "<img src='"+$('#initPath').val()+"/cms/fileico/open.png' title='已读'/>";
					}else{ //未读
						imgStr = "<img src='"+$('#initPath').val()+"/cms/fileico/close.png' title='未读'/>";
					}
					$(nRow).find('td[name=isRead]').html(imgStr);
					$(nRow).find('td[name=title]').html('<a href="javascript:void(0);" onclick="messageList.toDetail(\''+aData.objId+'\');return false;">'+aData.title+'</a>');
					$(nRow).append(messageList.getOperatorStr(aData.objId));
					return nRow;
				},
				"sAjaxSource": $('#initPath').val()+"/MessageController.do?method=list"
			});
		}
		//非管理员
		else{
			messageList.oTable = $('#QualityList').dataTable({
				'params':{'type':'00','receiver':$("#currentUserId").val(),'isSave':'false'},
				'searchZone':'messageSearchForm',
				'singleSelect':false,
				'checkbox':true,
				'queryColumns':'isRead,title,senderName,sendTime',
				'fnDrawCallback':function(oSettings) {
					messageList.oTable.oSettings=oSettings;
				},
				'fnRowCallback': function(nRow, aData, iDisplayIndex){
					var imgStr = "";
					if(aData.isRead == 'true'){ //已读
						imgStr = "<img src='"+$('#initPath').val()+"/cms/fileico/open.png' title='已读'/>";
					}else{ //未读
						imgStr = "<img src='"+$('#initPath').val()+"/cms/fileico/close.png' title='未读'/>";
					}
					$(nRow).find('td[name=isRead]').html(imgStr);
					$(nRow).find('td[name=title]').html('<a href="javascript:void(0);" onclick="messageList.toDetail(\''+aData.objId+'\');return false;">' +aData.title+'</a>');
					$(nRow).append(messageList.getOperatorStr(aData.objId));
					return nRow;
				},
				"sAjaxSource": $('#initPath').val()+"/MessageController.do?method=list"
			});
		}
	}else{
		//系统站内信
		if(index==0){
			//管理员
			if($("#isManager").val() == 'true'){
				$(messageList.oTable.dataTableSettings).attr("params",{'type':'00','sender':$("#currentUserId").val(),'sender_op':'!=','isSave':'true'});
			}else{ //非管理员
				$(messageList.oTable.dataTableSettings).attr("params",{'type':'00','receiver':$("#currentUserId").val(),'isSave':'false'});
			}
			messageList.oTable.fnDraw();
		}
		//私人站内信
		else if(index==1){
			$(messageList.oTable.dataTableSettings).attr("params",{'type':'01','receiver':$("#currentUserId").val(),'isSave':'false'});
			messageList.oTable.fnDraw();
		}
	}
}

//取得站内信发件箱信息列表
messageList.getOutboxList = function() {
	if(null==messageList.oTableOutbox){
		messageList.oTableOutbox = $('#OutboxList').dataTable({   
			'params':{'senderName':$("#username").val(),'isSave':'true'},
			'searchZone':'messageSearchForm',
			'singleSelect':false,	
			'checkbox':true,		
			'queryColumns':'title,receiverName,sendTime',
			'fnInitComplete':function(oSettings) {
			},
			'fnDrawCallback':function(oSettings) {
				messageList.oTableOutbox.oSettings=oSettings;
			},
			'fnRowCallback': function(nRow, aData, iDisplayIndex){
				var objIdStr = '"' + aData.objId+'"';
				$(nRow).find('td[name=title]').html('<a href="javascript:void(0);" onclick="messageList.toDetail(\''+aData.objId+'\')">'+aData.title+'</a>');
				$(nRow).append(messageList.getOperatorStr(aData.objId));
				return nRow;
			},
			"sAjaxSource": $('#initPath').val()+"/MessageController.do?method=list"
		});
	}else{
		$(messageList.oTableOutbox.dataTableSettings).attr('params', {'senderName':$("#username").val(),'isSave':'true'});
		messageList.oTableOutbox.fnDraw();
	}
}

$(document).ready(function(){
	//初始化tabs
	$('#epsTabs').tabs({}); 
	
	//站内信收件箱信息列表
	messageList.getInboxList();
	
	//切换列表
	$('#tabs_toSystem').click(function() {
		messageList.getInboxList(0);
		messageList.currentTabId = "tabs_toSystem";
	});
	$('#tabs_toUser').click(function() {
		messageList.getInboxList(1);
		messageList.currentTabId = "tabs_toUser";
	});
	$('#tabs_toOutbox').click(function() {
		messageList.getOutboxList();
		messageList.currentTabId = "tabs_toOutbox";
	});
	
	//新增系统站内信
	$("#addSystemMessage").click(function(){
		messageList.toAdd('00');
	});
	
	//新增私人站内信
	$("#addUserMessage").click(function(){
		messageList.toAdd('01');
	});
});