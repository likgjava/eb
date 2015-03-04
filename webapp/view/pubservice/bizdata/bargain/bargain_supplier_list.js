
//定义文件全局变量 处理方法名重复问题
var BargainSupplierList={};

BargainSupplierList.currentTabID="tabs_bargaining"; //当前Tab的ID

BargainSupplierList.getOperatorStr=function(objId){
	$('#conBody').loadPage($('#initPath').val()+'/BargainController.do?method=toBargainDetailView&type=supplier&bargainId='+objId);
}

$(document).ready(function(){
	//加载tabs
	$('#epsTabs').tabs({}); 
	var endRule=null;
	var startRule=null;
	//开始时间
    $("#startDate").epsDatepicker({applyRule: endRule });  //增加结束时间的规则
    //结束时间
    $("#endDate").epsDatepicker({applyRule: startRule });  //增加开始时间的规则

    $("li a.refreshData").click(function(){
		BargainSupplierList.currentTabID = $(this).attr("id");
		if(BargainSupplierList.currentTabID == "tabs_bargaining"){
			$(BargainSupplierList.oTable.dataTableSettings).attr('params',{useStatus:"01"});
		}else if(BargainSupplierList.currentTabID == "tabs_done"){
			$(BargainSupplierList.oTable.dataTableSettings).attr('params',{useStatus:"02"});
		}
		BargainSupplierList.oTable.fnDraw();
	});
	BargainSupplierList.oTable=$('#BargainSupplierList').dataTable( {
		
		'singleSelect':true,
		'checkbox':false,
		'queryColumns':'bargainNo,bargainName,buyerName,createTime',
		'alias' : 'bargainNo,bargainName,buyerName,createTime',
		'hiddenColumns':'',
		'fnInitComplete':function(oSettings) {
		},
		'fnDrawCallback':function(oSettings) {	
			BargainSupplierList.oTable.oSettings=oSettings;
		},
		'fnRowCallback': function( nRow, aData, iDisplayIndex ) {
			$(nRow).append('<td align="center"><a href="javascript:void(0);" onclick="BargainSupplierList.getOperatorStr(\''+aData.objId+'\');return false;">查看</a></td>');
			return nRow;
		},
		'searchZone':'BargainSupplierListForm',
		'params':{useStatus:"01"},
		"sAjaxSource": $('#initPath').val()+'/BargainController.do?method=listBargain&type=supplier'
	});
	
	//查询
	$("#query").click(function(){
		BargainSupplierList.oTable.fnDraw();
	});
	$("#returnUrl").val($('#initPath').val()+"/view/pubservice/bizdata/bargain/bargain_supplier_list.jsp");
});
	
