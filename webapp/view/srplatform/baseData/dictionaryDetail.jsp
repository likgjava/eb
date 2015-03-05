<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/baseData/dictionaryDetail.js"></script>

<div class="formZone">  
	<form id="dictionaryDetailForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<div class="desc">数据字典      <spring:message code="globe.input.required.prompt"/></div>
		<div class="formFieldset">
			<div class="formRow">
				<div class="formLabel"><spring:message code="dictionaryForm.createTime"/></div>
				<div class="formField">
					<div><span id="createTime"></span></div>
				</div>
			</div>
			<div class="formRow">
				<div class="formLabel"><spring:message code="dictionaryForm.dicName"/></div>
				<div class="formField">
					<div><span id="dicName"></span></div>
				</div>
			</div>
			<div class="formRow">
				<div class="formLabel"><spring:message code="dictionaryForm.dicCacheName"/></div>
				<div class="formField">
					<div><span id="dicCacheName"></span></div>
				</div>
			</div>
			<div class="formRow">
				<div class="formLabel"><spring:message code="dictionaryForm.dicMemo"/></div>
				<div class="formField">
					<div><span id="dicMemo"></span></div>
				</div>
			</div>
			<div class="formRow">
				<div class="formLabel"><spring:message code="dictionaryForm.dicType"/></div>
				<div class="formField">
					<div><span id="dicType"></span></div>
				</div>
			</div>
			<div class="formRow">
				<div class="formLabel"><spring:message code="dictionaryForm.dicValue"/></div>
				<div class="formField">
					<div><span id="dicValue"></span></div>
				</div>
			</div>
			<div class="formRow">
				<div class="formLabel"><spring:message code="dictionaryForm.dicUseCache"/></div>
				<div class="formField">
					<div><span id="dicUseCache"></span></div>
				</div>
			</div>
		</div>
	    <div class="btnArea">
	        <button class="btn primary" id="dictionaryReturn" type="button" tabindex="19"><span><span><spring:message code="globe.return"/></span></span></button>
	    </div>
	</form>
</div>
