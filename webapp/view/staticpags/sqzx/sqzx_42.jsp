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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130361e0401303e78cb4f00ff.jsp" class="toContentMain" title="纺织业高成本不可避免 倒逼纺织企业转型升级">纺织业高成本不可避免 倒逼纺织企业转型升级</a></td>
          <td style="text-align:right">2011-05-30</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130361e0401303e78213000fd.jsp" class="toContentMain" title="铅酸蓄电池行业整风 电动自行车恐长期“过冬”">铅酸蓄电池行业整风 电动自行车恐长期“过冬”</a></td>
          <td style="text-align:right">2011-05-30</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130361e0401303e77295400fb.jsp" class="toContentMain" title="光纤业后年或走出“进口预制棒依赖症”">光纤业后年或走出“进口预制棒依赖症”</a></td>
          <td style="text-align:right">2011-05-30</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130361e0401303e7305cc00f8.jsp" class="toContentMain" title="有机食品 打造沂南品牌新农业">有机食品 打造沂南品牌新农业</a></td>
          <td style="text-align:right">2011-05-30</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130361e0401303e580625000f.jsp" class="toContentMain" title="2010中国零售百强发布 两渝企上榜">2010中国零售百强发布 两渝企上榜</a></td>
          <td style="text-align:right">2011-05-30</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130361e0401303e4f0d590009.jsp" class="toContentMain" title="贾良群：十二五期间钢材市场趋势分析">贾良群：十二五期间钢材市场趋势分析</a></td>
          <td style="text-align:right">2011-05-30</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130361e0401303e4d94c40007.jsp" class="toContentMain" title="中钢协拟推中国铁矿石价格指数">中钢协拟推中国铁矿石价格指数</a></td>
          <td style="text-align:right">2011-05-30</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130361e0401303e4cacc80005.jsp" class="toContentMain" title="前4月进口矿石多花857亿 钢铁业利润率降至2.86%">前4月进口矿石多花857亿 钢铁业利润率降至2.86%</a></td>
          <td style="text-align:right">2011-05-30</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813026ae99013029bb257e000d.jsp" class="toContentMain" title="成品油：调价未动　市场先跌">成品油：调价未动　市场先跌</a></td>
          <td style="text-align:right">2011-05-26</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813026ae99013029b9d52e000b.jsp" class="toContentMain" title="越南自7月早期开始上调铁矿石出口关税至40%">越南自7月早期开始上调铁矿石出口关税至40%</a></td>
          <td style="text-align:right">2011-05-26</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813026ae99013029b7d5960007.jsp" class="toContentMain" title="20亿元煤炭超市项目落户井陉">20亿元煤炭超市项目落户井陉</a></td>
          <td style="text-align:right">2011-05-26</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130210701013024cf8a6f019e.jsp" class="toContentMain" title="粮食批发市场扩容 粮油零售价会比商场便宜约10%">粮食批发市场扩容 粮油零售价会比商场便宜约10%</a></td>
          <td style="text-align:right">2011-05-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130210701013024c96bb60176.jsp" class="toContentMain" title="三大动力助推工程机械产业发展提速 处于历史机遇期">三大动力助推工程机械产业发展提速 处于历史机遇期</a></td>
          <td style="text-align:right">2011-05-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130210701013024c6896f0174.jsp" class="toContentMain" title="工程机械行业国际贸易摩擦原因与应对措施">工程机械行业国际贸易摩擦原因与应对措施</a></td>
          <td style="text-align:right">2011-05-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130210701013024c17b830172.jsp" class="toContentMain" title="薄膜光伏冲关20%市场份额 设备成本压力仍大">薄膜光伏冲关20%市场份额 设备成本压力仍大</a></td>
          <td style="text-align:right">2011-05-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130210701013024bc88600146.jsp" class="toContentMain" title="零售行业报告：估值仍有提升空间">零售行业报告：估值仍有提升空间</a></td>
          <td style="text-align:right">2011-05-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130210701013024b56d060144.jsp" class="toContentMain" title="监管食品添加剂 各国法律有侧重">监管食品添加剂 各国法律有侧重</a></td>
          <td style="text-align:right">2011-05-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130210701013024b38f720142.jsp" class="toContentMain" title="广西侗乡力争做大做强茶叶产业">广西侗乡力争做大做强茶叶产业</a></td>
          <td style="text-align:right">2011-05-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130210701013024b1433e0140.jsp" class="toContentMain" title="4月肯尼亚茶叶产量降出口增">4月肯尼亚茶叶产量降出口增</a></td>
          <td style="text-align:right">2011-05-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130210701013024ae815c013e.jsp" class="toContentMain" title="工程机械产业最大困难是信贷紧缩">工程机械产业最大困难是信贷紧缩</a></td>
          <td style="text-align:right">2011-05-25</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_41.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_43.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：42/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
