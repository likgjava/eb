<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/auth/postForm.js"></script>

  <div class="formLayout ">
  <form id="postForm" method="post">
  		<input type="hidden" name="model" id="model" value="<c:out value="${param.model}"/>"/>
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<input type="hidden" name="parent.objId" id="parent.objId" value="<c:out value="${param.parentId}"/>"/>
    	<h5><span>岗位信息</span></h5>
    	<ul>
		      <li>
		        <label><spring:message code="postForm.name"/>:</label>
		        <input type="text" name="name" id="name" class="required" class="required" />
				 <span class="eleRequired">*</span>
		      </li>
		      <li>
		        <label for="input02"><spring:message code="postForm.shortName"/>:</label>
		        <input type="text" name="shortName" id="shortName" class="required" class="required"/>
				<span class="eleRequired">*</span>
		      </li>
		      <li class="eleDisable">
		        <label>上级机构简称:</label>
		        <input type="hidden" name="parent.objId" id="parent.objId" value="<c:out value="${param.parentId}"/>"/>
		        <input type="text" name="parent.shortName" id="parent.shortName"  value="<c:out value="${param.parentShortName}"/>"/>
		     </li>
	    </ul>
		   <div class="conOperation">
		       <button  id="postSave" type="button" tabindex="19"><span><spring:message code="globe.save"/></span></button>
			   <button  id="postReturn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
		   </div>
    </form>
  </div>
  <div class="formTips">
  	<ul>
  		<li><spring:message code="globe.input.required.prompt"/></li>
  	</ul>
  </div>
 