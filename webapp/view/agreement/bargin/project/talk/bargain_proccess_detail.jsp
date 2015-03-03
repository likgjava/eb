<%@ page contentType="text/html;charset=UTF-8" %><%@ include file="/view/srplatform/common/taglibs.jsp"%>
 
<input type="hidden" id="projectObjId" value="<%=request.getParameter("projectObjId")%>" />
<input type="hidden" id="oldTotalAmount" value="" /><!--上次报价的总金额-->
<div class="orderManagement">
	<table class="frontGoodsTableList" id="bargainGoodsTable">
		<thead>
			<tr>
				<th>商品名称</th>
				<th>商品型号</th>
				<th class="money">市场价</th>
				<th class="money">协议价</th>
				<th class="money">单价</th>
				<th class="amount">数量</th>
				<th class="money">金额</th>
			</tr>
		</thead>
		<tbody>	 
		</tbody>
	</table>
 
	<div class="billingInfo">
		<ul>
			<li>数量总计：<strong><span id="countGoods">0</span></strong>件</li>
			<li>金额总计：<strong>￥<span id="totalAmount">0.00</span></strong>元</li>
		</ul>
		<input type="hidden" id="totalAmountNum" value="" /><!--本次报价的总金额-->
	</div>
	<div class="formLayout form3Pa"></div>
	
	<div id="bargainFile"></div>
    <div class="conOperation">
    	<button type="button" id="bargainAllBtn"><span>报价</span></button>
    	<button type="button" id="closeDialogBtn"><span>关闭</span></button>
    </div>
</div>


<script>
/**
 * 议价厅，供应商报价页面
 */
var bargainGoodsDT = {};
bargainGoodsDT.oTable;

//报价
bargainGoodsDT.goodsQtyPriceBargain = function(){
	//非第一次报价，判断本次总报价是否低于上次总报价
	if($('#oldTotalAmount').val()!=null && $('#oldTotalAmount').val()!=''){
		if(Number($('#totalAmountNum').val()) >= Number($('#oldTotalAmount').val())){
			alert('您的本次总报价必须低于您的上次总报价！\n上次总报价：'+formatAmount(Number($('#oldTotalAmount').val()),2)+'元\n本次总报价：'+formatAmount(Number($('#totalAmountNum').val()),2)+'元');
			return;
		}
	}
	
	if(!window.confirm("确定报价?")){return;}

	//报价记录对象
	var biddingRecord={
		goodsTotal:$('#totalAmount').html().replace(/,/g,''),
		totalDiscount:1,
		project:{objId:$('#projectObjId').val()}
	};
	
	//循环多个商品，组合报价记录条目
	var biddingRecordItems=[];
	$("#bargainGoodsTable tbody").find("tr").each(function(i,domEle){
		var biddingRecordItem = {
			goods: {objId:$(this).attr('goodsid')},
			requireItem: {objId:$(this).attr('objId')},
			goodsPrice: $(this).find('td[name=goodsPrice] input').val().replace(/,/g,''),
			goodsQty: $(this).attr('goodsQty'),
			goodsTotal: $(this).find('td[name=goodsTotal]').html().replace(/,/g,'')
		};
		biddingRecordItems.push(biddingRecordItem);
	});
	biddingRecord.biddingRecordItemSet = biddingRecordItems;

	//附件处理
	if($("input[name=bargainFile]")!=null&&$("input[name=bargainFile]").val()!=""){
		biddingRecord.bargainFile = $("input[name=bargainFile]").val();
	}

	//提交报价
	$.getJSON($('#initPath').val()+'/BiddingRecordController.do?method=saveBiddingRecord',{'biddingRecordStr':JSON.stringify(biddingRecord)},function(json){
		if(json.failure){if(json.result.indexOf('已结束')>-1);bargainHall.quitHall();alert(json.result);return;}
		
		bargainHall.bargainSendSupplierContent($("#orgName").val()+' 报价:'+$('#totalAmount').html() +'元', json.objId);
	    
		$('#closeDialogBtn').click();  //关闭窗口
		bargainHall.findBargainHistory();//更新报价历史
	});
}

//计算总金额,并回填总金额和总数量
bargainGoodsDT.caculateAmount = function(){
	var totalAmount = 0; //报价总金额
	var totalGoodsQty = 0; //总商品数量
	
	$("#bargainGoodsTable tbody").find("tr").each(function (index, domEle){
		var currentBiddingPrice = Number($(domEle).find("input[name=goodsPrice]").val());
		var goodsQty = Number($(domEle).attr("goodsQty"));
		totalAmount += currentBiddingPrice * goodsQty;
		totalGoodsQty += goodsQty;
		$(domEle).find("td[name=goodsTotal]").html(formatAmount(currentBiddingPrice*goodsQty,2));//回填当前商品的小计
	});

	//回填总金额
	$('#totalAmount').html(formatAmount(totalAmount,2));
	$('#countGoods').html(totalGoodsQty);
	$('#totalAmountNum').val(totalAmount);
}

