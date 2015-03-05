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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813204bef60132131fd4c903d1.jsp" class="toContentMain" title="二甘醇市场超跌 业内投机热情减淡">二甘醇市场超跌 业内投机热情减淡</a></td>
          <td style="text-align:right">2011-08-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813204bef60132131e1af703d0.jsp" class="toContentMain" title="2011卫浴市场消费过渡 行业洗牌即将进行">2011卫浴市场消费过渡 行业洗牌即将进行</a></td>
          <td style="text-align:right">2011-08-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813204bef60132131945fb03cf.jsp" class="toContentMain" title="从五大模式解析涂料行业的未来营销趋势">从五大模式解析涂料行业的未来营销趋势</a></td>
          <td style="text-align:right">2011-08-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813204bef601321317bfde03ce.jsp" class="toContentMain" title="云计算产业发展迅速 需要让安全优先">云计算产业发展迅速 需要让安全优先</a></td>
          <td style="text-align:right">2011-08-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813204bef601321314702c03cd.jsp" class="toContentMain" title="背光需求2012年到顶 LED增长引擎将转向照明">背光需求2012年到顶 LED增长引擎将转向照明</a></td>
          <td style="text-align:right">2011-08-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813204bef601321313673603cc.jsp" class="toContentMain" title="市场价格上涨 烧碱星星之火能否燎原">市场价格上涨 烧碱星星之火能否燎原</a></td>
          <td style="text-align:right">2011-08-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813204bef601321312f1f303cb.jsp" class="toContentMain" title="鞋服制造震撼 带动中西鞋服产业再次腾飞">鞋服制造震撼 带动中西鞋服产业再次腾飞</a></td>
          <td style="text-align:right">2011-08-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813204bef60132130f7b8c03ca.jsp" class="toContentMain" title="为什么说整体衣柜产业是个大市场？">为什么说整体衣柜产业是个大市场？</a></td>
          <td style="text-align:right">2011-08-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813204bef60132130acdfd03c9.jsp" class="toContentMain" title="中国丙烯价格上扬势头或将受到抑制">中国丙烯价格上扬势头或将受到抑制</a></td>
          <td style="text-align:right">2011-08-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813204bef60132130984da03c8.jsp" class="toContentMain" title="日本服装商预计2011年冬保暖内衣销量大增">日本服装商预计2011年冬保暖内衣销量大增</a></td>
          <td style="text-align:right">2011-08-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813204bef601321308ffd203c7.jsp" class="toContentMain" title="从光伏到光热：太阳能热发电受到关注">从光伏到光热：太阳能热发电受到关注</a></td>
          <td style="text-align:right">2011-08-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813204bef601321307340703c5.jsp" class="toContentMain" title="行业增速回归理性 中国风电积蓄后势">行业增速回归理性 中国风电积蓄后势</a></td>
          <td style="text-align:right">2011-08-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813204bef6013212fec9f503a7.jsp" class="toContentMain" title="背光需求2012年到顶 LED增长引擎将转向照明">背光需求2012年到顶 LED增长引擎将转向照明</a></td>
          <td style="text-align:right">2011-08-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131ff33bb013203ce262d015a.jsp" class="toContentMain" title="红木家具“水深” 呼吁建立行业统一标准">红木家具“水深” 呼吁建立行业统一标准</a></td>
          <td style="text-align:right">2011-08-26</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131ff33bb013203cb28fc0156.jsp" class="toContentMain" title="中央空调节能新国标推动行业升级转型">中央空调节能新国标推动行业升级转型</a></td>
          <td style="text-align:right">2011-08-26</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131ff33bb013203c601ba0154.jsp" class="toContentMain" title="节能环保为热泵热水器发展创造条件">节能环保为热泵热水器发展创造条件</a></td>
          <td style="text-align:right">2011-08-26</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131ff33bb013203c4f1660153.jsp" class="toContentMain" title="家电企业IT化 竞争从硬件转向软件">家电企业IT化 竞争从硬件转向软件</a></td>
          <td style="text-align:right">2011-08-26</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131ff33bb013203c33d200152.jsp" class="toContentMain" title="医药工业集中度将迎来快速提升期">医药工业集中度将迎来快速提升期</a></td>
          <td style="text-align:right">2011-08-26</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131ff33bb013203be04e2014f.jsp" class="toContentMain" title="需求市场愈见明朗 LED投资热潮再涌">需求市场愈见明朗 LED投资热潮再涌</a></td>
          <td style="text-align:right">2011-08-26</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131ff33bb013203bda8a4014e.jsp" class="toContentMain" title="汽车后市场 倚靠电子商务会更加精彩">汽车后市场 倚靠电子商务会更加精彩</a></td>
          <td style="text-align:right">2011-08-26</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_4.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_6.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：5/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
