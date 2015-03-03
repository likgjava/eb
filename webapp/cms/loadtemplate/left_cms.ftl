<%@ page pageEncoding="UTF-8"%>
<div class="hasBorder">
<ul>
  <li class="selected">
	<a href="javascript:void(0)"  class="icon1"><span>${notice_index.name}</span></a>
	<ul class="subnav">
		<li class="" id="${notice_index.objId}"><a href="javascript:void(0)" class="cmsHref_self" id="${notice_index.url!}" >${notice_index.name}</a></li>
	</ul>
  </li>
  
  <li class="selected">
	<a href="javascript:void(0)"  class="icon1"><span>网站资讯</span></a>
	<ul class="subnav">
		<li class="" id="${cgxqzx_index.objId}"><a href="javascript:void(0)" class="cmsHref_self" id="${cgxqzx_index.url!}" >${cgxqzx_index.name}</a></li>
		<li class="" id="${goodszx_index.objId}"><a href="javascript:void(0)" class="cmsHref_self" id="${goodszx_index.url!}" >${goodszx_index.name}</a></li>
		<li class="" id="${supplierzx_index.objId}"><a href="javascript:void(0)" class="cmsHref_self" id="${supplierzx_index.url!}" >${supplierzx_index.name}</a></li>
	</ul>
  </li>

<#if customer?? && customer?size != 0>
  <li class="selected">
	<a href="javascript:void(0)"  class="icon1"><span><#if customer[0].parent??>${customer[0].parent.name}</#if></span></a>
	<ul class="subnav">
	<#list customer as l>
		<#if l.display> </#if>
		<li class="" id="${l.objId}"><a href="http://www.ebid360.com${l.url!}" >${l.name}</a></li>
	</#list>
	</ul>
  </li>
</#if>
</ul>
</div>