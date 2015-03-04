<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/projrule/projectPackageForm.js"></script>
<div class="formZone">         
	<form id="projectPackageForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<div class="desc">项目拆分      <spring:message code="globe.input.required.prompt"/></div>
     	<div class="formFieldset">
	     	<div class="formRow">
        		<div class="formLabel"><spring:message code="projectPackageForm.project"/></div>
        		<div class="formField">
					
				</div>
				<span class="eleNote"></span>	
    	    </div>
	     	<div class="formRow">
        		<div class="formLabel"><spring:message code="projectPackageForm.packCode"/></div>
        		<div class="formField">
					<input type="text" name="packCode" id="packCode" class="required" 
						      />
				</div>
				<span class="eleNote"></span>	
    	    </div>
	     	<div class="formRow">
        		<div class="formLabel"><spring:message code="projectPackageForm.packName"/></div>
        		<div class="formField">
					<input type="text" name="packName" id="packName" class="required" 
						      />
				</div>
				<span class="eleNote"></span>	
    	    </div>
	     	<div class="formRow">
        		<div class="formLabel"><spring:message code="projectPackageForm.packContent"/></div>
        		<div class="formField">
					<input type="text" name="packContent" id="packContent" class="required" 
						      />
				</div>
				<span class="eleNote"></span>	
    	    </div>
	     	<div class="formRow">
        		<div class="formLabel"><spring:message code="projectPackageForm.remark"/></div>
        		<div class="formField">
					<input type="text" name="remark" id="remark" class="required" 
						      />
				</div>
				<span class="eleNote"></span>	
    	    </div>
	     	<div class="formRow">
        		<div class="formLabel"><spring:message code="projectPackageForm.creator"/></div>
        		<div class="formField">
					<input type="text" name="creator" id="creator" class="required" 
						      />
				</div>
				<span class="eleNote"></span>	
    	    </div>
	     	<div class="formRow">
        		<div class="formLabel"><spring:message code="projectPackageForm.createTime"/></div>
        		<div class="formField">
					<input type="text" name="createTime" id="createTime" class="required" 
									readonly="readyonly"
						      />
				</div>
				<span class="eleNote"></span>	
    	    </div>
		</div>
	    <div class="btnArea">
	        <button class="btn primary" id="projectPackageSave" type="button" tabindex="18"><span><span><spring:message code="globe.save"/></span></span></button>
	        <button class="btn primary" id="projectPackageReturn" type="button" tabindex="19"><span><span><spring:message code="globe.return"/></span></span></button>
	    </div>
	</form>
</div>