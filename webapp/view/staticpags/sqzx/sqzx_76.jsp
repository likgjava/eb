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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e8e7df0012e9881cd3f03f8.jsp" class="toContentMain" title="市场供应紧张 全球各钛白粉巨头同时涨价">市场供应紧张 全球各钛白粉巨头同时涨价</a></td>
          <td style="text-align:right">2011-03-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e8e7df0012e987d4b1503e7.jsp" class="toContentMain" title="环保低碳引领装潢新时代 竹地板成为潮流趋势">环保低碳引领装潢新时代 竹地板成为潮流趋势</a></td>
          <td style="text-align:right">2011-03-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e8e7df0012e987c70dd03e2.jsp" class="toContentMain" title="关注：木门行业各主流产品市场行情分析">关注：木门行业各主流产品市场行情分析</a></td>
          <td style="text-align:right">2011-03-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e8e7df0012e9879f62203d4.jsp" class="toContentMain" title="LED市场需求促成灯具发展的四大流行趋势">LED市场需求促成灯具发展的四大流行趋势</a></td>
          <td style="text-align:right">2011-03-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e8e7df0012e9878fe2d03d2.jsp" class="toContentMain" title="从2011年十大涂料品牌看涂料业走势">从2011年十大涂料品牌看涂料业走势</a></td>
          <td style="text-align:right">2011-03-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e8e7df0012e9877a2f803ce.jsp" class="toContentMain" title="坚持价格战中坚守阵地 地板行业定价需谨慎">坚持价格战中坚守阵地 地板行业定价需谨慎</a></td>
          <td style="text-align:right">2011-03-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e8e7df0012e98753cb903cc.jsp" class="toContentMain" title="2010年上海关区金属加工机床出口保持增长态势">2010年上海关区金属加工机床出口保持增长态势</a></td>
          <td style="text-align:right">2011-03-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e8e7df0012e9874ad4803ca.jsp" class="toContentMain" title="2010年日本机床进口额同比增长47.36%">2010年日本机床进口额同比增长47.36%</a></td>
          <td style="text-align:right">2011-03-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e8e7df0012e986f0f0f03c8.jsp" class="toContentMain" title="2011年中国3G移动通信市场版图分析">2011年中国3G移动通信市场版图分析</a></td>
          <td style="text-align:right">2011-03-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e8e7df0012e986d959b03c6.jsp" class="toContentMain" title="欧洲排放决议：锂电池进入黄金发展期">欧洲排放决议：锂电池进入黄金发展期</a></td>
          <td style="text-align:right">2011-03-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e8e7df0012e9499fa5102f5.jsp" class="toContentMain" title="两会延伸：不锈钢制品十二五开局谋划再腾飞">两会延伸：不锈钢制品十二五开局谋划再腾飞</a></td>
          <td style="text-align:right">2011-03-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e8e7df0012e9497c11202f3.jsp" class="toContentMain" title="“千湖之省”湖北将投入上千亿建设水利">“千湖之省”湖北将投入上千亿建设水利</a></td>
          <td style="text-align:right">2011-03-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e8e7df0012e9492778e02eb.jsp" class="toContentMain" title="广东省工业品内销大幅增长">广东省工业品内销大幅增长</a></td>
          <td style="text-align:right">2011-03-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e8e7df0012e948d89be02d7.jsp" class="toContentMain" title="沧州市场大中型材价格下跌">沧州市场大中型材价格下跌</a></td>
          <td style="text-align:right">2011-03-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e8e7df0012e948ac4b202d5.jsp" class="toContentMain" title="深耕市场 空调行业中等规模企业品牌崛起">深耕市场 空调行业中等规模企业品牌崛起</a></td>
          <td style="text-align:right">2011-03-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e8e7df0012e9418cbc2016c.jsp" class="toContentMain" title="国际大买家在穗发布2011采购计划 金额超20亿美金">国际大买家在穗发布2011采购计划 金额超20亿美金</a></td>
          <td style="text-align:right">2011-03-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e8e7df0012e9417cfc3016a.jsp" class="toContentMain" title="企业采购积极 安徽灵璧小麦价格略涨">企业采购积极 安徽灵璧小麦价格略涨</a></td>
          <td style="text-align:right">2011-03-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e8e7df0012e941237470163.jsp" class="toContentMain" title="药品采购成本大降 医疗器械本月也进药交所">药品采购成本大降 医疗器械本月也进药交所</a></td>
          <td style="text-align:right">2011-03-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e8e7df0012e940ae5dc0149.jsp" class="toContentMain" title="国外买家加价采购“中国制造”">国外买家加价采购“中国制造”</a></td>
          <td style="text-align:right">2011-03-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e8e7df0012e940932f00147.jsp" class="toContentMain" title="定向采购20亿美元国际买家广州下单">定向采购20亿美元国际买家广州下单</a></td>
          <td style="text-align:right">2011-03-08</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_75.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_77.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：76/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
