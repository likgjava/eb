<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<%@page import="java.util.*,com.gpcsoft.es.planform.project.domain.*,com.gpcsoft.es.planform.workgroup.domain.*" %>
	<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/workgroup/evalbid_member_list.js"></script>
	
<div class="partContainers">
  <h4><span></span></h4>
  <c:forEach items="${project.subProject}" var="sub">
 
				 <fieldset class="operationDiv">
				    <legend><c:if test="${project.isDividePack==true}"><dm:out value="local__package" tenderType="${project.tenderType}">包组</dm:out>名称</c:if><c:if test="${project.isDividePack==false}">招标名称</c:if>：${sub.projName}&nbsp;&nbsp;
				    [<a href="#" onclick="evalbid_member_list.add('${sub.workGroup.objId}','${sub.parentId}')">新增</a>]</legend>
				    <table class="tableList">
				    	<thead>
				    		<tr>
				    			<th>姓名</th>
				    			<th>用户</th>
				    			<th>专业</th>
				    			<th>操作</th>
				    		</tr>
				    	</thead>
				    	<tbody>
				    		<c:forEach items="${sub.workGroup.memberSet}" var="member">
				    			<tr>
					    			<td>${member.workgmName }</td>
					    			<td><c:if test="${!empty member.user}">${member.user.usName}</c:if></td>
					    			<td>${member.workmSpeciality}</td>
					    			<td><a href="#" onclick="evalbid_member_list.del('${member.objId}')">删除</a></td>
				    			</tr>
				    		</c:forEach>
				    	</tbody>
				    </table>
				  </fieldset>

 </c:forEach>
  <input type="hidden" id="formTab" value="${fromTab}"/>

</div>
