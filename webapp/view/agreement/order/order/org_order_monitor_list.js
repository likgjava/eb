/** 上级公司监控下级公司订单列表页面 */
var OrgOrderMonitorList = {};
OrgOrderMonitorList.tree;
OrgOrderMonitorList.oTable;

//初始化组织机构树
OrgOrderMonitorList.initOrgnizationTree = function(companyId){
	OrgOrderMonitorList.tree = new dhtmlXTreeObject("orgnizationTreeGrid","100%","100%",0);
	OrgOrderMonitorList.tree.setImagePath($('#initPath').val()+"/view/resource/plug-in/dhtmlxTree/imgs/");
	OrgOrderMonitorList.tree.setOnClickHandler(OrgOrderMonitorList.nodeClick);
	OrgOrderMonitorList.tree.setXMLAutoLoading("OrganizationController.do?method=getTree&order=sort&action=listById&isOpen=0");
	OrgOrderMonitorList.tree.loadXML($("#initPath").val()+"/OrganizationController.do?method=getTree&id=1&action=listById&order=sort&objId="+companyId,function(){
		OrgOrderMonitorList.nodeClick(companyId);
		OrgOrderMonitorList.tree.selectItem(companyId);
	});
}

//点击节点事件
OrgOrderMonitorList.nodeClick = function(id){
	var nodeLevel = OrgOrderMonitorList.tree.getLevel(id); //获取节点层级
	//显示上级公司的订单
	if(nodeLevel == 1){
		OrgOrderMonitorList.getOrderList($("#orgInfoId").val());
	}
	//显示下级公司的订单
	else{
		//通过companyId获取orgInfoId
		$.getJSON($("#initPath").val()+"/OrgInfoController.do?method=getObjectQuery", {"queryColumns":"objId", "company.objId":id}, function(json){
			if(json.success){
				OrgOrderMonitorList.getOrderList(json.result[0].objId);
			}
		});
	}
}

//获取订单列表数据
OrgOrderMonitorList.getOrderList = function(orgInfoId) {
	if(!OrgOrderMonitorList.oTable) {
		OrgOrderMonitorList.oTable=$('#orderList').dataTable( {
			'singleSelect':true,
			'checkbox':false,
			'queryColumns':'orderNo,categoryNames,goodsQty,goodsTotal,createTime',
			'hiddenColumns':'useStatus,buyerConfirmStatus,supplierConfirmStatus',
			'fnInitComplete':function(oSettings) {
			},
			'fnDrawCallback':function(oSettings) {	
				OrgOrderMonitorList.oTable.oSettings=oSettings;
			},
			'fnRowCallback': function( nRow, aData, iDisplayIndex ) {
				//判断订单状态
				$(nRow).append('<td class="center">'+OrgOrderMonitorList.checkOrderStatus(aData.useStatus,aData.buyerConfirmStatus,aData.supplierConfirmStatus)+'</td>');
				//添加按钮
				var oper = '<td class="operation"><a href="javascript:void(0);" onclick="OrgOrderMonitorList.showOrderDetail(\''+aData['objId']+'\',\''+aData['orderNo']+'\');return false;">查看</a></td>';
				$(nRow).append(oper);
				return nRow;
			},
			'params':{'buyer.objId':orgInfoId},
			'searchZone':'monitorOrderForm',
			"sAjaxSource": $('#initPath').val()+'/OrderController.do?method=list'
		});
	}else {
		$(OrgOrderMonitorList.oTable.dataTableSettings).attr("params",{'buyer.objId':orgInfoId});
		OrgOrderMonitorList.oTable.fnDraw();
	}
}

//判断订单状态
OrgOrderMonitorList.checkOrderStatus = function(useStatus,buyerConfirmStatus,supplierConfirmStatus){
	var orderStatus = '';
	//待提交
	if(useStatus=='00' && buyerConfirmStatus=='00' && supplierConfirmStatus=='00'){
		orderStatus = '待提交';
	}
	//待供应商确认
	else if(useStatus=='00' && buyerConfirmStatus=='01' && supplierConfirmStatus=='00'){
		orderStatus = '待确认';
	}
	//待供应商调整
	else if(useStatus=='00' && buyerConfirmStatus=='00' && supplierConfirmStatus=='02'){
		orderStatus = '待调整';
	}
	//已完成
	else if(useStatus=='01' && buyerConfirmStatus=='01' && supplierConfirmStatus=='01'){
		orderStatus = '已完成';
	}
	return orderStatus;
}

//查看订单信息
OrgOrderMonitorList.showOrderDetail = function(orderId,orderNo){
	$.epsDialog({
		title:'订单详情('+orderNo+')',
		url:$('#initPath').val()+'/OrderController.do?method=toOrderForm&navigate=divDetail&objId='+orderId
	}); 
}

$(document).ready(function(){
	//初始化组织机构树
	OrgOrderMonitorList.initOrgnizationTree($("#companyId").val());
});
