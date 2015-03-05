
//定义文件全局变量 处理方法名重复问题
var OrderPurchaserList={};
OrderPurchaserList.oTable1;	
OrderPurchaserList.oTable2;	
OrderPurchaserList.oTable3;	
OrderPurchaserList.oTable4;
OrderPurchaserList.currentTabID="tabs_toSubmit"; //当前Tab的ID

// 生成列表中每条数据的操作：根据Tab不同，生成“取消、确定”和“查看”超链接
OrderPurchaserList.getOperatorStr=function(objId){	
	if ("tabs_toSubmit"==OrderPurchaserList.currentTabID) {
		return '<td><a href="javascript:void(0);" onclick="OrderPurchaserList.openExchangePage(\'edit_toSubmit\',\''+objId+'\');return false;"><span>查看详情</span></a></td>';
	} 
	else if ("tabs_toSure"==OrderPurchaserList.currentTabID) {
		return '<td><a href="javascript:void(0);" onclick="OrderPurchaserList.openCashPage(\'view_toSure\',\''+objId+'\');return false;"><span>查看详情</span></a></td>';
	} 
	else if("tabs_toAdjust"==OrderPurchaserList.currentTabID){
		return '<td><a href="javascript:void(0);" onclick="OrderPurchaserList.openDealPage(\'adjust_toAdjust\',\''+objId+'\');return false;"><span>查看详情</span></a></td>';
	} 
	else if("tabs_done"==OrderPurchaserList.currentTabID){
		return '<td><a href="javascript:void(0);" onclick="OrderPurchaserList.openConsumePage(\'view_down\',\''+objId+'\');return false;"><span>查看详情</span></a></td>';
	}
}

//查看一条积分详细
OrderPurchaserList.openExchangePage=function(type,id) {
	var url =$('#initPath').val()+"/ExchangeController.do?method=queryExchangeById&objId="+id;
	$.epsDialog({
        title:'积分详情',
        url:url,
        width: '600',
        height: '350'
    });
}

//查看一条兑现积分详细
OrderPurchaserList.openCashPage=function(type,id) {
	var url =$('#initPath').val()+"/CashController.do?method=queryCashById&objId="+id;
	$.epsDialog({
        title:'兑现积分详情',
        url:url,
        width: '800',
        height: '430'
    });
}

//查看一条交易积分详细
OrderPurchaserList.openDealPage=function(type,id) {
	var url =$('#initPath').val()+"/DealController.do?method=queryDealById&objId="+id;
	$.epsDialog({
        title:'交易积分详情',
        url:url,
        width: '600',
        height: '350'
    });
}

//查看一条消费积分详细
OrderPurchaserList.openConsumePage=function(type,id) {
	var url =$('#initPath').val()+"/ConsumeController.do?method=queryConsumeById&objId="+id;
	$.epsDialog({
        title:'消费积分详情',
        url:url,
        width: '600',
        height: '350'
    });
}

OrderPurchaserList.down2=function(){
	//兑现积分
	OrderPurchaserList.oTable2=$('#OrderPurchaserList2').dataTable( {
		'singleSelect':true,//(false表示可以多选)
		'checkbox':false,//(默认为true)为true时左边会出现复选框,  没必要用单选框(因为有更好的表达方式)
		'queryColumns':'cashDate,cashNumber,cashMoney',//指定要查询的列
		'hiddenColumns':'userId',//隐藏查询不显示的列属性
		'fnInitComplete':function(oSettings) {
			 //表格初始化完毕、未开始查询之前的方法
		},
		'fnDrawCallback':function(oSettings) {	
			//OrderPurchaserList.oTable1.oSettings=oSettings;
		},
		'fnRowCallback': function( nRow, aData, iDisplayIndex ) {//查询完毕每行的回填事件	
			$(nRow).append(OrderPurchaserList.getOperatorStr(aData.objId))//添加操作连接
			return nRow;
		},
		'params':{"userIdes":"userIdes"},//一加载时引用 的条件
		'searchZone':'purchaserOrderForm',
		'sAjaxSource': $('#initPath').val()+"/CashController.do?method=list"
	});
}

OrderPurchaserList.down3=function(){
	//交易积分
	OrderPurchaserList.oTable3=$('#OrderPurchaserList3').dataTable( {
		'singleSelect':true,//(false表示可以多选)
		'checkbox':false,//(默认为true)为true时左边会出现复选框,  没必要用单选框(因为有更好的表达方式)
		'queryColumns':'dealDate,dealNumber,formUser.usName,toUser.usName',//指定要查询的列
		'hiddenColumns':'',//隐藏查询不显示的列属性
		'fnInitComplete':function(oSettings) {
			 //表格初始化完毕、未开始查询之前的方法
		},
		'fnDrawCallback':function(oSettings) {	
			//OrderPurchaserList.oTable1.oSettings=oSettings;
		},
		'fnRowCallback': function( nRow, aData, iDisplayIndex ) {//查询完毕每行的回填事件	
			$(nRow).append(OrderPurchaserList.getOperatorStr(aData.objId))//添加操作连接
			return nRow;
		},
		'params':{"userIdes":"userIdes"},//一加载时引用 的条件
		'searchZone':'purchaserOrderForm',
		'sAjaxSource': $('#initPath').val()+"/DealController.do?method=list"
	});
}

