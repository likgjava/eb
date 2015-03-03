<#ftl>

<#macro queryList list='' titLen=10 dataFormat='' ftl='' flashWidth='' flashHeight=''>
	<#if ftl!='' && list?size gt 0>
		<#include "${ftl}"/>
	</#if>
</#macro>
