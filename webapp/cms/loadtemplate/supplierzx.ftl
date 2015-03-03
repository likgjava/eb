<%@ page pageEncoding="UTF-8"%>

<div class="cols supplierInfo">
	<h2>供应商最新资讯</h2>
	<ul class="newsList">
		<#if supplierzx?? && supplierzx?size != 0>
		<#list supplierzx as l>
			<li <#if (!l_has_next)>class="last"</#if>><a href="javascript:void(0)" id="${l.url!}" class="cmsHref_blank">${l.getTit(15)}</a></li>
		</#list>
		</#if>
	</ul>
	<div class="more"><a href="javascript:void(0);" id="${supplierzx_index.url!}" class="cmsHref_blank right">更多</a></div>
</div>