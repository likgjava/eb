<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/auth/org/floatEmployeeTree.js"></script>
<input type="hidden" name="hiddenProperty" id="hiddenProperty" value="<c:out value="${param.hiddenProperty}"/>"/>
<input type="hidden" name="showProperty" id="showProperty" value="<c:out value="${param.showProperty}"/>"/>
<input type="hidden" name="orgId" id="orgId" value="<c:out value="${param.orgId}"/>"/>
<div class="treeOutside">
  <div class="treeBtn">
  	<button type="button" id="clearEmp">清空</button>
  </div>
  <div id="floatEmployeeTree" class="treeContentDiv"></div>
  <div class="treeResize" ></div>
</div>

