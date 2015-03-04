<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/view/es/planform/taskplan/agentInfo.js"></script>
<div class="partContainers">
<form id="AgencyForm">
<div class="formLayout form2Pa detail">
<h5><span>招标中心信息</span></h5>
<ul id="TaskPlanUl">
	<li>
		<label class="short"  for="agentName">招标中心名称：</label>
		<span id="purchaseName">${agent.orgInfo.orgName}</span>
	</li>
	<li>
		<label class="short"  for="regPrctsNmbr">资质：</label>
		<span id="unitDesc">${agent.unitDesc}</span>
	</li>
	<li>
		<label class="short"  for="regPrctsNmbr">从业人数：</label>
		<span id="regPrctsNmbr">${agent.regPrctsNmbr}</span>
	</li>
	<li>
		<label class="short"  for="regCapital">注册资金：</label>
		<span id="regCapital">${agent.regCapital}</span>
	</li>
	<li>
		<label class="short"  for="regCoporate">注册法人：</label>
		<span id="regCoporate">${agent.regCoporate}</span>
	</li>
	<li>
		<label class="short"  for="webUrl">联系地址：</label>
		<span id="webUrl">${agent.orgInfo.company.mobilePhone}</span>
	</li>
</ul>
</div>
</form>
</div>