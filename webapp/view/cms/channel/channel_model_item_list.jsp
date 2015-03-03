<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/view/cms/channel/channel_model_item_list.js"></script>
</head>
<body>	  

	<form id="channelModelItemSearchZone" >
		<div class="conSearch">
			<h4><span><spring:message code="globe.query"/></span></h4>
			<ul>
				<li class="operationBtnDiv"><button type="submit"><span><spring:message code="globe.query"/></span></button></li>
			</ul>
		</div>
	</form>

	<flex:flexgrid checkbox="true"
		id="channelModelItemGrid" url="ChannelModelItemController.do?method=list" queryColumns=""  
			searchZone="channelModelItemSearchZone" rp="10" title="栏目模型明细"  
			onSubmit="channelModelItemList.before" >
					<flex:flexCol name="name" display="channelModelItemForm.name" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="sort" display="channelModelItemForm.sort" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="display" display="channelModelItemForm.display" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="label" display="channelModelItemForm.label" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="required" display="channelModelItemForm.required" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="defaultValue" display="channelModelItemForm.defaultValue" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="help" display="channelModelItemForm.help" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="channelModel" display="channelModelItemForm.channelModel" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="formType" display="channelModelItemForm.formType" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="textLength" display="channelModelItemForm.textLength" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="textareaRows" display="channelModelItemForm.textareaRows" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="textareaCols" display="channelModelItemForm.textareaCols" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="keyVal" display="channelModelItemForm.keyVal" sortable="true" width="100"align="left"></flex:flexCol>
		<flex:flexBtn name="globe.new" bclass="add" onpress="channelModelItemList.add"></flex:flexBtn>	
		<flex:flexBtn name="globe.modify" bclass="add" onpress="channelModelItemList.update"></flex:flexBtn>	
		<flex:flexBtn name="globe.delete" bclass="delete" onpress="channelModelItemList.remove"></flex:flexBtn>	
	</flex:flexgrid>
</body>
</html>
