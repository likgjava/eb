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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131db79dc0131dfa25c1d0259.jsp" class="toContentMain" title="工程机械增速放缓 高铁减速再次施压">工程机械增速放缓 高铁减速再次施压</a></td>
          <td style="text-align:right">2011-08-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131db79dc0131dfa185990258.jsp" class="toContentMain" title="陶瓷市场供大于求 行业在逆境中求发展">陶瓷市场供大于求 行业在逆境中求发展</a></td>
          <td style="text-align:right">2011-08-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131db79dc0131df9f45e20256.jsp" class="toContentMain" title="太阳能光伏市场向好 中国有望实现平价上网">太阳能光伏市场向好 中国有望实现平价上网</a></td>
          <td style="text-align:right">2011-08-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131db79dc0131df9ec96a0255.jsp" class="toContentMain" title="纺织服装行业股纷纷活跃 7天反弹6%">纺织服装行业股纷纷活跃 7天反弹6%</a></td>
          <td style="text-align:right">2011-08-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131db79dc0131df9db98c0254.jsp" class="toContentMain" title="月饼盒内的小包装也要标生产日期">月饼盒内的小包装也要标生产日期</a></td>
          <td style="text-align:right">2011-08-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131db79dc0131df9cd3d50253.jsp" class="toContentMain" title="LED产品叫好不叫座 商照遭遇高成本难题">LED产品叫好不叫座 商照遭遇高成本难题</a></td>
          <td style="text-align:right">2011-08-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131db79dc0131df9c16440252.jsp" class="toContentMain" title="2012年全球棉花产量上升 预期致棉价下跌">2012年全球棉花产量上升 预期致棉价下跌</a></td>
          <td style="text-align:right">2011-08-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131db79dc0131df9b90d40251.jsp" class="toContentMain" title="“十二五”期间五金机床行业竞争加剧">“十二五”期间五金机床行业竞争加剧</a></td>
          <td style="text-align:right">2011-08-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131db79dc0131df9a94de0250.jsp" class="toContentMain" title="LGD新厂喊卡 面板业松口气">LGD新厂喊卡 面板业松口气</a></td>
          <td style="text-align:right">2011-08-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131db79dc0131df9a2fb5024f.jsp" class="toContentMain" title="年内上报待批光伏发电装机已达约3.6GW">年内上报待批光伏发电装机已达约3.6GW</a></td>
          <td style="text-align:right">2011-08-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131db79dc0131df98e14a024e.jsp" class="toContentMain" title="中国拟提高再生铅行业准入门槛">中国拟提高再生铅行业准入门槛</a></td>
          <td style="text-align:right">2011-08-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131db79dc0131df9879cd024d.jsp" class="toContentMain" title="医保总额预付制改革阻力多 药业竞争将加剧">医保总额预付制改革阻力多 药业竞争将加剧</a></td>
          <td style="text-align:right">2011-08-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131dabeec0131daffcaf9002d.jsp" class="toContentMain" title="透析中国五金业：离世界先进还有多远？">透析中国五金业：离世界先进还有多远？</a></td>
          <td style="text-align:right">2011-08-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131dabeec0131dafa87270028.jsp" class="toContentMain" title="国内玩具标准不能总是跟着国外屁股后面跑">国内玩具标准不能总是跟着国外屁股后面跑</a></td>
          <td style="text-align:right">2011-08-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131dabeec0131daf863690027.jsp" class="toContentMain" title="五金阀门市场：两大扰心问题亟待解决">五金阀门市场：两大扰心问题亟待解决</a></td>
          <td style="text-align:right">2011-08-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131dabeec0131daf65b6a0025.jsp" class="toContentMain" title="塑钢门窗发展举步维艰 技术匮乏成关键">塑钢门窗发展举步维艰 技术匮乏成关键</a></td>
          <td style="text-align:right">2011-08-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131dabeec0131daf553590024.jsp" class="toContentMain" title="揭秘中国模具行业亟待转变的“七宗罪”">揭秘中国模具行业亟待转变的“七宗罪”</a></td>
          <td style="text-align:right">2011-08-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131dabeec0131daf3611f001c.jsp" class="toContentMain" title="中国轻纺城市场：秋季涤纶长丝面料款增量升">中国轻纺城市场：秋季涤纶长丝面料款增量升</a></td>
          <td style="text-align:right">2011-08-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131dabeec0131daf00923001a.jsp" class="toContentMain" title="2011地暖行业地热地板市场争夺战打响">2011地暖行业地热地板市场争夺战打响</a></td>
          <td style="text-align:right">2011-08-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131d7d3750131da667cb70016.jsp" class="toContentMain" title="电子商务有望成就家电零售业的新大陆">电子商务有望成就家电零售业的新大陆</a></td>
          <td style="text-align:right">2011-08-18</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_10.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_12.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：11/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
