
//定义文件全局变量 处理方法名重复问题
var OrderPurchaserList={};
OrderPurchaserList.oTable1;	
OrderPurchaserList.oTable2;	

OrderPurchaserList.currentTabID="tabs_toSubmit"; //当前Tab的ID

// 生成列表中每条数据的操作：根据Tab不同，生成“取消、确定”和“查看”超链接
OrderPurchaserList.getOperatorStr=function(objId,orderNo,goodsTotal,payStatus){	
	var operStr = '<td class="operation">';
	if ("tabs_toSubmit"==OrderPurchaserList.currentTabID) {
		operStr += '<a href="javascript:void(0);" onclick="OrderPurchaserList.openOperatorPage(\'edit_toSubmit\',\''+objId+'\');return false;"><span>编辑</span></a>';
	} 
	else if ("tabs_toSupSure"==OrderPurchaserList.currentTabID) {
		operStr += '<a href="javascript:void(0);" onclick="OrderPurchaserList.openOperatorPage(\'view_toSure\',\''+objId+'\');return false;"><span>查看</span></a>';
	} 
	else if("tabs_toSupAdjust"==OrderPurchaserList.currentTabID){
		operStr += '<a href="javascript:void(0);" onclick="OrderPurchaserList.openOperatorPage(\'adjust_toAdjust\',\''+objId+'\');return false;"><span>调整</span></a>';
	} 
	else if("tabs_toSupdone"==OrderPurchaserList.currentTabID){
		operStr += '<a href="javascript:void(0);" onclick="OrderPurchaserList.openOperatorPage(\'view_down\',\''+objId+'\');return false;"><span>查看</span></a>';
	}
	if(payStatus != '01'){
		operStr += '<a href="javascript:void(0);" onclick="OrderPurchaserList.toPayView(\''+objId+'\',\''+orderNo+'\','+goodsTotal+');return false;"><span>支付</span></a>';
	}
	operStr += '</td>';
	return operStr;
}

//根据不同的操作，导向不同的页面
OrderPurchaserList.openOperatorPage=function(type,objId){
	$("#returnUrl").val($("#initPath").val()+"/OrderController.do?method=toOrderBuyerList&appType=XEJY");
	if("view_toSure" == type){
		$('#conBody').loadPage($('#initPath').val()+"/OrderController.do?method=toOrderForm&appType=XEJY&navigate=bdetail&type=to_confirm&objId="+objId+"&appType=XEJY");
	} 
	else if("cancel_toSure" == type){
		if(confirm('确定要取消该订单吗？')){
			OrderPurchaserList.cancel(objId);
		}
	}
	else if("edit_toSubmit" == type){
		$('#conBody').loadPage($('#initPath').val()+"/OrderController.do?method=toOrderForm&appType=XEJY&type=edit_toSubmit&objId="+objId+"&appType=XEJY");
	}
	else if("submit_toSubmit" == type){
		if(confirm('确定要提交该订单吗？')){
			OrderPurchaserList.submitOrder(objId);
		}
	} 
	else if("adjust_toAdjust" == type){
		$('#conBody').loadPage($('#initPath').val()+"/OrderController.do?method=toOrderForm&appType=XEJY&type=adjust_toAdjust&objId="+objId+"&appType=XEJY");
	} 
	else if("view_down" == type){
		$('#conBody').loadPage($('#initPath').val()+"/OrderController.do?method=toOrderForm&appType=XEJY&navigate=bdetail&type=draft_contract&objId="+objId+"&appType=XEJY");
	}
	else if("view_contract" == type){
		$('#conBody').loadPage($('#initPath').val()+"/AgContractController.do?method=toContractDetail&appType=XEJY&objId="+objId);
	}
}

//网银支付
OrderPurchaserList.toPayView=function(orderId,orderCode,orderTotal){
	var bankUrl = $("#initPath").val()+"/OrderController.do?method=toOrderPayView&v_oid="+orderCode+"&v_amount="+orderTotal+"&orderId="+orderId;
	window.open(bankUrl);
}

//查看订单支付记录
OrderPurchaserList.showOrderPayRecord = function(pay_business_id) {
	var url = $('#initPath').val()+"/PayController.pay?method=toOrderPayRecordDetail&pay_business_id=" + pay_business_id;
	$.epsDialog({
        title:'订单支付记录',
        width:820,
        height:350,
        url:url
	});
}

