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
		$("#conBody").loadPage($('#initPath').val()+'/CmsNewsController.do?method=search&chnlCodes=zzbjyctwh&searchKey='+strIgnore($('#searchKeyContentMain').val())+'&pageSize=20&startTime='+$('#startTime').val()+'&endTime='+$('#endTime').val()+'&searchResultForm=search_news_body_result.ftl');
	});
	$("#startTime").epsDatepicker();
     $("#endTime").epsDatepicker();
})
</script>
 <div id="conTitle">
 	<div class="navCurrent ">

	<a href="/view/srplatform/portal/index.jsp" id="/view/srplatform/portal/index.jsp" title="首页" target="_self">首页</a>
	<a href="javascript:void(0)" id="/view/smallscale/business/shangquan.jsp" title="陶朱公商圈" class="cmsHref_self">陶朱公商圈</a>
	<a href="javascript:void(0)" id="/view/staticpags/zzbjyctwh/zzbjyctwh.jsp" title="诸子百家与传统文化" class="cmsHref_self">诸子百家与传统文化</a>
		
 	</div>
  <div class="formTips attention">
	<ul><li><em>诸子百家与传统文化展示：</em></li></ul>
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
 <input type="hidden"  id="channelId" value="ff8080812ed7f020012ee0c98e670995" />
 <table class="frontTableList" id="qualityTable">
      <thead>
        <tr>
          <th class="left">标题</th>
          <th class="center">时间</th>
        </tr>
      </thead>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812eec2137012eff352cde1926.jsp" class="toContentMain" title="郑宇民：传承浙商文明的历史使命">郑宇民：传承浙商文明的历史使命</a></td>
          <td style="text-align:right">2011-03-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812eec2137012eff2cf424189c.jsp" class="toContentMain" title="民企与文化“交响”，文化浙商也精彩">民企与文化“交响”，文化浙商也精彩</a></td>
          <td style="text-align:right">2011-03-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812eec2137012eff269c241894.jsp" class="toContentMain" title="玉商文化与浙商文化风格之比较">玉商文化与浙商文化风格之比较</a></td>
          <td style="text-align:right">2011-03-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812eec2137012eff235a34188f.jsp" class="toContentMain" title="浙商文化与区域经济发展">浙商文化与区域经济发展</a></td>
          <td style="text-align:right">2011-03-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812eec2137012eff1fe13717c2.jsp" class="toContentMain" title="新浙商历史文化溯源">新浙商历史文化溯源</a></td>
          <td style="text-align:right">2011-03-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812eec2137012efb92e755130e.jsp" class="toContentMain" title="浙商四大文化品质：中国式市场经济的开拓者">浙商四大文化品质：中国式市场经济的开拓者</a></td>
          <td style="text-align:right">2011-03-28</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812eec2137012efb8d37c1130a.jsp" class="toContentMain" title="追求“唯一”：一个“文化新浙商”的使命">追求“唯一”：一个“文化新浙商”的使命</a></td>
          <td style="text-align:right">2011-03-28</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812eec2137012efb87fde112e4.jsp" class="toContentMain" title="新浙商文化能提供哪些想象力">新浙商文化能提供哪些想象力</a></td>
          <td style="text-align:right">2011-03-28</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812eec2137012efb7b956b12d5.jsp" class="toContentMain" title="浙商文化的和谐因子">浙商文化的和谐因子</a></td>
          <td style="text-align:right">2011-03-28</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812eec2137012efb345421100a.jsp" class="toContentMain" title="用跨文化管理思维看潮商企业管理模式">用跨文化管理思维看潮商企业管理模式</a></td>
          <td style="text-align:right">2011-03-28</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812eec2137012efb1e62cd1001.jsp" class="toContentMain" title="从“徽商”没落看到“浙商”崛起">从“徽商”没落看到“浙商”崛起</a></td>
          <td style="text-align:right">2011-03-28</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812eec2137012efb0f6da30ffb.jsp" class="toContentMain" title="潮商演变的文化根源">潮商演变的文化根源</a></td>
          <td style="text-align:right">2011-03-28</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812eec2137012efa6ea9590d14.jsp" class="toContentMain" title="孔子经济思想的生态阐释">孔子经济思想的生态阐释</a></td>
          <td style="text-align:right">2011-03-28</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812eec2137012efa52c2120b16.jsp" class="toContentMain" title="桑弘羊的经济思想及其文化意蕴">桑弘羊的经济思想及其文化意蕴</a></td>
          <td style="text-align:right">2011-03-28</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812eec2137012efa33fc700964.jsp" class="toContentMain" title="桑弘羊理财思想的现代启示">桑弘羊理财思想的现代启示</a></td>
          <td style="text-align:right">2011-03-28</td>
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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812eec2137012eeda0648b011a.jsp" class="toContentMain" title="君子和而不同，小人同而不和">君子和而不同，小人同而不和</a></td>
          <td style="text-align:right">2011-03-25</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/zzbjyctwh/zzbjyctwh.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/zzbjyctwh/zzbjyctwh_7.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/zzbjyctwh/zzbjyctwh_9.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/zzbjyctwh/zzbjyctwh_10.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：8/10 每页20条 </span>
</div>		
      </div>
      
  </div>
	
