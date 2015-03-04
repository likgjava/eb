<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/inquiryprice/inqpbulletin/updateInqpBulletinForm.js"></script>       
<div class="partContainers" id="bulletinForms">
<!-- 隐藏数据 因为该页面为公有业页，若不是弹出层，则需要重新跳转 -->
<input type="hidden" name="fromDiv" id="fromDiv" value="<c:out value="${param.fromDiv}"/>"/>
<form id="bulletinForm" method="post">
	<div class="formLayout form2Pa">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${bulletin.objId}"/>"/>
		<input type="hidden" name="taskId" id="taskId" value="<c:out value="${param.taskId}"/>"/>
		<input type="hidden" name="project.objId" id="project.objId" value="${project.objId }"/>
		<input type="hidden" id="buyMethod" name="ebuyMethod" value="${project.ebuyMethod }"/>
		<h5><span>基本信息</span></h5>
			<ul>

				<li>
					<label for="bullTitle" class="short"><spring:message code="bulletinForm.bullTitle"/>：</label>
					<input type="text" name="bullTitle" id="bullTitle" class="required" value="${bulletin.bullTitle }"/>
					<span class="eleRequired">*</span> 	  
				</li>

				<li>
					<label for="bulletinNo" class="short"><spring:message code="bulletinForm.bulletinNo"/>：</label>
					<input type="text" name="bulletinNo" id="bulletinNo" style="border: 0" readonly="readonly"  value="${bulletin.bulletinNo }" />
				</li>
				<li>
					<label for="signUpSTime" class="short">报名开始时间：</label>
					<input type="text" name="signUpSTime" id="signUpSTime" class="required" readonly="readonly" value="${bulletin.signUpSTime }" />
					<span class="eleRequired">*</span>
				</li>
				<li>
					<label for="signUpETime" class="short">报名结束时间：</label>
					<input type="text" name="signUpETime" id="signUpETime" class="required"	readonly="readonly" value="${bulletin.signUpETime }" />
					<span class="eleRequired">*</span>
				</li>
				<li>
					<label for="tenderStartTime" class="short"><dm:out value="local__OpenTendering__submitTender_zh">投标</dm:out>开始时间：</label>
					<input type="text" name="tenderStartTime" id="tenderStartTime" class="required" readonly="readonly" value="${bulletin.tenderStartTime }" />
					<span class="eleRequired">*</span>
				</li>
				<li>
					<label for="openBidTime" class="short">
					<dm:out value="local__OpenTendering__openBid_zh">开标</dm:out>开始时间：</label>
					<input type="text" name="openBidTime" id="openBidTime" class="required" readonly="readonly" value="${bulletin.tenderEndTime }"/>
					<!-- 隐藏数据    开标时间默认等于投标结束时间 -->
					<input type="hidden" name="tenderEndTime" id="tenderEndTime" readonly="readonly" />
					<span class="eleRequired">*</span>
				</li>
				
				</ul>
	</div>
	<div class="formLayout formPa">
		<h5><span><dm:out value="local__PURBULLETIN"  tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">公告</dm:out>内容</span></h5>
		<textarea name="bullContents" id="bullContents" class="required hidden"/></textarea><span class="eleRequired">*</span> 	
	</div>
	<div class="conOperation">
		<!-- 隐藏数据    确认状态 -->
		<input type="hidden" name="confirmStatus" id="confirmStatus" />
		<input type="hidden" name="auditStatus" id="auditStatus" value="${bulletin.auditStatus}"/>
		<input type="hidden" name="useStatus" id="useStatus" value="${bulletin.useStatus}"/>
		<input type="hidden" name="bullType" id="bullType" value="${bulletin.bullType}"/>
		<input type="hidden" name="fromList" id="fromList" value="${fromList}"/>
		<button id="bulletinSave" type="button" tabindex="18"><span><spring:message code="globe.save"/></span></button>
		<button id="bulletinSubmit" type="button" tabindex="18"><span>提 交</span></button>
		<button id="toPrint" type="button" tabindex="19"><span>打印预览</span></button>
		<button id="historyId" name="historyName" type="button" tabindex="20"><span>操作历史</span></button>
		<c:if test="${fromList=='yes'}">
		<button id="returnBtn" type="button" tabindex="18" onclick="bullentinReturn();"><span>返回</span></button>
		</c:if>
	</div>
			<!-- 报名符合性 
	<div class="formLayout" id="signUpCondFactorView"></div>
			-->
</form>
</div>
<textarea id="bulletin" style="display:none;">${bulletin.bullContents}</textarea>
   <div id="historyView"></div>