<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div class="list-content">
	<ul class="list-view hlisting sell">
		<c:forEach var="supplier" items="${PAGERESULT.data}">
			<li class="list-item">
				<h3 class="summary" style="margin-left:110px;"><a class="EventCanSelect" href="javascript:void(0);" onclick="common.goToOrgShop('${supplier.orgInfo.objId}');return false;">${supplier.orgInfo.orgName}</a></h3>
		 		<div class="photo" style="width:100px;height:75px;">
					<a href="javascript:void(0);" onclick="common.goToOrgShop('${supplier.orgInfo.objId}');return false;"><span><img style="width:100px;height:75px;" src="<c:url value="AttachmentController.do?method=showImg&objId=${supplier.orgInfo.logo }" />"></span></a>
				</div>
		 		<ul class="attribute nomargin">                        	
		            <li class="srvlist">
		            	<a href="javascript:void(0);" onclick="common.goToOrgShop('${supplier.orgInfo.objId}');return false;"><img src="view/resource/skin/sysicon/16/star.png">&nbsp;<fmt:formatNumber type="number" value="${supplier.evalSum }" pattern="#0.0"/>&nbsp;分</a>
		            	<a href="javascript:void(0);" onclick="common.goToOrgShop('${supplier.orgInfo.objId}','CGAL');return false;"><img src="view/resource/skin/sysicon/16/ruby.png">&nbsp;成功案例</a>
		            	<a href="javascript:void(0);" onclick="common.addFavorites('${supplier.orgInfo.objId}','${supplier.orgInfo.orgName}','02');return false;"><img src="view/resource/skin/sysicon/16/award_star_add.png">&nbsp;收藏</a>
					</li>
		            <li class="place"> <fmt:formatDate value="${supplier.orgInfo.createTime}" pattern="yyyy.MM.dd"/>  </li>
		            <li class="place"> ${supplier.orgInfo.distinctName}  </li>
		        </ul>
		        <div class="extend">
				</div>
		        <p class="seller lister hCard" style="left:110px;">主营产品：
		        	<c:choose>
		        		<c:when test="${fn:length(supplier.orgInfo.bidForRangeName) > 50 }">${fn:substring(supplier.orgInfo.bidForRangeName,0,49) }<b>...</b></c:when>
		        		<c:otherwise>${supplier.orgInfo.bidForRangeName}</c:otherwise>
		        	</c:choose>
		        </p>
		    </li>
		</c:forEach>
	</ul>
</div>
<div>
	<%@ include file="/view/pubservice/common/pageDirection.jsp" %>
</div>