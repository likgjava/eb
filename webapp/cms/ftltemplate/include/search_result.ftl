<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script language="javascript" type="text/javascript">
$(function(){

	$("[name^=searchPage]").click(function(){
		$("#contentMain").loadPage("${base}/CmsNewsController.do?method=search&chnlCodes="+$("#_chnlCodes").val()+"&searchKey="+strIgnore($("#_searchKey").val())+"&pageSize="+$("#pageSize").val()+"&curPage="+this.id+"&startTime="+$("#_startTime").val()+"&endTime="+$("#_endTime").val()+"&searchResultForm="+$('#_searchResultForm').val());
	})
	$('#searchNewsContentMainBtn').click(function() {
		$("#conBody").loadPage($('#initPath').val()+'/CmsNewsController.do?method=search&chnlCodes=&searchKey='+strIgnore($('#searchKeyContentMain').val())+'&pageSize=20&startTime='+$('#startTime').val()+'&endTime='+$('#endTime').val()+'&searchResultForm=search_news_body_result.ftl');
	});
	$("#startTime").epsDatepicker();
    $("#endTime").epsDatepicker();
})
</script>
<div id="conTitle">
 	<div class="navCurrent ">
 	<@f.position/> <span id="resultType">采购资讯搜索结果</span>
 	</div>
  <div class="formTips attention">
	<ul><li><em>采购资讯展示：</em>汇聚多平台采购资讯信息，方便快捷，一目了然</li></ul>
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
 <table class="frontTableList" id="qualityTable">
      <thead>
        <tr>
          <th class="left">标题</th>
          <th class="center">时间</th>
        </tr>
      </thead>
       <#if searList?size!=0>
        <#list searList as l>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0)" id="${l.url!}" class="cmsHref_self" title="${l.title!}">${l.title!}</a></td>
          <td style="text-align:center">${l.date?string('yyyy-MM-dd')}</td>
        </tr>
	       </#list>
	       <#else>
	       <tr>
          <td colSpan="2">没有检索到记录！</td>
        </tr>
	       </#if>
  </table>
      <div class="dataTables_paginate paging_full_numbers">

		<#if hasPrePage>
			<span class="next paginate_button" name="searchPage_first" id="${firstPage!}">首页</span>
			<span class="previous paginate_button" name="searchPage_pre" id="${prePage!}">上一页</span>
		</#if>
		
		<#if hasNextPage>
			<span class="next paginate_button" name="searchPage_next" id="${nextPage}">下一页</span>
			<span class="last paginate_button" name="searchPage_last" id="${lastPage}">最后页</span>
		</#if>
		
		<span class="totalNum">页次：${curPage!}/${totalPage!} &nbsp;每页${pageSize!}条 </span>
		<input id="pageSize" name="pageSize" type="hidden"  value="${pageSize!}"/>
		<input id="_searchKey" name="searchKey" type="hidden"  value="${searchKey!}"/>
		<input id="_startTime" name="startTime" type="hidden"  value="${startTime!}"/>
		<input id="_endTime" name="endTime" type="hidden"  value="${endTime!}"/>
		<input id="_chnlCodes" name="chnlCodes" type="hidden"  value="${chnlCodes!}"/>
		<input id="_searchResultForm" name="searchResultForm" type="hidden"  value="${searchResultForm!}"/>
	 </div>
      
  </div>
  
