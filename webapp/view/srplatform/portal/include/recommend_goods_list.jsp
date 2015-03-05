<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript">
showWellGoodsDetail = function(objId) {
	if(!objId){
		window.open( $('#initPath').val()+"/GoodsShowController.do?method=toGoodsList&rp=20&page=1");
	}else{
		common.geToGoodsDetail( objId );
	}
}

</script>
<div class='goodsShowList'>
	<ul class="verticalShowList">
	   <c:forEach var="recommendGoods" items="${recommendGoodsList}" varStatus="status">
	     <li <c:if test="${status.index%5 == 4}">class="omega"</c:if>>
	        <h3><a href="javascript:void(0);" onclick="showWellGoodsDetail('${recommendGoods.goods.objId}');return false;" title="${recommendGoods.goods.productName}">
	        	<c:choose>
    				<c:when test="${fn:length(recommendGoods.goods.productName) > 22 }">${fn:substring(recommendGoods.goods.productName,0,21)}…</c:when>
    				<c:otherwise>${recommendGoods.goods.productName }</c:otherwise>
				</c:choose>
	        </a></h3>
	        <p class="hotImg"><a href="javascript:void(0);" onclick="showWellGoodsDetail('${recommendGoods.goods.objId}');return false;"><img style="width: 120px; height: 120px;" src="AttachmentController.do?method=showImg&objId=${recommendGoods.goods.picture}&fileNameSuffix=_160*160" /></a></p>
	        <p class="hotPrice"><em>￥<fmt:formatNumber value="${recommendGoods.goods.referPrice}" pattern="#,##0.00" /></em></p>
	     </li>
	   </c:forEach>
	</ul>
</div>
<div class="more"><a href="javascript:void(0);" onclick="showWellGoodsDetail();" title="更多">更多</a></div>
