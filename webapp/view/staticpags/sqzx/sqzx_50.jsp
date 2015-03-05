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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f7ae5ba012f7b087d6a0077.jsp" class="toContentMain" title="限价令显效 酒类市场涨价潮暂落">限价令显效 酒类市场涨价潮暂落</a></td>
          <td style="text-align:right">2011-04-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f7ae5ba012f7b07040a006d.jsp" class="toContentMain" title="四川进口酒类急速增长，葡萄酒占进口酒类大部分比重">四川进口酒类急速增长，葡萄酒占进口酒类大部分比重</a></td>
          <td style="text-align:right">2011-04-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f7ae5ba012f7b057c480063.jsp" class="toContentMain" title="2009年广东酒类销售额逾300多亿元">2009年广东酒类销售额逾300多亿元</a></td>
          <td style="text-align:right">2011-04-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f7ae5ba012f7b02db0d0057.jsp" class="toContentMain" title="“酒博会”打造酒类交易“广州价格”">“酒博会”打造酒类交易“广州价格”</a></td>
          <td style="text-align:right">2011-04-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f7ae5ba012f7b003cbc004d.jsp" class="toContentMain" title="中央强调扩大内需 年底消费股或迎“蜜月”期">中央强调扩大内需 年底消费股或迎“蜜月”期</a></td>
          <td style="text-align:right">2011-04-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f7ae5ba012f7af64af3002e.jsp" class="toContentMain" title="广交会一期成交逾230亿美元 日本采购商略增中东下降明显">广交会一期成交逾230亿美元 日本采购商略增中东下降明显</a></td>
          <td style="text-align:right">2011-04-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f7ae5ba012f7af49bf10029.jsp" class="toContentMain" title="河北沧州皮棉价格大幅下跌 纺织企业下调采购报价">河北沧州皮棉价格大幅下跌 纺织企业下调采购报价</a></td>
          <td style="text-align:right">2011-04-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f7ae5ba012f7af364500023.jsp" class="toContentMain" title="中石化签下850亿美元澳洲天然气采购合约">中石化签下850亿美元澳洲天然气采购合约</a></td>
          <td style="text-align:right">2011-04-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f7ae5ba012f7aefc3db001d.jsp" class="toContentMain" title="烟台前3月县市区采购“全面开花”">烟台前3月县市区采购“全面开花”</a></td>
          <td style="text-align:right">2011-04-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f7ae5ba012f7aee619c0019.jsp" class="toContentMain" title="钱袋网与江波龙签大规模采购协议 创手机支付新革命">钱袋网与江波龙签大规模采购协议 创手机支付新革命</a></td>
          <td style="text-align:right">2011-04-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f7ae5ba012f7aec158c0012.jsp" class="toContentMain" title="第八届装饰建材采购节五一开幕">第八届装饰建材采购节五一开幕</a></td>
          <td style="text-align:right">2011-04-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f7ae5ba012f7aea6f87000b.jsp" class="toContentMain" title="印度上调当地小麦采购价格4.5%">印度上调当地小麦采购价格4.5%</a></td>
          <td style="text-align:right">2011-04-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f71626e012f77460b4b02bf.jsp" class="toContentMain" title="中国智能电网应参照高铁发展模式">中国智能电网应参照高铁发展模式</a></td>
          <td style="text-align:right">2011-04-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f71626e012f773e27a3029e.jsp" class="toContentMain" title="扬州智能电网示范园区获批准 明年底建成">扬州智能电网示范园区获批准 明年底建成</a></td>
          <td style="text-align:right">2011-04-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f71626e012f772d748d029a.jsp" class="toContentMain" title="“十二五”山东滨州市190亿元投资公路建设">“十二五”山东滨州市190亿元投资公路建设</a></td>
          <td style="text-align:right">2011-04-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f71626e012f772c5b4c0298.jsp" class="toContentMain" title="新疆五个新能源项目集体开工 投资均过亿">新疆五个新能源项目集体开工 投资均过亿</a></td>
          <td style="text-align:right">2011-04-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f71626e012f772aff990296.jsp" class="toContentMain" title="援建喀什钢铁项目正式开工建设">援建喀什钢铁项目正式开工建设</a></td>
          <td style="text-align:right">2011-04-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f71626e012f7729d35a0294.jsp" class="toContentMain" title="2012年将开启LED照明产业“黄金十年”">2012年将开启LED照明产业“黄金十年”</a></td>
          <td style="text-align:right">2011-04-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f71626e012f77258ebe0292.jsp" class="toContentMain" title="LED灯具认证企业参加广交会拓展中国市场">LED灯具认证企业参加广交会拓展中国市场</a></td>
          <td style="text-align:right">2011-04-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f71626e012f772390320290.jsp" class="toContentMain" title="聚焦地板行业加速“抢滩”国内市场">聚焦地板行业加速“抢滩”国内市场</a></td>
          <td style="text-align:right">2011-04-21</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_49.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_51.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：50/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
