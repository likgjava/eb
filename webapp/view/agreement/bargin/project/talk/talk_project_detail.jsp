<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>【${project.projName}】议价项目-阳光易购采购交易平台</title>
<c:import url="/view/srplatform/common/init.jsp">
	<c:param name="isLastLoadPage">
		true
	</c:param>
</c:import>

<!--CSS-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/skin07/css/plugInEdit.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/skin01/css/talkProjectDetail.css"/>

<!--JS-->
<script type="text/javascript" src='<%=request.getContextPath()%>/view/srplatform/portal/include/common.js'></script>
<script type="text/javascript" src='<%=request.getContextPath()%>/view/agreement/bargin/project/talk/talk_project_detail.js'></script>
</head>
<body>
<!--页面容器 开始-->
<div id="container"> 

	<!-- 项目Id -->
	<input name="projectId" id="projectId" type="hidden" value="${project.objId}" />
	<!-- 用户类型 -->
	<input name="userType" id="userType" type="hidden" value="${userType}" />

  <!--头部容器 开始-->
  <div class="header"> 
  		<input type="hidden" id="isOutCss" value="${param.isOutCss}"/>
  		<c:choose>
	  		<c:when test="${!isOutCss}"><jsp:include page="/view/srplatform/portal/include/backgroundmenu.jsp"></jsp:include></c:when>
  			<c:otherwise><%@ include file="/view/srplatform/portal/include/main_header_index.jsp"%></c:otherwise>
  		</c:choose>
  </div>
  <!--头部容器 结束--> 
	<!--主要内容容器 开始-->
	<div class="page" style="min-height:600px!important;">
		<div class="gridBox">
			<!-- 三级菜单 -->
			<div class="grid16_3 hidden" id="menuList">
				<div class="menuList"><ul></ul></div>
			</div>
			
			<!-- conBody开始 内容页面加载位置 -->
			<div class="grid16_16" id="conBody">
			
			<div id="base_bd" class="base_t3">
				<!-- 项目信息 开始 -->
				<h2 class="package_headline layoutfix ">
					[${project.projCode}]<span id="projName">${project.projName}</span>
					<p class="package_fontcolor">报价时间：<fmt:formatDate value="${project.evalStartTime }" pattern="yyyy-MM-dd HH:mm:ss"/>&nbsp;至&nbsp;<fmt:formatDate value="${project.evalEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
					<p class="package_fontcolor">采购人：<a href="javascript:void(0);" onclick="TalkProjectDetail.showBuyerDetail('${createBuyerId}');return false;">${createBuyerName}</a></p>
					<p class="package_fontcolor">联系人：${project.monitor.name}&nbsp;&nbsp;${project.monitor.email}</p>
				</h2>
				<!-- 项目信息  结束 -->
			
				<!-- 项目进度 开始[0:未开始；1:进行中；2:已结束] -->
				<div class="proce_status">
					<span class="<c:choose><c:when test="${projStatus[0]==0}">unfinishedFirst</c:when>
					<c:when test="${projStatus[0]==1}">currentFirst</c:when>
					<c:otherwise>finishedFirst</c:otherwise></c:choose>">邀请供应商</span>
					
					<span class="<c:choose><c:when test="${projStatus[1]==0}">unfinished</c:when>
					<c:when test="${projStatus[1]==1}">current</c:when>
					<c:otherwise>finished</c:otherwise></c:choose>">供应商报价</span>
					
					<span class="<c:choose><c:when test="${projStatus[2]==0}">unfinished</c:when>
					<c:when test="${projStatus[2]==1}">current</c:when>
					<c:otherwise>finished</c:otherwise></c:choose>">确定结果</span>
					
					<span class="<c:choose><c:when test="${projStatus[3]==0}">unfinishedLast</c:when>
					<c:otherwise>finishedLast</c:otherwise></c:choose>">项目结束</span>
				</div>
				<!-- 项目进度 结束 -->
				
				<!-- 操作按钮 开始 -->
				<div class="operationBtn">
					<c:if test="${(userType == 'buyer')}">
						<c:if test="${project.evalStartTime==null || project.evalEndTime==null || project.useStatus!='01'}">
							<button type="button" class="base_btns7" onclick="TalkProjectDetail.operator('update')" ><span>修改项目</span></button>
							<button type="button" class="base_btns7" onclick="TalkProjectDetail.operator('submit')" ><span>提交</span></button>
						</c:if>
						<c:if test="${project.projProcessStatus=='170' && evalEndTime < cureentDate}">
							<button type="button" class="base_btns7" onclick="TalkProjectDetail.operator('confirm')" ><span>确定结果</span></button>
						</c:if>
					</c:if>
					
					<c:if test="${(userType=='buyer') || (userType=='supplier')}">
						<c:if test="${project.projProcessStatus=='160' && evalStartTime < cureentDate && evalEndTime > cureentDate }">
							<button type="button" class="base_btns7" onclick="TalkProjectDetail.operator('intoHall')" ><span>议价厅</span></button>
						</c:if>
						<c:if test="${(project.projProcessStatus=='170' && evalEndTime < cureentDate) || project.projProcessStatus=='200'}">
							<button type="button" class="base_btns7" onclick="TalkProjectDetail.operator('evaluate')" ><span>评价</span></button>
							<button type="button" class="base_btns7" onclick="TalkProjectDetail.operator('complaints')" ><span>投诉</span></button>
							<button type="button" class="base_btns7" onclick="TalkProjectDetail.operator('report')" ><span>举报</span></button>
						</c:if>
					</c:if>
					
					<c:if test="${project.projProcessStatus=='200'}">
						<button type="button" class="base_btns7" onclick="TalkProjectDetail.operator('viewResult')" ><span>查看结果</span></button>
					</c:if>
				</div>
				<!-- 操作按钮 结束 -->
				
				<!-- 需求条目 开始 -->
				<div class="package_details_intro layoutfix">
					<div class="b_bd layoutfix">
						<div class="pripackage_date_main">
							<h5>需求条目</h5>
							<table id="requireItemListT" class="pripackage_date_list">
							<thead>
								<tr>
							  		<th>需求商品</th>
									<th>需求数量</th>
									<th>计量单位</th>
									<th>期望价格(元)</th>
									<th>总额(元)</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:set var="totalPrice" value="0" />
								<c:forEach var="requireItem" items="${requireItemList}">
								<tr name="goods_${requireItem.goods.objId}">
									<td>
										<a href="javascript:void(0);" onclick="TalkProjectDetail.showGoodsDetail('${requireItem.goods.objId}');">${requireItem.goods.productName }</a>
										<!-- 礼包 -->
										<c:if test="${!empty requireItem.requireGoodsGifts && fn:length(requireItem.requireGoodsGifts) > 0}">
											<a href="javascript:void(0);" title="商品礼包详情" onclick="TalkProjectDetail.showOrHideGoodsGift(this);return false;"><img src="view/resource/skin/sysicon/16/goods_gift.png" /></a>
										</c:if>
									</td>
									<td align="right">${requireItem.goodsQty }</td>
									<td align="center">${requireItem.goodsUnit }</td>
									<td align="right"><fmt:formatNumber value="${requireItem.goodsPrice }" pattern="#,##0.00#" /></td>
									<td align="right"><fmt:formatNumber value="${requireItem.goodsTotal }" pattern="#,##0.00#" /></td>
									<td align="right"><a href="javascript:void(0);" onclick="TalkProjectDetail.highShow('${requireItem.goods.objId}');">报价详情</a></td>
									<c:set var="totalPrice" value="${totalPrice + requireItem.goodsTotal}" />
								</tr>
								
								<!-- 礼包显示  开始 -->
								<c:if test="${!empty requireItem.requireGoodsGifts && fn:length(requireItem.requireGoodsGifts) > 0}">
								<tr class="hidden">
									<td colspan="6">
										<div>
											<table>
												<tr>
													<c:forEach items="${requireItem.requireGoodsGifts}" var="requireGoodsGift">
													<td>
														<div class="goodsGift">
															<img class="goodsImg" src='<c:url value="AttachmentController.do?method=showImg&objId=${requireGoodsGift.goodsGift.giftPicture}" />' />
										 					<div class="fitting" style="float:left">
										 						${requireGoodsGift.goodsGift.giftName}<br/>
										 						￥<fmt:formatNumber value="${requireGoodsGift.giftPrice}" pattern="#,##0.00#"/><br/>
										 					</div>
									   					</div>
													</td>
													</c:forEach>
												</tr>
											</table>
										</div>
									</td>
								</tr>
								</c:if>
								<!-- 礼包显示  结束 -->
								</c:forEach>
								<tr>
									<td colspan="6" style="text-align: right; padding-right: 10px;">总计：<fmt:formatNumber value="${totalPrice}" pattern="#,##0.00#" />元</td>
								</tr>
							</tbody>
							</table>
						</div>	
					</div>
				</div>
				<!-- 需求条目 结束 -->
				
				<!-- 最低报价和聊天信息  开始-->
				<div class="package_details_intro layoutfix">
					<c:forEach var="signUprecord" items="${signUprecordList}" varStatus="status">
					<div class="b_bd layoutfix">
						<div class="pripackage_date_main">
							<h5>
								<a href="javascript:void(0);" onclick="common.goToOrgShop('${signUprecord.supplier.objId}');">${signUprecord.supplierName}</a>
								<span class="alignR"><a onclick="TalkProjectDetail.loadBiddingAndChat('${signUprecord.supplier.objId}');" href="javascript:void(0);">展开/折叠</a></span>
							</h5>
							<!-- 注意：此div中不能出现任何字符，包括空格和换行 -->
							<div id="biddingAndChat_${signUprecord.supplier.objId}"><c:if test="${status.index == 0}"><%@ include file="/view/agreement/bargin/project/talk/talk_bidding_and_chat.jsp" %></c:if></div>
						</div>
					</div>
					</c:forEach>
				</div>
				<!-- 最低报价和聊天信息  结束 -->
			</div>
			
			</div>
			<!-- conBody结束 内容页面加载位置-->
    	</div>
	</div>
	<!--主要内容容器 结束--> 
  
  <!--底部容器 开始-->
  <div class="footer">
    <%@ include file="/view/srplatform/portal/include/foot.jsp" %>
  </div>
  <!--底部容器 结束-->
  <!--在线客服开始-->
  <%@ include file="/view/srplatform/portal/include/online_customer_service.jsp" %>
  <!--在线客服结束-->
</div>
</body>
</html>