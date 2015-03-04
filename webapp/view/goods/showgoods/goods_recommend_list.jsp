<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript">
var show_list1 = {};
//显示商品详情
show_list1.showDetail = function(goodsId) {
	common.geToGoodsDetail(goodsId );
}

$(document).ready(function(){
	if($("#contentSupp").find("span[id=otherDiv]").html()==null){
		if($("#contentSupp").find("div").html()!=null){
			$("#contentSupp").find("div:first").before('<span id="otherDiv"></span>');
		}else{
			$("#contentSupp").append('<span id="otherDiv"></span>');
		}
		$("#contentSupp").find("#otherDiv").loadPage($("#initPath").val()+"/view/pubservice/common/customer_service_div.jsp");
	}
})
</script>

<c:if test="${fn:length(recommendGoodsList)!= 0}">
<div class="workNews hotProduct" >
<!--推荐商品 开始-->
<h4><span class="jiantou">推荐商品</span></h4>
<ul class="center" id="recommendProduct">
	<c:forEach var="recommendGoods" items="${recommendGoodsList}">
	<li>
		<div class="goodsPic">
			<a href="javascript:void(0);" onclick="show_list1.showDetail('${recommendGoods.goods.objId}');return false;">
			<img src="<%=request.getContextPath()%>/AttachmentController.do?method=showImg&objId=${recommendGoods.goods.picture}&fileNameSuffix=_160*160">
			</a>
		</div>
		<div><strong>￥ <fmt:formatNumber value="${recommendGoods.goods.referPrice}" pattern="#,##0.00#" /></strong></div>
		<div><a href="javascript:void(0);" onclick="show_list1.showDetail('${recommendGoods.goods.objId}');return false;">${recommendGoods.goods.productName}&nbsp;${recommendGoods.goods.productCode}</a></div>
	</li>
	</c:forEach>
</ul>
<!--推荐商品 结束-->
</div>
</c:if>