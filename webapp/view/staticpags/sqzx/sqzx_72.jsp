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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ed683ea012ed6f6f6040070.jsp" class="toContentMain" title="重庆市财政局加强对政府采购投标保证金账户监管工作">重庆市财政局加强对政府采购投标保证金账户监管工作</a></td>
          <td style="text-align:right">2011-03-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ec322a0012ec7e17b0100d4.jsp" class="toContentMain" title="能效标准落实促进平板电视升级脚步加快">能效标准落实促进平板电视升级脚步加快</a></td>
          <td style="text-align:right">2011-03-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ec322a0012ec7ce6cf9009c.jsp" class="toContentMain" title="从政府采购成绩单上取长补短">从政府采购成绩单上取长补短</a></td>
          <td style="text-align:right">2011-03-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ec322a0012ec7c72791006c.jsp" class="toContentMain" title="中国钢铁企业首发绿色采购指南 推动产业链升级">中国钢铁企业首发绿色采购指南 推动产业链升级</a></td>
          <td style="text-align:right">2011-03-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ec322a0012ec7a48450003a.jsp" class="toContentMain" title="用户群体转变　家居风格回归复古">用户群体转变　家居风格回归复古</a></td>
          <td style="text-align:right">2011-03-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ec322a0012ec795976c0031.jsp" class="toContentMain" title="上海：低碳产品纳入政府采购">上海：低碳产品纳入政府采购</a></td>
          <td style="text-align:right">2011-03-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ec322a0012ec78e6422002f.jsp" class="toContentMain" title="消费者需谨慎选购轮胎">消费者需谨慎选购轮胎</a></td>
          <td style="text-align:right">2011-03-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ec322a0012ec70dfc0a0022.jsp" class="toContentMain" title="2011家居智能化 新型节水龙头成为一枝独秀">2011家居智能化 新型节水龙头成为一枝独秀</a></td>
          <td style="text-align:right">2011-03-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ec322a0012ec6f0106d0020.jsp" class="toContentMain" title="豆奶机”悄然上市 饮家电或将掀起变革飓风">豆奶机”悄然上市 饮家电或将掀起变革飓风</a></td>
          <td style="text-align:right">2011-03-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ec322a0012ec6ed40d60019.jsp" class="toContentMain" title="国内三层实木地板逐渐受到消费者青睐">国内三层实木地板逐渐受到消费者青睐</a></td>
          <td style="text-align:right">2011-03-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ec322a0012ec69e29eb000b.jsp" class="toContentMain" title="LED路灯智能控制功能凸显最大优势">LED路灯智能控制功能凸显最大优势</a></td>
          <td style="text-align:right">2011-03-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ea2d882012ec1a04d1b0c23.jsp" class="toContentMain" title="亚洲醋酸乙烯市场现货价格继续上涨">亚洲醋酸乙烯市场现货价格继续上涨</a></td>
          <td style="text-align:right">2011-03-17</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ea2d882012ec19bcd930c13.jsp" class="toContentMain" title="混合定位促使生成高集成芯片">混合定位促使生成高集成芯片</a></td>
          <td style="text-align:right">2011-03-17</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ea2d882012ec199adbf0c0c.jsp" class="toContentMain" title="十二五中国大半导体产业将迎来黄金发展期">十二五中国大半导体产业将迎来黄金发展期</a></td>
          <td style="text-align:right">2011-03-17</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ea2d882012ec19325990bdb.jsp" class="toContentMain" title="LED照明将成灯具市场核心竞争 发展可观">LED照明将成灯具市场核心竞争 发展可观</a></td>
          <td style="text-align:right">2011-03-17</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ea2d882012ec1925c400bd6.jsp" class="toContentMain" title="大功率LED产品的工作特性介绍">大功率LED产品的工作特性介绍</a></td>
          <td style="text-align:right">2011-03-17</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ea2d882012ec18dd5a40bc8.jsp" class="toContentMain" title="星光一号芯片 改写中国无芯史">星光一号芯片 改写中国无芯史</a></td>
          <td style="text-align:right">2011-03-17</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ea2d882012ec17cb2290baa.jsp" class="toContentMain" title="徐工重磅推出第五代超值型汽车起重机">徐工重磅推出第五代超值型汽车起重机</a></td>
          <td style="text-align:right">2011-03-17</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ea2d882012ec16912c20b7a.jsp" class="toContentMain" title="国内首台载人升降车研发成功">国内首台载人升降车研发成功</a></td>
          <td style="text-align:right">2011-03-17</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ea2d882012ec162836b0b63.jsp" class="toContentMain" title="广西桂来高速公路将开工 项目概算超45亿">广西桂来高速公路将开工 项目概算超45亿</a></td>
          <td style="text-align:right">2011-03-17</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_71.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_73.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：72/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
