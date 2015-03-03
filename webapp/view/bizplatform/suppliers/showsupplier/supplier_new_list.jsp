<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="cols">
	<h2>最新加盟供应商</h2>
	<ul>
		<c:forEach var="supplier" items="${newSupplierList}" varStatus="status">
		<li class="hotAreaNews">
			<h3>
				<a href="javascript:void(0);" onclick="common.goToOrgShop('${supplier.orgInfo.objId}');" title="${supplier.orgInfo.orgName}">
	        		<c:choose><c:when test="${fn:length(supplier.orgInfo.orgName) > 13}">${fn:substring(supplier.orgInfo.orgName,0,12)}…</c:when><c:otherwise>${supplier.orgInfo.orgName}</c:otherwise></c:choose>
	        	</a>
	        </h3>
			<p class="hotImg"><a href="javascript:void(0);" onclick="common.goToOrgShop('${supplier.orgInfo.objId}');"><img style="width: 60px; height: 60px;" src="<c:url value="AttachmentController.do?method=showImg&objId=${supplier.orgInfo.logo}" />"></a></p>
			<p class="hotDetails">经营范围：<c:choose><c:when test="${fn:length(supplier.orgInfo.bidForRangeName) > 27}">${fn:substring(supplier.orgInfo.bidForRangeName,0,26)}…</c:when><c:otherwise>${supplier.orgInfo.bidForRangeName}</c:otherwise></c:choose></p>
		</li>
		</c:forEach>
	</ul>
	<div class="more"><a href="javascript:void(0);" onclick="ShowSupplierIndex.toSupplierList('');">更多</a></div>
</div>