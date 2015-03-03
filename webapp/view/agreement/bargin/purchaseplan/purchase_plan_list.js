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

//新增
ProcurementTask.newPlan = function(){
	$("#conBody").loadPage($("#initPath").val()+"/ProcurementtaskController.do?method=toCreateOrUpdateView");
}


//导入计划
ProcurementTask.importPlanImport = function(){
	if (null == $('#importPlan').val() || null == $('#importPlan').val() || $('#importPlan').val().toString().length<1) {
		alert('请选择采购计划XML或EXCEL文件');
		return false;
	}
	var filePath = $('#importPlan').val().replace(/.+\\([^\\]+)/,'$1');
	var i = filePath.lastIndexOf('.');        //从右边开始找第一个'.'
	var len = filePath.length;                //取得总长度
	var str = filePath.substring(len,i+1);    //取得后缀名
	var exName = "XML,XLS,XLSX";       //允许的后缀名
	var k = exName.indexOf(str.toUpperCase());//转成大写后判断
	if(k==-1) {
	  alert("上传文件错误！只能上传"+exName);
	  return false;
	}
	$('#importPlanForm').ajaxSubmit({
		url:$('#initPath').val()+'/ProcurementtaskController.do?method=importPlan',
		dataType:'json',
		success:function(json){
			alert( ascii2native(json.result) );
			ProcurementTask.oTable.fnDraw();
		}
	});
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

//设置返回路径
ProcurementTask.setBackUrl = function(){
	$("#returnUrl").val($("#initPath").val()+"/ProcurementtaskController.do?method=toProcurementtaskList");
}

$(document).ready(function(){
	
	//设置返回路径
	ProcurementTask.setBackUrl();
	
	//加载tabs
	//var $tabs = $('#epsTabs').tabs({}); 
	
	//tab切换事件
//	$('#epsTabs').bind('tabsselect', function(event, ui) {
//		if(ui.index==0){
//			$(ProcurementTask.oTable.dataTableSettings).attr('params',{'useStatus':'00'});
//		}else if(ui.index==1){
//			$(ProcurementTask.oTable.dataTableSettings).attr('params',{'useStatus':'01','finTotal':0,'finTotal_op':'!='});
//		}else{
//			$(ProcurementTask.oTable.dataTableSettings).attr('params',{'useStatus':'01','finTotal':0});
//		}
//		ProcurementTask.oTable.fnDraw();
//	});
	
	//开始时间
    $("#startDate").epsDatepicker({applyRule: endRule });  //增加结束时间的规则
    //结束时间
    $("#endDate").epsDatepicker({applyRule: startRule });  //增加开始时间的规则
 
	ProcurementTask.oTable=$('#procurementTask').dataTable( {
		//根据采购人过滤
		'params':{},
		'singleSelect':true,
		'checkbox':false,
		'queryColumns':'taskNo,categoryNames,sumTotal,sumQty,finGoodSqty,periodType,createTime',
		'hiddenColumns':'useStatus,periodValue',
		'fnInitComplete':function(oSettings) {
		},
		'fnDrawCallback':function(oSettings) {	
		},
		'fnRowCallback': function( nRow, aData, iDisplayIndex ) {//查询完毕每行的回填事件	
			//类型
			if(aData.periodType=='year'){
				$(nRow).find("td:eq(5)").html(aData.periodValue+"年度采购计划");
			}else if(aData.periodType=='month'){
				$(nRow).find("td:eq(5)").html(aData.periodValue.split("-")[0]+"年"+aData.periodValue.split("-")[1]+"月采购计划");
			}else{
				$(nRow).find("td:eq(5)").html(aData.periodValue.split("-")[0]+"年"+aData.periodValue.split("-")[1]+"季度采购计划");
			}
		
			//添加按钮td
			$(nRow).append('<td class="operation"></td>');
		
			//修改
			if(aData.finGoodSqty==0){
				$(nRow).find("td:last").append('<a href="javascript:void(0);" name="modify"><span>修改</span></a>')
				$(nRow).find('a[name=modify]').click(function(){
					$("#conBody").loadPage($("#initPath").val()+"/ProcurementtaskController.do?method=toCreateOrUpdateView&objId="+aData.objId);
				});
			}
			
			//删除
			if(aData.finGoodSqty==0){
				$(nRow).find("td:last").append('<a href="javascript:void(0);" name="remove"><span>删除</span></a>')
				$(nRow).find('a[name=remove]').click(function(){
					if(confirm("确认删除？")){
						$.getJSON($('#initPath').val()+'/ProcurementtaskController.do?method=remove',
								{"objId":aData.objId},function(json){
									if(json.success){
										ProcurementTask.oTable.fnDraw();	//刷新
									}else{
										alert("操作失败！");
									}
								}
						);
					}
				});
			}
		
			//明细
			$(nRow).find("td:last").append('<a href="javascript:void(0);" name="detail"><span>明细</span></a>')
			$(nRow).find('a[name=detail]').click(function(){
				$('#conBody').loadPage($('#initPath').val()+"/ProcurementtaskController.do?method=toPlanDetailView&objId="+aData.objId);	
			});
			return nRow;
		},
		'searchZone':'ProcurementTaskSearchForm',
		"sAjaxSource": $('#initPath').val()+'/ProcurementtaskController.do?method=list&order=createTime&order_flag=true'
	});
	                                            
	//查询点击事件
	$("#query").click(function(){
		ProcurementTask.queryOption();
	})
});	
