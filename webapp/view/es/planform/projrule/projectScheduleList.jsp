<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/projrule/projectScheduleList.js"></script>
</head>
<body>	  

	<form id="projectScheduleSearchZone" >
		<div class="conSearch">
			<h4><span><spring:message code="globe.query"/></span></h4>
			<ul>
			   	<li><label><spring:message code="projectScheduleForm.project"/>
										<input type="text" name="project"  >
									   <input type="hidden" name="project_op" value="like"/>
									</label></li>
					<li><label><spring:message code="projectScheduleForm.projectpackage"/>
										<input type="text" name="projectpackage"  >
									   <input type="hidden" name="projectpackage_op" value="like"/>
									</label></li>	
					<li><label><spring:message code="projectScheduleForm.remark"/>
										<input type="text" name="remark"  >
									   <input type="hidden" name="remark_op" value="like"/>
									</label></li>								
				<li><button type="submit"><span><spring:message code="globe.query"/></span></button></li>
			</ul>
		</div>
	</form>

	<flex:flexgrid checkbox="true"
		id="projectScheduleGrid" url="ProjectScheduleController.do?method=getAllProjectSchedule" queryColumns=""  
			searchZone="projectScheduleSearchZone" rp="10" title="项目执行时间安排" width="1200"  
			onSubmit="projectScheduleList.before" onSuccess="projectScheduleList.success">
					<flex:flexCol name="project" display="projectScheduleForm.project" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="projectpackage" display="projectScheduleForm.projectpackage" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="signUpSTime" display="projectScheduleForm.signUpSTime" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="signUpETime" display="projectScheduleForm.signUpETime" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="sellBidDocSTime" display="projectScheduleForm.sellBidDocSTime" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="sellBidDocETime" display="projectScheduleForm.sellBidDocETime" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="tenderStartTime" display="projectScheduleForm.tenderStartTime" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="tenderEndTime" display="projectScheduleForm.tenderEndTime" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="openBidTime" display="projectScheduleForm.openBidTime" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="remark" display="projectScheduleForm.remark" sortable="true" width="100"align="left"></flex:flexCol>
		<flex:flexBtn name="globe.new" bclass="add" onpress="projectScheduleList.add"></flex:flexBtn>	
		<flex:flexBtn name="globe.modify" bclass="add" onpress="projectScheduleList.update"></flex:flexBtn>	
		<flex:flexBtn name="globe.delete" bclass="delete" onpress="projectScheduleList.remove"></flex:flexBtn>	
	</flex:flexgrid>
</body>
</html>
