<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/logs/LoginLogsList.js"></script>
<input type="hidden" name="onlineUserId" value="<c:out value="${param.onlineUserId}"/>">
	<div class="conSearch">
	  <h4><span>查询</span></h4>
	  <form id="loginLogsSearchZone" >
		  <ul>
		    <li>
		      <label><spring:message code="loginLogsForm.user" />:</label>
		      <input type="text" name="user.usName" value="">
			  <input type="hidden" name="user.usName_op" value="like">
		    </li>
		    <li class="operationBtnDiv">
		      <button ><span><spring:message code="globe.query"/></span></button>
		    </li>
		  </ul>
	  </form>
	</div>
		<flex:flexgrid
			id="loginLogsGrid" url="LoginLogsController.do?method=list" queryColumns="user.emp.name,user.usName,lgnInTime,lgnOutTime,lgnFromIp"  
				searchZone="loginLogsSearchZone" rp="10" title="登陆日志列表"   checkbox="true"
				onSubmit="loginLogsList.before" onSuccess="loginLogsList.success">
			<flex:flexCol name="user.emp.name" display="员工" sortable="true" width="150"align="left"></flex:flexCol>
			<flex:flexCol name="user.usName" display="用户名" sortable="true" width="150"align="left"></flex:flexCol>
			<flex:flexCol name="lgnInTime" display="登录时间" sortable="true" width="150"align="left"></flex:flexCol>
			<flex:flexCol name="lgnOutTime" display="退出时间" sortable="true" width="150"align="left"></flex:flexCol>
			<flex:flexCol name="lgnFromIp" display="IP" sortable="true" width="150"align="left"></flex:flexCol>
			
			<flex:flexBtn name="删除" bclass="delete" onpress="loginLogsList.remove"></flex:flexBtn>	
		</flex:flexgrid>
