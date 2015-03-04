
//定义文件全局变量 处理方法名重复问题
var OrderSupplierDetail={};

OrderSupplierDetail.openContract=function(objId){
	$('#conBody').loadPage($('#initPath').val()+'/AgContractController.do?method=toContractVeiw&objId='+objId);
}

//计算数量、金额、节省金额
OrderSupplierDetail.caculateTotal=function(oSettings){
	var totalAmountArray=goodsAPI.fnCaculateGoodsAmount(oSettings,'goodsQty','goodsPrice','additional','option','goodsTotal','marketPrice');
	$('#totalMoney').html(totalAmountArray[0]);
	$('#saveAmount').html(totalAmountArray[1]);
	$('#countGoods').html(totalAmountArray[2]);
	return totalAmountArray;
}
function showOrgInfo(id){
	var url = $('#initPath').val()+'/OrgInfoController.do?method=getOrgAllInfo&orgInfoId='+id;
	$.epsDialog({
        title:'机构详情',
        url:url,
        width: '900',
        height: '500'
    }); 
}
$(document).ready(function(){
	// 订单商品详细
	OrderSupplierDetail.oTable=$('#OrderItemList').dataTable( {
		'bPaginate': false,//不分页
		'singleSelect':true,//(false表示可以多选)
		'checkbox':false,//(默认为false)为true时左边会出现复选框,  没必要用单选框(因为有更好的表达方式)
		'queryColumns':'productName,productCode,marketPrice,agreePrice,goodsPrice,goodsQty,goodsTotal',//指定要查询的列
		'hiddenColumns':'',//隐藏查询不显示的列属性
		'fnInitComplete':function(oSettings) {
		},
		'fnDrawCallback':function(oSettings) {
			OrderSupplierDetail.oTable.oSettings=oSettings;//很重要，将当前的oSettings对象赋给当前table的oSettgins
			var option=[ 
						{property:'option.optionContent',name:'productName',preText:'<b>可选配件:</b>'},
						{property:'optAgreePrice',name:'marketPrice'},
						{property:'optAgreePrice',name:'agreePrice'},
						{property:'optPrice',name:'goodsPrice'},
						{property:'optQty',name:'goodsQty'}
					];
			//参数: 表格属性、配件、选配、需要跨越的列名(默认跨越商品行+配件行+选配行)
			goodsAPI.fnDisplayAdditionalAndOption(oSettings,[],option,'orderGoodsOptions',[],2);//显示选配配件并格式化金额(包括input和非input的)  
			
			//计算数量和金额
			OrderSupplierDetail.caculateTotal(oSettings);
		},
		'fnRowCallback': function( nRow, aData, i) {//查询完毕每行的回填事件,i代表行序号	
			var goods=aData.goods;//商品
			$(nRow).attr('type','goods');
			$(nRow).attr('goodsitemid',aData.objId);
			$(nRow).attr('goodsid',aData.goods.objId);
			
			$(nRow).find('td[name=productName]').html(goods.productName);
			$(nRow).find('td[name=productCode]').html(goods.productCode);
		
			$(nRow).find('td[name=attachemt]').addClass('img').html('<a href="javascript:void(0);"><img src='+$('#initPath').val()+'/AttachmentController.do?method=showImg&objId='+goods.picture+' /></a>');

			return nRow;
		},
		'sAjaxSource': $('#initPath').val()+"/OrderController.do?method=getOrderGoodsOption&orderId="+$('#objId').val()
	});
});
	
