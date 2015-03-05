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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813140dc4701314a49b2650b10.jsp" class="toContentMain" title="中国继续大力补贴太阳能行业">中国继续大力补贴太阳能行业</a></td>
          <td style="text-align:right">2011-07-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813140dc4701314a4180aa0b09.jsp" class="toContentMain" title="温家宝强调：尽快明确新能源汽车发展要集中解决问题">温家宝强调：尽快明确新能源汽车发展要集中解决问题</a></td>
          <td style="text-align:right">2011-07-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813140dc4701314a40f8070b08.jsp" class="toContentMain" title="前5月化肥利润增长超七成 下半年景气有望持续">前5月化肥利润增长超七成 下半年景气有望持续</a></td>
          <td style="text-align:right">2011-07-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813140dc4701314a4071a70b07.jsp" class="toContentMain" title="前5月水泥利润增长1.7倍 价值回升成行业共识">前5月水泥利润增长1.7倍 价值回升成行业共识</a></td>
          <td style="text-align:right">2011-07-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813140dc4701314a3fc1d30b06.jsp" class="toContentMain" title="赣州提高稀土分离行业门槛 民企或批量出局">赣州提高稀土分离行业门槛 民企或批量出局</a></td>
          <td style="text-align:right">2011-07-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813140dc4701314a3e1b670b04.jsp" class="toContentMain" title="稀土价格一路上涨 节能灯推广项目成烫手山芋">稀土价格一路上涨 节能灯推广项目成烫手山芋</a></td>
          <td style="text-align:right">2011-07-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813140dc4701314a3c61d40b03.jsp" class="toContentMain" title="海上风电实施细则出台 引导向深水离岸布局">海上风电实施细则出台 引导向深水离岸布局</a></td>
          <td style="text-align:right">2011-07-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813140dc4701314a3346cb0afa.jsp" class="toContentMain" title="阀门需求量增大 阀门供应商机遇与挑战并存">阀门需求量增大 阀门供应商机遇与挑战并存</a></td>
          <td style="text-align:right">2011-07-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813140dc4701314a3148140af9.jsp" class="toContentMain" title="八一钢铁：区域需求强劲 产品溢价空间再扩大">八一钢铁：区域需求强劲 产品溢价空间再扩大</a></td>
          <td style="text-align:right">2011-07-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813140dc4701314a3088b80af7.jsp" class="toContentMain" title="东盟市场需求扩大带动我国食品机械出口">东盟市场需求扩大带动我国食品机械出口</a></td>
          <td style="text-align:right">2011-07-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813140dc4701314a30127d0af6.jsp" class="toContentMain" title="水泥产业需求仍处于增长状态">水泥产业需求仍处于增长状态</a></td>
          <td style="text-align:right">2011-07-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813140dc470131453655230350.jsp" class="toContentMain" title="太阳能市场：农村包围城市 转型迫在眉睫">太阳能市场：农村包围城市 转型迫在眉睫</a></td>
          <td style="text-align:right">2011-07-20</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813140dc470131453403a3034f.jsp" class="toContentMain" title="太阳能PK空气能 谁能独霸热水器市场">太阳能PK空气能 谁能独霸热水器市场</a></td>
          <td style="text-align:right">2011-07-20</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813140dc470131452e9048034d.jsp" class="toContentMain" title="2015年ARM芯片在笔记本市场份额将达22.9%">2015年ARM芯片在笔记本市场份额将达22.9%</a></td>
          <td style="text-align:right">2011-07-20</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813140dc470131452ced34034c.jsp" class="toContentMain" title="欧盟标准今实施　国内玩具行业或加速洗牌">欧盟标准今实施　国内玩具行业或加速洗牌</a></td>
          <td style="text-align:right">2011-07-20</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813140dc470131452c1b50034a.jsp" class="toContentMain" title="铅酸蓄电池行业调整 骆驼股份中长期受益">铅酸蓄电池行业调整 骆驼股份中长期受益</a></td>
          <td style="text-align:right">2011-07-20</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813140dc470131452b6f020349.jsp" class="toContentMain" title="电子元器件国内市场趋饱和 外贸发展成趋向">电子元器件国内市场趋饱和 外贸发展成趋向</a></td>
          <td style="text-align:right">2011-07-20</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813140dc470131452a68ca0348.jsp" class="toContentMain" title="老茅台投资价值不逊拉菲 收藏价值在于年份">老茅台投资价值不逊拉菲 收藏价值在于年份</a></td>
          <td style="text-align:right">2011-07-20</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813140dc470131451efb0a0340.jsp" class="toContentMain" title="国内光伏企业看好美国市场潜力越洋淘金">国内光伏企业看好美国市场潜力越洋淘金</a></td>
          <td style="text-align:right">2011-07-20</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813140dc470131451d15f0033f.jsp" class="toContentMain" title="大豆需求八成靠进口 今年缺口将超4000万吨">大豆需求八成靠进口 今年缺口将超4000万吨</a></td>
          <td style="text-align:right">2011-07-20</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_23.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_25.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：24/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
