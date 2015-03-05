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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081304e848f013076feb89d4226.jsp" class="toContentMain" title="我国LED企业经营和管理存在的六个主要问题">我国LED企业经营和管理存在的六个主要问题</a></td>
          <td style="text-align:right">2011-06-10</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081304e848f013076fd81fa4143.jsp" class="toContentMain" title="行业在整顿 汽车后市场在吹和煦风？">行业在整顿 汽车后市场在吹和煦风？</a></td>
          <td style="text-align:right">2011-06-10</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081304e848f013071f1e12930ab.jsp" class="toContentMain" title="玉米价格半年涨两成深加工企业利润缩水">玉米价格半年涨两成深加工企业利润缩水</a></td>
          <td style="text-align:right">2011-06-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081304e848f013071ee5bd33097.jsp" class="toContentMain" title="中国食品开发高档酒产品">中国食品开发高档酒产品</a></td>
          <td style="text-align:right">2011-06-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081304e848f013071e64e3b307d.jsp" class="toContentMain" title="美国是最大汽车零件市场 待中国企业进入">美国是最大汽车零件市场 待中国企业进入</a></td>
          <td style="text-align:right">2011-06-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081304e848f013071e494443071.jsp" class="toContentMain" title="中国服装需求旺 外资催化剂公司闷声发财">中国服装需求旺 外资催化剂公司闷声发财</a></td>
          <td style="text-align:right">2011-06-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081304e848f013071e1b0db3062.jsp" class="toContentMain" title="中国国内铜需求已经恢复到金融危机前夕高位">中国国内铜需求已经恢复到金融危机前夕高位</a></td>
          <td style="text-align:right">2011-06-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081304e848f013071d4ad96303a.jsp" class="toContentMain" title="全球农副产品将继续保持高价位">全球农副产品将继续保持高价位</a></td>
          <td style="text-align:right">2011-06-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081304e848f013071d15e393034.jsp" class="toContentMain" title="3%-5% 发改委称物价上涨或成常态">3%-5% 发改委称物价上涨或成常态</a></td>
          <td style="text-align:right">2011-06-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081304e848f013071d0cce53032.jsp" class="toContentMain" title="高煤价高在中间环节 煤炭专用通道能否根治顽疾？">高煤价高在中间环节 煤炭专用通道能否根治顽疾？</a></td>
          <td style="text-align:right">2011-06-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081304e848f013071d000ef3030.jsp" class="toContentMain" title="汇丰银行:铂系金属将迎强劲升势">汇丰银行:铂系金属将迎强劲升势</a></td>
          <td style="text-align:right">2011-06-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081304e848f01306ccd917c232b.jsp" class="toContentMain" title="中国电梯市场面临转型 “上上下下”如何打通经络？">中国电梯市场面临转型 “上上下下”如何打通经络？</a></td>
          <td style="text-align:right">2011-06-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081304e848f01306ccbcfa92329.jsp" class="toContentMain" title="广东LED产业明年规模达1200亿">广东LED产业明年规模达1200亿</a></td>
          <td style="text-align:right">2011-06-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081304e848f01306ccaf4de2327.jsp" class="toContentMain" title="多晶硅项目审批有望开闸">多晶硅项目审批有望开闸</a></td>
          <td style="text-align:right">2011-06-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081304e848f01306cc9108e2325.jsp" class="toContentMain" title="粮农组织：全球高粮价将持续到明年">粮农组织：全球高粮价将持续到明年</a></td>
          <td style="text-align:right">2011-06-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081304e848f01306cc5041f2321.jsp" class="toContentMain" title="中国化工网提供的数据显示，在其监测的68种化工品中，5月份价格下跌的占57.4%，价格上涨的仅占32.4%。总体来看，化工市场呈现跌多涨少、跌势凶猛的局面。不过，纯碱等部分化工品依然强势上涨，并且已经得到二级市场的追捧。">中国化工网提供的数据显示，在其监测的68种化工品中，5月份价格下跌的占57.4%，价格上涨的仅占32.4%。总体来看，化工市场呈现跌多涨少、跌势凶猛的局面。不过，纯碱等部分化工品依然强势上涨，并且已经得到二级市场的追捧。</a></td>
          <td style="text-align:right">2011-06-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081304e848f01306cc321a4231d.jsp" class="toContentMain" title="部分化工品价格仍强势上涨">部分化工品价格仍强势上涨</a></td>
          <td style="text-align:right">2011-06-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081304e848f01306cc2710a231b.jsp" class="toContentMain" title="水电油气价格改革可能提速">水电油气价格改革可能提速</a></td>
          <td style="text-align:right">2011-06-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081304e848f01306cc1786d2319.jsp" class="toContentMain" title="日本公司宣布稀土涨价4成 可能带来跟风涨价">日本公司宣布稀土涨价4成 可能带来跟风涨价</a></td>
          <td style="text-align:right">2011-06-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081304e848f01306cbe2baa2317.jsp" class="toContentMain" title="我市开展进出口食品非法添加剂专项整治">我市开展进出口食品非法添加剂专项整治</a></td>
          <td style="text-align:right">2011-06-08</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_38.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_40.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：39/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
