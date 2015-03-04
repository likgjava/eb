<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/workgroup/leaderGroupViewMember.js"></script>
<style>
<!--
.styleA{
cursor: hand;
text-decoration: underline;
}
-->

</style>
<div id="workMemberDiv">
<div class="partContainers">
		<input type="hidden" id="subOrProjectId" value="${projectId}"/>
		<input type="hidden" id="groupType" value="${groupType}"/>
		<input type="hidden" id="workGroupId" value="${workGroup.objId}"/>
	<div class="formLayout form2Pa">
		<h5><span>组建<dm:out value="local__OpenTendering__openBid_zh">领导</dm:out>小组信息</span></h5>        
 
		<fieldset class="operationDiv">
				<table class="tableList">
					<thead>
						<tr>
							<th>姓名</th>
							<th>工作单位</th>
							<th>类型</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${workgMemberList}" var="workgMember">
							<tr>
								<td>${workgMember.workgmName}</td>
								<td>${workgMember.workgmCompany}</td>
								<td>${workgMember.workgmTypeCN}</td>
							</tr>
							<input type="hidden" name="agentEmpId" value="${workgMember.user.emp.objId}"/>
						</c:forEach>
					</tbody>
				</table>
		</fieldset>
	</div>    
</div>
</div>