//获取列表数据  已确定的订单的div使用的table是oTable2 ，其他tab使用的是oTable1
OrderPurchaserList.getTableList = function() {
	if(OrderPurchaserList.currentTabID != "tabs_toSupdone") {
		if(!OrderPurchaserList.oTable1) {
			OrderPurchaserList.oTable1=$('#OrderPurchaserList1').dataTable( {
				'singleSelect':true,//(false表示可以多选)
				'checkbox':false,//(默认为true)为true时左边会出现复选框,  没必要用单选框(因为有更好的表达方式)
				'queryColumns':'orderNo,categoryNames,supplier.orgName,goodsQty,goodsTotal,createTime',//指定要查询的列
				'hiddenColumns':'supplier.objId,payStatus',//隐藏查询不显示的列属性
				'fnInitComplete':function(oSettings) {
					 //表格初始化完毕、未开始查询之前的方法
				},
				'fnDrawCallback':function(oSettings) {	
					OrderPurchaserList.oTable1.oSettings=oSettings;
				},
				'fnRowCallback': function( nRow, aData, iDisplayIndex ) {//查询完毕每行的回填事件	
					
					//特殊处理
					if(aData.payStatus=='01') {//已支付
						var payHtml = '<a href="#" onclick="OrderPurchaserList.showOrderPayRecord(\''+aData.objId+'\')">【已支付】</a>'
						$(nRow).find("td[name=orderNo]").html(aData.orderNo+'<font color="green">'+payHtml+'</font>');
					}
					
					$(nRow).append(OrderPurchaserList.getOperatorStr(aData.objId,aData.orderNo,aData.goodsTotal,aData.payStatus))//添加操作连接
					return nRow;
				},
				'params':{"tabsType":OrderPurchaserList.currentTabID.replace("tabs_","")},
				'searchZone':'purchaserOrderForm',
				'sAjaxSource': $('#initPath').val()+"/OrderController.do?method=list&orgType=buyer"
			});
		} else {
			$(OrderPurchaserList.oTable1.dataTableSettings).attr('params', {"tabsType":OrderPurchaserList.currentTabID.replace("tabs_","")});
			OrderPurchaserList.oTable1.fnDraw();
		}
	}else {
		if(!OrderPurchaserList.oTable2) {
			OrderPurchaserList.oTable2=$('#OrderPurchaserList2').dataTable( {
				'singleSelect':false,//(默认为false)为true时左边会出现复选框,  没必要用单选框(因为有更好的表达方式)
				'checkbox':true,//(默认为true)为true时左边会出现复选框,  没必要用单选框(因为有更好的表达方式)
				'queryColumns':'orderNo,categoryNames,supplier.orgName,goodsQty,goodsTotal,createTime,contract.contractNo',//指定要查询的列
				'hiddenColumns':'contract.objId,supplier.objId,payStatus',//隐藏查询不显示的列属性
				'fnInitComplete':function(oSettings) {
					 //表格初始化完毕、未开始查询之前的方法
				},
				'fnDrawCallback':function(oSettings) {
					OrderPurchaserList.oTable2.oSettings=oSettings;//很重要，将当前的oSettings对象赋给当前table的oSettgins
				},
				'fnRowCallback': function( nRow, aData, iDisplayIndex ) {//查询完毕每行的回填事件	
					
					//特殊处理
					$(nRow).find("td[name=orderNo]").html(aData.orderNo+'<font color="'+ ( aData.payStatus=='01'?'green':'red' ) +'">【'+( aData.payStatus=='01'?'已支付':'未支付' )+'】</font>');
					
					if(aData["contract.contractNo"]){
						$(nRow).find('td[name=contract.contractNo]').html('<td><a href="javascript:OrderPurchaserList.openOperatorPage(\'view_contract\',\''+aData["contract.objId"]+'\')">'+aData["contract.contractNo"]+'</a></td>');//添加连接按钮
					}
					$(nRow).append(OrderPurchaserList.getOperatorStr(aData.objId,aData.orderNo,aData.goodsTotal,aData.payStatus))//添加操作连接
					return nRow;
				},
				'params':{"tabsType":OrderPurchaserList.currentTabID.replace("tabs_","")},
				'searchZone':'purchaserOrderForm',
				'sAjaxSource': $('#initPath').val()+"/OrderController.do?method=list&orgType=buyer"
			});
		}else {
			$(OrderPurchaserList.oTable2.dataTableSettings).attr('params', {"tabsType":OrderPurchaserList.currentTabID.replace("tabs_","")});
			OrderPurchaserList.oTable2.fnDraw();
		}
	}
}

//查询
OrderPurchaserList.reload=function(){
	var table = OrderPurchaserList.oTable1;
	var setting = OrderPurchaserList.oTable1.dataTableSettings[0]
	if(OrderPurchaserList.currentTabID == "tabs_toSupdone"){
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
	//加载tabs,绑定选中事件为加载列表
	var $tabs = $('#epsTabs').tabs({
		select: function(event, ui) {
			OrderPurchaserList.currentTabID = ui.tab.id;
			$("#currentTab").val(ui.index); //当前tab的index
			OrderPurchaserList.getTableList();
		}
	});
	//指定某一个tab被选中，默认值为0
	$tabs.tabs('select', parseInt($("#currentTab").val()));
	
	//tab无法触发第一个选中，所以需要手动加载一次
	if($("#currentTab").val() == "0") {
		OrderPurchaserList.getTableList();
	}
	
	//开始时间
    $("#startDate").epsDatepicker({applyRule: endRule });  //增加结束时间的规则
    //结束时间
    $("#endDate").epsDatepicker({applyRule: startRule });  //增加开始时间的规则
	
	//查询
	$("#query").click(function(){
		OrderPurchaserList.reload();
	})
	
	//过滤未起草合同
	$("#onlyNoContract").click(function(){
		//选中
		if($("#onlyNoContract").attr("checked")){
			$(OrderPurchaserList.oTable2.dataTableSettings).attr('sAjaxSource',$('#initPath').val()+"/OrderController.do?method=list&contract.objId=null&contract.objId_op=is&orgType=buyer&tabsType=toSupdone");
		}
		else{
			$(OrderPurchaserList.oTable2.dataTableSettings).attr('sAjaxSource',$('#initPath').val()+"/OrderController.do?method=list&orgType=buyer&tabsType=toSupdone");
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
				alert("请选择未起草合同的订单！");return;
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
			//设置返回按钮
			$("#returnUrl").val($("#initPath").val()+"/OrderController.do?method=toOrderBuyerList&appType=XEJY" );
			$('#conBody').loadPage($('#initPath').val()+"/AgContractController.do?method=toContractDraft&forType=buyer&orderIds="+select+"&supplierId="+suppliers[0]+"&supplierName="+suppliers[1]);
		}
	})
	
	
});
	
