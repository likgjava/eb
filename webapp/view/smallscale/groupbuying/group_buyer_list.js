var GroupBuyerList = {};
GroupBuyerList.oTable;

//获取操作字符串
GroupBuyerList.getOperatorStr=function(objId,groupBuyingId,payStatus){
	var operatorHtml = "";
	operatorHtml += '<td class="operation">';
	operatorHtml += '<a title="查看" href="javascript:void(0);" onclick="GroupBuyerList.showGroupBuying(\''+groupBuyingId+'\')"><span>查看</span></a>';
	if(payStatus == '00'){
		operatorHtml += '<a title="网银支付" href="javascript:void(0);" onclick="GroupBuyerList.toPay(\''+objId+'\')"><span>支付</span></a>';
	}
	operatorHtml += '</td>';
	return operatorHtml;
}

//查看团购信息
GroupBuyerList.showGroupBuying = function(groupBuyingId){
	$.epsDialog({
		title:'团购详细信息',
		url:$('#initPath').val()+'/GroupBuyingController.do?method=toGroupBuyingDetailView&objId='+groupBuyingId,
		width: '800',
		height: '450'
	});
}

//网银支付
GroupBuyerList.toPay = function(objId){
	window.open($('#initPath').val()+'/GroupBuyingShowController.do?method=toGroupBuyingPayView&groupBuyerId='+objId);
	return false;
}

//加载团购信息列表
GroupBuyerList.getGroupBuyerList = function(){
	if(null==GroupBuyerList.oTable){
		GroupBuyerList.oTable = $('#groupBuyerList').dataTable({
			'queryColumns' : 'groupBuying.name,groupBuying.goods.productName,groupBuying.groupPrice,amount,payStatus',
			'alias' : 'groupBuying.name,groupBuying.goods.productName,groupBuying.groupPrice,amount,payStatusCN',
			'hiddenColumns': 'groupBuying.objId',
			'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
				$(nRow).append(GroupBuyerList.getOperatorStr(aData.objId,aData['groupBuying.objId'],aData.payStatus));
				return nRow;
			},
			"sAjaxSource" : $('#initPath').val()+"/GroupBuyerController.do?method=list",
			"params":{},
			'searchZone':'groupBuyerSearchForm'
		});
	}else{
		GroupBuyerList.oTable.fnDraw();
	}
}

$(document).ready(function(){
	//加载列表
	GroupBuyerList.getGroupBuyerList();
});