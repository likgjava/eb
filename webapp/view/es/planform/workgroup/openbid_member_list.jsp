<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
	<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/workgroup/openbid_member_list.js"></script>
	
<div class="partContainers">
  <h4><span></span></h4>
			<input type="hidden" value="$(projectId) %>" id="projectId"/>
			<c:forEach items="${workGroupList}" var="workGroup">
				 <fieldset class="operationDiv">
				    <legend><c:if test="${isDividePack==true}"><dm:out value="local__package" tenderType="${project.tenderType}">包组</dm:out>名称</c:if><c:if test="${isDividePack==false}">招标名称</c:if>：${workGroup.project.projName}&nbsp;&nbsp;
				    [<a href="#" onclick="openbid_member_list.add('${workGroup.objId}')">新增</a>]</legend>
				    <table class="tableList">
				    	<thead>
				    		<tr>
				    			<th>姓名</th>
				    			<th>用户</th>
				    			<th>类别</th>
				    			<th>操作</th>
				    		</tr>
				    	</thead>
				    	<tbody>
				    		<c:forEach items="${workGroup.memberSet}" var="member">
				    			<tr>
					    			<td>${member.workgmName }</td>
					    			<td><c:if test="${!empty member.user}">${member.user.usName}</c:if></td>
					    			<td>
					    			<c:choose>
					    				<c:when test="${member.workgmType=='01'}">招标中心</c:when>
					    				<c:when test="${member.workgmType=='02'}">监管</c:when>
					    				<c:otherwise>招标单位</c:otherwise>
					    			</c:choose>
					    			</td>
					    			<td><a href="#" onclick="openbid_member_list.del('${member.objId}')">删除</a></td>
				    			</tr>
				    		</c:forEach>
				    	</tbody>
				    </table>
				  </fieldset>
  			</c:forEach>
</div>