OrderPurchaserList.down4=function(){
	
	//消费积分
	OrderPurchaserList.oTable4=$('#OrderPurchaserList4').dataTable( {
		'singleSelect':true,//(false表示可以多选)
		'checkbox':false,//(默认为true)为true时左边会出现复选框,  没必要用单选框(因为有更好的表达方式)
		'queryColumns':'consumeType,consumeDate,consumeNumber',//指定要查询的列
		'alias':'consumeTypeCN,consumeDate,consumeNumber',
		'hiddenColumns':'',//隐藏查询不显示的列属性
		'fnInitComplete':function(oSettings) {
			 //表格初始化完毕、未开始查询之前的方法
		},
		'fnDrawCallback':function(oSettings) {	
			//OrderPurchaserList.oTable1.oSettings=oSettings;
		},
		'fnRowCallback': function( nRow, aData, iDisplayIndex ) {//查询完毕每行的回填事件	
			$(nRow).append(OrderPurchaserList.getOperatorStr(aData.objId))//添加操作连接
			return nRow;
		},
		'params':{"userIdes":"userIdes"},//一加载时引用 的条件
		'searchZone':'purchaserOrderForm',
		'sAjaxSource': $('#initPath').val()+"/ConsumeController.do?method=list"
	});
}

//操作按钮
OrderPurchaserList.getOperButton=function(){
	if("tabs_toSubmit"==OrderPurchaserList.currentTabID){   //积分管理
		$('#cashBtn').addClass("hidden");
		$('#dealBtn').addClass("hidden");
	}else if("tabs_toSure"==OrderPurchaserList.currentTabID){  //兑现积分
	    $('#cashBtn').removeClass("hidden");
	    $('#dealBtn').addClass("hidden");
	}else if("tabs_toAdjust"==OrderPurchaserList.currentTabID){         //交易积分
	    $('#dealBtn').removeClass("hidden");
	    $('#cashBtn').addClass("hidden"); 
	}else{       //消费积分
	    $('#cashBtn').addClass("hidden");
		$('#dealBtn').addClass("hidden");
	}
}



$(document).ready(function(){
	$('#schic').val("exchange");
	//加载tabs
	$('#epsTabs').tabs(); 
	
	//tabs的点击事件
	$("li a.refreshData").click(function(){
		OrderPurchaserList.currentTabID = $(this).attr("id");
		OrderPurchaserList.getOperButton();   //--------------
		//参数值
		var paramValue = "";
		if(OrderPurchaserList.currentTabID == "tabs_toSubmit"){//积分管理
			$('#schic').val("exchange");
			$(OrderPurchaserList.oTable1.dataTableSettings).attr('params', {"userIdes":"userIdes"});
			OrderPurchaserList.oTable1.fnDraw();
		}else if(OrderPurchaserList.currentTabID == "tabs_toSure"){//兑现积分
			if(!OrderPurchaserList.oTable2){  //如果页面表格没有画过
				OrderPurchaserList.down2();   //画表格
			}else{  //否刷新表格中的数据
			    $(OrderPurchaserList.oTable2.dataTableSettings).attr('params', {"userIdes":"userIdes"});
			    OrderPurchaserList.oTable2.fnDraw();
			}
		}else if(OrderPurchaserList.currentTabID == "tabs_toAdjust"){//交易积分
			if(!OrderPurchaserList.oTable3){  //如果页面表格没有画过
				OrderPurchaserList.down3();   //画表格
			}else{  //否刷新表格中的数据
			    $(OrderPurchaserList.oTable3.dataTableSettings).attr('params', {"userIdes":"userIdes"});
			    OrderPurchaserList.oTable3.fnDraw();
			}
		}else if(OrderPurchaserList.currentTabID == "tabs_done"){//消费积分
			if(!OrderPurchaserList.oTable4){  //如果页面表格没有画过
				OrderPurchaserList.down4();   //画表格
			}else{  //否刷新表格中的数据
			    $(OrderPurchaserList.oTable4.dataTableSettings).attr('params', {"userIdes":"userIdes"});
			    OrderPurchaserList.oTable4.fnDraw();
			}
		}
	})
	OrderPurchaserList.getOperButton();    //--------------
	//积分管理
	OrderPurchaserList.oTable1=$('#OrderPurchaserList1').dataTable( {
		'singleSelect':true,//(false表示可以多选)
		'checkbox':false,//(默认为true)为true时左边会出现复选框,  没必要用单选框(因为有更好的表达方式)
		'queryColumns':'exchangeType,exchangeNumber,exchangeMoney,obtainDate,valDate,currentStatus',//指定要查询的列
		'alias':'exchangeTypeCN,exchangeNumber,exchangeMoney,obtainDate,valDate,currentStatusCN',
		'hiddenColumns':'userId',//隐藏查询不显示的列属性
		'fnInitComplete':function(oSettings) {
			 //表格初始化完毕、未开始查询之前的方法
		},
		'fnDrawCallback':function(oSettings) {	
			OrderPurchaserList.oTable1.oSettings=oSettings;
		},
		//参数一：行元素，参数二：这条记录的数据 ，参数三：索引号
		'fnRowCallback': function( nRow, aData, iDisplayIndex ) {//查询完毕每行的回填事件	
			$(nRow).append(OrderPurchaserList.getOperatorStr(aData.objId))//添加操作连接
			return nRow;
		},
		'params':{"userIdes":"userIdes"},//一加载时引用 的条件
		'searchZone':'purchaserOrderForm',
		'sAjaxSource': $('#initPath').val()+"/ExchangeController.do?method=list"
	});
	
	//兑现积分
	$('#cashBtn').click(function(){
	     $('#conBody').loadPage($('#initPath').val()+"/CashController.do?method=toCash");
	});
	//交易积分
	$('#dealBtn').click(function(){
	     $('#conBody').loadPage($('#initPath').val()+"/DealController.do?method=toDeal");
	});
});
	
