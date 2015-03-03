<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

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
   <div class="frameNews">
    <h1>${news.title}</h1>
    <div class="subtitle"></div>
       <div class="source">
	      <span>发布时间：${(news.date)!?string("yyyy-MM-dd")}</span>
	      <span>发布人：${news.author!}  </span>
      </div>
      <#if news.imgUrl??>
      <p><img src="${base}/${news.imgUrl}" width="200px;" height="200px;"></p>
      </#if>
      ${news.origin!}
      <p>${content!}</p>
      <#if news.fileUrl??>
      <p><a href="${base}/${news.fileUrl}" target="_blank">点击下载附件</a></p>
      </#if>
   </div>
   
 </div>

