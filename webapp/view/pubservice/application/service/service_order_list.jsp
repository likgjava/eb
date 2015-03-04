<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<link href="view/resource/skin/pubservice/css/services.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/view/pubservice/application/service/service_order_list.js"></script>

<form id="ServiceSupplierForm" method="post">
<div id="epsTabs">
  <ul>
    <li>
      <a href="#subService" id = "tabs_serviceSubscribe" class="refreshData"><span>订购服务</span></a>
    </li>
    <li>
      <a href="#myService" id = "tabs_myservice" class="refreshData"><span>我的服务</span></a>
    </li>
  </ul>
  
  <div id="subService" style="padding:1em 0 0 0;">
		<div id="serviceSubscribe">
			<div class="title">推荐服务</div>
			<ul class="unity-list">
			<c:set var="priceUnit" value="" scope="request"></c:set>
			<c:forEach var="serviceBase" items="${serviceBaseList}" varStatus="status1">
				<c:if test="${serviceBase.isRecommendation=='1'}">
					<li class="unity-line">
					<div class="unity-logo">
						<img alt="${serviceBase.serviceName}" src="<c:url value="AttachmentController.do?method=showImg&objId=${serviceBase.servicePic}" />"></div>
					<dl class="unity-info">
						<dt class="unity-tilte"><strong>${serviceBase.serviceName}</strong></dt>
						<dd class="unity-content">${serviceBase.serviceDesc}
							<c:if test="${serviceBase.serviceLink != null}">
								<a href="${serviceBase.serviceLink}" target="_blank" name="serviceDetail" title="了解详情">了解详情&gt;&gt;</a>
							</c:if>
						</dd>
					</dl>
					
					<!--判断是是否已订阅-->
					<c:choose>
						<c:when test="${fn:contains(subscribedServiceIds,serviceBase.objId)}">
							<c:choose>
							<c:when test="${!empty serviceBase.servicePreposition && !empty serviceBase.servicePreposition.objId}">
								<div class="unity-price">
				                   <div class="unity-price-box" id="fee_${serviceBase.objId}" >
					                    <div class="unity-arrow-down">       
					                        <p>限${serviceBase.servicePreposition.serviceName}购买</p>
					                    </div>
				                    	<span class="unity-btn-down" title="展开">&nbsp;</span>
					                    <!-- 根据服务ID获取各个级别最小时长的服务计费 -->
					                    <div class="unity-arrow-box" style="display: none;"></div>
				            		</div>
							   	</div>
							</c:when>
							<c:otherwise>
								<div class="unity-price"><strong><fmt:formatNumber value="${serviceBase.servicePrice}" pattern="####" />元/${serviceBase.serviceUnitCN}</strong></div>
							</c:otherwise>
							</c:choose>
							<div class="unity-btn">
								<div class="unity-btn-box">
									<span class="unity-btn-order show-tips">已订阅...</span>
								</div>
							</div>
						</c:when>
						<c:otherwise>
							<c:choose>
							<c:when test="${!empty serviceBase.servicePreposition && !empty serviceBase.servicePreposition.objId}">
								<div class="unity-price">
				                    <div class="unity-price-box" id="fee_${serviceBase.objId}" >
					                    <div class="unity-arrow-down">       
					                        <p>限${serviceBase.servicePreposition.serviceName}购买</p>
					                    </div>
				                    	<span class="unity-btn-down" title="展开">&nbsp;</span>
					                    <!-- 根据服务ID获取各个级别最小时长的服务计费 -->
					                    <div class="unity-arrow-box"></div>
				            		</div>
							   	</div>
							   	<c:choose>
									<c:when test="${fn:contains(subscribedServiceIds,serviceBase.servicePreposition.objId)}">
										<div class="unity-btn">
										<div class="unity-btn-box">
										<span class="unity-btn-order">
										<a href="javascript:void(0);" onclick="ServiceSupplierForm.purchaseNow('${serviceBase.objId}',this);">立即订购</a>
										</span>
										</div>
										</div>
									</c:when>
									<c:otherwise>
										<div class="unity-btn">
											<div class="unity-btn-box">
												<span class="unity-btn-order show-tips">立即订购</span>
											</div>
										</div>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								<div class="unity-price"><strong><fmt:formatNumber value="${serviceBase.servicePrice}" pattern="####" />元/${serviceBase.serviceUnitCN}</strong></div>
								<div class="unity-btn">
									<div class="unity-btn-box">
										<span class="unity-btn-order">
											<a href="javascript:void(0);" onclick="ServiceSupplierForm.purchaseNow('${serviceBase.objId}',this);">立即订购</a>
										</span>
									</div>
								</div>
							</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
					</li>
				</c:if>
			</c:forEach>
			</ul>
		</div>

		<div id="generalService">
			<div class="title">常规服务</div>
			<ul class="unity-list">
				<c:set var="priceUnit" value="" scope="request"></c:set>
				<c:forEach var="serviceBase" items="${serviceBaseList}" varStatus="status2">
					<c:if test="${serviceBase.isRecommendation != '1'}">
						<li class="unity-line">
						<div class="unity-logo">
							<img alt="${serviceBase.serviceName}" src="<c:url value="AttachmentController.do?method=showImg&objId=${serviceBase.servicePic}" />"></div>
						<dl class="unity-info">
							<dt class="unity-tilte"><strong>${serviceBase.serviceName}</strong></dt>
							<dd class="unity-content">${serviceBase.serviceDesc}
							<a href="${serviceBase.serviceLink}" target="_blank" name="serviceDetail" title="了解详情">了解详情&gt;&gt;</a>
							</dd>
						</dl>
						<!--判断是是否已订阅-->
					<c:choose>
						<c:when test="${fn:contains(subscribedServiceIds,serviceBase.objId)}">
							<c:choose>
							<c:when test="${!empty serviceBase.servicePreposition && !empty serviceBase.servicePreposition.objId}">
								<div class="unity-price">
				                   <div class="unity-price-box">
					                    <div class="unity-arrow-down">       
					                        <p>限${serviceBase.servicePreposition.serviceName}购买</p>
					                    </div>
				                    	<span id="fee_${serviceBase.objId}" class="unity-btn-down" title="展开">&nbsp;</span>
					                    <!-- 根据服务ID获取各个级别最小时长的服务计费 -->
					                    <div class="unity-arrow-box" style="display: none;"></div>
				            		</div>
							   	</div>
							</c:when>
							<c:otherwise>
								<div class="unity-price"><strong><fmt:formatNumber value="${serviceBase.servicePrice}" pattern="####" />元/${serviceBase.serviceUnitCN}</strong></div>
							</c:otherwise>
							</c:choose>
							<div class="unity-btn">
								<div class="unity-btn-box">
									<span class="unity-btn-order show-tips">已订阅...</span>
								</div>
							</div>
						</c:when>
						<c:otherwise>
							<c:choose>
							<c:when test="${!empty serviceBase.servicePreposition && !empty serviceBase.servicePreposition.objId}">
								<div class="unity-price" style="height: 55px;">
				                    <div class="unity-price-box">
					                    <div class="unity-arrow-down">       
					                        <p>限${serviceBase.servicePreposition.serviceName}购买</p>
					                    </div>
				                    	<span id="fee_${serviceBase.objId}" class="unity-btn-down " title="展开">&nbsp;</span>
					                    <!-- 根据服务ID获取各个级别最小时长的服务计费 -->
					                    <div class="unity-arrow-box" style="display: none;"></div>
				            		</div>
							   	</div>
							   	<c:choose>
									<c:when test="${fn:contains(subscribedServiceIds,serviceBase.servicePreposition.objId)}">
										<div class="unity-btn">
										<div class="unity-btn-box">
										<span class="unity-btn-order">
										<a href="javascript:void(0);" onclick="ServiceSupplierForm.purchaseNow('${serviceBase.objId}',this);">立即订购</a>
										</span>
										</div>
										</div>
									</c:when>
									<c:otherwise>
										<div class="unity-btn">
											<div class="unity-btn-box">
												<span class="unity-btn-order show-tips">立即订购</span>
											</div>
										</div>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								<div class="unity-price"><strong><fmt:formatNumber value="${serviceBase.servicePrice}" pattern="####" />元/${serviceBase.serviceUnitCN}</strong></div>
								<div class="unity-btn">
									<div class="unity-btn-box">
										<span class="unity-btn-order">
											<a href="javascript:void(0);" onclick="ServiceSupplierForm.purchaseNow('${serviceBase.objId}',this);">立即订购</a>
										</span>
									</div>
								</div>
							</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
						</li>
					</c:if>
				</c:forEach>
			</ul>
		</div>
   </div>
   
   <div id="myService">
   </div>
</div>
</form>