<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/serve/hotel/guest_room_manage_list_all.js"></script>

<input type="hidden" id="viewType" name="viewType" value="${param.viewType}"/>

<!-- 查询条件 -->
<div class="conSearch">
<h4><span><spring:message code="globe.query" /></span></h4>
<form id="guestRoomManageListForm">
	<input type="hidden" id="orgInfoId" name="hotel.orgInfo.objId" value="${orgInfoId}"/>
	<ul>
		<li>
			<label>酒店名称：</label>
			<input type="text" id="hotel.hotelName" name="hotel.hotelName" />
			<input type="hidden" name="hotel.hotelName_op" value="like" />
		</li>
		<li>
			<label>客房类型：</label>
			<html:select id="guestRoomType" name="guestRoomType" code="serve.hotel.type">
				<html:option value="">全部</html:option>
			</html:select>
		</li>
		<li class="operationBtnDiv">
			<button type="button" id="query"><span><spring:message code="globe.query" /></span></button>
		</li>
	</ul>
</form>
</div>

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
				<th class="center">酒店名称</th>
				<th class="center">客房类型</th>
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
