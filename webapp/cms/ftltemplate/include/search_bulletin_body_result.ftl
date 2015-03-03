<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script language="javascript" type="text/javascript">
$(function(){
	$("[name^=searchPage]").click(function(){
		$("#conBody").loadPage($('#initPath').val()+'/BulletinController.do?method=searchBulletin&searchKey='+strIgnore($('#_searchKey').val())+'&pageSize='+$('#pageSize').val()+'&bullType='+$('#_bullType').val()+'&startTime='+$('#startTime').val()+'&endTime='+$('#endTime').val()+'&bulletinSrc='+$('#_bulletinSrc').val()+'&searchResultForm='+$('#_searchResultForm').val()+'&curPage='+this.id);
	})
	$("#qualityTable  tr").find("a").click(function() {
		$("#contentMain").loadPage($('#initPath').val()+"/BulletinController.do?method=toShowView&objId="+$(this).attr("id"));
	})
})
</script>
 <table class="frontTableList" id="qualityTable">
      <thead>
        <tr>
          <th class="left" style="width:500px">公告标题</th>
          <th class="left" style="width:100px">公告来源</th>
          <th class="right">时间</th>
        </tr>
      </thead>
       <#if searList?size!=0>
        <#list searList as l>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0)" id="${l.objId!}" class="cmsHref_self" title="${l.bullTitle!}">${l.bullTitle!}</a></td>
          <td style="text-align:left"><#if l.srcApp??>${l.srcApp!}</#if></td>
          <td style="text-align:center"><#if l.relDate??>${l.relDate?string('yyyy-MM-dd')}</#if></td>
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
			<span class="next paginate_button" name="searchPage_first" id="${firstPage}">首页</span>
			<span class="previous paginate_button" name="searchPage_pre" id="${prePage}">上一页</span>
		</#if>
		
		<#if hasNextPage>
			<span class="next paginate_button" name="searchPage_next" id="${nextPage}">下一页</span>
			<span class="last paginate_button" name="searchPage_last" id="${lastPage}">最后页</span>
		</#if>
		
		<span class="totalNum">页次：${curPage}/${totalPage} &nbsp;每页${pageSize}条 </span>
		<input id="pageSize" name="pageSize" type="hidden"  value="${pageSize}"/>
		<input id="_searchKey" name="searchKey" type="hidden"  value="${searchKey!}"/>
		<input id="_bullType" name="bullType" type="hidden"  value="${bullType!}"/>
		<input id="_startTime" name="startTime" type="hidden"  value="${startTime!}"/>
		<input id="_endTime" name="endTime" type="hidden"  value="${endTime!}"/>
		<input id="_bulletinSrc" name="bulletinSrc" type="hidden"  value="${bulletinSrc!}"/>
		<input id="_searchResultForm" name="searchResultForm" type="hidden"  value="${searchResultForm!}"/>
 	</div>
      
  
