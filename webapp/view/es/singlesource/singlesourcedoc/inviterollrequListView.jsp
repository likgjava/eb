<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="partContainers">
	<table class="tableList" id="inviterollrequListId">
		<caption>投标单位列表</caption>
  		<thead>
      		<tr class="center">
      			<th>投标单位名称</th>
          		<th>邀请函种类</th>
          		<th>审核状态</th>
     		</tr>
		</thead>
	<tbody>
		<c:forEach items="${inrqDetailList}" var="inrqDetail" varStatus="i">
		<tr>
			<td>${inrqDetail.supplier.orgName}</td>
			<td>${inrqDetail.inrqDKindCN}</td>
			<td>${inrqDetail.inviterollrequ.auditStatusCN}</td>
		</tr>
	</c:forEach>
	</tbody>
    </table>
</div>