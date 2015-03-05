<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>


<!--最新加入专家 开始-->
<c:if test="${fn:length(expertList)!= 0}">
<div class="workNews hotProduct">
  <h4><span class="jiantou">最新加入专家</span></h4>
  <ul class="center">
  	<c:forEach var="expert" items="${expertList}">
		<li>
			<div class="personPic">
				<a style="padding-left:5px;" href="javascript:void(0);" onclick="common.goToExpertDetail('${expert.objId}');">
				<img src="<%=request.getContextPath()%>/AttachmentController.do?method=showImg&objId=${expert.photo}&fileNameSuffix=_180*240">
				</a>
			</div>
			<div><a href="javascript:void(0);" onclick="common.goToExpertDetail('${expert.objId}');">${expert.name}</a></div>
			<div>${expert.professionQualificationLevelCN}</div>
		</li>
	</c:forEach>
  </ul>
</div>
</c:if>
<!--最新加入专家 结束-->