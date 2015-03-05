<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div class="list-content">
	<ul class="list-view hlisting sell">
		<c:forEach var="hotel" items="${PAGERESULT.data}">
			<li class="list-item">
				<h3 class="summary"><a class="EventCanSelect" href="javascript:void(0);" onclick="show_list.showDetail('${hotel.objId}');return false;">${hotel.hotelName} </a>&nbsp;<span class="hotel_stars${hotel.star }">&nbsp;</span></h3>
		 		<div class="photo">
					<a href="javascript:void(0);" onclick="show_list.showDetail('${hotel.objId}');return false;"><span><img src="<c:url value="AttachmentController.do?method=showImg&objId=${hotel.picture }" />"></span></a>
				</div>
		 		<ul class="attribute nomargin">                        	
		            <li class="srvlist">
		            	<a href="javascript:void(0);" onclick="show_list.showDetail('${hotel.objId}','successCaseTab');return false;"><img src="view/resource/skin/sysicon/16/ruby.png">&nbsp;<fmt:formatNumber type="number" value="${hotel.evalSum }" pattern="#0"/> 颗星</a>
		            	<a href="javascript:void(0);" onclick="common.addFavorites('${hotel.objId}','${hotel.hotelName}','06');return false;"><img src="view/resource/skin/sysicon/16/award_star_add.png">&nbsp;收藏</a>
					</li>
		            <li class="place"> <fmt:formatDate value="${hotel.startTime}" pattern="yyyy年"/> 开业  </li>
		            <li class="place"> 房间数量(间)：${hotel.guestRoomCount}</li>
		        </ul>
		        <div class="extend">	
		        	<em>酒店位置：</em>${hotel.hotelAddress}
				</div>
		        <p class="seller lister hCard nowrap">
		        	<em>酒店服务：</em>${hotel.serviceItemsName}<br />
		        	<em>娱乐设施：</em>${hotel.funFacilitiesName}
		        </p>
		    </li>
		</c:forEach>
	</ul>
</div>
<div>
	<%@ include file="/view/pubservice/common/pageDirection.jsp" %>
</div>