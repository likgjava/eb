<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/toUpdateProjectTime.js"></script>       
<div class="partContainers" id="bulletinForms">
	<div class="formLayout form2Pa">
	<input type="hidden" id="fromType" value="${param.fromType}">
	<form id="projectForm" method="post">
		<input type="hidden" name="objId" id="objId" value="${projectRule.objId}"/>
		<h5><span>项目时间维护：</span></h5>
		<%--<c:if test="${project.signUpSTime == null}" ><span class="highlight" >项目还未起草招标公告，不能调整时间</span></c:if>--%>
			<ul>
				<li>
					<label class="short" for="signUpSTime">报名开始时间：</label>
					<input type="text" name="signUpSTime" id="signUpSTime" class="required" readonly="readonly" value="<fmt:formatDate value="${projectRule.signUpSTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
					<span class="eleRequired">*</span>
				</li>
				<li>
					<label class="short" for="signUpETime">报名结束时间：</label>
					<input type="text" name="signUpETime" id="signUpETime" class="required"	readonly="readonly" value="<fmt:formatDate value="${projectRule.signUpETime}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
					<span class="eleRequired">*</span>
				</li>
				<li>
					<label class="short" for="tenderStartTime"><dm:out value="local__OpenTendering__submitTender_zh">投标</dm:out>开始时间：</label>
					<input type="text" name="submitStTime" id="tenderStartTime" class="required" readonly="readonly" value="<fmt:formatDate value="${projectRule.submitStTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
					<span class="eleRequired">*</span>
				</li>
				<li>
					<label class="short" for="tenderEndTime"><dm:out value="local__OpenTendering__submitTender_zh">投标</dm:out>结束时间：</label>
					<input type="text" name="submitETime" id="tenderEndTime" readonly="readonly" class="required" value="<fmt:formatDate value="${projectRule.submitETime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
					<span class="eleRequired">*</span>
				</li>
				<li>
					<label class="short" for="openBidStartDate"><dm:out value="local__OpenTendering__openBid_zh">开标</dm:out>开始时间：</label>
					<input type="text" name="openBidSDate" id="openBidStartDate" readonly="readonly" class="required" value="<fmt:formatDate value="${projectRule.openBidSDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
					<span class="eleRequired">*</span>
				</li>
				</ul>
	</form>
		<div class="conOperation">
		<button id="projectSave" type="button" tabindex="18" ><span><spring:message code="globe.save"/></span></button>
		</div>
	</div>


</div>
   <div id="historyView"></div>