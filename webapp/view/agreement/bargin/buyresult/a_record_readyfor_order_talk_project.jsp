<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<form id="hiddenForm">
<input id="projectId" name="projectId" type="hidden" value="${param.projectId }">
<input id="supplierId" name="supplierId" type="hidden" value="${param.supplierId }">
<input id="singlePrice" name="singlePrice" type="hidden" value="${param.singlePrice }"/>
<input id="appType" name="appType" type="hidden" value="${param.appType }"/>
</form>

<c:if test="${param.singlePrice=='0'}">
<div class="formTips attention"><ul><li>
			<em>注意：</em>此项目属于总体报价，不得单个商品生成订单
</li></ul></div>
</c:if>

<c:set var="totalQty" value="0"/>
<c:set var="marktTotal" value="0"/>
<table class="tableList" id="goodsAndOption">
	<thead>
		<tr>
			<c:if test="${param.singlePrice=='1'}">
				<th style="width:40px;"><input type="checkbox"  onclick="recordItemDetail.checkAllOrNot('checkCell',this)"></th>
			</c:if>
			<th>商品名称/描述</th>
			<th>市场价(元)</th>
			<th>数量</th>
			<c:if test="${param.singlePrice=='1'||record.project.ebuyMethod=='05'}">
				<th>报价(元)</th>
				<th>小计(元)</th>
			</c:if>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="recordItem" items="${biddingRecord.biddingRecordItemSet}">
			<tr>
				
				<td style="width:40px;" class="center <c:if test="${param.singlePrice=='0'}"> hidden</c:if>"><input type="checkbox" name="checkCell" value="${recordItem.objId }" <c:if test="${param.singlePrice=='0'}">checked="checked" </c:if>></td>
				<td>
				<c:choose>
					<c:when test="${recordItem.requireItem.goods !=null }">
						<a href="javascript:void(0);" onclick="recordItemDetail.showDetail('${recordItem.requireItem.goods.objId}');return false;"  >${recordItem.requireItem.goods.productName } &nbsp;${recordItem.requireItem.goods.productCode }</a>
					</c:when>
					<c:otherwise>	
						<a href="javascript:void(0);" onclick="recordItemDetail.showDetail('${recordItem.goods.objId}');return false;" >${recordItem.goods.productName } &nbsp;${recordItem.goods.productCode }</a>
					</c:otherwise>
				</c:choose>
				<!-- 有礼包 -->
				<c:if test="${!empty recordItem.requireItem.requireGoodsGifts && fn:length(recordItem.requireItem.requireGoodsGifts) > 0}">
					<a href="javascript:void(0);" title="商品礼包详情" onclick="recordItemDetail.showOrHideGoodsGift(this);return false;"><img src="view/resource/skin/sysicon/16/goods_gift.png" /></a>
				</c:if>
				<!-- 有零配件  -->
				<c:if test="${!empty recordItem.requireItem.requireGoodsAccess && fn:length(recordItem.requireItem.requireGoodsAccess) > 0}">
					<a href="javascript:void(0);" title="商品零配件详情" onclick="recordItemDetail.showOrHideGoodsAccess(this);return false;"><img src="view/resource/skin/sysicon/16/goods_access.png" /></a>
				</c:if>
				<br>
				<c:choose>
						<c:when test="${!empty recordItem.requireItem.requireGoodsOpt }">
							<strong>
								<c:forEach var ="regoodsOpt" items="${recordItem.requireItem.requireGoodsOpt }">
									${regoodsOpt.optionalFitting.optionContent } &nbsp;
								</c:forEach>
							</strong>
						</c:when>
						<c:otherwise>
						</c:otherwise>
					</c:choose>	
				</td>
				<c:choose>
					<c:when test="${recordItem.requireItem.goods!=null||recordItem.requireItem!=null}">
						<td align="right">
							<fmt:formatNumber value="${recordItem.goods.referPrice}" pattern="#,##0.00#" />
						</td>
					</c:when>
					<c:otherwise><td align="center">--</td></c:otherwise>
				</c:choose>
				<c:set var="marktTotal" value="${marktTotal+recordItem.goods.referPrice*recordItem.requireItem.goodsQty }"/>
				<td align="right">${recordItem.requireItem.goodsQty }
					<c:set var="totalQty" value="${totalQty+ recordItem.requireItem.goodsQty}"/>
				</td>
				<c:if test="${param.singlePrice=='1'||record.project.ebuyMethod=='05'}">
					<td align="right">
						<fmt:formatNumber value="${recordItem.goodsPrice }" pattern="#,##0.00#" />
					</td>
					<td align="right">
						<fmt:formatNumber value="${recordItem.goodsTotal }" pattern="#,##0.00#" />
					</td>
				</c:if>
			</tr>
			
			<!-- 礼包 -->
			<c:if test="${!empty recordItem.requireItem.requireGoodsGifts && fn:length(recordItem.requireItem.requireGoodsGifts) > 0}">
			<tr class="hidden">
				<td colspan="6">
					<div>
					<table>
						<tr>
						<c:forEach items="${recordItem.requireItem.requireGoodsGifts}" var="requireGoodsGift">
						<td>
						   <div style="float:left">
								<img class="goodsImg" src='<c:url value="AttachmentController.do?method=showImg&objId=${requireGoodsGift.goodsGift.giftPicture}" />' >
							 	<div class="fitting" style="float:left">
							 	${requireGoodsGift.goodsGift.giftName}<br/>
							 	￥<fmt:formatNumber value="${requireGoodsGift.giftPrice}" pattern="#,##0.00#"/><br/>
							 	</div>
						   </div>
						</td>
						</c:forEach>
						</tr>
					</table>
					</div>
				</td>
			</tr>
			</c:if>
			
			<!-- 零配件 -->
			<c:if test="${!empty recordItem.requireItem.requireGoodsAccess && fn:length(recordItem.requireItem.requireGoodsAccess) > 0}">
			<tr class="hidden">
				<td colspan="6">
					<div>
					<table>
						<tr>
						<c:forEach items="${recordItem.requireItem.requireGoodsAccess}" var="requireGoodsAccess">
						<td>
							<div style="float:left">
								<img class="goodsImg" src='<c:url value="AttachmentController.do?method=showImg&objId=${requireGoodsAccess.goodsAccess.accessoryGoods.picture}" />' >
								<div class="fitting" style="float:left">
								${requireGoodsAccess.goodsAccess.accessoryGoods.productName}<br/>
								￥<fmt:formatNumber value="${requireGoodsAccess.accessPrice}" pattern="#,##0.00#"/>
								</div>
							</div>
						</td>
						</c:forEach>
						</tr>
					</table>
					</div>
				</td>
			</tr>
			</c:if>
		</c:forEach>
		<tr><td colspan="6" ></td></tr>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="6" align="right">
					数量总计：<em><span id="countGoods">${totalQty }</span></em>
					节省金额：￥<em><span id="totalAmount"><fmt:formatNumber value="${marktTotal-biddingRecord.goodsTotal }" pattern="#,##0.00#" /></span></em>元
					商品总金额：<strong>￥<span id="totalAmount"><fmt:formatNumber value="${biddingRecord.goodsTotal }" pattern="#,##0.00#" /></span></strong>元
			</td>
		</tr>
	</tfoot>
