<%@ page pageEncoding="UTF-8"%>
<h3>焦点咨询</h3>
<ul>
	<#if cgxqzx?? && cgxqzx?size != 0>
		<#list cgxqzx as l>
			<li><a href="javascript:void(0)" id="${l.url!}" class="cmsHref_blank">${l.getTit(14)}</a><span class="date">${(l.date)!?string("MM-dd")}</span></li>
		</#list>
	</#if>
</ul>
<span class="more"><a href="javascript:void(0);" id="${cgxqzx_index.url!}" class="cmsHref_blank right">更多</a></span>
