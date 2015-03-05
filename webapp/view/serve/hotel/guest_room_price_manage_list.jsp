<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/serve/hotel/guest_room_price_manage_list.js"></script>

<input type="hidden" name="orgInfoId" id="orgInfoId" value="${orgInfoId }"/>
<input type="hidden" id="_dialogID"  value="<c:out value="${param.dialogId}"/>"/>
<input type="hidden" id="viewType_grpm" value="${param.viewType}"/>
<input type="hidden" id="guestRoomId" name="guestRoom.objId" value="${guestRoom.objId}" />

<!-- 客房信息开始 -->
<form:form id="guestRoomForm">
	<div class="formLayout  form2Pa">
		<div class="k1">
			<div class="img_250_2" id="newPreview">
				<c:choose>
					<c:when test="${guestRoom.picture==null}">
						<img id="view"  src="<%=request.getContextPath()%>/view/resource/skin/bizplatform/img/orginfo_add.gif" width="200px" height="200px"></img>
					</c:when>
					<c:otherwise>
						<img src="<c:url value="AttachmentController.do?method=showImg&objId=${guestRoom.picture}" />" width="200px" height="200px">
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<ul>
	    	<li>
	            <label>客房编号：</label>
	            <span>${guestRoom.guestroomCode}</span>
	        </li>
	        <li>
	            <label>房间数量：</label>
	            <span>${guestRoom.guestroomNum}</span>
	        </li>
	        <li>
	            <label>门市价：</label>
	            <span>${guestRoom.retailPrice} 元</span>
	        </li>
	        <li>
	            <label>面积：</label>
	            <span>${guestRoom.area} 平方</span>
	        </li>
	        <li>
	          	<label>楼层：</label>
	          	<span>${guestRoom.floor}</span>
    	  	</li>
	        <li>
	          	<label>宽带：</label>
	          	<span>${guestRoom.broadband}</span>
    	  	</li>
	    	<li>
	            <label>客房类型：</label>
	            <span>${guestRoom.guestRoomTypeCN}</span>
	        </li>
	        <li>
	            <label>床型：</label>
				<span>${guestRoom.bedTypeCN}</span>
	        </li>
	        <li class="fullLine">
	          	<label>早餐类型：</label>
	          	<span>${guestRoom.breakfastTypeCN}</span>
    	  	</li>
	    </ul>
	    <ul>
	        <li class="fullLine">
	           <label>房间描述：</label>
	           <div>${guestRoom.guestroomDesc}<c:if test="${fn:length(guestRoom.guestroomDesc) < 100}"><br/><br/><br/><br/></c:if></div>
	        </li>
	    </ul>
	</div>
</form:form>
<!-- 客房信息结束 -->

<!-- 新增操作 -->
<div class="formTips attention" id="createGuestRoomPriceAttention">
	<ul>
	  <li>
		<em>新增客房价格请点击
		<span class="sysicon siAdd"><a id="createGuestRoomPriceBtn" href="javascript:void(0);"><strong>新增客房价格</strong></a></span>
		</em>
	  </li>
	</ul>
</div>

<!-- Tab页 -->
<div id="epsTabs">
	<div id="guestRoomPriceList">
		<table class="frontTableList" id="guestRoomPriceManageList">
		<thead>
			<tr>
				<th class="money">协议价(元)</th>
				<th class="date">开始时间</th>
				<th class="date">结束时间</th>
				<th class="center">早餐</th>
				<th class="operation">操作</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
		</table>
	</div>
</div>

<div class="conOperation"> 
 	<button class="largeBtn" id="historyBackBtn_view" type="button" ><span>返回</span></button>
 	<button class="largeBtn" id="showGuestRoomPriceCloseBut" type="button" ><span>关闭</span></button>
</div>
