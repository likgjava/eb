<%@ page contentType="text/html;charset=UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/pubservice/css/goods_l.css"/>

<c:choose>
		<c:when test="${!empty mySubscribeList && fn:length(mySubscribeList) > 0}">
			<div class="list-content">
			<ul class="list-view hlisting sell">
				<c:forEach var="mySubscribe" items="${mySubscribeList}" varStatus="status">
					<li class="list-item">
						<h3 class="summary">${mySubscribe.serviceBase.serviceName}</h3>
						<div class="photo">
							<a href="javascript:void(0);" onclick="show_list.showDetail('','evaluateListTab');return false;">
							<img src="<c:url value="AttachmentController.do?method=showImg&objId=${mySubscribe.serviceBase.servicePic}" />">
							</a>
						</div> 
				 		<ul class="attribute">                        	
				            <li class="legend2">
				            	<span>订阅期限：<strong>${mySubscribe.duration}</strong> ${mySubscribe.serviceBase.serviceUnitCN}</span>
				            	<span>
				            		<c:if test="${mySubscribe.auditStatus != '02'}">
				            			<button type="button" class="largeBtn" onclick="javascript:ServiceSupplierForm.cancelSubscribe('${mySubscribe.objId}');" id="cancelSubscribe"><span>取消订阅</span></button>
				            		</c:if>
				            		
				            		<!-- 未支付，显示支付按钮 -->
				            		<c:if test="${mySubscribe.payStatus != '01' && mySubscribe.payAmount>0}">
				            			<button type="button" class="largeBtn" onclick="javascript:toServicePay('${mySubscribe.objId}','${mySubscribe.payAmount}');" id="cancelSubscribe"><span>支付</span></button>
				            		</c:if>
				            	</span>
				           	</li>
				            <li class="srvlist" style="width:60px;">
				            	<em><c:if test="${mySubscribe.auditStatus=='02'}">使用中</c:if><c:if test="${mySubscribe.auditStatus=='01'}">审核中</c:if><c:if test="${mySubscribe.auditStatus=='03'}">审核未通过</c:if><c:if test="${empty mySubscribe.auditStatus}">未知</c:if></em>
							</li>
				            <li class="price" style="width:120px;"> 缴费金额(元)<em> <fmt:formatNumber value="${mySubscribe.payAmount}" pattern="#,##0.00#" /></em></li>
				        </ul>
				        <div class="extend">
						</div>
				        <p class="seller lister hCard" style="width:240px;top:30px;color:#666666" title="${mySubscribe.serviceBase.serviceDesc}">服务描述：
				        	<c:choose><c:when test="${fn:length(mySubscribe.serviceBase.serviceDesc) == 0}">暂无描述</c:when><c:when test="${fn:length(mySubscribe.serviceBase.serviceDesc) < 45}">${goods.spec}</c:when><c:otherwise>${fn:substring(mySubscribe.serviceBase.serviceDesc,0,44)}...</c:otherwise></c:choose>
			        		<c:if test="${mySubscribe.serviceBase.serviceLink != null}">
			        			<a href="${mySubscribe.serviceBase.serviceLink}" target="_blank" name="serviceDetail" title="了解详情">了解详情&gt;&gt;</a>
			        		</c:if>
				        </p>
				    </li>
				</c:forEach>
			</ul>
			</div>
		</c:when>
		<c:otherwise>
			<div class="no-data">
			    <p class="nodata-tit">您目前没有已订购的服务</p>
			    <p>快去订购服务列表选择服务吧。</p>
			    <p class="mt10">
			    	<span class="btn-unity mr20"><a href="javascript:void(0);" id="subscribeService">选择订购服务</a></span>
			    </p>
			</div>
		</c:otherwise>
</c:choose>

<script>
//转向支付页面
function toServicePay(v_business_id,v_amount) {
	window.open($('#initPath').val()+'/ServiceSubscribeController.do?method=toServicePayView&v_business_id='+v_business_id+'&v_amount='+v_amount);
}
$(document).ready(function(){
	//选择订购服务
	$('#subscribeService').click(function(){
		$('#conBody').loadPage($('#initPath').val()+'/ServiceBaseController.do?method=toServiceSubscribeList');
	})
});
</script>