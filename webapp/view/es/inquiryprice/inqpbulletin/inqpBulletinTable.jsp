<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/inquiryprice/inqpbulletin/inqpBulletinTable.js"></script>

<div id="superviseDoDiv" style="overflow: auto;" class="page">
	<div style="margin-bottom: 5px;" class="tabs">
		<div class="tabsHeader">
		<div class="tabsHeaderContent">
		<input type="hidden" name="projectId" id="projectId" value="${projectId}" />
		<input type="hidden" name="agencyId" id="agencyId" value="${agencyId}" />
		<input type="hidden" name="supervisionId" id="supervisionId" value="${supervisionId}" />
			<ul>	
			    <li id="tab0" class=""><a href="#" onClick="mydesktop_supervise.getPurBulletin();"><span><dm:out value="local__PURBULLETIN"  tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">公告</dm:out></span></a></li>
				<!--<li id="tab1" class=""><a href="#" id="PublicBulletin" onClick="mydesktop_supervise.getPublicitybulletin();"><span>招标公示</span></a></li>
			--></ul>
		</div>
		</div>
		<div id="superviseTabDiv" style="height:auto;" class="tabsContent">
		</div>
	</div>
	</div>