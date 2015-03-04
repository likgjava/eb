<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/projreview/bidEvaluationReportForm.js"></script>       
<div class="partContainers" id="bulletinForms">
<!-- 隐藏数据 因为该页面为公有业页，若不是弹出层，则需要重新跳转 -->
<form id="bidEvaluationReportForm" method="post">
        <input type="hidden" name="objId" id="objId" value="<c:out value="${bulletin.objId}"/>"/>
		<input type="hidden" name="taskId" id="taskId" value="<c:out value="${param.taskId}"/>"/>
		<input type="hidden" name="project.objId" id="project.objId" value="${bulletin.project.objId}"/>
		<input type="hidden" name="bulletinNo" id="bulletinNo" value="${bulletin.project.projCode}"/>
		<input type="hidden" name="bullTitle" id="bullTitle" value="${bulletin.project.projName}"/>
		<input type="hidden" id="bullType" name="bullType" value="${bulletin.bullType}"/>
		<input type="hidden" id="buyMethod" name="ebuyMethod" value="${project.ebuyMethod }"/>
		<div class="formLayout formPa">
		<h5><span>报告内容</span></h5>
		<textarea name="bullContents" id="bullContents" class="required hidden"/></textarea>
	<div class="conOperation">
		<!-- 隐藏数据    确认状态 -->
		<input type="hidden" name="confirmStatus" id="confirmStatus" readonly="readonly"/>
		<input type="hidden" name="auditStatus" id="auditStatus" readonly="readonly" value="${bulletin.auditStatus}"/>
		<input type="hidden" name="useStatus" id="useStatus" readonly="readonly" value="${bulletin.useStatus}"/>
		<button id="bulletinSave" type="button" tabindex="18"><span><spring:message code="globe.save"/></span></button>
		<button id="bulletinSubmit" type="button" tabindex="18"><span>提 交</span></button>
		<button id="toPrint" type="button" tabindex="18"><span>打印预览</span></button>
	</div>
	</div>
</form>
</div>
<textarea id="bulletin" style="display:none;">${bulletin.bullContents}</textarea>