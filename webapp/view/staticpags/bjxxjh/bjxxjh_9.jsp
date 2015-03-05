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
		$("#conBody").loadPage($('#initPath').val()+'/CmsNewsController.do?method=search&chnlCodes=bjxxjh&searchKey='+strIgnore($('#searchKeyContentMain').val())+'&pageSize=20&startTime='+$('#startTime').val()+'&endTime='+$('#endTime').val()+'&searchResultForm=search_news_body_result.ftl');
	});
	$("#startTime").epsDatepicker();
     $("#endTime").epsDatepicker();
})
</script>
 <div id="conTitle">
 	<div class="navCurrent ">

	<a href="/view/srplatform/portal/index.jsp" id="/view/srplatform/portal/index.jsp" title="首页" target="_self">首页</a>
	<a href="javascript:void(0)" id="/view/staticpags/zzbjyctwh/zzbjyctwh.jsp" title="诸子百家与传统文化" class="cmsHref_self">诸子百家与传统文化</a>
	<a href="javascript:void(0)" id="/view/staticpags/bjxxjh/bjxxjh.jsp" title="百家思想精华" class="cmsHref_self">百家思想精华</a>
		
 	</div>
  <div class="formTips attention">
	<ul><li><em>百家思想精华展示：</em></li></ul>
 </div>	
 <div class="simpleSearch">
   <ul>
	<li><label> 关键字：</label>
		<input type="text" name="searchKeyContentMain" id="searchKeyContentMain" value="" style="width:120px"/>
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
 <input type="hidden"  id="channelId" value="ff8080812ed7f020012ee0caa13d09a3" />
 <table class="frontTableList" id="qualityTable">
      <thead>
        <tr>
          <th class="left">标题</th>
          <th class="center">时间</th>
        </tr>
      </thead>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812ee243a4012eea7bbcaa1a2f.jsp" class="toContentMain" title="晋商商业道德的内涵">晋商商业道德的内涵</a></td>
          <td style="text-align:right">2011-03-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812ee243a4012ee6a457d515c1.jsp" class="toContentMain" title="孔子、孟子和荀子的富民思想简论">孔子、孟子和荀子的富民思想简论</a></td>
          <td style="text-align:right">2011-03-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812ee243a4012ee67ec1e314d6.jsp" class="toContentMain" title="晋商精神与传统文化">晋商精神与传统文化</a></td>
          <td style="text-align:right">2011-03-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812ee243a4012ee6484d151352.jsp" class="toContentMain" title="荀子的商业经济思想">荀子的商业经济思想</a></td>
          <td style="text-align:right">2011-03-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812ee243a4012ee64503e01333.jsp" class="toContentMain" title="道家文化中管理思想的探索">道家文化中管理思想的探索</a></td>
          <td style="text-align:right">2011-03-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812ee243a4012ee282ddfc01d7.jsp" class="toContentMain" title="法家这么说">法家这么说</a></td>
          <td style="text-align:right">2011-03-23</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/bjxxjh/bjxxjh.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/bjxxjh/bjxxjh_8.jsp" title="上一页">上一页</span> 
<span class="totalNum">页次：9/9 每页20条 </span>
</div>		
      </div>
      
  </div>
	
