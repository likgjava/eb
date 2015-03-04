<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script>
var GoodsHistoryList={};
$(document).ready(function(){
	//关闭
	$("#closeGoodsHistory").click(function(){
		$("#goodsChangeHistoryDiv").find('.epsDialogClose').trigger('click');
	})
});
</script>

<ul>
	<c:choose>
	<c:when test="${!empty goodsHistoryList && fn:length(goodsHistoryList) > 0}">
		<table class="tableList">
			<thead>
				<tr>
					<th class="operation">变更信息</th>
					<th>变更前</th>
					<th>变更后</th>
					<th>变更时间</th>
					<th class="operation">变更人</th>
					<th class="operation">状态</th>
					<th>审核时间</th>
					<th>审核意见</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="changeGoods" items="${goodsHistoryList}" varStatus="status1">
				<tr>
					<td class="operation">
						<c:if test="${changeGoods.modifyType=='purCategory'}">商品品目</c:if>
						<c:if test="${changeGoods.modifyType=='goodsClass'}">商品分类</c:if>
						<c:if test="${changeGoods.modifyType=='goodsBrand'}">商品品牌</c:if>
					</td>
					<td>
						${changeGoods.oldValueName}
					</td>
					<td>
						${changeGoods.newValueName}
					</td>
					<td>
						<fmt:formatDate value="${changeGoods.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td class="operation">
						${changeGoods.createUser.emp.name}
					</td>
					<td class="operation">
						${changeGoods.auditStatusCN}
					</td>
					<td>
						<fmt:formatDate value="${changeGoods.verifyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td title="${changeGoods.opinion}">
						${fn:length(changeGoods.opinion)>8?fn:substring(changeGoods.opinion,0,8):changeGoods.opinion }
						${fn:length(changeGoods.opinion)>8?'...':''}
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:when>
	<c:otherwise>
		<li class="fullLine">没有历史记录</li>
	</c:otherwise>
	</c:choose>
</ul>

<div id="orghistoryDiv">
</div>

<div class="conOperation">
	<button id="closeGoodsHistory" type="button"  class="largeBtn" ><span><spring:message code="globe.close"/></span></button>
</div>
