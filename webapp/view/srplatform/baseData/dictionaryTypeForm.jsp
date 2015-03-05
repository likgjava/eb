<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/baseData/dictionaryTypeForm.js"></script>
<div class="formLayout">        
	<form id="dictionaryTypeForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<h4><span>数据字典关联表      </span></h4>
     	<ul >
	     	<li >
        		<label><spring:message code="dictionaryTypeForm.groupName"/>:</label>
					<input type="text" name="groupName" id="groupName" class="required" 
						      />
				<span class="eleRequired">*</span>
    	    </li>
		</ul>
	   <div class="conOperation">
	        <button class="btn primary" id="dictionaryTypeSave" type="button" tabindex="18"><span><spring:message code="globe.save"/></span></button>
	        <button class="btn primary" id="dictionaryTypeReturn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
	        <button class="btn" type="reset" id="dictionaryTypeReset" tabindex="20" ><span><spring:message code="globe.reset"/></span></button>
	    </div>
	</form>
</div>