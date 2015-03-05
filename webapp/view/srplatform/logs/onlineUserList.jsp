<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/logs/onlineUserList.js"></script>
	<div class="conSearch">
	  <h4><span>查询</span></h4>
	  <form id="userSearchZone" >
		  <ul>
		    <li>
		      <label>用户名:</label>
		      <input type="text" name="usName" id="usName" />
		    </li>
		   
		    <li class="operationBtnDiv">
		      <button type="submit"><span><spring:message code="globe.query"/></span></button>
		    </li>
		  </ul>
	  </form>
	</div>
	<flex:flexgrid
		id="userGrid" url="OnlineUserController.do?method=findOnlineUser" queryColumns=""  
			searchZone="userSearchZone" rp="10" title="在线用户列表"  
			onSubmit="onlineUserList.before" onSuccess="onlineUserList.success" checkbox="true">
		<flex:flexCol name="usName" display="帐号" width="150" sortable="true"></flex:flexCol>
		<flex:flexCol name="emp.company.name" display="机构名称" width="180"></flex:flexCol>
		<flex:flexCol name="loginTime" display="登入时间" sortable="true" width="150"align="left"></flex:flexCol>
		<flex:flexCol name="onlineTime" display="在线时长" sortable="true" width="150"align="left"></flex:flexCol>
		<flex:flexBtn name="注销" bclass="stop" onpress="onlineUserList.offline"></flex:flexBtn>	
	</flex:flexgrid>
