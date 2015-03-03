<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<style>
<!--
.layoutfix {display: inline-block;overflow: hidden;}
h2.under_line {border-bottom: 1px solid #D8D8D8;height:2px;}
.line_content td {color: #333333;padding: 3px 2px;vertical-align: top;}
.line_content th {color: #333333;padding: 3px 2px;vertical-align: top;}
.hi_60{height:60px;}
.hi_auto{height:auto;}
-->
</style>

<script type="text/javascript">

project_record_by_require_supplier = {}

//添加附件事件
project_record_by_require_supplier.downLoadFile = function(id){
	$.epsDialog({
		title:"附件下载",
		url:$("#initPath").val()+"/AttachmentController.do?defineSelf=qualificationFile&isSelect=yes&attachRelaId="+id,
		width: 600,
		height: 300
	});
}

//check事件
project_record_by_require_supplier.checkDetail = function(detailId,supplierId){
	//目标是选中
	if( $("input[id="+detailId+"]:checked") ){
		var checkedObj = $("input[name="+supplierId+"]:checked");
		$("input[name="+supplierId+"]").not("input[id="+detailId+"]").attr("checked",false);
	}
}

//标记最低价
project_record_by_require_supplier.tagLowPrice = function(){

	if( $("span[name=detailPrice]:eq(0)").html()==null ){
		return;
	}

	var tempPrice = Number( $("span[name=detailPrice]:eq(0)").html().replace("￥",'').replace(/,/g,'') ) ;

	var detailId = $("span[name=detailPrice]:eq(0)").attr("id") ;
	
	$.each( $("span[name=detailPrice]") ,function( index , obj ){
		if( tempPrice > Number( $(obj).html().replace("￥",'').replace(/,/g,'') ) ){
			tempPrice = Number( $(obj).html().replace("￥",'').replace(/,/g,'') );
			detailId = $(obj).attr("id");
		}
	})
	$("span[id="+detailId+"]").addClass("base_txtred");
}

//确认
project_record_by_require_supplier.confirmChoose = function(){

	var changedIdandValue = [];

	//取所有的check的id
	$.each( $("input[type=checkbox]") , function( index , obj ){
		changedIdandValue.push( $(obj).attr("id")+":"+ ($(obj).attr("checked")?"1":"0") +":"+ $("#supplierId").val() +":"+ $(obj).attr("price") ) 
	})
	
	//确认请求
	$.getJSON($("#initPath").val()+"/BiddingRecordDetailController.do?method=updateRecordChooseStatus",{ "changedIdandValue":changedIdandValue.toString(),"requireId":$("#requireId").val() },function(json){
		if(json.success){

			//定位回显 (逐行)
			$.each(json.resultList , function( index , obj ){
				var spanObj =  $("span[name=cellPrice][id="+obj[1]+","+obj[2]+"]");
				spanObj.html( formatAmount(obj[4],2) );
				obj[3]=='1'?spanObj.parent().parent().addClass("flag"):spanObj.parent().parent().removeClass("flag");
			})
			
			// 重新计算
			project_detail_view.caculator();
			$('.epsDialogClose').trigger('click');
		}
	})
	
}

$(document).ready(function(){
	//标记
	project_record_by_require_supplier.tagLowPrice();
})

</script>


<!-- 需求id -->
<input id="requireId" name="requireId" type="hidden"
	value="${param.requireId}" />
<!-- 供应商id -->
<input id="supplierId" name="supplierId" type="hidden"
	value="${param.supplierId}" />

<!-- 代理人可操作 -->
<c:if test="${currentOrgId==requireItem.project.buyersId || confirmResult }">
<div class="formTips attention">
	<em>注意：</em>您可以选择一个合适的报价进行作为标记。
	<span class="conOperation" style="float:right">
		<button onclick="project_record_by_require_supplier.confirmChoose();"><span>确定</span></button>
		<button onclick="$('.epsDialogClose').trigger('click');"><span>关闭</span></button>
	</span>
</div>
</c:if>

<c:set var="detailId" value="" />

<div class="itinerary_content layoutfix"
	id="package_detail_line0-page1_day2"
	style="width: 100%; background: none repeat scroll 0 0 #DDDDDD;">
	<c:set var="requrieDesc"
		value="${requireItem.goods!=null?requireItem.goods.productName:requireItem.descr}" />
	<table class="line_content" style="width: 100%;">
		<tr>
			<!-- 需求名称 -->
			<th title="${requrieDesc}"><b>需求商品/描述:</b> <br />
				<c:if test="${requireItem.goods!=null}"><a onclick="common.geToGoodsDetail('${requireItem.goods.objId}');" href="javascript:void(0);">${requrieDesc}</a></c:if>
				<c:if test="${requireItem.goods==null}">${requrieDesc}</c:if>
			</th>
			<th style="width: 150px;"><b>报价时间</b></th>
			<th style="width:310px;"><b>需求预算:</b> <br />￥<fmt:formatNumber
				value="${requireItem.goodsPrice*requireItem.goodsQty}"
				pattern="#,##0.00#" />（元）</th>
		</tr>
	</table>
</div>

<c:set var="requrieId" value="" />

<c:forEach var="biddingRecordObject" items="${biddingRecordObjectList}"
	varStatus="status">
	<c:if test="${requrieId != biddingRecordObject[0]}"><h2 class="under_line"></h2></c:if>
	<div class="itinerary_content layoutfix"
		id="package_detail_line0-page1_day2" style="width: 100%;">
			<table class="line_content" style="width: 100%;">
				<tr>
					<td title="${biddingRecordObject[4]}" >
						<c:if test="${requrieId != biddingRecordObject[0]}">
							<input onchange="project_record_by_require_supplier.checkDetail('${biddingRecordObject[0]}','${requireItem.objId}');"
							price="${biddingRecordObject[2]}" name="${requireItem.objId}" id="${biddingRecordObject[0]}" type="checkbox"
							<c:if test="${biddingRecordObject[3]==49}">checked="checked"</c:if> />
								<c:if test="${biddingRecordObject[8]!=null && requireItem.goods==null}">
									<a onclick="common.geToGoodsDetail('${biddingRecordObject[8]}');" href="javascript:void(0);">${biddingRecordObject[4]}</a><BR/>${biddingRecordObject[7]}
								</c:if>
								<c:if test="${biddingRecordObject[8]==null && requireItem.goods==null}">
									${biddingRecordObject[4]}
								</c:if>
								<c:if test="${requireItem.goods!=null}">
									报价商品同需求商品
								</c:if>
						</c:if>
						<c:if test="${biddingRecordObject[9]!=null}"><br>【备注】：${biddingRecordObject[9] }</c:if>
						
						<c:set var="requrieId" value="${biddingRecordObject[0]}" />
					</td>
					<td style="width: 150px;">${biddingRecordObject[1]}</td>
					<td style="width: 250px;">
						<span name="detailPrice" id="${biddingRecordObject[6]}">￥<fmt:formatNumber value="${biddingRecordObject[2]}" pattern="#,##0.00#" /></span>
						<i class="save_total">节省：<fmt:formatNumber value="${requireItem.goodsPrice*requireItem.goodsQty - biddingRecordObject[2]}" pattern="#,##0.00#" /></i>
					</td>
					<td style="width: 60px;"><c:if test="${detailId != biddingRecordObject[0]}">
						<a href="javascript:void(0);" onclick="project_record_by_require_supplier.downLoadFile('${biddingRecordObject[5]}');">报价文件</a>
					</c:if></td>
				</tr>
				<c:set var="detailId" value="${biddingRecordObject[0]}" />
			</table>
	</div>
</c:forEach>
<h2 class="under_line"></h2>