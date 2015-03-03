<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/base/css/common.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/base/css/button.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/skin07/css/plugInEdit.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/skin07/css/baseEdit.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/skin07/css/supplierAgreement.css"/>

<div class="conSearch">
	<form id="GoodsSelectListForm">
		<input type="hidden" name="ctrlIndex" id="ctrlIndex" value="${param.ctrlIndex}" />
		<input type="hidden" name="ctrlId" id="ctrlId" value="${param.ctrlId}" />
		<input type="hidden" name="categoryId" id="categoryId" value="${param.categoryId}" />
		<h4><span><spring:message code="globe.query" /></span></h4>	
		<ul >
			<li>
				<label for="productName"> 商品名称：</label>
				<input type="text" name="productName" id="productName">
				<input type="hidden" name="productName_op" value="like">
			</li>
			<li>
				<label for="productName"> 商品品牌：</label>
				<input type="text" name="goodsBrand.brandName" id="goodsBrand.brandName">
				<input type="hidden" name="goodsBrand.brandName_op" value="like">
			</li>
			<li class="operationBtnDiv">
				<button type="button" id="query"><span><spring:message code="globe.query" /></span></button>
			</li>
		</ul>
	</form>
</div>

<table class="frontTableList" id="GoodsSelectList">
		<thead>
			<tr>
				<th class="omission" omiLength="10">商品名称</th>
				<th class="omission" omiLength="15">规格型号</th>
				<th class="omission" omiLength="10">商品品牌</th>
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
var GoodsSelectList = {};
GoodsSelectList.getOperatorStr=function(aData) {
	var operatorHtml = "";
	operatorHtml += '<td align="center">';
	operatorHtml += '<a  title="选择" href="javascript:void(0);" onclick="GoodsSelectList.select(\''+aData.objId+'\',\''+aData.productName+'\',\''+aData.measureUnit+'\',\''+aData.referPrice+'\',\''+aData["goodsClass.objId"]+'\',\''+aData["goodsClass.goodsClassName"]+'\',\''+aData["purCategory.objId"]+'\',\''+aData["purCategory.categoryName"]+'\');return false;">选择</a>';
	operatorHtml += '</td>';
	return operatorHtml;
}

//选择商品
GoodsSelectList.select=function(goodsId,productName,measureUnit,marketPrice,goodsClass,goodsClassName,purCategory,categoryName) {
	var index = $('#ctrlIndex').val();
	if(index) {
		//竞价厅
		$('#bargainSupplierListDiv'+index).find('input[id=goodsId'+index+']').val(goodsId);//商品ID
		$('#bargainSupplierListDiv'+index).find('textarea[id=productName'+index+']').val(productName);//商品名称
		
		//购物车
		$('#cartItemDiv'+index).find('input[id=goodsId'+index+']').val(goodsId);//商品ID
		$('#cartItemDiv'+index).find('input[id=productName'+index+']').val(productName);//商品名称
		$('#cartItemDiv'+index).find('input[id=goodsUnit'+index+']').val(measureUnit);//计量单位
		$('#cartItemDiv'+index).find('input[id=marketPrice'+index+']').val(marketPrice);//市场价
		$('#cartItemDiv'+index).find('input[id=agreePrice'+index+']').val(marketPrice);//协议价
		$('#cartItemDiv'+index).find('input[id=goodsPrice'+index+']').val(marketPrice);//单价
		$('#cartItemDiv'+index).find('input[id=purCategoryId'+index+']').val(purCategory);//品目
		$('#cartItemDiv'+index).find('input[id=purCategoryName'+index+']').val(categoryName);//品目名称
		$('#cartItemDiv'+index).find('span[id=marketPrice'+index+']').text(formatAmount(marketPrice,2));//市场价
		$('#cartItemDiv'+index).find('span[id=agreePrice'+index+']').text(formatAmount(marketPrice,2));//协议价
		$('.epsDialogClose').trigger('click');
	} 
	else {
		var ctrlId = $('#ctrlId').val();
		var index = ctrlId.replace('productName','');
		$('span[id='+ctrlId+']').attr('title',productName);
		if(productName.length > 15) {
			productName = productName.substring(0,14)+"...";
		}
		$('span[id='+ctrlId+']').empty().append(productName);//商品名称
		$('input[id=goods'+index+']').val(goodsId);//商品ID
		$('input[id=goodsUnit'+index+']').val(measureUnit);//计量单位
		$('input[id=marketPrice'+index+']').val(marketPrice);//市场价
		$('input[id=agreePrice'+index+']').val(marketPrice);//协议价
		$('input[id=goodsPrice'+index+']').val(marketPrice);//单价
		$('input[id=goodsClass'+index+']').val(goodsClass);//商品分类
		$('input[id=goodsClassName'+index+']').val(goodsClassName);//商品分类名称
		$('input[id=purCategory'+index+']').val(purCategory);//品目
		$('input[id=categoryName'+index+']').val(categoryName);//品目名称
		$('.epsDialogClose').trigger('click');
	}
}

$(document).ready(function(){
	//开始时间
    $("#startDate").epsDatepicker();  //增加结束时间的规则
    //结束时间
    $("#endDate").epsDatepicker();  //增加开始时间的规则
	var categoryId = $('#categoryId').val();
    var params = {"useStatus":"01"};
    if(categoryId != "") {
    	params = {"useStatus":"01","goodsClass.objId":categoryId,"goodsClass.objId_op":"like"};
    }
	//加载商品列表
	GoodsSelectList.oTable = $('#GoodsSelectList').dataTable({
		'singleSelect' : true,
		'checkbox' : false,
		'queryColumns' : 'productName,productCode,goodsBrand.brandName,referPrice',
		'alias' : 'productName,productCode,goodsBrand.brandName,referPrice',
		'hiddenColumns':'auditStatus,measureUnit,goodsClass.objId,goodsClass.goodsClassName,purCategory.objId,purCategory.categoryName',
		'fnInitComplete' : function(oSettings) {
		},
		'fnDrawCallback' : function(oSettings) {
			GoodsSelectList.oTable.oSettings = oSettings;
		},
		'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
			//添加操作按钮
			$(nRow).append(GoodsSelectList.getOperatorStr(aData))
			return nRow;
		},
		"sAjaxSource" : $('#initPath').val()+ "/GoodsController.do?method=list",
		"params":params,
		'searchZone':'GoodsSelectListForm'
	});
	
	// 查询
	$("#query").click(function() {
		GoodsSelectList.oTable.fnDraw();
	})

	//关闭
	$("#close").click(function(){
		$('.epsDialogClose').trigger('click');
	})
})
</script>
