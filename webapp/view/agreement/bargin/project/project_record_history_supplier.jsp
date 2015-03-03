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

<div class="formTips attention">
	<em>注意：</em>对需求的所有报价。
	<span class="conOperation" style="float:right">
		<button onclick="$('.epsDialogClose').trigger('click');"><span>关闭</span></button>
	</span>
</div>

<c:set var="detailId" value="" />

<div class="itinerary_content layoutfix"
	id="package_detail_line0-page1_day2"
	style="width: 100%; background: none repeat scroll 0 0 #DDDDDD;">
	<c:set var="requrieDesc"
		value="${requireItem.goods!=null?requireItem.goods.productName:requireItem.descr}" />
	<table class="line_content" style="width: 100%;">
		<tr>
			<!-- 需求名称 -->
			<th title="${requrieDesc}"><b>需求描述:</b> <br />${requrieDesc}</th>
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
							<c:choose>
								<c:when test="${biddingRecordObject[8]!=null}">
									<a onclick="common.geToGoodsDetail('${biddingRecordObject[8]}');" href="javascript:void(0);">${biddingRecordObject[4]}</a><BR/>${biddingRecordObject[7]}
								</c:when>
								<c:otherwise>
									${biddingRecordObject[4]}
								</c:otherwise>
							</c:choose>
						</c:if>
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