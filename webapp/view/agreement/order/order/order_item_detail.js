/*
 * 协议供货.订单商品列表
 * author: liangx
 * mail: liangxj@gpcsoft.com
 */
//定义文件全局变量 处理方法名重复问题
var orderItemDetail={};
orderItemDetail.oTable;	

$(document).ready(function(){
	// 订单商品详细
	orderItemDetail.oTable=$('#orderItemList').dataTable( {
		'bPaginate': false,//不分页
		'singleSelect':true,//(false表示可以多选)
		'checkbox':false,//(默认为false)为true时左边会出现复选框,  没必要用单选框(因为有更好的表达方式)
		'queryColumns':'attachemt,productName,productCode,marketPrice,goodsPrice,goodsQty,goodsTotal',//指定要查询的列
		'hiddenColumns':'',//隐藏查询不显示的列属性
		'fnInitComplete':function(oSettings) {
		},
		'fnDrawCallback':function(oSettings) {	
			var additional=[
							{property:'paramName',preText:'<b>标配:</b>'},
							{property:'paramValue',preText:'<b>标配:</b>'}
						];
			var option=[ 
						{property:'goodsOptionalFitting.optionContent',name:'goods.productName',preText:'<b>可选配件:</b>'},
						{property:'optAgreePrice',name:'marketPrice'},
						{property:'optPrice',name:'goodsPrice'},
						{property:'optQty',name:'goodsQty'}
					];
			//参数: 表格属性、配件、选配、需要跨越的列名(默认跨越商品行+配件行+选配行)
			goodsAPI.fnDisplayAdditionalAndOption(oSettings,[],option,'orderGoodsOptions',[]);//显示选配配件并格式化金额(包括input和非input的)  
			//功能计算每行金额以及返回总金额,参数:fnCaculateGoodsAmount(oSettings、数量变量名、价格变量名、配件变量名、配件变量名、选配变量名、每行总金额名)
			var totalAmountArray=goodsAPI.fnCaculateGoodsAmount(oSettings,'goodsQty','goodsPrice','additional','option','goodsTotal','marketPrice');
			$('.totalAmount').html(totalAmountArray[0]);
			$('#saveAmount').html(totalAmountArray[1]);
			$('#countGoods').html(totalAmountArray[2]);
			orderItemDetail.oTable.oSettings=oSettings;
		},
		'fnRowCallback': function( nRow, aData, i) {//查询完毕每行的回填事件,i代表行序号	
			var goods=aData.goods;//商品
			$(nRow).attr('type','goods');
			$(nRow).find('td[name=attachemt]').addClass('img').html('<a href="javascript:void(0);"><img src='+$('#initPath').val()+'/view/resource/skin/skin07/img/good1.png /></a>');
			$(nRow).find('td[name=productName]').html(goods.productName);
			$(nRow).find('td[name=productCode]').html(goods.productCode);
			$(nRow).find('td[name=marketPrice]').html(aData.marketPrice);
			$(nRow).find('td[name=goodsPrice]').html(aData.goodsPrice);
			$(nRow).find('td[name=goodsQty]').html(aData.goodsQty);
			$(nRow).find('td[name=goodsTotal]').html(aData.goodsTotal);
			
			return nRow;
		},
		'sAjaxSource': $('#initPath').val()+"/OrderController.do?method=getOrderGoodsOption&orderId="+$('#objId').val()
	});
});
	
