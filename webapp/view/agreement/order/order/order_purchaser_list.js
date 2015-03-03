
//定义文件全局变量 处理方法名重复问题
var OrderPurchaserList={};
OrderPurchaserList.oTable1;	
OrderPurchaserList.oTable2;	

OrderPurchaserList.currentTabID="tabs_toSubmit"; //当前Tab的ID

// 生成列表中每条数据的操作：根据Tab不同，生成“取消、确定”和“查看”超链接
OrderPurchaserList.getOperatorStr=function(objId){	
	if ("tabs_toSubmit"==OrderPurchaserList.currentTabID) {
		return '<td class="operation"><a href="javascript:void(0);" onclick="OrderPurchaserList.openOperatorPage(\'edit_toSubmit\',\''+objId+'\');return false;"><span>编辑</span></a></td>';
	} 
	else if ("tabs_toSure"==OrderPurchaserList.currentTabID) {
		return '<td class="operation"><a href="javascript:void(0);" onclick="OrderPurchaserList.openOperatorPage(\'view_toSure\',\''+objId+'\');return false;"><span>查看</span></a></td>';
	} 
	else if("tabs_toAdjust"==OrderPurchaserList.currentTabID){
		return '<td class="operation"><a href="javascript:void(0);" onclick="OrderPurchaserList.openOperatorPage(\'adjust_toAdjust\',\''+objId+'\');return false;"><span>调整</span></a></td>';
	} 
	else if("tabs_done"==OrderPurchaserList.currentTabID){
		return '<td class="operation"><a href="javascript:void(0);" onclick="OrderPurchaserList.openOperatorPage(\'view_down\',\''+objId+'\');return false;"><span>查看</span></a></td>';
	}
}

//根据不同的操作，导向不同的页面
OrderPurchaserList.openOperatorPage=function(type,objId){
	$("#returnUrl").val($("#initPath").val()+"/OrderController.do?method=toOrderBuyerList&appType=XYGH");
	if("view_toSure" == type){
		$('#conBody').loadPage($('#initPath').val()+"/OrderController.do?method=toOrderForm&appType=XYGH&navigate=bdetail&type=confirm_already&objId="+objId);
	} 
	else if("cancel_toSure" == type){
		if(confirm('确定要取消该订单吗？')){
			OrderPurchaserList.cancel(objId);
		}
	}
	else if("edit_toSubmit" == type){
		$('#conBody').loadPage($('#initPath').val()+"/OrderController.do?method=toOrderForm&appType=XYGH&type=edit_toSubmit&objId="+objId);
	}
	else if("submit_toSubmit" == type){
		if(confirm('确定要提交该订单吗？')){
			OrderPurchaserList.submitOrder(objId);
		}
	} 
	else if("adjust_toAdjust" == type){
		$('#conBody').loadPage($('#initPath').val()+"/OrderController.do?method=toOrderForm&appType=XYGH&type=adjust_toAdjust&objId="+objId);
	} 
	else if("view_down" == type){
		$('#conBody').loadPage($('#initPath').val()+"/OrderController.do?method=toOrderForm&navigate=bdetail&type=draft_contract&objId="+objId);
	}
	else if("view_contract" == type){
		$.epsDialog({ //by yucy 方式改为弹出
			id:'合同信息',
	        title:'合同编号：'+$("#contractNo").text(),
			url:$('#initPath').val()+"/AgContractController.do?method=toContractDetail&forType=buyer&objId="+objId,
			width: '800',
			height: '550'
		}); 
	}
}

