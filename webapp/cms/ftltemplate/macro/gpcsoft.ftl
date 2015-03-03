<#ftl>
<#macro gpcsoft list='' ftl='' titLen=10 dataFormat='' flashWidth='' flashHeight=''>
	<#if list?? && list?size!=0>
			<@queryList list=list titLen=titLen dataFormat=dataFormat ftl='/ftllib/ftl_${ftl}.ftl' flashWidth=flashWidth flashHeight=flashHeight/>
	</#if>

</#macro>
