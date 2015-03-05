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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813218571c01321873eb64003e.jsp" class="toContentMain" title="涂料市场标准欠缺 潜规则不断涌现">涂料市场标准欠缺 潜规则不断涌现</a></td>
          <td style="text-align:right">2011-08-30</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813218571c0132186e2b260034.jsp" class="toContentMain" title="玻璃钢门窗多年来没有普及的原因分析">玻璃钢门窗多年来没有普及的原因分析</a></td>
          <td style="text-align:right">2011-08-30</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813218571c0132186d81b40033.jsp" class="toContentMain" title="风电行业利润巨降 五年高增长遇拐点">风电行业利润巨降 五年高增长遇拐点</a></td>
          <td style="text-align:right">2011-08-30</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813218571c0132186ba7af0032.jsp" class="toContentMain" title="陶瓷行业应淡季加速三四级市场抢夺">陶瓷行业应淡季加速三四级市场抢夺</a></td>
          <td style="text-align:right">2011-08-30</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813218571c013218698fb70031.jsp" class="toContentMain" title="水暖卫浴与陶瓷行业 跨界经营难度重在终端">水暖卫浴与陶瓷行业 跨界经营难度重在终端</a></td>
          <td style="text-align:right">2011-08-30</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813218571c01321867822c0030.jsp" class="toContentMain" title="我国清洁能源产业发展亟须突破融资瓶颈">我国清洁能源产业发展亟须突破融资瓶颈</a></td>
          <td style="text-align:right">2011-08-30</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813218571c013218667830002f.jsp" class="toContentMain" title="市场越萎缩 高附加值陶瓷产品越有机会">市场越萎缩 高附加值陶瓷产品越有机会</a></td>
          <td style="text-align:right">2011-08-30</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813218571c01321865508e002e.jsp" class="toContentMain" title="卫浴五金品牌定位高低不一 市场扩展泛滥">卫浴五金品牌定位高低不一 市场扩展泛滥</a></td>
          <td style="text-align:right">2011-08-30</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813218571c01321864ed62002d.jsp" class="toContentMain" title="卫浴洁具市场现状分析 突破传统观念">卫浴洁具市场现状分析 突破传统观念</a></td>
          <td style="text-align:right">2011-08-30</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813218571c013218618ed40029.jsp" class="toContentMain" title="节能玻璃是否能复兴美国的玻璃产业">节能玻璃是否能复兴美国的玻璃产业</a></td>
          <td style="text-align:right">2011-08-30</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813218571c0132185f494e0028.jsp" class="toContentMain" title="光伏行业内的两大强者生存法则">光伏行业内的两大强者生存法则</a></td>
          <td style="text-align:right">2011-08-30</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813218571c0132185e8ba50027.jsp" class="toContentMain" title="棉花价格起伏不定 牵动服装企业神经">棉花价格起伏不定 牵动服装企业神经</a></td>
          <td style="text-align:right">2011-08-30</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813218571c0132185d1e1c0025.jsp" class="toContentMain" title="五大模式深度解析涂料行业未来营销趋势">五大模式深度解析涂料行业未来营销趋势</a></td>
          <td style="text-align:right">2011-08-30</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813218571c0132185b73f80024.jsp" class="toContentMain" title="我国包装机械企业举行业之力 未雨绸缪">我国包装机械企业举行业之力 未雨绸缪</a></td>
          <td style="text-align:right">2011-08-30</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813218571c0132185aa1a70023.jsp" class="toContentMain" title="太阳能产业将成为下一个主战场">太阳能产业将成为下一个主战场</a></td>
          <td style="text-align:right">2011-08-30</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813204bef60132133d2b8b0427.jsp" class="toContentMain" title="水性环氧地坪涂料施工条件及适用场所">水性环氧地坪涂料施工条件及适用场所</a></td>
          <td style="text-align:right">2011-08-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813204bef60132133c6f3003e7.jsp" class="toContentMain" title="变频空调将成主流 四强争霸战价格">变频空调将成主流 四强争霸战价格</a></td>
          <td style="text-align:right">2011-08-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813204bef601321330620103d8.jsp" class="toContentMain" title="从光伏到光热：太阳能热发电受关注">从光伏到光热：太阳能热发电受关注</a></td>
          <td style="text-align:right">2011-08-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813204bef601321327e4a003d5.jsp" class="toContentMain" title="全球建筑涂料市场及发展趋势分析">全球建筑涂料市场及发展趋势分析</a></td>
          <td style="text-align:right">2011-08-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813204bef601321326929003d4.jsp" class="toContentMain" title="建筑垃圾“变身”新型建材 前途一片光明">建筑垃圾“变身”新型建材 前途一片光明</a></td>
          <td style="text-align:right">2011-08-29</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_3.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_5.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：4/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
