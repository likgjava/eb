<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/auth/organizationGrant.js"></script>

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
    <div id="OrganizationGrant_form" class="pageContent treeRight">
        <div class="form-container" id="OrganizationInfo">
        <form method="post" name="OrganizationGrantForm" id="OrganizationGrantForm"  action="OrganizationController.do?method=save" onsubmit="return validateOrganizationGrantForm(this);">
            <input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
            <fieldset>
  				<legend>机构信息</legend>
			  		<div><label><spring:message code="OrganizationForm.name" />：:</label>
                         <div id="name"></div>
                    </div>
                    <div><label><spring:message code="OrganizationForm.code" />：:</label>
                         <div id="code"></div>
                    </div>
                    <div><label><spring:message code="OrganizationForm.shortName" />：:</label>
                         <div id="shortName"></div>
                    </div>
                    <div><label><spring:message code="OrganizationForm.croporate" />：:</label>
                         <div id="croporate"></div>
                    </div>
                    <div><label><spring:message code="OrganizationForm.email" />：:</label>
                         <div id="email"></div>
                    </div>
                    <div><label><spring:message code="OrganizationForm.contact" />：:</label>
                         <div id="contact"></div>
                    </div>
                    <div><label><spring:message code="OrganizationForm.tel" />：:</label>
                         <div id="tel"></div>
                    </div>
			</fieldset>

            <div class="buttonClass">
             <button  type="button"  id="submit"><span><spring:message code="globe.save"/></span></button>
          	 <button  type="button" id="return"><span><spring:message code="globe.return"/></span></button> 
            </div>  
        </form>
        </div> 
    </div> 
    


