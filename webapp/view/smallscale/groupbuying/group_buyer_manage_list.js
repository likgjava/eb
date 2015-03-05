var GroupBuyerManageList = {};
GroupBuyerManageList.oTable;

//查看团购采购人详情
GroupBuyerManageList.showDetail = function(objId){
	$.epsDialog({
		id: 'gropBuyerDetail',
		title:'团购采购人详情',
		width: 700,
		height: 400,
		url:$('#initPath').val()+'/GroupBuyerController.do?method=toGroupBuyerDetailView&dialogId=gropBuyerDetail&objId='+objId
	})
}

//加载团购采购人信息列表
GroupBuyerManageList.getGroupBuyerList = function(){
	if(null==GroupBuyerManageList.oTable){
		GroupBuyerManageList.oTable = $('#groupBuyerManageList').dataTable({
			'queryColumns' : 'receiveName,mobilePhone,amount,payStatus',
			'alias' : 'receiveName,mobilePhone,amount,payStatusCN',
			'hiddenColumns': '',
			'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
				$(nRow).append('<td class="operation"><a href="javascript:void(0);" onclick="GroupBuyerManageList.showDetail(\''+aData.objId+'\');return false;">查看</a></td>')
				return nRow;
			},
			"sAjaxSource" : $('#initPath').val()+"/GroupBuyerController.do?method=list",
			"params":{},
			'searchZone':'groupBuyerSearchForm'
		});
	}else{
		GroupBuyerManageList.oTable.fnDraw();
	}
}

$(document).ready(function(){
	//加载列表
	GroupBuyerManageList.getGroupBuyerList();
})