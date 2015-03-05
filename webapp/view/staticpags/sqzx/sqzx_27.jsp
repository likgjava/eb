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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813118359201312b78f10f246a.jsp" class="toContentMain" title="高压变频器促工业产业链协调发展">高压变频器促工业产业链协调发展</a></td>
          <td style="text-align:right">2011-07-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813118359201312b70c5842458.jsp" class="toContentMain" title="高压变频器行业“价格战”将蔓延">高压变频器行业“价格战”将蔓延</a></td>
          <td style="text-align:right">2011-07-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813118359201312b6accf42456.jsp" class="toContentMain" title="轮胎特保案掀贸易摩擦高发期">轮胎特保案掀贸易摩擦高发期</a></td>
          <td style="text-align:right">2011-07-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813118359201312b6960712452.jsp" class="toContentMain" title="电梯新国标近期将实施 弃用老欧标改用09年欧标">电梯新国标近期将实施 弃用老欧标改用09年欧标</a></td>
          <td style="text-align:right">2011-07-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813118359201312b68e57d2450.jsp" class="toContentMain" title="造纸业近一成落后产能将被淘汰">造纸业近一成落后产能将被淘汰</a></td>
          <td style="text-align:right">2011-07-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813118359201312b684823244e.jsp" class="toContentMain" title="十年后进口天然气将占一半 业界建言提升定价权">十年后进口天然气将占一半 业界建言提升定价权</a></td>
          <td style="text-align:right">2011-07-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813118359201312b67af112448.jsp" class="toContentMain" title="豆油企业榨一吨亏200元 677万吨进口大豆睡港口">豆油企业榨一吨亏200元 677万吨进口大豆睡港口</a></td>
          <td style="text-align:right">2011-07-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813118359201312b668a952444.jsp" class="toContentMain" title="今年稀土出口配额超预期大幅增加 同比增97.3%">今年稀土出口配额超预期大幅增加 同比增97.3%</a></td>
          <td style="text-align:right">2011-07-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081311835920131264bfdfb19a4.jsp" class="toContentMain" title="太阳能热水器已经进入停摆期">太阳能热水器已经进入停摆期</a></td>
          <td style="text-align:right">2011-07-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081311835920131263d03151955.jsp" class="toContentMain" title="太阳能热水器：趁“热”打铁 挺军进城">太阳能热水器：趁“热”打铁 挺军进城</a></td>
          <td style="text-align:right">2011-07-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081311835920131263c0b651953.jsp" class="toContentMain" title="智能3D电视成各彩电厂商最核心战略级产品">智能3D电视成各彩电厂商最核心战略级产品</a></td>
          <td style="text-align:right">2011-07-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081311835920131263444c0192a.jsp" class="toContentMain" title="细读太阳能灯具对比市电灯具之五大优势">细读太阳能灯具对比市电灯具之五大优势</a></td>
          <td style="text-align:right">2011-07-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081311835920131262fec3a1921.jsp" class="toContentMain" title="触控面板需求夯今年全球产值年成长达90%">触控面板需求夯今年全球产值年成长达90%</a></td>
          <td style="text-align:right">2011-07-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081311835920131262c6f58191d.jsp" class="toContentMain" title="凉茶产销量超越可口可乐 近年翻番速度发展">凉茶产销量超越可口可乐 近年翻番速度发展</a></td>
          <td style="text-align:right">2011-07-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081311835920131262aeca5191b.jsp" class="toContentMain" title="产业集群优势仍将保障中国制造前行">产业集群优势仍将保障中国制造前行</a></td>
          <td style="text-align:right">2011-07-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081311835920131262a0f9c1917.jsp" class="toContentMain" title="加多宝为首的凉茶企业签公约：秘方不可随便卖">加多宝为首的凉茶企业签公约：秘方不可随便卖</a></td>
          <td style="text-align:right">2011-07-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813118359201312628ea551915.jsp" class="toContentMain" title="中国纺织品服装出口贸易额前五个月增二成六">中国纺织品服装出口贸易额前五个月增二成六</a></td>
          <td style="text-align:right">2011-07-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813118359201312627cdb71913.jsp" class="toContentMain" title="今年铝淘汰规模占运行产能3%">今年铝淘汰规模占运行产能3%</a></td>
          <td style="text-align:right">2011-07-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131183592013126274c00190f.jsp" class="toContentMain" title="中国稀土行业协会雏形初具">中国稀土行业协会雏形初具</a></td>
          <td style="text-align:right">2011-07-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813118359201312626b8fa190d.jsp" class="toContentMain" title="电子商会：今年3D电视销量有望超预期">电子商会：今年3D电视销量有望超预期</a></td>
          <td style="text-align:right">2011-07-14</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_26.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_28.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：27/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
