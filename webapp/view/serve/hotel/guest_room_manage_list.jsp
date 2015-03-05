<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/serve/hotel/guest_room_manage_list.js"></script>

<!-- 图片放大区域 -->
<div id="imgViewer" class="izViewer"></div>
<input type="hidden" id="viewType" name="viewType" value="${param.viewType}"/>

<!-- 酒店信息开始 -->
<form:form id="HotelDetailForm" method="post" modelAttribute="hotel">
	<input type="hidden" id="hotelId" name="hotel.objId" value="${hotel.objId }"/>
	<div class="formLayout imgAndForm">
		<h4 class="title"><span>酒店信息</span></h4>
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
<!-- 酒店信息结束 -->

<!-- 新增操作 -->
<div class="formTips attention" id="guestRoomAttention">
	<ul>
	  <li>
		<em>新增客房请点击
		<span class="sysicon siAdd"><a id="createGuestRoomBtn" href="javascript:void(0);"><strong>新增客房</strong></a></span>
		</em>
	  </li>
	</ul>
</div>

<!-- Tab页 -->
<div id="epsTabs">
	<div id="guestRoomList">
		<table class="frontTableList" id="guestRoomManageList">
		<thead>
			<tr>
				<th class="center">图片</th>
				<th class="center">房型</th>
				<th class="money">门市价(元)</th>
				<th class="center">早餐</th>
				<th class="center">床型</th>
				<th class="center">宽带</th>
				<th class="operation">操作</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
		</table>
	</div>
</div>

<!-- 操作按钮 -->
<div>
	<div class="conOperation"> 
  		<button class="largeBtn" id="historyBackBtn" type="button" ><span>返回</span></button>
	</div>
</div>
