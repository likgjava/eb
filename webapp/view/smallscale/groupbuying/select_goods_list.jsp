<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<input type="hidden" id="HGoodsId" value="${param.HgoodsId}" />
<input type="hidden" id="HProductName" value="${param.HproductName}" />
<input type="hidden" id="HReferPrice" value="${param.HreferPrice}" />

<div class="conSearch">
	<h4><span><spring:message code="globe.query" /></span></h4>
	<form id="selectGoodsListSearchForm">
		<input type="hidden" id="goodsClassObjId" name="goodsClass.objId" value="${param.goodsClassId}" />
		<input type="hidden" name="goodsClass.objId_op" value="like" />
		<ul >
			<li>
				<label>商品名称：</label>
				<input type="text" name="productName" id="productName" />
				<input type="hidden" name="productName_op" value="like" />
			</li>
			<li>
				<label>商品品牌：</label>
				<input type="text" name="goodsBrand.brandName" id="goodsBrand.brandName" />
				<input type="hidden" name="goodsBrand.brandName_op" value="like" />
			</li>
			<li class="operationBtnDiv">
				<button type="button" id="query"><span><spring:message code="globe.query" /></span></button>
			</li>
		</ul>
	</form>
</div>

<table class="frontTableList" id="selectGoodsList">
	<thead>
		<tr>
			<th class="omission" omiLength="10">商品名称</th>
			<th class="omission" omiLength="10">规格型号</th>
			<th class="omission" omiLength="10">商品品牌</th>
			<th class="omission" omiLength="10">商品分类</th>
			<th class="money">市场价</th>
			<th class="operation">操作</th>
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>

<div class="conOperation">	
	<button  id="close" type="button"><span>关闭</span></button>
</div>

<script>
var SelectGoodsList = {};
SelectGoodsList.getOperatorStr=function(aData) {
	var operatorHtml = "";
	operatorHtml += '<td class="operation">';
	operatorHtml += '<a title="选择" href="javascript:void(0);" onclick="SelectGoodsList.select(\''+aData.objId+'\',\''+aData.productName+'\',\''+aData["goodsClass.objId"]+'\','+aData.referPrice+');return false;">选择</a>';
	operatorHtml += '</td>';
	return operatorHtml;
}

//选择商品，回填数据
SelectGoodsList.select=function(goodsId,productName,goodsClassId,referPrice) {
	$("#"+$("#HGoodsId").val()).val(goodsId);
	$("#"+$("#HProductName").val()).val(productName);
	if($("#HReferPrice").val() != ""){
		$("#"+$("#HReferPrice").val()).val(referPrice);
	}
	$("#close").click();
}

$(document).ready(function(){
	//加载商品列表
	SelectGoodsList.oTable = $('#selectGoodsList').dataTable({
		'singleSelect' : true,
		'checkbox' : false,
		'queryColumns' : 'productName,productCode,goodsBrand.brandName,goodsClass.goodsClassName,referPrice',
		'hiddenColumns':'goodsClass.objId',
		'fnInitComplete' : function(oSettings) {
		},
		'fnDrawCallback' : function(oSettings) {
			SelectGoodsList.oTable.oSettings = oSettings;
		},
		'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
			//添加操作按钮
			$(nRow).append(SelectGoodsList.getOperatorStr(aData))
			return nRow;
		},
		"sAjaxSource" : $('#initPath').val()+ "/GoodsController.do?method=list",
		"params":{"useStatus":"01"},
		'searchZone':'selectGoodsListSearchForm'
	});

	// 查询
	$("#query").click(function() {
		SelectGoodsList.oTable.fnDraw();
	})

	//关闭
	$("#close").click(function(){
		$('.epsDialogClose').trigger('click');
	})
})
</script>
