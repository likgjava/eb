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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f57dd75861597.jsp" class="toContentMain" title="中国稳步鼎立全球塑料第一消费大国">中国稳步鼎立全球塑料第一消费大国</a></td>
          <td style="text-align:right">2011-04-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f57d80f3c1595.jsp" class="toContentMain" title="400亿市场待抢 垂直电商风投受热宠">400亿市场待抢 垂直电商风投受热宠</a></td>
          <td style="text-align:right">2011-04-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f57d5dcc51591.jsp" class="toContentMain" title="霸气外露 苹果欲通吃高低端消费市场">霸气外露 苹果欲通吃高低端消费市场</a></td>
          <td style="text-align:right">2011-04-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f57d499b7158c.jsp" class="toContentMain" title="病险水库除险今年中央再投168亿">病险水库除险今年中央再投168亿</a></td>
          <td style="text-align:right">2011-04-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f57d2439d158a.jsp" class="toContentMain" title="中铁十三局三首个盾构施工项目实现贯通">中铁十三局三首个盾构施工项目实现贯通</a></td>
          <td style="text-align:right">2011-04-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f57cd0ed81587.jsp" class="toContentMain" title="内需市场中整体衣柜业的三大机遇">内需市场中整体衣柜业的三大机遇</a></td>
          <td style="text-align:right">2011-04-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f57c6e9641585.jsp" class="toContentMain" title="当历史遭遇时尚：仿古地板悄然走红市场">当历史遭遇时尚：仿古地板悄然走红市场</a></td>
          <td style="text-align:right">2011-04-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f57b9dd0d1579.jsp" class="toContentMain" title="加洲出资50万美元助推LED制造工艺升级">加洲出资50万美元助推LED制造工艺升级</a></td>
          <td style="text-align:right">2011-04-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f571f201b1565.jsp" class="toContentMain" title="节省能源 低碳办公 环保铅笔打印机问世">节省能源 低碳办公 环保铅笔打印机问世</a></td>
          <td style="text-align:right">2011-04-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f571e00121563.jsp" class="toContentMain" title="未来五年中国等五个国家特种纸发展强劲">未来五年中国等五个国家特种纸发展强劲</a></td>
          <td style="text-align:right">2011-04-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f571917d01561.jsp" class="toContentMain" title="我国市场需求旺盛带动无机颜料价格大涨">我国市场需求旺盛带动无机颜料价格大涨</a></td>
          <td style="text-align:right">2011-04-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f5716f326155f.jsp" class="toContentMain" title="地震让上海日本间化工运输船舶锐减25%">地震让上海日本间化工运输船舶锐减25%</a></td>
          <td style="text-align:right">2011-04-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f5714251f1559.jsp" class="toContentMain" title="浆价再创新高 中国纸浆购买力小幅减缓">浆价再创新高 中国纸浆购买力小幅减缓</a></td>
          <td style="text-align:right">2011-04-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f570d58241553.jsp" class="toContentMain" title="五金产业深陷价格战 该涨还是该降？">五金产业深陷价格战 该涨还是该降？</a></td>
          <td style="text-align:right">2011-04-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f570b5be11543.jsp" class="toContentMain" title="全球磨具磨料产品需求量2013年或增5.9%">全球磨具磨料产品需求量2013年或增5.9%</a></td>
          <td style="text-align:right">2011-04-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f5708365b1524.jsp" class="toContentMain" title="中俄机电贸易恢复性增长 2015或达300亿美元">中俄机电贸易恢复性增长 2015或达300亿美元</a></td>
          <td style="text-align:right">2011-04-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f5705db9b1517.jsp" class="toContentMain" title="家居建材业进入保障房市场 性价比受关注">家居建材业进入保障房市场 性价比受关注</a></td>
          <td style="text-align:right">2011-04-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f57020bf814ff.jsp" class="toContentMain" title="家具卖场进行疯狂扩张 行家揭秘谁为此买单">家具卖场进行疯狂扩张 行家揭秘谁为此买单</a></td>
          <td style="text-align:right">2011-04-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f56fe613f14e6.jsp" class="toContentMain" title="塞拉尼斯宣布5月1日起聚乙烯提价">塞拉尼斯宣布5月1日起聚乙烯提价</a></td>
          <td style="text-align:right">2011-04-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f56fc9b0214d6.jsp" class="toContentMain" title="亚洲乙烯价格4月14日涨跌不一">亚洲乙烯价格4月14日涨跌不一</a></td>
          <td style="text-align:right">2011-04-15</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_58.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_60.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：59/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
