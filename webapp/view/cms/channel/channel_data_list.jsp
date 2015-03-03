<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/cms/channel/channel_data_list.js"></script>
<form id="channelDataSearchZone" >
	<div class="conSearch">
		<h4><span><spring:message code="globe.query"/></span></h4>
		<ul>
			<li class="operationBtnDiv"><button type="submit"><span><spring:message code="globe.query"/></span></button></li>
		</ul>
	</div>
</form>

<flex:flexgrid checkbox="true"
	id="channelDataGrid" url="ChannelDataController.do?method=list" queryColumns=""  
		searchZone="channelDataSearchZone" rp="10" title="栏目数据"
		onSubmit="channelDataList.before" >
				<flex:flexCol name="name" display="channelDataForm.name" sortable="true" width="100"align="left"></flex:flexCol>
				<flex:flexCol name="sortNo" display="channelDataForm.sortNo" sortable="true" width="100"align="left"></flex:flexCol>
				<flex:flexCol name="display" display="channelDataForm.display" sortable="true" width="100"align="left"></flex:flexCol>
				<flex:flexCol name="channel" display="channelDataForm.channel" sortable="true" width="100"align="left"></flex:flexCol>
				<flex:flexCol name="dataType" display="channelDataForm.dataType" sortable="true" width="100"align="left"></flex:flexCol>
				<flex:flexCol name="num" display="channelDataForm.num" sortable="true" width="100"align="left"></flex:flexCol>
				<flex:flexCol name="needLoad" display="channelDataForm.needLoad" sortable="true" width="100"align="left"></flex:flexCol>
				<flex:flexCol name="dataKey" display="channelDataForm.dataKey" sortable="true" width="100"align="left"></flex:flexCol>
				<flex:flexCol name="dataVal" display="channelDataForm.dataVal" sortable="true" width="100"align="left"></flex:flexCol>
				<flex:flexCol name="tmpType" display="channelDataForm.tmpType" sortable="true" width="100"align="left"></flex:flexCol>
				<flex:flexCol name="sortType" display="channelDataForm.sortType" sortable="true" width="100"align="left"></flex:flexCol>
	<flex:flexBtn name="globe.new" bclass="add" onpress="channelDataList.add"></flex:flexBtn>	
	<flex:flexBtn name="globe.modify" bclass="add" onpress="channelDataList.update"></flex:flexBtn>	
	<flex:flexBtn name="globe.delete" bclass="delete" onpress="channelDataList.remove"></flex:flexBtn>	
</flex:flexgrid>
