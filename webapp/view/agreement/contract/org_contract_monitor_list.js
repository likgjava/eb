/** 上级公司监控下级公司合同列表页面 */
var OrgContractMonitorList = {};
OrgContractMonitorList.tree;
OrgContractMonitorList.oTable;

//初始化组织机构树
OrgContractMonitorList.initOrgnizationTree = function(companyId){
	OrgContractMonitorList.tree = new dhtmlXTreeObject("orgnizationTreeGrid","100%","100%",0);
	OrgContractMonitorList.tree.setImagePath($('#initPath').val()+"/view/resource/plug-in/dhtmlxTree/imgs/");
	OrgContractMonitorList.tree.setOnClickHandler(OrgContractMonitorList.nodeClick);
	OrgContractMonitorList.tree.setXMLAutoLoading("OrganizationController.do?method=getTree&order=sort&action=listById&isOpen=0");
	OrgContractMonitorList.tree.loadXML($("#initPath").val()+"/OrganizationController.do?method=getTree&id=1&action=listById&order=sort&objId="+companyId,function(){
		OrgContractMonitorList.nodeClick(companyId);
		OrgContractMonitorList.tree.selectItem(companyId);
	});
}

//点击节点事件
OrgContractMonitorList.nodeClick = function(id){
	var nodeLevel = OrgContractMonitorList.tree.getLevel(id); //获取节点层级
	//显示上级公司的合同
	if(nodeLevel == 1){
		OrgContractMonitorList.getContractList($("#orgInfoId").val());
	}
	//显示下级公司的合同
	else{
		//通过companyId获取orgInfoId
		$.getJSON($("#initPath").val()+"/OrgInfoController.do?method=getObjectQuery", {"queryColumns":"objId", "company.objId":id}, function(json){
			if(json.success){
				OrgContractMonitorList.getContractList(json.result[0].objId);
			}
		});
	}
}

//获取合同列表数据
OrgContractMonitorList.getContractList = function(orgInfoId){
	if(!OrgContractMonitorList.oTable) {
		OrgContractMonitorList.oTable=$('#contractList').dataTable( {
			'singleSelect':true,
			'checkbox':false,
			'queryColumns':'contractNo,contractName,supplier.orgName,total,contractSignedTime',
			'hiddenColumns':'contractFile,useStatus,buyerConfirmStatus,supplierConfirmStatus',
			'fnInitComplete':function(oSettings) {
			},
			'fnDrawCallback':function(oSettings) {	
				OrgContractMonitorList.oTable.oSettings=oSettings;
			},
			'fnRowCallback': function( nRow, aData, iDisplayIndex ) {
				//判断合同状态
				$(nRow).append('<td class="center">'+OrgContractMonitorList.checkContractStatus(aData.useStatus,aData.buyerConfirmStatus,aData.supplierConfirmStatus)+'</td>');
				//添加按钮
				var oper = '<td class="operation">';
				oper += '<a href="javascript:void(0);" onclick="OrgContractMonitorList.showOrderDetail(\''+aData.objId+'\');return false;">查看</a>';
				oper += '<a href="javascript:void(0);" onclick="OrgContractMonitorList.downloadContractFile(\''+aData.contractFile+'\');return false;">下载附件</a>';
				oper += '</td>';
				$(nRow).append(oper);
				return nRow;
			},
			'params':{'buyer.objId':orgInfoId},
			'searchZone':'monitorContractForm',
			"sAjaxSource": $('#initPath').val()+'/AgContractController.do?method=list&contractType=02&orgType=buyer'
		});
	}else {
		$(OrgContractMonitorList.oTable.dataTableSettings).attr("params",{'buyer.objId':orgInfoId});
		OrgContractMonitorList.oTable.fnDraw();
	}
}

//判断合同状态
OrgContractMonitorList.checkContractStatus = function(useStatus,buyerConfirmStatus,supplierConfirmStatus){
	var contractStatus = '';
	//待提交
	if(useStatus=='00' && buyerConfirmStatus=='00' && supplierConfirmStatus=='00'){
		contractStatus = '待提交';
	}
	//采购人待确认
	else if(useStatus=='00' && ((buyerConfirmStatus=='00' && supplierConfirmStatus=='01') || (buyerConfirmStatus=='01' && supplierConfirmStatus=='00'))){
		contractStatus = '待确认';
	}
	//被退回
	else if(useStatus=='00' && buyerConfirmStatus=='00' && supplierConfirmStatus=='02'){
		contractStatus = '被退回';
	}
	//待确认作废
	else if(useStatus=='01' && buyerConfirmStatus=='00' && supplierConfirmStatus=='01'){
		contractStatus = '待作废';
	}
	//已作废
	else if(useStatus=='02' && buyerConfirmStatus=='01' && supplierConfirmStatus=='01'){
		contractStatus = '已作废';
	}
	//已签订
	else if(useStatus=='01' && buyerConfirmStatus=='01' && supplierConfirmStatus=='01'){
		contractStatus = '已签订';
	}
	return contractStatus;
}

//查看合同信息
OrgContractMonitorList.showOrderDetail = function(contractId){
	$.epsDialog({
		title: '合同详情',
		url: $('#initPath').val()+"/AgContractController.do?method=toContractDetail&isDialog=1&forType=admin&objId="+contractId
	});
}

//下载合同文件
OrgContractMonitorList.downloadContractFile = function(contractFileId){
	$.epsDialog({
		title:'合同文件',
		url:$('#initPath').val()+'/view/agreement/contract/contract_file_div.jsp?attachRelaId='+contractFileId
	});
}

$(document).ready(function(){
	//初始化组织机构树
	OrgContractMonitorList.initOrgnizationTree($("#companyId").val());
});
