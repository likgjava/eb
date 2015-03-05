<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<ul class='brandList'>
<c:forEach var="recommendBrand" items="${recommendBrandList}">
  <li><a title="${recommendBrand.goodsBrand.brandName}" href="<%=request.getContextPath()%>/GoodsShowController.do?method=toGoodsByBrandList&rp=20&page=1&brandId=${recommendBrand.goodsBrand.objId}" target="_blank"><img style="width: 100px; height: 75px;" src="AttachmentController.do?method=showImg&objId=${recommendBrand.goodsBrand.mainLogo}&fileNameSuffix=_100*75" /></a></li>
</c:forEach>
</ul>