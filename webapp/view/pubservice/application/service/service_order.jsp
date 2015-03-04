<%@ page contentType="text/html;charset=UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<link href="view/resource/skin/pubservice/css/services.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/view/pubservice/application/service/service_order.js"></script>

<form id="ServiceSubscribeForm" method="post">
<input type="hidden" name="serviceBaseId" id="serviceBaseId" value="${serviceBase.objId}"/>
<input type="hidden" name="servicePrice" id="servicePrice" value="${serviceBase.servicePrice}"/>

<div>
<div class="title_v1">
    <h3 class="titie_txt">订购产品</h3>
    <s class="c_left"></s>
    <s class="c_right"></s>
</div>

<div class="content_box">
    <div class="box_1" id="yui-gen0">
        <h4 class="title_1">
            <div class="img_box">
                <img width="66px" alt="${serviceBase.serviceName}" src="<c:url value="AttachmentController.do?method=showImg&objId=${serviceBase.servicePic}" />">
            </div>
            <span><c:choose><c:when test="${fn:length(serviceBase.serviceName) > 15}">${fn:substring(serviceBase.serviceName,0,15)}...</c:when><c:otherwise>${serviceBase.serviceName}</c:otherwise></c:choose></span>
        </h4>
        <div class="inline_box">
		<c:choose><c:when test="${fn:length(serviceBase.serviceDesc) > 200}">${fn:substring(serviceBase.serviceDesc,0,200)}...</c:when><c:otherwise>${serviceBase.serviceDesc}</c:otherwise></c:choose>
		<!--暂时屏蔽<a href="${serviceBase.serviceLink}" target="_blank" name="serviceDetail" title="了解详情">了解详情&gt;&gt;</a>-->
        </div>
         <div style="clear:both;"></div>
    </div>
    
    <div class="box_2">
        <h4 class="title_1">产品内容</h4>
        <div class="inline_box p1">
            <p>${serviceBase.serviceName}</p>
        </div>
         <div style="clear:both;"></div>
    </div>
    <div class="box_3">
        <h4 class="title_1">订购时长</h4>
		<c:if test="${!empty serviceChargingList && fn:length(serviceChargingList) > 0}">
			<c:forEach var="serviceCharging" items="${serviceChargingList}" varStatus="status">
			<p class="p1">
				<input type="radio" name="timeLg" <c:if test="${status.index==0}">checked="checked"</c:if> value="${serviceCharging.duration}">
				<label name="lab_duration" timeLang="${serviceCharging.duration}">
				${serviceCharging.duration}${serviceCharging.serviceBase.serviceUnitCN}</label>
				<span class="strong_num">
					<fmt:formatNumber value="${serviceCharging.amount}" pattern="####" />
				</span>元
			</p>
			</c:forEach>
		</c:if>
		<div style="clear:both;"></div>
    </div>
    
    <c:if test="${!empty serviceGroupList && fn:length(serviceGroupList) > 0}">
    	<div class="box_5">
			<h4 class="title_1">搭配购买</h4>
	        <div class="img1 inline_box">
		        <img width="60px" alt="${serviceBase.serviceName}" src="<c:url value="AttachmentController.do?method=showImg&objId=${serviceBase.servicePic}" />">
		        <span>${serviceBase.serviceName}</span>
	        </div>
	        <div class="img2 inline_box"></div>
	        
	        <c:forEach var="serviceGroup" items="${serviceGroupList}" varStatus="status">
	        	<c:set var="sunit" value="" scope="request"></c:set>
	        	<c:if test="${serviceGroup.appendService.serviceUnit=='01'}"><c:set var="sunit" value="年" scope="request"></c:set></c:if>
	        	<c:if test="${serviceGroup.appendService.serviceUnit=='02'}"><c:set var="sunit" value="月" scope="request"></c:set></c:if>
	        	<c:if test="${serviceGroup.appendService.serviceUnit=='03'}"><c:set var="sunit" value="期" scope="request"></c:set></c:if>
		        <div class="img3 inline_box">
			        <div class="selectsell_box">
			            <div class="ico"><img width="60px" alt="${serviceGroup.appendService.serviceName}" src="<c:url value="AttachmentController.do?method=showImg&objId=${serviceGroup.appendService.servicePic}" />"></div>
			            <p class="name">
			            <input id="${serviceGroup.appendService.objId}" name="${serviceGroup.appendService.serviceName}" 
			            	duration="${serviceGroup.duration}" amount="${serviceGroup.amount}" 
			            	oldprice="${serviceGroup.appendService.servicePrice*serviceGroup.duration}"
			            	discount="${serviceGroup.appendService.servicePrice*serviceGroup.duration-serviceGroup.amount}"
			            	sunit="${sunit}"
			            	type="checkbox" class="repair_checkbox">&nbsp;${serviceGroup.appendService.serviceName}</p>
			            
			            <c:if test="${serviceGroup.appendService.serviceUnit=='01'}">
			            	<p class="p1">${serviceGroup.duration}年
								<!--搭配服务金额-->
				            	<span class="strong_num">${serviceGroup.amount}</span>元
				            </p>
				            <p class="p2">省了
				            <span class="normal_num">
				            	<!--服务单价乘以单位  减去 搭配服务金额-->
				            	<c:set var="saveAmount" value="${serviceGroup.appendService.servicePrice * serviceGroup.duration - serviceGroup.amount}" scope="request"></c:set>
				            	<c:if test="${saveAmount < 0}"><c:set var="saveAmount" value="0.0" scope="request"></c:set></c:if>
				            	<fmt:formatNumber value="${saveAmount}" pattern="####" />
				            </span>元</p>
			            </c:if>
		            	<c:if test="${serviceGroup.appendService.serviceUnit=='02'}">
		            		<p class="p1">${serviceGroup.duration}个月
								<!--搭配服务金额-->
				            	<span class="strong_num">${serviceGroup.amount}</span>元
				            </p>
				            <p class="p2">省了
				            <span class="normal_num">
				            	<!--服务单价乘以单位  减去 搭配服务金额-->
				            	<c:set var="saveAmount" value="${serviceGroup.appendService.servicePrice * serviceGroup.duration - serviceGroup.amount}" scope="request"></c:set>
				            	<c:if test="${saveAmount < 0}"><c:set var="saveAmount" value="0.0" scope="request"></c:set></c:if>
				            	<fmt:formatNumber value="${saveAmount}" pattern="####" />
				            </span>元</p>
		            	</c:if>
		            	<c:if test="${serviceGroup.appendService.serviceUnit=='03'}">
		            		<p class="p1">${serviceGroup.duration}期
								<!--搭配服务金额-->
				            	<span class="strong_num">${serviceGroup.amount}</span>元
				            </p>
				            <p class="p2">省了
				            <span class="normal_num">
				            	<!--服务单价乘以单位  减去 搭配服务金额-->
				            	<c:set var="saveAmount" value="${serviceGroup.appendService.servicePrice * serviceGroup.duration - serviceGroup.amount}" scope="request"></c:set>
				            	<c:if test="${saveAmount < 0}"><c:set var="saveAmount" value="0.0" scope="request"></c:set></c:if>
				            	<fmt:formatNumber value="${saveAmount}" pattern="####" />
				            </span>元</p>
		            	</c:if>
			        </div>
				</div>
			</c:forEach>
    	</div>
    </c:if>