</table>

<div class="conOperation">
	<button type="button" onclick="recordItemDetail.close()" ><span>关闭</span></button>
	<button type="button" onclick="recordItemDetail.createOrderByRecord()" ><span>生成订单</span></button>
</div>
<script type="text/javascript">

var recordItemDetail = {};

//全选或全不选事件
recordItemDetail.checkAllOrNot = function(name,e){
	$("input[name*="+name+"]").attr("checked",$(e).attr("checked"));
}

//显示商品详情
recordItemDetail.showDetail = function(goodsId,tabId) {
	$.epsDialog({
		id:"showGoodsDetail",
		title:"商品详情",
		url:$("#initPath").val()+"/GoodsShowController.do?method=getGoodsInfo&viewName=goodsDetailView&viewFrom=bargain&fromDiv=yes&isShowPic=true&objId="+goodsId
	})
}

//生成订单
recordItemDetail.createOrderByRecord = function(){
	var itemIds = [];
	$.each($("input[name=checkCell]:checked"), function(index,obj){
		itemIds.push($(obj).val());
	})
	if(itemIds.length<1){alert("请至少选择一个商品！");return;}

	var appType = $("#hiddenForm").find("input[name=appType]").val()=="XYGH"?"XYGH":"XEJY";

	//生成订单
	$.getJSON($("#initPath").val()+"/OrderController.do?method=createOrderByBidding",{"biddingRecordItem":itemIds.toString(),"appType":appType},function(json){
		if(json.success){
			$("#conBody").loadPage($('#initPath').val()+"/OrderController.do?method=toOrderForm&appType="+appType+"&type=edit_toSubmit&objId="+json.order.objId);
			$('.epsDialogClose').trigger('click');
		}else{
			alert(json.result);
		}
	});
}

//关闭
recordItemDetail.close = function(){
	$("#resultCreateOrderDiv").find('.epsDialogClose').trigger('click');
}

//显示或隐藏商品礼包
recordItemDetail.showOrHideGoodsGift = function(obj){
	$(obj).parent().parent().next().toggle();
}
//显示或隐藏商品零配件
recordItemDetail.showOrHideGoodsAccess = function(obj){
	$(obj).parent().parent().next().toggle();
}

$(document).ready(function(){
	
})
</script>