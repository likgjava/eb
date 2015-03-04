
//定义文件全局变量 处理方法名重复问题
var OrderSupplierList={};

OrderSupplierList.oTable1;
OrderSupplierList.oTable2;

OrderSupplierList.currentTabID="tabs_toSure"; //当前Tab的ID

OrderSupplierList.getOperatorStr=function(objId){
	$('#conBody').loadPage($('#initPath').val()+"/OrderController.do?method=toOrderDetailView&objId="+objId);
}

OrderSupplierList.openOperatorPage=function(type,objId){
	$('#conBody').loadPage($('#initPath').val()+'/AgContractController.do?method=toContractVeiw&objId='+objId);
}
OrderSupplierList.down=function(){
	//已确认订单
	OrderSupplierList.oTable2=$('#RderSupplierList').dataTable( {
		'singleSelect':true,//(默认为false)为true时左边会出现复选框,  没必要用单选框(因为有更好的表达方式)
		'checkbox':false,//(默认为true)为true时左边会出现复选框,  没必要用单选框(因为有更好的表达方式)
		'queryColumns':'orderNo,categoryNames,buyerName,goodsQty,goodsTotal,createTime,contract.contractNo',//指定要查询的列
		'hiddenColumns':'contract.objId,buyerId',//隐藏查询不显示的列属性
		'fnInitComplete':function(oSettings) {
		},
		'fnDrawCallback':function(oSettings) {	
			OrderSupplierList.oTable2.oSettings=oSettings;//很重要，将当前的oSettings对象赋给当前table的oSettgins
		},
		'fnRowCallback': function( nRow, aData, iDisplayIndex ) {//查询完毕每行的回填事件	
			if(aData["contract.contractNo"]){
				$(nRow).find('td[name=contract.contractNo]').html('<a href="javascript:void(0);" onclick="OrderSupplierList.openOperatorPage(\'view_contract\',\''+aData["contract.objId"]+'\');return false;">'+aData["contract.contractNo"]+'</a>');//添加连接按钮
			}
			$(nRow).append('<td align="center"><a href="javascript:void(0);" onclick="OrderSupplierList.getOperatorStr(\''+aData.objId+'\');return false;">查看</a></td>');
			return nRow;
		},
		params:{"useStatus":"01","buyerConfirmStatus":"01","supplierConfirmStatus":"01","currentId":"supplierConfirmUser.objId"},
		"sAjaxSource": $('#initPath').val()+"/OrderController.do?method=list&type=supplier",
		'searchZone':'supplierOrderForm'
	});
}

//查询
OrderSupplierList.reload=function(){
	var table = OrderSupplierList.oTable1;
	var setting = OrderSupplierList.oTable1.dataTableSettings[0];
	if(OrderSupplierList.currentTabID == "tabs_done"){
		table = OrderSupplierList.oTable2;
		setting = OrderSupplierList.oTable2.dataTableSettings[1];
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
	
	//tabs的点击事件
	$("li a.refreshData").click(function(){
		OrderSupplierList.currentTabID = $(this).attr("id");
		//参数值
		var paramValue = "";
		if(OrderSupplierList.currentTabID == "tabs_toSure"){//待确认
			$(OrderSupplierList.oTable1.dataTableSettings).attr('params', {"useStatus":"00","buyerConfirmStatus":"01","supplierConfirmStatus":"00","currentId":"supplier.objId"});
			OrderSupplierList.oTable1.fnDraw();
		}else if(OrderSupplierList.currentTabID == "tabs_toOppositeAdjust"){//待对方调整订单
			$(OrderSupplierList.oTable1.dataTableSettings).attr('params', {"useStatus":"00","buyerConfirmStatus":"00","supplierConfirmStatus":"02","currentId":"supplierConfirmUser.objId"});
			OrderSupplierList.oTable1.fnDraw();
		}else if(OrderSupplierList.currentTabID == "tabs_done"){//已确认订单
			if(!OrderSupplierList.oTable2){
				OrderSupplierList.down();
			}
			else{
				$(OrderSupplierList.oTable2.dataTableSettings).attr('params', {"useStatus":"01","buyerConfirmStatus":"01","supplierConfirmStatus":"01","currentId":"supplierConfirmUser.objId"});
				OrderSupplierList.oTable2.fnDraw();
			}
		}
	})
	var endRule = null;
	var startRule = null;
	//开始时间
    $("#startDate").epsDatepicker({applyRule: endRule });  //增加开始时间的规则
    //结束时间
    $("#endDate").epsDatepicker({applyRule: startRule });  //增加结束时间的规则
	//待确认订单
	OrderSupplierList.oTable1=$('#OrderSupplierList').dataTable( {
		'singleSelect':true,//(false表示可以多选)
		'checkbox':false,//(默认为true)为true时左边会出现复选框,  没必要用单选框(因为有更好的表达方式)
		'queryColumns':'orderNo,categoryNames,buyerName,goodsQty,goodsTotal,createTime',//指定要查询的列
		'hiddenColumns':'',//隐藏查询不显示的列属性
		'fnInitComplete':function(oSettings) {
			 //表格初始化完毕、未开始查询之前的方法
		},
		
		'fnRowCallback': function( nRow, aData, iDisplayIndex ) {//查询完毕每行的回填事件	
			$(nRow).append('<td align="center"><a href="javascript:void(0);" onclick="OrderSupplierList.getOperatorStr(\''+aData.objId+'\')">查看</a></td>');
			return nRow;
		},
		
		"sAjaxSource": $('#initPath').val()+"/OrderController.do?method=list&type=supplier",
		params:{"useStatus":"00","buyerConfirmStatus":"01","supplierConfirmStatus":"00","currentId":"supplier.objId"},
		'searchZone':'supplierOrderForm'
	});
	//查询
	$("#query").click(function(){
		OrderSupplierList.reload();
	})
	$("#returnUrl").val($('#initPath').val()+"/view/pubservice/bizdata/order/order_supplier_list.jsp");
	$('#epsTabs').tabs(); 
	$('#epsTabs').tabs('select',0); 
});
	
