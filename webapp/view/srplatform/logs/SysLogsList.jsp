<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/logs/SysLogsList.js"></script>
	<div class="formZone" >
		<form id="operLogsSearchZone" >
			
		</form>
	</div>
	<flex:flexgrid
		id="operLogsGrid" url="SysLogsController.do?method=list" queryColumns=""  
			searchZone="sysLogsSearchZone" rp="10" title="系统日志列表"   
			onSubmit="sysLogsList.before" onSuccess="sysLogsList.success" checkbox="true">
		<flex:flexCol name="fileName" display="文件名" sortable="true" width="200"align="left"></flex:flexCol>
		<flex:flexCol name="lastModifiedTime" display="最后修改时间" sortable="true" width="200"align="left"></flex:flexCol>
		<flex:flexBtn name="下载" bclass="download" onpress="sysLogsList.download"></flex:flexBtn>	
		<flex:flexBtn name="查看" bclass="look" onpress="sysLogsList.see"></flex:flexBtn>	
	</flex:flexgrid>
