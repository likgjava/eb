<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<!--推荐供应商 开始-->
<c:if test="${fn:length(supplierList)!= 0}">
<div class="workNews hotProduct">
  <h4><span class="jiantou">推荐供应商</span></h4>
  <ul class="center">
  	<c:forEach var="supplier" items="${supplierList}">
	<li>
		<div class="orgInfoPic">
			<a href="javascript:void(0);" onclick="common.goToOrgShop('${supplier.orgInfo.objId}');return false;">
			<img src="<c:url value="AttachmentController.do?method=showImg&objId=${supplier.orgInfo.logo}" />">
			</a>
		</div>
		<div><a href="javascript:void(0);" onclick="common.goToOrgShop('${supplier.orgInfo.objId}');return false;">${supplier.orgInfo.orgName}</a></div>
	</li>
	</c:forEach>
  </ul>
</div>
</c:if>
<!--推荐供应商 结束-->