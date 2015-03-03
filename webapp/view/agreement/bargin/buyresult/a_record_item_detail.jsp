<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<input id="projectId" name="projectId" type="hidden" value="${param.projectId }">
<input id="supplierId" name="supplierId" type="hidden" value="${param.supplierId }">

<input id="singlePrice" name="singlePrice" type="hidden" value="${param.singlePrice }"/>


<c:set var="totalQty" value="0"/>
<c:set var="marktTotal" value="0"/>
<div class="formLayout">
	<table class="tableList">
		<thead>
			<tr>
				<th>商品名称/描述</th>
				<th>市场价</th>
				<th>数量</th>
				<c:choose>
					<c:when test="${param.singlePrice=='1'||record.project.ebuyMethod=='05'}">
						<th>报价</th>
						<th>小计</th>
					</c:when>
					<c:otherwise>
						<td>报价商品</td>
					</c:otherwise>
				</c:choose>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="recordItem" items="${recordItemList}">
				<tr><td>
					<c:choose>
						<c:when test="${recordItem.requireItem.goods !=null }">
							${recordItem.requireItem.goods.productName } &nbsp;${recordItem.requireItem.goods.productCode }
						</c:when>
						<c:otherwise>	
							${recordItem.goods.productName } &nbsp;${recordItem.goods.productCode }
						</c:otherwise>
						
					</c:choose>
					<br>
					<c:choose>
							<c:when test="${!empty recordItem.requireItem.requireGoodsOpt && fn:length(recordItem.requireItem.requireGoodsOpt) > 0}">
								<strong>
									<c:forEach var ="regoodsOpt" items="${recordItem.requireItem.requireGoodsOpt }">
										${regoodsOpt.optionalFitting.optionContent }
									</c:forEach>
								</strong>
							</c:when>
							<c:otherwise>
							</c:otherwise>
						</c:choose>	
					</td>
						<c:choose>
							<c:when test="${recordItem.requireItem.goods!=null||recordItem.requireItem!=null }">
								<td align="right">
								<fmt:formatNumber value="${recordItem.goods.referPrice }" pattern="#,##0.00#" />
								</td>
							</c:when>
							<c:otherwise><td align="center">--</td></c:otherwise>
						</c:choose>
					<td align="right">${recordItem.requireItem.goodsQty }
						<c:set var="totalQty" value="${totalQty+ recordItem.requireItem.goodsQty}"/>
					</td>
					<c:set var="marktTotal" value="${marktTotal+recordItem.goods.referPrice*recordItem.requireItem.goodsQty }"/>
					<c:choose>
						<c:when test="${param.singlePrice=='1'||record.project.ebuyMethod=='05'}">
						<td align="right"><fmt:formatNumber value="${recordItem.goodsPrice }" pattern="#,##0.00#" /></td>
						<td align="right">
							<fmt:formatNumber value="${recordItem.goodsPrice * recordItem.requireItem.goodsQty }" pattern="#,##0.00#" />
						</td>
						</c:when>
						<c:otherwise>
						<td>
							<a href="javascript:void(0);" onclick="recordItemDetail.showDetail('${recordItem.goods.objId}');return false;" >${recordItem.goods.productName } &nbsp;${recordItem.goods.productCode }</a>
						</td>
						</c:otherwise>
					</c:choose>
				</tr>
			</c:forEach>
			<tr><td colspan="6" ></td></tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="6" align="right">
						数量总计：<em><span id="countGoods">${totalQty }</span></em>
						节省金额：￥<em><span id="totalAmount"><fmt:formatNumber value="${marktTotal-record.goodsTotal }" pattern="#,##0.00#" /></span></em>元
						商品总金额：<strong>￥<span id="totalAmount"><fmt:formatNumber value="${record.goodsTotal }" pattern="#,##0.00#" /></span></strong>元
				</td>
			</tr>
		</tfoot>
	</table>
</div>

<div id="bargainFile" value="${record.bargainFile}"></div>

<div class="conOperation">
	<button type="button" onclick="recordItemDetail.close()" ><span>关闭</span></button>
</div>
<script type="text/javascript">

var recordItemDetail = {};

//显示商品详情
recordItemDetail.showDetail = function(goodsId,tabId) {
	$.epsDialog({
		id:"showGoodsDetail",
		title:"商品详情",
		url:$("#initPath").val()+"/GoodsShowController.do?method=getGoodsInfo&viewName=goodsDetailView&viewFrom=bargain&fromDiv=yes&isShowPic=true&objId="+goodsId
	})
}

//关闭
recordItemDetail.close = function(){
	$("#recordItemDiv").find('.epsDialogClose').trigger('click');
}

$(document).ready(function(){

	var resId = $("#bargainFile").attr("value")
	$('#bargainFile').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=bargainFile&isSelect=yes&attachRelaId='+resId);
	
})
</script>