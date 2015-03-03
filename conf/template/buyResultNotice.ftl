<p style="text-align: center;"><span style="text-decoration: underline;"><font size="4">${title}</font><br /></span></p>
<p><span style="text-decoration: underline;">${seller}</span>，你好！</p>
<p>你在项目<strong>${project.projName}</strong>【${project.projCode}】标段<strong>${subProject.projName}</strong>的投标结果为<strong>${result}</strong>，请知悉。</p>

<#if isDeal=='YES'>
<table  style="WIDTH: 562px; HEIGHT: 45px" class=MsoNormalTable border=1 cellspacing=0 cellpadding=0 border-collapse:collapse;border:none;>
	<tbody>
	<tr><th style="text-align: center">采购人</th><th style="text-align: center">采购品目</th><th style="text-align: center">采购数量</th></tr>
	<#list projectMTaskPlanSubList as subTask>
	<tr><td>${subTask.buyMainBody.orgName}<#if subproject=='false'>${subTask.taskPlanSub.budgetName}</#if></td><td>${subTask.taskPlanSub.purchaseName}</td><td align="right">${subTask.quantity}<#if subproject=='false'>${subTask.taskPlanSub.quantity}</#if></td></tr>
	</#list>
	</tbody>
</table>
</#if>
<p style="text-align: right;">代理机构：${agentName}<br /></p>
