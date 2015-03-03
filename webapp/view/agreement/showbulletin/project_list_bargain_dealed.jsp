<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="cols sameHeigh">
	<h2>最近成交项目</h2>
	<ul class="newsList">
		<c:forEach var="project" items="${bargainDealedProjectList}">
		<li>
			<a href="javascript:void(0);" title="${project.projName}" onclick="common.goToBulletinDetail('${project.objId}','12');">
				<c:choose><c:when test="${fn:length(project.projName) > 20}">${fn:substring(project.projName,0,19)}…</c:when><c:otherwise>${project.projName}</c:otherwise></c:choose>
			</a><br/>
			<span>采购方式:${project.ebuyMethodCN}</span>&nbsp;&nbsp;
			<span>项目编号:${project.projCode}</span>
		</li>
		</c:forEach>
	</ul>
	<div class="more"><a href="javascript:void(0);" onclick="ShowBargainIndex.toBargainProjectListView();">更多</a></div>
</div>