OrderPurchaserList.down=function(){
	//已确认订单
	OrderPurchaserList.oTable2=$('#OrderPurchaserList2').dataTable( {
		'singleSelect':false,//(默认为false)为true时左边会出现复选框,  没必要用单选框(因为有更好的表达方式)
		'checkbox':true,//(默认为true)为true时左边会出现复选框,  没必要用单选框(因为有更好的表达方式)
		'queryColumns':'orderNo,categoryNames,supplier.orgName,goodsQty,goodsTotal,createTime,contract.contractNo',//指定要查询的列
		'hiddenColumns':'contract.objId,supplier.objId',//隐藏查询不显示的列属性
		'fnInitComplete':function(oSettings) {
			 //表格初始化完毕、未开始查询之前的方法
		},
		'fnDrawCallback':function(oSettings) {
			OrderPurchaserList.oTable2.oSettings=oSettings;//很重要，将当前的oSettings对象赋给当前table的oSettgins
		},
		'fnRowCallback': function( nRow, aData, iDisplayIndex ) {//查询完毕每行的回填事件	
			if(aData["contract.contractNo"]){
				$(nRow).find('td[name=contract.contractNo]').html('<td><a href="javascript:void(0);" onclick="OrderPurchaserList.openOperatorPage(\'view_contract\',\''+aData["contract.objId"]+'\');return false;">'+aData["contract.contractNo"]+'</a></td>');//添加连接按钮
				
				//已经起草合同的订单记录，让复选框失效
				$(nRow).find('td:first').find('input[type=checkbox]').attr('disabled', true);
			}
			$(nRow).append(OrderPurchaserList.getOperatorStr(aData.objId))//添加操作连接
			return nRow;
		},
		'params':{useStatus:"01",buyerConfirmStatus:"01",supplierConfirmStatus:"01","tabsType":"toSupdone","orgType":"buyer"},
		'searchZone':'purchaserOrderForm',
		'sAjaxSource': $('#initPath').val()+"/OrderController.do?method=list"
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
				$.extend(setting.params,{"createTime":$("#endDate").val(),"createTime_op":"le"}));
	}
	else if($("#endDate").val().length > 0 && $("#startDate").val().length > 0){
		$(table.dataTableSettings).attr('params',
				$.extend(setting.params,{"createTime":$("#startDate").val()+","+$("#endDate").val(),"createTime_op":"bt"}));
	}else{
		$(table.dataTableSettings).attr('params', $.extend(setting.params,{"createTime":"","createTime_op":""}));
	}
	table.fnDraw();
}

//提交订单
OrderPurchaserList.submitOrder=function(orderId){
	$.ajax({
		url: $("#initPath").val()+"/OrderController.do?method=saveOrder",
		type:"POST",
		data:{objId:orderId,buyerConfirmStatus:"01"},
		dataType:'json',
		success:function(json){
			if(json.result){
				alert(json.result,{inco:'info'});
			}
			if(json.failure){
				return;
			}
			alert("已将该订单提交给乙方，请等待乙方确认！")
			OrderPurchaserList.oTable1.fnDraw();
		},
		error:function(msg){
			alert('操作失败');
		}
	})
}

