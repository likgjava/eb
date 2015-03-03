<#--
-->

<#list list as l>

<li><a href="javascript:void(0)" id="${l.url!}" class="sq_index" title="${l.title!}">${l.getTit(titLen)}</a><#if dataFormat != '' ><span class="right">${(l.date)!?string(dataFormat)}</span></#if></li>
</#list>