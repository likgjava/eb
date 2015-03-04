<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/agentAssign.js"></script>
<div class="partContainers">
   	<div class="formLayout form2Pa">
 			<h5><span>指定经办人</span></h5>
     		<form id="agentAssignForm">
     		<input type="hidden" name="manager.objId" id="manager.objId" value="${project.manager.objId }"/>
     		<input type="hidden" name="objId" id="objId" value="${project.objId }"/>
		<ul>
			<li class="fullLine">
			   <label for="input02">指定经办人：</label>
			   <select name="linkGovMan" id="linkGovMan">
			   	<c:forEach items="${empList}" var="emp">
			   		<option value="${emp.objId }">${emp.name }</option>
			   	</c:forEach>
			        </select>
			      </li>
			      <li class="formTextarea">
			        <label for="input20">备注：</label>
			        	<textarea name="remark"></textarea>
			        <span class="eleNote"></span>
			      </li>
		</ul>
		 <div class="conOperation">
	    	<button type="button" id="sure"><span>确定</span></button>
	    	<button type="button" id="closeButton"><span>关闭</span></button>
		 </div>
		</form>
   	</div>
</div>