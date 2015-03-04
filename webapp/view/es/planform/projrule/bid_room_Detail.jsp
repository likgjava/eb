<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/view/es/planform/projrule/bid_room_Detail.js"></script>
<input type="hidden" id="projpackId" name="projpackId"
	value="${param.projectId}">
<style type="text/css">
<!--
#orderRoomtable tr td {
	width: 90px;
	height: 30px;
	text-align: center;
}

table.orderRoomtable {
	width: 800px;
	border-style: solid;
	border-width: 1px;
}

span.orderRoom {
	color: blue;
	text-decoration: underline
}

span.projectMessage {
	color: black
}

td.sun {
	background: #b0e0e6;
}

td.sat {
	background: #b0e0e6;
}

td.mon {
	background: #F5F5DC;
}

td.tue {
	background: #F5F5DC;
}

td.wed {
	background: #F5F5DC;
}

td.thu {
	background: #F5F5DC;
}

td.fri {
	background: #F5F5DC;
}
-->
</style>
<div>
<ul>
	<li>
	<table border="1" cellspacing="1" class="orderRoomtable"
		id="orderRoomtable" style="margin-left: 10px; width: 98%;">
		
		<caption style="text-align: right;">
		<input type="button"
			value="上一周" id="lastWeek" onclick="deleteDate(7)"> <input
			type="button" value="下一周" id="nextWeek" onclick="addDate(7)">
		</caption>
		<tr>
			<td colspan="1" width="120px;">&nbsp;<input type="hidden"
				id="mond" /><input type="hidden" id="tuesd" /><input type="hidden"
				id="wednesd" /><input type="hidden" id="thursd" /><input
				type="hidden" id="frid" /><input type="hidden" id="saturd" /><input
				type="hidden" id="sund" /></td>
			<td class="mon"><span id="monday"></span></td>
			<td class="tue"><span id="tuesday"></span></td>
			<td class="wed"><span id="wednesday"></span></td>
			<td class="thu"><span id="thursday"></span></td>
			<td class="fri"><span id="friday"></span></td>
			<td class="sat"><span id="saturday"></span></td>
			<td class="sun"><span id="sunday"></span></td>
		</tr>
		<tr id="title">
			<td colspan="1">评标室信息</td>
			<td class="mon">星期一</td>
			<td class="tue">星期二</td>
			<td class="wed">星期三</td>
			<td class="thu">星期四</td>
			<td class="fri">星期五</td>
			<td class="sat">星期六</td>
			<td class="sun">星期日</td>
		</tr>
	</table>
	</li>
</ul>

</div>