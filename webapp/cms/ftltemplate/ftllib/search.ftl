
<#--
当前位置
-->
<ul>
<#if searList??>
<#list searList as l>
<li>

<a href="${l.url!}" title="${l.title!}">${l.title!}</a>
</li>
</#list>
</#if>
</ul>