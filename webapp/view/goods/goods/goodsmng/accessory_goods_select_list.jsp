<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="conSearch">
<h4><span><spring:message code="globe.query" /></span></h4>	
<form id="GoodsSelectListForm">

<input type="hidden" name="id" id="id" value="${param.id}" />
<input type="hidden" name="name" id="name" value="${param.name}" />
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
	operatorHtml += '<a  title="选择" href="javascript:void(0);" onclick="GoodsSelectList.select(\''+aData.objId+'\',\''+aData.productName+'\');return false;">选择</a>';
	operatorHtml += '</td>';
	return operatorHtml;
}

//选择商品
GoodsSelectList.select=function(goodsId,productName) {
	var ctrlId = $('#id').val();
	var ctrlName = $('#name').val();
	$('input[id='+ctrlId+']').val(goodsId);//商品ID
	$('input[id='+ctrlName+']').val(productName);//商品名称
	$('.epsDialogClose').trigger('click');
}

$(document).ready(function(){
	//开始时间
    $("#startDate").epsDatepicker();  //增加结束时间的规则
    //结束时间
    $("#endDate").epsDatepicker();  //增加开始时间的规则
	var categoryId = $('#categoryId').val();
    var params = {"useStatus":"01","isAccessory":"1"};
    if(categoryId) {
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
