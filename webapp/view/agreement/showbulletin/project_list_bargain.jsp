<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="cols sameHeigh">
	<h2>竞价</h2>
	<ul>
		<c:forEach var="project" items="${bargainProjectList}" varStatus="status">
		<li>
			<div class="announcementBox">
				<h3>
					<a href="javascript:void(0);" title="${project.projName}" onclick="common.goToBulletinDetail('${project.objId}','12');">
						<c:choose><c:when test="${fn:length(project.projName) > 35}">${fn:substring(project.projName,0,34)}…</c:when><c:otherwise>${project.projName}</c:otherwise></c:choose>
					</a>
				</h3>
				<ul>
					<li>项目编号：<em>${project.projCode}</em></li>
					<li>采购方式：<em>${project.ebuyMethodCN}</em></li>
					<li>采购单位：<c:choose><c:when test="${fn:length(project.buyersName) > 16}">${fn:substring(project.buyersName,0,15)}…</c:when><c:otherwise>${project.buyersName}</c:otherwise></c:choose></li>
					<li>报名截止时间：<em><fmt:formatDate value="${project.signUpETime}" pattern="yyyy-MM-dd HH:mm"/></em> </li>
					<li class="spread"> 项目规则：
						<c:forEach var="projRule" items="${projRuleListList[status.index]}">
						<c:choose>
							<c:when test="${projRule.code == 'bargainNumber'}">
								<c:if var="isMany" test="${projRule.resValue == '0'}">单次报价</c:if><c:if test="${!isMany}">多次报价</c:if>
							</c:when>
						</c:choose>
						</c:forEach>
					</li>
				</ul>
				<div class="announcementBtn">
					<a class="good" href="javascript:void(0);" onclick="ShowBargainIndex.shareBulletin('${project.objId}');"><span>推荐</span></a>
					<a class="collection" href="javascript:void(0);" onclick="ShowBargainIndex.addFavorites('${project.objId}');"><span>收藏</span></a>
					<c:choose>
						<c:when test="${signUpSTime.time > nowDate.time}">
							<a href="javascript:void(0);" class="bigBtn" onclick="common.goToBulletinDetail('${project.objId}','12');">报名未开始</a>
						</c:when>
						<c:when test="${signUpETime.time < nowDate.time}">
							<a href="javascript:void(0);" class="bigBtn" onclick="common.goToBulletinDetail('${project.objId}','12');">报名已结束</a>
						</c:when>
						<c:otherwise>
				         	<a href="javascript:void(0);" class="bigBtn" onclick="common.goToBulletinDetail('${project.objId}','12');">去报名</a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</li>
		</c:forEach>
	</ul>
	<div class="more"><a title="点击查看更多" href="javascript:void(0);" onclick="ShowBargainIndex.toBargainProjectListView();">更多</a></div>
</div>
