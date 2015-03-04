<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="entity">
	<ul class="list">
		<c:forEach var="serviceGroup" items="${serviceGroupList}">
		<li class="item">
			<div class="pic">
				<img src="<c:url value="AttachmentController.do?method=showImg&objId=${serviceGroup.appendService.servicePic}" />">
			</div>
			<div class="title">
				<a title="组合服务：${serviceGroup.appendService.serviceName}" href="javascript:void(0);" onclick="ServiceListForm.view('${serviceGroup.appendService.objId}','showView','ServiceBase');">
				<c:choose>
  					<c:when test="${fn:length(serviceGroup.appendService.serviceName) > 5}">${fn:substring(serviceGroup.appendService.serviceName,0,4)}…</c:when>
  					<c:otherwise>${serviceGroup.appendService.serviceName}</c:otherwise>
  				</c:choose>
				</a>
			</div>
			<c:if test="${!empty serviceGroup.memberClass.memberClassName}">
				<div class="title">
					<a title="会员级别：${serviceGroup.memberClass.memberClassName}" href="javascript:void(0);" onclick="ServiceListForm.view('${serviceGroup.memberClass.objId}','showView','MemberClass');">
						<c:choose>
	  					<c:when test="${fn:length(serviceGroup.memberClass.memberClassName) > 5}">${fn:substring(serviceGroup.memberClass.memberClassName,0,4)}…</c:when>
	  					<c:otherwise>${serviceGroup.memberClass.memberClassName}</c:otherwise>
	  				</c:choose>
					</a>
				</div>
			</c:if>
			<div class="price">${serviceGroup.duration} ${serviceGroup.appendService.serviceUnitCN}</div>
			<div class="price">￥<span><fmt:formatNumber value="${serviceGroup.amount}" pattern="#,##0.00#" /></span></div>
			<div class="title">
				<a href="javascript:void(0);" onclick="ServiceListForm.view('${serviceGroup.objId}','updateView','ServiceGroup','${serviceGroup.mainService.objId}');">修改</a>
				<a href="javascript:void(0);" onclick="ServiceListForm.deleteServiceGroup('${serviceGroup.objId}','${serviceGroup.mainService.objId}');">删除</a>
			</div>
		</li>
		</c:forEach>
	</ul>
</div>
<div class="view-all">
	<span class="sysicon siAdd">
		<a href="javascript:void(0);" onclick="ServiceListForm.addServiceGroup('${serviceBaseId}');return false;">增加组合计费</a>
	</span>
</div>