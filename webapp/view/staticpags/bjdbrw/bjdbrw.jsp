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
		$("#conBody").loadPage($('#initPath').val()+'/CmsNewsController.do?method=search&chnlCodes=bjdbrw&searchKey='+strIgnore($('#searchKeyContentMain').val())+'&pageSize=20&startTime='+$('#startTime').val()+'&endTime='+$('#endTime').val()+'&searchResultForm=search_news_body_result.ftl');
	});
	$("#startTime").epsDatepicker();
     $("#endTime").epsDatepicker();
})
</script>
 <div id="conTitle">
 	<div class="navCurrent ">

	<a href="/view/srplatform/portal/index.jsp" id="/view/srplatform/portal/index.jsp" title="首页" target="_self">首页</a>
	<a href="javascript:void(0)" id="/view/staticpags/zzbjyctwh/zzbjyctwh.jsp" title="诸子百家与传统文化" class="cmsHref_self">诸子百家与传统文化</a>
	<a href="javascript:void(0)" id="/view/staticpags/bjdbrw/bjdbrw.jsp" title="百家代表人物" class="cmsHref_self">百家代表人物</a>
		
 	</div>
  <div class="formTips attention">
	<ul><li><em>百家代表人物展示：</em></li></ul>
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
 <input type="hidden"  id="channelId" value="ff8080812ed7f020012ee0ca236c099a" />
 <table class="frontTableList" id="qualityTable">
      <thead>
        <tr>
          <th class="left">标题</th>
          <th class="center">时间</th>
        </tr>
      </thead>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjdbrw/ff8080812f04f7fd012f42586d2a3cc7.jsp" class="toContentMain" title="“史圣”及古代著名经济学家">“史圣”及古代著名经济学家</a></td>
          <td style="text-align:right">2011-04-11</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjdbrw/ff8080812f04f7fd012f424d2d223cc5.jsp" class="toContentMain" title="“春秋第一相”">“春秋第一相”</a></td>
          <td style="text-align:right">2011-04-11</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjdbrw/ff8080812f04f7fd012f287d57ec22f6.jsp" class="toContentMain" title="清朝货币理论家、财政学家">清朝货币理论家、财政学家</a></td>
          <td style="text-align:right">2011-04-06</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjdbrw/ff8080812eec2137012eff76d34f1985.jsp" class="toContentMain" title="秦朝丞相">秦朝丞相</a></td>
          <td style="text-align:right">2011-03-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjdbrw/ff8080812eec2137012eff6fa8c21983.jsp" class="toContentMain" title="先秦法家代表人物">先秦法家代表人物</a></td>
          <td style="text-align:right">2011-03-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjdbrw/ff8080812eec2137012efa29a840091a.jsp" class="toContentMain" title="西汉著名理财家">西汉著名理财家</a></td>
          <td style="text-align:right">2011-03-28</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjdbrw/ff8080812eec2137012efa14088407f8.jsp" class="toContentMain" title="名家代表人物">名家代表人物</a></td>
          <td style="text-align:right">2011-03-28</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjdbrw/ff8080812eec2137012efa05742a0779.jsp" class="toContentMain" title="道家学派创始人">道家学派创始人</a></td>
          <td style="text-align:right">2011-03-28</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjdbrw/ff8080812eec2137012eedab6ebc013c.jsp" class="toContentMain" title="杂家代表人物">杂家代表人物</a></td>
          <td style="text-align:right">2011-03-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjdbrw/ff8080812ee243a4012eeb1a10181afc.jsp" class="toContentMain" title="兵学鼻祖">兵学鼻祖</a></td>
          <td style="text-align:right">2011-03-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjdbrw/ff8080812ee243a4012eeb0467a81aa4.jsp" class="toContentMain" title="法家思想集大成者">法家思想集大成者</a></td>
          <td style="text-align:right">2011-03-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjdbrw/ff8080812ee243a4012eeaf757391a8e.jsp" class="toContentMain" title="墨家创始人">墨家创始人</a></td>
          <td style="text-align:right">2011-03-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjdbrw/ff8080812ee243a4012eeaebce4e1a6c.jsp" class="toContentMain" title="道家代表人物">道家代表人物</a></td>
          <td style="text-align:right">2011-03-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjdbrw/ff8080812ee243a4012eeadfb5441a68.jsp" class="toContentMain" title="儒家代表人物">儒家代表人物</a></td>
          <td style="text-align:right">2011-03-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjdbrw/ff8080812ee243a4012eeaca6ca21a55.jsp" class="toContentMain" title="“亚圣”">“亚圣”</a></td>
          <td style="text-align:right">2011-03-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjdbrw/ff8080812ee243a4012ee295c27d0537.jsp" class="toContentMain" title="儒家创始人">儒家创始人</a></td>
          <td style="text-align:right">2011-03-23</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
<span class="totalNum">页次：1/1 每页20条 </span>
</div>		
      </div>
      
  </div>
	
