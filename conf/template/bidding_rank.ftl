<?xml version='1.0' encoding='UTF-8'?>
<chart chartLeftMargin='25' chartRightMargin='25' chartTopMargin='1' chartBottomMargin='1' 
	showBorder='0' bgColor='FFFCED' bgAlpha='100' baseFont='Arial' baseFontSize='12' showValues='0' 
	caption='供应商报价排名' decimalPrecision='0' formatNumberScale='0' numvdivlines='3' 
	divLineIsDashed='1' showDivLineValue='0' showLimits='0' yAxisMaxValue='${maxPrice!}' yAxisMinValue='${minPrice!}'>
	
	<categories>
		<#list rankList as rank >
			<#if rank[0]! == supplierId>
				<category name='我的报价'/>
			<#else>
				<category name='供应商${rank_index+1}'/>
			</#if>
		</#list>
	</categories>
	<dataset color='8BBA00'>
		<#list rankList as rank >
			<#if rank_index == 0>
				<set value='${rank[1]!}' hoverText='<#if rank[0]! == supplierId>我的报价:￥${rank[1]!}<#else>供应商${rank_index+1}</#if>' showValue='<#if showMinPrice == "1">1<#else>0</#if>' />
			<#elseif (!rank_has_next)>
				<set value='${rank[1]!}' hoverText='<#if rank[0]! == supplierId>我的报价:￥${rank[1]!}<#else>供应商${rank_index+1}</#if>' showValue='<#if showMaxPrice == "1">1<#else>0</#if>' />
			<#else>
				<set value='${rank[1]!}' hoverText='<#if rank[0]! == supplierId>我的报价:￥${rank[1]!}<#else>供应商${rank_index+1}</#if>' />
			</#if>
		</#list>
	</dataset>
</chart>