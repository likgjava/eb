
//定义文件全局变量 处理方法名重复问题
var BargainPurchaserList={};

BargainPurchaserList.getOperatorStr=function(objId){
	$('#conBody').loadPage($('#initPath').val()+'/BargainController.do?method=toBargainDetailView&bargainId='+objId);
}

$(document).ready(function(){
	var $tabs=$('#epsTabs').tabs({}); 
	$("li a.refreshData").click(function(){
		BargainPurchaserList.currentTabID = $(this).attr("id");
		if(BargainPurchaserList.currentTabID == "tabs_tostart"){
			$(BargainPurchaserList.oTable.dataTableSettings).attr('params',{useStatus:"00"});
		}else if(BargainPurchaserList.currentTabID == "tabs_bargaining"){
			$(BargainPurchaserList.oTable.dataTableSettings).attr('params',{useStatus:"01"});
		}else if(BargainPurchaserList.currentTabID == "tabs_done"){
			$(BargainPurchaserList.oTable.dataTableSettings).attr('params',{useStatus:"02"});
		}
		BargainPurchaserList.oTable.fnDraw();
	});
	var startRule=null;
	var endRule=null;
    $("#startDate").epsDatepicker({applyRule: startRule });  //增加结束时间的规则
    $("#endDate").epsDatepicker({applyRule: endRule});  //增加开始时间的规则
	
	BargainPurchaserList.oTable=$('#BargainPurchaserList').dataTable( {
		'singleSelect':true,
		'checkbox':false,
		'queryColumns':'bargainNo,bargainName,supplierNames,createTime',
		//'alias' : 'bargainNo,bargainName,supplierNames,createTime',
		'hiddenColumns':'',
		'fnInitComplete':function(oSettings) {
		},
		'fnDrawCallback':function(oSettings) {	
			BargainPurchaserList.oTable.oSettings=oSettings;
		},
		'fnRowCallback': function( nRow, aData, iDisplayIndex ) {//查询完毕每行的回填事件	
			$(nRow).append('<td align="center"><a href="javascript:void(0);" onclick="BargainPurchaserList.getOperatorStr(\''+aData.objId+'\');return false;">查看</a></td>');
			return nRow;
		},
		'params':{useStatus:"00"},
		'searchZone':'bargainPurchaserForm',
		'sAjaxSource': $('#initPath').val()+'/BargainController.do?method=listBargain&type=buyer'
	});
	$('#query').click(function(){
		BargainPurchaserList.oTable.fnDraw();
	})
	$("#returnUrl").val($('#initPath').val()+"/view/pubservice/bizdata/bargain/bargain_purchaser_list.jsp");
});
	
