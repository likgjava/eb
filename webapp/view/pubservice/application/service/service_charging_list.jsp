<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="entity">
	<ul class="list">
		<c:forEach var="serviceCharging" items="${serviceChargingList}">
		<li class="item no-pic">
			<c:if test="${!empty serviceCharging.memberClass.memberClassName}">
				<div class="title">
					<a title="会员级别：${serviceCharging.memberClass.memberClassName}" href="javascript:void(0);" onclick="ServiceListForm.view('${serviceCharging.memberClass.objId}','showView','MemberClass');">
						<c:choose>
	  					<c:when test="${fn:length(serviceCharging.memberClass.memberClassName) > 5}">${fn:substring(serviceCharging.memberClass.memberClassName,0,4)}…</c:when>
	  					<c:otherwise>${serviceCharging.memberClass.memberClassName}</c:otherwise>
	  				</c:choose>
					</a>
				</div>
			</c:if>
			<div class="price">${serviceCharging.duration} ${serviceCharging.serviceBase.serviceUnitCN}</div>
			<div class="price">￥<span><fmt:formatNumber value="${serviceCharging.amount}" pattern="#,##0.00#" /></span></div>
			<div class="title">
				<a href="javascript:void(0);" onclick="ServiceListForm.view('${serviceCharging.objId}','updateView','ServiceCharging','${serviceCharging.serviceBase.objId}');">修改</a>
				<a href="javascript:void(0);" onclick="ServiceListForm.deleteServiceCharging('${serviceCharging.objId}','${serviceCharging.serviceBase.objId}');">删除</a>
			</div>
		</li>
		</c:forEach>
	</ul>
</div>
<div class="view-all">
	<span class="sysicon siAdd">
		<a href="javascript:void(0);" onclick="ServiceListForm.addServiceCharging('${serviceBaseId}');return false;">增加标准计费</a>
	</span>
</div>