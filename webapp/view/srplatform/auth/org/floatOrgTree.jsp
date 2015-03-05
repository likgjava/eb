<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/auth/org/floatOrgTree.js"></script>
<input type="hidden" name="hiddenProperty" id="hiddenProperty" value="<c:out value="${param.hiddenProperty}"/>"/>
<input type="hidden" name="showProperty" id="showProperty" value="<c:out value="${param.showProperty}"/>"/>
<input type="hidden" name="roleOrgId" id="roleOrgId" value="<c:out value="${param.roleOrgId}"/>"/>
<input type="hidden" name="type" id="type" value="<c:out value="${param.type}"/>"/>
<div class="treeOutside">
  <div class="treeBtn">
  	<button id="_clear">清空</button>
  </div>
  <div id="floatOrgTree" class="treeContentDiv"></div>
  <div class="treeResize" ></div>
</div>

