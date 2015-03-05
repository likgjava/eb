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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131c418af0131cafc48870026.jsp" class="toContentMain" title="电子商务势不可挡 未来发展电销或成主角">电子商务势不可挡 未来发展电销或成主角</a></td>
          <td style="text-align:right">2011-08-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131c418af0131cafbbf7b0025.jsp" class="toContentMain" title="中国鞋业将步入“制造”或“创造”阶段">中国鞋业将步入“制造”或“创造”阶段</a></td>
          <td style="text-align:right">2011-08-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131c418af0131cafa9c9d0023.jsp" class="toContentMain" title="涂料企业渠道决策要解决的三个核心问题">涂料企业渠道决策要解决的三个核心问题</a></td>
          <td style="text-align:right">2011-08-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131c418af0131caeb78bd001b.jsp" class="toContentMain" title="行业整顿开始 稀土价格平均下挫已超20%">行业整顿开始 稀土价格平均下挫已超20%</a></td>
          <td style="text-align:right">2011-08-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131c418af0131caeb021f001a.jsp" class="toContentMain" title="铅酸蓄电池行业准入条件或明年上半年出台">铅酸蓄电池行业准入条件或明年上半年出台</a></td>
          <td style="text-align:right">2011-08-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131c418af0131caea78610019.jsp" class="toContentMain" title="经济二次探底担忧加重 汽车行业走势成谜">经济二次探底担忧加重 汽车行业走势成谜</a></td>
          <td style="text-align:right">2011-08-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131c418af0131cae990650018.jsp" class="toContentMain" title="电动车市场环保阵痛 企业老板出动全国找电池">电动车市场环保阵痛 企业老板出动全国找电池</a></td>
          <td style="text-align:right">2011-08-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813192fe7c0131b1c0c7791ce0.jsp" class="toContentMain" title="我国下半年陶瓷业走势预测 机遇与挑战并存">我国下半年陶瓷业走势预测 机遇与挑战并存</a></td>
          <td style="text-align:right">2011-08-10</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813192fe7c0131b1be26f81cdf.jsp" class="toContentMain" title="我国粉末涂料企业未来走势：占据中低端市场">我国粉末涂料企业未来走势：占据中低端市场</a></td>
          <td style="text-align:right">2011-08-10</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813192fe7c0131b1414d581ca4.jsp" class="toContentMain" title="光伏玻璃市场需求与技术成本">光伏玻璃市场需求与技术成本</a></td>
          <td style="text-align:right">2011-08-10</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813192fe7c0131b13f3bc81ca3.jsp" class="toContentMain" title="中国光伏产业何时摘下“打工仔”帽子">中国光伏产业何时摘下“打工仔”帽子</a></td>
          <td style="text-align:right">2011-08-10</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813192fe7c0131b13ea14d1ca2.jsp" class="toContentMain" title="我国纺服出口价格大涨的原因分析">我国纺服出口价格大涨的原因分析</a></td>
          <td style="text-align:right">2011-08-10</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813192fe7c0131b13bc8e71ca0.jsp" class="toContentMain" title="达芬奇事件促行业洗牌 进口家具价格昂贵遭质疑">达芬奇事件促行业洗牌 进口家具价格昂贵遭质疑</a></td>
          <td style="text-align:right">2011-08-10</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813192fe7c0131b13b08aa1c9f.jsp" class="toContentMain" title="稀土上市公司净利翻7倍 下半年价格仍趋涨">稀土上市公司净利翻7倍 下半年价格仍趋涨</a></td>
          <td style="text-align:right">2011-08-10</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813192fe7c0131b13a637c1c9e.jsp" class="toContentMain" title="十二五智能制造装备发展重点明确">十二五智能制造装备发展重点明确</a></td>
          <td style="text-align:right">2011-08-10</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813192fe7c0131b13963821c9d.jsp" class="toContentMain" title="下半年稀土价格或继续高位运行">下半年稀土价格或继续高位运行</a></td>
          <td style="text-align:right">2011-08-10</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813192fe7c0131b138a4f11c9c.jsp" class="toContentMain" title="煤化工五年内或难入商业化 规划项目仅1成落地">煤化工五年内或难入商业化 规划项目仅1成落地</a></td>
          <td style="text-align:right">2011-08-10</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813192fe7c0131b137eb121c9b.jsp" class="toContentMain" title="国内铁矿石价格连续5周走高">国内铁矿石价格连续5周走高</a></td>
          <td style="text-align:right">2011-08-10</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813192fe7c0131ac2f5b52143c.jsp" class="toContentMain" title="太阳能成为“十二五”节能减排主力军">太阳能成为“十二五”节能减排主力军</a></td>
          <td style="text-align:right">2011-08-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813192fe7c0131ac2e7f07143b.jsp" class="toContentMain" title="纺织服装、鞋类及皮包订单自中国向越南增多">纺织服装、鞋类及皮包订单自中国向越南增多</a></td>
          <td style="text-align:right">2011-08-09</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_13.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_15.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：14/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
