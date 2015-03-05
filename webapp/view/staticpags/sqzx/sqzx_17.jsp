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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813192fe7c013197828e64033a.jsp" class="toContentMain" title="太阳能热利用合同能源管理标准将出台">太阳能热利用合同能源管理标准将出台</a></td>
          <td style="text-align:right">2011-08-05</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813192fe7c01319781a2460339.jsp" class="toContentMain" title="日本家电业二季度业绩惨淡 巨亏之下谋求转型">日本家电业二季度业绩惨淡 巨亏之下谋求转型</a></td>
          <td style="text-align:right">2011-08-05</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813192fe7c0131977bbb540334.jsp" class="toContentMain" title="有色金属工业协会：稀土全行业不可能停产">有色金属工业协会：稀土全行业不可能停产</a></td>
          <td style="text-align:right">2011-08-05</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813192fe7c0131977afec40333.jsp" class="toContentMain" title="欧盟对华自行车反倾销税期再延5年">欧盟对华自行车反倾销税期再延5年</a></td>
          <td style="text-align:right">2011-08-05</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813192fe7c01319779db010331.jsp" class="toContentMain" title="我国橡胶防老剂产销再创新高">我国橡胶防老剂产销再创新高</a></td>
          <td style="text-align:right">2011-08-05</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081317673560131926833ea1bc0.jsp" class="toContentMain" title="工程机械行业风光几许 “围城”进行时">工程机械行业风光几许 “围城”进行时</a></td>
          <td style="text-align:right">2011-08-04</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131767356013192604aaf1b80.jsp" class="toContentMain" title="挖掘机行业快速增长 民族品牌占比提高">挖掘机行业快速增长 民族品牌占比提高</a></td>
          <td style="text-align:right">2011-08-04</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081317673560131925f44d71b7f.jsp" class="toContentMain" title="建材业关门潮 二三线城市或将擦出火花">建材业关门潮 二三线城市或将擦出火花</a></td>
          <td style="text-align:right">2011-08-04</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081317673560131925c0ab21b72.jsp" class="toContentMain" title="引领绿色未来 燃料电池蓄势待发">引领绿色未来 燃料电池蓄势待发</a></td>
          <td style="text-align:right">2011-08-04</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131767356013192542f831aec.jsp" class="toContentMain" title="云计算技术将进入金融行业趋势不可挡">云计算技术将进入金融行业趋势不可挡</a></td>
          <td style="text-align:right">2011-08-04</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081317673560131924db4481ae7.jsp" class="toContentMain" title="十二五水泥业上演重组大戏 西南或再成主战场">十二五水泥业上演重组大戏 西南或再成主战场</a></td>
          <td style="text-align:right">2011-08-04</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081317673560131924d1e5e1ae6.jsp" class="toContentMain" title="食用油限价解禁 油脂产业逐渐回暖">食用油限价解禁 油脂产业逐渐回暖</a></td>
          <td style="text-align:right">2011-08-04</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081317673560131924c7ff71ae5.jsp" class="toContentMain" title="飞机制造跃升为国家战略性产业">飞机制造跃升为国家战略性产业</a></td>
          <td style="text-align:right">2011-08-04</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081317673560131924ba5331ae3.jsp" class="toContentMain" title="我国掀起甲醇制烯烃项目热潮 甲醇价格大涨">我国掀起甲醇制烯烃项目热潮 甲醇价格大涨</a></td>
          <td style="text-align:right">2011-08-04</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081317673560131924a71b51adf.jsp" class="toContentMain" title="纺织业压力重重直面生死限">纺织业压力重重直面生死限</a></td>
          <td style="text-align:right">2011-08-04</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081317673560131923c301b19f6.jsp" class="toContentMain" title="需求放缓 全球CPU年度出货量预期下调(图)">需求放缓 全球CPU年度出货量预期下调(图)</a></td>
          <td style="text-align:right">2011-08-04</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081317673560131923b325319f5.jsp" class="toContentMain" title="适应能源增长需求 我国核电小堆开发应用已启幕">适应能源增长需求 我国核电小堆开发应用已启幕</a></td>
          <td style="text-align:right">2011-08-04</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813176735601318d4266af125f.jsp" class="toContentMain" title="卫浴业在寒流中寻找“温暖” 主打中低端市场">卫浴业在寒流中寻找“温暖” 主打中低端市场</a></td>
          <td style="text-align:right">2011-08-03</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813176735601318d402237125c.jsp" class="toContentMain" title="国产彩电智能化 芯片操作系统却姓“洋”">国产彩电智能化 芯片操作系统却姓“洋”</a></td>
          <td style="text-align:right">2011-08-03</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813176735601318d3c7f6b1259.jsp" class="toContentMain" title="空气能热水器将成空调业利润新增长点">空气能热水器将成空调业利润新增长点</a></td>
          <td style="text-align:right">2011-08-03</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_16.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_18.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：17/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
