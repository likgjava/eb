<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript">
var show_list1 = {};
//显示酒店详情
show_list1.showDetail = function(objId) {
	var targetUrl = $('#initPath').val()+"/HotelShowController.do?method=getHotelInfo::objId=" + objId;
	var contentSuppUrl = $('#initPath').val()+'/HotelShowController.do?method=getRecommendHotel::rp=10::page=1::';
	if(!objId){
		targetUrl = $('#initPath').val()+"/HotelShowController.do?method=toHotelList::rp=20::page=1";
		window.open( $('#initPath').val()+"/IndexViewController.do?method=index&sysContentUrl="+targetUrl);
	}else{
		window.open( $('#initPath').val()+"/IndexViewController.do?method=index&contentMainUrl="+targetUrl+"&contentSuppUrl="+contentSuppUrl );
	}
}
</script>

<c:if test="${fn:length(recommendHotelList)!= 0}">
<div class="workNews hotProduct">
	<h4><span class="jiantou">推荐酒店</span></h4>
	<ul class="center">
		<c:forEach var="recommendHotel" items="${recommendHotelList}">
		<li>
			<div class="orgInfoPic">
				<a href="javascript:void(0);" onclick="show_list1.showDetail('${recommendHotel.hotel.objId}');return false;">
				<img src="<c:url value="AttachmentController.do?method=showImg&objId=${recommendHotel.hotel.picture}" />">
				</a>
			</div>
			<div>
				<a href="javascript:void(0);" onclick="show_list1.showDetail('${recommendHotel.hotel.objId}');return false;">${recommendHotel.hotel.hotelName}</a>
			</div>
		</li>
		</c:forEach>
	</ul>
</div>
</c:if>