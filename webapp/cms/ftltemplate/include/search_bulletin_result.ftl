<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script language="javascript" type="text/javascript">
$(function(){
	$("[name^=searchPage]").click(function(){
		$("#contentMain").loadPage($('#initPath').val()+'/BulletinController.do?method=searchBulletin&searchKey='+strIgnore($('#_searchKey').val())+'&pageSize='+$('#pageSize').val()+'&bullType='+$('#_bullType').val()+'&startTime='+$('#startTime').val()+'&endTime='+$('#endTime').val()+'&bulletinSrc='+$('#_bulletinSrc').val()+'&searchResultForm='+$('#_searchResultForm').val()+'&curPage='+this.id);
	})
	$("#qualityTable  tr").find("a").click(function() {
		$("#contentMain").loadPage($('#initPath').val()+"/BulletinController.do?method=toShowView&objId="+$(this).attr("id"));
	})
	 // 搜索
    $('#searchNewsContentMainBtn').click(function() {
    	$("#conBody").loadPage($('#initPath').val()+'/BulletinController.do?method=searchBulletin&searchKey='+strIgnore($('#searchKeyContentMain').val())+'&pageSize=20&bullType='+$('#bullType').val()+'&startTime='+$('#startTime').val()+'&endTime='+$('#endTime').val()+'&bulletinSrc='+$('#bulletinSrc').val()+'&searchResultForm=search_bulletin_body_result.ftl');
    });
    $("#startTime").epsDatepicker();
    $("#endTime").epsDatepicker();
})
</script>
<div id="conTitle">
 	<div class="navCurrent ">
 	<@f.position/><span id="resultType">公告、公示搜索结果</span>
 	</div>
  <div class="formTips attention">
	<ul><li><em>公告、公示展示：</em>汇聚多平台采购公告、公示信息，方便快捷，一目了然</li></ul>
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
	<li><label> 公告来源：</label>
		<select name="bulletinSrc" id="bulletinSrc" style="width:100px">
		<option value="">--请选择--</option>
		</select>
	</li>
	<li><button type="button" id="searchNewsContentMainBtn"><span>搜索</span></button></li>
  </ul>
</div>

 </div>
 
 <div id="conBody">
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
      
 </div>
  
