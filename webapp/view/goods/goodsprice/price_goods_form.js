var goodsPriceForm = {};
goodsPriceForm.oTable;

//新增行情
goodsPriceForm.addPrice = function(){
    $.epsDialog({
    	id:'addPriceDiv',
        title:'新增行情',
        url: $('#initPath').val()+'/GoodsPriceController.do?method=toAddPrice&goodsId='+$("#objId").val()+'&goodsPriceSupplierId='+$("#priceSupplierId").val()+"&appType="+$("#appType").val(),
        width: '1000',
        height:'550',
        onClose: function(){}
    });
}

//管理商品优惠礼包
goodsPriceForm.manageGift = function(goodsPriceId){
	$.epsDialog({
		id:'manageGiftDiv',
		title:'管理商品优惠礼包',
		url: $('#initPath').val()+'/GoodsGiftPriceController.do?method=toGoodsGiftManageView&goodsPriceId='+goodsPriceId+'&goodsId='+$("#objId").val(),
		width: '800',
		height:'500',
		onClose: function(){}
	});
}

//修改行情
goodsPriceForm.updatePrice = function(id){
    $.epsDialog({
    	id:'updatePriceDiv',
        title:'修改行情',
        url:$('#initPath').val()+'/GoodsPriceController.do?method=toUpdatePrice&goodsId='+$("#objId").val()+'&priceId='+id+'&goodsPriceSupplierId='+$("#priceSupplierId").val()+"&appType="+$("#appType").val(),
        width: '1000',
        height:'550',
        onClose: function(){}
    });
}

//维护零配件价格
goodsPriceForm.updateAccPrice = function(id){
	$.epsDialog({
    	id:'updateAccPriceDiv',
        title:'维护零配件价格',
        url:$('#initPath').val()+'/view/goods/goodsprice/goodsaccessories_price_list.jsp?goodsPriceId='+id+"&goodsId="+$('#goodsId').val()+"&goodsPriceSupplierId="+$('#priceSupplierId').val(),
        width: '700',
        height:'350'
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

//删除
goodsPriceForm.del = function(ids){
	if(confirm("确定删除？")){
		$.getJSON($("#initPath").val()+"/GoodsPriceController.do?method=deletePrice",{"priceIds":ids},function(json){
			if(json.success){
				alert("操作成功！");
				goodsPriceForm.getPriceList();
			}else{
				alert("操作失败！");
			}
		})
	}
}

//查看
goodsPriceForm.viewPrice = function(id){
    $.epsDialog({
    	id:'viewPriceDiv',
        title:'查看行情',
        url:$('#initPath').val()+'/GoodsPriceController.do?method=toPriceDetail&priceId='+id+'&goodsId='+$("input[name=objId]").val(),
        width: '900',
        height:'550',
        onClose: function(){}
    });
}

//加载已有行情列表
goodsPriceForm.getPriceList = function(){
	if(null==goodsPriceForm.oTable){
		goodsPriceForm.oTable = $('#priceList').dataTable({
			'queryColumns' : 'unitPrice,dscuRate,prtcPrice,efctDate,endDate,district.name',
			'hiddenColumns': 'useStatus,auditStatus',
			'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
				$(nRow).append('<td class="operation"></td>');
				/**协议供货
				if(aData.useStatus != '02') {
					$(nRow).find('td:last').append('<a name="accprice" title="维护零配件价格" href="javascript:void(0);"><span>维护零配件价格</span></a>');
					$(nRow).find('a[name=accprice]').click(function(){//维护零配件价格
						goodsPriceForm.updateAccPrice(aData.objId);
					})
				}
				*/
				
				/**小额交易*/
				if(aData.useStatus != '02') {
					$(nRow).find('td:last').append('<a name="manageGift" title="维护商品优惠礼包价格" href="javascript:void(0);"><span>维护礼包价格</span></a>');
					$(nRow).find('a[name=manageGift]').click(function(){//维护商品礼包价格
						goodsPriceForm.manageGift(aData.objId);
					})
				}
				
				if((aData.auditStatus=='00'||aData.auditStatus=='03')&&aData.useStatus=='00'){
					$(nRow).find('td:last').append('<a name="update" title="修改" href="javascript:void(0);"><span>修改</span></a>');
					$(nRow).find('a[name=update]').click(function(){
						goodsPriceForm.updatePrice(aData.objId);
					})
				}

				if(aData.useStatus=='01'){
					$(nRow).find('td:last').append('<a name="stop" title="禁用" href="javascript:void(0);"><span>禁用</span></a>');
					$(nRow).find('a[name=stop]').click(function(){
						goodsPriceForm.stopPrice(aData.objId);
					})
				}
				
				if(aData.auditStatus=='00'||aData.auditStatus=='03'){
					$(nRow).find('td:last').append('<a name="del" title="删除" href="javascript:void(0);"><span>删除</span></a>');
					$(nRow).find('a[name=del]').click(function(){
						goodsPriceForm.del(aData.objId);
					})
				}
				
				$(nRow).find('td:last').append('<a name="view" title="查看" href="javascript:void(0);"><span>查看</span></a>');
				$(nRow).find('a[name=view]').click(function(){
					goodsPriceForm.viewPrice(aData.objId);
				})
				return nRow;
			},
			"sAjaxSource" : $('#initPath').val()+ "/GoodsPriceController.do?method=list&goodsPriceSupplier.supplier.objId="+$("input[name=supplierId]").val(),
			"params":{"goodsPriceSupplier.goods.objId":$("#objId").val(),'auditStatus_op':'in','auditStatus':'00,03','useStatus':'00'}
		});
	}else{
		goodsPriceForm.oTable.fnDraw();
	}
}

$(document).ready(function(){
	
	//初始化tabs
	$('#epsTabs').tabs({}); 
	
	//切换列表
	$('#epsTabs').bind('tabsselect', function(event, ui) {
		if(ui.index==0){				//新建
			$(goodsPriceForm.oTable.dataTableSettings).attr("params",{"goodsPriceSupplier.goods.objId":$("#objId").val(),'auditStatus_op':'in','auditStatus':'00,03','useStatus':'00'});
		}else if(ui.index==1){			//待审
			$(goodsPriceForm.oTable.dataTableSettings).attr("params",{"goodsPriceSupplier.goods.objId":$("#objId").val(),'auditStatus':'01','useStatus':'00'});
		}else if(ui.index==2){			//有效
			$(goodsPriceForm.oTable.dataTableSettings).attr("params",{"goodsPriceSupplier.goods.objId":$("#objId").val(),'auditStatus':'02','useStatus':'01'});
		}else if(ui.index==3){			//无效
			$(goodsPriceForm.oTable.dataTableSettings).attr("params",{"goodsPriceSupplier.goods.objId":$("#objId").val(),'useStatus':'02'});
		}
		goodsPriceForm.getPriceList();
	});
	
	//加载列表
	goodsPriceForm.getPriceList();
	
})