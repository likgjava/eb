<#--
-->

<#list list as l>
<li><a href="javascript:void(0)" onclick="this.href='${base}/${l.fileUrl!}'">${l.getTit(titLen)}</a></li>
</#list>