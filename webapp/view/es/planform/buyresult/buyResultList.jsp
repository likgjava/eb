<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/buyresult/buyResultList.js"></script>
	<input type="hidden" id="projectId" name="projectId" value="${param.projectId}">
	<div id="buyResultListView">
	<flex:flexgrid checkbox="true"
		id="buyResultGrid" url="BuyResultController.do?method=list&project.objId=${param.projectId}" queryColumns=""  
			searchZone="buyResultSearchZone" rp="10"  
			onSubmit="buyResultList.before" onSuccess="buyResultList.success">
					<flex:flexCol name="buyrResult" display="buyResultForm.buyrResult" sortable="true" width="150"align="left"></flex:flexCol>
					<flex:flexCol name="ebuyMethod" display="buyResultForm.ebuyMethod" sortable="true" width="150"align="left"></flex:flexCol>
		<flex:flexBtn name="globe.new" bclass="add" onpress="buyResultList.add"></flex:flexBtn>	
		<flex:flexBtn name="globe.modify" bclass="modify" onpress="buyResultList.update"></flex:flexBtn>
		<flex:flexBtn name="详情" bclass="look" onpress="buyResultList.look"></flex:flexBtn>		
		<flex:flexBtn name="globe.delete" bclass="delete" onpress="buyResultList.remove"></flex:flexBtn>	
	</flex:flexgrid>
    </div>
