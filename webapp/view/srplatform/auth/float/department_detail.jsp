<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/auth/float/department_detail.js"></script>

  <div class="formLayout  detail">
  <form id="departmentDetailForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
	    <ul>
		      <li>
		        <label><spring:message code="departmentForm.name"/>:</label>
		        <span id="name"></span>
		      </li>
		      <li>
		        <label><spring:message code="departmentForm.shortName"/>:</label>
		        <span id="shortName"></span>
		       </li>
		      <li>
		        <label>上级机构:</label>
		        <span id="parent.shortName"></span>
		        </li>
		      <li>
		        <label>分管领导:</label>
		        <span id="leaderCnNamee"></span>
		        </li>
		      <li>
		        <label>负责人:</label>
		        <span id="supervisorCnName"></span>
		     </li>
		      <li>
		        <label>联系人:</label>
		        <span id="contact"></span>
		        </li>
		      <li>
		        <label>联系电话:</label>
		        <span id="tel"></span>
		     </li>
	   </ul>
    </form>
  </div>