<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="cols sameHeigh">
	<h2>重点招标单位</h2>
	<div class="ehotAreaNews">
		<c:set var="buyerFirst" value="${buyerList[0]}"></c:set>
		<h3><a onclick="common.geToBuyerDetail('${buyerFirst.objId}');" href="javascript:void(0);">${buyerFirst.orgInfo.orgName}</a></h3>
		<p class="hotImg"><img style="width: 90px; height: 90px;" src="AttachmentController.do?method=showImg&objId=${buyerFirst.orgInfo.logo}" /></p>
		<div class="hotDetails">
			<p>发标次数：${bulletinNumList[0]}</p>
			<p>评价总分：<fmt:formatNumber type="number" value="${buyerFirst.evalSum }" pattern="#0.0"/>分</p>
			<p><a href="javascript:void(0);" onclick="common.addFavorites('${buyerFirst.objId }','${buyerFirst.orgInfo.orgName }','04')" class="collection">收藏</a></p>
		</div>
	</div>
	<div class="infoList ">
		<ul>
			<c:forEach var="buyer" items="${buyerList}" begin="1" varStatus="status">
			<li>
				<h5><a target="_blank" href="<%=request.getContextPath()%>/BuyerShowController.do?method=getBuyerInfo&objId=${buyer.objId}">${buyer.orgInfo.orgName}</a></h5>
				<ul>
					<li>发标：<em>${bulletinNumList[status.index]}</em></li>
					<li>评分：<em><fmt:formatNumber type="number" value="${buyer.evalSum }" pattern="#0.0" />分</em></li>
				</ul>
				<div class="btnBoxR"><a href="javascript:void(0);" class="collection" onclick="common.addFavorites('${buyer.objId }','${buyer.orgInfo.orgName }','04')">收藏</a></div>
			</li>
			</c:forEach>
		</ul>
	</div>
	<div class="more"><a target="_blank" href="<%=request.getContextPath()%>/BuyerShowController.do?method=toBuyerList&rp=21&page=1&districtLevel=1">更多</a></div>
</div>