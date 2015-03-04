<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/oppugnrequisition/addOppugnRequisitionForm.js"></script>
<div class="formLayout form2Pa"> 
	<form:form id="oppugnRequisitionForm" method="post" modelAttribute="oppugn">
		<form:hidden path="objId"></form:hidden> 
		<input type="hidden" name="project.objId" value="${param.projectId}"/>
		<input type="hidden" name="useStatus" id="useStatus" value=""/>

		<h5><span>质疑信息</span></h5>
     		<table>
					<tr>
						<th><spring:message code="oppugnRequisitionForm.oppuType"/>：</th>
						<td><select name="oppuType" id="oppuType" class="required">
							<option value="00"><dm:out value="local__PURDOC" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">招标文件</dm:out>质疑</option>
							<option value="01">采购过程质疑</option>
							<option value="02">初定采购结果质疑</option>
							<option value="03">其它</option>
						</select>
						<span class="eleRequired">*</span></td>
						<th><spring:message code="oppugnRequisitionForm.consBuyerName"/>：</th>
						<td><select name="consBuyerName" id="consBuyerName" class="required">
							<option value="00">招标单位</option>
							<option value="01">招标中心</option>
							<option value="02">两者</option>
						</select>
						<span class="eleRequired">*</span></td>
					</tr>

					<tr>
						<th><spring:message code="oppugnRequisitionForm.oppuLinker"/>：</th>
						<td><form:input path="oppuLinker" cssClass="required"/>
						<span class="eleRequired">*</span></td>
					
						<th><spring:message code="oppugnRequisitionForm.oppuLinkerTel"/>：</th>
						<td><form:input path="oppuLinkerTel" cssClass="required"/>
						<span class="eleRequired">*</span></td>
					</tr>

					<tr>
						<th><spring:message code="oppugnRequisitionForm.oppuLinkerFax"/>：</th>
						<td><form:input path="oppuLinkerFax" cssClass="required"/>
						<span class="eleRequired">*</span></td>
					
						<th><spring:message code="oppugnRequisitionForm.oppuLinkerEmail"/>：</th>
						<td><form:input path="oppuLinkerEmail" cssClass="required"/>
						<span class="eleRequired">*</span></td>
					</tr>

					<tr >
						<th><spring:message code="oppugnRequisitionForm.oppuLinkerAddress"/>：</th>
						<td colspan="3"><form:input path="oppuLinkerAddress" cssClass="required long"/>
						<span class="eleRequired">*</span></td>
					</tr>
					
					<tr >
						<th><spring:message code="oppugnRequisitionForm.attachRelaId"/>：</th>
						<td colspan="3"><div class="uploadFile" id="attachRelaId">${oppugn.attachRelaId}</div></td>
					</tr>
					
					<tr class="formTextarea" >
						<th><spring:message code="oppugnRequisitionForm.oppuContent"/>：</th>
						<td colspan="3"><form:textarea path="oppuContent" cssClass="required"  onkeyup="oppugnRequisitionForm.checkOppuContent(this);"/>
						<span class="eleRequired">*</span></td>
					</tr>
					
		</table>
		<div class="conOperation">
			<button id="oppugnRequisitionSave" type="button" tabindex="18"><span><spring:message code="globe.save"/></span></button>
			<button id="oppugnRequisitionSubmit" type="button" tabindex="18"><span>提交</span></button>
			<button id="oppugnRequisitionReturn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
		</div>
	</form:form>
</div>
