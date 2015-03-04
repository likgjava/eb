<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
	<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/workgroup/workGroupList.js"></script>

  <div id="workGroupListView">
	<flex:flexgrid checkbox="true"
		id="workGroupGrid" url="WorkGroupController.do?method=list" queryColumns=""  
			searchZone="workGroupSearchZone" rp="10" title="工作小组" width="800"  
			onSubmit="workGroupList.before" onSuccess="workGroupList.success">
					<flex:flexCol name="workgType" display="workGroupForm.workgType" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="workgRemark" display="workGroupForm.workgRemark" sortable="true" width="100"align="left"></flex:flexCol>
		<flex:flexBtn name="globe.new" bclass="add" onpress="workGroupList.add"></flex:flexBtn>	
		<flex:flexBtn name="详情" bclass="look" onpress="workGroupList.look"></flex:flexBtn>	
		<flex:flexBtn name="globe.modify" bclass="modify" onpress="workGroupList.update"></flex:flexBtn>	
		<flex:flexBtn name="globe.delete" bclass="delete" onpress="workGroupList.remove"></flex:flexBtn>	
	</flex:flexgrid>
</div>
