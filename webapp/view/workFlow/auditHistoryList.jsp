<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<c:if test="${fn:length(data) > 0}">
	<div style="width: 100%;height: 10px;"></div>
	<table class="tableList">
		<caption title="隐藏/收缩 审批历史" class="shrink" style="cursor: hand"><span>审批历史</span></caption>
		<thead>
			<tr>
				<th style="width: 140px;"><span style="width:140px;display: block;">单位名称</span></th>
				<th style="width: 140px;"><span style="width:140px;display: block;">部门名称</span></th>
				<th style="width: 70px;"><span style="width:70px;display: block;">审核人</span></th>
				<th style="width: 70px;"><span style="width:70px;display: block;">审核时间</span></th>
				<th style="width: 170px;"><span style="width:170px;display: block;">审核结果</span></th>
				<th><span>审核意见</span></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${data}" var="history">
				<tr>
					<td><span style="width:140px;display: block;color:#666">${history["createUser.emp.company.name"]}</span></td>
					<td><span style="width:140px;display: block;color:#666">${history["createUser.emp.department.name"]}</span></td>
					<td><span style="width:70px;display: block;color:#666">${history["createUser.emp.name"]}</span></td>
					<td class="name"><span style="width:70px;display: block;color:#666">${fn:substring(history.createTime,0,10)}</span></td>
					<td>
						<span style="width:170px;display: block;color:#666">
							${history.bizName}
							${history.disposeResultCN}
						</span>
					</td>
					<td style="color:#666">${history.content}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</c:if>