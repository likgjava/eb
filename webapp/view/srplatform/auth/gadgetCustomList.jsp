<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>

	<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/auth/gadgetCustomList.js"></script>
	
	<flex:flexgrid showTableToggleBtn="true" usepager="false" rp="50"
		id="gadgetGrid" url="GadgetController.do?method=getNotAtDesktopGadget" queryColumns=""  
			searchZone="gadgetSearchZone"  width="auto"
			onSuccess="gadgetList.success" >
		<flex:flexCol name="name" display="名称" width="100"></flex:flexCol>
		<flex:flexCol name="resource.url" display="URL" sortable="true" align="left" width="300"></flex:flexCol>
		<flex:flexCol name="descs" display="描述" width="160"></flex:flexCol>
	</flex:flexgrid>
	

