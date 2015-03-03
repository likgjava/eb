<%@ page pageEncoding="UTF-8"%>
<h2>商品库最新资讯</h2>
<ul class="newsList">
	<#if goodszx?? && goodszx?size != 0>
		<#list goodszx as l>
			<li>
				<a title="${l.title!}" href="javascript:void(0)" id="${l.url!}" class="cmsHref_blank">
			  	<#if (l.title?length) gt 12 >
			  	  ${l.title?substring(0,11)}…
			  	<#else>
			  	  ${l.title!}
			  	</#if>
				</a>
				<span class="date">${(l.date)!?string("MM-dd")}</span>
			</li>
		</#list>
	</#if>
</ul>
<div class="more"><a href="javascript:void(0);" id="${goodszx_index.url!}" class="cmsHref_blank right">更多</a></div>