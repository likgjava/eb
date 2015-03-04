var goodsPriceForm = {};
goodsPriceForm.oTable;

//查看
goodsPriceForm.viewPrice = function(id,goodsId){
    $.epsDialog({
    	id:'viewPriceDiv',
        title:'查看行情',
        url:$('#initPath').val()+'/GoodsPriceController.do?method=toPriceDetail&priceId='+id+'&goodsId='+$("input[name=objId]").val(),
        width: '900',
        height:'550',
        onClose: function(){}
    });
}

//禁用
goodsPriceForm.stopPrice = function(id){
	if(confirm("确定禁用？")){
		$.getJSON($("#initPath").val()+"/GoodsPriceController.do?method=updateStopStatus",{"priceId":id},function(json){
			if(json.success){
				alert("操作成功！");
				goodsPriceForm.getPriceList();
			}else{
				alert("操作失败！");
			}
		})
	}
}

//关闭
goodsPriceForm.close = function(){
	$('.epsDialogClose').trigger('click');
}

//加载已有行情列表
goodsPriceForm.getPriceList = function(){
	if(null==goodsPriceForm.oTable){
		goodsPriceForm.oTable = $('#priceDetailList').dataTable({
			'queryColumns' : 'unitPrice,dscuRate,prtcPrice,efctDate,endDate,district.name',
			'hiddenColumns':'useStatus,auditStatus',
			'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
				$(nRow).append('<td class="operation"></td>');
				
				if(aData.useStatus=='01'){
					$(nRow).find('td:last').append('<a name="stop" title="禁用" href="javascript:void(0);"><span>禁用</span></a>');
					$(nRow).find('a[name=stop]').click(function(){
						goodsPriceForm.stopPrice(aData.objId);
					})
				}
				
				$(nRow).find('td:last').append('<a name="view" title="查看" href="javascript:void(0);"><span>查看</span></a>');
				$(nRow).find('a[name=view]').click(function(){
					goodsPriceForm.viewPrice(aData.objId);
				})
				return nRow;
			},
			"sAjaxSource" : $('#initPath').val()+ "/GoodsPriceController.do?method=list&goodsPriceSupplier.supplier.objId="+$("input[name=supplierId]").val(),
			"params":{"goodsPriceSupplier.goods.objId":$("#objId").val(),'auditStatus':'02','useStatus':'01'}
		});
	}else{
		goodsPriceForm.oTable.fnDraw();
	}
}

$(document).ready(function(){
	
	//初始化tabs
	$('#epsTabs2').tabs({}); 
	
	//切换列表
	$('#epsTabs2').bind('tabsselect', function(event, ui) {
		if(ui.index==0){			//有效
			$(goodsPriceForm.oTable.dataTableSettings).attr("params",{"goodsPriceSupplier.goods.objId":$("#objId").val(),'auditStatus':'02','useStatus':'01'});
		}else if(ui.index==1){			//无效
			$(goodsPriceForm.oTable.dataTableSettings).attr("params",{"goodsPriceSupplier.goods.objId":$("#objId").val(),'useStatus':'02'});
		}
		goodsPriceForm.getPriceList();
	});
	
	//加载列表
	goodsPriceForm.getPriceList();
})