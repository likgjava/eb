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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f14e37e921a9a.jsp" class="toContentMain" title="陕西省:政府采购助推现代农业提质增效">陕西省:政府采购助推现代农业提质增效</a></td>
          <td style="text-align:right">2011-04-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f14ddab3d1a98.jsp" class="toContentMain" title="中国停止采购阿根廷豆油，其被迫低价转卖他国">中国停止采购阿根廷豆油，其被迫低价转卖他国</a></td>
          <td style="text-align:right">2011-04-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f14dbec651a96.jsp" class="toContentMain" title="网上商城促销 e时代的春游采购计划">网上商城促销 e时代的春游采购计划</a></td>
          <td style="text-align:right">2011-04-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f14da0e2c1a94.jsp" class="toContentMain" title="基本药物采购机制昨日开始实施">基本药物采购机制昨日开始实施</a></td>
          <td style="text-align:right">2011-04-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f14d8a4721a92.jsp" class="toContentMain" title="制造业采购经理指数回升">制造业采购经理指数回升</a></td>
          <td style="text-align:right">2011-04-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f0ae7789312ef.jsp" class="toContentMain" title="不再困惑 中小企业路由器采购指南">不再困惑 中小企业路由器采购指南</a></td>
          <td style="text-align:right">2011-03-31</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f0ae32c4412c4.jsp" class="toContentMain" title="政府汽车采购多元化 家轿有望受宠">政府汽车采购多元化 家轿有望受宠</a></td>
          <td style="text-align:right">2011-03-31</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f0ae0e46612ba.jsp" class="toContentMain" title="来采购会 装修直省20%">来采购会 装修直省20%</a></td>
          <td style="text-align:right">2011-03-31</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f0adcf54a12b6.jsp" class="toContentMain" title="沪企有望进入全球采购网络">沪企有望进入全球采购网络</a></td>
          <td style="text-align:right">2011-03-31</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f0ad889a912a1.jsp" class="toContentMain" title="浦东国际采购洽谈会探讨“跨国采购在华新趋势及应对之策”">浦东国际采购洽谈会探讨“跨国采购在华新趋势及应对之策”</a></td>
          <td style="text-align:right">2011-03-31</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f0ad6d9f0129c.jsp" class="toContentMain" title="丰田称500种零部件采购困难 或将改用替代品">丰田称500种零部件采购困难 或将改用替代品</a></td>
          <td style="text-align:right">2011-03-31</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f0ad4e041125b.jsp" class="toContentMain" title="六服装巨头携2亿美元订单来安徽寻采购伙伴">六服装巨头携2亿美元订单来安徽寻采购伙伴</a></td>
          <td style="text-align:right">2011-03-31</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f0ac5b27a121e.jsp" class="toContentMain" title="日本3月制造业采购经理人指数创记录最大跌幅">日本3月制造业采购经理人指数创记录最大跌幅</a></td>
          <td style="text-align:right">2011-03-31</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f0ac2872e1216.jsp" class="toContentMain" title="利丰的服装采购业务中中国份额下降明显">利丰的服装采购业务中中国份额下降明显</a></td>
          <td style="text-align:right">2011-03-31</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f0aadbc0611e0.jsp" class="toContentMain" title="磷肥等待印度采购中国化肥网">磷肥等待印度采购中国化肥网</a></td>
          <td style="text-align:right">2011-03-31</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f0aabffd511de.jsp" class="toContentMain" title="奥巴马：2015年美国政府将仅采购新能源汽车">奥巴马：2015年美国政府将仅采购新能源汽车</a></td>
          <td style="text-align:right">2011-03-31</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f0aaa764611dc.jsp" class="toContentMain" title="孟加拉国拟招标采购7.5万吨白糖">孟加拉国拟招标采购7.5万吨白糖</a></td>
          <td style="text-align:right">2011-03-31</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f0aa8ffc211da.jsp" class="toContentMain" title="淮安市金湖政府采购年节约资金3000万元">淮安市金湖政府采购年节约资金3000万元</a></td>
          <td style="text-align:right">2011-03-31</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f0aa5e66d11d1.jsp" class="toContentMain" title="中俄农机产品展销会上同江掀起采购热潮">中俄农机产品展销会上同江掀起采购热潮</a></td>
          <td style="text-align:right">2011-03-31</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f0aa2fb3111c8.jsp" class="toContentMain" title="巢湖招标采购评标专家库“扩容”">巢湖招标采购评标专家库“扩容”</a></td>
          <td style="text-align:right">2011-03-31</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_65.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_67.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：66/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
