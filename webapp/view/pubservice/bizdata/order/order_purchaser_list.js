
//定义文件全局变量 处理方法名重复问题
var OrderPurchaserList={};

OrderPurchaserList.currentTabID="tabs_toSubmit"; //当前Tab的ID

OrderPurchaserList.getOperatorStr=function(objId){
	$('#conBody').loadPage($('#initPath').val()+"/OrderController.do?method=toOrderDetailView&objId="+objId);
}

OrderPurchaserList.openOperatorPage=function(type,objId){
	$('#conBody').loadPage($('#initPath').val()+'/AgContractController.do?method=toContractVeiw&objId='+objId);
}

OrderPurchaserList.down=function(){
	//已确认订单
	OrderPurchaserList.oTable2=$('#OrderPurchaserList2').dataTable( {
		'singleSelect':true,//(默认为false)为true时左边会出现复选框,  没必要用单选框(因为有更好的表达方式)
		'checkbox':false,//(默认为true)为true时左边会出现复选框,  没必要用单选框(因为有更好的表达方式)
		'queryColumns':'orderNo,categoryNames,supplierName,goodsQty,goodsTotal,createTime,contract.contractNo',//指定要查询的列
		'hiddenColumns':'contract.objId,supplierId',//隐藏查询不显示的列属性
		'fnInitComplete':function(oSettings) {
			 //表格初始化完毕、未开始查询之前的方法
		},
		'fnDrawCallback':function(oSettings) {
			OrderPurchaserList.oTable2.oSettings=oSettings;//很重要，将当前的oSettings对象赋给当前table的oSettgins
		},
		'fnRowCallback': function( nRow, aData, iDisplayIndex ) {//查询完毕每行的回填事件	
			if(aData["contract.contractNo"]){
				$(nRow).find('td[name=contract.contractNo]').html('<a href="javascript:void(0);" onclick="OrderPurchaserList.openOperatorPage(\'view_contract\',\''+aData["contract.objId"]+'\');return false;">'+aData["contract.contractNo"]+'</a>');//添加连接按钮
			}
			$(nRow).append('<td align="center"><a href="javascript:void(0);" onclick="OrderPurchaserList.getOperatorStr(\''+aData.objId+'\');return false;">查看</a></td>');
			return nRow;
		},
		'params':{useStatus:"01",buyerConfirmStatus:"01",supplierConfirmStatus:"01","currentId":'buyerConfirmUser.objId'},
		'searchZone':'purchaserOrderForm',
		'sAjaxSource': $('#initPath').val()+"/OrderController.do?method=list&type=buyer"
	});
}

//查询
OrderPurchaserList.reload=function(){
	var table = OrderPurchaserList.oTable1;
	var setting = OrderPurchaserList.oTable1.dataTableSettings[0]
	if(OrderPurchaserList.currentTabID == "tabs_done"){
		table = OrderPurchaserList.oTable2;
		setting = OrderPurchaserList.oTable2.dataTableSettings[1];
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
}

$(document).ready(function(){
	//加载tabs
	$('#epsTabs').tabs(); 
	
	//tabs的点击事件
	$("li a.refreshData").click(function(){
		OrderPurchaserList.currentTabID = $(this).attr("id");
		//参数值
		var paramValue = "";
		if(OrderPurchaserList.currentTabID == "tabs_toSubmit"){//待提交	
			$(OrderPurchaserList.oTable1.dataTableSettings).attr('params', {useStatus:"00",buyerConfirmStatus:'00',supplierConfirmStatus:'00','currentId':'createUser.objId'});
			OrderPurchaserList.oTable1.fnDraw();
		}else if(OrderPurchaserList.currentTabID == "tabs_toSure"){//待对方确认订单
			$(OrderPurchaserList.oTable1.dataTableSettings).attr('params', {useStatus:'00',buyerConfirmStatus:'01',supplierConfirmStatus:'00','currentId':'buyerConfirmUser.objId'});
			OrderPurchaserList.oTable1.fnDraw();
		}else if(OrderPurchaserList.currentTabID == "tabs_toAdjust"){//待调整订单
			$(OrderPurchaserList.oTable1.dataTableSettings).attr('params', {useStatus:'00',buyerConfirmStatus:'00',supplierConfirmStatus:'02','currentId':'buyerConfirmUser.objId'});
			OrderPurchaserList.oTable1.fnDraw();
		}else if(OrderPurchaserList.currentTabID == "tabs_done"){//已确认订单
			if(!OrderPurchaserList.oTable2){
				OrderPurchaserList.down();
			}
			else{
				$(OrderPurchaserList.oTable2.dataTableSettings).attr('params', {useStatus:'01',buyerConfirmStatus:'01',supplierConfirmStatus:'01','currentId':'buyerConfirmUser.objId'});
				OrderPurchaserList.oTable2.fnDraw();
			}
		}
	})
	var endRule = null;
	var startRule = null;
	
	//开始时间
    $("#startDate").epsDatepicker({applyRule: endRule });  //增加开始时间的规则
    //结束时间
    $("#endDate").epsDatepicker({applyRule: startRule });  //增加结束时间的规则
	
	//已确认订单
	OrderPurchaserList.oTable1=$('#OrderPurchaserList1').dataTable( {
		'singleSelect':true,//(false表示可以多选)
		'checkbox':false,//(默认为true)为true时左边会出现复选框,  没必要用单选框(因为有更好的表达方式)
		'queryColumns':'orderNo,categoryNames,supplierName,goodsQty,goodsTotal,createTime',//指定要查询的列
		'hiddenColumns':'',//隐藏查询不显示的列属性
		'fnInitComplete':function(oSettings) {
			 //表格初始化完毕、未开始查询之前的方法
		},
		'fnDrawCallback':function(oSettings) {	
			OrderPurchaserList.oTable1.oSettings=oSettings;
		},
		'fnRowCallback': function( nRow, aData, iDisplayIndex ) {//查询完毕每行的回填事件	
			$(nRow).append('<td align="center"><a href="javascript:void(0);" onclick="OrderPurchaserList.getOperatorStr(\''+aData.objId+'\')">查看</a></td>');
		return nRow;
		},
		
		'params':{useStatus:"00",buyerConfirmStatus:"00",supplierConfirmStatus:"00",'currentId':'createUser.objId'},
		'searchZone':'purchaserOrderForm',
		'sAjaxSource': $('#initPath').val()+"/OrderController.do?method=list&type=buyer"
	});
	
	//查询
	$("#query").click(function(){
		OrderPurchaserList.reload();
	})
	$("#returnUrl").val($('#initPath').val()+"/view/pubservice/bizdata/order/order_purchaser_list.jsp");
});
	
