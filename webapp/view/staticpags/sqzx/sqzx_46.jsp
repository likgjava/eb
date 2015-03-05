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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fbfbecc012fd2c9cb7807fb.jsp" class="toContentMain" title="医疗器械产业借力创新打造“深圳军团”">医疗器械产业借力创新打造“深圳军团”</a></td>
          <td style="text-align:right">2011-05-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fbfbecc012fd298da460785.jsp" class="toContentMain" title="电企煤企双双怠工 理顺价格势在必行">电企煤企双双怠工 理顺价格势在必行</a></td>
          <td style="text-align:right">2011-05-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fbfbecc012fd2944d200783.jsp" class="toContentMain" title="“心态型”行情难改国内钢市“平台期”特征">“心态型”行情难改国内钢市“平台期”特征</a></td>
          <td style="text-align:right">2011-05-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fbfbecc012fd28f9a3f0781.jsp" class="toContentMain" title="国家邮政局：3月份快递业务收入61.8亿元">国家邮政局：3月份快递业务收入61.8亿元</a></td>
          <td style="text-align:right">2011-05-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fbfbecc012fd28bf07e0763.jsp" class="toContentMain" title="油价上升影响我国沿海航运企业亏损面约30%">油价上升影响我国沿海航运企业亏损面约30%</a></td>
          <td style="text-align:right">2011-05-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fbfbecc012fd2887e830756.jsp" class="toContentMain" title="“电荒”蔓延 电企纷纷加快煤电一体化">“电荒”蔓延 电企纷纷加快煤电一体化</a></td>
          <td style="text-align:right">2011-05-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fbfbecc012fd2820ba80732.jsp" class="toContentMain" title="6月原油期货跌9.44美元 至每桶99.80美元">6月原油期货跌9.44美元 至每桶99.80美元</a></td>
          <td style="text-align:right">2011-05-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fbfbecc012fd27f87930730.jsp" class="toContentMain" title="预计美国今夏天然气价格将下跌50美分">预计美国今夏天然气价格将下跌50美分</a></td>
          <td style="text-align:right">2011-05-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fbfbecc012fd27d267d072e.jsp" class="toContentMain" title="山东医药流通行业发展迎来新活力">山东医药流通行业发展迎来新活力</a></td>
          <td style="text-align:right">2011-05-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fbfbecc012fd235b561066d.jsp" class="toContentMain" title="国内六大“洋奶粉”企业“顶风涨价”">国内六大“洋奶粉”企业“顶风涨价”</a></td>
          <td style="text-align:right">2011-05-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fbfbecc012fd22e5224065a.jsp" class="toContentMain" title="中美战略与经济对话讨论四议题">中美战略与经济对话讨论四议题</a></td>
          <td style="text-align:right">2011-05-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fbfbecc012fc3718f5402c2.jsp" class="toContentMain" title="商务部严肃查处成品油批发企业价格违法行为">商务部严肃查处成品油批发企业价格违法行为</a></td>
          <td style="text-align:right">2011-05-06</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fbfbecc012fc3522ca70277.jsp" class="toContentMain" title="河南开展家电以旧换新工作专项检查行动">河南开展家电以旧换新工作专项检查行动</a></td>
          <td style="text-align:right">2011-05-06</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fbfbecc012fc30efea60124.jsp" class="toContentMain" title="调控与转型助推二三线家具市场">调控与转型助推二三线家具市场</a></td>
          <td style="text-align:right">2011-05-06</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fbfbecc012fc30794e40122.jsp" class="toContentMain" title="我国光伏产业或将重演“德国行情”">我国光伏产业或将重演“德国行情”</a></td>
          <td style="text-align:right">2011-05-06</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fbfbecc012fc2f9bb23011c.jsp" class="toContentMain" title="食糖连续第三年减产 糖价高位运行或将成常态">食糖连续第三年减产 糖价高位运行或将成常态</a></td>
          <td style="text-align:right">2011-05-06</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fbfbecc012fc2f1598b00f2.jsp" class="toContentMain" title="纺织服装出口退税率短期或难下调">纺织服装出口退税率短期或难下调</a></td>
          <td style="text-align:right">2011-05-06</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fbfbecc012fc2e9e84700e8.jsp" class="toContentMain" title="商务部明确未来五年药品流通行业发展目标">商务部明确未来五年药品流通行业发展目标</a></td>
          <td style="text-align:right">2011-05-06</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fbfbecc012fc2c940ad00d8.jsp" class="toContentMain" title="数年鲜见：铁道部首季亏损37.6亿元">数年鲜见：铁道部首季亏损37.6亿元</a></td>
          <td style="text-align:right">2011-05-06</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fba4e32012fbeb1112a0595.jsp" class="toContentMain" title="红木市场再掀高潮受追捧 多路商人重金杀入">红木市场再掀高潮受追捧 多路商人重金杀入</a></td>
          <td style="text-align:right">2011-05-05</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_45.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_47.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：46/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
