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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130c14db40130ce8ef6890134.jsp" class="toContentMain" title="生猪价格冲刺历史新高">生猪价格冲刺历史新高</a></td>
          <td style="text-align:right">2011-06-27</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130c14db40130ce8dfff40130.jsp" class="toContentMain" title="进口铁矿石风光不再 价格进入下降通道">进口铁矿石风光不再 价格进入下降通道</a></td>
          <td style="text-align:right">2011-06-27</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130bf34b60130bf56226900a6.jsp" class="toContentMain" title="中国茶叶品牌迎来“孙子兵法”">中国茶叶品牌迎来“孙子兵法”</a></td>
          <td style="text-align:right">2011-06-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130bf34b60130bf54849200a4.jsp" class="toContentMain" title="葡萄酒相关行业迎来井喷之势">葡萄酒相关行业迎来井喷之势</a></td>
          <td style="text-align:right">2011-06-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130bf34b60130bf4b09430090.jsp" class="toContentMain" title="国内纺织品服装出口萎缩苗头不容忽视">国内纺织品服装出口萎缩苗头不容忽视</a></td>
          <td style="text-align:right">2011-06-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130bf34b60130bf49c462008e.jsp" class="toContentMain" title="液晶面板连跌11个月 中国企业遭日韩价格“绑架”？">液晶面板连跌11个月 中国企业遭日韩价格“绑架”？</a></td>
          <td style="text-align:right">2011-06-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130bf34b60130bf46a453008c.jsp" class="toContentMain" title="液体灌装机在液态食品市场有很大占有率">液体灌装机在液态食品市场有很大占有率</a></td>
          <td style="text-align:right">2011-06-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130bf34b60130bf43833b0088.jsp" class="toContentMain" title="有机葡萄酒市场走热 待进一步完善">有机葡萄酒市场走热 待进一步完善</a></td>
          <td style="text-align:right">2011-06-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130bf34b60130bf4139ea0086.jsp" class="toContentMain" title="云计算发展进入高潮期 万亿产业待爆发">云计算发展进入高潮期 万亿产业待爆发</a></td>
          <td style="text-align:right">2011-06-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130bf34b60130bf4018090084.jsp" class="toContentMain" title="中国纺织服装业下半年出口环境逐步好转">中国纺织服装业下半年出口环境逐步好转</a></td>
          <td style="text-align:right">2011-06-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130bf287a0130bf308ae7000d.jsp" class="toContentMain" title="牛奶新标准不进反退 业内指系扩张过快乱象之一">牛奶新标准不进反退 业内指系扩张过快乱象之一</a></td>
          <td style="text-align:right">2011-06-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130bf287a0130bf2f4416000b.jsp" class="toContentMain" title="国产彩电低价抢六成市场 3D电视格局骤变">国产彩电低价抢六成市场 3D电视格局骤变</a></td>
          <td style="text-align:right">2011-06-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130bf287a0130bf2e45900009.jsp" class="toContentMain" title="稀土储备国家版图将形成 国内定价权加固在望">稀土储备国家版图将形成 国内定价权加固在望</a></td>
          <td style="text-align:right">2011-06-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130bf287a0130bf2d6b440007.jsp" class="toContentMain" title="猪肉价格创新高 养殖加工冰火两重天">猪肉价格创新高 养殖加工冰火两重天</a></td>
          <td style="text-align:right">2011-06-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130bf287a0130bf2c3e730005.jsp" class="toContentMain" title="钢铁行业景气度下滑 铁矿石需求遇冷">钢铁行业景气度下滑 铁矿石需求遇冷</a></td>
          <td style="text-align:right">2011-06-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130bf287a0130bf2a6ad90003.jsp" class="toContentMain" title="食用油限价令或再延期两月 进口大豆现压港现象">食用油限价令或再延期两月 进口大豆现压港现象</a></td>
          <td style="text-align:right">2011-06-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130bf287a0130bf29bc3a0001.jsp" class="toContentMain" title="工程机械十二五规划已上报 市场需求将达9000亿">工程机械十二五规划已上报 市场需求将达9000亿</a></td>
          <td style="text-align:right">2011-06-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130bc5ca70130bf270a7e0038.jsp" class="toContentMain" title="多重限制政策叠加 车市销量走低">多重限制政策叠加 车市销量走低</a></td>
          <td style="text-align:right">2011-06-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130bc5ca70130bf189434002a.jsp" class="toContentMain" title="成本上涨蚕食利润 通胀考验食品饮料业定价能力">成本上涨蚕食利润 通胀考验食品饮料业定价能力</a></td>
          <td style="text-align:right">2011-06-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130b726330130ba27582f01b4.jsp" class="toContentMain" title="全球LED产业进入“黄金时代”">全球LED产业进入“黄金时代”</a></td>
          <td style="text-align:right">2011-06-23</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_33.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_35.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：34/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
