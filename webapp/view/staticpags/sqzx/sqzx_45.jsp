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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fde6366012fe194bdc9000f.jsp" class="toContentMain" title="电力缺口加大 柴油需求料猛增">电力缺口加大 柴油需求料猛增</a></td>
          <td style="text-align:right">2011-05-12</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fde6366012fe19410f7000d.jsp" class="toContentMain" title="部分重点行业运行情况">部分重点行业运行情况</a></td>
          <td style="text-align:right">2011-05-12</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fde6366012fe1932abb000b.jsp" class="toContentMain" title="“十二五”新疆将建设现代化大型煤炭基地">“十二五”新疆将建设现代化大型煤炭基地</a></td>
          <td style="text-align:right">2011-05-12</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fbfbecc012fdc79901c0d84.jsp" class="toContentMain" title="钢铁等九大行业“再融资”限制放宽">钢铁等九大行业“再融资”限制放宽</a></td>
          <td style="text-align:right">2011-05-11</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fbfbecc012fdc78e1d20d82.jsp" class="toContentMain" title="吉林油田含二氧化碳气藏开发获重大进展">吉林油田含二氧化碳气藏开发获重大进展</a></td>
          <td style="text-align:right">2011-05-11</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fbfbecc012fdc7849ab0d80.jsp" class="toContentMain" title="焦炭维持区间振荡走势">焦炭维持区间振荡走势</a></td>
          <td style="text-align:right">2011-05-11</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fbfbecc012fdc736be10d7a.jsp" class="toContentMain" title="日本强震对煤炭行业影响呈“短空长多”特征">日本强震对煤炭行业影响呈“短空长多”特征</a></td>
          <td style="text-align:right">2011-05-11</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fbfbecc012fdc72b0630d78.jsp" class="toContentMain" title="未来5年新疆“钢需”预计超7500万吨">未来5年新疆“钢需”预计超7500万吨</a></td>
          <td style="text-align:right">2011-05-11</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fbfbecc012fd77d03410c76.jsp" class="toContentMain" title="老字号药店有望获得“十二五”政策支持">老字号药店有望获得“十二五”政策支持</a></td>
          <td style="text-align:right">2011-05-10</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fbfbecc012fd779a4cf0c6a.jsp" class="toContentMain" title="稀土四个月暴涨200%  泡沫破了怎么办？">稀土四个月暴涨200%  泡沫破了怎么办？</a></td>
          <td style="text-align:right">2011-05-10</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fbfbecc012fd76986020c24.jsp" class="toContentMain" title="工信部明确表示：坚决淘汰平板玻璃落后产能">工信部明确表示：坚决淘汰平板玻璃落后产能</a></td>
          <td style="text-align:right">2011-05-10</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fbfbecc012fd766f49a0c21.jsp" class="toContentMain" title="用电大省抢购电煤备战电荒竞争悄然提前">用电大省抢购电煤备战电荒竞争悄然提前</a></td>
          <td style="text-align:right">2011-05-10</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fbfbecc012fd76348510c10.jsp" class="toContentMain" title="财政部正酝酿铝型材出口退税下调4%">财政部正酝酿铝型材出口退税下调4%</a></td>
          <td style="text-align:right">2011-05-10</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fbfbecc012fd75b6b700c04.jsp" class="toContentMain" title="王岐山吁美放宽对华出口管制 承认中国市场经济地位">王岐山吁美放宽对华出口管制 承认中国市场经济地位</a></td>
          <td style="text-align:right">2011-05-10</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fbfbecc012fd2e3c3270883.jsp" class="toContentMain" title="中国制造业环境急剧恶化的迹象值得警惕">中国制造业环境急剧恶化的迹象值得警惕</a></td>
          <td style="text-align:right">2011-05-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fbfbecc012fd2e2e9f70881.jsp" class="toContentMain" title="意大利紧缩光伏补贴对中国厂商不利">意大利紧缩光伏补贴对中国厂商不利</a></td>
          <td style="text-align:right">2011-05-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fbfbecc012fd2e1fc6e087f.jsp" class="toContentMain" title="“十二五”上海奉贤将建立智能电网三大基地">“十二五”上海奉贤将建立智能电网三大基地</a></td>
          <td style="text-align:right">2011-05-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fbfbecc012fd2e07b9e087d.jsp" class="toContentMain" title="光伏产业：争做全国行业“翘楚”">光伏产业：争做全国行业“翘楚”</a></td>
          <td style="text-align:right">2011-05-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fbfbecc012fd2de8a13087b.jsp" class="toContentMain" title="西藏将成为中国太阳能发电量最大省区">西藏将成为中国太阳能发电量最大省区</a></td>
          <td style="text-align:right">2011-05-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fbfbecc012fd2dd8e720879.jsp" class="toContentMain" title="中国电子制造业如何借力信息化快速发展">中国电子制造业如何借力信息化快速发展</a></td>
          <td style="text-align:right">2011-05-09</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_44.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_46.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：45/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
