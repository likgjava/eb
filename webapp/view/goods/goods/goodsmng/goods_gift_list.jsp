<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<input type="hidden" id="goodsId" value="${goods.objId}"/>
<input type="hidden" id="supplierId" value="${supplierId}"/>

<!-- 商品信息 -->
<div class="formLayout form2Pa">
	<h4 class="title"><span>商品信息</span></h4>
	<div class="k1">
		<div class="img_250_2" id="newPreview">
		<img width="200px" height="175px" src="<c:url value="AttachmentController.do?method=showImg&objId=${goods.picture}" />">
		</div>
	</div>
	<ul>
		<li>
			<label>商品名称：</label>
			${goods.productName}
		</li>
	 	<li>
	 		<label>规格型号：</label>
	 		${goods.productCode}
	 	</li>
	 	<li>
	 		<label>品牌名称：</label>
	 		${goods.goodsBrand.brandName}
	 	</li>
	 	<li>
	 		<label>采购品目：</label>
	 		${goods.purCategory.categoryName}
	 	</li>
	 	<li>
	 		<label>商品分类：</label>
	 		${goods.goodsClass.goodsClassName}
	 	</li>
	 	<li>
	 		<label>计量单位：</label>
	 		${goods.measureUnit}
	 	</li>
	 	<li>
	 		<label>发布日期：</label>
	 		<fmt:formatDate value="${goods.productDateIssued}" pattern="yyyy-MM-dd"/>
	 	</li>
	 	<li>
	 		<label>参考价(元)：</label>
	 		<span>￥<fmt:formatNumber value="${goods.referPrice}" pattern="#,##0.00#" /></span>
	 	</li>
	 	<li>
	 		<label>制造商：</label>
	 		${goods.factory}
	 	</li>
	 	<li>
	 		<label>产地：</label>
	 		${goods.madeIn}
	 	</li>
	 	<li class="fullLine">
	 		<label>外部链接：</label>
	 		${goods.externalInforLink}
	 	</li>
	 	<c:if test="${goods.paramInputType==02}">
	 		<li id="spec_1" class="fullLine">
 				<label>详细配置：</label>
 				${goods.spec}
 			</li>
	 	</c:if>
	</ul>
</div>

<!-- 新增操作  -->
<div class="formTips attention">
	<ul>
		<li>
			新增礼包请点击
			<span class="sysicon siAdd">
				<a id="addGoodsGift" style="hide-focus: true" href="javascript:void(0);"><strong>新增礼包</strong></a>
			</span>
		</li>
	</ul>
</div>

<!-- 列表 -->
<div>
	<table class="frontTableList" id="goodsGiftList">
		<thead>
			<tr>
				<th class="center">礼包图片</th>
				<th class="left">礼包名称</th>
				<th class="center">所属商品</th>
				<th class="operation">操作</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
</div>

<div class="conOperation ">
	<button class="largeBtn" id="returnBtn" type="button" ><span><spring:message code="globe.return"/></span></button>
</div> 

<script>
/**
 * 商品礼包列表页面
 * create by likg
 */
var GoodsGiftList = {};

//删除礼包
GoodsGiftList.deleteGift = function(goodsGiftId) {
	if(confirm("确定删除该商品礼包吗？")){
		var url = $('#initPath').val()+'/GoodsGiftController.do?method=remove';
		$.getJSON(url, {'objId':goodsGiftId}, function(json){
			if(json.success){
				GoodsGiftList.oTable.fnDraw(); //刷新列表
			}else{
				alert("操作失败！");
			}
		});
	}
}

//新增或修改礼包
GoodsGiftList.createOrUpdate = function(goodsGiftId) {
	var url = $('#initPath').val()+'/GoodsGiftController.do?method=toCreateOrUpdateGiftView';
	if(goodsGiftId!=null && goodsGiftId!=""){
		url += "&goodsGiftId=" + goodsGiftId;
	}
	$.epsDialog({
        title:'商品礼包信息',
        url:url,
        width: '700',
        height: '400',
        onClose: function(){
			GoodsGiftList.oTable.fnDraw(); //刷新列表
        }
    });
}

//查看
GoodsGiftList.view = function(goodsGiftId) {
	var url = $('#initPath').val()+'/GoodsGiftController.do?method=toGoodsGiftDetailView&goodsGiftId='+goodsGiftId;
	$.epsDialog({
		id:'goodsGiftDetailDiv',
        title:'商品礼包详细信息',
        url:url,
        width: '500',
        height: '300'
    });
}

$(document).ready(function() {

	//加载列表
	GoodsGiftList.oTable = $('#goodsGiftList').dataTable({
		'singleSelect' : true,
		'checkbox' : false,
		'queryColumns' : 'giftPicture,giftName,goods.productName',
		//'alias':'name,belongIndustry.name,auditStatusCN',
		'hiddenColums' : 'auditStatus',
		'fnInitComplete' : function(oSettings) {
		},
		'fnDrawCallback' : function(oSettings) {
			GoodsGiftList.oTable.oSettings = oSettings;
		},
		'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
			$(nRow).find("td[name=giftPicture]").empty().append('<img src="AttachmentController.do?method=showImg&objId='+aData.giftPicture+'" style="cursor:pointer" width="30px" height="30px" id="'+aData.giftPicture+'" />');
			//添加操作按钮
			var operStr = '<td class="operation">';
			operStr += '<a href="javascript:void(0);" title="删除礼包" onclick="GoodsGiftList.deleteGift(\'' + aData.objId + '\');return false;">删除</a>';
			operStr += '<a href="javascript:void(0);" title="修改礼包信息" onclick="GoodsGiftList.createOrUpdate(\'' + aData.objId + '\');return false;">修改</a>';
			operStr += '<a href="javascript:void(0);" title="查看礼包详情" onclick="GoodsGiftList.view(\'' + aData.objId + '\');return false;">查看</a>';
			operStr += '</td>';
			$(nRow).append(operStr);
			
			return nRow;
		},
		"sAjaxSource" : $('#initPath').val()+ "/GoodsGiftController.do?method=list",
		'params' : {'goods.objId':$("#goodsId").val(),'supplier.objId':$("#supplierId").val()},
		'searchZone':'expertInfoAuditListForm'
	});
	
	//查询
	$("#query").click(function() {
		GoodsGiftList.oTable.fnDraw();
	});
	
	//新增礼包
	$("#addGoodsGift").click(function() {
		GoodsGiftList.createOrUpdate();
	});

	//返回
	$("#returnBtn").click(function(){
		$('#conBody').loadPage($('#initPath').val()+'/view/goods/goodsprice/s_price_goods_list.jsp');
	});
});
</script>
