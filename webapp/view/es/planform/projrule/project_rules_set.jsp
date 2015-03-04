<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/projrule/project_rules_set.js"></script>
<form id="projectRulesForm">
	<div class="formLayout">
	<span>当前位置：编制文件 >> 项目规则 >> 设置项目规则</span>
	 <div class="partContainers operationLog"><h5 id="projectRuleSet"><span class="switch  left11">设置项目规则</span></h5>	</div>
	 <div id="projectRulesContent"></div>
	     
	</div>
</form>
 <div class="functionBtnDiv" style="text-align:center;">
	    <button type="submit" id="lastStep" style="width:70px"><span>上一步</span></button>
	    <button type="submit" id="nextStep" style="width:70px"><span>下一步</span></button>
 </div>
<div id="historyDiv"></div>