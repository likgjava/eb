<%@ page contentType="text/html;charset=UTF-8" %><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div class="list-content">
	<ul class="list-view hlisting sell">
		<c:forEach var="template" items="${PAGERESULT.data}">
		<li class="list-item">
			<h3 class="summary" style="margin-left:5px; width: 400px;">${template.templateName}</h3>
			<ul class="attribute nomargin">                        	
				<li class="srvlist">
					<a href="javascript:void(0);" onclick="show_list.favoriteTemplate('${template.objId}');return false;"><img src="view/resource/skin/sysicon/16/award_star_add.png">收藏(${template.favoriteNum})</a>
					<c:if test="${template.isShare}">
					<a href="javascript:void(0);" onclick="show_list.downloadTemplate('${template.objId}','${template.templateFile}');return false;"><img src="view/resource/skin/sysicon/16/arrow_down.png">下载(${template.downNum})</a>
					</c:if>
				</li>
				<li class="place"><fmt:formatDate value="${template.createTime}" pattern="yyyy.MM.dd"/></li>
				<li class="place" title="${template.districtName}"><c:choose><c:when test="${fn:length(template.districtName) > 10}">${fn:substring(template.districtName,0,9)}...</c:when><c:otherwise>${template.districtName}</c:otherwise></c:choose></li>
			</ul>
			<div class="extend"></div>
			<p class="seller" style="left:5px; right: 5px;">简要描述：
				<c:choose><c:when test="${fn:length(template.templateDesc) > 110}">${fn:substring(template.templateDesc,0,109)}...</c:when><c:otherwise>${template.templateDesc}</c:otherwise></c:choose>
			</p>
		</li>
		</c:forEach>
	</ul>
</div>
<div><%@ include file="/view/pubservice/common/pageDirection.jsp" %></div>