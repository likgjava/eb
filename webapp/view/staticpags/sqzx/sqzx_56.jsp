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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f6b66a97808e4.jsp" class="toContentMain" title="国统股份、青龙管业拿下南水北调采购大单">国统股份、青龙管业拿下南水北调采购大单</a></td>
          <td style="text-align:right">2011-04-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f6b64ca7c08e2.jsp" class="toContentMain" title="德意志电信和法国电信决定结盟采购设备">德意志电信和法国电信决定结盟采购设备</a></td>
          <td style="text-align:right">2011-04-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f6b60895e08de.jsp" class="toContentMain" title="厦门政府大宗采购产品扩容 采购30万须公开招标">厦门政府大宗采购产品扩容 采购30万须公开招标</a></td>
          <td style="text-align:right">2011-04-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f6b5e57de08dc.jsp" class="toContentMain" title="美洲地区采购商明显增多">美洲地区采购商明显增多</a></td>
          <td style="text-align:right">2011-04-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f6b5d254f08da.jsp" class="toContentMain" title="徐州政府采购大学生创业产品优先">徐州政府采购大学生创业产品优先</a></td>
          <td style="text-align:right">2011-04-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f6b5ad60f08d5.jsp" class="toContentMain" title="OCP与巴西达成磷肥采购协议">OCP与巴西达成磷肥采购协议</a></td>
          <td style="text-align:right">2011-04-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f6b5980a108cb.jsp" class="toContentMain" title="岚山政府采购一季度节约资金2189万元">岚山政府采购一季度节约资金2189万元</a></td>
          <td style="text-align:right">2011-04-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f6b5746dc08c9.jsp" class="toContentMain" title="家居卖场大浪淘沙 家居建材产品采购四大秘籍">家居卖场大浪淘沙 家居建材产品采购四大秘籍</a></td>
          <td style="text-align:right">2011-04-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f67cb9b390697.jsp" class="toContentMain" title="新兴事物物联网给智能家居带来巨大商机">新兴事物物联网给智能家居带来巨大商机</a></td>
          <td style="text-align:right">2011-04-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f67c81f0d0695.jsp" class="toContentMain" title="2010年我国激光医疗器械市场规模超29亿元">2010年我国激光医疗器械市场规模超29亿元</a></td>
          <td style="text-align:right">2011-04-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f67c3ca450693.jsp" class="toContentMain" title="江苏上上集团荣获2010年度惠生工程“合格供应商”称号">江苏上上集团荣获2010年度惠生工程“合格供应商”称号</a></td>
          <td style="text-align:right">2011-04-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f67bf998b068e.jsp" class="toContentMain" title="水电开发大潮即将来临 电缆市场呈商机">水电开发大潮即将来临 电缆市场呈商机</a></td>
          <td style="text-align:right">2011-04-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f67bdb6ca068c.jsp" class="toContentMain" title="山西省电力拉开农网改造升级大幕">山西省电力拉开农网改造升级大幕</a></td>
          <td style="text-align:right">2011-04-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f67ba2256068a.jsp" class="toContentMain" title="五年内贵州装备制造业产值将突破千亿元">五年内贵州装备制造业产值将突破千亿元</a></td>
          <td style="text-align:right">2011-04-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f67b8487d0688.jsp" class="toContentMain" title="十二五期间我国将建2到3个亿吨级铁矿集团">十二五期间我国将建2到3个亿吨级铁矿集团</a></td>
          <td style="text-align:right">2011-04-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f67b45e8a0686.jsp" class="toContentMain" title="徐工高空作业车打开京津地区市场新局面">徐工高空作业车打开京津地区市场新局面</a></td>
          <td style="text-align:right">2011-04-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f67af3c130682.jsp" class="toContentMain" title="新型聚光光伏电池效率达43.5%">新型聚光光伏电池效率达43.5%</a></td>
          <td style="text-align:right">2011-04-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f67ad4c960680.jsp" class="toContentMain" title="内蒙古风电用钢市场包钢抢占 销量本区占有率60%">内蒙古风电用钢市场包钢抢占 销量本区占有率60%</a></td>
          <td style="text-align:right">2011-04-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f67a9f1a7067e.jsp" class="toContentMain" title="液晶面板价格下滑曲线：需求不振">液晶面板价格下滑曲线：需求不振</a></td>
          <td style="text-align:right">2011-04-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f588033012f67a073de067c.jsp" class="toContentMain" title="五一来临 五金产品价格直线上升">五一来临 五金产品价格直线上升</a></td>
          <td style="text-align:right">2011-04-18</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_55.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_57.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：56/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
