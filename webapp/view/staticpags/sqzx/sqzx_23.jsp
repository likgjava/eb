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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a01315f208425010b.jsp" class="toContentMain" title="LED风靡世界 中国和日本需求量最大">LED风靡世界 中国和日本需求量最大</a></td>
          <td style="text-align:right">2011-07-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a01315f1e92bc0109.jsp" class="toContentMain" title="上半年商用车销量降3.67% 同比下降6.07%">上半年商用车销量降3.67% 同比下降6.07%</a></td>
          <td style="text-align:right">2011-07-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a01315f1d5f900108.jsp" class="toContentMain" title="光伏业传触底反弹行业巨头加速扩产">光伏业传触底反弹行业巨头加速扩产</a></td>
          <td style="text-align:right">2011-07-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a01315f1bedce0106.jsp" class="toContentMain" title="国际垄断致钾肥连年涨价 中化呼吁建立淡季储备">国际垄断致钾肥连年涨价 中化呼吁建立淡季储备</a></td>
          <td style="text-align:right">2011-07-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a01315f1b42410105.jsp" class="toContentMain" title="煤化工战略规划缺失致产业乱象丛生">煤化工战略规划缺失致产业乱象丛生</a></td>
          <td style="text-align:right">2011-07-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a01315f11aaa000f7.jsp" class="toContentMain" title="云计算与企业需求匹配最重要">云计算与企业需求匹配最重要</a></td>
          <td style="text-align:right">2011-07-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813140dc4701314f82611412c8.jsp" class="toContentMain" title="我国电子元件有望进入稳定增长期">我国电子元件有望进入稳定增长期</a></td>
          <td style="text-align:right">2011-07-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813140dc4701314f7ffcc212c6.jsp" class="toContentMain" title="中国将对太阳能光伏产业标准进行整治">中国将对太阳能光伏产业标准进行整治</a></td>
          <td style="text-align:right">2011-07-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813140dc4701314f7e100512c5.jsp" class="toContentMain" title="食品饮料是包装机械行业最大的细分市场">食品饮料是包装机械行业最大的细分市场</a></td>
          <td style="text-align:right">2011-07-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813140dc4701314f7ac89c12c3.jsp" class="toContentMain" title="医药产业运行总体向好 中成药产值增幅行业第一">医药产业运行总体向好 中成药产值增幅行业第一</a></td>
          <td style="text-align:right">2011-07-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813140dc4701314f7646aa12bf.jsp" class="toContentMain" title="世界最大太阳能发电站预计2013年建成">世界最大太阳能发电站预计2013年建成</a></td>
          <td style="text-align:right">2011-07-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813140dc4701314f75b52a12be.jsp" class="toContentMain" title="显示技术未来走向 裸眼3D技术成大势所趋">显示技术未来走向 裸眼3D技术成大势所趋</a></td>
          <td style="text-align:right">2011-07-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813140dc4701314f71911612b9.jsp" class="toContentMain" title="卫浴中小企业不能靠打价格战发展">卫浴中小企业不能靠打价格战发展</a></td>
          <td style="text-align:right">2011-07-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813140dc4701314f6fe96812b7.jsp" class="toContentMain" title="本土零部件企业准备去日本寻商机">本土零部件企业准备去日本寻商机</a></td>
          <td style="text-align:right">2011-07-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813140dc4701314f6a3b4e1242.jsp" class="toContentMain" title="食用油企否认提价 部分超市已接口头涨价通知">食用油企否认提价 部分超市已接口头涨价通知</a></td>
          <td style="text-align:right">2011-07-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813140dc4701314f69810f1241.jsp" class="toContentMain" title="上海食用油价暂无明显波动 囤货现象乍现市场">上海食用油价暂无明显波动 囤货现象乍现市场</a></td>
          <td style="text-align:right">2011-07-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813140dc4701314f6872451240.jsp" class="toContentMain" title="白酒类金融化趋势明显 环境尚未成熟暗藏风险">白酒类金融化趋势明显 环境尚未成熟暗藏风险</a></td>
          <td style="text-align:right">2011-07-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813140dc4701314a4d1eef0b13.jsp" class="toContentMain" title="零部件成为工程机械行业亟待改善的重点">零部件成为工程机械行业亟待改善的重点</a></td>
          <td style="text-align:right">2011-07-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813140dc4701314a4bcf770b12.jsp" class="toContentMain" title="太阳能碳纳米管染料或取代电池">太阳能碳纳米管染料或取代电池</a></td>
          <td style="text-align:right">2011-07-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813140dc4701314a4b0e140b11.jsp" class="toContentMain" title="机械工业瓶颈呼唤转型 迈入做强关键阶段">机械工业瓶颈呼唤转型 迈入做强关键阶段</a></td>
          <td style="text-align:right">2011-07-21</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_22.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_24.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：23/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
