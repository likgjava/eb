<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<style>
.attributes .attributes-list-full li {
    height: auto;
    white-space: normal;
    width: 100%;
}
</style>
<input type="hidden" id="tabId" value="${tabId}">

<input type="hidden" id="hotelId" value="${hotel.objId}" />
<div id="conTitle">
	<div class="navCurrent">
		<a id="a" href="javascript:void(0);" onclick="hotelDetail.searchByTitle('',null,'1');return false;" >酒店展示</a>
		<a href="javascript:void(0);" onclick="hotelDetail.searchByTitle('${hotel.star }',null,'1');return false;" >${hotel.starCN }</a>
		<c:if test="${districtId!=null}">
			<a id="b" href="javascript:void(0);" onclick="hotelDetail.searchByTitle('${hotel.star }','${districtId }','1');return false;" >${districtName }</a>
		</c:if>
		酒店详情
	</div>
</div> 
<div id="idHandle" class="handle"></div>
<div id="conBody"><!--功能页内容-->
	<div class="imgAndInfo">
		<div id="showImg">
			<ul id="hotelImg">
				<li><img src="<c:url value="AttachmentController.do?method=showImg&objId=${hotel.picture}" />" id="mainImg"></li>
				<c:forEach var="image" items="${images}">
					<li><img src="<c:url value="AttachmentController.do?method=showImg&objId=${image.objId}" />"></li>
				</c:forEach>				
			</ul>
		</div>
		<div id="imgViewer" class="izViewer"></div>
		<div id="showInfo"  class="morePic">
			<ul class="meta">	
				<li class="detail-title"><span>酒店星级：</span><span class="hotel_stars${hotel.star }">&nbsp;</span></li>
				<li class="detail-title"><span>酒店名称：</span><strong>${hotel.hotelName }</strong>
					<button type="button" onclick="common.addFavorites('${hotel.objId }','${hotel.hotelName}','06')" class="favBtn" height="25px">加入收藏</button>
				</li>
				<li class="detail-title"><span>酒店地址：</span>${hotel.hotelAddress }</li>
				<li class="detail-title"><span>周围环境：</span>${hotel.surroundings}</li>
			</ul>
		    <div class="key">
			    <dl>
		   			<dt><em>评价总分：</em></dt>
		   			<dd class="totalScore">
		   						<ul class="rating-level">
		   								<li><a class="aa<fmt:formatNumber type="number" value="${hotel.evalSum }" maxFractionDigits="0"/>-stars current-rating"  href="#"></a></li>
		   						</ul>
		   						<span id="stars2-tips" class="result">
		   							<fmt:formatNumber type="number" value="${hotel.evalSum }" pattern="#0.0"/>分
		   						</span>
		   			</dd>
		   		</dl>
		    </div>
			<ul class="other">
				<li><span>所在地区：</span>${hotel.district.name }</li>
				<li><span>开业时间：</span><fmt:formatDate value="${hotel.startTime }" pattern="yyyy年MM月dd"/></li>										
				<li><span>电话：</span>${hotel.contact }</li>
				<li><span>传真：</span>${hotel.fax }</li>
			</ul>
		</div>
		</div>
		<!---商品图 结束--> <!-- Tab页 -->
		<div id="epsTabs" class="epsTabs">
		<ul>
			<li><a href="#baseParam" id="baseParamTab"><span>酒店详情</span></a></li>
			<li><a href="#optionList" id="optionListTab"><span>客房信息</span></a></li>
			<li><a href="#hotelPriceList" id="hotelPriceListTab"><span>会议室信息</span></a></li>
			<li><a href="#evaluateList" id="evaluateListTab"><span>评价信息</span></a></li>
		</ul>
		<div id="baseParam" class="formLayout">
			<div class="attributes" id="attributes"> 
				<ul class="attributes-list attributes-list-full">
					<li><img src="view/resource/skin/sysicon/16/bullet_go.png"/> <em>酒店服务项目：</em>${hotel.serviceItemsName}</li>
					<li><img src="view/resource/skin/sysicon/16/bullet_go.png"/> <em>餐饮设施：</em>${hotel.foodFacilitiesName}</li>
					<li><img src="view/resource/skin/sysicon/16/bullet_go.png"/> <em>娱乐设施：</em>${hotel.funFacilitiesName}</li>
					<li><img src="view/resource/skin/sysicon/16/bullet_go.png"/> <em>客房设施和服务：</em>${hotel.guestRoomFacilitiesName}</li>
					<li><img src="view/resource/skin/sysicon/16/bullet_go.png"/> <em>可接受信用卡类型：</em>${hotel.creditCardTypeName}</li>
				</ul>
    		</div>
	    	<div>
    			<h4><span>酒店概况</span></h4>
    			${hotel.hotelDesc}
    		</div>
	    	<div>
    			<h4><span>详细介绍</span></h4>
    			${hotel.hotelDetail}
    		</div>
		</div>
		<div id="optionList">
			<c:choose>
				<c:when test="${fn:length(hotel.guestRooms) == 0}">
					<div class="sorry">暂无客房信息！</div>
				</c:when>
				<c:otherwise>
					<table class="frontTableList" id="optionListTable">
						<thead>
							<tr>
								<th>房型</th>
								<th>市场价（元）</th>
								<th>早餐</th>
								<th>床型</th>
								<th>宽带</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="guestRoom" items="${hotel.guestRooms}">
								<tr>
									<td class="center"><a href="javascript:void(0);" onclick="hotelDetail.openOrCloseMsg('${guestRoom.objId}','true');return false;">${guestRoom.guestRoomTypeCN}</a></td>
									<td class="center">￥<fmt:formatNumber value="${guestRoom.retailPrice}" pattern="#,##0.00#" /></td>
									<td class="center">${guestRoom.breakfastTypeCN}</td>
									<td class="center">${guestRoom.bedTypeCN}</td>
									<td class="center">${guestRoom.broadband}</td>
								</tr>
								<tr class="hidden" id="roomTr_${guestRoom.objId}">
									<td colspan="5">
										<div class="searchresult_caption layoutfix">
											<span id="span_${guestRoom.objId}" name="${guestRoom.picture}" ></span>
											<ul class="searchresult_caplist">
												<li>面积：${guestRoom.area}平方米</li>
												<li>楼层：${guestRoom.floor}</li>
											</ul>
											<span style="padding-left:20px;">今日协议价：<span id="agreePrice_${guestRoom.objId}"></span></span><br/>
											<span style="padding-left:20px;">${guestRoom.guestroomDesc}</span>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:otherwise>
			</c:choose>
		</div>
		<div id="hotelPriceList">
			<c:choose>
				<c:when test="${fn:length(hotel.meetingRooms) == 0}">
					<div class="sorry">暂无会议室信息！</div>
				</c:when>
				<c:otherwise>
					<table class="frontTableList" id="optionListTable">
						<thead>
							<tr>
								<th>会议室类型</th>
								<th>市场价（元）</th>
								<th>坐席人数</th>
								<th>会议室设施</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="meetingRoom" items="${hotel.meetingRooms}">
								<tr>
									<td class="center"><a href="javascript:void(0);" onclick="hotelDetail.openOrCloseMsg('${meetingRoom.objId}');return false;">${meetingRoom.meetingRoomTypeCN}</a></td>
									<td class="center">￥<fmt:formatNumber value="${meetingRoom.marketPrice}" pattern="#,##0.00#" /> / ${meetingRoom.unitCN}</td>
									<td class="center"><fmt:formatNumber value="${meetingRoom.containNum}" pattern="#,##0" /></td>
									<td class="center">${meetingRoom.meetingRoomFacilitiesName}</td>
								</tr>
								<tr class="hidden" id="roomTr_${meetingRoom.objId}">
									<td colspan="4">
										<div class="searchresult_caption layoutfix">
											<span id="span_${meetingRoom.objId}" name="${meetingRoom.picture}" ></span>
											<span style="padding-left:20px;">${meetingRoom.meetingRoomFesc}</span>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:otherwise>
			</c:choose>
		</div>
		<div id="evaluateList"></div>
		
		</div>
	<!-- 评价from -->
	<div id="evaluateDiv"><%@ include file="/view/serve/hotel/showhotel/hotel_evaluate_form.jsp"%></div>
	
