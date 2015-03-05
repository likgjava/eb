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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fba4e32012fbeaf1b6e0593.jsp" class="toContentMain" title="台湾内销螺丝全面提价 但未扩量">台湾内销螺丝全面提价 但未扩量</a></td>
          <td style="text-align:right">2011-05-05</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fba4e32012fbeaeb4cd0591.jsp" class="toContentMain" title="LED照明市场两大营销模式拉开战事">LED照明市场两大营销模式拉开战事</a></td>
          <td style="text-align:right">2011-05-05</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fba4e32012fbeae2033058f.jsp" class="toContentMain" title="家居业业绩下滑猛 翻新装修或成今年主流">家居业业绩下滑猛 翻新装修或成今年主流</a></td>
          <td style="text-align:right">2011-05-05</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fba4e32012fbead6ed7058d.jsp" class="toContentMain" title="我国智能变电站建设不断注入新“基因”">我国智能变电站建设不断注入新“基因”</a></td>
          <td style="text-align:right">2011-05-05</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fba4e32012fbda18cd8013b.jsp" class="toContentMain" title="锗价表现抢眼 小金属价格延续上涨">锗价表现抢眼 小金属价格延续上涨</a></td>
          <td style="text-align:right">2011-05-05</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fba4e32012fbd9f741c0139.jsp" class="toContentMain" title="医药流通行业“十二五”规划发布">医药流通行业“十二五”规划发布</a></td>
          <td style="text-align:right">2011-05-05</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fba4e32012fbd9cb7910137.jsp" class="toContentMain" title="电荒油荒持续蔓延 内能源价格调整势在必行">电荒油荒持续蔓延 内能源价格调整势在必行</a></td>
          <td style="text-align:right">2011-05-05</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fba4e32012fbd96dcae0118.jsp" class="toContentMain" title="铁道部：一季度亏损37.6亿主因系原材料涨价">铁道部：一季度亏损37.6亿主因系原材料涨价</a></td>
          <td style="text-align:right">2011-05-05</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fba4e32012fbd9282b80114.jsp" class="toContentMain" title="一季度粗钢产量16990.86万吨 同比增长8.69%">一季度粗钢产量16990.86万吨 同比增长8.69%</a></td>
          <td style="text-align:right">2011-05-05</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fba4e32012fbd9071b40112.jsp" class="toContentMain" title="电厂无奈贷款买煤 秦皇岛动力煤价创新高">电厂无奈贷款买煤 秦皇岛动力煤价创新高</a></td>
          <td style="text-align:right">2011-05-05</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fba4e32012fbd8b46430110.jsp" class="toContentMain" title="质检总局计划整顿我国光伏产业质量问题">质检总局计划整顿我国光伏产业质量问题</a></td>
          <td style="text-align:right">2011-05-05</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fba4e32012fbd878a6b010e.jsp" class="toContentMain" title="国家能源局:“加快推进煤矿企业兼并重组”方案">国家能源局:“加快推进煤矿企业兼并重组”方案</a></td>
          <td style="text-align:right">2011-05-05</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fb4297b012fb8f8329a01d8.jsp" class="toContentMain" title="动力煤价创新高或与煤炭进口大幅萎缩有关">动力煤价创新高或与煤炭进口大幅萎缩有关</a></td>
          <td style="text-align:right">2011-05-04</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fb4297b012fb8d84591019d.jsp" class="toContentMain" title="2015年后家具业将成为我国第一大产业">2015年后家具业将成为我国第一大产业</a></td>
          <td style="text-align:right">2011-05-04</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fb4297b012fb8d6f2240178.jsp" class="toContentMain" title="2011年LED芯片出货量将达1650亿片">2011年LED芯片出货量将达1650亿片</a></td>
          <td style="text-align:right">2011-05-04</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fb4297b012fb8d5b4df0176.jsp" class="toContentMain" title="厨房劲吹“环保风”厨房家具成必然趋势">厨房劲吹“环保风”厨房家具成必然趋势</a></td>
          <td style="text-align:right">2011-05-04</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fb4297b012fb8d4894c0174.jsp" class="toContentMain" title="2015年后家具业将成为我国第一大产业">2015年后家具业将成为我国第一大产业</a></td>
          <td style="text-align:right">2011-05-04</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f9cf3c5012fb38fecd30526.jsp" class="toContentMain" title="钢铁“绿动工厂”理念实现节能与安全同步">钢铁“绿动工厂”理念实现节能与安全同步</a></td>
          <td style="text-align:right">2011-05-03</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f9cf3c5012fb38ef8f80524.jsp" class="toContentMain" title="戴姆勒美国奔驰工厂受龙卷风影响停产">戴姆勒美国奔驰工厂受龙卷风影响停产</a></td>
          <td style="text-align:right">2011-05-03</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f9cf3c5012fb38d97470522.jsp" class="toContentMain" title="我国电工仪表行业面临空前成本压力">我国电工仪表行业面临空前成本压力</a></td>
          <td style="text-align:right">2011-05-03</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_46.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_48.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：47/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
