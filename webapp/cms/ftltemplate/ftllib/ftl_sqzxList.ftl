<#--
-->

<#list list as l>

<li><a href="javascript:void(0)" id="${l.url!}" class="sq_index" title="${l.title!}">[公告]${l.getTit(titLen)}</a><#if dataFormat != '' ><span class="date">${(l.date)!?string(dataFormat)}</span></#if></li>
</#list>