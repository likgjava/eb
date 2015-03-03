<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="cols sameHeigh">
	<h2>工程</h2>
	<ul>
		<c:forEach var="project" items="${engineeringProjectList}" varStatus="status">
		<li>
			<div class="announcementBox">
				<h3>
					<a href="javascript:void(0);" title="${project.projName}" onclick="common.goToBulletinDetail('${project.objId}','01');">
						<c:choose><c:when test="${fn:length(project.projName) > 35}">${fn:substring(project.projName,0,34)}…</c:when><c:otherwise>${project.projName}</c:otherwise></c:choose>
					</a>
				</h3>
				<ul>
					<li>项目编号：<em>${project.projCode}</em></li>
					<li>采购方式：<em>${project.ebuyMethodCN}</em></li>
					<li>项目进度：<em>${project.projProcessStatusCN}</em></li>
					<li>报名截止时间：<em><fmt:formatDate value="${engineeringProjectRuleList[status.index].signUpETime}" pattern="yyyy-MM-dd HH:mm"/></em> </li>
					<li>查看公告：<a href="javascript:void(0);" onclick="common.goToBulletinDetail('${project.objId}','01');">[招标公告]</a></li>
				</ul>
				<div class="announcementBtn">
					<a class="good" href="javascript:void(0);" onclick="ShowBiddingIndex.shareBulletin('${project.objId}');"><span>推荐</span></a>
					<a class="collection" title="收藏此公告" href="javascript:void(0);" onclick="ShowBiddingIndex.addFavorites('${project.objId}');"><span>收藏</span></a>
					<c:choose>
						<c:when test="${bulletin.signUpSTime.time > nowDate.time}">
							<a href="javascript:void(0);" class="bigBtn" onclick="common.goToBulletinDetail('${project.objId}','01');">报名未开始</a>
						</c:when>
						<c:when test="${bulletin.signUpETime.time < nowDate.time}">
							<a href="javascript:void(0);" class="bigBtn" onclick="common.goToBulletinDetail('${project.objId}','01');">报名结束</a>
						</c:when>
						<c:otherwise>
				         	<a href="javascript:void(0);" class="bigBtn" onclick="common.goToBulletinDetail('${project.objId}','01');">去报名</a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</li>
		</c:forEach>
	</ul>
	<div class="more">
		<a title="点击查看更多" href="javascript:void(0);" onclick="ShowBiddingIndex.toBiddingProjectListView('B');">更多</a>
	</div>
</div>