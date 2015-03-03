<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<h3>中标公告</h3>
<ul class="smallDivUl" id="winbidBulletinList">
	<c:forEach var="bulletin" items="${winbidBulletinList}">
		<li>
			<a id="${bulletin.objId}" href="javascript:void(0);" title="${bulletin.bullTitle}">
				<c:choose>
					<c:when test="${fn:length(bulletin.bullTitle) > 30}">${fn:substring(bulletin.bullTitle,0,30)}… </c:when>
					<c:otherwise>${bulletin.bullTitle}</c:otherwise>
				</c:choose>
			</a>
			<span class="state redHL">${bulletin.project.ebuyMethodCN}</span>
			<span class="date"><fmt:formatDate value="${bulletin.createTime}" pattern="MM-dd"/></span>
		</li>
	</c:forEach>
</ul>
<span class="more"><a href="javascript:void(0);" onclick="BulletinWinbidList.showMoreWinbidBulletin();return false;" title="更多" class="right">更多</a></span>

<script type="text/javascript">
/**
 * 采购需求首页中标公告列表
 */

var BulletinWinbidList={};
 
//显示更多中标公告
BulletinWinbidList.showMoreWinbidBulletin = function() {
	$("#sysContent").loadPage($('#initPath').val()+"/BulletinShowController.do?method=toBulletinList&rp=21&page=1&bulletinType=13");	
}
 
$(document).ready(function(){
	$('#winbidBulletinList').find("a").click(function() {
		common.goToBulletinDetail($(this).attr("id"),"");
	})
});
</script>