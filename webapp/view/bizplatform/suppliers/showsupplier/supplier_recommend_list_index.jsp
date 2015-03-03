<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<!--推荐供应商 开始-->
<div class="cols recSuppliers">
	<h2>推荐供应商</h2>
	<ul class="sipplierShowList" id="recommendSupplierList">
		<c:forEach var="supplier" items="${supplierList}" varStatus="status">
		<li name="page${status.index / 3}" class="<c:if test="${status.index > 2}">hidden</c:if> <c:if test="${status.index % 3 == 2}">omega</c:if>">
			<h3>
				<a href="javascript:void(0);" onclick="common.goToOrgShop('${supplier.orgInfo.objId}');" title="${supplier.orgInfo.orgName}">
	        		<c:choose><c:when test="${fn:length(supplier.orgInfo.orgName) > 13}">${fn:substring(supplier.orgInfo.orgName,0,12)}…</c:when><c:otherwise>${supplier.orgInfo.orgName}</c:otherwise></c:choose>
	        	</a>
	        </h3>
			<p class="hotImg"><a href="javascript:void(0);" onclick="common.goToOrgShop('${supplier.orgInfo.objId}');"><img style="width: 127px; height: 127px;" src="<c:url value="AttachmentController.do?method=showImg&objId=${supplier.orgInfo.logo}" />"></a></p>
			<p class="hotPrice" style="height: 48px;">经营：<c:choose><c:when test="${fn:length(supplier.orgInfo.bidForRangeName) > 27}">${fn:substring(supplier.orgInfo.bidForRangeName,0,26)}…</c:when><c:otherwise>${supplier.orgInfo.bidForRangeName}</c:otherwise></c:choose></p>
			<p><span  class="star" title="星级">三星</span></p>
			<p class="btnBoxR"><a class="collection" title="加入收藏" href="javascript:void(0);" onclick="common.addFavorites('${supplier.orgInfo.objId }','${supplier.orgInfo.orgName}','02');return false;">加入收藏</a></p>
		</li>
		</c:forEach>
	</ul>
	<div class="pageBox">
		<a href="javascript:void(0);" onclick="RecommendSupplierList.showPage(this,0);" class="selected">1</a>
		<a href="javascript:void(0);" onclick="RecommendSupplierList.showPage(this,1);">2</a>
		<a href="javascript:void(0);" onclick="RecommendSupplierList.showPage(this,2);">3</a>
		<a href="javascript:void(0);" onclick="RecommendSupplierList.showPage(this,3);">4</a>
	</div>
	<div class="more"><a href="javascript:void(0);" onclick="ShowSupplierIndex.toSupplierList('');">更多</a></div>
</div>
<!--推荐供应商 结束-->
<script>
RecommendSupplierList = {};

//换页
RecommendSupplierList.showPage = function(domE, num){
	$('#recommendSupplierList').find('li').hide();
	$('#recommendSupplierList').find('li[name^=page'+num+']').show();
	$(domE).siblings().removeClass('selected');
	$(domE).addClass('selected');
}
</script>