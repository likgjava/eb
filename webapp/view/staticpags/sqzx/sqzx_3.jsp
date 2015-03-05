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
		$("#conBody").loadPage($('#initPath').val()+'/CmsNewsController.do?method=search&chnlCodes=sqzx&searchKey='+strIgnore($('#searchKeyContentMain').val())+'&pageSize=20&startTime='+$('#startTime').val()+'&endTime='+$('#endTime').val()+'&searchResultForm=search_news_body_result.ftl');
	});
	$("#startTime").epsDatepicker();
     $("#endTime").epsDatepicker();
})
</script>
 <div id="conTitle">
 	<div class="navCurrent ">

	<a href="/view/srplatform/portal/index.jsp" id="/view/srplatform/portal/index.jsp" title="首页" target="_self">首页</a>
	<a href="javascript:void(0)" id="/view/smallscale/business/shangquan.jsp" title="陶朱公商圈" class="cmsHref_self">陶朱公商圈</a>
	<a href="javascript:void(0)" id="/view/staticpags/sqzx/sqzx.jsp" title="商圈资讯" class="cmsHref_self">商圈资讯</a>
		
 	</div>
  <div class="formTips attention">
	<ul><li><em>商圈资讯展示：</em></li></ul>
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
 <input type="hidden"  id="channelId" value="ff8080812dd59be5012dda319fa70356" />
 <table class="frontTableList" id="qualityTable">
      <thead>
        <tr>
          <th class="left">标题</th>
          <th class="center">时间</th>
        </tr>
      </thead>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081321f7bae0132228876f30058.jsp" class="toContentMain" title="彩电业突围：四大升级方向的抉择">彩电业突围：四大升级方向的抉择</a></td>
          <td style="text-align:right">2011-09-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081321f7bae0132227f1c5b0053.jsp" class="toContentMain" title="需求减缓而且产能过剩 基础化工业增长将踩刹车">需求减缓而且产能过剩 基础化工业增长将踩刹车</a></td>
          <td style="text-align:right">2011-09-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813218571c01321d784fbc0261.jsp" class="toContentMain" title="白酒盛宴延续：新一轮涨价潮到来">白酒盛宴延续：新一轮涨价潮到来</a></td>
          <td style="text-align:right">2011-08-31</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813218571c01321d779b640260.jsp" class="toContentMain" title="我国中央空调业再次收获国际领先技术">我国中央空调业再次收获国际领先技术</a></td>
          <td style="text-align:right">2011-08-31</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813218571c01321d74d01f025e.jsp" class="toContentMain" title="苯酐市场部分小涨 后市仍稳定为主">苯酐市场部分小涨 后市仍稳定为主</a></td>
          <td style="text-align:right">2011-08-31</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813218571c01321d7364b0025c.jsp" class="toContentMain" title="电动工具行业深度分析 四大瓶颈待突破">电动工具行业深度分析 四大瓶颈待突破</a></td>
          <td style="text-align:right">2011-08-31</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813218571c01321d70b225025b.jsp" class="toContentMain" title="啤酒没有涨价是为走进高端市场？">啤酒没有涨价是为走进高端市场？</a></td>
          <td style="text-align:right">2011-08-31</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813218571c01321d702bf0025a.jsp" class="toContentMain" title="机电制造业增速领跑海口 光伏产业成新长点">机电制造业增速领跑海口 光伏产业成新长点</a></td>
          <td style="text-align:right">2011-08-31</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813218571c01321d6f71830259.jsp" class="toContentMain" title="2011年7月份轮胎行业增速加快">2011年7月份轮胎行业增速加快</a></td>
          <td style="text-align:right">2011-08-31</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813218571c01321d69187e0254.jsp" class="toContentMain" title="棉花产业形势比金融危机时还严峻">棉花产业形势比金融危机时还严峻</a></td>
          <td style="text-align:right">2011-08-31</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813218571c01321d66e2640252.jsp" class="toContentMain" title="中小卫浴企业发展“冰火两重天”原因何在">中小卫浴企业发展“冰火两重天”原因何在</a></td>
          <td style="text-align:right">2011-08-31</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813218571c01321d646be2023b.jsp" class="toContentMain" title="中国轮胎行业增速加快 但利润率显降">中国轮胎行业增速加快 但利润率显降</a></td>
          <td style="text-align:right">2011-08-31</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813218571c01321d63a9a20234.jsp" class="toContentMain" title="我国船舶涂料业进入黄金发展期">我国船舶涂料业进入黄金发展期</a></td>
          <td style="text-align:right">2011-08-31</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813218571c01321d5a65230225.jsp" class="toContentMain" title="月饼税让我们hold不住">月饼税让我们hold不住</a></td>
          <td style="text-align:right">2011-08-31</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813218571c01321d5937f30223.jsp" class="toContentMain" title="我国工程机械配套件进口依赖症威胁产业安全">我国工程机械配套件进口依赖症威胁产业安全</a></td>
          <td style="text-align:right">2011-08-31</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813218571c0132187ba1660046.jsp" class="toContentMain" title="预计未来两年将成为LED电视高增长期">预计未来两年将成为LED电视高增长期</a></td>
          <td style="text-align:right">2011-08-30</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813218571c01321878da520045.jsp" class="toContentMain" title="烹饪炊具业转型升级须走好“两步棋”">烹饪炊具业转型升级须走好“两步棋”</a></td>
          <td style="text-align:right">2011-08-30</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813218571c0132187812e90044.jsp" class="toContentMain" title="二三线城市限购 或将“冷却”五金行业">二三线城市限购 或将“冷却”五金行业</a></td>
          <td style="text-align:right">2011-08-30</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813218571c01321876f0a20043.jsp" class="toContentMain" title="红木家具风头正劲 取代洋家具成新风尚追求">红木家具风头正劲 取代洋家具成新风尚追求</a></td>
          <td style="text-align:right">2011-08-30</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813218571c01321874b1ee003f.jsp" class="toContentMain" title="家具市场恶性竞争加剧 唯创新才能脱困">家具市场恶性竞争加剧 唯创新才能脱困</a></td>
          <td style="text-align:right">2011-08-30</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_2.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_4.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：3/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
