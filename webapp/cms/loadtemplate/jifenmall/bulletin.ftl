<%@ page pageEncoding="UTF-8"%>
<h2>商城公告</h2>
            <ul>
            	<#if jfsc?? && jfsc?size != 0>
	            	<#list jfsc as l>
	              		<li><a href="javascript:void(0)" id="${l.url!}" class="cmsHref_index" title="${l.title!}"><#if l.title?length lt 17>${l.title!}<#else>${l.title[0..16]}...</#if></a></li>
	              	</#list>
              	</#if>
            </ul>

            
