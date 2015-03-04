<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/workgroup/float/expert_info.js"></script>
  <div class="formLayout ">
  <form id="expertForm" method="post">
  	<input type="hidden" name="workGroup.objId"  value="<c:out value="${param.workGroupId}"/>"/>
  	<input type="hidden" name="projectId"  value="<c:out value="${param.projectId}"/>"/>
    <h4><span>评委信息</span></h4>
    <ul>
   	<li>
        <label>姓名:</label>
        <input type="text" name="workgmName" id="workgmName" class="required"/>
        <span class="eleRequired">*</span>
     </li>
      <li>
        <label>职称:</label>
        <input type="text" name="workgmDuty" id="workgmDuty" class="required"/>
        <span class="eleRequired">*</span>
      </li>
      <li>
        <label>专业:</label>
        <input type="text" name="workgmSpeciality" id="workgmSpeciality" class="required"/>
        <span class="eleRequired">*</span>
      </li>
       <li>
        <label>用户名:</label>
        <input type="text" name="usName" id="usName" class="required"/>
        <span class="eleRequired">*</span>
       </li>
       <li>
        <label>密码:</label>
        <input type="password" name="password" id="password" class="required "/>
        <span class="eleRequired">*</span>
       </li>
       <li>
        <label>再次输入密码:</label>
        <input type="password" name="password2" id="password2" class="required "/>
        <span class="eleRequired">*</span>
       </li>
   </ul>
    <div class="conOperation">
      	<button  id="saveExpert" type="button" ><span><spring:message code="globe.save"/></span></button>
    </div>
    </form>
  </div>
  

      
      
      
      
      
      
      
      
      
	