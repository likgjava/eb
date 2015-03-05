<%@ page pageEncoding="utf-8"%><%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/sysconfig/sysConfigItemValueForm.js"></script>
<div class="formZone">         
	<form id="sysConfigItemValueForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<div class="desc">系统配置值      <spring:message code="globe.input.required.prompt"/></div>
     	<div class="formFieldset">
	     	<div class="formRow">
        		<div class="formLabel"><spring:message code="sysConfigItemValueForm.createTime"/></div>
        		<div class="formField">
					<input type="text" name="createTime" id="createTime" class="required" 
									
						      />
				</div>
				<span class="eleNote"></span>	
    	    </div>
	     	<div class="formRow">
        		<div class="formLabel"><spring:message code="sysConfigItemValueForm.sysConfigItem"/></div>
        		<div class="formField">
					<input type="text" name="sysConfigItem" id="sysConfigItem" class="required" 
						      />
				</div>
				<span class="eleNote"></span>	
    	    </div>
	     	<div class="formRow">
        		<div class="formLabel"><spring:message code="sysConfigItemValueForm.value"/></div>
        		<div class="formField">
					<input type="text" name="value" id="value" class="required" 
						      />
				</div>
				<span class="eleNote"></span>	
    	    </div>
	     	<div class="formRow">
        		<div class="formLabel"><spring:message code="sysConfigItemValueForm.preValueName"/></div>
        		<div class="formField">
					<input type="text" name="preValueName" id="preValueName" class="required" 
						      />
				</div>
				<span class="eleNote"></span>	
    	    </div>
	     	<div class="formRow">
        		<div class="formLabel"><spring:message code="sysConfigItemValueForm.sysConfitItemPreValue"/></div>
        		<div class="formField">
					<input type="text" name="sysConfitItemPreValue" id="sysConfitItemPreValue" class="required" 
						      />
				</div>
				<span class="eleNote"></span>	
    	    </div>
	     	<div class="formRow">
        		<div class="formLabel"><spring:message code="sysConfigItemValueForm.notes"/></div>
        		<div class="formField">
					<input type="text" name="notes" id="notes" class="required" 
						      />
				</div>
				<span class="eleNote"></span>	
    	    </div>
		</div>
	    <div class="btnArea">
	        <button class="btn primary" id="sysConfigItemValueSave" type="button" tabindex="18"><span><span><spring:message code="globe.save"/></span></span></button>
	        <button class="btn primary" id="sysConfigItemValueReturn" type="button" tabindex="19"><span><span><spring:message code="globe.return"/></span></span></button>
	        <button class="btn" type="reset" id="sysConfigItemValueReset" tabindex="20" ><span><span><spring:message code="globe.reset"/></span></span></button>
	    </div>
	</form>
</div>