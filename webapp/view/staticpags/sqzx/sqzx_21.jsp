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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a01316e4fdff80700.jsp" class="toContentMain" title="2015全球印刷软件市场有望超108亿美元">2015全球印刷软件市场有望超108亿美元</a></td>
          <td style="text-align:right">2011-07-28</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a01316e4bfa2d06fb.jsp" class="toContentMain" title="制鞋行业着眼于加速提高品牌附加值">制鞋行业着眼于加速提高品牌附加值</a></td>
          <td style="text-align:right">2011-07-28</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a01316e4b3b4a06fa.jsp" class="toContentMain" title="三大矿山垄断 国内铁矿石价格逆势走高">三大矿山垄断 国内铁矿石价格逆势走高</a></td>
          <td style="text-align:right">2011-07-28</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a01316e42569506f3.jsp" class="toContentMain" title="刚上市的秋装价格普涨一成 涨价已成定局">刚上市的秋装价格普涨一成 涨价已成定局</a></td>
          <td style="text-align:right">2011-07-28</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a01316e410a5506f2.jsp" class="toContentMain" title="LED照明市场呼声渐高 国际资本亲睐中国蛋糕">LED照明市场呼声渐高 国际资本亲睐中国蛋糕</a></td>
          <td style="text-align:right">2011-07-28</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a01316935e23804ee.jsp" class="toContentMain" title="政策市场双重利好 中国阀门业前景光明">政策市场双重利好 中国阀门业前景光明</a></td>
          <td style="text-align:right">2011-07-27</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a013169314ea004e8.jsp" class="toContentMain" title="太阳能电热带行业标准讨论尘埃落定">太阳能电热带行业标准讨论尘埃落定</a></td>
          <td style="text-align:right">2011-07-27</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a01316930b03404e7.jsp" class="toContentMain" title="加拿大消费品安全法实施 影响中国服装出口">加拿大消费品安全法实施 影响中国服装出口</a></td>
          <td style="text-align:right">2011-07-27</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a0131692fc4ca04e6.jsp" class="toContentMain" title="铁矿石三巨头垄断力增 国内价格逆势走高">铁矿石三巨头垄断力增 国内价格逆势走高</a></td>
          <td style="text-align:right">2011-07-27</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a0131692c7fbd04df.jsp" class="toContentMain" title="国内3D手机战即将开打">国内3D手机战即将开打</a></td>
          <td style="text-align:right">2011-07-27</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a0131692b6ec804de.jsp" class="toContentMain" title="太阳能热水器出口价格普遍提升10%-15%">太阳能热水器出口价格普遍提升10%-15%</a></td>
          <td style="text-align:right">2011-07-27</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a0131692a644104d6.jsp" class="toContentMain" title="家电暗涌整合扩张 目标高一点再高一点">家电暗涌整合扩张 目标高一点再高一点</a></td>
          <td style="text-align:right">2011-07-27</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a01316928750304d1.jsp" class="toContentMain" title="内需市场将成为中国纺织业发展的重要支撑">内需市场将成为中国纺织业发展的重要支撑</a></td>
          <td style="text-align:right">2011-07-27</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a0131692771d004d0.jsp" class="toContentMain" title="中国运动鞋服市场已成为国际媒体关注焦点">中国运动鞋服市场已成为国际媒体关注焦点</a></td>
          <td style="text-align:right">2011-07-27</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a013169248bc404cf.jsp" class="toContentMain" title="智能电视渗透率年底将达19%">智能电视渗透率年底将达19%</a></td>
          <td style="text-align:right">2011-07-27</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a0131692385e904cd.jsp" class="toContentMain" title="合成橡胶厂商抱团减产 部分地区丁二烯价格回落">合成橡胶厂商抱团减产 部分地区丁二烯价格回落</a></td>
          <td style="text-align:right">2011-07-27</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a0131692284fc04cb.jsp" class="toContentMain" title="内需市场将成为中国纺织业发展重要支撑">内需市场将成为中国纺织业发展重要支撑</a></td>
          <td style="text-align:right">2011-07-27</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a01316921cba804ca.jsp" class="toContentMain" title="四企业垄断450亿铁路信号市场 难有新进入者">四企业垄断450亿铁路信号市场 难有新进入者</a></td>
          <td style="text-align:right">2011-07-27</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a0131690df45804bb.jsp" class="toContentMain" title="中印市场需求推动钻石销售年增33%">中印市场需求推动钻石销售年增33%</a></td>
          <td style="text-align:right">2011-07-27</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a0131690cc93604b9.jsp" class="toContentMain" title="灯具销售需求量大 行业却陷入品">灯具销售需求量大 行业却陷入品</a></td>
          <td style="text-align:right">2011-07-27</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_20.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_22.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：21/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
