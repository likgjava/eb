<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/ProjectPackage.js"></script>
<div class="formZone">         
	<form id="projectForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<input type="hidden" name="parentID" id="parentID" value="${param.parentId}"/>
		<div class="desc">项目      <spring:message code="globe.input.required.prompt"/></div>
     	<div class="formFieldset">
	     	<div class="formRow">
        		<div class="formLabel"><spring:message code="projectForm.projCode"/></div>
        		<div class="formField">
					<input type="text" name="projCode" id="projCode" class="required" 
						      />
				</div>
				<span class="eleNote"></span>	
    	    </div>
	     	<div class="formRow">
        		<div class="formLabel"><spring:message code="projectForm.projName"/></div>
        		<div class="formField">
					<input type="text" name="projName" id="projName" class="required" 
						      />
				</div>
				<span class="eleNote"></span>	
    	    </div>
	     	<div class="formRow">
        		<div class="formLabel"><spring:message code="projectForm.createOperator"/></div>
        		<div class="formField">
					<input type="text" name="createOperator" id="createOperator" class="required" 
						      />
				</div>
				<span class="eleNote"></span>	
    	    </div>
	     	<div class="formRow">
        		<div class="formLabel"><spring:message code="projectForm.createTime"/></div>
        		<div class="formField">
					<input type="text" name="createTime" id="createTime" class="required" 
									readonly="readyonly"
						      />
				</div>
				<span class="eleNote"></span>	
    	    </div>
	     	<div class="formRow">
        		<div class="formLabel"><spring:message code="projectForm.remark"/></div>
        		<div class="formField">
					<input type="text" name="remark" id="remark" class="required" 
						      />
				</div>
				<span class="eleNote"></span>	
    	    </div>
	     	<div class="formRow">
        		<div class="formLabel"><spring:message code="projectForm.projDepartMent"/></div>
        		<div class="formField">
					
					<select name="projDepartMent" id="projDepartMent" class="required" style="width: 248px;height: 23px">
						<option value="采购一部">采购一部</option>
						<option value="采购二部">采购二部</option>
						<option value="采购三部">采购三部</option>
					</select>
				</div>
				<span class="eleNote"></span>	
    	    </div>
	     	<div class="formRow">
        		<div class="formLabel"><spring:message code="projectForm.projManager"/></div>
        		<div class="formField">
					<select name="projManager" id="projManager" class="required" style="width: 248px;height: 23px">
						<option value="张一">张一</option>
						<option value="张二">张二</option>
						<option value="张三">张三</option>
					</select>
				</div>
				<span class="eleNote"></span>	
    	    </div>
	     	
	     	<div class="formRow">
        		<div class="formLabel"><spring:message code="projectForm.projSummary"/></div>
        		<div class="formField">
					<input type="text" name="projSummary" id="projSummary" class="required" 
						      />
				</div>
				<span class="eleNote"></span>	
    	    </div>
	     	<div class="formRow">
        		<div class="formLabel"><spring:message code="projectForm.ebuyMethod"/></div>
        		<div class="formField">
				 	<select name="ebuyMethod" id="ebuyMethod" class="required" style="width: 248px;height: 23px">
						<option value="0">公开招标</option>
						<option value="1">邀请招标</option>
						<option value="2">竞争性谈判</option>
						<option value="3">单一来源</option>
						<option value="4">询价</option>
					</select>
				    <input type="hidden" name="useStatus" id="useStatus" class="required" 
						      value="01"/>
					<input type="hidden" name="projProcessStatus" id="projProcessStatus" class="required" 
						      value="00"/>
				</div>
				<span class="eleNote"></span>	
    	    </div>
		</div>
	    <div class="btnArea">
	        <button class="btn primary" id="projectSave" type="button" tabindex="18"><span><span><spring:message code="globe.save"/></span></span></button>
	        <button class="btn primary" id="projectReturn" type="button" tabindex="19"><span><span><spring:message code="globe.return"/></span></span></button>
	    </div>
	</form>
</div>