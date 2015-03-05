<%@ page contentType="text/html;charset=UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="cols">
	<h2>推荐专家</h2>
	<div class="expertsInfo ">
		<c:set var="exFirst" value="${expertList[0]}"></c:set>
		<div class="expertsBase">
			<h3>
				<a href="javascript:void(0);" onclick="common.goToExpertDetail('${exFirst.objId}');">${exFirst.name} </a>
				<c:if test="${exFirst.isConsultant=='1'}"><img src="<%=request.getContextPath()%>/view/resource/skin/smallscale/img/expert-zx.png" alt="咨询专家"></c:if>
				<c:if test="${exFirst.isReviewers=='1'}"><img src="<%=request.getContextPath()%>/view/resource/skin/smallscale/img/expert-ps.png" alt="评审专家"></c:if>
			</h3>
			<h4>${exFirst.professionQualificationLevelCN}</h4>
			<p class="expertsPhotos"><img style="width: 120px; height: 156px;" src="<%=request.getContextPath()%>/AttachmentController.do?method=showImg&objId=${exFirst.photo}&fileNameSuffix=_120*160" /></p>
		</div>
		<div class="expertsdetails">
			<h5>特长：</h5>
			<p><c:choose><c:when test="${fn:length(exFirst.technicalExcellence)> 50 }">${fn:substring(exFirst.technicalExcellence,0,50) }… </c:when><c:otherwise>${exFirst.technicalExcellence }</c:otherwise></c:choose></p>
			<h5>经验：</h5>
			<p><c:choose><c:when test="${fn:length(exFirst.tenderExperience)> 50 }">${fn:substring(exFirst.tenderExperience,0,50) }… </c:when><c:otherwise>${exFirst.tenderExperience }</c:otherwise></c:choose></p>
		</div>
	</div>
	<div class="newsList">
		<ul>
			<c:forEach var="expert" items="${expertList}" begin="1">
			<li>
				<a href="javascript:void(0);" onclick="common.goToExpertDetail('${expert.objId}');">${expert.name}</a>
				<span>：${expert.belongIndustry.name}</span>
			</li>
			</c:forEach>
		</ul>
	</div>
	<div class="more"><a target="_blank" href="<%=request.getContextPath()%>/ExpertShowController.do?method=toExpertList&rp=20&page=1&districtLevel=1" title="更多">更多</a></div>
</div>