//验证供应商报价金额
bargainGoodsDT.validGoodsPrice = function(obj){
	var theLastBiddingPrice = Number($(obj).attr("goodsPrice")); //上次报价金额（如果是第一次报价，则为协议价）

	//报价金额无效
	if($(obj).val()=='' || isNaN($(obj).val())){
		alert('请输入有效金额！');
		$(obj).val(theLastBiddingPrice);
		return ;
	}

	//当前报价大于上次报价
	var currentBiddingPrice = Number($(obj).val()); //当前报价金额
	if(currentBiddingPrice > theLastBiddingPrice){
		alert("当前报价：￥"+formatAmount(currentBiddingPrice,2)+"  大于上次报价￥"+formatAmount(theLastBiddingPrice,2));
		$(obj).val(theLastBiddingPrice);
		return ;
	}
}


$(document).ready(function(){
	$('#bargainAllBtn').focus();

	//回填附件
	var resId = $("#bargainFile").attr("value")
	if(null!=resId&&""!=resId){
		$('#bargainFile').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=bargainFile&attachRelaId='+resId+'&buttonName='+native2ascii("上传报价文件"));
	}else{
		$('#bargainFile').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=bargainFile&buttonName='+native2ascii("上传报价文件"));
	}

	if($("#isLogin").val()=="false"){alert("请先登录！");return;}
	bargainGoodsDT.oTable=$('#bargainGoodsTable').dataTable( {
		'params':{'project.objId':$('#projectObjId').val()},
		'sAjaxSource': $('#initPath').val()+'/RequireItemController.do?method=list',
		'bPaginate': false,//不分页
		'singleSelect':true,
		'checkbox':false,
		'queryColumns':'goods.productName,goods.productCode,marketPrice,agreePrice,goodsPrice,goodsQty,goodsTotal',
		'hiddenColumns':'goods.objId',
		'fnInitComplete':function(oSettings) {
		},
		'fnDrawCallback':function(oSettings) {
			bargainGoodsDT.oTable.oSettings=oSettings;
			var data = {projectObjId:$('#projectObjId').val(),supplierObjId:$('#orgId').val()};
			//获取上次报价信息
			$.getJSON($('#initPath').val()+'/TalkProjectController.do?method=getSupplierBiddingHistory&biddingType=theLast',data,function(json){
				if(json.biddingRecordItemSet){
					var oldTotalAmount = 0;
					$.each(json.biddingRecordItemSet,function(i,n){
						var goodsId1 = n.goods.objId;
						var goodsPrice = n.goodsPrice;
						$("#bargainGoodsTable").find("tr").each(function (index, domEle){
							var goodsId2 = $(domEle).attr("goodsid");
							if(goodsId1 == goodsId2){
								$(domEle).find("input[name=goodsPrice]").val(goodsPrice);
								$(domEle).find("input[name=goodsPrice]").attr("goodsPrice",goodsPrice);
								oldTotalAmount += goodsPrice;
							}
						});
					});
					$('#oldTotalAmount').val(oldTotalAmount);
				}
				bargainGoodsDT.caculateAmount(); //计算总金额
			});
		},
		'fnRowCallback': function( nRow, aData, i) {
			$(nRow).attr('type','goods');
			$(nRow).attr('goodsitemid',aData.objId);
			$(nRow).attr('goodsid',aData["goods.objId"]);
			$(nRow).attr('goodsQty',aData.goodsQty);
			
			//把单价弄成可编辑
			var goodsPriceColumn = $(nRow).find('td[name=goodsPrice]');
			goodsPriceColumn.html($('<input goodsPrice='+aData.agreePrice+' name="goodsPrice" size=8 type=text value='+aData.agreePrice+' />').addClass('paddingR').blur(function(){
				bargainGoodsDT.validGoodsPrice(this); //验证供应商报价金额
				bargainGoodsDT.caculateAmount(); //计算总金额
			})); 
			return nRow;
		}
	});

	//报价
	$('#bargainAllBtn').click(function(){
		bargainGoodsDT.goodsQtyPriceBargain();
	});

	//关闭
	$('#closeDialogBtn').click(function(){
		$('.epsDialogClose').trigger('click');
	});
	 
});
</script>
