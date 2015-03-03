<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<h3><span>结果公告</span></h3>
<ul>
	<c:forEach var="bulletin" items="${bargainedBulletinList}">
		<li>
			<a href="javascript:void(0);" onclick="common.goToBulletinDetail('${bulletin.objId}');return false;">${bulletin.project.projName}</a>
			<span class="state redHL"><fmt:formatNumber value="${bulletin.project.budgetTotalMoney*0.0001}" pattern="#,##0.00" />万元</span>
			<span class="date"><fmt:formatDate value="${bulletin.project.evalEndTime}" pattern="MM-dd"/></span>
		</li>
	</c:forEach>
</ul>
<span class="more"><a class="right" href="javascript:void(0);" onclick="BargainedBulletinIndex2.showMoreBarginedBulletin();return false;">更多</a></span>

<script>
/** 采购需求首页-结果公告页面 */
var BargainedBulletinIndex2 = {};

//显示更多结果公告
BargainedBulletinIndex2.showMoreBarginedBulletin = function() {
	$("#sysContent").loadPage($('#initPath').val()+"/BulletinShowController.do?method=toBulletinList&rp=21&page=1");	
}
</script>