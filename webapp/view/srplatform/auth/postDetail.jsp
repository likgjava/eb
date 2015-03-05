<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/auth/postDetail.js"></script>

  <div class="formLayout form2Pa detail">
  <form id="postDetailForm" method="post">
  		<input type="hidden" name="model" id="model" value="<c:out value="${param.model}"/>"/>
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
	    <div  class="treeEditNav">
			<ul id="createBtnsArea">
				<li id="postEdit" class="edit"><a href="#"><span><spring:message code="globe.modify"/></span></a></li>
				<li id="postDel" class="del"><a href="#"><span><spring:message code="globe.delete"/></span></a> </li>
			</ul>
		</div>
	    <h4><span><spring:message code="postForm.info"/></span></h4>
	    <ul>
	      <li>
	        <label for="input01"><spring:message code="postForm.name"/>:</label>
	       <span id="name"></span>
	      </li>
	      <li>
	        <label for="input02"><spring:message code="postForm.shortName"/>:</label>
	        <span id="shortName"></span>
	      </li>  
	   </ul>
    </form>
  </div>


