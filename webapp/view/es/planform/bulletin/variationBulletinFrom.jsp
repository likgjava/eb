<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/bulletin/variationBulletinFrom.js"></script>       
<div class="partContainers" id="variationDivId">
<!-- 隐藏数据 因为该页面为公有业页，若不是弹出层，则需要重新跳转 -->
<input type="hidden" id="fromType" value="${param.fromType}">
<form id="variationForm" method="post">
	<div class="formLayout">
		<h5><span>基本信息</span></h5>
			<ul>
				<li>
					<label for="bullTitle"><spring:message code="bulletinForm.bullTitle"/>：</label>
					<input type="text" name="bullTitle" id="bullTitle" class="required" value="${bulletin.bullTitle}"/>
					<span class="eleRequired">*</span> 	  
				</li>
				<li>
					<label for="bulletinNo"><spring:message code="bulletinForm.bulletinNo"/>：</label>
					<input type="text" name="bulletinNo" id="bulletinNo" class="required" value="${bulletin.bulletinNo}" />
					<span class="eleRequired">*</span> 	  
				</li>
		</ul>
	</div>
	<div class="formLayout formPa">
		<h5><span>变更公告内容</span></h5>
		<textarea name="bullContents" id="bullContents" class="required hidden"/></textarea><span class="eleRequired">*</span> 	
	</div>
	<div class="conOperation">
		<!-- 隐藏数据    确认状态 -->
		<button id="bulletinSave" type="button" tabindex="18"><span><spring:message code="globe.save"/></span></button>
		<button id="bulletinSubmit" type="button" tabindex="18"><span>提 交</span></button>
		<button id="returnBtn" type="button" tabindex="18"><span>返回</span></button>
	</div>
</form>
</div>
<textarea id="bulletin" style="display:none;">${bulletin.bullContents}</textarea>