//取消订单
OrderPurchaserList.cancel=function(orderId){
	var table = OrderPurchaserList.oTable1;
	var setting = OrderPurchaserList.oTable1.dataTableSettings[0];
	                                                           
	var data={"objId":orderId};
	$.getJSON($('#initPath').val()+'/OrderController.do?method=remove',data,function(json){
		if(json.failure)
			alert(json.result);return;
		if(json.failure)
			return;
		alert("已经成功取消订单！");	

		table.fnDraw();
	});
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
			$(OrderPurchaserList.oTable1.dataTableSettings).attr('params', {"useStatus":"00","buyerConfirmStatus":"00","supplierConfirmStatus":"00","tabsType":"toSubmit","orgType":"buyer"});
			OrderPurchaserList.reload();
		}else if(OrderPurchaserList.currentTabID == "tabs_toSure"){//待对方确认订单
			$(OrderPurchaserList.oTable1.dataTableSettings).attr('params', {useStatus:'00',buyerConfirmStatus:'01',supplierConfirmStatus:'00',"tabsType":"toSupSure","orgType":"buyer"});
			OrderPurchaserList.reload();
		}else if(OrderPurchaserList.currentTabID == "tabs_toAdjust"){//待调整订单
			$(OrderPurchaserList.oTable1.dataTableSettings).attr('params', {useStatus:'00',buyerConfirmStatus:'00',supplierConfirmStatus:'02',"tabsType":"toSupAdjust","orgType":"buyer"});
			OrderPurchaserList.reload();
		}else if(OrderPurchaserList.currentTabID == "tabs_done"){//已确认订单
			if(!OrderPurchaserList.oTable2){
				OrderPurchaserList.down();
				$("#startDate").val('');
				$("#endDate").val('');
			}
			else{
				$(OrderPurchaserList.oTable2.dataTableSettings).attr('params', {useStatus:'01',buyerConfirmStatus:'01',supplierConfirmStatus:'01',"tabsType":"toSupdone","orgType":"buyer"});
				OrderPurchaserList.reload();
			}
		}
	})
	
	//开始时间
    $("#startDate").epsDatepicker({applyRule: endRule });  //增加结束时间的规则
    //结束时间
    $("#endDate").epsDatepicker({applyRule: startRule });  //增加开始时间的规则
	
	/**
	 * 待提交订单
	 * 待对方确认订单
	 * 待调整订单
	 * 默认参数为00 00 00
	 */
	//已确认订单
	OrderPurchaserList.oTable1=$('#OrderPurchaserList1').dataTable( {
		'singleSelect':true,//(false表示可以多选)
		'checkbox':false,//(默认为true)为true时左边会出现复选框,  没必要用单选框(因为有更好的表达方式)
		'queryColumns':'orderNo,categoryNames,supplier.orgName,goodsQty,goodsTotal,createTime',//指定要查询的列
		'hiddenColumns':'',//隐藏查询不显示的列属性
		'fnInitComplete':function(oSettings) {
			 //表格初始化完毕、未开始查询之前的方法
		},
		'fnDrawCallback':function(oSettings) {	
			OrderPurchaserList.oTable1.oSettings=oSettings;
		},
		'fnRowCallback': function( nRow, aData, iDisplayIndex ) {//查询完毕每行的回填事件	
			$(nRow).append(OrderPurchaserList.getOperatorStr(aData.objId))//添加操作连接
			return nRow;
		},
		'params':{useStatus:"00",buyerConfirmStatus:"00",supplierConfirmStatus:"00","tabsType":"toSubmit","orgType":"buyer"},
		'searchZone':'purchaserOrderForm',
		'sAjaxSource': $('#initPath').val()+"/OrderController.do?method=list"
	});
	
	//查询
	$("#query").click(function(){
		OrderPurchaserList.reload();
	})
	
	//过滤未起草合同
	$("#onlyNoContract").click(function(){
		//选中
		if($("#onlyNoContract").attr("checked")){
			$(OrderPurchaserList.oTable2.dataTableSettings).attr('sAjaxSource',$('#initPath').val()+"/OrderController.do?method=list&contract.objId=null&contract.objId_op=is");
		}
		else{
			$(OrderPurchaserList.oTable2.dataTableSettings).attr('sAjaxSource',$('#initPath').val()+"/OrderController.do?method=list");
		}
		OrderPurchaserList.oTable2.fnDraw();
	})
	
	//起草合同
	$("#addContract").click(function(){
		
		
		var select = $("#OrderPurchaserList2").dtSelects();
		
		if(select.length == 0){
			alert("请至少选中一个订单");return;
		}
		//过滤已有合同和不同供应商
		var temp = select.split(",");
		var isPass = true;
		var suppliers = new Array();
		for(var i=0; i<temp.length; i++){
			var data = $("#OrderPurchaserList2").dtGetRow(temp[i],OrderPurchaserList.oTable2.oSettings);
			if(data["contract.contractNo"]){  //过滤已经有合同编号的
				alert("请选择未起草合同的订单！");
				isPass = false;break;
			}
			if(i == 0) {
				suppliers[0] = data["supplier.objId"];
				suppliers[1] = data["supplier.orgName"];
			}
			if($.inArray(data["supplier.objId"], suppliers) == -1){
				alert("请选择同一供应商的订单！")
				isPass = false;break;
			}
		}
		if(isPass){
			$("#returnUrl").val($("#initPath").val()+"/OrderController.do?method=toOrderBuyerList&appType=XEJY" );
			$('#conBody').loadPage($('#initPath').val()+"/AgContractController.do?method=toContractDraft&forType=buyer&orderIds="+select+"&supplierId="+suppliers[0]+"&supplierName="+suppliers[1]);
		}
	})
	
	
});
	
