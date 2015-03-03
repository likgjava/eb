/*
 * 协议供货，任务书列表页面
 * author: 梁潇静
 * mail: liangxj@gpcsoft.com
 */

//定义文件全局变量 处理方法名重复问题
var ProcurementTask={};

ProcurementTask.oTable;	

ProcurementTask.currentTabID="tabs_todo"; //当前Tab的ID

//刷新
ProcurementTask.reload=function(){
	ProcurementTask.oTable.fnDraw();
}

//拼装查询
ProcurementTask.queryOption = function(){
	
	var table = ProcurementTask.oTable;
	
	if($("#startDate").val().length > 0 && $("#endDate").val().length == 0){
		$(table.dataTableSettings).attr("params",{
			"createTime":$("#startDate").val(),
			"createTime_op":"ge"
		});
	}
	else if($("#endDate").val().length > 0 && $("#startDate").val().length == 0){
		$(table.dataTableSettings).attr("params",{
			"createTime":$("#startDate").val(),
			"createTime_op":"le"
		});
	}
	else if($("#endDate").val().length > 0 && $("#startDate").val().length > 0){
		$(table.dataTableSettings).attr("params",{
			"createTime":$("#startDate").val()+","+$("#endDate").val(),
			"createTime_op":"bt"
		});
	}
	table.fnDraw();
}

$(document).ready(function(){
	
	//加载tabs
	var $tabs = $('#epsTabs').tabs({}); 
	
	//tab切换事件
	$('#epsTabs').bind('tabsselect', function(event, ui) {
		if(ui.index==0){
			$(ProcurementTask.oTable.dataTableSettings).attr('params',{'finTotal':0,'finTotal_op':'!='});
		}else{
			$(ProcurementTask.oTable.dataTableSettings).attr('params',{'finTotal':0});
		}
		ProcurementTask.oTable.fnDraw();
	});
	
	//开始时间
    $("#startDate").epsDatepicker({applyRule: endRule });  //增加结束时间的规则
    //结束时间
    $("#endDate").epsDatepicker({applyRule: startRule });  //增加开始时间的规则
 
    
    
    
	ProcurementTask.oTable=$('#procurementTask').dataTable( {
		//根据采购人过滤
		'params':{'finTotal':0,'finTotal_op':'!='},
		'singleSelect':true,
		'checkbox':false,
		'queryColumns':'taskNo,categoryNames,sumTotal,sumQty,finGoodSqty,finTotal,createTime',
		'hiddenColumns':'',
		'fnInitComplete':function(oSettings) {
		},
		'fnDrawCallback':function(oSettings) {	
		},
		'fnRowCallback': function( nRow, aData, iDisplayIndex ) {//查询完毕每行的回填事件	
			//添加按钮
			$(nRow).append('<td name="detail"><a href="javascript:void(0);" type="alink"><span>明细</span></a></td>')
			//按钮事件
			$(nRow).find('a').click(function(){
				$('#conBody').loadPage($('#initPath').val()+"/view/agreement/order/task/procurement_task_form.jsp?objId="+aData.objId);	
			});
			return nRow;
		},
		'searchZone':'ProcurementTaskSearchForm',
		"sAjaxSource": $('#initPath').val()+'/ProcurementtaskController.do?method=list&useStatus=01'
	});
	
	//查询点击事件
	$("#query").click(function(){
		ProcurementTask.queryOption();
	})

});	
