<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%><%@ page import="java.util.*,java.math.*" %>

<h3>最近竞价公告</h3>
<ul class="smallDivUl">
	<%pageContext.setAttribute("now",(new Date()).getTime());%>
	<c:forEach var="bulletin" items="${bargainingBulletinList}">
		<c:set var="remainStartTime" value="${bulletin.project.evalStartTime.time - now}"></c:set>
		<li>
			<a href="javascript:void(0);" onclick="common.goToBulletinDetail('${bulletin.objId}');">
				<c:choose>
					<c:when test="${fn:length(bulletin.bullTitle) > 25}">${fn:substring(bulletin.bullTitle,0,25)}… </c:when>
					<c:otherwise>${bulletin.bullTitle}</c:otherwise>
				</c:choose>
			</a>
			<span class="state2 redHL">
				<c:choose>
					<c:when test="${bulletin.project.budgetTotalMoney < 10000}"><fmt:formatNumber value="${bulletin.project.budgetTotalMoney}" pattern="#,###" />元</c:when>
					<c:otherwise><fmt:formatNumber value="${bulletin.project.budgetTotalMoney*0.0001}" pattern="#,##0.00" />万元</c:otherwise>
				</c:choose>
			</span>
			<c:choose>
				<c:when test="${remainStartTime > 0}"><span class="state grayHL">未开始</span></c:when>
				<c:otherwise><span class="state greenHL">进行中</span></c:otherwise>
			</c:choose>
			<span class="date"><fmt:formatDate value="${bulletin.project.createTime}" pattern="MM-dd"/></span>
		</li>
	</c:forEach>
</ul>
<span class="more"><a href="javascript:void(0);" onclick="BulletinBarginIndexList.showMoreBarginBulletin();return false;" title="更多" class="right">更多</a></span>

<script type="text/javascript">
/**
 * 首页竞价公告列表页面
 */

var BulletinBarginIndexList={};
 
//显示更多竞价公告
BulletinBarginIndexList.showMoreBarginBulletin = function() {
	$("#sysContent").loadPage($('#initPath').val()+"/BulletinShowController.do?method=toBulletinList&rp=21&page=1&bulletinType=12");	
}

</script>