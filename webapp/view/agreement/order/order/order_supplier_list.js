
//定义文件全局变量 处理方法名重复问题
var OrderSupplierList={};

OrderSupplierList.oTable1;
OrderSupplierList.oTable2;

OrderSupplierList.currentTabID="tabs_toSure"; //当前Tab的ID

// 生成列表中每条数据的操作：根据Tab不同，生成“取消、确定”和“查看”超链接
OrderSupplierList.getOperatorStr=function(objId){
	if ("tabs_toSure"==OrderSupplierList.currentTabID) {
		return '<td class="operation"><a href="javascript:void(0);" onclick="OrderSupplierList.openOperatorPage(\'confirm_toSure\',\''+objId+'\');return false;"><span>确认</span></a></td>';
	}
	else if("tabs_toOppositeAdjust"==OrderSupplierList.currentTabID){
		return '<td class="operation"><a href="javascript:void(0);" onclick="OrderSupplierList.openOperatorPage(\'view_toOppositeAdjust\',\''+objId+'\');return false;"><span>查看</span></a></td>';
	}
	else if("tabs_done"==OrderSupplierList.currentTabID){
		return '<td class="operation"><a href="javascript:void(0);" onclick="OrderSupplierList.openOperatorPage(\'view_down\',\''+objId+'\');return false;"><span>查看</span></a></td>';
	}
}

//根据不同的操作，导向不同的页面
OrderSupplierList.openOperatorPage=function(type,objId){
	if("confirm_toSure" == type){
		//小额交易的页面为供应商不可以修改价格
		$('#conBody').loadPage($('#initPath').val()+"/OrderController.do?method=toOrderForm&appType=XEJY&navigate=sform&objId="+objId);
	} 
	else if("cancel_toSure" == type){
		if(confirm('确定要取消该订单吗？')){
			$.getJSON($('#initPath').val()+'/OrderController.do?method=orderCancel',{objId:objId},function(json){
				if(json.result)
					alert(json.result);
				if(json.failure)
					return;
				alert("该订单已取消!");
				OrderSupplierList.oTable1.fnDraw();
			});
		}
	}
	else if("view_down" == type){
		$('#conBody').loadPage($('#initPath').val()+"/OrderController.do?method=toOrderForm&navigate=sdetail&type=draft_contract&objId="+objId);
	}
	else if("view_toOppositeAdjust" == type){
		$('#conBody').loadPage($('#initPath').val()+"/OrderController.do?method=toOrderForm&navigate=sdetail&objId="+objId);
	}
	else if("cancel_toOppositeAdjust" == type){
		if(confirm('确定要取消该订单吗？')){
			$.getJSON($('#initPath').val()+'/OrderController.do?method=orderCancel',{objId:objId},function(json){
				if(json.result)
					alert(json.result);
				if(json.failure)
					return;
				alert("该订单已取消!")
				OrderSupplierList.oTable1.fnDraw();
			});
		}
	}else if("view_contract" == type){
		$('#conBody').loadPage($('#initPath').val()+"/AgContractController.do?method=toContractDetail&objId="+objId);
	}
}

OrderSupplierList.down=function(){
	if(null==OrderSupplierList.oTable2){
		//已确认订单
		OrderSupplierList.oTable2=$('#RderSupplierList').dataTable( {
			'singleSelect':false,//(默认为false)为true时左边会出现复选框,  没必要用单选框(因为有更好的表达方式)
			'checkbox':true,//(默认为true)为true时左边会出现复选框,  没必要用单选框(因为有更好的表达方式)
			'queryColumns':'orderNo,categoryNames,buyer.orgName,goodsQty,goodsTotal,createTime,contract.contractNo',//指定要查询的列
			'hiddenColumns':'contract.objId,buyer.objId',//隐藏查询不显示的列属性
			'fnInitComplete':function(oSettings) {
			},
			'fnDrawCallback':function(oSettings) {	
				OrderSupplierList.oTable2.oSettings=oSettings;//很重要，将当前的oSettings对象赋给当前table的oSettgins
			},
			'fnRowCallback': function( nRow, aData, iDisplayIndex ) {//查询完毕每行的回填事件	
				if(aData["contract.contractNo"]){
					$(nRow).find('td[name=contract.contractNo]').html('<td><a href="OrderSupplierList.openOperatorPage(\'view_contract\',\''+aData["contract.objId"]+'\')">'+aData["contract.contractNo"]+'</a></td>');//添加连接按钮
					
					//已经起草合同的订单记录，让复选框失效
					$(nRow).find('td:first').find('input[type=checkbox]').attr('disabled', true);
				}
			$(nRow).append(OrderSupplierList.getOperatorStr(aData.objId))//添加操作连接
			return nRow;
			},
			params:{"useStatus":"01","buyerConfirmStatus":"01","supplierConfirmStatus":"01","tabsType":"done","orgType":"supplier"},
			"sAjaxSource": $('#initPath').val()+"/OrderController.do?method=list",
			'searchZone':'supplierOrderForm'
		});
		
		$("#startDate").val('');
		$("#endDate").val('');
	}else{
		$(OrderSupplierList.oTable2.dataTableSettings).attr('params', {"useStatus":"01","buyerConfirmStatus":"01","supplierConfirmStatus":"01","tabsType":"done","orgType":"supplier"});
		OrderSupplierList.reload();
	}
}

