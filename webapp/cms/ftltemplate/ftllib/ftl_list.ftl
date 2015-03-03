<#--
-->

<#list list as l>

<li><a href="javascript:void(0)" id="${l.url!}" class="cmsHref_self" title="${l.title!}">${l.getTit(titLen)}</a><#if dataFormat != '' ><span class="right">${(l.date)!?string(dataFormat)}</span></#if></li>
</#list>