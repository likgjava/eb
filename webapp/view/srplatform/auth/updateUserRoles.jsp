<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/auth/updateUserRoles.js"></script>

<!--页面按钮开始-->
<div id="pageMenu" class="pageMenu ui-state-default">
    <a href="javascript:void(0);" id="refresh" class="addicon refresh">刷新</a>
    <a href="javascript:void(0);" id="print" class="addicon print">打印</a>
    <a href="javascript:void(0);" id="help" class="addicon help">帮助</a>
</div>
<!--页面按钮结束-->
	<div class="pageTree">
    	<div id="treeLeft" class="xTree"></div>  
    </div>
    <div id="UserRoles_from" class="pageContent treeRight">
    	<div class="form-container" id="userInfo">
   	 	<form method="post" name="UserRolesForm" id="UserForm"  action="UserController.do?method=save" onsubmit="return validateUserForm(this);">
	    	<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
				<fieldset>
  				<legend>用户信息</legend>
			  		<div><label><spring:message code="UserForm.usName"/>：:</label>
			           	 <div id="usName"></div>
			         </div>
			        <div><label><spring:message code="UserForm.usrPeriodOfValidity"/>：:</label>
			          	 <div id="usrPeriodOfValidity"></div>
			        </div>
			        <div><label><spring:message code="UserForm.org"/>：:</label>
			          	 <div id="org.name"></div>
					</div>
					<div><label><spring:message code="UserForm.usrCnName"/>：:</label>
			          	 <div id="usrCnName"></div>
					</div>
					<div><label><spring:message code="UserForm.usrEmail"/>：:</label>
			          	 <div id="usrEmail"></div>
					</div>
					<div><label><spring:message code="UserForm.usrMobile"/>：:</label>
			          	 <div id="usrMobile"></div>
					</div>
					<div><label><spring:message code="UserForm.usrTelOffice"/>：:</label>
			          	 <div id="usrTelOffice"></div>
					</div>
					<div><label><spring:message code="UserForm.usrTelHome" />：:</label>
			          	 <div id="usrTelHome"></div>
					</div>
			    </fieldset>			

	    	<div class="buttonClass">
	         <button  type="button"  id="submit"><span><spring:message code="globe.save"/></span></button>
  			 <button  type="button"  id="return"><span><spring:message code="globe.return"/></span></button>
	    	</div>  
    	</form>

        </div>
    </div> 