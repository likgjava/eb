<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<h2>最新加入会员</h2>
<table>
	<thead>
		<tr>
			<th>企业名称</th>
			<th>行业</th>
			<th>地区</th>
			<th>加入时间</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="newMember" items="${newMemberList}">
		<tr>
			<td>
				<a href="javascript:void(0);" title="${newMember.orgInfo.orgName}" onclick="shangquanIndex.showOrgDetail('${newMember.orgInfo.objId}','${newMember.orgInfo.buyerId}','${newMember.orgInfo.supplierId}');return false;">
				<c:choose>
							<c:when test="${fn:length(newMember.orgInfo.orgName) > 11}">${fn:substring(newMember.orgInfo.orgName,0,11)}…</c:when>
							<c:otherwise>${newMember.orgInfo.orgName}</c:otherwise>
						</c:choose>
						</a>
					</td>
			<td title="${newMember.orgInfo.belongIndustry.name}">
				<c:choose>
							<c:when test="${fn:length(newMember.orgInfo.belongIndustry.name) > 13}">${fn:substring(newMember.orgInfo.belongIndustry.name,0,13)}…</c:when>
							<c:otherwise>${newMember.orgInfo.belongIndustry.name}</c:otherwise>
						</c:choose>
			</td>
			<td class="center" title="${newMember.orgInfo.distinctName}">${fn:substring(newMember.orgInfo.distinctName,0,3)}</td>
			<td class="center"><fmt:formatDate value="${newMember.createTime}" pattern="MM-dd"/></td>
		</tr>
		</c:forEach>
	</tbody>
</table>
<div class="more"><a href="javascript:void(0);" onclick="shangquanIndex.toShowBusinessMemberListView();return false;" class="right">更多</a></div>
