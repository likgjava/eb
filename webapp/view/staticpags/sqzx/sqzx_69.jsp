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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812eec2137012eff257c0e1892.jsp" class="toContentMain" title="福岛地震或影响电子产品行业">福岛地震或影响电子产品行业</a></td>
          <td style="text-align:right">2011-03-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812eec2137012efb538adf1020.jsp" class="toContentMain" title="断桥铝门窗成本揭秘 明明白白选门窗">断桥铝门窗成本揭秘 明明白白选门窗</a></td>
          <td style="text-align:right">2011-03-28</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812eec2137012efb4ca4e1101e.jsp" class="toContentMain" title="业内人士点评LED投资浪潮再现">业内人士点评LED投资浪潮再现</a></td>
          <td style="text-align:right">2011-03-28</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812eec2137012efb4978f2101c.jsp" class="toContentMain" title="灯饰营销中的“她经济”不容忽视">灯饰营销中的“她经济”不容忽视</a></td>
          <td style="text-align:right">2011-03-28</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812eec2137012efb47db37101a.jsp" class="toContentMain" title="比亚迪放弃传统家电行业 着力汽车、新能源、IT领域发展">比亚迪放弃传统家电行业 着力汽车、新能源、IT领域发展</a></td>
          <td style="text-align:right">2011-03-28</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812eec2137012efb4204801017.jsp" class="toContentMain" title="变频空调四角鼎立 发展服务才是关键">变频空调四角鼎立 发展服务才是关键</a></td>
          <td style="text-align:right">2011-03-28</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812eec2137012efb3b83f31013.jsp" class="toContentMain" title="门窗品质大幅升级成中国住宅建筑新趋势">门窗品质大幅升级成中国住宅建筑新趋势</a></td>
          <td style="text-align:right">2011-03-28</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812eec2137012efb35e774100c.jsp" class="toContentMain" title="2011年的玻璃行业很有看头">2011年的玻璃行业很有看头</a></td>
          <td style="text-align:right">2011-03-28</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812eec2137012efb1ce46e0fff.jsp" class="toContentMain" title="2011三层实木地板受公认 或将引爆市场">2011三层实木地板受公认 或将引爆市场</a></td>
          <td style="text-align:right">2011-03-28</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812eec2137012efa6458c30bd8.jsp" class="toContentMain" title="雨污分流管材“寿命”长达百年以上">雨污分流管材“寿命”长达百年以上</a></td>
          <td style="text-align:right">2011-03-28</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812eec2137012efa4145ec0a2a.jsp" class="toContentMain" title="LED照明产业前景诱人 但需注意技术发展是核心">LED照明产业前景诱人 但需注意技术发展是核心</a></td>
          <td style="text-align:right">2011-03-28</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ee243a4012eebde308c1e90.jsp" class="toContentMain" title="灯具连锁专卖市场走俏 品牌看服务">灯具连锁专卖市场走俏 品牌看服务</a></td>
          <td style="text-align:right">2011-03-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ee243a4012eebcd0ca11e01.jsp" class="toContentMain" title="震断货源 大陆电子商品价飙">震断货源 大陆电子商品价飙</a></td>
          <td style="text-align:right">2011-03-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ee243a4012eebc43e251d4a.jsp" class="toContentMain" title="“回南天”反复临时需求激增 除湿机热销">“回南天”反复临时需求激增 除湿机热销</a></td>
          <td style="text-align:right">2011-03-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ee243a4012eebbcb0da1d03.jsp" class="toContentMain" title="家电商体验电子商务火箭速度">家电商体验电子商务火箭速度</a></td>
          <td style="text-align:right">2011-03-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ee243a4012eeba51de91c0d.jsp" class="toContentMain" title="3D或成为医疗显示器市场主打力量">3D或成为医疗显示器市场主打力量</a></td>
          <td style="text-align:right">2011-03-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ee243a4012eeba3c90b1c01.jsp" class="toContentMain" title="面对市场竞争 国内家具品牌走向国际化">面对市场竞争 国内家具品牌走向国际化</a></td>
          <td style="text-align:right">2011-03-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ee243a4012eead625eb1a66.jsp" class="toContentMain" title="业内人士如何看待LED投资浪潮再现？">业内人士如何看待LED投资浪潮再现？</a></td>
          <td style="text-align:right">2011-03-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ee243a4012eeac70d461a53.jsp" class="toContentMain" title="商家纷纷加价电子产品 真正考验还在后头">商家纷纷加价电子产品 真正考验还在后头</a></td>
          <td style="text-align:right">2011-03-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ee243a4012eeac56f811a51.jsp" class="toContentMain" title="低碳时代 家电行业盛行“绿色环保”风">低碳时代 家电行业盛行“绿色环保”风</a></td>
          <td style="text-align:right">2011-03-25</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_68.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_70.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：69/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
