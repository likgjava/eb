<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/auth/departmentForm.js"></script>

  <div class="formLayout ">
  <form id="departmentForm" method="post">
  		<input type="hidden" name="model" id="model" value="<c:out value="${param.model}"/>"/>
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<input type="hidden" name="parent.objId" id="parent.objId" value="<c:out value="${param.parentId}"/>"/>
    <h4><span>部门信息 </span></h4>
    <ul>
      <li>
        <label for="input01"><spring:message code="departmentForm.name"/>:</label>
        <input type="text" name="name" id="name" class="required" 
									class="required"/>
		<span class="eleRequired">*</span> 
      </li>
      <li>
        <label for="input02"><spring:message code="departmentForm.shortName"/>:</label>
       <input type="text" name="shortName" id="shortName" class="required" 
									class="required"/>
		<span class="eleRequired">*</span> 
      </li>
      <li class="eleDisable">
	        <label>上级机构简称:</label>
	        <input type="hidden" name="parent.objId" id="parent.objId" value="<c:out value="${param.parentId}"/>"/>
	        <input type="text" name="parent.shortName" id="parent.shortName"  value="<c:out value="${param.parentShortName}"/>"/>
	     </li>
      <li>
        <label for="input03">分管领导:</label>
        <input type="hidden" name="leader" id="leader" class="" />
		<input type="text" name="leaderCnName" class="sysicon siSearch" 
							 id="leaderCnName" value="" size="20" readonly="readyonly">
        <span class="eleRight"></span>
      </li>
      <li>
        <label for="input04">负责人:</label>
        <input type="hidden" name="supervisor" id="supervisor" class="" />
        <input type="text" name="supervisorCnName" class="sysicon siSearch" 
							 id="supervisorCnName" value="" size="20" readonly="readyonly">
      </li>
       <li>
	        <label>联系人:</label>
	        <input type="text" name="contact" id="contact" class=""/>
	     </li>
	      <li>
	        <label>联系电话:</label>
		     <input type="text" name="tel" id="tel" class=" cnPhone"/>
	     </li>
   </ul>
    <div class="conOperation">
      	  <button  id="departmentSave" type="button" ><span><spring:message code="globe.save"/></span></button>
	      <button  id="departmentReturn" type="button" ><span><spring:message code="globe.return"/></span></button>
    </div>
    </form>
  </div>
  <div class="formTips">
  	<ul>
  		<li><spring:message code="globe.input.required.prompt"/></li>
  	</ul>
  </div>
      
      
      
      
      










<!--<div class="formZone twoCol">         
	<form id="departmentForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<input type="hidden" name="parent.objId" id="parent.objId" value="<c:out value="${param.parentId}"/>"/>
		<div class="desc">部门信息      <spring:message code="globe.input.required.prompt"/></div>
     	<div class="formFieldset">
	     	<div class="formRow">
        		<div class="formLabel"><spring:message code="departmentForm.name"/><em>*</em></div>
        		<div class="formField">
					<input type="text" name="name" id="name" class="required" 
									class="required"
						      />
				</div>
				<div class="formTips"></div><p class="error"></p>	
    	    </div>
	     	<div class="formRow">
        		<div class="formLabel"><spring:message code="departmentForm.shortName"/><em>*</em></div>
        		<div class="formField">
					<input type="text" name="shortName" id="shortName" class="required" 
									class="required"
						      />
				</div>
				<div class="formTips"></div><p class="error"></p>	
    	    </div>
    	  </div>
    	  <div class="formFieldset">
	     	<div class="formRow">
        		<div class="formLabel">分管领导<em></em></div>
        		<div class="formField">
					<input type="hidden" name="leader" id="leader" class="" />
					<input type="text" name="leaderCnName"
									class="sysicon siSearch" 
							 id="leaderCnName" value="" size="20" readonly="readyonly">
				</div>
				<div class="formTips"></div><p class="error"></p>	
    	    </div>
	     	<div class="formRow">
        		<div class="formLabel">负责人<em></em></div>
        		<div class="formField">
					<input type="hidden" name="supervisor" id="supervisor" class="" />
					<input type="text" name="supervisorCnName"
									class="sysicon siSearch" 
							 id="supervisorCnName" value="" size="20" readonly="readyonly">
				</div>
				<div class="formTips"></div><p class="error"></p>	
    	    </div>
		</div>
	    <div class="conOperation">
	        <button  id="departmentSave" type="button" tabindex="19"><span><spring:message code="globe.save"/></span></button>
	        <button  id="departmentReturn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
	    </div>
	</form>
</div>-->