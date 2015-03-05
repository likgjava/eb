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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813078a95601308bb602cb12b5.jsp" class="toContentMain" title="零售业进场费推高物价 食利型模式亟待转型">零售业进场费推高物价 食利型模式亟待转型</a></td>
          <td style="text-align:right">2011-06-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813078a95601308694e56d0435.jsp" class="toContentMain" title="原料价格大涨 服装企业库存压力陡增">原料价格大涨 服装企业库存压力陡增</a></td>
          <td style="text-align:right">2011-06-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813078a9560130868d9d0f03df.jsp" class="toContentMain" title="全球五金行业市场发展状况及趋势分析">全球五金行业市场发展状况及趋势分析</a></td>
          <td style="text-align:right">2011-06-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813078a9560130868cdaeb03dc.jsp" class="toContentMain" title="市场升温 涂料卖场商圈化经营成主流方向">市场升温 涂料卖场商圈化经营成主流方向</a></td>
          <td style="text-align:right">2011-06-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813078a9560130868a1cb503ca.jsp" class="toContentMain" title="各国纷纷发展机床再制造产业">各国纷纷发展机床再制造产业</a></td>
          <td style="text-align:right">2011-06-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813078a95601308686595403b8.jsp" class="toContentMain" title="LED照明产业受困于上游 出台标准成当务之急">LED照明产业受困于上游 出台标准成当务之急</a></td>
          <td style="text-align:right">2011-06-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813078a9560130868551a203b2.jsp" class="toContentMain" title="风机问题或一两年内爆发 专家称中国必须发展核电">风机问题或一两年内爆发 专家称中国必须发展核电</a></td>
          <td style="text-align:right">2011-06-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813078a956013086844c0e03b0.jsp" class="toContentMain" title="无污染难敌价低 电动自行车企不愿弃铅酸电池取锂电池">无污染难敌价低 电动自行车企不愿弃铅酸电池取锂电池</a></td>
          <td style="text-align:right">2011-06-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813078a9560130866d78c30352.jsp" class="toContentMain" title="南方稀土面临大洗牌 前三名单揭晓在即">南方稀土面临大洗牌 前三名单揭晓在即</a></td>
          <td style="text-align:right">2011-06-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813078a9560130866a4408034e.jsp" class="toContentMain" title="前5月铁矿石进口多花上千亿 总量增速下降">前5月铁矿石进口多花上千亿 总量增速下降</a></td>
          <td style="text-align:right">2011-06-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813078a9560130866998e4034c.jsp" class="toContentMain" title="成品油调价陷迷雾 定价权或下放给石油三巨头">成品油调价陷迷雾 定价权或下放给石油三巨头</a></td>
          <td style="text-align:right">2011-06-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813078a95601308668ef64034a.jsp" class="toContentMain" title="发改委要求 产煤大省取缔煤炭出省限制">发改委要求 产煤大省取缔煤炭出省限制</a></td>
          <td style="text-align:right">2011-06-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081304e848f013077158f8d427e.jsp" class="toContentMain" title="服务外包产业有望成为中国中小企业发展沃土">服务外包产业有望成为中国中小企业发展沃土</a></td>
          <td style="text-align:right">2011-06-10</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081304e848f01307711ef594272.jsp" class="toContentMain" title="国际石油供应吃紧 国内市场旺季不旺">国际石油供应吃紧 国内市场旺季不旺</a></td>
          <td style="text-align:right">2011-06-10</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081304e848f013077093226424e.jsp" class="toContentMain" title="9日又有16款产品新进塑化剂黑名单">9日又有16款产品新进塑化剂黑名单</a></td>
          <td style="text-align:right">2011-06-10</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081304e848f01307707c6fb424c.jsp" class="toContentMain" title="汽车产品召回年内出台 轮胎生产标准将更高">汽车产品召回年内出台 轮胎生产标准将更高</a></td>
          <td style="text-align:right">2011-06-10</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081304e848f01307705ea064241.jsp" class="toContentMain" title="汽车零件进口大幅增长 国内同类产业亟待发展">汽车零件进口大幅增长 国内同类产业亟待发展</a></td>
          <td style="text-align:right">2011-06-10</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081304e848f01307704ed7c423c.jsp" class="toContentMain" title="工信部下发紧急通知 杜绝食品非法添加行为">工信部下发紧急通知 杜绝食品非法添加行为</a></td>
          <td style="text-align:right">2011-06-10</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081304e848f013077036fe0423a.jsp" class="toContentMain" title="至2015年全球管材塑料市场年增长5.8%">至2015年全球管材塑料市场年增长5.8%</a></td>
          <td style="text-align:right">2011-06-10</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081304e848f013077017c794233.jsp" class="toContentMain" title="陕西西安将建东城纺织服装商贸中心">陕西西安将建东城纺织服装商贸中心</a></td>
          <td style="text-align:right">2011-06-10</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_37.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_39.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：38/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
