<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<style>.frameNews p{font-size:12px;}</style>
<script language="javascript" type="text/javascript">
$(document).ready(function(){

	var id = $("#channelId").val();
	if(null != $('.subnav>li[id='+id+']').html()) {
		$('.subnav>li').removeClass('selected');
		$('.subnav>li[id='+id+']').addClass('selected');
	}
})
</script>

 <div id="conTitle">
 	<div class="navCurrent ">
 	<@f.position/>
 	</div>
 
 </div>
 
<div id="conBody">

   <input type="hidden"  id="channelId" value="${news.channel.objId}" />
   <div class="frameNews" style="overflow:hidden;">
    <h1>${news.str2} -- ${news.title}</h1>
    <div class="subtitle"></div>
       <div class="source">
	      <span>发布时间：${(news.date)!?string("yyyy-MM-dd")}</span>
	      <span>发布人：${news.author!}</span>
      </div>
      <#if news.str3??>
      <p style="float: left; margin-right:20px;">
	      <embed width="320" height="267" 
			pluginspage="http://www.macromedia.com/go/getflashplayer" 
			flashvars="isShowRelatedVideo=false&amp;VideoIDS=63298273&amp;isAutoPlay=false&amp;isDebug=false&amp;UserID=0&amp;RecordCode=1001,1002,1003,1004,1005,1006,2001,3001,3002,3003,3004,3005,3007,3008,9999&amp;RecordResource=index&amp;isLoop=false&amp;imglogo=http://g3.ykimg.com/1130391F464D8A9176459803C5DAE1E3578090-D208-AFE6-374B-FF70CCA22889?u=1300926839&amp;winType=index&amp;playMovie=true&amp;MMControl=true&amp;MMout=true" 
			wmode="transparent" quality="high" bgcolor="#FFFFFF" name="index_player_swf" id="index_player_swf" 
			src="${news.str3!}" 
		type="application/x-shockwave-flash">
	  </p>
      <#elseif news.imgUrl??>
      <p style="float: left; margin-right:20px;"><img src="${base}/${news.imgUrl}" width="150px;" height="150px;"></p>
      </#if>
      
      <p style="float:left; ">${content!}</p>
      <#if news.fileUrl??>
      <p><a href="${base}/${news.fileUrl}" target="_blank">下载 ${news.str4!}</a></p>
      </#if>
      <#if news.file2Url??>
      <p><a href="${base}/${news.file2Url}" target="_blank">下载 ${news.str5!}</a></p>
      </#if>
      <#if news.str7??>
      <p><a href="${base}/${news.str7}" target="_blank">下载 ${news.str6!}</a></p>
      </#if>
      <#if news.str9??>
      <p><a href="${base}/${news.str9}" target="_blank">下载 ${news.str8!}</a></p>
      </#if>
   </div>
   
 </div>

