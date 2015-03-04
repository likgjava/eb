 <%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/number_set_inf.js"></script>
 <form id="projectNumberForm">
 <flex:flexgrid id="taskDetailGrid" rp="10" usepager="false" url="ProjectController.do?method=queryProject" queryColumns="projCode,projName,createProjDate,createOperator" onSubmit="list.before" onSuccess="list.success"  height="50">
	<flex:flexCol name="projCode" display="招标编号" width="150" sortable="true"></flex:flexCol>
	<flex:flexCol name="projName" display="招标名称" width="150" sortable="true"></flex:flexCol>
	<flex:flexCol name="createProjDate" display="项目立项时间" width="150" sortable="true"></flex:flexCol>
	<flex:flexCol name="createOperator" display="项目立项人" width="150" sortable="true"></flex:flexCol>
 </flex:flexgrid>
   <ul style="padding-top:0px">
	 <li>
		 <div class="conOperation" style="text-align:center">
	       		<button type="button" id="sure"><span>确定</span></button>
	   	 </div>
	 </li>
	 <li>
	 	<div class="functionBtnDiv" style="padding-top:5px;padding-bottom:5px">
		    <button type="button" id="pre"><span>上一步</span></button><button type="button" id="next"><span>下一步</span></button>
	    </div>
	 </li>
  </ul>
  </form>