//查询
OrderSupplierList.reload=function(){
	var table = OrderSupplierList.oTable1;
	var setting = OrderSupplierList.oTable1.dataTableSettings[0]
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

$(document).ready(function(){
	//加载tabs
	var $tabs = $('#epsTabs').tabs({}); 
	
	//设定返回事件
	$("#returnUrl").val($('#initPath').val()+"/OrderController.do?method=toOrderSupplierList");

	//tabs的点击事件
	$("li a.refreshData").click(function(){
		OrderSupplierList.currentTabID = $(this).attr("id");
		//参数值
		var paramValue = "";
		
		if(OrderSupplierList.currentTabID == "tabs_toSure"){//待确认
			$(OrderSupplierList.oTable1.dataTableSettings).attr('params', {"useStatus":"00","buyerConfirmStatus":"01","supplierConfirmStatus":"00","tabsType":"toSure","orgType":"supplier"});
			OrderSupplierList.reload();
		}else if(OrderSupplierList.currentTabID == "tabs_toOppositeAdjust"){//待对方调整订单
			$(OrderSupplierList.oTable1.dataTableSettings).attr('params', {"useStatus":"00","buyerConfirmStatus":"00","supplierConfirmStatus":"02","tabsType":"toOppositeAdjust","orgType":"supplier"});
			OrderSupplierList.reload();
		}else if(OrderSupplierList.currentTabID == "tabs_done"){//已确认订单
			OrderSupplierList.down();
		}
	})
	
	//开始时间
    $("#startDate").epsDatepicker({applyRule: endRule });  //增加结束时间的规则
    //结束时间
    $("#endDate").epsDatepicker({applyRule: startRule });  //增加开始时间的规则
	
	//待确认订单
	OrderSupplierList.oTable1=$('#OrderSupplierList').dataTable( {
		'singleSelect':true,//(false表示可以多选)
		'checkbox':false,//(默认为true)为true时左边会出现复选框,  没必要用单选框(因为有更好的表达方式)
		'queryColumns':'orderNo,categoryNames,buyer.orgName,goodsQty,goodsTotal,createTime',//指定要查询的列
		'hiddenColumns':'',//隐藏查询不显示的列属性
		'fnInitComplete':function(oSettings) {
			 //表格初始化完毕、未开始查询之前的方法
		},
		'fnDrawCallback':function(oSettings) {	
		},
		'fnRowCallback': function( nRow, aData, iDisplayIndex ) {//查询完毕每行的回填事件	
			if(aData["contract.contractNo"]){
				$(nRow).find('td[name=contract.contractNo]').html('<td><a href="javascritp:OrderPurchaserList.openOperatorPage(\'view_contract\',\''+aData["contract.objId"]+'\')">'+aData["contract.contractNo"]+'</a></td>');//添加连接按钮
			}
			$(nRow).append(OrderSupplierList.getOperatorStr(aData.objId))//添加操作按钮
			return nRow;
		},
		params:{"useStatus":"00","buyerConfirmStatus":"01","supplierConfirmStatus":"00","tabsType":"toSure","orgType":"supplier"},
		"sAjaxSource": $('#initPath').val()+"/OrderController.do?method=list",
		'searchZone':'supplierOrderForm'
	});
	
	//查询
	$("#query").click(function(){
		OrderSupplierList.reload();
	})
	
	//过滤未起草合同
	$("#onlyNoContract").click(function(){
		//选中
		if($("#onlyNoContract").attr("checked")){
			$(OrderSupplierList.oTable2.dataTableSettings).attr('sAjaxSource',$('#initPath').val()+"/OrderController.do?method=list&contract.objId=null&contract.objId_op=is");
		}
		else{
			$(OrderSupplierList.oTable2.dataTableSettings).attr('sAjaxSource',$('#initPath').val()+"/OrderController.do?method=list");
		}
		OrderSupplierList.oTable2.fnDraw();
	})
	
	//起草合同
	$("#addContract").click(function(){
		var select = $("#RderSupplierList").dtSelects();
		
		if(select.length == 0){
			alert("请至少选中一个订单");return;
		}
		//过滤已有合同和不同供应商
		var temp = select.split(",");
		var isPass = true;
		var buyers = new Array();
		for(var i=0; i<temp.length; i++){
			var data = $("#RderSupplierList").dtGetRow(temp[i],OrderSupplierList.oTable2.oSettings);
			if(data["contract.contractNo"]){  //过滤已经有合同编号的
				alert("请选择未起草合同的订单！");
				isPass = false;break;
			}
			if(i == 0) {
				buyers[0] = data["buyer.objId"];
				buyers[1] = data["buyer.orgName"];
			}
			if($.inArray(data["buyer.objId"], buyers) == -1){
				alert("请选择同一采购人的订单！")
				isPass = false;break;
			}
		}
		if(isPass){
			$('#conBody').loadPage($('#initPath').val()+"/AgContractController.do?method=toContractDraft&forType=supplier&orderIds="+select+"&buyerId="+buyers[0]+"&buyerName="+buyers[1]);
		}
	})
});
	
