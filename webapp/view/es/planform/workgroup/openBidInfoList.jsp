<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/workgroup/openBidInfoList.js"></script>
<input type="hidden" id="fromType" value ="${param.fromType}">
<div class="partContainers">
	<input type="hidden" id="projectId" value="${projectId}"/>
	<input type="hidden" id="groupType" value="${groupType}"/>
	<input type="hidden" id="workGroupId" value="${workGroup.objId}"/>
	<input type="hidden" id="projProcessRule" value="${projProcessRule}"/>
	<c:if test="${projProcessRule=='TRUE'}">
<div class="tabs" id="subDiv">
	<div class="tabsHeader">
		<div class="tabsHeaderContent">
			<ul>	
				<c:forEach items="${subProjectList}" var="subProject" varStatus="i">
			    <li id="tab${i.count}" onClick="openBidInfo.getOpenBidUser('${subProject.objId}','${i.count}');"><a href="#" ><span>${subProject.projName}</span></a></li>
				</c:forEach>
			</ul>
		</div>
	</div>
</div>
	</c:if>
	<div id="openBidList"></div>
</div>
