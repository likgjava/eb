<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<!--推荐代理机构 开始-->
<c:if test="${fn:length(buyerList)!= 0}">
<div class="workNews hotProduct">
  <h4><span class="jiantou">采购明星</span></h4>
  <ul class="center">
  	<c:forEach var="buyer" items="${buyerList}">
		<li>
			<div class="orgInfoPic">
				<a onclick="common.geToBuyerDetail('${buyer.objId}');" href="javascript:void(0);" title="${buyer.orgInfo.orgName}">
				<img src="<%=request.getContextPath()%>/AttachmentController.do?method=showImg&objId=${buyer.orgInfo.logo}">
				</a>
			</div>
			<div><a onclick="common.geToBuyerDetail('${buyer.objId}');"  href="javascript:void(0);" title="${buyer.orgInfo.orgName}">${buyer.orgInfo.orgName}</a></div>
		</li>
	</c:forEach>
  </ul>
</div>
</c:if>
<!--推荐代理机构 结束-->