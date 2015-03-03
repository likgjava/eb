<link rel="stylesheet" type="text/css" href="${base}/view/resource/skin/smallscale/css/shangquan.css"/>
<style>
.shangquanLeft li li.selected {
    background: none repeat scroll 0 0 #FFFFE3
</style>
<ul>

<#if sq?? && sq?size != 0>
<#list sq as l>
 <#if l.subChannel?size gt 0>
  <li class="selected">
	<a href="javascript:void(0)"  class="icon1"><span>${l.name}</span></a>
	<ul class="subnav">
		<#list l.subChannel as s>
		 <li class="" id="${s.objId}"><a href="javascript:void(0)" class="toContentMain" id="${s.url!}" >${s.name}</a></li>
		</#list>
	</ul>
  </li>
 <#else>
 <li class="selected">
	<a href="javascript:void(0)"  class="icon1"><span>${l.name}</span></a>
	<ul class="subnav">
		 <li class="" id="${l.objId}"><a href="javascript:void(0)" class="toContentMain" id="${l.url!}" >${l.name}</a></li>
	</ul>
  </li>
 </#if>

</#list>
</#if>

<#--
  <li class="selected">
	<a href="javascript:void(0)"  class="icon1"><span>${notice_index.name}</span></a>
	<ul class="subnav">
		<li class="" id="${notice_index.objId}"><a href="javascript:void(0)" class="toContentMain" id="${notice_index.url!}" >${notice_index.name}</a></li>
	</ul>
  </li>

<#if customer?? && customer?size != 0>
  <li class="selected">
	<a href="javascript:void(0)"  class="icon1"><span><#if customer[0].parent??>${customer[0].parent.name}</#if></span></a>
	<ul class="subnav">
	<#list customer as l>
		<#if l.display> </#if>
		<li class="" id="${l.objId}"><a href="javascript:void(0)" class="toContentMain" id="${l.url!}" >${l.name}</a></li>
	</#list>
	</ul>
  </li>
</#if>
-->
</ul>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
              $("#contentSub").removeClass().addClass("contentSub").addClass("shangquanLeft");
})
</script>
