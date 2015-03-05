<%@ page contentType="text/html;charset=UTF-8" %><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div>
	<table class="frontTableList">
	<thead>
		<tr>
			<th>包组名称</th>
			<th align="center">报名状态</th>
			<th align="center">标书费(元)</th>
			<th align="center">支付状态</th>
			<th align="center">缴费时间</th>
			<th align="center">操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="subProj" items="${subProjList}">
		<tr>
			<td>${subProj.projName }</td>
			<td align="center">
				<c:if var="hasSignUp" test="${fn:contains(signUprecordIds , subProj.objId)}">已报名</c:if>
				<c:if test="${!hasSignUp}">未报名</c:if>
			</td>
			<td align="right"><fmt:formatNumber value="${subProj.purDocPrice}" pattern="#,##0.00#"/></td>
			<c:set var="hasPay" value="0" />
			<c:forEach var="dosBuyRecord" items="${dosBuyRecordList}">
				<c:if test="${dosBuyRecord.tenderId==subProj.objId && dosBuyRecord.docBuyStatus=='01'}">
				<td align="center">已支付</td>
				<td align="center"><fmt:formatDate value="${dosBuyRecord.renderTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<c:set var="hasPay" value="1" />
				</c:if>
			</c:forEach>
			<c:if test="${hasSignUp && hasPay==0}">
				<td align="center">未支付</td>
				<td></td>
			</c:if>
			<td align="center">
				<c:if test="${hasSignUp && hasPay==0}">
					<a href="javascript:void(0);" onclick="ShowBulletinDetail.toDocPay('${subProj.objId}','${subProj.purDocPrice}');">缴费</a>
				</c:if>
			</td>
		</tr>
		</c:forEach>
	</tbody>
</table>
</div>
