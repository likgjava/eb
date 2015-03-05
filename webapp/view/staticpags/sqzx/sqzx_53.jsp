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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f6dbbe8012f70eccc6202b4.jsp" class="toContentMain" title="中国照明要找到真正优势 需权衡整体发展">中国照明要找到真正优势 需权衡整体发展</a></td>
          <td style="text-align:right">2011-04-20</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f6dbbe8012f70eb0d9602a0.jsp" class="toContentMain" title="济南7万余盏路灯实现集中控制“按需照明”">济南7万余盏路灯实现集中控制“按需照明”</a></td>
          <td style="text-align:right">2011-04-20</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f6dbbe8012f70e841fc028c.jsp" class="toContentMain" title="安徽“十二五”公共建筑执行65%的节能标准">安徽“十二五”公共建筑执行65%的节能标准</a></td>
          <td style="text-align:right">2011-04-20</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f6dbbe8012f70e559e4027e.jsp" class="toContentMain" title="LED商业照明兵家必争 创新设计成亮点">LED商业照明兵家必争 创新设计成亮点</a></td>
          <td style="text-align:right">2011-04-20</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f6dbbe8012f70e034200272.jsp" class="toContentMain" title="泉州十二五电网建设总投资约155.5亿元">泉州十二五电网建设总投资约155.5亿元</a></td>
          <td style="text-align:right">2011-04-20</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f6dbbe8012f70de8135026a.jsp" class="toContentMain" title="江苏沿海风电开发十二五期间将把建设重点逐步由陆地向海上推进">江苏沿海风电开发十二五期间将把建设重点逐步由陆地向海上推进</a></td>
          <td style="text-align:right">2011-04-20</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f6dbbe8012f70db066a0260.jsp" class="toContentMain" title="雷州积极推进政企办公自动化建设">雷州积极推进政企办公自动化建设</a></td>
          <td style="text-align:right">2011-04-20</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f6dbbe8012f70d857400251.jsp" class="toContentMain" title="协同办公软件成为软件渠道商香饽饽 OA市场火爆">协同办公软件成为软件渠道商香饽饽 OA市场火爆</a></td>
          <td style="text-align:right">2011-04-20</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f6dbbe8012f70d5b1b7023d.jsp" class="toContentMain" title="“宜宾早茶”到广州 上万茶礼免费送">“宜宾早茶”到广州 上万茶礼免费送</a></td>
          <td style="text-align:right">2011-04-20</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f6dbbe8012f70d3d9c30217.jsp" class="toContentMain" title="古丈春茶生产形势喜人">古丈春茶生产形势喜人</a></td>
          <td style="text-align:right">2011-04-20</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f6dbbe8012f70d264290215.jsp" class="toContentMain" title="浙江松阳茶叶由“只能喝”变为“还能吃”">浙江松阳茶叶由“只能喝”变为“还能吃”</a></td>
          <td style="text-align:right">2011-04-20</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f6dbbe8012f70cf23f301fc.jsp" class="toContentMain" title="山东曲阜:政采“零距离”服务高铁建设">山东曲阜:政采“零距离”服务高铁建设</a></td>
          <td style="text-align:right">2011-04-20</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f6dbbe8012f70b4bc6e015c.jsp" class="toContentMain" title="打造坚强智能电网 宽带PLC势在必然">打造坚强智能电网 宽带PLC势在必然</a></td>
          <td style="text-align:right">2011-04-20</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f6dbbe8012f70b1ee030159.jsp" class="toContentMain" title="天津生态城智能电网年内建成">天津生态城智能电网年内建成</a></td>
          <td style="text-align:right">2011-04-20</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f6dbbe8012f70a9d374012f.jsp" class="toContentMain" title="我国谋划世界最高电压长距离输电工程">我国谋划世界最高电压长距离输电工程</a></td>
          <td style="text-align:right">2011-04-20</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f6dbbe8012f70a87bb20122.jsp" class="toContentMain" title="“十二五”期间华北地区特高压建设预计投资315亿元">“十二五”期间华北地区特高压建设预计投资315亿元</a></td>
          <td style="text-align:right">2011-04-20</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f6dbbe8012f70a43a720105.jsp" class="toContentMain" title="华泰圣达菲1.8T成政府采购SUV新标准">华泰圣达菲1.8T成政府采购SUV新标准</a></td>
          <td style="text-align:right">2011-04-20</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f6dbbe8012f709f52b900de.jsp" class="toContentMain" title="惠普“智慧”系列全能激光一体机采购热荐">惠普“智慧”系列全能激光一体机采购热荐</a></td>
          <td style="text-align:right">2011-04-20</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f6dbbe8012f7099e7a200bc.jsp" class="toContentMain" title="广交会最新动态：日商大量采购五金建材">广交会最新动态：日商大量采购五金建材</a></td>
          <td style="text-align:right">2011-04-20</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f6dbbe8012f7095ff9a00a4.jsp" class="toContentMain" title="波音计划未来五年中国采购额翻番">波音计划未来五年中国采购额翻番</a></td>
          <td style="text-align:right">2011-04-20</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_52.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_54.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：53/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
