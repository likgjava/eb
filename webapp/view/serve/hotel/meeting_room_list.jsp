<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src='<c:url value="/view/serve/hotel/meeting_room_list.js"/>'></script>

<input type="hidden" id="priceSupplierId" name="priceSupplierId" value="${goodsPriceSupplierId }"/>
<input type="hidden" id="supplierId" name="orgInfoId" value="${orgInfoId }"/>
<input type="hidden" id="viewType" name="viewType" value="${param.viewType}"/>


<form:form id="HotelDetailForm" method="post" modelAttribute="hotel">
		<input type="hidden" id="hotelId" name="hotel.objId" value="${hotel.objId }"/>
		<input type="hidden" name="additionPicture" value="${hotel.additionPicture}" />
		
	<div class="formLayout imgAndForm">
		<h4 class="title"><span>商品信息</span></h4>
		<div class="k1">
			<img width="200px" height="175px" src="<c:url value="AttachmentController.do?method=showImg&objId=${hotel.picture}" />">
		</div>
		<ul>
		 	<li class="fullLine"><label>酒店名称 ：</label> 
				<span id="hotelName">${hotel.hotelName }</span>
			</li>
			<li class="fullLine"><label>酒店星级：</label> 
				<span id="starCN">${hotel.starCN }</span>
			</li>
			<li class="fullLine">
	            <label>开业时间：</label>
	            <span id="startTime"><fmt:formatDate value="${hotel.startTime}" pattern="yyyy-MM-dd"/></span>
	        </li>
			
			<li class="fullLine"><label>酒店区域：</label> 
				<span id="">${hotel.district.parent.parent.name }&nbsp;${hotel.district.parent.name }&nbsp;${hotel.district.name }</span>
			</li>
			<li class="fullLine"><label>酒店地址：</label> 
				<span id="hotelAddress">${hotel.hotelAddress }</span>
			</li>
			<li class="fullLine"><label>联系电话：</label>
				<span id="contact">${hotel.contact }</span>
			</li>
			<li class="fullLine"><label>传&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;真：</label>
				<span id="fax">${hotel.fax }</span>
			</li>
		</ul>
	</div>
	
	
</form:form>

<div class="formTips attention" id="meetingRoomAttention">
	<ul>
		<li>
			<em>注意：</em>新增会议室请点击<a id="addMeetingRoom" class="sysicon siAdd" href="javascript:void(0);"><span><strong>新增会议室</strong></span></a>
		</li>
	</ul>
</div>

<div id="epsTabs">
	<div id="meetingRoomPage">
	<table class="frontTableList" id="meetingRoomList">
		<thead>
			<tr>
				<th class="center">图片</th>
				<th class="money">市场价(元)</th>
				<th class="center">价格单位</th>
				<th class="center">会议类型</th>
				<th class="quantity">坐席人数</th>
				<th class="operation">操作</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
	</div>
</div>

<div>
	<div class="conOperation"> 
  		<button class="largeBtn" id="historyBackBtn" type="button" ><span>返回</span></button>
	</div>
</div>


