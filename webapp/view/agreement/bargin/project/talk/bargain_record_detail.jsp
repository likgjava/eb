<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<input type="hidden" id="projId" value="<%=request.getParameter("projId")%>" />
<input type="hidden" id="biddingRecordId" value="<%=request.getParameter("biddingRecordId")%>" />
<input type="hidden" id="orgType" value="<%=request.getParameter("orgType")%>" />


<div class="orderManagement">
    <h2><span id="recordTitle">报价详情</span></h2>
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
			<li>节省金额：<strong><span id="saveAmount">0</span></strong>元</li>
		</ul>
	</div>
	<div class="formLayout form3Pa"></div>
	
	<div id="bargainFile" value="${biddingRecord.bargainFile }"></div>
    <div class="conOperation">
    	<button type="button" id="closeDialogBtn"><span>关闭</span></button>
    </div>
</div>
<script><!--
var bargainGoodsDT={};
bargainGoodsDT.oTable;	
bargainGoodsDT.currentGoodsName;
bargainGoodsDT.currentOrgName;

//计算数量、金额、节省金额
bargainGoodsDT.caculateTotal=function(oSettings,eager){
	var totalAmountArray=goodsAPI.fnCaculateGoodsAmount(oSettings,'requireItem.goodsQty','goodsPrice','additional','option','goodsTotal','requireItem.marketPrice');
	$('#totalAmount').html(totalAmountArray[0]);
	$('#saveAmount').html(totalAmountArray[1]);
	$('#countGoods').html(totalAmountArray[2]);
	return totalAmountArray;
}
$(document).ready(function(){

	//回填附件
	var resId = $("#bargainFile").attr("value")
	$('#bargainFile').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=bargainFile&isSelect=yes&attachRelaId='+resId);
	
	bargainGoodsDT.oTable=$('#bargainGoodsTable').dataTable( {
		'params':{'biddingRecord.objId':$('#biddingRecordId').val()},
		'sAjaxSource': $('#initPath').val()+'/BiddingRecordItemController.do?method=list',
		'bPaginate': false,//不分页
		'singleSelect':true,//(默认为true)为true时左边会出现复选框,  没必要用单选框(因为有更好的表达方式)
		'checkbox':false,//(默认为false)为true时左边会出现复选框,  没必要用单选框(因为有更好的表达方式)
		'queryColumns':'goods.productName,goods.productCode,requireItem.marketPrice,requireItem.agreePrice,goodsPrice,requireItem.goodsQty,goodsTotal',//指定要查询的列
		'hiddenColumns':'goods.objId',//隐藏查询不显示的列属性
		'fnInitComplete':function(oSettings) {
		},
		'fnDrawCallback':function(oSettings) {
			//功能计算每行金额以及返回总金额,参数:fnCaculateGoodsAmount(oSettings、数量变量名、价格变量名、配件变量名、配件变量名、选配变量名、每行总金额名、市场价)
			bargainGoodsDT.oTable.oSettings=oSettings;
			bargainGoodsDT.caculateTotal(bargainGoodsDT.oTable.oSettings);
		},
		'fnRowCallback': function( nRow, aData, i) {//查询完毕每行的回填事件,i代表行序号	
			$(nRow).attr('type','goods');
			$(nRow).attr('goodsitemid',aData.objId);
			$(nRow).attr('goodsid',aData["goods.objId"]);
			return nRow;
		}
	});

	$('#closeDialogBtn').click(function(){
		$('.epsDialogClose').trigger('click');
	});

});
</script>
