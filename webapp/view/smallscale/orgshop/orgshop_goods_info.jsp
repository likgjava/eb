<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<input type="hidden" id="goodsClassCode" name="goodsClassCode" value="${param.goodsClassCode}"/>
<input type="hidden" id="currentViewName" value="orgshopGoodsInfoView"/>
<!--产品信息-->
<div class="introduction">
	<div class="titleright">
		<h2 class="marginright">产品橱窗</h2>
	</div>
	<div class="conrig">
		<c:if var="hasGoods" test="${!empty PAGERESULT.data && fn:length(PAGERESULT.data) > 0}">
			<c:forEach var="goods" items="${PAGERESULT.data}">
			<div class="figure"> 
				<div class="photo"><img class="goodsImg" src="<%=request.getContextPath()%>/AttachmentController.do?method=showImg&objId=${goods[1]}" height="95" width="142" /></div>
				<p class="name"><a title="${goods[2]}" href="javascript:void(0);" onclick="OrgShopIndex.showGoodsDetail('${goods[0]}');">${goods[2]}</a></p>
			</div>
			</c:forEach>
		</c:if>
		<div style="padding-left: 8px;">
			<%@ include file="/view/pubservice/common/pageDirection.jsp" %>
		</div>
	</div>
</div>

