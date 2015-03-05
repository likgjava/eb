<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/auth/org/change_parent_org.js"></script>
<input type="hidden" name="oldPid" id="oldPid" value="<c:out value="${param.oldPid}"/>"/>
<input type="hidden" name="orgId" id="orgId" value="<c:out value="${param.orgId}"/>"/>
<input type="hidden" name="roleOrgId" id="roleOrgId" value="<c:out value="${param.roleOrgId}"/>"/>
<input type="hidden" name="type" id="type" value="<c:out value="${param.type}"/>"/>
<div class="treeOutside">
  <div class="treeBtn">
  	<button id="_clear">清空</button>
  </div>
  <div id="floatOrgTree" class="treeContentDiv"></div>
  <div class="treeResize" ></div>
</div>

