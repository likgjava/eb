<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<%@page import="java.util.*,com.gpcsoft.es.planform.project.domain.*,com.gpcsoft.es.planform.workgroup.domain.*" %>
	<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/workgroup/judge_member_list.js"></script>
	
<div class="partContainers">
  <h4><span></span></h4>
  <c:forEach items="${workGroupList}" var="workGroup">
		 <fieldset class="operationDiv">
		    <legend><c:if test="${isDividePack==true}"><dm:out value="local__package" tenderType="${project.tenderType}">包组</dm:out>名称</c:if><c:if test="${isDividePack==false}">招标名称</c:if>：${workGroup.project.projName}&nbsp;&nbsp;
		    [<a href="#" onclick="openbid_member_list.add('${workGroup.objId}','${workGroup.project.objId}')">新增</a>]</legend>
		    
		    <table class="tableList">
		    	<thead>
		    		<tr>
		    			<th>姓名</th>
		    			<th>成员类型</th>
		    			<th>是否为组长</th>
		    			<th>职称</th>
		    			<th>专业</th>
		    			<th>联系电话</th>
		    			<th>操作</th>
		    		</tr>
		    	</thead>
		    	<tbody>
		    		<c:forEach items="${workGroup.memberSet}" var="member">
		    			<tr>
			    			<td>${member.workgmName }</td>
			    			<td>${member.workgmTypeCN}</td>
			    			<td><c:if test="${member.workgmIsLeader==false}">否</c:if>
			    				<c:if test="${member.workgmIsLeader==true}">是</c:if>
			    			</td>
			    			<td>${member.workgmDuty }</td>
			    			<td>${member.workgmSpeciality }</td>
			    			<td>${member.linkerPhone }</td>
			    			<td><a href="#" onclick="openbid_member_list.del('${member.objId}')">删除</a></td>
		    			</tr>
		    		</c:forEach>
		    	</tbody>
		    </table>
		  </fieldset>
	
 </c:forEach>
  

</div>
