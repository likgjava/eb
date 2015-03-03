<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
 
<h3>采购预告</h3>
<ul id="buyPreviewListUl">
	<c:forEach var="bulletin" items="${buyPreviewList}">
		<li>
			<a id="${bulletin.objId}" href="javascript:void(0);" title="${bulletin.bullTitle}">
				<c:choose>
					<c:when test="${fn:length(bulletin.bullTitle) > 30}">${fn:substring(bulletin.bullTitle,0,30)}… </c:when>
					<c:otherwise>${bulletin.bullTitle}</c:otherwise>
				</c:choose>
			</a>
			<span class="date newDate"><fmt:formatDate value="${bulletin.createTime}" pattern="MM-dd"/></span>
		</li>
	</c:forEach>
</ul>
<span class="more"><a href="javascript:void(0);" onclick="bulletin_buy_pre.showMorePurchasePreview();return false;" title="更多" class="right">更多</a></span>

<script type="text/javascript">
/** 采购需求首页-采购预告页面 */

var bulletin_buy_pre={};
 
//显示更多采购预告
bulletin_buy_pre.showMorePurchasePreview = function() {
	var targetUrl = $('#initPath').val()+"/BulletinShowController.do?method=toBuyPreList::rp=21::page=1";
	var contentSuppUrl = $('#initPath').val()+'/BulletinShowController.do?method=getRecommendProject::rp=5::page=1';
	window.open( $('#initPath').val()+"/IndexViewController.do?method=index&contentMainUrl="+targetUrl +"&contentSuppUrl="+contentSuppUrl);	
}
 
$(document).ready(function(){
	$('#buyPreviewListUl').find("a").click(function() {
		var targetUrl = $('#initPath').val()+"/BulletinShowController.do?method=toShowBulletinView::objId="+$(this).attr("id");
		var contentSuppUrl = $('#initPath').val()+'/BulletinShowController.do?method=getRecommendProject::rp=9::page=1';
		window.open( $('#initPath').val()+"/IndexViewController.do?method=index&contentMainUrl="+targetUrl +"&contentSuppUrl="+contentSuppUrl);
	})
});
</script>