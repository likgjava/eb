<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div class="list-content">
	<ul class="list-view hlisting sell">
		<c:forEach var="buyer" items="${PAGERESULT.data}">
			<li class="list-item">
				<h3 class="summary" style="margin-left:110px;"><a class="EventCanSelect" onclick="common.geToBuyerDetail('${buyer.objId}');" href="javascript:void(0);">${buyer.orgInfo.orgName}</a></h3>
		 		<div class="photo" style="width:100px;height:75px;">
					<a onclick="common.geToBuyerDetail('${buyer.objId}');" href="javascript:void(0);"><span><img style="width:100px;height:75px;" src="<c:url value="AttachmentController.do?method=showImg&objId=${buyer.orgInfo.logo }" />"></span></a>
				</div>
		 		<ul class="attribute nomargin">                        	
		            <li class="srvlist" style="width: 120px;">
		            	<a onclick="common.geToBuyerDetail('${buyer.objId}','evaluateTab');" href="javascript:void(0);" title="信用评分"><img src="view/resource/skin/sysicon/16/rosette.png">&nbsp;<fmt:formatNumber type="number" value="${buyer.evalSum }" pattern="#0.0"/>&nbsp;分</a>
		            	<a onclick="common.geToBuyerDetail('${buyer.objId}','successCaseTab');" href="javascript:void(0);" title="已采购金额"><img src="view/resource/skin/sysicon/16/coins.png">&nbsp;￥<fmt:formatNumber type="number" value="${buyer.dealTotal / 10000 }" pattern="#,##0.00"/>万元</a>
		            	<a href="javascript:void(0);" onclick="common.addFavorites('${buyer.objId}','${buyer.orgInfo.orgName}','04');return false;"><img src="view/resource/skin/sysicon/16/award_star_add.png">&nbsp;收藏</a>
					</li>
		            <li class="place"> <fmt:formatDate value="${buyer.orgInfo.validTime}" pattern="yyyy.MM.dd"/>  </li>
		            <li class="place"> ${buyer.orgInfo.distinctName}  </li>
		        </ul>
		        <div class="extend">
				</div>
		        <p class="seller lister hCard" style="left:110px;">企业简介：
		        	<c:choose>
		        		<c:when test="${fn:length(buyer.unitIntroduction) > 50 }">${fn:substring(buyer.unitIntroduction,0,50) }<b>...</b></c:when>
		        		<c:otherwise>${buyer.unitIntroduction}</c:otherwise>
		        	</c:choose>
		        </p>
		    </li>
		</c:forEach>
	</ul>
</div>
<div>
	<%@ include file="/view/pubservice/common/pageDirection.jsp" %>
</div>