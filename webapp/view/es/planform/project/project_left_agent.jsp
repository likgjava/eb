<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<ul>
	<li class="" onmouseover="javascript:changeMouseover(this);" onmouseout="javascript:changeMouseout(this);">
		<a href="#" id="menu_project_number"	class="icon1" onclick="checkProjectMenu('menu_project_number');"><span></span>设置招标编号</a>		
	</li>
	<li class="" onmouseover="javascript:changeMouseover(this);" onmouseout="javascript:changeMouseout(this);">
		<a href="#" id="menu_project"	class="icon1" onclick="checkProjectMenu('menu_project');"><span></span>项目<dm:out value="local__package" tenderType="${project.tenderType}">包组</dm:out></a>		
	</li>
	<li class="">
		<a href="#"	id="menu_projProcessRule" class="icon1" onclick="checkProjectMenu('menu_projProcessRule');"><span></span>项目规则</a>
	</li>
	<li class="">
		<a href="#"	id="menu_projProcessRule_aidot" class="icon1" onclick="checkProjectMenu('menu_projProcessRule_aidot');"><span></span>审核项目规则</a>
	</li>
	<li class="">
		<a href="#"	id="menu_purchaseDoc" class="icon1" onclick="checkProjectMenu('menu_purchaseDoc');"><span></span><dm:out value="local__PURDOC" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">招标文件</dm:out></a>
	</li>
	<li class="">
		<a href="#"	id="menu_purBulletin" class="icon1" onclick="checkProjectMenu('menu_purBulletin');"><span></span><dm:out value="local__PURBULLETIN"  tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">公告</dm:out></a>
	</li>
</ul>