<%@ page contentType="text/html;charset=UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="workNews hotProduct">
	<h4><span class="jiantou">推荐采购项目</span></h4>
	<c:if var="hasProject" test="${fn:length(recommendProjectList)!= 0}">
		<ul class="center">
			<c:forEach var="recommendProject" items="${recommendProjectList}">
			<li>
				<div class="goodsPic">
					<a href="javascript:void(0);" onclick="RecommendProjectSupp.showProjectBulletin('${recommendProject.project.objId}','${recommendProject.project.ebuyMethod}');return false;">
						<img src="<c:url value="AttachmentController.do?method=showImg&objId=${recommendProject.picture}" />">
					</a>
				</div>
				<div>
					<a href="javascript:void(0);" onclick="RecommendProjectSupp.showProjectBulletin('${recommendProject.project.objId}','${recommendProject.project.ebuyMethod}');return false;">${recommendProject.project.projName}</a>
					<div><fmt:formatDate value="${recommendProject.project.evalStartTime}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
				</div>
			</li>
			</c:forEach>
		</ul>
	</c:if>
	<c:if test="${!hasProject}">暂无推荐项目!</c:if>
</div>

<script>
var RecommendProjectSupp = {};

//查看项目公告
RecommendProjectSupp.showProjectBulletin = function(projectId, ebuyMethod){
	var bulletinType = (ebuyMethod=='00' ? '01' : '12');
	common.goToBulletinDetail(projectId, bulletinType);
}
</script>