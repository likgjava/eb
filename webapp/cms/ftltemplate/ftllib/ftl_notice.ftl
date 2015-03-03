<#--
首页的公告
-->

<#list list as l>
	<#if l_index == 0>
		<div class="hotAreaNews">
		    <h3>
		    	<a href="javascript:void(0)" id="${l.url!}" class="cmsHref_blank" title="${l.title!}">
		    		<#if l.title?length gt 9 >${l.title[0..8]}…<#else>${l.title!}</#if>
		    	</a>
		    </h3>
		    <p class="hotImg"><img style="width: 60px; height: 60px;" src="/${l.imgUrl!}"/></p> 
		    <p class="hotDetails">
		    	<#if l.description??>
			    	<#if l.description?length gt 20 >${l.description[0..19]}…<#else>${l.description!}</#if>
				</#if>
		    </p>
		</div>
	</#if>
</#list>
<ul class="newsList">
	<#list list as l>
		<#if l_index gt 0>
			<li><a href="javascript:void(0)" id="${l.url!}" class="cmsHref_blank" title="${l.title!}">${l.getTit(titLen)}</a><#if dataFormat != '' ><span class="date">${(l.date)!?string(dataFormat)}</span></#if></li>
		</#if>
	</#list>
</ul>
<div class="more"><a href="javascript:void(0);" id="${notice_index.url!}" class="cmsHref_blank right">更多</a></div>
