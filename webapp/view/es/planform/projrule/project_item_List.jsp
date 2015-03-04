<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/projrule/project_item_List.js"></script>

<flex:flexgrid id="itemGrid" rp="10" title="项目拆分信息" usepager="true" showTableToggleBtn="true" url="ProjectPackageController.do?method=showProjectPackage" queryColumns="" onSubmit="project_item_manager.before" onSuccess="project_item_manager.success"  height="100">
	<flex:flexCol name="packCode" display="包号" width="40" sortable="true"></flex:flexCol>
	<flex:flexCol name="packName" display="包名" width="100" sortable="true"></flex:flexCol>
	<flex:flexCol name="packContent" display="采购内容" width="200" sortable="true"></flex:flexCol>
	<flex:flexCol name="remark" display="包组说明" width="200" sortable="true"></flex:flexCol>
	<flex:flexCol name="creator" display="操作员" width="100" sortable="true"></flex:flexCol>
	<flex:flexCol name="createTime" display="创建时间" width="150" sortable="true"></flex:flexCol>
	<flex:flexCol name="options" display="操作" width="150" sortable="true"></flex:flexCol>
 </flex:flexgrid>
 <form id="projectItemForm"  method="post">
	 <div class="partContainers operationLog"><h5 id="PackageInfo"><span class="switch left11">录入拆分信息</span></h5></div>
	 <div id="PackageInfoDetail">
	<ul>
		<li>
			<label>包号</label>
			第<input type="text" id="packCode"  name="packCode"  size="2"  class="required" />包
		    <em>*</em>
		     <input type="hidden" name="objId" id="objId">
		</li>
		<li>
			<label>包名</label>
			<input type="text" id="packName" name="packName"  class="required"/>
		    <em>*</em>
		</li>
		<li>
			<label>创建操作员</label>
			<input type="text" id="creator" name="creator"  class="required"/>
		    <em>*</em>
		</li>
		<li class="formTextarea">
			<label>采购内容</label>
			<textarea  id="packContent" name="packContent" style="width:80%;height:105px;margin-top:3px"></textarea>
		    <em>*</em>
		</li>
		<li class="formTextarea">
			<label><dm:out value="local__package" tenderType="${project.tenderType}">包组</dm:out>说明</label>
			<textarea id="remark"  name="remark" style="width:80%;height:105px;margin-top:3px"></textarea>
		    <em>*</em>
		</li>
		
		<div class="operationBtnDiv">
	  		<button type="button" id="submitBtn"><span>保存</span></button>
	  		<button class="btn" type="reset" id="taskPlanDetailReset" tabindex="20" ><span>重置</span></button>
   		</div>
	</ul>
	</div>
	</form>