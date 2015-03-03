<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div class="cols">
	<h2>需求</h2>
	<div class="demandList eInfoList">
		<c:forEach var="pHotTag" items="${pHotTagList}" varStatus="status">
		<h3><a href="javascript:void(0);" onclick="RequirementList.showMoreRequirement('${pHotTag.tagsId}');">${pHotTag.tagsName}</a></h3>
		<h4>&nbsp;
			<c:forEach var="hotTag" items="${hotTagListList[status.index]}">
				<a href="javascript:void(0);" onclick="RequirementList.showMoreRequirement('${hotTag.tagsId}');">${hotTag.tagsName }</a>
			</c:forEach>
		</h4>
		<ul>
			<c:forEach var="requirement" items="${requirementListList[status.index]}" varStatus="status1">
			<li <c:if test="${status1.index % 2 == 1}">class="omega"</c:if>>
				<h5>
					<a id="${requirement.objId}" href="<%=request.getContextPath()%>/RequirementInfo/${requirement.objId}" target="_blank" title="${requirement.title}">
					<c:choose><c:when test="${fn:length(requirement.title) > 23}">${fn:substring(requirement.title,0,22)}…</c:when><c:otherwise>${requirement.title}</c:otherwise></c:choose>
					</a>
				</h5>
				<ul>
					<li>地区：<em>${requirement.districtNames}</em></li>
					<li>截止时间：<em><fmt:formatDate value="${requirement.endTime}" pattern="MM-dd"/></em></li>
				</ul>
			</li>
			</c:forEach>
		</ul>
		</c:forEach>
	</div>
	<div class="more"> <a href="javascript:void(0);" onclick="RequirementList.showMoreRequirement();">更多</a> </div>
</div>

<script type="text/javascript">
/**电子采购首页-采购需求页面 */

var RequirementList = {};
 
//显示更多采购需求
RequirementList.showMoreRequirement = function(categoryCode) {
	var targetUrl = $('#initPath').val()+"/RequirementShowController.do?method=toRequirementList&rp=21&page=1&districtLevel=1";
	if(categoryCode != null){
		targetUrl += '&categoryCode=' + categoryCode;
	}
	window.open( targetUrl );	
}
</script>