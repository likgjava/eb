<%@ page contentType="text/html;charset=UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="cols">
	<h2><span class="titleDown">供应商</span></h2>
	<div class="hotAreaNews">
		<c:set var="supFirst" value="${supplierList[0]}"></c:set>
		<h3><a href="javascript:void(0);" onclick="common.goToOrgShop('${supFirst.orgInfo.objId}');return false;">${supFirst.orgInfo.orgName}</a></h3>
		<p class="hotImg"><img style="width: 60px; height: 60px;" src="AttachmentController.do?method=showImg&objId=${supFirst.orgInfo.logo}" /></p>
		<p class="hotDetails"><c:choose><c:when test="${fn:length(supFirst.orgInfo.descCn)> 60}">${fn:substring(supFirst.orgInfo.descCn,0,59) }…</c:when><c:otherwise>${supFirst.orgInfo.descCn }</c:otherwise></c:choose></p>
	</div>
	<ul class="newsList">
		<c:forEach var="supplier" items="${supplierList}" begin="1">
		<li>
			<span>[${supplier.orgInfo.belongIndustry!=null ? supplier.orgInfo.belongIndustry.name : '其他行业'}]</span>
			<a href="javascript:void(0);" onclick="common.goToOrgShop('${supplier.orgInfo.objId}');">
				<c:choose><c:when test="${fn:length(supplier.orgInfo.belongIndustry.name)+fn:length(supplier.orgInfo.orgName) > 32}">${fn:substring(supplier.orgInfo.orgName,0,32-fn:length(supplier.orgInfo.belongIndustry.name )) }…</c:when><c:otherwise>${supplier.orgInfo.orgName}</c:otherwise></c:choose>
			</a>
		</li>
		</c:forEach>
	</ul>
	<div class="more"><a href="<%=request.getContextPath()%>/SupplierShowController.do?method=toSupplierList&rp=20&page=1&districtLevel=1" target="_blank" title="更多">更多</a></div>
</div>