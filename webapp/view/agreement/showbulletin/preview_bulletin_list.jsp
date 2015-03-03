<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<h3><span>竞价公告</span></h3>
<ul>
	<c:forEach var="bulletin" items="${previewBulletinList}">
		<li><a href="javascript:void(0);" onclick="common.goToBulletinDetail('${bulletin.objId}');return false;">${bulletin.project.projName}</a>
		<span class="date newDate"><fmt:formatDate value="${bulletin.project.createTime}" pattern="MM-dd"/></span>
		</li>
	</c:forEach>
</ul>
<span class="more"><a class="right" title="更多" href="javascript:void(0);" onclick="BargainingBulletinIndex2.showMoreBarginBulletin();return false;">更多</a></span>

<script>
/** 采购需求首页-竞价公告页面 */
var BargainingBulletinIndex2 = {};

//显示更多竞价公告
BargainingBulletinIndex2.showMoreBarginBulletin = function() {
	$("#sysContent").loadPage($('#initPath').val()+"/BulletinShowController.do?method=toBulletinList&rp=21&page=1&bulletinType=12");	
}

$(document).ready(function(){
	var nowDate = gpcsoftDate;
	var nowDateStr = (nowDate.getMonth()<9 ? "0" : "")+(nowDate.getMonth()+1)+"-"+nowDate.getDate();
	$("span.newDate").each(function(index, domEle){
		if(nowDateStr == $(domEle).text()){ //是否新信息
			$(domEle).before('<span class="state redHL">new!</span>');
		}
	});
});
</script>