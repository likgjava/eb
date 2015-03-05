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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f057ae3ed0340.jsp" class="toContentMain" title="3月30日豆粕市场预测及采购建议">3月30日豆粕市场预测及采购建议</a></td>
          <td style="text-align:right">2011-03-30</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f057793c6033c.jsp" class="toContentMain" title="风筝产品迎来旺季 凉鞋批量采购启动">风筝产品迎来旺季 凉鞋批量采购启动</a></td>
          <td style="text-align:right">2011-03-30</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f05755d01033a.jsp" class="toContentMain" title="亳州将举办药企生产设备采购洽谈会">亳州将举办药企生产设备采购洽谈会</a></td>
          <td style="text-align:right">2011-03-30</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f057326e60338.jsp" class="toContentMain" title="武汉打造“智慧城市” 试水新型服务类政府采购">武汉打造“智慧城市” 试水新型服务类政府采购</a></td>
          <td style="text-align:right">2011-03-30</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f057135450336.jsp" class="toContentMain" title="日本大量跨国采购引担忧 不会加剧中国通胀？">日本大量跨国采购引担忧 不会加剧中国通胀？</a></td>
          <td style="text-align:right">2011-03-30</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f056d3a2c0239.jsp" class="toContentMain" title="第五届华东五金机电建材采购节即将开幕">第五届华东五金机电建材采购节即将开幕</a></td>
          <td style="text-align:right">2011-03-30</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f056b9e600237.jsp" class="toContentMain" title="中国移动将于近期启动TD-LTE试验终端采购">中国移动将于近期启动TD-LTE试验终端采购</a></td>
          <td style="text-align:right">2011-03-30</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f05693b300235.jsp" class="toContentMain" title="日立空调入选中国房地产500强采购首选品牌">日立空调入选中国房地产500强采购首选品牌</a></td>
          <td style="text-align:right">2011-03-30</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f0566917f0233.jsp" class="toContentMain" title="回扣惊人专业杀熟 建材采购中不得不知的“潜规则”">回扣惊人专业杀熟 建材采购中不得不知的“潜规则”</a></td>
          <td style="text-align:right">2011-03-30</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f05638cde0231.jsp" class="toContentMain" title="卫生部：医疗卫生机构须加强医学装备采购管理">卫生部：医疗卫生机构须加强医学装备采购管理</a></td>
          <td style="text-align:right">2011-03-30</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f0561c499022f.jsp" class="toContentMain" title="江淮瑞风喜获西北最大驾校批量采购">江淮瑞风喜获西北最大驾校批量采购</a></td>
          <td style="text-align:right">2011-03-30</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812eec2137012f003dbb5f1a56.jsp" class="toContentMain" title="新疆：昌吉州政府采购出新办法">新疆：昌吉州政府采购出新办法</a></td>
          <td style="text-align:right">2011-03-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812eec2137012f0036640519dd.jsp" class="toContentMain" title="初探国内汽车零部件新技术市场发展趋势">初探国内汽车零部件新技术市场发展趋势</a></td>
          <td style="text-align:right">2011-03-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812eec2137012f0029bd4119d0.jsp" class="toContentMain" title="防辐射概念席卷五金圈 真假迷人眼">防辐射概念席卷五金圈 真假迷人眼</a></td>
          <td style="text-align:right">2011-03-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812eec2137012effbc25ad19b2.jsp" class="toContentMain" title="高清家用投影机价格破万 开始吸引市民目光">高清家用投影机价格破万 开始吸引市民目光</a></td>
          <td style="text-align:right">2011-03-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812eec2137012effb7985619ae.jsp" class="toContentMain" title="严把关市场关 拒绝伪智能电视混入市场">严把关市场关 拒绝伪智能电视混入市场</a></td>
          <td style="text-align:right">2011-03-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812eec2137012effabfb9d19a0.jsp" class="toContentMain" title="调查：八成燃气壁挂炉未配智能温控器">调查：八成燃气壁挂炉未配智能温控器</a></td>
          <td style="text-align:right">2011-03-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812eec2137012eff956d1b1994.jsp" class="toContentMain" title="2011地板展商四新模式 创新产品寻出路">2011地板展商四新模式 创新产品寻出路</a></td>
          <td style="text-align:right">2011-03-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812eec2137012eff52b3b11976.jsp" class="toContentMain" title="绿茶上市最佳时机未到 消费者需谨防假茶">绿茶上市最佳时机未到 消费者需谨防假茶</a></td>
          <td style="text-align:right">2011-03-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812eec2137012eff26a9201896.jsp" class="toContentMain" title="电线电缆行业 乱象之痛">电线电缆行业 乱象之痛</a></td>
          <td style="text-align:right">2011-03-29</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_67.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_69.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：68/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
