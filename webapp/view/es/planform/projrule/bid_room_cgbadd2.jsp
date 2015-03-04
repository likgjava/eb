<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/projrule/bid_room_cgbadd2.js"></script>

<ul style="margin-left: 10px;">
<li>
<input type="checkbox" id="checkTemporaryRoom">增加评标室:(如须增加其他评标室，请勾选增加评标室)</li>
<li id="temporaryRoom">
<table id="facilities"  border="0">
	
</table>
<form id="RoomForm">
<table border="0">
<tr><td width="80px" align="right">评标室名称：</td>
<td>	<span class="eleRequired">*</span></td>
<td><input type="text" size="60" id="meetRoomName" name="meetRoomName" ></td>
</tr>
<tr>
<td width="80px" align="right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;地址：</td>
<td>	<span class="eleRequired">*</span></td>
<td><input type="text" size="60" id="meetRoomAddress" name="meetRoomAddress"></td>
<td><input type="button" value="确定" id="addRoom"><input type="hidden" size="60" id="roomType" name="roomType" value="01"></td>
<td><input type="button" value="新增设施" id="addFacilities"></td></tr>
</table>
</form>
</li>
</ul>