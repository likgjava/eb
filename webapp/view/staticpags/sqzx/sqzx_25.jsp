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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813140dc470131451240590335.jsp" class="toContentMain" title="化工行业处于需求淡季 产品价格普遍弱势">化工行业处于需求淡季 产品价格普遍弱势</a></td>
          <td style="text-align:right">2011-07-20</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813140dc470131451112330334.jsp" class="toContentMain" title="国内外棉价分道扬镳 下游需求不见起色制约反弹">国内外棉价分道扬镳 下游需求不见起色制约反弹</a></td>
          <td style="text-align:right">2011-07-20</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813140dc470131450e67f10332.jsp" class="toContentMain" title="大豆需求八成靠进口 今年缺口将超4000万吨">大豆需求八成靠进口 今年缺口将超4000万吨</a></td>
          <td style="text-align:right">2011-07-20</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813140dc470131450d7cb70331.jsp" class="toContentMain" title="预计十年内世界铝需求将增加一倍">预计十年内世界铝需求将增加一倍</a></td>
          <td style="text-align:right">2011-07-20</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813140dc4701314505d84e032a.jsp" class="toContentMain" title="乙烯供应趋紧 连塑反弹有望延续">乙烯供应趋紧 连塑反弹有望延续</a></td>
          <td style="text-align:right">2011-07-20</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081313c08ec01313ffefa840231.jsp" class="toContentMain" title="建材市场 追踪实木地板价格上涨的因素">建材市场 追踪实木地板价格上涨的因素</a></td>
          <td style="text-align:right">2011-07-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081313c08ec01313ffcb886022f.jsp" class="toContentMain" title="3D电视不再“奢侈” 半年最高降幅达四成">3D电视不再“奢侈” 半年最高降幅达四成</a></td>
          <td style="text-align:right">2011-07-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081313c08ec01313ff8a40c022c.jsp" class="toContentMain" title="食品饮料行业并购加速 加剧国内行业洗牌">食品饮料行业并购加速 加剧国内行业洗牌</a></td>
          <td style="text-align:right">2011-07-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081313c08ec01313ff80bdb022b.jsp" class="toContentMain" title="生物医药卫生领域发展重点明确">生物医药卫生领域发展重点明确</a></td>
          <td style="text-align:right">2011-07-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081313c08ec01313ff2d9990229.jsp" class="toContentMain" title="稀土加工能力过剩：央企国企围猎民企">稀土加工能力过剩：央企国企围猎民企</a></td>
          <td style="text-align:right">2011-07-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081313c08ec01313ff10a6c0228.jsp" class="toContentMain" title="玉米小麦每吨倒挂150元 饲料企业大量购小麦">玉米小麦每吨倒挂150元 饲料企业大量购小麦</a></td>
          <td style="text-align:right">2011-07-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081313c08ec01313fe3b5040219.jsp" class="toContentMain" title="下半年全球钢材需求和产量增速将放慢">下半年全球钢材需求和产量增速将放慢</a></td>
          <td style="text-align:right">2011-07-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081313c08ec01313fe205f80216.jsp" class="toContentMain" title="面板厂商因全球需求疲软拟减产">面板厂商因全球需求疲软拟减产</a></td>
          <td style="text-align:right">2011-07-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081312d69ed01313af188630238.jsp" class="toContentMain" title="三大动力助推中国工程机械产业发展提速">三大动力助推中国工程机械产业发展提速</a></td>
          <td style="text-align:right">2011-07-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081312d69ed01313aeffc100236.jsp" class="toContentMain" title="我国家具业标准低 甲醛释放限量是国际三倍">我国家具业标准低 甲醛释放限量是国际三倍</a></td>
          <td style="text-align:right">2011-07-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081312d69ed01313aecec0e0235.jsp" class="toContentMain" title="发改委：1－5月纺织服装行业经济运行分析">发改委：1－5月纺织服装行业经济运行分析</a></td>
          <td style="text-align:right">2011-07-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081312d69ed01313aeb50970233.jsp" class="toContentMain" title="电热水器产品与燃气热水器产品性能对比">电热水器产品与燃气热水器产品性能对比</a></td>
          <td style="text-align:right">2011-07-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081312d69ed01313ae92b660231.jsp" class="toContentMain" title="原料需求量减少 国内纸浆价格趋于下滑">原料需求量减少 国内纸浆价格趋于下滑</a></td>
          <td style="text-align:right">2011-07-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081312d69ed01313ae7d52c022f.jsp" class="toContentMain" title="加快推动轮胎翻新行业 促进可持续发展">加快推动轮胎翻新行业 促进可持续发展</a></td>
          <td style="text-align:right">2011-07-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081312d69ed01313ae543e0022e.jsp" class="toContentMain" title="2011年全球笔记本电脑市场将创新低">2011年全球笔记本电脑市场将创新低</a></td>
          <td style="text-align:right">2011-07-18</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_24.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_26.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：25/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
