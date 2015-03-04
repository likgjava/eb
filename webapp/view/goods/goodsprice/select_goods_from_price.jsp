<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/base/css/common.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/base/css/button.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/skin07/css/plugInEdit.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/skin07/css/baseEdit.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/skin07/css/supplierAgreement.css"/>

<script type="text/javascript">

/*
 * 脚本
 * created by yucy
 */
var selectGoodsbyPrice={};
selectGoodsbyPrice.oTable;
selectGoodsbyPrice.oTable2;

selectGoodsbyPrice.selected;

//搜索事件
selectGoodsbyPrice.searchFunction=function(){
	if(selectGoodsbyPrice.selected == "myPriceListA"){
		selectGoodsbyPrice.getselectGoodsbyPrice();
	}else{
		selectGoodsbyPrice.getselectGoodsList();
	}
}

//选择商品
selectGoodsbyPrice.select = function(goodsId,productName,measureUnit,marketPrice,goodsClass,goodsClassName,purCategory,categoryName) {
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

selectGoodsbyPrice.getOperatorStr=function(aData) {
	var operatorHtml = "";
	operatorHtml += '<td align="center">';
	operatorHtml += '<a title="选择" href="javascript:void(0);" onclick="selectGoodsbyPrice.select(\''+aData.objId+'\',\''+aData.productName+'\',\''+aData.measureUnit+'\',\''+aData.referPrice+'\',\''+aData["goodsClass.objId"]+'\',\''+aData["goodsClass.goodsClassName"]+'\',\''+aData["purCategory.objId"]+'\',\''+aData["purCategory.categoryName"]+'\');return false;">选择</a>';
	operatorHtml += '</td>';
	return operatorHtml;
}

//取得商品列表
selectGoodsbyPrice.getselectGoodsbyPrice = function(){
	if(null == selectGoodsbyPrice.oTable){
		selectGoodsbyPrice.oTable = $('#selectGoodsbyPrice').dataTable({
			'params':{"categoryId":$("#categoryId").val()},
			'searchZone':'priceSearchForm',
			'queryColumns' : 'productName,productCode,goodsBrand.brandName,referPrice,remark',

			'hiddenColumns':'referPrice',
			'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
				//加按钮
				$(nRow).append( selectGoodsbyPrice.getOperatorStr(aData) );
				
				return nRow;
			},
			"sAjaxSource" : $('#initPath').val()+ "/GoodsController.do?method=getGoodsListForSupplier&nobidForRange=true&validePrice=true"//不过滤品目的带行情商品列表
		});
	}else{
		selectGoodsbyPrice.oTable.fnDraw();
	}
}

//取得商品列表（全部）
selectGoodsbyPrice.getselectGoodsList = function(){

	if(null == selectGoodsbyPrice.oTable2 ){
		//加载商品列表
		selectGoodsbyPrice.oTable2 = $('#selectGoodsList').dataTable({
			'singleSelect' : true,
			'checkbox' : false,
			'queryColumns' : 'productName,productCode,goodsBrand.brandName,referPrice',
			'alias' : 'productName,productCode,goodsBrand.brandName,referPrice',
			'hiddenColumns':'auditStatus,measureUnit,goodsClass.objId,goodsClass.goodsClassName,purCategory.objId,purCategory.categoryName',
			'fnInitComplete' : function(oSettings) {
			},
			'fnDrawCallback' : function(oSettings) {
				selectGoodsbyPrice.oTable2.oSettings = oSettings;
			},
			'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
				//添加操作按钮
				$(nRow).append(selectGoodsbyPrice.getOperatorStr(aData))
				return nRow;
			},
			"sAjaxSource" : $('#initPath').val()+ "/GoodsController.do?method=list&purCategory.objId="+$("#categoryId").val(),
			'searchZone':'priceSearchForm'
		});
	}else{
		selectGoodsbyPrice.oTable2.fnDraw();
	}
}

$(document).ready(function(){

	//加载tabs,绑定选中事件为加载列表
	var $tabs = $('#epsTabs').tabs({
		select: function(event, ui) {
			if(ui.index==0){//我的行情
				selectGoodsbyPrice.getselectGoodsbyPrice();
			}else {//商品库
				selectGoodsbyPrice.getselectGoodsList();
			}
			selectGoodsbyPrice.selected = ui.tab.id;	
		}
	});

	//加载列表(第一次)
	selectGoodsbyPrice.getselectGoodsbyPrice();
	selectGoodsbyPrice.selected = $("#epsTabs").find("a:first").attr("id");
});

</script>
<input type="hidden" id="currentOrgId" value="${currentOrgId}"/>

<input type="hidden" name="ctrlIndex" id="ctrlIndex" value="${param.ctrlIndex}" />
<input type="hidden" name="ctrlId" id="ctrlId" value="${param.ctrlId}" />
<input type="hidden" name="categoryId" id="categoryId" value="${param.categoryId}"/>

<div>
	<!-- 搜索条件 -->
	<div class="conSearch">
		<h4><span>搜索</span></h4>
		<form id="priceSearchForm">
			<ul >
                <li>
                  <label for="productName">商品名称：</label>
                    <input type="text" name="productName" id="productName">
                    <input type="hidden" name="productName_op" value="like">
                    
                </li>
				<li>
                  <label for="goodsClass.goodsclassName">商品品牌：</label>
                    <input type="text" name="goodsBrand.brandName" id="brandName">
                    <input type="hidden" name="goodsBrand.brandName_op" value="like">
                </li>
                <li class="operationBtnDiv">
       				 <button type="button" onclick="selectGoodsbyPrice.searchFunction();"><span><spring:message code="globe.query" /></span></button>
     			 </li>
              </ul>
		</form>
	</div>
</div>

<div id="epsTabs">
	<ul>
		<li><a href="#myPriceList" id="myPriceListA" class="refreshData"><span>我的行情</span></a></li>
		<li><a href="#allGoodsList" id="allGoodsListA" class="refreshData"><span>所有商品</span></a></li>
	</ul>
	<div id="myPriceList">
		<!-- 行情列表 -->
		<table class="frontTableList" id="selectGoodsbyPrice">
			<thead>
				<tr>
					<th class="left omission" omiLength="15">商品名称</th>
					<th class="left omission" omiLength="15">规格型号</th>
					<th class="left">品牌</th>
					<th class="center money">市场价</th>
					<th class="center">我提供的行情数</th>
					<th class="operation">操作</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
	<div id="allGoodsList">
		<!-- 商品列表 -->
		<table class="frontTableList" id="selectGoodsList">
			<thead>
				<tr>
					<th class="omission" omiLength="10">商品名称</th>
					<th class="omission" omiLength="15">规格型号</th>
					<th class="omission" omiLength="10">品牌</th>
					<th class="money">市场价</th>
					<th class="operation">操作</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
</div>