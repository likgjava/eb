<style>
.picNews{ float:left; width:316px; height:200px; margin-right:8px;overflow:hidden; display:inline; position:relative;}
.picNews dl{ background:#fff;margin-top:0;margin-left:0;padding-top:0;padding-left:0}
.picNews dt img{ width:316px; height:200px; margin-bottom:2px; display:block;}
.picNews dt span{background:none repeat scroll 0 0 #FFFFFF;bottom:-180px;display:block;font-size:13px;line-height:35px;position:absolute;width:316px;filter:alpha(opacity=70); -moz-opacity:0.7; opacity:0.7;text-align:center}
.picNews dt a {color:#369;font-size:12px;}
.picNews dt a:hover{ color:#369;}
.info{ position:absolute; left:0; top:0;}
.info div{ width:316px; margin-right:0px; float:left;}
.info div.hide{ display:none;}
.picNewsSmallShow{ float:left; width:60px; height:200px; }
.picNewsSmallShow ul{margin-top:0;margin-left:0;padding-top:0;padding-left:0}
.picNewsSmallShow li{filter:alpha(opacity=40); -moz-opacity:0.4; opacity:0.4;}
.picNewsSmallShow .cur{list-style: none outside none;filter:alpha(opacity=100); -moz-opacity:1; opacity:1;}
</style>
<div class="picNews"> 
	<div class="info" id="info"> 
	<#assign count=0/> 
	<#list list as l>
		<#if l.imgUrl?? && l.imgUrl?length gt 0 && count lt 5>
			<div><dl><dt><a href="javascript:void(0)" id="${l.url!}" class="cmsHref_self"><img src="${l.imgUrl!}" /><span style="color:#000">${l.getTit(titLen)}</span></a></dt></dl></div> 
		</#if>
	</#list>
	</div>            
</div> 
<div class="picNewsSmallShow"> 
	<ul id="list"> 
	<#list list as l>
		<#if l.imgUrl?? && l.imgUrl?length gt 0 && count lt 5>
			<#if l_index == 0>
				<li class="cur" style="list-style:none outside none;background:#fff"><img src="${l.imgUrl!}" width="80px" height="45px"/></li>
			<#else>
				<li style="list-style:none outside none;background:#fff"><img src="${l.imgUrl!}" width="80px" height="45px"/></li>
			</#if>
			<#assign count = count+1/>
		</#if>
	</#list>
	</ul>
</div> 

