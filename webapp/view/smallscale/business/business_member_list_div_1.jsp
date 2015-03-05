<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="list-content">
	<ul class="list-view hlisting sell">
		<c:forEach var="businessMember" items="${PAGERESULT.data}">
			<li class="list-item">
				<h3 class="summary"><a class="EventCanSelect" href="javascript:void(0);" onclick="show_list.showDetail('${businessMember.orgInfo.objId}','${businessMember.orgInfo.buyerId}','${businessMember.orgInfo.supplierId}');return false;">
					<c:choose>
						<c:when test="${fn:length(businessMember.orgInfo.orgName) > 13}">${fn:substring(businessMember.orgInfo.orgName,0,13)}... </c:when>
						<c:otherwise>${businessMember.orgInfo.orgName}</c:otherwise>
					</c:choose>
				</a></h3>
		 		<div class="photo">
					<a href="javascript:void(0);" onclick="show_list.showDetail('${businessMember.orgInfo.objId}','${businessMember.orgInfo.buyerId}','${businessMember.orgInfo.supplierId}');return false;"><span><img src="<c:url value="AttachmentController.do?method=showImg&objId=${businessMember.orgInfo.logo }" />"></span></a>
				</div>
		 		<ul class="attribute nomargin">                        	
		            <li class="place"> <fmt:formatDate value="${businessMember.createTime}" pattern="yyyy.MM.dd"/>  </li>
		            <li class="place"> ${businessMember.orgInfo.distinctName}  </li>
		            <li class="place"> ${businessMember.orgInfo.belongIndustry.name}  </li>
		        </ul>
		        <div class="extend"></div>
		        <p class="seller lister hCard">主营产品：
		        	<c:choose>
		        		<c:when test="${fn:length(businessMember.orgInfo.bidForRangeName) > 50 }">${fn:substring(businessMember.orgInfo.bidForRangeName,0,50) }<b>...</b></c:when>
		        		<c:otherwise>${businessMember.orgInfo.bidForRangeName}</c:otherwise>
		        	</c:choose>
		        </p>
		    </li>
		</c:forEach>
	</ul>
</div>
<div>
	<%@ include file="/view/pubservice/common/pageDirection.jsp" %>
</div>