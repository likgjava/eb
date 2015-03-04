<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/workgroup/openBidInfo_member_list.js"></script>
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
		<input type="hidden" id="workGroupId" value="${workGroupId}"/>
	<div >
		<h5><span>组建<dm:out value="local__OpenTendering__openBid_zh">开标</dm:out>小组信息</span></h5>        
	    	 <%-- 暂时隐藏 --%>
	    	 <ul style="display: none">
	    		<li>
					<label  class="short"><dm:out value="local__OpenTendering__openBid_zh">开标</dm:out>最少人数：</label>
					<input type="text" id="" name="" value=""/>
				</li>
	    	</ul>
<fieldset class="operationDiv">
	<legend>招标方代表&nbsp;&nbsp;[<a href="#" onclick="openBidInfoList.addWorkgMember('00');">新增</a>]</legend>
		<table class="tableList" id="zbf">
			<thead>
				<tr>
					<th>招标方代表名称</th>
					<th>招标方代表帐号</th>
					<th>联系电话</th>
					<th>组员类型</th>
					<th>是否正选</th>
					<th>是否在现场</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${workgMemberBuyerList}" var="workgMemberAgent">
					<tr>
						<td><a onclick="openBidInfoList.viewWorkMember('${workgMemberAgent.objId}')" class="styleA">${workgMemberAgent.workgmName}</a></td>
						<td>${workgMemberAgent.workgmAccount}</td>
						<td>${workgMemberAgent.linkerPhone}</td>
						<td>
						<c:choose>
						<c:when test='${workgMemberAgent.workgmIsLeader=="01"}'>组长</c:when>
						<c:when test='${workgMemberAgent.workgmIsLeader=="01"&&workgMemberAgent.isAmount=="01"}'>副组长</c:when>
						<c:otherwise>组员</c:otherwise>
						</c:choose>
						</td>
						<td>
						<c:choose>
						<c:when test="${workgMemberAgent.isAmount=='00'}">正选</c:when>
						<c:otherwise>备选</c:otherwise>
						</c:choose></td>
						<td>
							<c:choose>
						<c:when test="${workgMemberAgent.signinStatus=='01'}">在现场</c:when>
						<c:otherwise>未在现场</c:otherwise>
						</c:choose>
						</td>
						<td class="functionBtnDiv" style="text-align:center">
						<a onclick="openBidInfoList.updateWorkMember('${workgMemberAgent.objId}','00','${workgMemberAgent.user.emp.objId}')" class="styleA"><span>修改</span></a>
						<a onclick="openBidInfoList.delWorkMember('${workgMemberAgent.objId}')" class="styleA"><span>删除</span></a>
						</td>
					</tr>
					<input type="hidden" name="agentEmpId" value="${workgMemberAgent.user.emp.objId}"/>
				</c:forEach>
			</tbody>
		</table>
</fieldset>
<fieldset class="operationDiv">
	<legend>招标代理方代表&nbsp;&nbsp;[<a href="#" onclick="openBidInfoList.addWorkgMember('02');">新增</a>]</legend>
		<table class="tableList" id="dlf">
			<thead>
				<tr>
					<th>招标代理方代表名称</th>
					<th>招标代理方代表帐号</th>
					<th>联系电话</th>
					<th>是否正选</th>
					<th>是否在现场</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${workgMemberAgentList}" var="workgMemberAgent">
					<tr>
						<td><a onclick="openBidInfoList.viewWorkMember('${workgMemberAgent.objId}')" class="styleA">${workgMemberAgent.workgmName}</a></td>
						<td>${workgMemberAgent.workgmAccount}</td>
						<td>${workgMemberAgent.linkerPhone}</td>
							<td>
						<c:choose>
						<c:when test="${workgMemberAgent.isAmount=='00'}">正选</c:when>
						<c:otherwise>备选</c:otherwise>
						</c:choose></td>
						<td>
							<c:choose>
						<c:when test="${workgMemberAgent.signinStatus=='01'}">在现场</c:when>
						<c:otherwise>未在现场</c:otherwise>
						</c:choose>
						</td>
						<td class="functionBtnDiv" style="text-align:center">
						<a onclick="openBidInfoList.updateWorkMember('${workgMemberAgent.objId}','02','${workgMemberAgent.user.emp.objId}')" class="styleA"><span>修改</span></a>
						<a onclick="openBidInfoList.delWorkMember('${workgMemberAgent.objId}')" class="styleA"><span>删除</span></a>
						</td>
					</tr>
					<input type="hidden" name="agentEmpId" value="${workgMemberAgent.user.emp.objId}"/>
				</c:forEach>
			</tbody>
		</table>
</fieldset>
<%-- 暂时隐藏 --%>
	<div class="conOperation" >
		<button id="saveBtn" type="button" tabindex="18"><span>完成组建开标小组</span></button>
	</div>
	</div>    
</div>
</div>