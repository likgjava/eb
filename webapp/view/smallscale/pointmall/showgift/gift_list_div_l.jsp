<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div class="list-content">
	<ul class="list-view hlisting sell">
		<c:forEach var="gift" items="${PAGERESULT.data}">
			<li class="list-item">
				<h3 class="summary"><a class="EventCanSelect" href="javascript:void(0);" onclick="gift_list.showDetail('${gift.objId}');return false;">${gift.giftName}</a></h3>
				<div class="photo">
					<img src="<c:url value="AttachmentController.do?method=showImg&objId=${gift.picture}" />">
				</div> 
		 		<ul class="attribute">                        	
		            <li class="legend2">
		            	<img src="view/resource/skin/sysicon/16/star.png">&nbsp;
		            	兑换人数:${gift.virtualRecordQty+gift.realRecordQty}[${gift.totalEvalQty}人评论]
		            </li>
		        </ul>
		        <c:if test="${gift.goods!=null}">
		        <p class="seller lister hCard">商品参数：
		        	<c:choose>
		        		<c:when test="${fn:length(gift.goods.spec) == 0}">暂无说明</c:when>
		        		<c:when test="${fn:length(gift.goods.spec) < 50}">${gift.goods.spec}</c:when>
		        		<c:otherwise>${fn:substring(gift.goods.spec,0,50)}...</c:otherwise>
		        	</c:choose>
		        </p>
	        	</c:if>
		    </li>
		</c:forEach>
	</ul>
</div>
<div>
	<%@ include file="/view/pubservice/common/pageDirection.jsp" %>
</div>