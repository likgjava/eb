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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a70130fcf7803302a4.jsp" class="toContentMain" title="中国汽车产销未来有望达到5000万辆">中国汽车产销未来有望达到5000万辆</a></td>
          <td style="text-align:right">2011-07-06</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a70130fcf6812f02a2.jsp" class="toContentMain" title="铁矿石价格短期仍高位震荡 大幅调整或难现">铁矿石价格短期仍高位震荡 大幅调整或难现</a></td>
          <td style="text-align:right">2011-07-06</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a70130fcf5ab0c029e.jsp" class="toContentMain" title="部分洋奶粉借新品上市之机涨价10%">部分洋奶粉借新品上市之机涨价10%</a></td>
          <td style="text-align:right">2011-07-06</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a70130fcf51940029c.jsp" class="toContentMain" title="未来两年建筑节能市场规模4000亿元">未来两年建筑节能市场规模4000亿元</a></td>
          <td style="text-align:right">2011-07-06</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a70130fcf47055029a.jsp" class="toContentMain" title="整车销量持续下滑 市场再望刺激政策">整车销量持续下滑 市场再望刺激政策</a></td>
          <td style="text-align:right">2011-07-06</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a70130fce9fedd01b0.jsp" class="toContentMain" title="洋奶粉涨价根源在需求">洋奶粉涨价根源在需求</a></td>
          <td style="text-align:right">2011-07-06</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a70130fce886b501aa.jsp" class="toContentMain" title="成本支撑需求释放 钢材市场有望迎来反弹">成本支撑需求释放 钢材市场有望迎来反弹</a></td>
          <td style="text-align:right">2011-07-06</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a70130f7f36f4c6e7a.jsp" class="toContentMain" title="塑化剂风波凸显中国茶叶的社会价值">塑化剂风波凸显中国茶叶的社会价值</a></td>
          <td style="text-align:right">2011-07-05</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a70130f7f1c99b6e78.jsp" class="toContentMain" title="轮胎工业节能减排实现革命性突破">轮胎工业节能减排实现革命性突破</a></td>
          <td style="text-align:right">2011-07-05</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a70130f7e8a52a6e74.jsp" class="toContentMain" title="糖高宗卷土重来 本周将再度抛储">糖高宗卷土重来 本周将再度抛储</a></td>
          <td style="text-align:right">2011-07-05</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a70130f7e815066e72.jsp" class="toContentMain" title="外资冰箱全球丧失战略主导权">外资冰箱全球丧失战略主导权</a></td>
          <td style="text-align:right">2011-07-05</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a70130f7cf74676e41.jsp" class="toContentMain" title="从5100上市背后看瓶装水市场标准需求">从5100上市背后看瓶装水市场标准需求</a></td>
          <td style="text-align:right">2011-07-05</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a70130f7ce7b1d6e3f.jsp" class="toContentMain" title="家具漆企业挖掘客户潜在需求成为发展关键">家具漆企业挖掘客户潜在需求成为发展关键</a></td>
          <td style="text-align:right">2011-07-05</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a70130f2b8dc370aad.jsp" class="toContentMain" title="2010年大陆半导体产业总产值超过1500亿元">2010年大陆半导体产业总产值超过1500亿元</a></td>
          <td style="text-align:right">2011-07-04</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a70130f2b1927b0a9a.jsp" class="toContentMain" title="十二五中国轮胎企业要进入世界前十">十二五中国轮胎企业要进入世界前十</a></td>
          <td style="text-align:right">2011-07-04</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a70130f2a2895e0a7a.jsp" class="toContentMain" title="北京将投2亿推广太阳能热水">北京将投2亿推广太阳能热水</a></td>
          <td style="text-align:right">2011-07-04</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a70130f2a16fd70a74.jsp" class="toContentMain" title="能源局强推风电功率预测系统">能源局强推风电功率预测系统</a></td>
          <td style="text-align:right">2011-07-04</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a70130f2a046b10a62.jsp" class="toContentMain" title="光伏行业陷入持续低迷 业内达成战略联盟抵御风险">光伏行业陷入持续低迷 业内达成战略联盟抵御风险</a></td>
          <td style="text-align:right">2011-07-04</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a70130f29f6c470a60.jsp" class="toContentMain" title="北大荒将赴阿根廷种地 业内称难解中国大豆危局">北大荒将赴阿根廷种地 业内称难解中国大豆危局</a></td>
          <td style="text-align:right">2011-07-04</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130db80a70130f29ec9840a58.jsp" class="toContentMain" title="国内钢市缺乏利好支撑 弱势运行态势难改">国内钢市缺乏利好支撑 弱势运行态势难改</a></td>
          <td style="text-align:right">2011-07-04</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_31.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_33.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：32/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
