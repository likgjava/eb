<%@ page contentType="text/html;charset=UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="cols">
	<h2><span class="titleUp">采购单位</span></h2>
	<div class="hotAreaNews">
		<c:set var="buyFirst" value="${newBuyerList[0]}"></c:set>
		<h3><a onclick="common.geToBuyerDetail('${buyFirst.objId}');" href="javascript:void(0);">${buyFirst.orgInfo.orgName}</a></h3>
		<p class="hotImg"><img style="width: 60px; height: 60px;" src="AttachmentController.do?method=showImg&objId=${buyFirst.orgInfo.logo}" /></p>
		<p class="hotDetails"><c:choose><c:when test="${fn:length(buyFirst.orgInfo.descCn)> 60}">${fn:substring(buyFirst.orgInfo.descCn,0,59) }…</c:when><c:otherwise>${buyFirst.orgInfo.descCn }</c:otherwise></c:choose></p>
	</div>
	<ul class="newsList">
		<c:forEach var="buyer" items="${newBuyerList}" begin="1">
		<li>
			<span>[${buyer.orgInfo.belongIndustry!=null ? buyer.orgInfo.belongIndustry.name : '其他行业'}]</span>
			<a onclick="common.geToBuyerDetail('${buyer.objId}');" href="javascript:void(0);">${buyer.orgInfo.orgName}</a>
			<span class="date" title="已采购金额">￥<fmt:formatNumber value="${buyer.dealTotal * 0.0001}" pattern="#,##0.00" /> 万元</span>
		</li>
		</c:forEach>
	</ul>
	<div class="more"><a target="_blank" href="<%=request.getContextPath()%>/BuyerShowController.do?method=toBuyerList&rp=21&page=1&districtLevel=1" title="更多">更多</a></div>
</div>