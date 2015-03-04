<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/negotationrecord/negotationrecordViewInfo.js"></script>
<div class="partContainers">
	<input type="hidden" id="projectId" value="${projectId}"/>
	<input type="hidden" id="projProcessRule" value="${projProcessRule}"/>
	<c:if test="${projProcessRule=='TRUE'}">
	<div id="subDiv">
			<ul>	
				<c:forEach items="${subProjectList}" var="subProject" varStatus="i">
			    <li id="tab${i.count}" ><a href="#" onClick="negotationrecordInfo.getOpenBidUser('${subProject.objId}','${i.count}');" id="a${i.count }"><span>${subProject.projName}</span></a></li>
				</c:forEach>
			</ul>
	</div>
	</c:if>
	<div id="openBidList"></div>
</div>
