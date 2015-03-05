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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e4689bf012e47481a7501b0.jsp" class="toContentMain" title="苏泊尔2010年净利润增三成 外贸销售猛增">苏泊尔2010年净利润增三成 外贸销售猛增</a></td>
          <td style="text-align:right">2011-02-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e4689bf012e471175f701a4.jsp" class="toContentMain" title="全球铁矿石定价规则再变 钢企迎来高成本时代">全球铁矿石定价规则再变 钢企迎来高成本时代</a></td>
          <td style="text-align:right">2011-02-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e4689bf012e470cd4d40158.jsp" class="toContentMain" title="受限购影响 北京二手车大量入辽引燃价格战">受限购影响 北京二手车大量入辽引燃价格战</a></td>
          <td style="text-align:right">2011-02-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e4689bf012e46f8135e011d.jsp" class="toContentMain" title="汽车保有量接近日本 中国到底能承载多少汽车？">汽车保有量接近日本 中国到底能承载多少汽车？</a></td>
          <td style="text-align:right">2011-02-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e4689bf012e46f34d510103.jsp" class="toContentMain" title="1月商用车销售36.54万辆 同比增长4.89%">1月商用车销售36.54万辆 同比增长4.89%</a></td>
          <td style="text-align:right">2011-02-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e4689bf012e46eff84b00e9.jsp" class="toContentMain" title="辽宁大商集团与台湾爱格发公司签署130吨虱目鱼丸采购合约">辽宁大商集团与台湾爱格发公司签署130吨虱目鱼丸采购合约</a></td>
          <td style="text-align:right">2011-02-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e31dec9012e3817f2fd01a9.jsp" class="toContentMain" title="财政部：今年继续扩大政府采购规模 防豪华采购">财政部：今年继续扩大政府采购规模 防豪华采购</a></td>
          <td style="text-align:right">2011-02-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e31dec9012e38148cdc01a7.jsp" class="toContentMain" title="商务部回应“旱情推高粮价” 称粮食产量有保障">商务部回应“旱情推高粮价” 称粮食产量有保障</a></td>
          <td style="text-align:right">2011-02-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e31dec9012e38081bfa01a3.jsp" class="toContentMain" title="除装修异味 布鲁雅尔303空气净化器">除装修异味 布鲁雅尔303空气净化器</a></td>
          <td style="text-align:right">2011-02-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e31dec9012e3805641a01a1.jsp" class="toContentMain" title="正太冷风机：节能型冷风机可净化空气">正太冷风机：节能型冷风机可净化空气</a></td>
          <td style="text-align:right">2011-02-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e31dec9012e38029dce019f.jsp" class="toContentMain" title="2011第五届上海国际供暖通风及净化产品展">2011第五届上海国际供暖通风及净化产品展</a></td>
          <td style="text-align:right">2011-02-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e31dec9012e37ff30cf019b.jsp" class="toContentMain" title="改善空气质量 西宁48家餐馆使用了清洁能源">改善空气质量 西宁48家餐馆使用了清洁能源</a></td>
          <td style="text-align:right">2011-02-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e31dec9012e32fb64dd002c.jsp" class="toContentMain" title="今年橱柜流行趋势解析 低碳受欢迎">今年橱柜流行趋势解析 低碳受欢迎</a></td>
          <td style="text-align:right">2011-02-17</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e31dec9012e32f20319002a.jsp" class="toContentMain" title="今年滑雪也团购">今年滑雪也团购</a></td>
          <td style="text-align:right">2011-02-17</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e31dec9012e32e925a40028.jsp" class="toContentMain" title=""常回家看看"立法尊老概念使皇威足浴器最给力">"常回家看看"立法尊老概念使皇威足浴器最给力</a></td>
          <td style="text-align:right">2011-02-17</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e31dec9012e32dcef00001d.jsp" class="toContentMain" title="集成环保灶异军突起冲击传统灶具市场">集成环保灶异军突起冲击传统灶具市场</a></td>
          <td style="text-align:right">2011-02-17</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e31dec9012e32d7e9d2001b.jsp" class="toContentMain" title="家用净水机开打节能牌">家用净水机开打节能牌</a></td>
          <td style="text-align:right">2011-02-17</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e2d96ae012e2dd0528101bf.jsp" class="toContentMain" title="一张“订单”破解食品行业发展“瓶颈”">一张“订单”破解食品行业发展“瓶颈”</a></td>
          <td style="text-align:right">2011-02-16</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e2d96ae012e2dc89c7701bd.jsp" class="toContentMain" title="2011年卫浴行业风云变幻 闪现五大亮点">2011年卫浴行业风云变幻 闪现五大亮点</a></td>
          <td style="text-align:right">2011-02-16</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e2d96ae012e2dc585b701bb.jsp" class="toContentMain" title="有望强制采购再生复印纸">有望强制采购再生复印纸</a></td>
          <td style="text-align:right">2011-02-16</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_81.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：82/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
