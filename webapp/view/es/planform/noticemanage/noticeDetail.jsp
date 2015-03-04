<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/noticemanage/noticeDetail.js"></script>

<div class="formLayout form2Pa">  
	<form id="noticeDetailForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<h4><span><dm:out value="local__OpenTendering__notice_zh">通知书</dm:out></span></h4>
		<ul>
			<li>
				<label for="noticeTitle"><spring:message code="noticeForm.noticeTitle"/></label>
				<span id="noticeTitle"></span>
			</li>
			<li>
				<label for="noticeType"><spring:message code="noticeForm.noticeType"/></label>
				<span id="noticeType"></span>
			</li>
			<li>
				<label for="projName"><spring:message code="noticeForm.projName"/></label>
				<span id="projName"></span>
			</li>
			<li>
				<label for="projCode"><spring:message code="noticeForm.projCode"/></label>
				<span id="projCode"></span>
			</li>
			<li>
				<label for="subProjName"><spring:message code="noticeForm.subProjName"/></label>
				<span id="subProjName"></span>
			</li>
			<li>
				<label for="subProjCode"><spring:message code="noticeForm.subProjCode"/></label>
				<span id="subProjCode"></span>
			</li>
			<li>
				<label for="sellerName"><spring:message code="noticeForm.sellerName"/></label>
				<span id="sellerName"></span>
			</li>
			<li>
				<label for="useStatus"><spring:message code="noticeForm.useStatus"/></label>
				<span id="useStatus"></span>
			</li>
			<li>
				<label for="createUser"><spring:message code="noticeForm.createUser"/></label>
				<span id="createUser"></span>
			</li>
			<li>
				<label for="createTime"><spring:message code="noticeForm.createTime"/></label>
				<span id="createTime"></span>
			</li>
			<li>
				<label for="updateUser"><spring:message code="noticeForm.updateUser"/></label>
				<span id="updateUser"></span>
			</li>
			<li>
				<label for="updateTime"><spring:message code="noticeForm.updateTime"/></label>
				<span id="updateTime"></span>
			</li>
			<li style="width:100%;height:120px">
				<label for="noticeContent"><spring:message code="noticeForm.noticeContent"/></label>
				<span id="noticeContent"></span>
			</li>
			<li style="width:100%;height:80px">
				<label for="noticeRemark"><spring:message code="noticeForm.noticeRemark"/></label>
				<span id="noticeRemark"></span>
			</li>
		</ul>
	    <div class="conOperation">
	        <button class="btn primary" id="noticeReturn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
	    </div>
	</form>
</div>
