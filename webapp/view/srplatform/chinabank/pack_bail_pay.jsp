<%@ page contentType="text/html;charset=UTF-8" %><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div>
	<table class="frontTableList">
	<thead>
		<tr>
			<th>包组名称</th>
			<th align="center">报名状态</th>
			<th align="center">保证金(元)</th>
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
			<td align="right"><fmt:formatNumber value="${subProj.bail}" pattern="#,##0.00#"/></td>
			<c:set var="hasPay" value="0" />
			<c:forEach var="bailRecord" items="${bailRecordList}">
				<c:if test="${bailRecord.projId==subProj.objId && bailRecord.bailStatus=='01'}">
				<td align="center">已支付</td>
				<td align="center"><fmt:formatDate value="${bailRecord.renderTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<c:set var="hasPay" value="1" />
				</c:if>
			</c:forEach>
			<c:if test="${hasSignUp && hasPay==0}">
				<td align="center">未支付</td>
				<td></td>
			</c:if>
			<td align="center">
				<c:if test="${hasSignUp && hasPay==0}">
					<a href="javascript:void(0);" onclick="ShowBulletinDetail.toBailPay('${subProj.objId}','${subProj.bail}');">缴费</a>
				</c:if>
			</td>
		</tr>
		</c:forEach>
	</tbody>
</table>
</div>
