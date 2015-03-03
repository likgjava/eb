<#list list as l>
<li><span>[${l.channel.name}]<a href="javascript:void(0)" id="${l.url!}" class="cmsHref_self" title="${l.title!}">${l.getTit(titLen)}</a></span></li>
</#list>