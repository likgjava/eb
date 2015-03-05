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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ee243a4012eeac1ffc01a4f.jsp" class="toContentMain" title="3D电视打降价牌消费者反应较冷淡也很理智">3D电视打降价牌消费者反应较冷淡也很理智</a></td>
          <td style="text-align:right">2011-03-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ee243a4012ee6c3e9ed16c8.jsp" class="toContentMain" title="集成吊顶走向市场 安装将会有标准可依">集成吊顶走向市场 安装将会有标准可依</a></td>
          <td style="text-align:right">2011-03-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ee243a4012ee6beccca16b5.jsp" class="toContentMain" title="上游木材原料紧俏 下游地板已被迫涨价">上游木材原料紧俏 下游地板已被迫涨价</a></td>
          <td style="text-align:right">2011-03-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ee243a4012ee6bdf20416b3.jsp" class="toContentMain" title="楼市民退商进 厨卫行业新机遇还是瓶颈">楼市民退商进 厨卫行业新机遇还是瓶颈</a></td>
          <td style="text-align:right">2011-03-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ee243a4012ee6bb3d0116b1.jsp" class="toContentMain" title="中国建材计划将水泥产能扩张到3亿吨">中国建材计划将水泥产能扩张到3亿吨</a></td>
          <td style="text-align:right">2011-03-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ee243a4012ee6b95e7516a9.jsp" class="toContentMain" title="无辐射大理石2011太阳欧洲生态石材上市">无辐射大理石2011太阳欧洲生态石材上市</a></td>
          <td style="text-align:right">2011-03-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ee243a4012ee67c1ec714c9.jsp" class="toContentMain" title="消费者警惕山寨家电 知名品牌遭遇被傍之痛">消费者警惕山寨家电 知名品牌遭遇被傍之痛</a></td>
          <td style="text-align:right">2011-03-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ee243a4012ee5a805e3110f.jsp" class="toContentMain" title="2011年LED照明产能供过于求价格持续下滑">2011年LED照明产能供过于求价格持续下滑</a></td>
          <td style="text-align:right">2011-03-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ee243a4012ee5766b2f10e4.jsp" class="toContentMain" title="家具营销向网络发展　面临新挑战">家具营销向网络发展　面临新挑战</a></td>
          <td style="text-align:right">2011-03-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ee243a4012ee56b07da10da.jsp" class="toContentMain" title="2011地板“四大”产品创意吸引采购者目光">2011地板“四大”产品创意吸引采购者目光</a></td>
          <td style="text-align:right">2011-03-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ee243a4012ee561a8f310d6.jsp" class="toContentMain" title="国产与进口家具环保板材价格相差达80%遭消费者疑惑">国产与进口家具环保板材价格相差达80%遭消费者疑惑</a></td>
          <td style="text-align:right">2011-03-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ed7f020012ee2039b1812cf.jsp" class="toContentMain" title="新兴照明应用激增 LED市场迎旺季">新兴照明应用激增 LED市场迎旺季</a></td>
          <td style="text-align:right">2011-03-23</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ed7f020012ee1cb61c610c2.jsp" class="toContentMain" title="橱柜行业求发展　服务是关键">橱柜行业求发展　服务是关键</a></td>
          <td style="text-align:right">2011-03-23</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ed7f020012ee1c5e0481094.jsp" class="toContentMain" title="中小涂料企业受“折扣战”挑战">中小涂料企业受“折扣战”挑战</a></td>
          <td style="text-align:right">2011-03-23</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ed7f020012ee1a9bc4d0f11.jsp" class="toContentMain" title="加紧研究 橱柜新品欲大胆突破瓶颈">加紧研究 橱柜新品欲大胆突破瓶颈</a></td>
          <td style="text-align:right">2011-03-23</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ed7f020012ee183906f0b92.jsp" class="toContentMain" title="一个小芯片使普通液晶电视画面三维立体">一个小芯片使普通液晶电视画面三维立体</a></td>
          <td style="text-align:right">2011-03-23</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ed7f020012ee17a793b0b54.jsp" class="toContentMain" title="高清机顶盒芯片：本土企业强势介入">高清机顶盒芯片：本土企业强势介入</a></td>
          <td style="text-align:right">2011-03-23</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ed7f020012ee15b100c0a83.jsp" class="toContentMain" title="山钢济钢两种新型材问世 满足用户需求">山钢济钢两种新型材问世 满足用户需求</a></td>
          <td style="text-align:right">2011-03-23</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ed7f020012ee0c257d90953.jsp" class="toContentMain" title="2011年半数显示器将使用LED背光源">2011年半数显示器将使用LED背光源</a></td>
          <td style="text-align:right">2011-03-23</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ed7f020012ee07581eb089a.jsp" class="toContentMain" title="新型无卤低烟阻燃电缆料通过鉴定">新型无卤低烟阻燃电缆料通过鉴定</a></td>
          <td style="text-align:right">2011-03-23</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_69.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_71.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：70/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
