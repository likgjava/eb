<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div id="firstGoodsInfo">
	<!-- 礼品信息 -->
	<div class="formLayout imgAndForm">
		<h4 class="title"><span>礼品信息</span></h4>
		<div class="k1">
			<div class="img_250_1" id="newPreview">
					<img width="200" height="175" src="<c:url value="AttachmentController.do?method=showImg&objId=${gift.picture}"/>"></img>
			</div>
		</div>
		<ul>
			<li class="fullLine"><label>礼品名称 ：</label> 
				<span>${gift.giftName }</span>
			</li>
			<li class="fullLine"><label>所属系列：</label>
	            <span>${gift.giftSeries.name}</span>
			</li>
			<c:if test="${gift.giftSupplier!=null}">
			<li><label>所属供货商：</label>
				<span>${gift.giftSupplier.supplierName }</span>
			</li>
			</c:if>
			<li class="fullLine"><label>礼品编号：</label> 
				<span>${gift.giftCode}</span>
			</li>
			<li class="fullLine"><label>兑换总数：</label> 
				<span>${gift.exchangeCount}</span>
			</li>
			<c:if test="${fn:length(gift.giftExchangeRuleSet)>0}">
				<c:forEach var="giftExchangeRule" items="${gift.giftExchangeRuleSet}" varStatus="statu">
					<c:choose>
						<c:when test="${statu.index==0}">
							<li name="rule" id="${giftExchangeRule.objId}"><label>兑换规则：</label> 
								<span>${giftExchangeRule.amount}</span>&nbsp;元+
								<span>${giftExchangeRule.score}</span>&nbsp;分
							</li>
						</c:when>
						<c:otherwise>
							<li name="rule" id="${giftExchangeRule.objId}"><label>或：</label> 
								<span>${giftExchangeRule.amount}</span>&nbsp;元+
								<span>${giftExchangeRule.score}</span>&nbsp;分
							</li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:if>
			<li class="fullLine"><label>开始时间：</label> 
				<span><fmt:formatDate value="${gift.startTime}" pattern="yyyy-MM-dd HH:mm:ss" /></span>至：<span><fmt:formatDate value="${gift.endTime}" pattern="yyyy-MM-dd HH:mm:ss" /></span>
			</li>
			<li class="fullLine"><label>是否启用：</label> 
				<span>${gift.isUsedCN}</span>
			</li>
		</ul>
		<h4>礼品描述信息</h4>
		<div>${gift.giftComment}</div>
	</div>
	
	<!-- 操作 -->
	<div class="conOperation">
		<button name="closeDetial" onclick="GiftDetail.close();"><span>关闭</span></button>
	</div>
</div>

<script type="text/javascript">
var GiftDetail={};

GiftDetail.close = function(){
	$('.epsDialogClose').trigger('click');
}

$(document).ready(function(){
})
</script>

	
