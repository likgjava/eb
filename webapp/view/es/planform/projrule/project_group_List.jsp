<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/projrule/project_group_List.js"></script>
<div class="formLayout" style="padding-bottom:35px">
<div id="infoDiv" >
   <ul>
	 	<li><a href="#leaderList">领导小组</a></li>
		<li><a href="#workList">工作小组</a></li>
	 </ul>
 <div id="leaderList">
 <flex:flexgrid id="leadGroupGrid" title="领导小组成员" rp="10" showTableToggleBtn="true" url="GroupMemberController.do?method=getLeaderGroup" queryColumns="name,duty,orgName,dept,memberTitle,tel" onSubmit="leadGroup.before" onSuccess="leadGroup.success"  height="120" checkbox="false">
	<flex:flexCol name="name" display="姓名" width="100" sortable="true"></flex:flexCol>
	<flex:flexCol name="duty" display="职务" width="100" sortable="true"></flex:flexCol>
	<flex:flexCol name="orgName" display="所在单位" width="150" sortable="true"></flex:flexCol>
	<flex:flexCol name="dept" display="处室" width="100" sortable="true"></flex:flexCol>
	<flex:flexCol name="memberTitle" display="级别" width="100" sortable="true"></flex:flexCol>
	<flex:flexCol name="tel" display="电话" width="100" sortable="true"></flex:flexCol>
	<flex:flexCol name="options" display="操作" width="150" sortable="true"></flex:flexCol>
 </flex:flexgrid>
 </div>


 <div id="workList">
 <flex:flexgrid id="workGroupGrid" title="工作小组成员" rp="10" usepager="true" showTableToggleBtn="true" url="GroupMemberController.do?method=getWorkerGroup" queryColumns="" onSubmit="workGroup.before" onSuccess="workGroup.success"  height="120" checkbox="false">
	<flex:flexCol name="name" display="姓名" width="100" sortable="true"></flex:flexCol>
	<flex:flexCol name="duty" display="职务" width="100" sortable="true"></flex:flexCol>
	<flex:flexCol name="orgName" display="所在单位" width="150" sortable="true"></flex:flexCol>
	<flex:flexCol name="dept" display="处室" width="100" sortable="true"></flex:flexCol>
	<flex:flexCol name="memberTitle" display="级别" width="100" sortable="true"></flex:flexCol>
	<flex:flexCol name="tel" display="电话" width="100" sortable="true"></flex:flexCol>
	<flex:flexCol name="options" display="操作" width="150" sortable="true"></flex:flexCol>
 </flex:flexgrid>
 </div>
 </div>
 </div>
 <form id="groupMemberForm">
<div class="formLayout" >
 <div class="partContainers operationLog"><h5 id="buildGroup"><span class="switch left11">组建项目组成员</span></h5></div>
 <div id="buildGroupDetail">
    <ul>
      <li>
	      <label for="input01">成员类别</label>
	      <input type="radio" name="groupType" checked="checked"  value="领导小组"/>项目领导小组  <input type="radio" name="groupType"  value="工作小组"/>项目工作小组
	      <input type="hidden" name="objId" id="objId">
      </li>
       <li>
	       <label for="input01">级别</label>
	       <input type="radio" name="memberTitle" checked="checked"  value="组长"/>组长  <input type="radio" name="memberTitle"  value="成员"/>成员
       </li>
       <li>
       		<label for="input01">姓名</label>
       		<input type="text" name="name" class="required" />
            <em>*</em>
       </li>
       <li>
       		<label for="input01">职务</label>
       		<input type="text" name="duty" class="required"/>
            <em>*</em>
       </li>
       <li>
       		<label for="input01">处室</label>
       		<input type="text" name="dept"/>
       </li>
       <li>
       		<label for="input01">电话</label>
       		<input type="text" name="tel" class="required cnPhone"/>
            <em>*</em>
       </li>
       <li>
       		<label for="input01">所在单位</label>
       		<input type="text" name="orgName" class="required"/>
            <em>*</em>
       </li>
      <div class="operationBtnDiv">
		  <button type="button" id="submitBtn"><span>保存</span></button>    
		  <button class="btn" type="reset" id="taskPlanDetailReset" tabindex="20" ><span>重置</span></button>
	  </div>
     </ul>
     </div>
</div>
</form>