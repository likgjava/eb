<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script>
var OrgInfoHistoryList={};
$(document).ready(function(){
	//关闭
	$("#closeHistory").click(function(){
		$('.epsDialogClose').trigger('click');
	})
});
</script>

<ul>
	<c:choose>
	<c:when test="${!empty orgHistoryList && fn:length(orgHistoryList) > 0}">
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
				</tr>
			</thead>
			<tbody>
				<c:forEach var="changeOrg" items="${orgHistoryList}" varStatus="status1">
				<tr>
					<td class="operation">
						<c:if test="${changeOrg.modifyType=='orgName'}">机构名称</c:if>
						<c:if test="${changeOrg.modifyType=='orgCode'}">机构代码</c:if>
						<c:if test="${changeOrg.modifyType=='bidForRange'}">经营范围</c:if>
					</td>
					<td>
						${changeOrg.oldValue}
					</td>
					<td>
						${changeOrg.newValueName}
					</td>
					<td>
						<fmt:formatDate value="${changeOrg.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td class="operation">
						${changeOrg.createUser.emp.name}
					</td>
					<td class="operation">
						【${changeOrg.auditStatusCN}】
						<c:if test="${changeOrg.auditStatus=='03'}">
						意见：${changeOrg.opinion }
						</c:if>
					</td>
					<td>
						<fmt:formatDate value="${changeOrg.verifyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
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
	<button id="closeHistory" type="button"  class="largeBtn" ><span><spring:message code="globe.close"/></span></button>
</div>