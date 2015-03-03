<#--
-->
<#assign count=0/> 
<script type="text/javascript">
var width=${flashWidth};
var focus_height=${flashHeight};
var text_height=20;
var swf_height = focus_height+text_height;
var pics='';
var links='';
var texts='';
<#list list as l>
<#if l.imgUrl?? && l.imgUrl?length gt 0 && count lt 6>
pics+='${l.imgUrl!}|';
//links+='javascript:void(0)|';

texts+='${l.getTit(titLen)}|';

<#assign count = count+1/>
</#if>
</#list>
pics=pics.substring(0,pics.length-1);
//links=links.substring(0,links.length-1);
texts=texts.substring(0,texts.length-1);
document.write('<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,19,0" width="'+width+'" height="'+swf_height+'">');
document.write('<param name="movie" value="${resbase}/flash/focus1.swf"/>');
document.write('<param name="quality" value="high"/><param name="bgcolor" value="#F0F0F0"/>');
document.write('<param name="menu" value="false"/><param name="wmode" value="opaque"/>');
document.write('<param name="FlashVars" value="pics='+pics+'&links='+links+'&texts='+texts+'&borderwidth='+width+'&borderheight='+focus_height+'&textheight='+text_height+'"/>');
document.write('<embed src="${resbase}/flash/focus1.swf" width="'+width+'" height="'+swf_height+'" FlashVars="pics='+pics+'&links='+links+'&texts='+texts+'&borderwidth='+width+'&borderheight='+focus_height+'&textheight='+text_height+'" menu="false" wmode="opaque" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash"></embed>');
document.write('</object>');
</script>
