<%@ page contentType="text/html;charset=UTF-8" %><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div class="list-content">
	<ul class="list-view hlisting sell">
		<c:forEach var="expert" items="${PAGERESULT.data}">
			<li class="list-item" style="height:105px;">
				<h3 class="summary">
					<a class="EventCanSelect" onclick="common.goToExpertDetail('${expert.objId}');" href="javascript:void(0);">${expert.name}</a>&nbsp;&nbsp;
					<c:if test="${expert.isConsultant=='1'}">
						<a href="javascript:void(0);" title="咨询专家"><img src="view/resource/skin/smallscale/img/expert-zx.png"></a>
					</c:if>
					<c:if test="${expert.isReviewers=='1'}">
						<a href="javascript:void(0);" title="评标专家"><img src="view/resource/skin/smallscale/img/expert-ps.png"></a>
					</c:if>
		        </h3>
		 		<div class="photo" style="height:105px;">
					<a onclick="common.goToExpertDetail('${expert.objId}');" href="javascript:void(0);"><span><img style="height:104px;width:80px;" src="<c:url value="AttachmentController.do?method=showImg&objId=${expert.photo }" />"></span></a>
				</div>
		 		<ul class="attribute nomargin">                        	
		            <li class="srvlist">
		            	<a href="javascript:void(0);" title="从事特长年限"><img src="view/resource/skin/sysicon/16/star.png">&nbsp;<fmt:formatNumber type="number" value="${expert.specifyYear }" pattern="#0"/>&nbsp;年</a>
		            	<a href="javascript:void(0);" title="政治面貌"><img src="view/resource/skin/sysicon/16/ruby.png">&nbsp;${expert.politicalLandscapeCN}</a>
		            	<a href="javascript:void(0);" onclick="common.addFavorites('${expert.objId}','${expert.name}','05');return false;"><img src="view/resource/skin/sysicon/16/award_star_add.png">&nbsp;收藏</a>
					</li>
		            <li class="place"> ${expert.professionQualificationLevelCN}  </li>
		            <li class="place"> ${expert.district.name}  </li>
		        </ul>
		        <div class="extend"></div>
		        <p class="seller lister hCard">特长描述：<c:choose><c:when test="${fn:length(expert.technicalExcellence) > 51}">${fn:substring(expert.technicalExcellence,0,50) }<b>...</b></c:when><c:otherwise>${expert.technicalExcellence}</c:otherwise></c:choose></p>
		    </li>
		</c:forEach>
	</ul>
</div>
<div><%@ include file="/view/pubservice/common/pageDirection.jsp" %></div>