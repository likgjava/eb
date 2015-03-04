<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/updateProjectEvalTime.js"></script>       
<div class="partContainers" id="bulletinForms">
<form id="evalStartTimeForm" method="post">
	<div class="formLayout form2Pa">
		<input type="hidden" name="objId" id="objId" value="${projectRule.objId}"/>
		<h5><span>设置评标时间:</span></h5>
			<ul>

				<li>
					<label class="short" for="evalStartTime">评标开始时间：</label>
					<input type="text" name="evalSTime" id="evalStartTime" class="required" readonly="readonly" value="${project.evalStartTime }" />
					<span class="eleRequired">*</span>
				</li>
				<li>
					<label class="short" for="evalEndTime">评标结束时间：</label>
					<input type="text" name="evalETime" id="evalEndTime" class="required"	readonly="readonly" value="${project.evalEndTime }" />
					<span class="eleRequired">*</span>
				</li>
				</ul>
	</div>
	<div class="conOperation">
	<button id="projectSaveEvalTime" type="button" tabindex="18"><span><spring:message code="globe.save"/></span></button>
	</div>
</form>
</div>
   <div id="historyView"></div>