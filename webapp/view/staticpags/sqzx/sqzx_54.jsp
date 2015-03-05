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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f6dbbe8012f70940c680083.jsp" class="toContentMain" title="欧盟采购商制鞋订单回流中国">欧盟采购商制鞋订单回流中国</a></td>
          <td style="text-align:right">2011-04-20</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f6dbbe8012f7091d7a70075.jsp" class="toContentMain" title="广交会：产品价格普涨 采购商青睐新技术">广交会：产品价格普涨 采购商青睐新技术</a></td>
          <td style="text-align:right">2011-04-20</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f6dbbe8012f708c5c7a0055.jsp" class="toContentMain" title="欧美采购团?晋江企业对接洽谈会成功举行">欧美采购团?晋江企业对接洽谈会成功举行</a></td>
          <td style="text-align:right">2011-04-20</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f6d09cf850b8f.jsp" class="toContentMain" title="杭州旅游茶叶市场假西湖龙井卖8千1斤">杭州旅游茶叶市场假西湖龙井卖8千1斤</a></td>
          <td style="text-align:right">2011-04-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f6d0649e60b8d.jsp" class="toContentMain" title="一个茶节“捧红”各地20种茶叶">一个茶节“捧红”各地20种茶叶</a></td>
          <td style="text-align:right">2011-04-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f6d04010d0b8b.jsp" class="toContentMain" title="成本增加茶叶价涨 “疯狂的茶叶”是否上演？">成本增加茶叶价涨 “疯狂的茶叶”是否上演？</a></td>
          <td style="text-align:right">2011-04-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f6cb8eda40b6e.jsp" class="toContentMain" title="个旧一季度实现集中政府采购资金290余万元">个旧一季度实现集中政府采购资金290余万元</a></td>
          <td style="text-align:right">2011-04-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f6cb741ea0b69.jsp" class="toContentMain" title="黑龙江协议供货价格有了“新地标”">黑龙江协议供货价格有了“新地标”</a></td>
          <td style="text-align:right">2011-04-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f6cb584500b5e.jsp" class="toContentMain" title="承德26个民生项目节资364万元">承德26个民生项目节资364万元</a></td>
          <td style="text-align:right">2011-04-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f6cb414ca0b5b.jsp" class="toContentMain" title="合肥未来招投标越来越“亲民”">合肥未来招投标越来越“亲民”</a></td>
          <td style="text-align:right">2011-04-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f6cb2c8330b59.jsp" class="toContentMain" title="浙江:医展会政采成交额达4.3亿元">浙江:医展会政采成交额达4.3亿元</a></td>
          <td style="text-align:right">2011-04-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f6cabc8680b45.jsp" class="toContentMain" title="余秋雨现在竟然变成了一代股神">余秋雨现在竟然变成了一代股神</a></td>
          <td style="text-align:right">2011-04-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f6c80dd390b29.jsp" class="toContentMain" title=""十二五"江苏"风电三峡"装机将达580万千瓦">"十二五"江苏"风电三峡"装机将达580万千瓦</a></td>
          <td style="text-align:right">2011-04-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f6c7e2b910b27.jsp" class="toContentMain" title="中国2010年B2B电子商务成交3.8万亿元">中国2010年B2B电子商务成交3.8万亿元</a></td>
          <td style="text-align:right">2011-04-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f6c79b5be0b25.jsp" class="toContentMain" title="黄山市投资6.2亿元建设生态环保工程">黄山市投资6.2亿元建设生态环保工程</a></td>
          <td style="text-align:right">2011-04-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f6c7795a40b23.jsp" class="toContentMain" title="重庆投资32.4亿元综合治理14条次级河流污染">重庆投资32.4亿元综合治理14条次级河流污染</a></td>
          <td style="text-align:right">2011-04-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f6c74fbef0b21.jsp" class="toContentMain" title="湖南龙永高速施工在即 全长90余公里">湖南龙永高速施工在即 全长90余公里</a></td>
          <td style="text-align:right">2011-04-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f6c70ad7a0b1a.jsp" class="toContentMain" title="国际巨头筑LED专利壁垒 布局渠道抢占中国市场">国际巨头筑LED专利壁垒 布局渠道抢占中国市场</a></td>
          <td style="text-align:right">2011-04-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f6c6e75500b17.jsp" class="toContentMain" title="内蒙古首个LED光电产业集群区落脚蒙西">内蒙古首个LED光电产业集群区落脚蒙西</a></td>
          <td style="text-align:right">2011-04-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f6c6d15ab0b0e.jsp" class="toContentMain" title="台湾连发光电拟投10亿建大陆LED路灯项目">台湾连发光电拟投10亿建大陆LED路灯项目</a></td>
          <td style="text-align:right">2011-04-19</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_53.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_55.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：54/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
