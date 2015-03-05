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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f56f9b3c614d4.jsp" class="toContentMain" title="LED制造成本下降 2011年LED灯具渗透率增加">LED制造成本下降 2011年LED灯具渗透率增加</a></td>
          <td style="text-align:right">2011-04-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f56f0ddb114be.jsp" class="toContentMain" title="苏宁启动华南空调节 将投入大量特价机型">苏宁启动华南空调节 将投入大量特价机型</a></td>
          <td style="text-align:right">2011-04-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f56ec540814ba.jsp" class="toContentMain" title="印度或强制采购本土电信设备 外资受冲击">印度或强制采购本土电信设备 外资受冲击</a></td>
          <td style="text-align:right">2011-04-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f56eb512414b8.jsp" class="toContentMain" title="今年海交会台湾展位比增50% 将首次办采购洽谈会">今年海交会台湾展位比增50% 将首次办采购洽谈会</a></td>
          <td style="text-align:right">2011-04-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f56e9237114b3.jsp" class="toContentMain" title="进口展优化国内采购商激增">进口展优化国内采购商激增</a></td>
          <td style="text-align:right">2011-04-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f56e0daa114ac.jsp" class="toContentMain" title="CSAE2011 六大看点打造汽车用品金秋第一展">CSAE2011 六大看点打造汽车用品金秋第一展</a></td>
          <td style="text-align:right">2011-04-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f56de9d6b14aa.jsp" class="toContentMain" title="第八届哈尔滨国际家具暨木工机械展览会今日开幕">第八届哈尔滨国际家具暨木工机械展览会今日开幕</a></td>
          <td style="text-align:right">2011-04-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f56dd002814a8.jsp" class="toContentMain" title="新加坡柴油评估价4月14日上涨">新加坡柴油评估价4月14日上涨</a></td>
          <td style="text-align:right">2011-04-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f56d9061714a2.jsp" class="toContentMain" title="中石化高档酒采购惹众怒 一味提价不降内部成本">中石化高档酒采购惹众怒 一味提价不降内部成本</a></td>
          <td style="text-align:right">2011-04-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f56d2e7291497.jsp" class="toContentMain" title="珠三角国际商贸城采购客商联盟成立">珠三角国际商贸城采购客商联盟成立</a></td>
          <td style="text-align:right">2011-04-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f56d156c41495.jsp" class="toContentMain" title="湖北襄阳：书记市长吆喝卖菜 企业商家上门采购">湖北襄阳：书记市长吆喝卖菜 企业商家上门采购</a></td>
          <td style="text-align:right">2011-04-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f56cf54f11493.jsp" class="toContentMain" title="哈市最大公交采购引关注 大公交话题市民问得多">哈市最大公交采购引关注 大公交话题市民问得多</a></td>
          <td style="text-align:right">2011-04-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f56cdee91148f.jsp" class="toContentMain" title="红河州本级一季度采购预算规模逾1400万元">红河州本级一季度采购预算规模逾1400万元</a></td>
          <td style="text-align:right">2011-04-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f56ccde75148d.jsp" class="toContentMain" title="内蒙古1211家医疗机构药品网上集中采购 让利患者近19亿">内蒙古1211家医疗机构药品网上集中采购 让利患者近19亿</a></td>
          <td style="text-align:right">2011-04-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f56c9fdde1482.jsp" class="toContentMain" title="纱厂跌价难刹车 下游采购都在等抄底？">纱厂跌价难刹车 下游采购都在等抄底？</a></td>
          <td style="text-align:right">2011-04-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f56c79e93147e.jsp" class="toContentMain" title="江苏省宿迁市集中采购占总规模9成以上">江苏省宿迁市集中采购占总规模9成以上</a></td>
          <td style="text-align:right">2011-04-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f56c601ae147b.jsp" class="toContentMain" title="郑州政府采购仨月省了1.58亿元 节约率为11.43%">郑州政府采购仨月省了1.58亿元 节约率为11.43%</a></td>
          <td style="text-align:right">2011-04-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f56c1328b1450.jsp" class="toContentMain" title="广交会揭幕 采购商料减3%">广交会揭幕 采购商料减3%</a></td>
          <td style="text-align:right">2011-04-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f56bf7729143b.jsp" class="toContentMain" title="巴西磷肥采购步伐缓慢">巴西磷肥采购步伐缓慢</a></td>
          <td style="text-align:right">2011-04-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f52b4e33b0843.jsp" class="toContentMain" title="全球最大的福田新型节能重卡数字工厂投产">全球最大的福田新型节能重卡数字工厂投产</a></td>
          <td style="text-align:right">2011-04-14</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_59.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_61.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：60/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
