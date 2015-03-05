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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130afdee10130b4bc277903ea.jsp" class="toContentMain" title="加拿大4月零售销售月比上升0.3%">加拿大4月零售销售月比上升0.3%</a></td>
          <td style="text-align:right">2011-06-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130ab37e40130afc843880627.jsp" class="toContentMain" title="3D电视后继无力 智能电视或成救命稻草">3D电视后继无力 智能电视或成救命稻草</a></td>
          <td style="text-align:right">2011-06-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130ab37e40130afc400e20600.jsp" class="toContentMain" title="“奶业标准”需要多方利益博弈">“奶业标准”需要多方利益博弈</a></td>
          <td style="text-align:right">2011-06-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130ab37e40130afc09caa05f4.jsp" class="toContentMain" title="香陈九畹芳兰气 普洱茶回归理性收藏">香陈九畹芳兰气 普洱茶回归理性收藏</a></td>
          <td style="text-align:right">2011-06-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130ab37e40130afb17e5b05b4.jsp" class="toContentMain" title="前5月兰州CPI同比上涨5.7% 食品类涨幅居首">前5月兰州CPI同比上涨5.7% 食品类涨幅居首</a></td>
          <td style="text-align:right">2011-06-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130ab37e40130afa133030580.jsp" class="toContentMain" title="智能电视“中国标准”有望推向国际市场">智能电视“中国标准”有望推向国际市场</a></td>
          <td style="text-align:right">2011-06-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130ab37e40130af9fe89a050b.jsp" class="toContentMain" title="国内啤酒业下半年涨价预期强烈">国内啤酒业下半年涨价预期强烈</a></td>
          <td style="text-align:right">2011-06-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130ab37e40130af9e26fb0507.jsp" class="toContentMain" title="前五月太原社会消费品零售额226.80亿元">前五月太原社会消费品零售额226.80亿元</a></td>
          <td style="text-align:right">2011-06-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081309cd86d0130aaa4b6950175.jsp" class="toContentMain" title="墨西哥进口中国牛仔布占总产量36%">墨西哥进口中国牛仔布占总产量36%</a></td>
          <td style="text-align:right">2011-06-20</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081309cd86d0130aa9fbeb70167.jsp" class="toContentMain" title="谁“绑架”了国内奶业标准">谁“绑架”了国内奶业标准</a></td>
          <td style="text-align:right">2011-06-20</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081309cd86d0130aa9c45d5015b.jsp" class="toContentMain" title="5月上旬以来全国水产品价格持续上涨">5月上旬以来全国水产品价格持续上涨</a></td>
          <td style="text-align:right">2011-06-20</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081309cd86d0130aa966fc30145.jsp" class="toContentMain" title="童装市场呈现井喷之势 参加专业展会抢占先机">童装市场呈现井喷之势 参加专业展会抢占先机</a></td>
          <td style="text-align:right">2011-06-20</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081309cd86d0130aa9580570140.jsp" class="toContentMain" title="联合国预计未来10年全球粮价继续上涨">联合国预计未来10年全球粮价继续上涨</a></td>
          <td style="text-align:right">2011-06-20</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081309cd86d0130aa88faab00f9.jsp" class="toContentMain" title="中印需求强劲 铁矿石“盛宴”何时休?">中印需求强劲 铁矿石“盛宴”何时休?</a></td>
          <td style="text-align:right">2011-06-20</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081309cd86d0130aa87dc6c00dc.jsp" class="toContentMain" title="多晶硅行业准入动真格 国内需求启动提速">多晶硅行业准入动真格 国内需求启动提速</a></td>
          <td style="text-align:right">2011-06-20</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081309cd86d0130aa77050c0011.jsp" class="toContentMain" title="下半年：钢价维持振荡有色整体偏弱">下半年：钢价维持振荡有色整体偏弱</a></td>
          <td style="text-align:right">2011-06-20</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081309200df01309b6b573705fa.jsp" class="toContentMain" title="投标商应用电子化招标平台投标反馈良好">投标商应用电子化招标平台投标反馈良好</a></td>
          <td style="text-align:right">2011-06-17</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081309200df01309b63d9050555.jsp" class="toContentMain" title="国信招标集团首次应用电子化平台公开招标">国信招标集团首次应用电子化平台公开招标</a></td>
          <td style="text-align:right">2011-06-17</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081309200df01309b2f09cd03a1.jsp" class="toContentMain" title="伊利举报门事件多方回应">伊利举报门事件多方回应</a></td>
          <td style="text-align:right">2011-06-17</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081309200df01309b2ce8de039b.jsp" class="toContentMain" title="全球服装制造业产值增长12.8％">全球服装制造业产值增长12.8％</a></td>
          <td style="text-align:right">2011-06-17</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_35.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_37.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：36/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
