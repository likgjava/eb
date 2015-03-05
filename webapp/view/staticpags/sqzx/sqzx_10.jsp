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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131ef1a5d0131ef1d0b5a0001.jsp" class="toContentMain" title="我国光电复合海底电缆市场“钱”力无限">我国光电复合海底电缆市场“钱”力无限</a></td>
          <td style="text-align:right">2011-08-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131e0e1bd0131ef1812dd0044.jsp" class="toContentMain" title="橱柜市场将刮定制风 抢占市场需要创新">橱柜市场将刮定制风 抢占市场需要创新</a></td>
          <td style="text-align:right">2011-08-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131e0e1bd0131ef163c480042.jsp" class="toContentMain" title="我国聚光光伏企业将参与国际标准制定">我国聚光光伏企业将参与国际标准制定</a></td>
          <td style="text-align:right">2011-08-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131e0e1bd0131ef120c35003f.jsp" class="toContentMain" title="我国冷链产业呈现巨大的市场潜力">我国冷链产业呈现巨大的市场潜力</a></td>
          <td style="text-align:right">2011-08-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131e0e1bd0131ef119450003e.jsp" class="toContentMain" title="工程机械新增长点：近万亿投资落户新疆">工程机械新增长点：近万亿投资落户新疆</a></td>
          <td style="text-align:right">2011-08-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131e0e1bd0131ef0da7e9003c.jsp" class="toContentMain" title="中小门窗企业征战市场的三大必备绝技">中小门窗企业征战市场的三大必备绝技</a></td>
          <td style="text-align:right">2011-08-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131e0e1bd0131ef0cf6d8003b.jsp" class="toContentMain" title="出口玩具企业应积极应对国外各项技术法规">出口玩具企业应积极应对国外各项技术法规</a></td>
          <td style="text-align:right">2011-08-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131e0e1bd0131ef066ae70034.jsp" class="toContentMain" title="河北棉价趋稳销量增加 新棉受连续降雨影响">河北棉价趋稳销量增加 新棉受连续降雨影响</a></td>
          <td style="text-align:right">2011-08-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131e0e1bd0131ef05daa70033.jsp" class="toContentMain" title="“十二五”期间化纤工业将更注重质的发展">“十二五”期间化纤工业将更注重质的发展</a></td>
          <td style="text-align:right">2011-08-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131e0e1bd0131ef05402e0031.jsp" class="toContentMain" title="动力电池成组检测曝出“漏洞”">动力电池成组检测曝出“漏洞”</a></td>
          <td style="text-align:right">2011-08-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131e0e1bd0131ef007e9a0030.jsp" class="toContentMain" title="新能源汽车规划部分微调 行业乱象或将改观">新能源汽车规划部分微调 行业乱象或将改观</a></td>
          <td style="text-align:right">2011-08-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131e0e1bd0131eefff570002f.jsp" class="toContentMain" title="棉价过山车 纺企咋想辙">棉价过山车 纺企咋想辙</a></td>
          <td style="text-align:right">2011-08-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131e0e1bd0131eeff5031002e.jsp" class="toContentMain" title="中秋节成食用油涨价窗口期 花生油或成领涨主力">中秋节成食用油涨价窗口期 花生油或成领涨主力</a></td>
          <td style="text-align:right">2011-08-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131e0e1bd0131eefe242e002d.jsp" class="toContentMain" title="医药工业十二五规划或9 月出台">医药工业十二五规划或9 月出台</a></td>
          <td style="text-align:right">2011-08-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131db79dc0131dfa8b17f025f.jsp" class="toContentMain" title="热水采暖合一成就空气源热泵拓展市场">热水采暖合一成就空气源热泵拓展市场</a></td>
          <td style="text-align:right">2011-08-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131db79dc0131dfa728a2025e.jsp" class="toContentMain" title="2011年中国LED市场将达58亿美元">2011年中国LED市场将达58亿美元</a></td>
          <td style="text-align:right">2011-08-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131db79dc0131dfa5addc025d.jsp" class="toContentMain" title="热泵热水器北方市场是杂牌军滋生的牧场">热泵热水器北方市场是杂牌军滋生的牧场</a></td>
          <td style="text-align:right">2011-08-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131db79dc0131dfa4e3b8025c.jsp" class="toContentMain" title="卫陶模具传弊端难克服 新材料有待开发">卫陶模具传弊端难克服 新材料有待开发</a></td>
          <td style="text-align:right">2011-08-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131db79dc0131dfa42c55025b.jsp" class="toContentMain" title="我国多功能透明钢化建筑玻璃研制成功">我国多功能透明钢化建筑玻璃研制成功</a></td>
          <td style="text-align:right">2011-08-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131db79dc0131dfa32470025a.jsp" class="toContentMain" title="热泵热水器企业如何布局北方市场？">热泵热水器企业如何布局北方市场？</a></td>
          <td style="text-align:right">2011-08-19</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_9.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_11.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：10/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
