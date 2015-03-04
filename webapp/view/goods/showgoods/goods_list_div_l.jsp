<%@ page contentType="text/html;charset=UTF-8" %><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div class="list-content">
	<ul class="list-view hlisting sell">
		<c:forEach var="goods" items="${PAGERESULT.data}">
			<li class="list-item">
				<h3 class="summary" style="width: 300px;"><a class="EventCanSelect" onclick="common.geToGoodsDetail('${goods.objId}');" href="javascript:void(0);">${goods.productName}&nbsp;${goods.productCode}</a></h3>
				<div class="photo">
					<a onclick="common.geToGoodsDetail('${goods.objId}');" href="javascript:void(0);">
					<img src="<%=request.getContextPath()%>/AttachmentController.do?method=showImg&objId=${goods.picture}&fileNameSuffix=_80*80">
					</a>
				</div> 
		 		<ul class="attribute">                        	
		            <li class="legend2">
		            	<a onclick="common.geToGoodsDetail('${goods.objId}');" href="javascript:void(0);"><img src="<%=request.getContextPath()%>/view/resource/skin/sysicon/16/star.png">&nbsp;<fmt:formatNumber type="number" value="${goods.evalSum }" pattern="#0.0"/>分</a>
		            	<a href="javascript:void(0);" onclick="common.addFavorites('${goods.objId}','${goods.productName}','01');return false;"><img src="<%=request.getContextPath()%>/view/resource/skin/sysicon/16/award_star_add.png">&nbsp;收藏</a>
		            	<a href="javascript:void(0);" onclick="show_list.addToCartDirect('${goods.objId}','${goods.referPrice}','${goods.measureUnit }');return false;"><img src="<%=request.getContextPath()%>/view/resource/skin/sysicon/16/shop_car.png">&nbsp;加入购物车</a></li>
		            <li class="srvlist">
		            	<c:choose><c:when test="${empty goods.productDateIssued}">&nbsp;</c:when><c:otherwise><fmt:formatDate value="${goods.productDateIssued }" pattern="yyyy.MM.dd"/></c:otherwise></c:choose>
					</li>
		            <li class="price"> 参考价: <em> ￥<fmt:formatNumber value="${goods.referPrice}" pattern="#,##0.00#" /></em></li>
		        </ul>
		        <div class="extend"></div>
		        <p class="seller lister hCard">商品参数：
		        	<c:choose><c:when test="${fn:length(goods.spec) == 0}">暂无说明</c:when><c:when test="${fn:length(goods.spec) < 50}">${goods.spec}</c:when><c:otherwise>${fn:substring(goods.spec,0,50)}...</c:otherwise></c:choose>
		        </p>
		        <span class="other">
		        	<input type="checkbox" name="compareCheckBox" gid="${goods.objId}" gname="${goods.productName}" gcode="${goods.productCode}"
		        		<c:if var="noCompare" test="${goods.paramInputType != '01'}">disabled = "disabled"</c:if><c:if test="${!noCompare}">title="点击进行商品对比"</c:if>
		        	/>
		        </span>
		    </li>
		</c:forEach>
	</ul>
</div>
<div><%@ include file="/view/pubservice/common/pageDirection.jsp" %></div>