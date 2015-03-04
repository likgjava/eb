<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/bulletin/qualificationForm.js"></script>       
<div class="partContainers" id="bulletinForms">
<!-- 隐藏数据 因为该页面为公有业页，若不是弹出层，则需要重新跳转 -->
<!--  
<div id="epsTabs">
<ul>
<c:forEach items="${subList }" var="subproject">
<li><a href="#consignListView" id="${subproject.objId}"
			class="refreshData" > ${subproject.projName}</a></li>
</c:forEach>
</ul>
</div>
-->
<input type="hidden" name="fromDiv" id="fromDiv" value="<c:out value="${param.fromDiv}"/>"/>
	<form id="bulletinForm" method="post">
	<div class="formLayout form2Pa">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${bulletin.objId}"/>"/>
		<input type="hidden" name="taskId" id="taskId" value="<c:out value="${param.taskId}"/>"/>
		<input type="hidden" name="project.objId" id="project.objId" value="${project.objId }"/>
		<input type="hidden" id="buyMethod" name="ebuyMethod" value="${project.ebuyMethod }"/>
		<!-- 隐藏数据    确认状态 -->
		<input type="hidden" name="confirmStatus" id="confirmStatus" />
		<input type="hidden" name="auditStatus" id="auditStatus" value="${bulletin.auditStatus}"/>
		<input type="hidden" name="useStatus" id="useStatus" value="${bulletin.useStatus}"/>
		<input type="hidden" name="bullType" id="bullType" value="${bulletin.bullType}"/>
		<input type="hidden" name="fromList" id="fromList" value="${fromList}"/>
		<h5><span>基本信息</span></h5>
	<div class="formLayout formPa">
		<h5><span><dm:out value="local__PURBULLETIN"  tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">公告</dm:out>内容</span></h5>
		<textarea name="bullContents" id="bullContents" class="required hidden"/></textarea><span class="eleRequired">*</span> 	
	</div>
	</form>
	<!-- 资格审查(产权交易竞价，土地交易拍卖，土地交易竞标，土地交易挂牌) -->
	<c:if test="${(project.ebuyMethod == '10'&& project.tenderType == '02') || (project.ebuyMethod == '09'&& project.tenderType == '02')  || (project.ebuyMethod == '08'&& project.tenderType == '02') || (project.ebuyMethod == '06'&& project.tenderType == '03') || (project.ebuyMethod == '00'&& project.tenderType == '01')}">
		<div class="formLayout" id="signUpCondFactorView">
		</div>
	</c:if>
	
	<div class="conOperation">
		<button id="bulletinSave" type="button" tabindex="18"><span><spring:message code="globe.save"/></span></button>
		<button id="bulletinSubmit" type="button" tabindex="18"><span>提 交</span></button>
		<button id="toPrint" type="button" tabindex="19"><span>打印预览</span></button>
		<button id="historyId" name="historyName" type="button" tabindex="20"><span>操作历史</span></button>
		<c:if test="${fromList=='yes'}">
			<button id="returnBtn" type="button" tabindex="18" onclick="bullentinReturn();"><span>返回</span></button>
		</c:if>
	</div>
</div>
<textarea id="bulletin" style="display:none;">${bulletin.bullContents}</textarea>
<div id="historyView"></div>