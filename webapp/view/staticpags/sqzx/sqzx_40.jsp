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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081304e848f01306cbc37512315.jsp" class="toContentMain" title="3G大战蔓延至平板电脑 融合业务优势或能取胜">3G大战蔓延至平板电脑 融合业务优势或能取胜</a></td>
          <td style="text-align:right">2011-06-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081304e848f01306cb85fcf2311.jsp" class="toContentMain" title="螺纹钢价格震荡向下 开始新一轮下跌">螺纹钢价格震荡向下 开始新一轮下跌</a></td>
          <td style="text-align:right">2011-06-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081304e848f01306cb7b2cb230f.jsp" class="toContentMain" title="供应增加导致国内氧化铝价格下滑约7%">供应增加导致国内氧化铝价格下滑约7%</a></td>
          <td style="text-align:right">2011-06-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081304e848f01306ca299e522ee.jsp" class="toContentMain" title="水电油气价改或将提速 资源产品调价预期渐强">水电油气价改或将提速 资源产品调价预期渐强</a></td>
          <td style="text-align:right">2011-06-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081304e848f01306ca2148822ec.jsp" class="toContentMain" title="发改委拟下调煤炭进口增值税 国内煤价或受抑制">发改委拟下调煤炭进口增值税 国内煤价或受抑制</a></td>
          <td style="text-align:right">2011-06-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081304e848f0130677ea5651694.jsp" class="toContentMain" title="钢材4800之下成价值洼地">钢材4800之下成价值洼地</a></td>
          <td style="text-align:right">2011-06-07</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081304e848f0130677e0a3d1682.jsp" class="toContentMain" title="出口退税即调整钢铁或成“重灾区”">出口退税即调整钢铁或成“重灾区”</a></td>
          <td style="text-align:right">2011-06-07</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081304e848f0130677925941678.jsp" class="toContentMain" title="发改委拟下调煤炭进口增值税">发改委拟下调煤炭进口增值税</a></td>
          <td style="text-align:right">2011-06-07</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081304e848f013052f712510634.jsp" class="toContentMain" title="“十二五”中国钢铁需求增速放缓">“十二五”中国钢铁需求增速放缓</a></td>
          <td style="text-align:right">2011-06-03</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081304e848f013052f669090632.jsp" class="toContentMain" title="煤炭储备应急基地开建 运作遵循市场化方式">煤炭储备应急基地开建 运作遵循市场化方式</a></td>
          <td style="text-align:right">2011-06-03</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081304e848f013052f59f460630.jsp" class="toContentMain" title="用世界眼光审视“大矿时代”">用世界眼光审视“大矿时代”</a></td>
          <td style="text-align:right">2011-06-03</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081304e848f013052f30063062c.jsp" class="toContentMain" title="吉林省医药产业增长快速">吉林省医药产业增长快速</a></td>
          <td style="text-align:right">2011-06-03</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813045f6fc013048d2eb3e014a.jsp" class="toContentMain" title="电荒催旺LED产业 业内人士称行业将大洗牌">电荒催旺LED产业 业内人士称行业将大洗牌</a></td>
          <td style="text-align:right">2011-06-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813045f6fc013048d232d50148.jsp" class="toContentMain" title="铅蓄电池业遭环保风暴或影响行业2/3企业">铅蓄电池业遭环保风暴或影响行业2/3企业</a></td>
          <td style="text-align:right">2011-06-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813045f6fc013048ceb844013e.jsp" class="toContentMain" title="环渤海动力煤平均价格连涨11周">环渤海动力煤平均价格连涨11周</a></td>
          <td style="text-align:right">2011-06-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813045f6fc013048ce2dd70135.jsp" class="toContentMain" title="十二五化肥业严控产能增长 重点拓展上游行业">十二五化肥业严控产能增长 重点拓展上游行业</a></td>
          <td style="text-align:right">2011-06-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813045f6fc013048cccc3d0133.jsp" class="toContentMain" title="水泥价格全国领跌 贵州水泥市场或洗牌">水泥价格全国领跌 贵州水泥市场或洗牌</a></td>
          <td style="text-align:right">2011-06-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813045f6fc013048c90b2c0130.jsp" class="toContentMain" title="旱情助涨药材价格 大型企业自己种植谋突围">旱情助涨药材价格 大型企业自己种植谋突围</a></td>
          <td style="text-align:right">2011-06-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813045f6fc013048c6e811012c.jsp" class="toContentMain" title="食用油限价令即将到期 行业或迎全面涨价潮">食用油限价令即将到期 行业或迎全面涨价潮</a></td>
          <td style="text-align:right">2011-06-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813045f6fc013048a9dd1a00b0.jsp" class="toContentMain" title="发改委预警成品油供应　产量增速不及需求">发改委预警成品油供应　产量增速不及需求</a></td>
          <td style="text-align:right">2011-06-01</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_39.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_41.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：40/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
