<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/numberSet.js"></script>
<div class="partContainers">
<div class="formLayout form2Pa">
		<h5><span>设置招标编号</span></h5>
	   <form id="projectNumberForm">
		  <ul>
		      <li class="fullLine">
		        <label for="input01">招标编号：</label>
		        <input id="objId" name="objId" value="${param.projectId }" type="hidden"/>
		        <input id="projCode" name="projCode" value=""/><span class='eleRequired'>*</span>
		      </li>
		  </ul>
	     <div class="conOperation">
       		<button type="button" id="sure"><span>确定</span></button>
   		 </div>
	</form>
</div>
</div>