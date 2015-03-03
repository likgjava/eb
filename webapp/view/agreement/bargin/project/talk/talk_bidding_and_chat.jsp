<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<!-- 最低报价和聊天信息 -->
<table class="pripackage_date_list">
<thead>
	<tr>
  		<th class="col_title_1">
  			<span>报价信息</span>
  			<span class="alignR"><a class="sysicon report" onclick="TalkProjectDetail.showAllBiddingRecord('${sayOrgId}');" href="javascript:void(0);">历史报价</a></span>
  		</th>
		<th class="col_title_2">
			<em class="base_txtred">聊天信息</em>
		</th>
	</tr>
</thead>

<tbody id="minPriceRecordTable">
	<tr>
		<td class="price_td">
			<!-- 最低报价 开始 -->
			<c:forEach var="recordItem" items="${minBiddingRecord.biddingRecordItemSet}" >
			<div class="bidding_record" name="goods_${recordItem.goods.objId}">
				<div>${recordItem.goods.productName}</div>
				<div>
					<span class="base_txtred">￥<fmt:formatNumber value="${recordItem.goodsPrice}" pattern="#,##0.00#" />元</span>
					<c:if test="${userType == 'buyer'}">
					<span class="save_total">节省：￥<fmt:formatNumber value="${recordItem.requireItem.goodsPrice - recordItem.goodsPrice}" pattern="#,##0.00#" />元</span>
					</c:if>
				</div>
			</div>
			</c:forEach>
			<!-- 最低报价 结束 -->
		</td>
		<td class="col_sum">
			<!-- 聊天信息 开始 -->
			<div class="tdog-popup-talkhistory">
				<c:forEach var="chat" items="${chatList}">
					<p class="tdog-talk-others talk">
						<span class="tdog-talk-username">
							<c:choose>
								<c:when test="${sayOrgId == chat.sayOrgInfo.objId}"><span style="color: #4169E1;">${chat.sayOrgInfo.orgName }</span></c:when>
								<c:otherwise><span style="color: #66CD00;">${chat.sayOrgInfo.orgName }</span></c:otherwise>
							</c:choose>
						</span>
						<span class="tdog-talk-time">(<fmt:formatDate value="${chat.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>):</span>
					</p>
					<div class="tdog-talk-content talk">${chat.content }</div>
				</c:forEach>
			</div>
			<!-- 聊天信息 结束 -->
		</td>
	</tr>
</tbody>
</table>