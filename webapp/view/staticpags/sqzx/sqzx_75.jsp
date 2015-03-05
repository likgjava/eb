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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ea2d882012ea2e3b6d7000e.jsp" class="toContentMain" title="60载厦工开春给力 斩获近500台现场大单">60载厦工开春给力 斩获近500台现场大单</a></td>
          <td style="text-align:right">2011-03-11</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ea2d882012ea2e21ccb000c.jsp" class="toContentMain" title="卡特重工经销商一次性团购40台挖掘机">卡特重工经销商一次性团购40台挖掘机</a></td>
          <td style="text-align:right">2011-03-11</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ea2d882012ea2e00d2f000a.jsp" class="toContentMain" title="家电以旧换新销量和回收量均突破4千万台">家电以旧换新销量和回收量均突破4千万台</a></td>
          <td style="text-align:right">2011-03-11</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ea2d882012ea2da03600001.jsp" class="toContentMain" title="北京：31个民生项目公开招标 招标资金达4.6亿元">北京：31个民生项目公开招标 招标资金达4.6亿元</a></td>
          <td style="text-align:right">2011-03-11</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e9e7c97012ea2d715800162.jsp" class="toContentMain" title="金湖政府采购年节约资金3000万元">金湖政府采购年节约资金3000万元</a></td>
          <td style="text-align:right">2011-03-11</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e9e7c97012ea2cd5ffd0138.jsp" class="toContentMain" title="昆明：采购员与供应商有利害关系须回避">昆明：采购员与供应商有利害关系须回避</a></td>
          <td style="text-align:right">2011-03-11</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e9e7c97012e9f03dd33010f.jsp" class="toContentMain" title="“浙30条”剑指政采价格虚高软肋">“浙30条”剑指政采价格虚高软肋</a></td>
          <td style="text-align:right">2011-03-10</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e9e7c97012e9f010a3a010d.jsp" class="toContentMain" title="常州将高架保洁纳入政府采购">常州将高架保洁纳入政府采购</a></td>
          <td style="text-align:right">2011-03-10</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e9e7c97012e9eff0a31010b.jsp" class="toContentMain" title="承德政府采购去年节支2.3亿余元">承德政府采购去年节支2.3亿余元</a></td>
          <td style="text-align:right">2011-03-10</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e9e7c97012e9efb726c0109.jsp" class="toContentMain" title="国产平板电脑首度进入2011年全国两会采购清单">国产平板电脑首度进入2011年全国两会采购清单</a></td>
          <td style="text-align:right">2011-03-10</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e9e7c97012e9ef8a7fa0107.jsp" class="toContentMain" title="财政部：今年继续扩大政府采购规模 重点为服务类项目">财政部：今年继续扩大政府采购规模 重点为服务类项目</a></td>
          <td style="text-align:right">2011-03-10</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e9e7c97012e9ef5d65b0105.jsp" class="toContentMain" title="配套立法提速 严治地方政府采购奢华乱象">配套立法提速 严治地方政府采购奢华乱象</a></td>
          <td style="text-align:right">2011-03-10</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e9e7c97012e9ef084940100.jsp" class="toContentMain" title="中国2月份制造业采购经理指数为52.2%">中国2月份制造业采购经理指数为52.2%</a></td>
          <td style="text-align:right">2011-03-10</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e9e7c97012e9eeaefa700fe.jsp" class="toContentMain" title="2009年国家定点印刷采购规模年均增长情况简析">2009年国家定点印刷采购规模年均增长情况简析</a></td>
          <td style="text-align:right">2011-03-10</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e9e7c97012e9ee5c4b100fc.jsp" class="toContentMain" title="门窗木门行业预测 木门市场把握5个特征">门窗木门行业预测 木门市场把握5个特征</a></td>
          <td style="text-align:right">2011-03-10</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e8e7df0012e98a74d870452.jsp" class="toContentMain" title="国外买家加价采购中国制造">国外买家加价采购中国制造</a></td>
          <td style="text-align:right">2011-03-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e8e7df0012e98a64847044d.jsp" class="toContentMain" title="伊拉克2011年拟采购逾280万吨小麦">伊拉克2011年拟采购逾280万吨小麦</a></td>
          <td style="text-align:right">2011-03-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e8e7df0012e98a340520439.jsp" class="toContentMain" title="中国电信与台湾亚太电信拟扩大联合采购">中国电信与台湾亚太电信拟扩大联合采购</a></td>
          <td style="text-align:right">2011-03-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e8e7df0012e989a745c0431.jsp" class="toContentMain" title="我国教育行业PC产品的采购特点分析">我国教育行业PC产品的采购特点分析</a></td>
          <td style="text-align:right">2011-03-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e8e7df0012e9898c66f042f.jsp" class="toContentMain" title="沈卫国：安徽药品采购价低于国家指导价5成">沈卫国：安徽药品采购价低于国家指导价5成</a></td>
          <td style="text-align:right">2011-03-09</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_74.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_76.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：75/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
