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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a70130f29cba870a54.jsp" class="toContentMain" title="废铜有价无市 价格面临调整">废铜有价无市 价格面临调整</a></td>
          <td style="text-align:right">2011-07-04</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a70130f29be1e50a52.jsp" class="toContentMain" title="中国乳业江湖争论不止：谁在绑架乳业标准">中国乳业江湖争论不止：谁在绑架乳业标准</a></td>
          <td style="text-align:right">2011-07-04</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a70130e34e59b70645.jsp" class="toContentMain" title="迷你洗衣机等时尚家电 收到消费者青睐">迷你洗衣机等时尚家电 收到消费者青睐</a></td>
          <td style="text-align:right">2011-07-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a70130e34d96510643.jsp" class="toContentMain" title="国务院牵头商议 或推新汽车消费鼓励政策">国务院牵头商议 或推新汽车消费鼓励政策</a></td>
          <td style="text-align:right">2011-07-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a70130e34b9f730641.jsp" class="toContentMain" title="太阳能光热：打开电力设备行业又一片天空">太阳能光热：打开电力设备行业又一片天空</a></td>
          <td style="text-align:right">2011-07-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a70130e3497aae063f.jsp" class="toContentMain" title="北方茶叶市场升温 绍兴茶企加快“北进”步伐">北方茶叶市场升温 绍兴茶企加快“北进”步伐</a></td>
          <td style="text-align:right">2011-07-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a70130e347c5ef063d.jsp" class="toContentMain" title="国际顶级红酒发力中国市场">国际顶级红酒发力中国市场</a></td>
          <td style="text-align:right">2011-07-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a70130e33c70a80631.jsp" class="toContentMain" title="半数铅蓄电池企业3年内将出局">半数铅蓄电池企业3年内将出局</a></td>
          <td style="text-align:right">2011-07-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a70130e33b355d062b.jsp" class="toContentMain" title="医疗器械召回今起执行国标 跨国企业不再免责">医疗器械召回今起执行国标 跨国企业不再免责</a></td>
          <td style="text-align:right">2011-07-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a70130e33a8e9b0629.jsp" class="toContentMain" title="今年前5月国内平板电视城市销量现负增长">今年前5月国内平板电视城市销量现负增长</a></td>
          <td style="text-align:right">2011-07-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a70130e3329e1b0615.jsp" class="toContentMain" title="我国塑料需求有望改善">我国塑料需求有望改善</a></td>
          <td style="text-align:right">2011-07-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a70130e32fd9180609.jsp" class="toContentMain" title="白酒：经济增长与消费升级推动奢侈品需求">白酒：经济增长与消费升级推动奢侈品需求</a></td>
          <td style="text-align:right">2011-07-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130c14db40130ceb3859c01d6.jsp" class="toContentMain" title="瞄准千亿目标 家电业是否能迎来战略拐点">瞄准千亿目标 家电业是否能迎来战略拐点</a></td>
          <td style="text-align:right">2011-06-27</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130c14db40130ceb1a5db01c9.jsp" class="toContentMain" title="中药价疯涨 业界称要警惕外资“炒中药材”">中药价疯涨 业界称要警惕外资“炒中药材”</a></td>
          <td style="text-align:right">2011-06-27</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130c14db40130ceaed51701c2.jsp" class="toContentMain" title="我国模具行业渐显地域分布特色分析">我国模具行业渐显地域分布特色分析</a></td>
          <td style="text-align:right">2011-06-27</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130c14db40130cead7d2401ae.jsp" class="toContentMain" title="受大陆与韩国夹击 台湾LED产业面临选择难题">受大陆与韩国夹击 台湾LED产业面临选择难题</a></td>
          <td style="text-align:right">2011-06-27</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130c14db40130ceab90f501a6.jsp" class="toContentMain" title="我国建材塑料门窗业发展环境及趋势分析">我国建材塑料门窗业发展环境及趋势分析</a></td>
          <td style="text-align:right">2011-06-27</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130c14db40130cea922ac0164.jsp" class="toContentMain" title="通用零部件产业“十二五”锁定高端制造">通用零部件产业“十二五”锁定高端制造</a></td>
          <td style="text-align:right">2011-06-27</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130c14db40130ce9f44d20154.jsp" class="toContentMain" title="让视频监控腾飞—3G为监控插上隐形翅膀">让视频监控腾飞—3G为监控插上隐形翅膀</a></td>
          <td style="text-align:right">2011-06-27</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130c14db40130ce9168800140.jsp" class="toContentMain" title="暴雨效应致建筑钢材价格大跌 跌幅存放大趋势">暴雨效应致建筑钢材价格大跌 跌幅存放大趋势</a></td>
          <td style="text-align:right">2011-06-27</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_32.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_34.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：33/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
