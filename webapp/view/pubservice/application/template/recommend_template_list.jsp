<%@ page contentType="text/html;charset=UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<style>
#recommendTemplateList li.list-item {border:1px dotted #ccc;}
#recommendTemplateList li.list-item:hover{background:#e3f1fc;}
</style>
<div class="workNews hotProduct">
	<h4><span class="jiantou">推荐范本</span></h4>
	<ul class="center" id="recommendTemplateList">
		<c:forEach var="template" items="${recommendTemplateList}">
		<li class="list-item">
			<div>${template.templateName}</div>
			<div><fmt:formatDate value="${template.createTime}" pattern="yyyy.MM.dd"/></div>
			<div>
				<a href="javascript:void(0);" onclick="show_list.favoriteTemplate('${template.objId}');return false;"><img src="view/resource/skin/sysicon/16/award_star_add.png">收藏(${template.favoriteNum})</a>
				<c:if test="${template.isShare}">
				<a href="javascript:void(0);" onclick="show_list.downloadTemplate('${template.objId}','${template.templateFile}');return false;"><img src="view/resource/skin/sysicon/16/arrow_down.png">下载(${template.downNum})</a>&nbsp;
				</c:if>
			</div>
		</li>
		</c:forEach>
	</ul>
</div>