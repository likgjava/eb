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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f1c10af811e3f.jsp" class="toContentMain" title="发改委紧急通知稳煤价：严查煤企串通涨价">发改委紧急通知稳煤价：严查煤企串通涨价</a></td>
          <td style="text-align:right">2011-04-03</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f1a717a461ca0.jsp" class="toContentMain" title="3月中国制造业采购经理指数为53.4% 指数回升">3月中国制造业采购经理指数为53.4% 指数回升</a></td>
          <td style="text-align:right">2011-04-03</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f15662f441b52.jsp" class="toContentMain" title="孩子春游如何采购零食？">孩子春游如何采购零食？</a></td>
          <td style="text-align:right">2011-04-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f1528882e1ace.jsp" class="toContentMain" title="6万全球客商下月聚山城 采购计划或达80亿">6万全球客商下月聚山城 采购计划或达80亿</a></td>
          <td style="text-align:right">2011-04-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f1524b8441acc.jsp" class="toContentMain" title="天宁检察护航政府采购 33次监督节约率达11%">天宁检察护航政府采购 33次监督节约率达11%</a></td>
          <td style="text-align:right">2011-04-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f1523042c1aca.jsp" class="toContentMain" title="全新酒店用品采购模式 雅库中国酒店用品网上线">全新酒店用品采购模式 雅库中国酒店用品网上线</a></td>
          <td style="text-align:right">2011-04-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f152107571ac8.jsp" class="toContentMain" title="联合利华欲来云南采购红茶">联合利华欲来云南采购红茶</a></td>
          <td style="text-align:right">2011-04-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f15085da51abf.jsp" class="toContentMain" title="蚌埠：近五年来政府集中采购节约9.57亿">蚌埠：近五年来政府集中采购节约9.57亿</a></td>
          <td style="text-align:right">2011-04-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f1506c03d1abc.jsp" class="toContentMain" title="制造业采购经理指数时隔三月再度回升">制造业采购经理指数时隔三月再度回升</a></td>
          <td style="text-align:right">2011-04-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f15046fd61aba.jsp" class="toContentMain" title="日本原料运送难国内造纸业调整采购策略">日本原料运送难国内造纸业调整采购策略</a></td>
          <td style="text-align:right">2011-04-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f150119401ab8.jsp" class="toContentMain" title="台湾外贸协会：大陆赴台采购团6月起连发">台湾外贸协会：大陆赴台采购团6月起连发</a></td>
          <td style="text-align:right">2011-04-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f14fcf4351ab6.jsp" class="toContentMain" title="青岛海尔：节约采购成本 贡献业绩">青岛海尔：节约采购成本 贡献业绩</a></td>
          <td style="text-align:right">2011-04-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f14fa8f5e1ab4.jsp" class="toContentMain" title="巴黎国际服装采购展将会保留中国展团">巴黎国际服装采购展将会保留中国展团</a></td>
          <td style="text-align:right">2011-04-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f14f838201ab2.jsp" class="toContentMain" title="引导文明祭祀 鹿泉采购万支鲜花免费供群众">引导文明祭祀 鹿泉采购万支鲜花免费供群众</a></td>
          <td style="text-align:right">2011-04-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f14f2d7561aae.jsp" class="toContentMain" title="移动大批量采购iPhone 4 水货亦可">移动大批量采购iPhone 4 水货亦可</a></td>
          <td style="text-align:right">2011-04-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f14ef19271aa4.jsp" class="toContentMain" title="业内呼吁应理性采购硫磺">业内呼吁应理性采购硫磺</a></td>
          <td style="text-align:right">2011-04-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f14edcbe21aa2.jsp" class="toContentMain" title="家佳喜厨房采购节4月3日启幕">家佳喜厨房采购节4月3日启幕</a></td>
          <td style="text-align:right">2011-04-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f14ec48561aa0.jsp" class="toContentMain" title="DMC2011同期活动 专业用户邀请已开展 采购商云集">DMC2011同期活动 专业用户邀请已开展 采购商云集</a></td>
          <td style="text-align:right">2011-04-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f14e86bc71a9e.jsp" class="toContentMain" title="自治区去年政府采购规模263亿多 节约资金31亿多">自治区去年政府采购规模263亿多 节约资金31亿多</a></td>
          <td style="text-align:right">2011-04-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f14e5c0031a9c.jsp" class="toContentMain" title="汽车零部件采购热情不减 采购会模式面临转变">汽车零部件采购热情不减 采购会模式面临转变</a></td>
          <td style="text-align:right">2011-04-02</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_64.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_66.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：65/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
