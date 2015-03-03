/***
 * 
 * 
 * create by yucy 2010.08.09
 * modify by yucy
 * 
 */

var catalogList={};  //定义文件全局变量 处理方法名重复问题
catalogList.oTable;	


$(document).ready(function(){
	
	//初始化tabs
	$('#epsTabs').tabs({}); 
	
	//切换列表
	$('#epsTabs').bind('tabsselect', function(event, ui) {
		if(ui.index==0){				//新建
			$("#newBtnDiv").show();
			$(catalogList.oTable.dataTableSettings).attr("params",{"useStatus":"00","auditStatus_op":"in","auditStatus":"00,03"});
		}else if(ui.index==1){			//通过
			$("#newBtnDiv").hide();
			$(catalogList.oTable.dataTableSettings).attr("params",{'useStatus':'01','auditStatus':'02'});
		}
		catalogList.oTable.fnDraw();
	});
	
	
	//加载列表
	catalogList.oTable=$('#catalogList').dataTable( {   
		'params':{"useStatus":"00","auditStatus_op":"in","auditStatus":"00,03"},
		'searchZone':'catalogSearchForm',
		'singleSelect':false,	
		'checkbox':true,		
		'queryColumns':'title,areaCode,areaName,year',
		'hiddenColumns':'useStatus,auditStatus',
		'alias':'title,areaCode,areaName,year',
		'fnRowCallback': function( nRow, aData, iDisplayIndex ) {
			//添加操作
			$(nRow).append('<td class="operation"></td>');
			
			//审核
			if(aData.useStatus=='00'||aData.auditStatus=='01'){
				//审核
				$(nRow).find('td:last').append('<a name="audit" title="审核" href="javascript:void(0);"><span>审核</span></a>');
				$(nRow).find('a[name=audit]').click(function(){
					$('#conBody').loadPage($('#initPath').val()+"/PurCatalogController.do?method=toAuditCatalogView&objId="+aData.objId);
				})
			}
			
			//查看
			if(aData.useStatus=='01'||aData.auditStatus=='02'){
				$(nRow).find('td:last').append('<a name="view" title="查看" href="javascript:void(0);"><span>查看</span></a>');
				$(nRow).find('a[name=view]').click(function(){
					
					//弹出层
					$.epsDialog({
						id:'catalogView',
				        title:'查看目录',
				        url:$('#initPath').val()+"/PurCatalogController.do?method=toCatalogView&objId="+aData.objId,
				        width: '800',
				        height: '550'
				    }); 
				})
			}
			
			return nRow;
		},
		"sAjaxSource": $('#initPath').val()+"/PurCatalogController.do?method=list"
	});
	
	//搜
	$("#catalogSearch").click(function(){
		catalogList.oTable.fnDraw();
	})
});