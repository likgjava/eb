<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/oppugnrequisition/oppugnReplyForm.js"></script>
<div class="formLayout form2Pa">        
	<form:form id="oppugnReplyForm" method="post" modelAttribute="reply">
		<form:hidden path="objId"></form:hidden> 
		<form:hidden path="oppugnRequisition.objId"></form:hidden> 
		<form:hidden path="oppurReplyType"></form:hidden> 
		<input type="hidden" id="type" value="${param.type}"/>
		<input type="hidden" id="projectId" value="${projectId}">
		<table>
			<tr>
				<th><spring:message code="oppugnReplyForm.attachRelaId"/>：</th>
		        <td colspan="3">
		        	<div class="uploadFile" id="attachRelaId">${reply.attachRelaId}</div>	
		        </td>
			</tr>
		      <tr class="formTextarea">
		        <th><spring:message code="oppugnReplyForm.oppurReplyContent"/>：</th>
		        <td><form:textarea path="oppurReplyContent" cssClass="required"/>
							<span class="eleRequired">*</span></td>
		      </tr>
	     </table>
     		
		<div class="conOperation">
			<button id="oppugnReplySave" type="button" tabindex="18"><span><spring:message code="globe.save"/></span></button>
			<button id="oppugnReplyReturn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
		</div>
	</form:form>
</div>