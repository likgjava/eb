var GroupBuyingList = {};
GroupBuyingList.oTable;
GroupBuyingList.currentTabID = "tabs_noStart";

//获取操作字符串
GroupBuyingList.getOperatorStr=function(objId,useStatus){
	var operatorHtml = "";
	operatorHtml += '<td class="operation">';
	operatorHtml += '<a title="查看" href="javascript:void(0);" onclick="GroupBuyingList.showGroupBuying(\''+objId+'\')"><span>查看</span></a>';
	operatorHtml += '<a title="查看团购人" href="javascript:void(0);" onclick="GroupBuyingList.showGroupBuyer(\''+objId+'\')"><span>查看团购人</span></a>';
	if(GroupBuyingList.currentTabID == 'tabs_noStart'){
		operatorHtml += '<a title="修改" href="javascript:void(0);" onclick="GroupBuyingList.updateGroupBuying(\''+objId+'\')">修改</a>';
		operatorHtml += '<a title="删除" href="javascript:void(0);" onclick="GroupBuyingList.deleteGroupBuying(\''+objId+'\')">删除</a>';
	}
	operatorHtml += '</td>';
	return operatorHtml;
}

//查看团购信息
GroupBuyingList.showGroupBuying = function(objId){
	$.epsDialog({
		title:'团购信息',
		url:$('#initPath').val()+'/GroupBuyingController.do?method=toGroupBuyingDetailView&objId='+objId,
		width: '800',
		height: '450'
	});
}

//查看团购采购人
GroupBuyingList.showGroupBuyer = function(objId){
	$.epsDialog({
		title:'团购人列表',
		url:$('#initPath').val()+'/GroupBuyerController.do?method=toGroupBuyerListView&groupBuyingId='+objId,
		width: '700',
		height: '400'
	});
}

//修改团购信息
GroupBuyingList.updateGroupBuying = function(objId){
	window.open($('#initPath').val()+"/GroupBuyingController.do?method=toCreateOrUpdateView&objId="+objId);
	return false;
}

//删除团购信息
GroupBuyingList.deleteGroupBuying = function(objId){
	if(confirm("确认删除吗？")){
		$.getJSON($('#initPath').val() + "/GroupBuyingController.do?method=remove&objId="+objId, function(json){
			if(json.failure){if(json.result)alert(json.result);return;}
			alert("删除成功！");
			GroupBuyingList.getGroupBuyingList(); //刷新列表
		});
	}
}

//加载团购信息列表
GroupBuyingList.getGroupBuyingList = function(){
	if(null==GroupBuyingList.oTable){
		GroupBuyingList.oTable = $('#groupBuyingList').dataTable({
			'queryColumns' : 'name,goods.productName,marketPrice,groupPrice,minNumber',
			'hiddenColumns': 'useStatus',
			'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
				$(nRow).append(GroupBuyingList.getOperatorStr(aData.objId,aData.useStatus));
				return nRow;
			},
			"sAjaxSource" : $('#initPath').val()+"/GroupBuyingController.do?method=list",
			"params":{'status':GroupBuyingList.currentTabID.replace("tabs_","")},
			'searchZone':'groupBuyingSearchForm'
		});
	}else{
		$(GroupBuyingList.oTable.dataTableSettings).attr("params",{'status':GroupBuyingList.currentTabID.replace("tabs_","")});
		GroupBuyingList.oTable.fnDraw();
	}
}

$(document).ready(function(){
	//加载tabs,绑定选中事件
	$('#epsTabs').tabs({
		select: function(event, ui) {
			GroupBuyingList.currentTabID = ui.tab.id;
			GroupBuyingList.getGroupBuyingList();
		}
	});
	
	//加载列表
	GroupBuyingList.getGroupBuyingList();
	
	//新增团购
	$('#addGroupBuying').click(function(){
		var url = $('#initPath').val()+"/GroupBuyingController.do?method=toCreateOrUpdateView";
		window.open(url);
		return false;
	});
})