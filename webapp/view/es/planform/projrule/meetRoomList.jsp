<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/projrule/meetRoomList.js"></script>
<div id="meetRoomListView">
	<flex:flexgrid checkbox="true"
		id="meetRoomGrid" url="MeetRoomController.do?method=list" queryColumns=""  
			searchZone="meetRoomSearchZone" rp="5" title="评标室信息" 
			onSubmit="meetRoomList.before" onSuccess="meetRoomList.success">
					<flex:flexCol name="meetRoomName" display="meetRoomForm.meetRoomName" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="meetRoomAddress" display="meetRoomForm.meetRoomAddress" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="roomType" alias="roomTypeCN" display="meetRoomForm.roomType" sortable="true" width="100"align="left"></flex:flexCol>
		<flex:flexBtn name="globe.new" bclass="add" onpress="meetRoomList.add"></flex:flexBtn>	
		<flex:flexBtn name="globe.modify" bclass="modify" onpress="meetRoomList.update"></flex:flexBtn>	
		<flex:flexBtn name="globe.delete" bclass="delete" onpress="meetRoomList.remove"></flex:flexBtn>	
	</flex:flexgrid>
</div>
