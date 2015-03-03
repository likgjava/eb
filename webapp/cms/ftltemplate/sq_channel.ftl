<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script language="javascript" type="text/javascript">
$(document).ready(function(){
$("#contentMain").addClass("sqindex3pa");
	// 为左边栏设定选中样式,判断用于解决点击左边栏后样式被覆盖问题
	var id = $("#channelId").val();
	if(null != $('.subnav>li[id='+id+']').html()) {
		$('.subnav>li').removeClass('selected');
		$('.subnav>li[id='+id+']').addClass('selected');
	}
	$('#searchNewsContentMainBtn').click(function() {
		$("#conBody").loadPage($('#initPath').val()+'/CmsNewsController.do?method=search&chnlCodes=${chnl.chnlCode}&searchKey='+strIgnore($('#searchKeyContentMain').val())+'&pageSize=20&startTime='+$('#startTime').val()+'&endTime='+$('#endTime').val()+'&searchResultForm=search_news_body_result.ftl');
	});
	$("#startTime").epsDatepicker();
     $("#endTime").epsDatepicker();
})
</script>
 <div id="conTitle">
 	<div class="navCurrent ">
 	<@f.position/>
 	</div>
  <div class="formTips attention">
	<ul><li><em>${chnl.name!}展示：</em></li></ul>
 </div>	
 <div class="simpleSearch">
   <ul>
	<li><label> 关键字：</label>
		<input type="text" name="searchKeyContentMain" id="searchKeyContentMain" value="${searchKey!}" style="width:120px"/>
	</li>
	<li><label> 发布时间：</label>
		<input type="text" name="startTime" id="startTime" readonly="readonly" style="width:80px"/>&nbsp;至
		<input type="text" name="endTime" id="endTime" readonly="readonly" style="width:80px"/>
	</li>
	<li><button type="button" id="searchNewsContentMainBtn"><span>搜索</span></button></li>
  </ul>
</div>
 </div>
 
 <div id="conBody">
 <input type="hidden"  id="channelId" value="${chnl.objId}" />
 <table class="frontTableList" id="qualityTable">
      <thead>
        <tr>
          <th class="left">标题</th>
          <th class="center">时间</th>
        </tr>
      </thead>
      <#if list?? && list?size!=0>
       <#list list as l>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="${l.url!}" class="toContentMain" title="${l.title!}">${l.title!}</a></td>
          <td style="text-align:right">${l.date?string('yyyy-MM-dd')}</td>
        </tr>
	       </#list>
       <#else>
       <tr>
      <td colSpan="2">没有检索到记录！</td>
    </tr>
       </#if>
  </table>
      <div class="flipPage">
      <#if list?size!=0>
      <@f.chnlPage/>
      </#if>
      </div>
      
  </div>
	
