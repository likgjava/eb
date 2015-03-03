<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<form:form id="OrganizationChangeForm" method="post"> 
		<table class="frontTableList" id="goodsAuditList">
		<thead>
			<tr>
				<th class="omission" omiLength="20">机构代码</th>
				<th class="omission" omiLength="20">机构名称</th>
				<th>变更人</th>
				<th class="center">变更时间</th>
				<th class="center">状态</th>
				<th class="center">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
			<c:when test="${!empty modifyAuditList && fn:length(modifyAuditList) > 0}">
				<c:forEach var="changeOrg" items="${modifyAuditList}" varStatus="status1">
				<tr>
					<td >${changeOrg.orgInfo.orgCode}</td>
					<td>${changeOrg.orgInfo.orgName}</td>
					<td>${changeOrg.createUser.emp.name}</td>
					<td class="operation"><fmt:formatDate value="${changeOrg.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td class="operation">待审核</td>
					<td class="operation">
						<a href="javascript:void(0);" onclick="OrganizationChangeAuditListForm.toAuditModify('${changeOrg.orgInfo.objId}')">审核</a>
					</td>
				</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
			<tr><td colspan="4" align="center">没有待审核的记录</td></tr>
			</c:otherwise>
			</c:choose>
		</tbody>
		</table>
</form:form>		

<script>
var OrganizationChangeAuditListForm={};

//跳转到审核页面
OrganizationChangeAuditListForm.toAuditModify=function(orgId){
	$('#conBody').loadPage($('#initPath').val()+'/OrgInfoModifyController.do?method=toAuditModifyOrg&orgId='+orgId);
}

$(document).ready(function(){
	
})
</script>