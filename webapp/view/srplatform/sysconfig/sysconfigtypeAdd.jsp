<%@ page pageEncoding="utf-8"%><%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/sysconfig/sysconfigtypeAdd.js"></script>
<div class="formLayout">
<form id="sysConfigTypeForm" method="post">
	<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
	<input type="hidden" name="parent.objId" id="parent.objId" type="hidden" value="<c:out value='${parentObjId }'/>"/>
	<h5><span>新增系统配置类型信息</span></h5>
    	<ul><li>
       		<label><spring:message code="sysConfigTypeForm.name"/>:</label>
       		<input type="text" name="name" id="name" class="required" style="width: 200px"/>
       		<span class="eleRequired">*</span>
       </li>
       <li>
       		<label><spring:message code="sysConfigTypeForm.code"/>:</label>
       		<input type="text" name="code" id="code" class="required" style="width: 200px" />
       		<span class="eleRequired">*</span>
       </li>
       <li>
       		<label><spring:message code="sysConfigTypeForm.typeKind"/>:</label>
       		<input type="text" name="typeKind" id="typeKind" class="required" style="width: 200px" />
       		<span class="eleRequired">*</span>
       </li>
       <li>
       		<label><spring:message code="sysConfigTypeForm.notes"/>:</label>
       		<input type="text" name="notes" id="notes" class="required" style="width: 200px" />
       		<span class="eleRequired">*</span>
       </li>
	</ul>
	<div class="conOperation">
		<button type="button" id="sysConfigTypeSave"><span><spring:message code="globe.save"/></span></button>
		<button type="button" id="sysConfigTypeReturn"><span><spring:message code="globe.return"/></span></button>
   </div>
</form>
</div>
