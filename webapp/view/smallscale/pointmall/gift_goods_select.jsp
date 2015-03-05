<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="conSearch">
<h4><span><spring:message code="globe.query" /></span></h4>	
<form id="GiftSelectListForm">

<input type="hidden" name="ctrlId" id="ctrlId" value="${param.ctrlId}" />
<input type="hidden" name="categoryId" id="categoryId" value="${param.categoryId}" />
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

<table class="frontTableList" id="GiftSelectList">
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
 		<button id="close" type="button"><span>关闭</span></button>
 		<button id="cler" type="button"><span>清空</span></button>
</div>

<script>
var GiftSelectList = {};
GiftSelectList.getOperatorStr=function(aData) {
	var operatorHtml = "";
	operatorHtml += '<td align="center">';
	operatorHtml += '<a  title="选择" href="javascript:void(0);" onclick="GiftSelectList.select(\''+aData.objId+'\',\''+aData.productName+'\',\''+aData.measureUnit+'\',\''+aData.referPrice+'\',\''+aData["goodsClass.objId"]+'\',\''+aData["goodsClass.goodsClassName"]+'\',\''+aData["purCategory.objId"]+'\',\''+aData["purCategory.categoryName"]+'\',\''+aData["picture"]+'\',\''+aData["functionIntro"]+'\');return false;">选择</a>';
	operatorHtml += '</td>';
	return operatorHtml;
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
	GiftSelectList.oTable = $('#GiftSelectList').dataTable({
		'singleSelect' : true,
		'checkbox' : false,
		'queryColumns' : 'productName,productCode,goodsBrand.brandName,referPrice',
		'alias' : 'productName,productCode,goodsBrand.brandName,referPrice',
		'hiddenColumns':'functionIntro,picture,auditStatus,measureUnit,goodsClass.objId,goodsClass.goodsClassName,purCategory.objId,purCategory.categoryName',
		'fnInitComplete' : function(oSettings) {
		},
		'fnDrawCallback' : function(oSettings) {
			GiftSelectList.oTable.oSettings = oSettings;
		},
		'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
			//添加操作按钮
			$(nRow).append('<td align="center"><a name="goodsSelect" title="选择" href="javascript:void(0);">选择</a></td>');
			$(nRow).find("a[name=goodsSelect]").click(function(){

				var pictureUrl = $("#initPath").val()+"/AttachmentController.do?method=showImg&objId="+aData.picture;
				$("#newPreview").html('<img src="'+pictureUrl+'" width="200px" height="175px">');//替换图片
				$("input[id=picture]").val(aData.picture);//回填id
				$("input[name=goods.objId]").val(aData.objId);//回填id
				$("span[id=goodsName]").html(aData.productName);//回显名称
				$("input[id=giftName]").val(aData.productName);//回显名称
				
				htmlEditor.setValue(aData.functionIntro); //编辑器赋值
				$('.epsDialogClose').trigger('click');
			})
			return nRow;
		},
		"sAjaxSource" : $('#initPath').val()+ "/GoodsController.do?method=list",
		"params":params,
		'searchZone':'GiftSelectListForm'
	});
	
	// 查询
	$("#query").click(function() {
		GiftSelectList.oTable.fnDraw();
	})

	//关闭
	$("#close").click(function(){
		$('.epsDialogClose').trigger('click');
	})

	//清空
	$("#cler").click(function(){
		$("#newPreview").html('<img src="'+$("#initPath").val()+'/view/resource/skin/goods/img/brand_add.gif" width="200px" height="175px">');//替换图片
		$("input[id=picture]").val("");//回填id
		$("input[name=goods.objId]").val("");//回填id
		$("span[id=goodsName]").empty();//回显名称

		$("input[id=giftName]").val("");//回显名称
		htmlEditor.setValue(''); //编辑器赋值清空
		
		$('.epsDialogClose').trigger('click');
	})
	
})
</script>
