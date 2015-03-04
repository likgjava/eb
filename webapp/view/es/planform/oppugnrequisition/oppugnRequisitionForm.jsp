<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/oppugnrequisition/oppugnRequisitionForm.js"></script>
<div class="partContainers">      
	<form:form id="oppugnRequisitionForm" method="post" modelAttribute="oppugn">
		<form:hidden path="objId"></form:hidden> 
		<input type="hidden" name="project.objId" value="${param.projectId}"/>
		<input type="hidden" name="useStatus" id="useStatus" value=""/>

		<div class="formLayout form2Pa">  
		<h5><span>质疑信息</span></h5>
     		<ul>
					<li>
						<label for="oppuType"><spring:message code="oppugnRequisitionForm.oppuType"/>：</label>
						<select name="oppuType" id="oppuType" class="required">
							<option value="00"><dm:out value="local__PURDOC" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}"  >招标文件</dm:out>质疑</option>
							<option value="01">采购过程质疑</option>
							<option value="02">初定采购结果质疑</option>
							<option value="03">其它</option>
						</select>
						<span class="eleRequired">*</span>
					</li>

					<li>
						<label for="consBuyerName"><spring:message code="oppugnRequisitionForm.consBuyerName"/>：</label>
						<select name="consBuyerName" id="consBuyerName" class="required">
							<option value="00">招标单位</option>
							<option value="01">招标中心</option>
							<option value="02">两者</option>
						</select>
						<span class="eleRequired">*</span>
					</li>

					<li>
						<label for="oppuLinker"><spring:message code="oppugnRequisitionForm.oppuLinker"/>：</label>
						<form:input path="oppuLinker" cssClass="required"/>
						<span class="eleRequired">*</span>
					</li>

					<li>
						<label for="oppuLinkerTel"><spring:message code="oppugnRequisitionForm.oppuLinkerTel"/>：</label>
						<form:input path="oppuLinkerTel" cssClass="required"/>
						<span class="eleRequired">*</span>
					</li>

					<li>
						<label for="oppuLinkerFax"><spring:message code="oppugnRequisitionForm.oppuLinkerFax"/>：</label>
						<form:input path="oppuLinkerFax" cssClass="required"/>
						<span class="eleRequired">*</span>
					</li>

					<li>
						<label for="oppuLinkerEmail"><spring:message code="oppugnRequisitionForm.oppuLinkerEmail"/>：</label>
						<form:input path="oppuLinkerEmail" cssClass="required"/>
						<span class="eleRequired">*</span>
					</li>

					<li class="fullLine" >
						<label for="oppuLinkerAddress"><spring:message code="oppugnRequisitionForm.oppuLinkerAddress"/>：</label>
						<form:input path="oppuLinkerAddress" cssClass="required"/>
						<span class="eleRequired">*</span>
					</li>
					
					<li class="fullLine" >
						<label for="attachRelaId"><spring:message code="oppugnRequisitionForm.attachRelaId"/>：</label>
						<span id="attachRelaId">${oppugn.attachRelaId}</span>
					</li>
					
					<li class="formTextarea" >
						<label for="oppuContent"><spring:message code="oppugnRequisitionForm.oppuContent"/>：</label>
						<form:textarea path="oppuContent" cssClass="required"/>
						<span class="eleRequired">*</span>
					</li>
					
		</ul>
		<div class="conOperation">
			<button id="oppugnRequisitionSave" type="button" tabindex="18"><span><spring:message code="globe.save"/></span></button>
			<button id="oppugnRequisitionSubmit" type="button" tabindex="18"><span>提交</span></button>
			<button id="oppugnRequisitionReturn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
		</div>
		</div>
	</form:form>
</div>