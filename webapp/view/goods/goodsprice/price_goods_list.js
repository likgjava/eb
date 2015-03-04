/*
 * 脚本
 * created by yucy
 */
var priceGoodsList={};
priceGoodsList.oTable;

//取得商品列表
priceGoodsList.getPriceGoodsList = function(){
	if(null==priceGoodsList.oTable){
		priceGoodsList.oTable = $('#priceGoodsList').dataTable({
			'searchZone':'priceSearchForm',
			'queryColumns' : 'picture,productName,productCode,goodsBrand.brandName,goodsClass.goodsClassName,remark',
			'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
				$(nRow).append('<td class="operation"></td>');
				
				//处理主标示
				$(nRow).find('td:eq(0)').html('<img width="80" height="50" src="'+$("#initPath").val()+"/AttachmentController.do?method=showImg&objId="+aData.picture+'"></img>');
				
				$(nRow).find('td:last').append('<a name="inputPrice" title="维护行情" href="javascript:void(0);"><span>维护行情</span></a>');
				$(nRow).find('a[name=inputPrice]').click(function(){
				    $('#conBody').loadPage($('#initPath').val()+'/GoodsPriceController.do?method=toCreatePrice&goodsId='+aData.objId);
				})
				return nRow;
			},
			"sAjaxSource" : $('#initPath').val()+ "/AgreementGoodsController.do?method=getPriceGoodsList"
		});
		
	}else{
		priceGoodsList.oTable.fnDraw();
	}
}

$(document).ready(function(){
	//加载列表
	priceGoodsList.getPriceGoodsList();
});
