<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!--这里引入CSS、JS文件-->
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/auth/SysInfoConfigForm.js"></script>

<form method="post" name="SysInfoConfigForm" id="SysInfoConfigForm"  action="SysInfoConfigController.do?method=save">
	<div class="partContainers">
		<div class="formLayout form2Pa">  
			<h5><span>公司信息</span></h5>
			<ul>
				<li><label><spring:message code="SysInfoConfigForm.compname"/>：</label>
            		<input type="text" name="compname" class="required">
					<span class="eleRequired">*</span>
	            </li>
	            <li><label><spring:message code="SysInfoConfigForm.ecompname"/>：</label>
            		<input type="text" name="ecompname" class="required">
					<span class="eleRequired">*</span>
	            </li>
	            <li><label><spring:message code="SysInfoConfigForm.sitename"/>：</label>
					<input type="text" name="sitename"  class="required">
					<span class="eleRequired">*</span>
				</li>
	            <li><label><spring:message code="SysInfoConfigForm.email"/>：</label>
					<input type="text" name="email"  class="required email">
					<span class="eleRequired">*</span>
				</li>
	            <li><label><spring:message code="SysInfoConfigForm.telephone"/>：</label>
					<input type="text" name="telephone"  class="required telephone">
					<span class="eleRequired">*</span>
			   </li>
	           <li><label><spring:message code="SysInfoConfigForm.address"/>：</label>
					<input type="text" name="address"  class="required">
					<span class="eleRequired">*</span>
			   </li>
			</ul>
		</div>
		<div class="formLayout form2Pa">  
			<h5><span>项目信息</span></h5>
			<ul>
				<li><label><spring:message code="SysInfoConfigForm.projectname"/>：</label>
					<input type="text" name="projectname" class="required">
					<span class="eleRequired">*</span>
				</li>
				<li><label><spring:message code="SysInfoConfigForm.eprojectname"/>：</label>
					<input type="text" name="eprojectname" class="required">
					<span class="eleRequired">*</span>
				</li>
	            <li><label><spring:message code="SysInfoConfigForm.version"/>：</label>
					<input type="text" name="version" class="required">
					<span class="eleRequired">*</span>
				</li>
			</ul>
		</div>
		<div class="formLayout form2Pa">  
			<h5><span>系统参数</span></h5>
			<ul>
				<li><label><spring:message code="SysInfoConfigForm.welcome"/>：</label>
					<input type="text" name="welcome" class="required">
					<span class="eleRequired">*</span>
			  	</li>
			  	<li><label><spring:message code="SysInfoConfigForm.maxLogNumber"/>：</label>
					<input type="text" name="maxLogNumber" class="required number">
					<span class="eleRequired">*</span>
			  	</li>
			  	<li><label><spring:message code="SysInfoConfigForm.resetTime"/>（秒）：</label>
					<input type="text" name="resetTime" class="required number">
					<span class="eleRequired">*</span>
			  	</li>
			  	<li><label><spring:message code="SysInfoConfigForm.concurrentUserNum"/>：</label>
					<input type="text" name="concurrentUserNum" class="required number">
					<span class="eleRequired">*</span>
			  	</li>
			  	<li><label><spring:message code="SysInfoConfigForm.defaultPassword"/>：</label>
					<input type="text" name="defaultPassword" class="required">
					<span class="eleRequired">*</span>
			  	</li>
			  	<li><label><spring:message code="SysInfoConfigForm.useProjectPlan"/>：</label>
					<input type="radio" value="1" name="useProjectPlan">是
					<input type="radio" value="0" name="useProjectPlan">否
					<span class="eleRequired">*</span>
			  	</li>
			</ul>
		</div>
		<div class="conOperation">
			<button  type="button"  id="submit"><span><spring:message code="globe.save"/></span></button>
		</div>
	</div>
</form>