<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/auth/departmentDetail.js"></script>

  <div class="formLayout form2Pa detail">
  <form id="departmentDetailForm" method="post">
  		<input type="hidden" name="model" id="model" value="<c:out value="${param.model}"/>"/>
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<div class="treeEditNav">
			<ul id="createBtnsArea">
				<li id="departmentEdit" class="edit"><a href="#"><span><spring:message code="globe.modify"/></span></a></li>
				<li id="departmentDel" class="del"><a href="#"><span><spring:message code="globe.delete"/></span></a> </li>
			</ul>
		</div>
	    <h5><span><spring:message code="departmentForm.info"/></span></h5>
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
      
      
      
      
      







