<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>
<!--
a.abtn {text-decoration:underline}
-->
</style>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/common/apiLogList.js"></script>
</head>
<body>	  
	<form id="apiLogSearchZone" >
		<div class="conSearch">
			<h4><span><spring:message code="globe.query"/></span></h4>
			<ul>
				<li class="fullLine" class="short">
					类型：
			    	<select name="apiType">
			    		<option value="">--请选择--</option>
			    		<option value="00">--公告--</option>
			    		<option value="02">--短消息--</option>
			    		<option value="01">--待办任务--</option>
			    	</select>
					创建时间：
			    	<input id="_applyDate" name="createTime" style="width: 90px;">
				</li>
				<li class="operationBtnDiv"><button type="submit"><span><spring:message code="globe.query"/></span></button></li>
			</ul>
		</div>
	</form>
<div id="epsTabs">

  <ul>
  	<li>
      <a href="#apiLogListInfo" id = "tabs_toFalse" class="refreshData"><span>未发送</span></a>
    </li>
    <li>
      <a href="#apiLogListInfo" id = "tabs_toTrue" class="refreshData"><span>已发送</span></a>
    </li>
    <li>
      <a href="#apiLogListInfo" id = "tabs_toException" class="refreshData"><span>异常</span></a>
    </li>
   </ul>
  <div id="apiLogListInfo">
	<flex:flexgrid checkbox="true"
		id="apiLogGrid" url="ApiLogController.do?method=list" queryColumns=""  
			searchZone="apiLogSearchZone" rp="10" title="日志记录"
			onSubmit="apiLogList.before" onSuccess="apiLogList.success">
					<flex:flexCol name="project.projName" display="招标名称" sortable="true" width="130"align="left"></flex:flexCol>
					<flex:flexCol name="project.projCode" display="招标编号" sortable="true" width="150"align="left"></flex:flexCol>
					<flex:flexCol name="targetName" display="目标地" sortable="true" width="130"align="left"></flex:flexCol>
					<flex:flexCol name="apiType" alias="apiTypeCN" display="类型" sortable="true" width="50"align="center"></flex:flexCol>
					<flex:flexCol name="status" alias="statusCN" display="状态" sortable="true" width="50"align="center"></flex:flexCol>
					<flex:flexCol name="createTime" display="创建时间" format="date" sortable="true" width="70" align="center"></flex:flexCol>
					<flex:flexBtn name="批量发送" bclass="tableGo" onpress="apiLogList.sendBulletin"></flex:flexBtn>
	</flex:flexgrid>
  </div>
</div>
</body>
</html>
