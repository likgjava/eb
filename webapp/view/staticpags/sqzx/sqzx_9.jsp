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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131f188870131f4284ffe0024.jsp" class="toContentMain" title="钛白价格2010年7月以来涨幅已达80%">钛白价格2010年7月以来涨幅已达80%</a></td>
          <td style="text-align:right">2011-08-23</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131f188870131f42762610023.jsp" class="toContentMain" title="渠道变革 橱柜行业电子商务路在何方？">渠道变革 橱柜行业电子商务路在何方？</a></td>
          <td style="text-align:right">2011-08-23</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131f188870131f4269fbf0022.jsp" class="toContentMain" title="国内彩电企业采用3D显示模式摇摆不定">国内彩电企业采用3D显示模式摇摆不定</a></td>
          <td style="text-align:right">2011-08-23</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131f188870131f42521c6001e.jsp" class="toContentMain" title="中国汽车市场到底是谁的">中国汽车市场到底是谁的</a></td>
          <td style="text-align:right">2011-08-23</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131f188870131f4242a01001c.jsp" class="toContentMain" title="变频器应用在国外日趋普及的基本认识">变频器应用在国外日趋普及的基本认识</a></td>
          <td style="text-align:right">2011-08-23</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131f188870131f4227780001b.jsp" class="toContentMain" title="童装行业爆发式增长的时代即将到来">童装行业爆发式增长的时代即将到来</a></td>
          <td style="text-align:right">2011-08-23</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131f188870131f421a7dd001a.jsp" class="toContentMain" title="棉花库存高企需求不旺 纺企不敢“囤棉花”">棉花库存高企需求不旺 纺企不敢“囤棉花”</a></td>
          <td style="text-align:right">2011-08-23</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131f188870131f4213cdd0019.jsp" class="toContentMain" title="通胀加紧缩 中国纺织服装企业该如何走">通胀加紧缩 中国纺织服装企业该如何走</a></td>
          <td style="text-align:right">2011-08-23</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131f188870131f42003b00018.jsp" class="toContentMain" title="安防行业即将进入新一轮的高速增长期">安防行业即将进入新一轮的高速增长期</a></td>
          <td style="text-align:right">2011-08-23</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131f188870131f41c68790017.jsp" class="toContentMain" title="农机工业发展政策公布 鼓励兼并重组">农机工业发展政策公布 鼓励兼并重组</a></td>
          <td style="text-align:right">2011-08-23</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131f188870131f41b79280016.jsp" class="toContentMain" title="工信部：九条金融财税政策支持农机业">工信部：九条金融财税政策支持农机业</a></td>
          <td style="text-align:right">2011-08-23</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131f188870131f41ab23e0015.jsp" class="toContentMain" title="我国白酒产品的利润年增长率达到60%">我国白酒产品的利润年增长率达到60%</a></td>
          <td style="text-align:right">2011-08-23</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131f188870131f41a4c6e000e.jsp" class="toContentMain" title="动车车轴裂纹指责影响北车业绩">动车车轴裂纹指责影响北车业绩</a></td>
          <td style="text-align:right">2011-08-23</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131ef3a550131ef5443c40013.jsp" class="toContentMain" title="全球造船业遭遇寒冬 年内恐难出低谷">全球造船业遭遇寒冬 年内恐难出低谷</a></td>
          <td style="text-align:right">2011-08-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131ef3a550131ef535fe90012.jsp" class="toContentMain" title="工业升级拉动西部电子高防护高可靠性元件需求">工业升级拉动西部电子高防护高可靠性元件需求</a></td>
          <td style="text-align:right">2011-08-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131ef3a550131ef44c9e7000d.jsp" class="toContentMain" title="油漆涂料市场利润空间压缩 经销商退步">油漆涂料市场利润空间压缩 经销商退步</a></td>
          <td style="text-align:right">2011-08-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131ef3a550131ef41c70b0009.jsp" class="toContentMain" title="“太阳能热利用”小行业之外的大领域">“太阳能热利用”小行业之外的大领域</a></td>
          <td style="text-align:right">2011-08-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131ef3a550131ef4123430008.jsp" class="toContentMain" title="乳企清理加速优化行业 现有企业仅为2008年一半">乳企清理加速优化行业 现有企业仅为2008年一半</a></td>
          <td style="text-align:right">2011-08-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131ef3a550131ef3f3dd40006.jsp" class="toContentMain" title="我国铝制采暖散热器发展浅析与展望">我国铝制采暖散热器发展浅析与展望</a></td>
          <td style="text-align:right">2011-08-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131ef3a550131ef3debfb0002.jsp" class="toContentMain" title="空气源热泵热水器的发展瓶颈">空气源热泵热水器的发展瓶颈</a></td>
          <td style="text-align:right">2011-08-22</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_8.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_10.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：9/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
