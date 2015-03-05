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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f71626e012f7721f7f7028e.jsp" class="toContentMain" title="广东抢占LED产业高地 2015年突破3000亿">广东抢占LED产业高地 2015年突破3000亿</a></td>
          <td style="text-align:right">2011-04-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f71626e012f77206029028c.jsp" class="toContentMain" title="净水器发展监管存缺位 行业品牌亟待洗牌">净水器发展监管存缺位 行业品牌亟待洗牌</a></td>
          <td style="text-align:right">2011-04-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f71626e012f771da0b3028a.jsp" class="toContentMain" title="家电企业出口欧美市场需谨慎专利障碍">家电企业出口欧美市场需谨慎专利障碍</a></td>
          <td style="text-align:right">2011-04-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f71626e012f771bc65d0288.jsp" class="toContentMain" title="家电行业：保持平稳增长 加快转型升级">家电行业：保持平稳增长 加快转型升级</a></td>
          <td style="text-align:right">2011-04-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f71626e012f77176bd60286.jsp" class="toContentMain" title="巴斯夫聚氨酯组合料厂在天津开工建设">巴斯夫聚氨酯组合料厂在天津开工建设</a></td>
          <td style="text-align:right">2011-04-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f71626e012f770ff51e0284.jsp" class="toContentMain" title="电线电缆市场：漏泄同轴电缆市场前景广阔">电线电缆市场：漏泄同轴电缆市场前景广阔</a></td>
          <td style="text-align:right">2011-04-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f71626e012f770dcce50282.jsp" class="toContentMain" title="五金产品价格或持续上涨10%至20%">五金产品价格或持续上涨10%至20%</a></td>
          <td style="text-align:right">2011-04-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f71626e012f770bb1770280.jsp" class="toContentMain" title="地板企业聚焦工程市场 工程派pk零售派">地板企业聚焦工程市场 工程派pk零售派</a></td>
          <td style="text-align:right">2011-04-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f71626e012f7707dfea027c.jsp" class="toContentMain" title="主流市场占有率低 中国茶叶出口须拓欧美市场">主流市场占有率低 中国茶叶出口须拓欧美市场</a></td>
          <td style="text-align:right">2011-04-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f71626e012f7703d1b6027a.jsp" class="toContentMain" title="浙南茶叶市场交易额同期增长了57％">浙南茶叶市场交易额同期增长了57％</a></td>
          <td style="text-align:right">2011-04-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f71626e012f77026a7f0278.jsp" class="toContentMain" title="湖南茶叶2020年要成千亿级产业 打造绿茶大省">湖南茶叶2020年要成千亿级产业 打造绿茶大省</a></td>
          <td style="text-align:right">2011-04-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f71626e012f7700fdca0276.jsp" class="toContentMain" title="云南临沧市部分区域茶叶价格大幅增长">云南临沧市部分区域茶叶价格大幅增长</a></td>
          <td style="text-align:right">2011-04-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f71626e012f76fda0a90274.jsp" class="toContentMain" title="九部门紧急通知遏制电解铝行业产能过剩和重复建设">九部门紧急通知遏制电解铝行业产能过剩和重复建设</a></td>
          <td style="text-align:right">2011-04-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f71626e012f76f988160272.jsp" class="toContentMain" title="十二五重庆南岸供电局投28亿电网建设">十二五重庆南岸供电局投28亿电网建设</a></td>
          <td style="text-align:right">2011-04-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f71626e012f76f5cf4d0270.jsp" class="toContentMain" title="19英寸LED背光液晶 宏碁G195WLAb降百元">19英寸LED背光液晶 宏碁G195WLAb降百元</a></td>
          <td style="text-align:right">2011-04-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f71626e012f76f2770d026e.jsp" class="toContentMain" title="LED微投市场迎来战国时代 消费者或将大受裨益">LED微投市场迎来战国时代 消费者或将大受裨益</a></td>
          <td style="text-align:right">2011-04-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f71626e012f76ecfa11026c.jsp" class="toContentMain" title="澳大利亚全力引导LED灯入市">澳大利亚全力引导LED灯入市</a></td>
          <td style="text-align:right">2011-04-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f71626e012f76eb2313026a.jsp" class="toContentMain" title="洞悉北京照明展览 见证LED照明技术最新成果">洞悉北京照明展览 见证LED照明技术最新成果</a></td>
          <td style="text-align:right">2011-04-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f71626e012f7681ac550237.jsp" class="toContentMain" title="渭南高新区“LED产品”亮相西洽会">渭南高新区“LED产品”亮相西洽会</a></td>
          <td style="text-align:right">2011-04-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f71626e012f767e66be0235.jsp" class="toContentMain" title="海南家电以旧换新开始招投标 将招50家相关企业">海南家电以旧换新开始招投标 将招50家相关企业</a></td>
          <td style="text-align:right">2011-04-21</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_50.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_52.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：51/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
