<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
	<script type="text/javascript" src="<%=request.getContextPath()%>/view/cms/channel/channel_model_list.js"></script>

	<flex:flexgrid checkbox="true"
		id="channelModelGrid" url="ChannelModelController.do?method=list" queryColumns=""  
			searchZone="" rp="10" title="栏目模型" 
			onSubmit="channelModelList.before" >
					<flex:flexCol name="name" display="channelModelForm.name" sortable="true" width="180" align="left"></flex:flexCol>
					<flex:flexCol name="shortName" display="channelModelForm.shortName" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="channelTplPrefix" display="channelModelForm.channelTplPrefix" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="contentTplPrefix" display="channelModelForm.contentTplPrefix" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="sort" display="channelModelForm.sort" sortable="true" width="30"align="left"></flex:flexCol>
					<flex:flexCol name="display" alias="displayCn" display="channelModelForm.display" sortable="true" width="100"align="center"></flex:flexCol>
		<flex:flexBtn name="globe.new" bclass="add" onpress="channelModelList.add"></flex:flexBtn>	
		<flex:flexBtn name="globe.modify" bclass="modify" onpress="channelModelList.update"></flex:flexBtn>	
		<flex:flexBtn name="拷贝" bclass="copyInfo" onpress="channelModelList.copyModel"></flex:flexBtn>	
		<flex:flexBtn name="globe.delete" bclass="delete" onpress="channelModelList.remove"></flex:flexBtn>	
	</flex:flexgrid>
