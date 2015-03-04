<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/noticemanage/moreNoticeList.js"></script>
<style>
<!--
a.abtn {text-decoration:underline}
-->
</style>
</head>

<body>	  

	<form id="noticeSearchZone" >
		<div class="conSearch">
			<h4><span><spring:message code="globe.query"/></span></h4>
			<ul>
								<li><label><spring:message code="noticeForm.noticeType"/></label>
									<select name="noticeType" id="noticeType" class="required">
										<option></option>
										<option value="00">成交<dm:out value="local__OpenTendering__notice_zh">通知书</dm:out></option>
										<option value="01">结果<dm:out value="local__OpenTendering__notice_zh">通知书</dm:out></option>
									</select>
								</li>
								<li><label><spring:message code="noticeForm.noticeTitle"/></label>
									<input type="text" name="noticeTitle"  >
									<input type="hidden" name="noticeTitle_op" value="like"/>
								</li>		
				<li class="operationBtnDiv"><button type="submit"><span><spring:message code="globe.query"/></span></button></li>
			</ul>
		</div>
	</form>
	<flex:flexgrid checkbox="false"
		id="noticeGrid" url="NoticeController.do?method=getNoticeByQueryObject" queryColumns="project.projCode,project.projName"  
			searchZone="noticeSearchZone" rp="5" title="通知书" onSuccess="noticeList.success" >
					<flex:flexCol name="noticeTitle" display="noticeForm.noticeTitle" sortable="true" width="150"align="left"></flex:flexCol>
					<flex:flexCol name="project.projCode" display="projectForm.projCode" sortable="true" width="120"align="left"></flex:flexCol>
					<flex:flexCol name="project.projName" display="projectForm.projName" sortable="true" width="120"align="left"></flex:flexCol>
	</flex:flexgrid>
	
</body>
</html>
