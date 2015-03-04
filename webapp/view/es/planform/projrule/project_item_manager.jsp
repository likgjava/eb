<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/projrule/project_item_manager.js"></script>
 <div class="formLayout">

 <span style="margin-left:5px;">当前位置：编制文件 >> 项目规则 >> 项目拆分</span>
 
 <div class="partContainers operationLog"><h5 id="PackageSet"><span class="switch  left11">项目拆分</span></h5></div>
 <div id="projectItemList"></div>
 
 </div>


 <div class="functionBtnDiv" style="text-align:center;">
	    <button type="submit" id="lastStep" style="width:70px"><span>上一步</span></button>
	    <button type="submit" id="nextStep" style="width:70px"><span>下一步</span></button>
 </div>
<div id="historyDiv"></div>