</div>

<div class="box_6" id="data_view">
	<table class="frontTableList" id="mySubscribeTable">
		<thead>
			<tr>
				<th>服务名称</th>
				<th class="center">订购时长</th>
				<th class="money">原价(元)</th>
				<th class="money">优惠(元)</th>
				<th class="money">现价(元)</th>
			</tr>
		</thead>
		<tbody>
			<tr id="${serviceBase.objId}" duration="" payAmount="" name="firsttr">
				<td>${serviceBase.serviceName}</td>
				<td class="center" id="serviceTimeL"></td>
				<td class="money" id="oldPrice">0</td>
				<td class="money" id="discountPrice">0</td>
				<td class="money" id="nowPrice">0.0</td>
			</tr>
		</tbody>
		<tfoot class="table_count">
			<tr>
				<td colspan="5">
					<ul>
							<li>您共节省：<span id="discF" class="normal_num">0.00元</span></li>
							<li>费用总计：<span id="totalF" class="strong_num">3,688.00元</span></li>
					</ul>
				</td>
			</tr>
		</tfoot>
	</table>
</div>

<div class="box_7">
    <p><span class="cb_ico2" id="alowed">&nbsp;</span>我已经阅读并同意服务协议
    <span id="agreement"><a href="javascript:void(0);" onclick="$.epsDialog({id:'',title:'服务协议',url:$('#initPath').val()+'/cms/resbase/html/service_order_agreement.html' });">服务协议</a></span>
    </p>
</div>

<div class="box_8">
    <p></p>
    <div class="submit_btn" id="submitBtn">确认订购</div>
    <span class="tips" id="submitTips">请先同意服务协议</span>
</div>
</div>
</form>