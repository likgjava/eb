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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081312d69ed01313ae03920022d.jsp" class="toContentMain" title="今年中国的智能手机发货量将增长53%">今年中国的智能手机发货量将增长53%</a></td>
          <td style="text-align:right">2011-07-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081312d69ed01313adfa498022c.jsp" class="toContentMain" title="LED电视销量将达1800万台 今年3D成新潮流">LED电视销量将达1800万台 今年3D成新潮流</a></td>
          <td style="text-align:right">2011-07-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081312d69ed01313ad9cd26022a.jsp" class="toContentMain" title="日本强震影响基本消除 数码相机等价格回落">日本强震影响基本消除 数码相机等价格回落</a></td>
          <td style="text-align:right">2011-07-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081312d69ed01313ad756d50229.jsp" class="toContentMain" title="中芯国际进入新国企时代">中芯国际进入新国企时代</a></td>
          <td style="text-align:right">2011-07-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081312d69ed01313ad64b0d0228.jsp" class="toContentMain" title="中国新能源汽车遭遇技术安全等多重问题">中国新能源汽车遭遇技术安全等多重问题</a></td>
          <td style="text-align:right">2011-07-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081312d69ed01313ad4fa230227.jsp" class="toContentMain" title="纺织行业内忧外患 化纤企业异军突起">纺织行业内忧外患 化纤企业异军突起</a></td>
          <td style="text-align:right">2011-07-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081312d69ed01313ad456320226.jsp" class="toContentMain" title="中国风电设备巨头进军欧洲海上风电">中国风电设备巨头进军欧洲海上风电</a></td>
          <td style="text-align:right">2011-07-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081312d69ed01313ad259830225.jsp" class="toContentMain" title="中国成未来全球玻纤行业增长最快市场">中国成未来全球玻纤行业增长最快市场</a></td>
          <td style="text-align:right">2011-07-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081312d69ed01313acbb4530222.jsp" class="toContentMain" title="水利4万亿 投资大头靠财政">水利4万亿 投资大头靠财政</a></td>
          <td style="text-align:right">2011-07-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081312d69ed01313acb00710221.jsp" class="toContentMain" title="稀土价格光速狂飙 部分品种猛翻10倍">稀土价格光速狂飙 部分品种猛翻10倍</a></td>
          <td style="text-align:right">2011-07-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081312d69ed01313aca76e80220.jsp" class="toContentMain" title="传工信部否认千亿补贴新能源汽车">传工信部否认千亿补贴新能源汽车</a></td>
          <td style="text-align:right">2011-07-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081312d69ed01313ac8b8f7021e.jsp" class="toContentMain" title="中国稀土配额公布 西方国家仍不满足">中国稀土配额公布 西方国家仍不满足</a></td>
          <td style="text-align:right">2011-07-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081312d69ed01313ac797f1021d.jsp" class="toContentMain" title="畸形利益导致逢煤必化乱局 加剧煤化工行业亏损">畸形利益导致逢煤必化乱局 加剧煤化工行业亏损</a></td>
          <td style="text-align:right">2011-07-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081312d69ed01313ac6ee7a021b.jsp" class="toContentMain" title="国内钢市欲涨还休 品种间分化行情将持续">国内钢市欲涨还休 品种间分化行情将持续</a></td>
          <td style="text-align:right">2011-07-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081312d69ed01313ac60add021a.jsp" class="toContentMain" title="专家认为我国汽车产业应突破技术瓶颈抢占制高点">专家认为我国汽车产业应突破技术瓶颈抢占制高点</a></td>
          <td style="text-align:right">2011-07-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081312d69ed01313ac590360219.jsp" class="toContentMain" title="众多厂商预计中国车市下半年将迎来恢复性增长">众多厂商预计中国车市下半年将迎来恢复性增长</a></td>
          <td style="text-align:right">2011-07-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813118359201312b8111242480.jsp" class="toContentMain" title="看好中国建材业 东盟企业对接中国内地市场">看好中国建材业 东盟企业对接中国内地市场</a></td>
          <td style="text-align:right">2011-07-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813118359201312b8083b1247e.jsp" class="toContentMain" title="LED照明性价比加速提高 将迎来降价热潮">LED照明性价比加速提高 将迎来降价热潮</a></td>
          <td style="text-align:right">2011-07-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813118359201312b7fdd0d2478.jsp" class="toContentMain" title="市场剖析：中国装载机步入理性发展阶段">市场剖析：中国装载机步入理性发展阶段</a></td>
          <td style="text-align:right">2011-07-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813118359201312b7a3080246c.jsp" class="toContentMain" title="服装企业利润更大 成本只是涨价的借口">服装企业利润更大 成本只是涨价的借口</a></td>
          <td style="text-align:right">2011-07-15</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_25.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_27.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：26/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
