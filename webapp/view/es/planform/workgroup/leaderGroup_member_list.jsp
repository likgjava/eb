<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/workgroup/leaderGroup_member_list.js"></script>
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
			<legend>&nbsp;&nbsp;[<a href="#" onclick="LeaderGroupMemberList.addWorkgMember('02');">新增</a>]</legend>
				<table class="tableList">
					<thead>
						<tr>
							<th>姓名</th>
							<th>工作单位</th>
							<th>类型</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${workgMemberList}" var="workgMember">
							<tr>
								<td>${workgMember.workgmName}</td>
								<td>${workgMember.workgmCompany}</td>
								<td>${workgMember.workgmTypeCN}</td>
								<td class="functionBtnDiv" style="text-align:center">
								<a onclick="LeaderGroupMemberList.updateWorkMember('${workgMember.objId}','02','${workgMember.user.emp.objId}')" class="styleA"><span>修改</span></a>
								<a onclick="LeaderGroupMemberList.delWorkMember('${workgMember.objId}')" class="styleA"><span>删除</span></a>
								</td>
							</tr>
							<input type="hidden" name="agentEmpId" value="${workgMember.user.emp.objId}"/>
						</c:forEach>
					</tbody>
				</table>
		</fieldset>
		<div class="conOperation" >
			<button id="finishBtn" type="button" tabindex="18"><span>完成组建领导小组</span></button>
		    <button id="printBtn" type="button" tabindex="18"><span>打印领导小组</span></button>
	    </div>
	</div>    
</div>
</div>