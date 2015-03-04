var goodsPriceManageList = {};
goodsPriceManageList.oTable;

//显示行情
goodsPriceManageList.stopAppear = function(id,isShow){
	if(confirm("确认"+(isShow=='true'?"禁止显示":"显示")+"？")){
		$.getJSON($("#initPath").val()+"/GoodsPriceSupplierController.do?method=updateShowStatus",{"goodsPriceSupplierId":id,"showType":isShow},function(json){
			if(json.success){
				alert("操作成功！");
				goodsPriceManageList.getPriceList();
			}else{
				alert("操作失败！");
			}
		})
	}
}

//查看
goodsPriceManageList.view = function(goodsId,supplierId){
    $.epsDialog({
    	id:'managePriceDiv',
        title:'查看行情',
        url:$('#initPath').val()+'/GoodsPriceController.do?method=toManagePriceDetail&goodsId='+goodsId+ '&supplierId='+supplierId,
        width: '1000',
        height:'500',
        onClose: function(){}
    });
}

//加载已有行情列表
goodsPriceManageList.getPriceList = function(){
	if(null==goodsPriceManageList.oTable){
		goodsPriceManageList.oTable = $('#priceList').dataTable({
			'queryColumns' : 'goods.productName,supplier.orgName',
			'hiddenColumns':'isShow,goods.objId,supplier.objId',
			'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
				$(nRow).append('<td class="operation"></td>');
				$(nRow).find('td:last').append('<a name="stop" title="'+(aData.isShow=='true'?"禁止显示":"显示")+'" href="javascript:void(0);"><span>'+(aData.isShow=='true'?"禁止显示":"显示")+'</span></a>');
				$(nRow).find('a[name=stop]').click(function(){
					goodsPriceManageList.stopAppear(aData.objId,aData.isShow);
				})
				
				$(nRow).find('td:last').append('<a name="view" title="管理" href="javascript:void(0);"><span>管理</span></a>');
				$(nRow).find('a[name=view]').click(function(){
					goodsPriceManageList.view(aData["goods.objId"],aData["supplier.objId"]);
				})
				return nRow;
			},
			"sAjaxSource" : $('#initPath').val()+ "/GoodsPriceSupplierController.do?method=list",
			"params":{"isShow":"true"},
			"searchZone":"priceSearchForm"
		});
	}else{
		goodsPriceManageList.oTable.fnDraw();
	}
}

$(document).ready(function(){
	//初始化tabs
	$('#epsTabs').tabs({});
	
	//切换列表
	$('#epsTabs').bind('tabsselect', function(event, ui) {
		if(ui.index==0){				//显示的
			$(goodsPriceManageList.oTable.dataTableSettings).attr("params",{"isShow":"true"});
		}else if(ui.index==1){			//禁止显示的
			$(goodsPriceManageList.oTable.dataTableSettings).attr("params",{"isShow":"false"});
		}
		goodsPriceManageList.oTable.fnDraw();
	});
	
	//加载列表
	goodsPriceManageList.getPriceList();
})