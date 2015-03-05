<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/wflow/taskDOPage.js"></script>
</head>
<input type="hidden" name="auditTaskId" id="auditTaskId" value="${param.taskId}"/>
<input type="hidden" name="taskDoParentBizId" id="taskDoParentBizId" value=""/>
<input type="hidden" name="taskDoBizId" id="taskDoBizId" value=""/>
		
<div class="formRow" id="viewPage"></div>
<div class="btnArea">
	<button class="btn primary" id="viewHistoryTaskPageButton" type="button" tabindex="18"><span><span>显示操作历史</span></span></button>
</div>
<div class="formRow" id="viewHistoryTaskPage" style="display: none;">
	<table id="viewHistoryTaskPageTable"  align="left" border="1" width="70%">
		<tr>
			<td width="40%" align="center">操作名称
			</td>
			<td width="40%" align="center">操作记录
			</td>
			<td width="20%" align="center">操作时间
			</td>
 		</tr>
	</table>
</div>
