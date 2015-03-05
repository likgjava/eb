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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131767356013182d357ff00f2.jsp" class="toContentMain" title="新能源重点锁定四大产业 生物质能领风骚">新能源重点锁定四大产业 生物质能领风骚</a></td>
          <td style="text-align:right">2011-08-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131767356013182d28de800f1.jsp" class="toContentMain" title="十二五长三角纺织业成新经济增长点">十二五长三角纺织业成新经济增长点</a></td>
          <td style="text-align:right">2011-08-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131767356013182d1253f00ee.jsp" class="toContentMain" title="中国争夺稀土定价权 整合政策引发国际争议">中国争夺稀土定价权 整合政策引发国际争议</a></td>
          <td style="text-align:right">2011-08-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a01317391355908da.jsp" class="toContentMain" title="空气能太阳能黄金组合 热水器市场大有所为">空气能太阳能黄金组合 热水器市场大有所为</a></td>
          <td style="text-align:right">2011-07-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a01317390714108d8.jsp" class="toContentMain" title="化工业利润增长高于全国工业利润一倍">化工业利润增长高于全国工业利润一倍</a></td>
          <td style="text-align:right">2011-07-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a0131738c487408d4.jsp" class="toContentMain" title="进口葡萄酒快速增长的市场问题分析">进口葡萄酒快速增长的市场问题分析</a></td>
          <td style="text-align:right">2011-07-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a0131738b29e308d3.jsp" class="toContentMain" title="热水器质量抽查敲响中小企业发展警钟">热水器质量抽查敲响中小企业发展警钟</a></td>
          <td style="text-align:right">2011-07-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a01317386188208d0.jsp" class="toContentMain" title="补贴取消原料上涨双重压力 今年华南空调市场惨淡">补贴取消原料上涨双重压力 今年华南空调市场惨淡</a></td>
          <td style="text-align:right">2011-07-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a01317385724708cf.jsp" class="toContentMain" title="光伏上网基准电价有望近期出台">光伏上网基准电价有望近期出台</a></td>
          <td style="text-align:right">2011-07-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a01317384fb7708ce.jsp" class="toContentMain" title="秦皇岛港电煤价格连跌 焦煤价格看涨">秦皇岛港电煤价格连跌 焦煤价格看涨</a></td>
          <td style="text-align:right">2011-07-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a01317384257508cd.jsp" class="toContentMain" title="国内矿企联合上调铁矿出厂价25至30元每吨">国内矿企联合上调铁矿出厂价25至30元每吨</a></td>
          <td style="text-align:right">2011-07-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a01317383360c08cb.jsp" class="toContentMain" title="添加剂新规出台 水果月饼口味或受冲击">添加剂新规出台 水果月饼口味或受冲击</a></td>
          <td style="text-align:right">2011-07-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a01316e5f7935070c.jsp" class="toContentMain" title="天然橡胶价格上涨长期利好优势轮胎企业">天然橡胶价格上涨长期利好优势轮胎企业</a></td>
          <td style="text-align:right">2011-07-28</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a01316e5d38f4070b.jsp" class="toContentMain" title="太阳能3C商机浮现 未来或大规模爆发">太阳能3C商机浮现 未来或大规模爆发</a></td>
          <td style="text-align:right">2011-07-28</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a01316e58bee60708.jsp" class="toContentMain" title="如何实现中国白酒的高端生态价值？">如何实现中国白酒的高端生态价值？</a></td>
          <td style="text-align:right">2011-07-28</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a01316e57ee9c0706.jsp" class="toContentMain" title="太阳能光热发电：突破瓶颈促进良性发展">太阳能光热发电：突破瓶颈促进良性发展</a></td>
          <td style="text-align:right">2011-07-28</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a01316e572bfb0705.jsp" class="toContentMain" title="智能电视表面火热 发展背后存五大隐忧">智能电视表面火热 发展背后存五大隐忧</a></td>
          <td style="text-align:right">2011-07-28</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a01316e559ac00704.jsp" class="toContentMain" title="变形金刚玩具模型最近在肥受追捧">变形金刚玩具模型最近在肥受追捧</a></td>
          <td style="text-align:right">2011-07-28</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a01316e51d4720702.jsp" class="toContentMain" title="白酒品牌如何决胜高端市场？">白酒品牌如何决胜高端市场？</a></td>
          <td style="text-align:right">2011-07-28</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131518b2a01316e511ab90701.jsp" class="toContentMain" title="新能源汽车的发展为五金行业带来转机">新能源汽车的发展为五金行业带来转机</a></td>
          <td style="text-align:right">2011-07-28</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_19.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_21.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：20/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
