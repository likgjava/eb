
<#macro voteTopic vote='' >
<#if vote!=''>
 <#include "/ftllib/vote.ftl"/>
 <#else>
 
 当前没有指定的投票主题！
</#if>
</#macro>
