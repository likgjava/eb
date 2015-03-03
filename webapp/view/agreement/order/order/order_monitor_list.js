
//定义文件全局变量 处理方法名重复问题
var OrderMonitorList={};

OrderMonitorList.oTable1;
OrderMonitorList.oTable2;

OrderMonitorList.currentTabID="order_all"; //当前Tab的ID

OrderMonitorList.down = function(){
	//已确认订单
	OrderMonitorList.oTable2=$('#RderMonitorList').dataTable( {
		'singleSelect':true,//(默认为false)为true时左边会出现复选框,  没必要用单选框(因为有更好的表达方式)
		'checkbox':false,//(默认为true)为true时左边会出现复选框,  没必要用单选框(因为有更好的表达方式)
		'queryColumns':'orderNo,categoryNames,buyer.user.usName,goodsQty,goodsTotal,createTime,contract.contractNo',//指定要查询的列
		'hiddenColumns':'contract.objId,buyer.objId',//隐藏查询不显示的列属性
		'fnInitComplete':function(oSettings) {
		},
		'fnDrawCallback':function(oSettings) {	
			OrderMonitorList.oTable2.oSettings=oSettings;//很重要，将当前的oSettings对象赋给当前table的oSettgins
		},
		'fnRowCallback': function( nRow, aData, iDisplayIndex ) {//查询完毕每行的回填事件	
			if(aData["contract.contractNo"]){
				$(nRow).find('td[name=contract.contractNo]').html('<a href="javascript:void(0);" onclick="OrderMonitorList.openOperatorPage(\'view_contract\',\''+aData["contract.objId"]+'\');return false;">'+aData["contract.contractNo"]+'</a>');//添加连接按钮
			}
		$(nRow).append(OrderMonitorList.getOperatorStr(aData.objId));
			return nRow;
		},
		params:{"useStatus":"01","buyerConfirmStatus":"01","supplierConfirmStatus":"01"},
		"sAjaxSource": $('#initPath').val()+"/OrderController.do?method=list",
		'searchZone':'monitorOrderForm'
	});
}

//查询
$("#query").click(function(){
	OrderMonitorList.reload();
})


//查询
OrderMonitorList.reload=function(){
	
	var table = OrderMonitorList.oTable1;
	var setting = OrderMonitorList.oTable1.dataTableSettings[0];
	if(OrderMonitorList.currentTabID == "tabs_done"){
		table = OrderMonitorList.oTable2;
		setting = OrderMonitorList.oTable2.dataTableSettings[1];
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

//添加 操作
OrderMonitorList.getOperatorStr=function(objId){
		return '<td align="operation"><a href="javascript:void(0);" onclick="OrderMonitorList.openOperatorPage(\'view_toDone\',\''+objId+'\');return false;">查看</a></td>';
}

//根据type导向不同的页面
OrderMonitorList.openOperatorPage=function(type,objId){
	if("view_toDone" == type){//查看订单		
		$('#conBody').loadPage($('#initPath').val()+'/OrderController.do?method=toOrderForm&navigate=monitorDetail&type=draft_contract&objId='+objId+'&currentTabNum='+OrderMonitorList.currentTabID);
	}
	else if("view_contract" == type){//查看合同
		$("#returnUrl").val($("#initPath").val()+"/view/agreement/order/order/order_monitor_list.jsp?currentTabNum=tabs_done");
		$('#conBody').loadPage($('#initPath').val()+'/AgContractController.do?method=toContractDetail&forType=admin&objId='+objId);
	}
}

$(document).ready(function(){
	
	//开始时间
    $("#startDate").epsDatepicker();  //增加开始时间的规则
    //结束时间
    $("#endDate").epsDatepicker();  //增加结束时间的规则
	  
	//加载tabs
	$('#epsTabs').tabs(); 
	$('#epsTabs').tabs('select',0);
	
	//tabs的点击事件
	$("li a.refreshData").click(function(){
		
		OrderMonitorList.currentTabID = $(this).attr("id");
		
		if(OrderMonitorList.currentTabID == "tabs_toNeverSubmit"){//待采购人提交订单
			$(OrderMonitorList.oTable1.dataTableSettings).attr('params', {"useStatus":"00","buyerConfirmStatus":"00","supplierConfirmStatus":"00"});
			OrderMonitorList.oTable1.fnDraw();
		}else if(OrderMonitorList.currentTabID == "tabs_done"){//已确认订单
			if(!OrderMonitorList.oTable2){
				OrderMonitorList.down();
			}
			else{
				$(OrderMonitorList.oTable2.dataTableSettings).attr('params', {"useStatus":"01","buyerConfirmStatus":"01","supplierConfirmStatus":"01"});
				OrderMonitorList.oTable2.fnDraw();
			}
		}else if(OrderMonitorList.currentTabID == "buyer_adjust"){//待采购人调整订单
			$(OrderMonitorList.oTable1.dataTableSettings).attr('params', {"useStatus":"00","buyerConfirmStatus":"00","supplierConfirmStatus":"02"});
			OrderMonitorList.oTable1.fnDraw();
		}else if(OrderMonitorList.currentTabID == "supplier_toSure"){//待供应商确认订单
			$(OrderMonitorList.oTable1.dataTableSettings).attr('params', {"useStatus":"00","buyerConfirmStatus":"01","supplierConfirmStatus":"00"});
			OrderMonitorList.oTable1.fnDraw();
		}else if(OrderMonitorList.currentTabID == "order_all"){//全部订单
			$(OrderMonitorList.oTable1.dataTableSettings).attr('params',{});
			OrderMonitorList.oTable1.fnDraw();
		}else if(OrderMonitorList.currentTabID=="tabs_destroy"){//作废订单
			$(OrderMonitorList.oTable1.dataTableSettings).attr('params',{"useStatus":"02"});
			OrderMonitorList.oTable1.fnDraw();
		}
	})

	//全部订单
	OrderMonitorList.oTable1=$('#OrderMonitorList').dataTable( {
		'singleSelect':true,//(false表示可以多选)
		'checkbox':false,//(默认为true)为true时左边会出现复选框,  没必要用单选框(因为有更好的表达方式)
		'queryColumns':'orderNo,categoryNames,buyer.user.usName,goodsQty,goodsTotal,createTime',//指定要查询的列
		'hiddenColumns':'',//隐藏查询不显示的列属性
		'fnInitComplete':function(oSettings) {
			 //表格初始化完毕、未开始查询之前的方法
		},
		
		'fnRowCallback': function( nRow, aData, iDisplayIndex ) {//查询完毕每行的回填事件	
			$(nRow).append(OrderMonitorList.getOperatorStr(aData.objId));
		return nRow;
		},
		
		"sAjaxSource": $('#initPath').val()+"/OrderController.do?method=list",
		params:{},
		'searchZone':'monitorOrderForm'
	});
	
	//查看返回时跳转到原有页面
	OrderMonitorList.currentTabID = $('#currentTabNum').val();
	
	if(OrderMonitorList.currentTabID == ''){OrderMonitorList.currentTabID="order_all";}
	
	if('order_all' == OrderMonitorList.currentTabID){
		$('#order_all').click();
	}else if('tabs_toNeverSubmit' == OrderMonitorList.currentTabID){
		$('#tabs_toNeverSubmit').click();
	}else if('buyer_adjust' == OrderMonitorList.currentTabID){
		$('#buyer_adjust').click();
	}else if('supplier_toSure' == OrderMonitorList.currentTabID){
		$('#supplier_toSure').click();
	}else if('tabs_done' == OrderMonitorList.currentTabID){
		$('#tabs_done').click();
	}
});