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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131fa2f340131fe9651dd015b.jsp" class="toContentMain" title="白糖价格现回落 “糖高宗”大势已去？">白糖价格现回落 “糖高宗”大势已去？</a></td>
          <td style="text-align:right">2011-08-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131fa2f340131fe9160ee015a.jsp" class="toContentMain" title="山东大力发展新能源产业 太阳能热水器居首">山东大力发展新能源产业 太阳能热水器居首</a></td>
          <td style="text-align:right">2011-08-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131fa2f340131fe8e513f0159.jsp" class="toContentMain" title="国内白糖价格上涨动力持续增加">国内白糖价格上涨动力持续增加</a></td>
          <td style="text-align:right">2011-08-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131fa2f340131fe8c22590155.jsp" class="toContentMain" title="大运会闭幕 五金机电业解读新商机">大运会闭幕 五金机电业解读新商机</a></td>
          <td style="text-align:right">2011-08-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131fa2f340131fe8ad1db0153.jsp" class="toContentMain" title="我国包装机械行业发展的优缺点">我国包装机械行业发展的优缺点</a></td>
          <td style="text-align:right">2011-08-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131fa2f340131fe89f28a0152.jsp" class="toContentMain" title="电动车将超过电脑成为锂离子电池最大市场">电动车将超过电脑成为锂离子电池最大市场</a></td>
          <td style="text-align:right">2011-08-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131fa2f340131fe82b5fe014f.jsp" class="toContentMain" title="发改委酝酿基本药物统一定价 或将利好品牌药企">发改委酝酿基本药物统一定价 或将利好品牌药企</a></td>
          <td style="text-align:right">2011-08-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131fa2f340131fe81bb67014e.jsp" class="toContentMain" title="中国市场第三季电视机出货需求欲增19%">中国市场第三季电视机出货需求欲增19%</a></td>
          <td style="text-align:right">2011-08-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131fa2f340131fe7f5fb0014d.jsp" class="toContentMain" title="我国生漆产业需突破当今存在的发展瓶颈">我国生漆产业需突破当今存在的发展瓶颈</a></td>
          <td style="text-align:right">2011-08-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131fa2f340131fe7e69a5014c.jsp" class="toContentMain" title="工程机械制造业托起中部六省第一县">工程机械制造业托起中部六省第一县</a></td>
          <td style="text-align:right">2011-08-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131fa2f340131fe6ac957013d.jsp" class="toContentMain" title="中国水果需求量增大贸易正在走向逆差">中国水果需求量增大贸易正在走向逆差</a></td>
          <td style="text-align:right">2011-08-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131fa2f340131fe690d3d013c.jsp" class="toContentMain" title="电子元器件行业旺季失约 需求启动仍需等待">电子元器件行业旺季失约 需求启动仍需等待</a></td>
          <td style="text-align:right">2011-08-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131f586b50131f96b93620055.jsp" class="toContentMain" title="冰蓄冷中央空调市场大有潜力">冰蓄冷中央空调市场大有潜力</a></td>
          <td style="text-align:right">2011-08-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131f586b50131f9679a060054.jsp" class="toContentMain" title="无线网络视频监控必须将经历的三个阶段">无线网络视频监控必须将经历的三个阶段</a></td>
          <td style="text-align:right">2011-08-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131f586b50131f962914a0053.jsp" class="toContentMain" title="原材料价格玩过山车 倒逼制造业新陈代谢">原材料价格玩过山车 倒逼制造业新陈代谢</a></td>
          <td style="text-align:right">2011-08-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131f586b50131f95d32fe0052.jsp" class="toContentMain" title="连续高温致使五金机电行业迎来更多商机">连续高温致使五金机电行业迎来更多商机</a></td>
          <td style="text-align:right">2011-08-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131f586b50131f95cb7080051.jsp" class="toContentMain" title="限制中国制冷业发展的行业3大现状">限制中国制冷业发展的行业3大现状</a></td>
          <td style="text-align:right">2011-08-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131f586b50131f95b27ef004f.jsp" class="toContentMain" title="电子书销售 3年成长1039%">电子书销售 3年成长1039%</a></td>
          <td style="text-align:right">2011-08-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131f586b50131f959bf80004e.jsp" class="toContentMain" title="为何空气源热泵热水器面临“冰火两重天”">为何空气源热泵热水器面临“冰火两重天”</a></td>
          <td style="text-align:right">2011-08-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131f586b50131f958f043004d.jsp" class="toContentMain" title="国内苯酐市场开盘气氛疲软观望气氛浓厚">国内苯酐市场开盘气氛疲软观望气氛浓厚</a></td>
          <td style="text-align:right">2011-08-24</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_6.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_8.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：7/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
