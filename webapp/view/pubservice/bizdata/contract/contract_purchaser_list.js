var ContractPurchaserList = {};
ContractPurchaserList.currentTabID = "tabs_submit";
//根据不同的操作，导向不同的页面
ContractPurchaserList.openOperatorPage=function(type,objId){
	if("detail" == type){
		$("#returnUrl").val($('#initPath').val()+"/view/pubservice/bizdata/contract/contract_purchaser_list.jsp");
		$('#conBody').loadPage($('#initPath').val()+"/AgContractController.do?method=toContractVeiw&objId="+objId);
	}else if("downFile" == type){
		//下载合同
		$.epsDialog({
	        title:'合同文件',
	        url:$("#initPath").val()+"/AttachmentController.do?defineSelf=contractFile&isSelect=yes&attachRelaId="+objId
		});
	}
}
$(document).ready(function(){
	//日期控件  
	  $('#contractBeginTime').epsDatepicker({ timeShow:false, picker: "" });
	  $('#contractEndTime').epsDatepicker({ timeShow:false, picker: "" });
	  $('#contractBeginTime').bind('click',function(){$('#contractBeginTime').val(new Date().DateAdd('y',1).Format('yyyy-MM-dd'));});
	  $('#contractEndTime').bind('click',function(){$('#contractEndTime').val(new Date().DateAdd('y',1).Format('yyyy-MM-dd'));});
	  //高级搜索
	$("#hightSearchSwitch").click(function(){
		$(".conSearch .hightSearch").toggle("slow");
		$(this).toggleClass("collapsable");
		$(".operationBtnDiv").toggleClass("hightSearchBtn");
	});
	//加载tab页
	var $tabs = $('#epsTabs').tabs({}); 
	//tabs的点击事件
	$("li a.refreshData").click(function(){
		ContractPurchaserList.currentTabID = $(this).attr("id");
		if ("tabs_submit"==ContractPurchaserList.currentTabID) {
			$(ContractPurchaserList.oTable.dataTableSettings).attr("params", {useStatus:"00",buyerConfirmStatus:"00",supplierConfirmStatus:"00","caseId":"createUser.objId"});
		} else if ("tabs_todo"==ContractPurchaserList.currentTabID) {
			$(ContractPurchaserList.oTable.dataTableSettings).attr("params", {useStatus:"00",buyerConfirmStatus:"00",supplierConfirmStatus:"01","caseId":"buyer.objId"});
		} else if("tabs_notpass"==ContractPurchaserList.currentTabID){
			$(ContractPurchaserList.oTable.dataTableSettings).attr("params", {useStatus:"00",buyerConfirmStatus:"00",supplierConfirmStatus:"02","caseId":"buyerConfirmUser.objId"});
		}else if("tabs_done"==ContractPurchaserList.currentTabID){
			$(ContractPurchaserList.oTable.dataTableSettings).attr("params", {useStatus:"01",buyerConfirmStatus:"01",supplierConfirmStatus:"01","caseId":"buyerConfirmUser.objId"});
		} else if("tabs_tocancel"==ContractPurchaserList.currentTabID){
			$(ContractPurchaserList.oTable.dataTableSettings).attr("params", {useStatus:"01",buyerConfirmStatus:"00",supplierConfirmStatus:"01","caseId":"buyer.objId"});
		} else if("tabs_cancel"==ContractPurchaserList.currentTabID){
			$(ContractPurchaserList.oTable.dataTableSettings).attr("params", {useStatus:"02",buyerConfirmStatus:"01",supplierConfirmStatus:"01","caseId":"buyerConfirmUser.objId"});
		}
		ContractPurchaserList.oTable.fnDraw();
	})
	//加载合同列表
	ContractPurchaserList.oTable = $('#contractManageList').dataTable({
		'singleSelect' : true,//(false表示可以多选)
		'checkbox' : false,
		'queryColumns' : 'contractNo,contractName,supplierName,contractSignedTime,contractFile',
		'hiddenColumns':'contractFile',
		'fnInitComplete' : function(oSettings) {//表格初始化完毕、未开始查询之前的方法
		},
		'fnDrawCallback' : function(oSettings) {
			ContractPurchaserList.oTable.oSettings = oSettings;
		},
		'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
			//添加操作按钮
			$(nRow).find('td[name=contractFile]').html('<a href="javascript:void(0);" onclick="ContractPurchaserList.openOperatorPage(\'downFile\',\''+aData.contractFile+'\');return false;">下载合同文件</a>'); // 合同文件作为超链
			$(nRow).append('<td  align="center"><a href="javascript:void(0);" onclick="ContractPurchaserList.openOperatorPage(\'detail\',\''+aData.objId+'\');return false;">查看</a></td>')
			return nRow;
		},
		"sAjaxSource" : $('#initPath').val()+ "/AgContractController.do?method=list&type=buyer",
		"params":{useStatus:"00",buyerConfirmStatus:"00",supplierConfirmStatus:"00","caseId":"createUser.objId"},
		'searchZone':'ContractManageSearchForm'
	});
	// 查询
	$("#query").click(function() {		
		ContractPurchaserList.oTable.fnDraw();
	})
})