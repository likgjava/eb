/*
 * 脚本
 * created by yucy
 */
var priceGoodsList={};
priceGoodsList.oTable;
priceGoodsList.currentTabId = "myPriceListA";

//取得商品列表
priceGoodsList.getPriceGoodsList = function(params){
	if(null==priceGoodsList.oTable){
		priceGoodsList.oTable = $('#priceGoodsList').dataTable({
			'params':params,
			'searchZone':'priceSearchForm',
			'queryColumns' : 'picture,productName,productCode,goodsBrand.brandName,goodsClass.goodsClassName,remark',
			'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
				$(nRow).append('<td class="operation"></td>');
				//处理主标示
				$(nRow).find('td:eq(0)').html('<img width="80" height="50" src="'+$("#initPath").val()+"/AttachmentController.do?method=showImg&objId="+aData.picture+'"></img>');
				
				var operatorHtml = "";
				operatorHtml += '<a title="维护商品优惠礼包" href="javascript:void(0);" onclick="priceGoodsList.showGift(\''+aData.objId+'\');return false;">维护礼包</a>';
				operatorHtml += '<a name="inputPrice" title="维护行情" href="javascript:void(0);"><span>维护行情</span></a>'
				$(nRow).find('td:last').append(operatorHtml);
				
				$(nRow).find('a[name=inputPrice]').click(function(){
				    $('#conBody').loadPage($('#initPath').val()+'/GoodsPriceController.do?method=toCreatePrice&goodsId='+aData.objId+ "&appType=XEJY");
				})
				if(priceGoodsList.currentTabId == "myPriceListA"){
					$(nRow).find('td:last').append('<a href="javascript:void(0);" name="priceQSCahrt"><span>报价趋势</span></a>');
					$(nRow).find('a[name=priceQSCahrt]').click(function(){
						$('#conBody').loadPage($('#initPath').val()+"/view/smallscale/chart/s_goods_project_bidding_line.jsp?userType=supplier&goodsId="+aData.objId);
					})
				}				
				return nRow;
			},
			"sAjaxSource" : $('#initPath').val()+ "/GoodsController.do?method=getGoodsListForSupplier"
		});
		
	}else{
		$(priceGoodsList.oTable.dataTableSettings).attr("params",params);
		priceGoodsList.oTable.fnDraw();
	}
}

//维护商品优惠礼包
priceGoodsList.showGift=function(id){
	$('#conBody').loadPage($('#initPath').val()+"/GoodsGiftController.do?method=toGoodsGiftListView&goodsId="+id);
}

$(document).ready(function(){
	
	$("#returnUrl").val($("#initPath").val()+"/view/goods/goodsprice/s_price_goods_list.jsp");
	
	//加载tabs,绑定选中事件为加载列表
	var $tabs = $('#epsTabs').tabs({
		select: function(event, ui) {
			if(ui.index==0){//我的行情
				priceGoodsList.getPriceGoodsList({"validePrice":"true"});
				priceGoodsList.currentTabId = "myPriceListA";
			}else {//未报价
				priceGoodsList.getPriceGoodsList({"noValidePrice":"true"});
				priceGoodsList.currentTabId = "allGoodsListA";
			}
		}
	});

	//加载列表(第一次)
	priceGoodsList.getPriceGoodsList({"validePrice":"true"});
});
