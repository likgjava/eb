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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f52b3272e0838.jsp" class="toContentMain" title="湖南长沙县融资20亿元推水利建设上新台阶">湖南长沙县融资20亿元推水利建设上新台阶</a></td>
          <td style="text-align:right">2011-04-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f52b1de5207f2.jsp" class="toContentMain" title="海南在建最大水利工程成功招标 总投资25亿">海南在建最大水利工程成功招标 总投资25亿</a></td>
          <td style="text-align:right">2011-04-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f52b092b407f0.jsp" class="toContentMain" title="提升照明企业品牌意识 大众品牌战略占优势">提升照明企业品牌意识 大众品牌战略占优势</a></td>
          <td style="text-align:right">2011-04-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f52ae791b07ec.jsp" class="toContentMain" title="LED照明产业聚焦垂直整合 优势互补">LED照明产业聚焦垂直整合 优势互补</a></td>
          <td style="text-align:right">2011-04-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f52ad021007e9.jsp" class="toContentMain" title="政府全力引导LED灯入市 未来几年发展可观">政府全力引导LED灯入市 未来几年发展可观</a></td>
          <td style="text-align:right">2011-04-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f52aa91f20755.jsp" class="toContentMain" title="我国首条硅烷气体生产线江西开建">我国首条硅烷气体生产线江西开建</a></td>
          <td style="text-align:right">2011-04-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f52a8fc8c0738.jsp" class="toContentMain" title="江西首家甲醇汽油生产企业已悄然投产">江西首家甲醇汽油生产企业已悄然投产</a></td>
          <td style="text-align:right">2011-04-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f52a2e6bd05d4.jsp" class="toContentMain" title="中国稳步鼎立全球塑料第一消费大国">中国稳步鼎立全球塑料第一消费大国</a></td>
          <td style="text-align:right">2011-04-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f52a00ac2056e.jsp" class="toContentMain" title="中国家电品牌在三四级市场面临新机遇">中国家电品牌在三四级市场面临新机遇</a></td>
          <td style="text-align:right">2011-04-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f529d23220465.jsp" class="toContentMain" title="数字印刷的发展 需要构建新的商业模式">数字印刷的发展 需要构建新的商业模式</a></td>
          <td style="text-align:right">2011-04-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f529be75d0449.jsp" class="toContentMain" title="太阳能热水器行业新标准即将呼之欲出">太阳能热水器行业新标准即将呼之欲出</a></td>
          <td style="text-align:right">2011-04-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f529ac4230444.jsp" class="toContentMain" title="厨电市场流行趋势主打“低碳与静音”">厨电市场流行趋势主打“低碳与静音”</a></td>
          <td style="text-align:right">2011-04-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f5298ef7f03ea.jsp" class="toContentMain" title="下个千亿巨头是谁？浅谈建材并购可行性">下个千亿巨头是谁？浅谈建材并购可行性</a></td>
          <td style="text-align:right">2011-04-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f520a1ae101fa.jsp" class="toContentMain" title="担心油价上涨开始打压塑料需求">担心油价上涨开始打压塑料需求</a></td>
          <td style="text-align:right">2011-04-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f52089f8401f6.jsp" class="toContentMain" title="小型陶企面临生死存亡？">小型陶企面临生死存亡？</a></td>
          <td style="text-align:right">2011-04-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f5206179801f4.jsp" class="toContentMain" title="钢价连续上涨 钢市拐点是否出现？">钢价连续上涨 钢市拐点是否出现？</a></td>
          <td style="text-align:right">2011-04-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f51fe71a901ee.jsp" class="toContentMain" title="二季度黄金强势将续印度将迎购买风潮">二季度黄金强势将续印度将迎购买风潮</a></td>
          <td style="text-align:right">2011-04-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f51fc8fb301ec.jsp" class="toContentMain" title="iPad2入华进程加快">iPad2入华进程加快</a></td>
          <td style="text-align:right">2011-04-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f51fa27ee01ea.jsp" class="toContentMain" title="建材价格月涨20%家装市场现海豚族">建材价格月涨20%家装市场现海豚族</a></td>
          <td style="text-align:right">2011-04-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f51f4845c01e6.jsp" class="toContentMain" title="越南红木价格走低 非洲原木酝酿更强走势">越南红木价格走低 非洲原木酝酿更强走势</a></td>
          <td style="text-align:right">2011-04-14</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_60.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_62.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：61/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
