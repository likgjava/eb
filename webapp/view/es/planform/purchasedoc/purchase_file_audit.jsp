<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/purchasedoc/purchase_file_audit.js"></script>
<div class="formLayout">
<span>当前位置：编制文件 >> <dm:out value="local__PURDOC" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">招标文件</dm:out> >> 经办部门审核</span>
<div class="partContainers operationLog"><h5 id="taskbook"><span class="switch left11"><dm:out value="local__PURDOC" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">招标文件</dm:out></span></h5></div>

<div id="noticeDiv"></div>

<div class="partContainers operationLog"><h5 id="beginAudit"><span class="switch left11">开始审核</span></h5></div>

<div id ="auditDetail"></div>

</div>
 <div class="functionBtnDiv" style="text-align:center;">
	    <button type="submit" id="lastStep" style="width:70px"><span>上一步</span></button>
	    <button type="submit" id="nextStep" style="width:70px"><span>下一步</span></button>
 </div>
<div id="historyDiv"></div>