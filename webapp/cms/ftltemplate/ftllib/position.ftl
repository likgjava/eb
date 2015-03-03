
<#--
当前位置
-->
<#list positionList as l>
<#if l_index == 0>

	<#--
	如果是首页 
	-->
	<a href="${l.url!}" id="${l.url!}" title="${l.title!}" target="_self">${l.title!}</a>
<#else>
	<a href="javascript:void(0)" id="${l.url!}" title="${l.title!}" class="cmsHref_self">${l.title!}</a>
</#if>
</#list>