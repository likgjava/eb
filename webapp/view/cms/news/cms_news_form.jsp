<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/cms/news/cms_news_form.js"></script>
<style>
.noCSS table, .noCSS table th, .noCSS table td, .noCSS ul, .noCSS input, .noCSS textarea, .noCSS select
{float:none;position:static;border:none;padding:0;margin:0;width:auto;height:auto;font-weight:normal;color:#000;display:block;overflow:hidden;}
.noCSS table{border-spacing:0;border-collapse:collapse;}
.noCSS table, .noCSS table th, .noCSS table td, .noCSS ul{background:none;}
.noCSS div{margin: 0px;}
</style>
<div class="formLayout form2Pa">
	<input type="hidden" name="addType" id="addType" value="<c:out value="${param.addType}"/>"/>
	<input type="hidden" id="returnSelf" value="<c:out value="${param.returnSelf}"/>"/>

	<div class="treeEditNav">
		<ul></ul>
	</div>
	
	<form id="cmsNewsForm" method="post" action="CmsNewsController.do?method=upLoadFile">
		<input type="hidden" name="uploadFileName" id="uploadFileName" value=""/>
		<input type="hidden" name="channel.objId" id="channelId" value="<c:out value="${param.channelId}"/>"/>
		<input type="hidden" name="objId" id="newsId" value="<c:out value="${param.objId}"/>"/>
     	<h5><span name="channelName"></span></h5>
     	<ul id="cmsNewsFormUl"></ul>
		<div class="conOperation">
			<button type="button" id="cmsNewsSave"><span><spring:message code="globe.save"/></span></button>
			<button type="button" id="cmsNewsReturn" tabindex="19""><span><spring:message code="globe.return"/></span></button>
			<button type="button" id="cmsNewsReset" type="button" tabindex="20""><span><spring:message code="globe.reset"/></span></button>
		</div>
	</form>
</div>