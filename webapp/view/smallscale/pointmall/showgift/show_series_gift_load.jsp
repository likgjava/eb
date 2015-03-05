<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<c:forEach var = "seriesAndGift" items="${seriesAndReallyGift}">
              	<c:if test="${fn:length(seriesAndGift['giftList'])>0}">
              	
              	<h3><span><a href="javascript:void(0);" onclick="showList('${seriesAndGift['giftSeries'].objId}','','00');return false;">${seriesAndGift['giftSeries'].name}</a></span></h3>
            		<div class="giftList">
            		<c:forEach var ="gift" items="${seriesAndGift['giftList']}">
				<ul>
				
					<li class="giftBigImgLi"><a href="javascript:gift_list.showDetail('${gift.objId}');"><img class="giftImg bigSize" src="<c:url value="AttachmentController.do?method=showImg&objId=${gift.picture}" />" /></a></li>
					<li class="center"><a href="javascript:gift_list.showDetail('${gift.objId}');">${gift.giftName}</a></li>
					<li class="center">兑换积分：<em class="em"><c:forEach var="giftExchange" items="${gift.giftExchangeRuleSet}" end="0">${giftExchange.score}</c:forEach></em> 积分</li>
					<li class="center">已兑换${gift.realRecordQty}人[${gift.totalEvalQty}人评论]</li>
					<li class="center">
						<input type="button" class="PointsMallBtn" value="兑 换" onclick="exchange('${gift.objId}','<c:forEach var="giftExchangeRule" items="${gift.giftExchangeRuleSet}" end="0">${giftExchangeRule.objId}</c:forEach>','1','${gift.giftType }');" />
						<input type="button" class="PointsMallBtn" value="详 情" onclick="gift_list.showDetail('${gift.objId}');"/>
					</li>
				</ul>
            		</c:forEach>
            		</div>
            	</c:if>
</c:forEach>



<script type="text/javascript">
</script>