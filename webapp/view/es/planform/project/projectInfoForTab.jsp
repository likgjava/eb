<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/projectInfoForTab.js"></script>
<input type="hidden" name="project" id="project_Ids" value=""/>
<input type="hidden" name="projectName" id="project_Names" value=""/>
<input type="hidden" name="cur_selectId" id="cur_selectId" value=""/><!-- 记录当前TAB页被选中的ID -->
<div id="tabsHeaderContent" class="otherTabs">
<ul id="projectTab">
	<li id="tab${project.objId}" >
		<a href="#" id="${project.objId}" onclick="getProjectByProjectId('${project.objId}')">
			<span>${project.tenderTypeCN}</span>
		</a>
		<button class="sysicon siExit" onclick="removeProjectTab('${project.objId}','${project.tenderTypeCN}');"> </button>
	</li>
</ul>
<div class="functionBtnDiv styleR otherPoject">
      <button id="moreProject"  type="button"><span>其他项目</span></button>
    </div>	
<div id="project_div" ></div>
</div>












