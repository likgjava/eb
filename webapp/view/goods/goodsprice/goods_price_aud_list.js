var goodsPriceAuditList = {};
goodsPriceAuditList.oTable;

//审核行情
goodsPriceAuditList.audit = function(id){
	$.epsDialog({
    	id:'auditPriceDiv',
        title:'审核行情',
        url:$('#initPath').val()+'/GoodsPriceController.do?method=toAuditPrice&priceId='+id,
        width: '900',
        height:'550',
        onClose: function(){}
    });
}

//查看
goodsPriceAuditList.viewPrice = function(id,goodsId){
    $.epsDialog({
    	id:'viewPriceDiv',
        title:'查看行情',
        url:$('#initPath').val()+'/GoodsPriceController.do?method=toPriceDetail&priceId='+id+ '&goodsId='+goodsId,
        width: '900',
        height:'550',
        onClose: function(){}
    });
}


//加载已有行情列表
goodsPriceAuditList.getPriceList = function(){
	if(null==goodsPriceAuditList.oTable){
		goodsPriceAuditList.oTable = $('#priceList').dataTable({
			'queryColumns' : 'goodsPriceSupplier.goods.productName,goodsPriceSupplier.supplier.orgName,unitPrice,dscuRate,prtcPrice,efctDate,endDate,district.name',
			'hiddenColumns':'auditStatus,useStatus,goodsPriceSupplier.goods.objId',
			'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
				$(nRow).append('<td class="operation"></td>');
				if(aData.auditStatus=='01'){
					$(nRow).find('td:last').append('<a name="audit" title="审核" href="javascript:void(0);"><span>审核</span></a>');
					$(nRow).find('a[name=audit]').click(function(){
						goodsPriceAuditList.audit(aData.objId);
					})
				}
				if(aData.auditStatus=='02'){
					$(nRow).find('td:last').append('<a name="view" title="查看" href="javascript:void(0);"><span>查看</span></a>');
					$(nRow).find('a[name=view]').click(function(){
						goodsPriceAuditList.viewPrice(aData.objId,aData["goodsPriceSupplier.goods.objId"]);
					})
				}
				return nRow;
			},
			"sAjaxSource" : $('#initPath').val()+ "/GoodsPriceController.do?method=list",
			"params":{'auditStatus':'01'},
			"searchZone":"priceSearchForm"
		});
	}else{
		goodsPriceAuditList.oTable.fnDraw();
	}
}

$(document).ready(function(){
	
	//初始化tabs
	$('#epsTabs').tabs({}); 
	
	//切换列表
	$('#epsTabs').bind('tabsselect', function(event, ui) {
		if(ui.index==0){				//待审
			$(goodsPriceAuditList.oTable.dataTableSettings).attr("params",{'auditStatus':'01'});
		}else if(ui.index==1){			//通过
			$(goodsPriceAuditList.oTable.dataTableSettings).attr("params",{'auditStatus':'02'});
		}
		goodsPriceAuditList.oTable.fnDraw();
	});
	
	//加载列表
	goodsPriceAuditList.getPriceList();
})