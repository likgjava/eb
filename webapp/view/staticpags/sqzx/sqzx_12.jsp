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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131d7d3750131da6591820015.jsp" class="toContentMain" title="卫浴洁具市场促销活动的三大“不等号”">卫浴洁具市场促销活动的三大“不等号”</a></td>
          <td style="text-align:right">2011-08-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131d7d3750131da64c5b10014.jsp" class="toContentMain" title="卫浴市场销售：中端市场为主高低端为辅">卫浴市场销售：中端市场为主高低端为辅</a></td>
          <td style="text-align:right">2011-08-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131d7d3750131da6416460013.jsp" class="toContentMain" title="LED灯具拟通过EMC模式走进室内照明">LED灯具拟通过EMC模式走进室内照明</a></td>
          <td style="text-align:right">2011-08-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131d7d3750131da61399e0011.jsp" class="toContentMain" title="中国钢铁业陷入反倾销泥潭 武钢7个月遭4起调查">中国钢铁业陷入反倾销泥潭 武钢7个月遭4起调查</a></td>
          <td style="text-align:right">2011-08-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131d7d3750131da5fdc730010.jsp" class="toContentMain" title="7月全国十种有色金属产量同比增长9.8%">7月全国十种有色金属产量同比增长9.8%</a></td>
          <td style="text-align:right">2011-08-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131d7d3750131da5f1e4d000f.jsp" class="toContentMain" title="服装品牌与代理商 合作共赢还是分道扬镳">服装品牌与代理商 合作共赢还是分道扬镳</a></td>
          <td style="text-align:right">2011-08-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131d1eaa60131d55df3c60078.jsp" class="toContentMain" title="玩具行业遭遇产销旺利润薄致命生死劫">玩具行业遭遇产销旺利润薄致命生死劫</a></td>
          <td style="text-align:right">2011-08-17</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131d1eaa60131d55b098d0075.jsp" class="toContentMain" title="LED照明灯具系统目前概况及未来发展趋势">LED照明灯具系统目前概况及未来发展趋势</a></td>
          <td style="text-align:right">2011-08-17</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131d1eaa60131d551e50c0061.jsp" class="toContentMain" title="3D彩电“十一”或迎爆发期">3D彩电“十一”或迎爆发期</a></td>
          <td style="text-align:right">2011-08-17</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131d1eaa60131d54eb314005e.jsp" class="toContentMain" title="机械工业国际贸易情况分析">机械工业国际贸易情况分析</a></td>
          <td style="text-align:right">2011-08-17</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131d1eaa60131d54bc7df005b.jsp" class="toContentMain" title="机械加工水平提高对刀具提出新要求">机械加工水平提高对刀具提出新要求</a></td>
          <td style="text-align:right">2011-08-17</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131d1eaa60131d54ad8d3005a.jsp" class="toContentMain" title="电子书会否昙花一现">电子书会否昙花一现</a></td>
          <td style="text-align:right">2011-08-17</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131d1eaa60131d549700f0058.jsp" class="toContentMain" title="太阳能光伏产业明年产值剑指千亿">太阳能光伏产业明年产值剑指千亿</a></td>
          <td style="text-align:right">2011-08-17</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131d1eaa60131d548448d0055.jsp" class="toContentMain" title="太阳能光伏产业：上网电价政策背后的隐忧">太阳能光伏产业：上网电价政策背后的隐忧</a></td>
          <td style="text-align:right">2011-08-17</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131d1eaa60131d54165ea0050.jsp" class="toContentMain" title="上半年工行贵金属业务交易量超过8.5万吨">上半年工行贵金属业务交易量超过8.5万吨</a></td>
          <td style="text-align:right">2011-08-17</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131d1eaa60131d53b6209004f.jsp" class="toContentMain" title="铅酸蓄电池行业或遭整合 半数企业将退出">铅酸蓄电池行业或遭整合 半数企业将退出</a></td>
          <td style="text-align:right">2011-08-17</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131d1eaa60131d53aed75004e.jsp" class="toContentMain" title="上半年汽车出口同比增50.22% 出口均价小幅下降">上半年汽车出口同比增50.22% 出口均价小幅下降</a></td>
          <td style="text-align:right">2011-08-17</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131ccdcaa0131d15358780687.jsp" class="toContentMain" title="价格涨产量增 水泥市场或将现利润拐点">价格涨产量增 水泥市场或将现利润拐点</a></td>
          <td style="text-align:right">2011-08-16</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131ccdcaa0131d152a2eb0686.jsp" class="toContentMain" title="空调企业利润下滑 市场处变革关键期">空调企业利润下滑 市场处变革关键期</a></td>
          <td style="text-align:right">2011-08-16</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131ccdcaa0131d13ff88b067e.jsp" class="toContentMain" title="用标准拯救太阳能热水器行业质量危机">用标准拯救太阳能热水器行业质量危机</a></td>
          <td style="text-align:right">2011-08-16</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_11.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_13.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：12/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
