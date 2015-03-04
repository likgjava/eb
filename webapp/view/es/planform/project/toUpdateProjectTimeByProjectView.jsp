<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/toUpdateProjectTimeByProjectView.js"></script>       
<div class="partContainers" id="bulletinForms">
<form id="projectForm" method="post">
	<div class="formLayout form2Pa">
		<input type="hidden" name="objId" id="objId" value="${project.objId }"/>
		<h5><span>项目时间维护：</span></h5>
		<%--<c:if test="${project.signUpSTime == null}" ><span class="highlight" >项目还未起草招标公告，不能调整时间</span></c:if>--%>
			<ul>
				<li>
					<label class="short" for="signUpSTime">报名开始时间：</label>
					<input type="text" name="signUpSTime" id="signUpSTime" class="required" readonly="readonly" value="<fmt:formatDate value="${project.signUpSTime }" pattern="yyyy-MM-dd HH:mm:SS"/>" />
					<span class="eleRequired">*</span>
				</li>
				<li>
					<label class="short" for="signUpETime">报名结束时间：</label>
					<input type="text" name="signUpETime" id="signUpETime" class="required"	readonly="readonly" value="${project.signUpETime }" />
					<span class="eleRequired">*</span>
				</li>
				<li>
					<label class="short" for="tenderStartTime"><dm:out value="local__OpenTendering__submitTender_zh">投标</dm:out>开始时间：</label>
					<input type="text" name="tenderStartTime" id="tenderStartTime" class="required" readonly="readonly" value="${project.tenderStartTime }" />
					<span class="eleRequired">*</span>
				</li>
				<li>
					<label class="short" for="tenderEndTime"><dm:out value="local__OpenTendering__submitTender_zh">投标</dm:out>结束时间：</label>
					<input type="text" name="tenderEndTime" id="tenderEndTime" readonly="readonly" class="required" value="${project.tenderEndTime }"/>
					<span class="eleRequired">*</span>
				</li>
				</ul>
	</div>
	<div class="conOperation">
	<button id="projectSave" type="button" tabindex="18" ><span><spring:message code="globe.save"/></span></button>
	</div>
</form>
</div>
   <div id="historyView"></div>