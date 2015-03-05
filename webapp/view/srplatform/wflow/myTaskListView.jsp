<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/wflow/myTaskListView.js"></script>
</head>
<body>	  
	<!--<div class="formZone" id="formZone">
		<form id="taskSearchZone" >
			<div>
				
				<button><span><spring:message code="globe.query"/></span></button>
			</div>
		</form>
	</div>
	--><div id="myTaskListViewGrid"></div>
	<flex:flexgrid  showTableToggleBtn="true"  
			id="userGrid" url="WorkFlowController.do?method=getMyTask" queryColumns="name,objId,createTime"  
				 rp="10" title="我的待办任务列表"  height="200"  
				 checkbox="true">
			<flex:flexCol name="name" display="任务名称" width="150" sortable="true"></flex:flexCol>
			<flex:flexCol name="createTime" display="创建时间" sortable="true" width="150" ></flex:flexCol>
			<span><flex:flexBtn name="进入任务" bclass="add" onpress="myTaskListView.doTask"></flex:flexBtn></span>	
			<flex:flexBtn name="开始任务申报书" bclass="look" onpress="myTaskListView.startBidBuytProIn"></flex:flexBtn>
		</flex:flexgrid>
</body>
</html>
