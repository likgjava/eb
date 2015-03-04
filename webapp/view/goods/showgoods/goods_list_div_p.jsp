<%@ page contentType="text/html;charset=UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div class="list-content">
	<ul class="list-view-enlarge">
		<c:forEach var="goods" items="${PAGERESULT.data}">
			<li class="list-item hlisting sell">
				<h3 class="summary"><a onclick="common.geToGoodsDetail('${goods.objId}');" href="javascript:void(0);">${goods.productName}&nbsp;${goods.productCode}</a></h3>
				<div class="photo"><a onclick="common.geToGoodsDetail('${goods.objId}');" href="javascript:void(0);"><img src="<c:url value="AttachmentController.do?method=showImg&objId=${goods.picture}" />"></a></div>
				<div class="price">
					<span class="item-price"> 参考价: <em> ￥<fmt:formatNumber value="${goods.referPrice}" pattern="#,##0.00#" /></em></span> 
					<span class="ww-light ww-large">
						<a href="javascript:void(0);" onclick="common.addFavorites('${goods.objId}','${goods.productName}','01');return false;"><img src="view/resource/skin/sysicon/16/award_star_add.png">&nbsp;收藏</a>
					</span>
				</div>
				<ul class="attribute">
					<li><img src="view/resource/skin/sysicon/16/calendar_add.png">&nbsp;<fmt:formatDate value="${goods.productDateIssued }" pattern="yyyy.MM.dd"/></li>
					<li><a onclick="common.geToGoodsDetail('${goods.objId}');" href="javascript:void(0);"><img src="view/resource/skin/sysicon/16/star.png">&nbsp;<fmt:formatNumber type="number" value="${goods.evalSum }" pattern="#0.0"/>&nbsp;分</a></li>
					<li><a href="javascript:void(0);" onclick="show_list.addToCartDirect('${goods.objId}','${goods.referPrice}','${goods.measureUnit }');return false;"><img src="view/resource/skin/sysicon/16/shop_car.png">&nbsp;购物车</a></li>
				</ul>
				<span class="other">
					<input type="checkbox" name="compareCheckBox" gid="${goods.objId}" gname="${goods.productName}" gcode="${goods.productCode}"
						<c:if test="${goods.paramInputType != '01'}">disabled = "disabled"</c:if> 
					/>
				</span>
			</li>
		</c:forEach>
	</ul>
</div>
<%@ include file="/view/pubservice/common/pageDirection.jsp" %>