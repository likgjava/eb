<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/auth/roleAuthorizeResource.js"></script>
<div class="partContainers">
  <h4>授权</h4>
	  <div class="treeBtn">
      	<ul>
			<li class="edit"><button href="#" id="saveRoleRes"><span><spring:message code="globe.save"/></span></button></li>
			<li class="del"><button href="#"><span><spring:message code="globe.reset"/></span></button> </li>
			<li class="add"><button href="#" id="roleReturn"><span><spring:message code="globe.cancel"/></span></button> </li>
		</ul>
	  </div>
  	<div id="resourceTree"></div>
</div>


 
