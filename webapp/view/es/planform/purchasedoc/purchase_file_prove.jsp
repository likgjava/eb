<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/purchasedoc/purchase_file_prove.js"></script>

<form id="" method="post">
<div class="formLayout ">
<span>当前位置：编制文件 >> <dm:out value="local__PURDOC" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">招标文件</dm:out> >> <dm:out value="local__PURDOC" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">招标文件</dm:out>论证</span>
<div class="partContainers operationLog"><h5 id="expertInfo"><span class="switch left11">论证小组(专家信息)</span></h5></div>
<div id="expertInfoDetail"></div>
 
<div class="partContainers operationLog"><h5 id="relateItem"><span class="switch left11">关联附件</span></h5></div>
<div id="relateItemDetail"></div>

<div class="partContainers operationLog"><h5 id="reviewCondition"><span class="switch left11">文件论证</span></h5></div>
<div id="reviewConditionDetail"></div>
	
</div>
</form>
 <div class="functionBtnDiv" style="text-align:center;">
	    <button type="submit" id="lastStep" style="width:70px"><span>上一步</span></button>
	    <button type="submit" id="nextStep" style="width:70px"><span>下一步</span></button>
 </div>
<div id="historyDiv"></div>