</div>

<script>
var hotelDetail = {}
hotelDetail.oTable;


//点击品目标题
hotelDetail.searchByTitle = function(star,districtId,districtLevel) {
	var param = "&starLevel=" + star;

	if(districtId) {  //区域id
		param += "&districtId=" + districtId;
	}
	
	if(districtLevel){
		param += "&districtLevel=" + districtLevel;
	}
	
	$('#sysContent').loadPage($('#initPath').val()+'/HotelShowController.do?method=toHotelList&rp=21&page=1&type=detail'+ param);
}

//显示或隐藏详细信息的内容
hotelDetail.openOrCloseMsg=function(id,isGuest){
	if($("#roomTr_"+id).hasClass("hidden")){
		$("#roomTr_"+id).removeClass("hidden");

		//如果没有图片
		if($("#span_"+id).html() == "") {
			$("#span_"+id).append('<img class="link" width="95px" height="80px" src="AttachmentController.do?method=showImg&objId='+$("#span_"+id).attr("name")+'" >');
		}

		if(isGuest && common.isLogin() && $("#agreePrice_"+id).html() == ""){
			$.getJSON($('#initPath').val()+'/GuestRoomPriceController.do?method=getTodayAgreePrice&guestRoomId='+id, function(json){
				$("#agreePrice_"+id).html(formatAmount(json.todayAgreePrice,2)+" 元");
			});
		}
	}else {
		$("#roomTr_"+id).addClass("hidden");
	}
}

$(document).ready(function(){
	//调整页面布局 
	fnRemoveOtherMain(); 
	changeTabsCss("goToHotel");
	
	//选中查询下拉框
	keyWordTypeChange('6');
	
	var imageZoom;
	if(imageZoom==null){
		imageZoom = new ImageZoom( "mainImg", "imgViewer", {
			mode: "handle", handle: "idHandle", scale: 20, delay: 0
		});
	}
	
	//加载评价列表
	$("#evaluateListTab").click(function(){
		$("#evaluateList").loadPage($("#initPath").val()+"/view/serve/hotel/showhotel/hotel_evaluate_list.jsp");
	})
	
	$('ul.#hotelImg').bxGallery({});//图片显示控件
	
	$('#epsTabs').tabs();//加载tabs
});
</script>