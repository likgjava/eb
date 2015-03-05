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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a013164127f4e02df.jsp" class="toContentMain" title="五金行业将高速震荡 橱柜品牌两极分化">五金行业将高速震荡 橱柜品牌两极分化</a></td>
          <td style="text-align:right">2011-07-26</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a0131641025e702de.jsp" class="toContentMain" title="山东光伏新能源出口4.23亿美元 同比增长96%">山东光伏新能源出口4.23亿美元 同比增长96%</a></td>
          <td style="text-align:right">2011-07-26</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a0131640f0b9a02dd.jsp" class="toContentMain" title="新能源汽车电池 碳酸锂上涨趋势基本确立">新能源汽车电池 碳酸锂上涨趋势基本确立</a></td>
          <td style="text-align:right">2011-07-26</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a0131640db03802dc.jsp" class="toContentMain" title="调查称35%美国消费者愿意购买iPhone 5">调查称35%美国消费者愿意购买iPhone 5</a></td>
          <td style="text-align:right">2011-07-26</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a0131640c24b902db.jsp" class="toContentMain" title="高端白酒年利润增长或逾百分之三十">高端白酒年利润增长或逾百分之三十</a></td>
          <td style="text-align:right">2011-07-26</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a0131640ad99802da.jsp" class="toContentMain" title="中国和日本LED照明市场需求量最大">中国和日本LED照明市场需求量最大</a></td>
          <td style="text-align:right">2011-07-26</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a01316407da4902d8.jsp" class="toContentMain" title="前5月中国印染纺织服装外贸额达40亿美元">前5月中国印染纺织服装外贸额达40亿美元</a></td>
          <td style="text-align:right">2011-07-26</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a01316406af4802d6.jsp" class="toContentMain" title="棉花价格跳水服装仍在观望">棉花价格跳水服装仍在观望</a></td>
          <td style="text-align:right">2011-07-26</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a01316403646902d5.jsp" class="toContentMain" title="白酒、啤酒继续保持高增速">白酒、啤酒继续保持高增速</a></td>
          <td style="text-align:right">2011-07-26</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a0131640240bc02d4.jsp" class="toContentMain" title="海上风电将进入发展迅猛期 涂料需求增大">海上风电将进入发展迅猛期 涂料需求增大</a></td>
          <td style="text-align:right">2011-07-26</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a0131640162ac02d3.jsp" class="toContentMain" title="LED企业蜂拥上市 光鲜难掩发展难题">LED企业蜂拥上市 光鲜难掩发展难题</a></td>
          <td style="text-align:right">2011-07-26</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a013163f8fb5502d0.jsp" class="toContentMain" title="新能源汽车技术路线图仍未明 车企两条腿走路">新能源汽车技术路线图仍未明 车企两条腿走路</a></td>
          <td style="text-align:right">2011-07-26</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a013163f4f21802ca.jsp" class="toContentMain" title="稀土产品价格暴涨 专家称担忧成本持续承压">稀土产品价格暴涨 专家称担忧成本持续承压</a></td>
          <td style="text-align:right">2011-07-26</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a013163f3d13302c9.jsp" class="toContentMain" title="国税总局出增值税新规 食用油企业避税途径被堵">国税总局出增值税新规 食用油企业避税途径被堵</a></td>
          <td style="text-align:right">2011-07-26</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a013163f1ec9b02c8.jsp" class="toContentMain" title="中国拟投巨资海外开采铁矿石 打破三巨头垄断">中国拟投巨资海外开采铁矿石 打破三巨头垄断</a></td>
          <td style="text-align:right">2011-07-26</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a013163e8b2e102c3.jsp" class="toContentMain" title="CMIC：2011年我国LED照明市场需求分析">CMIC：2011年我国LED照明市场需求分析</a></td>
          <td style="text-align:right">2011-07-26</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a01315f26f7ab010f.jsp" class="toContentMain" title="印刷复制业仍是新闻出版业中的支柱产业">印刷复制业仍是新闻出版业中的支柱产业</a></td>
          <td style="text-align:right">2011-07-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a01315f260c92010e.jsp" class="toContentMain" title="电子化学品市场成为企业掘金新热土">电子化学品市场成为企业掘金新热土</a></td>
          <td style="text-align:right">2011-07-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a01315f23b7a9010d.jsp" class="toContentMain" title="太阳能光伏：触底反弹行业巨头加速扩产">太阳能光伏：触底反弹行业巨头加速扩产</a></td>
          <td style="text-align:right">2011-07-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a01315f21dcf2010c.jsp" class="toContentMain" title="太阳能路灯常识及太阳能路灯的组成和特点">太阳能路灯常识及太阳能路灯的组成和特点</a></td>
          <td style="text-align:right">2011-07-25</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_21.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_23.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：22/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
